package org.psk.practice.aconex.gedcom.parser.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.psk.practice.aconex.gedcom.parser.exception.ParseException;
import org.psk.practice.aconex.gedcom.parser.exception.UnexpectedInputException;
import org.psk.practice.aconex.gedcom.parser.mapper.LineMapper;
import org.psk.practice.aconex.gedcom.parser.model.Node;
import org.psk.practice.aconex.gedcom.parser.util.IOUtils;
import org.psk.practice.aconex.gedcom.parser.util.StringUtils;
import org.psk.practice.aconex.gedcom.parser.util.TreeBuilder;

/**
 * The Class FlatFileReader is for reading flat files like gedcom files line by
 * line and generate the domain object.
 * 
 * @author Pinaki S Kabiraj
 */
public class FlatFileReader implements ItemReader<Node> {

	private LineMapper<Node> lineMapper; // Maps 1 line to domain object
	private TreeBuilder<Node> treeBuilder; // To build domain hierarchy
	private BufferedReader reader; // Reader to read file
	private String input = null; // read line from file
	private Node node = null; // Domain object created out of read line
	private String inputFilePath;

	/**
	 * Instantiates a new flat file reader.
	 * 
	 * @param inputFilePath the input file path
	 * @param lineMapper the line mapper
	 * @param treeBuilder the tree builder
	 */
	public FlatFileReader(String inputFilePath, LineMapper<Node> lineMapper,
			TreeBuilder<Node> treeBuilder) {
		if (StringUtils.isBlank(inputFilePath)) {
			throw new IllegalArgumentException("input path cannot be null.");
		}
		if (lineMapper == null) {
			throw new IllegalArgumentException("Line mapper cannot be null");
		}

		if (treeBuilder == null) {
			throw new IllegalArgumentException("Domain tree builder cannot be null");
		}

		this.inputFilePath = inputFilePath;
		this.lineMapper = lineMapper;
		this.treeBuilder = treeBuilder;
	}

	/*
	 * (non-Javadoc)
	 * @see org.aconex.gedcom.parser.reader.ItemReader#open()
	 */
	@Override
	public void open() {
		try {
			// Create input file reader
			reader = new BufferedReader(new FileReader(inputFilePath));
			// Read first line and keep to get started
			input = reader.readLine();
		} catch (FileNotFoundException e) {
			throw new ParseException("Input file path is not valid.", e);
		} catch (IOException e) {
			throw new ParseException("Could not read input.", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.aconex.gedcom.parser.reader.ItemReader#read()
	 */
	@Override
	public Node read() throws UnexpectedInputException {
		// Keep reading unless EOF is reached
		while (input != null) {
			node = null;
			// Convert line to domain object
			if (StringUtils.isNotBlank(input)) {
				node = lineMapper.mapLine(input);
			}

			try {
				// Read line from file
				input = reader.readLine();
			} catch (IOException e) {
				throw new UnexpectedInputException("Could not read input.", e);
			}

			if (node != null) {
				// Build tree
				treeBuilder.add(node);
				// Check if one node tree is done or not
				if (treeBuilder.isTreeComplete()) {
					// Return the tree
					return treeBuilder.toTree();
				}
			}
		}

		node = null;
		return treeBuilder.toTree(); // Return last tree node
	}

	/*
	 * (non-Javadoc)
	 * @see org.aconex.gedcom.parser.reader.ItemReader#canRead()
	 */
	@Override
	public boolean canRead() {
		return input != null || node != null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.aconex.gedcom.parser.reader.ItemReader#close()
	 */
	@Override
	public void close() {
		IOUtils.closeQuietly(reader);
	}
}