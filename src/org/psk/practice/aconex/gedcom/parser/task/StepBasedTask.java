package org.psk.practice.aconex.gedcom.parser.task;

import java.util.ArrayList;
import java.util.List;

import org.psk.practice.aconex.gedcom.parser.reader.ItemReader;
import org.psk.practice.aconex.gedcom.parser.writer.ItemWriter;
import org.psk.practice.aconex.gedcom.parser.exception.ParseException;

/**
 * <p>
 * The Class StepBasedTask to execute the task in a step based manner based on
 * how many main domain object it can create.
 * </p>
 * <p>
 * It reads data in chunk in order to avoid reading data completely and keeping
 * it in memory. This will help reducing the memory consumption. It also helps
 * performing better because it write a chunk of data once and not writing each
 * domain object.
 * </p>
 * 
 * @param <T> the generic type representing domain object.
 * @author Pinaki S Kabiraj
 */
public class StepBasedTask<T> implements Task {

	private static final int MAX_COUNT = 10;

	private ItemReader<T> reader; // To read data
	private ItemWriter<T> writer; // To write domain object
	private int maxCount; // Max number of root domain objects to be created at
							// each step
	private int currentCount = 0; // Current no. of root domain objects read

	/**
	 * Instantiates a new step based task.
	 * 
	 * @param reader the reader
	 * @param writer the writer
	 */
	public StepBasedTask(ItemReader<T> reader, ItemWriter<T> writer) {
		this(reader, writer, MAX_COUNT);
	}

	/**
	 * Instantiates a new step based task.
	 * 
	 * @param reader the reader
	 * @param writer the writer
	 * @param maxCount the max count
	 */
	public StepBasedTask(ItemReader<T> reader, ItemWriter<T> writer, int maxCount) {
		this.reader = reader;
		this.writer = writer;
		this.maxCount = maxCount;
	}

	/*
	 * (non-Javadoc)
	 * @see org.aconex.gedcom.parser.task.Task#execute()
	 */
	@Override
	public void execute() {
		List<T> values = new ArrayList<T>(); // Root domain objects list
		try {
			reader.open(); // Open reader to start reading
			writer.open(); // Open writer to start writing
			while (reader.canRead()) { // Continue till reader can read data
				// In one chunk keep reading the data and pass the list to write
				while (currentCount < maxCount && reader.canRead()) {
					values.add(reader.read()); // Read data and add
					currentCount++; // Increment current read data count
				}

				if (!values.isEmpty()) { // Write in chunks
					writer.write(values);
					values.clear(); // Reset list
					currentCount = 0; // Reset count
				}
			}
		} catch (Exception e) {
			throw new ParseException("Could not read file.", e);
		} finally {
			reader.close(); // Close the reader
			writer.close(); // CLose the writer
		}
	}
}
