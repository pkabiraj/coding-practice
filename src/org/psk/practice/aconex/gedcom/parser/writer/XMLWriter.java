package org.psk.practice.aconex.gedcom.parser.writer;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.psk.practice.aconex.gedcom.parser.exception.WriteException;
import org.psk.practice.aconex.gedcom.parser.model.Node;
import org.psk.practice.aconex.gedcom.parser.util.GedcomConstants;
import org.psk.practice.aconex.gedcom.parser.util.IOUtils;
import org.psk.practice.aconex.gedcom.parser.util.StringUtils;

/**
 * The Class XMLWriter writes object data as XML.
 * 
 * @author Pinaki S Kabiraj
 */
public class XMLWriter implements ItemWriter<Node> {

	private String outputFilePath;
	private NodeBuilder builder;
	private OutputStream out;
	private FileOutputStream fos;

	/**
	 * Instantiates a new xML writer.
	 * 
	 * @param outputFilePath the output file path
	 * @param builder the node builder to generate XML data
	 */
	public XMLWriter(String outputFilePath, NodeBuilder builder) {
		if (StringUtils.isBlank(outputFilePath)) {
			throw new IllegalArgumentException("output path cannot be null.");
		}

		this.outputFilePath = outputFilePath;
		this.builder = builder;
	}

	/*
	 * (non-Javadoc)
	 * @see org.aconex.gedcom.parser.writer.ItemWriter#open()
	 */
	@Override
	public void open() {
		// Open stream to write to file
		File file = new File(outputFilePath);
		if (file.exists()) {
			file.delete();
		}
		file.getParentFile().mkdirs();
		try {
			file.createNewFile();
			fos = new FileOutputStream(file);
			out = new BufferedOutputStream(fos);
		} catch (IOException e) {
			throw new WriteException("Could not create file to be written", e);
		}

		// Write starting element
		writeStartElem();
	}

	/*
	 * (non-Javadoc)
	 * @see org.aconex.gedcom.parser.writer.ItemWriter#write(java.util.List)
	 */
	@Override
	public void write(List<? extends Node> items) {
		for (Node item : items) {
			builder.buildNode(item); // Build xml data
			writeAndFlush(builder.toNodeString()); // write data to file
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.aconex.gedcom.parser.writer.ItemWriter#close()
	 */
	@Override
	public void close() {
		// Flush end of data
		String endElem = GedcomConstants.TAG_END_ST + GedcomConstants.TAG_ROOT
				+ GedcomConstants.TAG_END_NEWLINE;
		writeAndFlush(endElem);
		// Close stream
		IOUtils.closeQuietly(fos);
		IOUtils.closeQuietly(out);
	}

	/**
	 * Write start element to output file.
	 */
	private void writeStartElem() {
		// Flush start of xml
		String startElem = GedcomConstants.XML_PROLOG + GedcomConstants.TAG_ROOT
				+ GedcomConstants.TAG_END_NEWLINE;
		writeAndFlush(startElem);
	}

	/**
	 * Write and flush data.
	 * 
	 * @param data the data
	 */
	private void writeAndFlush(String data) {
		try {
			out.write(data.getBytes());
			out.flush();
		} catch (IOException e) {
			throw new WriteException("Write to file failed", e);
		}
	}
}
