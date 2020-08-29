package org.psk.practice.aconex.gedcom.parser;

import org.psk.practice.aconex.gedcom.parser.mapper.GedcomLineMapper;
import org.psk.practice.aconex.gedcom.parser.mapper.LineMapper;
import org.psk.practice.aconex.gedcom.parser.model.Node;
import org.psk.practice.aconex.gedcom.parser.reader.FlatFileReader;
import org.psk.practice.aconex.gedcom.parser.reader.ItemReader;
import org.psk.practice.aconex.gedcom.parser.task.StepBasedTask;
import org.psk.practice.aconex.gedcom.parser.util.NodeTreeBuilder;
import org.psk.practice.aconex.gedcom.parser.util.TreeBuilder;
import org.psk.practice.aconex.gedcom.parser.writer.ItemWriter;
import org.psk.practice.aconex.gedcom.parser.writer.XMLWriter;
import org.psk.practice.aconex.gedcom.parser.writer.XmlBuilder;

/**
 * The Class GedcomParser is the parser class which does the parsing of GEDCOM flat file and converts it into an xml
 * document.
 *
 * @author Pinaki S Kabiraj
 */
public class GedcomParser {

    private final String inputFilePath;
    private final String outputFilePath;

    /**
     * Instantiates a new gedcom parser.
     *
     * @param inputFilePath  the input file path
     * @param outputFilePath the output file path
     */
    public GedcomParser(String inputFilePath, String outputFilePath) {
        this.inputFilePath = inputFilePath;
        this.outputFilePath = outputFilePath;
    }

    /**
     * Parses the file.
     */
    public void parse() {
        LineMapper<Node> lineMapper = new GedcomLineMapper();
        TreeBuilder<Node> treeBuilder = new NodeTreeBuilder();
        XmlBuilder xmlBuilder = new XmlBuilder();
        ItemReader<Node> reader = new FlatFileReader(inputFilePath, lineMapper, treeBuilder);
        ItemWriter<Node> writer = new XMLWriter(outputFilePath, xmlBuilder);

        // Parse and generate output
        new StepBasedTask<Node>(reader, writer).execute(); // Process
    }

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        if (args.length < 4) {
            System.err.println("Arguments are not properly supplied.");
            System.err.println("Arguments: -i <ABS_INPUT_PATH> -o <ABS_OUTPUT_PATH>");
            return;
        }

        String inputFilePath = null;
        String outputFilePath = null;
        int i = 0;
        while (i < args.length) {
            String arg = args[i];
            if (arg.equals("-i")) {
                inputFilePath = args[i + 1];
                i += 2;
            } else if (arg.equals("-o")) {
                outputFilePath = args[i + 1];
                i += 2;
            } else {
                i++;
            }
        }

        GedcomParser parser = new GedcomParser(inputFilePath, outputFilePath);
        parser.parse();
    }
}
