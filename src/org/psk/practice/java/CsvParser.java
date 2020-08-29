package org.psk.practice.java;/* IMPORTANT: class must not be public. */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvParser {

    private Reader reader = null;

    public static void main(String[] args) throws UnexpectedInputException, IOException {
        CsvParser parser = new CsvParser();

        parser.initialize();

        List<? extends Object> objects = parser.parse();

        for (Object object : objects) {
            System.out.println(object);
        }
    }

    private void initialize() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String typeLine = bufferedReader.readLine();

        String type = typeLine.substring(typeLine.indexOf("Type:") + "Type:".length());

        String[] names = bufferedReader.readLine().split(",");

        // Initialize reader
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(names);

        FieldSetMapperFactory fieldSetMapperFactory = new FieldSetMapperFactory();
        FieldSetMapper fieldSetMapper = fieldSetMapperFactory.getMapper(type);

        DefaultLineMapper lineMapper = new DefaultLineMapper();
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        reader = new StdInputReader(bufferedReader, lineMapper);
    }

    public List<? extends Object> parse() throws UnexpectedInputException {
        List<Object> objects = new ArrayList<Object>();

        Object object = null;
        while ((object = reader.read()) != null) {
            objects.add(object);
        }

        return objects;
    }
}

class Employee {

    private String name;
    private int age;
    private int salary;

    public Employee() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Name : " + name + "; Age : " + age;
    }
}

class Department {

    private String code;
    private String name;

    public Department() {
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Code : " + code + ";Name : " + name;
    }
}

/**
 * The interface ItemReader is strategy interface for providing the data.
 *
 * @param <T> the generic type to represent the type of data to be returned
 */
interface Reader<T> {

    /**
     * Reads a piece of input data and advance to the next one. Implementations <strong>must</strong> return
     * <code>null</code> at the end of the input data set.
     *
     * @throws UnexpectedInputException {@inheritDoc}
     */
    T read() throws UnexpectedInputException;
}

/**
 * The Interface LineMapper is for mapping lines (strings) to domain objects typically used to map lines read from a
 * file to domain objects on a per line basis.
 *
 * @param <T> the generic type of domain object
 */
interface LineMapper<T> {

    /**
     * Implementations must implement this method to map the provided line to the parameter type T.
     *
     * @param line to be mapped
     * @return mapped object of type T
     */
    T mapLine(String line) throws Exception;
}

/**
 * Interface that is used to map data obtained from a {@link FieldSet} into an object.
 */
interface FieldSetMapper<T> {

    /**
     * Method used to map data obtained from a {@link FieldSet} into an object.
     *
     * @param fieldSet the {@link FieldSet} to map
     * @throws Exception if there is a problem with the binding
     */
    T mapFieldSet(FieldSet fieldSet);
}

/**
 * Interface that is used by framework to split string obtained typically from a file into tokens.
 */
interface LineTokenizer {

    /**
     * Yields the tokens resulting from the splitting of the supplied <code>line</code>.
     *
     * @param line the line to be tokenized (can be <code>null</code>)
     * @return the resulting tokens
     */
    FieldSet tokenize(String line);
}

/**
 * Class used by flat file input sources to encapsulate concerns of converting an array of Strings to Java native types.
 * A bit like the role played by {@link ResultSet} in JDBC, clients will know the name or position of strongly typed
 * fields that they want to extract.
 */
class FieldSet {

    private final String[] tokens;
    private final List<String> names;

    public FieldSet(String[] tokens, String[] names) {
        this.tokens = tokens.clone();
        this.names = Arrays.asList(names);
    }

    public String readString(int index) {
        return readAndTrim(index);
    }

    public String readString(String name) {
        return readString(indexOf(name));
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof FieldSet) {
            FieldSet fs = (FieldSet) object;

            if (tokens == null) {
                return fs.tokens == null;
            } else {
                return Arrays.equals(tokens, fs.tokens);
            }
        }

        return false;
    }

    @Override
    public int hashCode() {
        // this algorithm was taken from java 1.5 jdk Arrays.hashCode(Object[])
        if (tokens == null) {
            return 0;
        }

        int result = 1;

        for (String token : tokens) {
            result = 31 * result + (token == null ? 0 : token.hashCode());
        }

        return result;
    }

    /**
     * Read and trim the {@link String} value at '<code>index</code>'.
     *
     * @return null if the field value is <code>null</code>.
     */
    private String readAndTrim(int index) {
        String value = tokens[index];

        if (value != null) {
            return value.trim();
        } else {
            return null;
        }
    }

    /**
     * Read and trim the {@link String} value from column with given ' <code>name</code>.
     *
     * @throws IllegalArgumentException if a column with given name is not defined.
     */
    private int indexOf(String name) {
        if (names == null) {
            throw new IllegalArgumentException("Cannot access columns by name without meta data");
        }
        int index = names.indexOf(name);
        if (index >= 0) {
            return index;
        }
        throw new IllegalArgumentException("Cannot access column [" + name + "] from " + names);
    }
}

class DefaultLineMapper<T> implements LineMapper<T> {

    private LineTokenizer tokenizer;
    private FieldSetMapper<T> fieldSetMapper;

    public void setLineTokenizer(LineTokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public void setFieldSetMapper(FieldSetMapper<T> fieldSetMapper) {
        this.fieldSetMapper = fieldSetMapper;
    }

    @Override
    public T mapLine(String line) throws Exception {
        return fieldSetMapper.mapFieldSet(tokenizer.tokenize(line));
    }
}

class DelimitedLineTokenizer implements LineTokenizer {

    /**
     * Convenient constant for the common case of a comma delimiter.
     */
    public static final String DELIMITER_COMMA = ",";

    private String[] names = new String[0];
    // the delimiter character used when reading input.
    private String delimiter;

    /**
     * Create a new instance of the {@link DelimitedLineTokenizer} class for the common case where the delimiter is a
     * {@link #DELIMITER_COMMA comma}.
     *
     * @see #DelimitedLineTokenizer(String)
     * @see #DELIMITER_COMMA
     */
    public DelimitedLineTokenizer() {
        this(DELIMITER_COMMA);
    }

    /**
     * Create a new instance of the {@link DelimitedLineTokenizer} class.
     *
     * @param delimiter the desired delimiter
     */
    public DelimitedLineTokenizer(String delimiter) {
        this.delimiter = delimiter;
    }

    /**
     * Setter for the delimiter character.
     *
     * @param delimiter
     */
    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    /**
     * Setter for column names. Optional, but if set, then all lines must have as many or fewer tokens.
     *
     * @param names
     */
    public void setNames(String[] names) {
        this.names = names == null ? null : names.clone();
    }

    @Override
    public FieldSet tokenize(String line) {
        if (line == null) {
            line = "";
        }

        String[] values = line.split(delimiter);

        if (names.length == values.length) {
            return new FieldSet(values, names);
        }

        return null;
    }
}

/**
 * The Class UnexpectedInputException used to signal an unexpected end of an input or message stream. This is an
 * abnormal condition, not just the end of the data - e.g. if a resource becomes unavailable, or a stream becomes
 * unreadable.
 */
class UnexpectedInputException extends Exception {

    private static final long serialVersionUID = -8121403834516047676L;

    /**
     * Create a new {@link UnexpectedInputException} based on a message.
     *
     * @param message the message for this exception
     */
    public UnexpectedInputException(String message) {
        super(message);
    }

    /**
     * Create a new {@link UnexpectedInputException} based on a message and another exception.
     *
     * @param msg    the message for this exception
     * @param nested the other exception
     */
    public UnexpectedInputException(String msg, Throwable nested) {
        super(msg, nested);
    }
}

/**
 * The Class ParseException indicating that an error has been encountered parsing io, typically from a file.
 */
class ParseException extends RuntimeException {

    private static final long serialVersionUID = -5005889688690229051L;

    /**
     * Create a new {@link ParseException} based on a message and another exception.
     *
     * @param message the message for this exception
     * @param cause   the other exception
     */
    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Create a new {@link ParseException} based on a message.
     *
     * @param message the message for this exception
     */
    public ParseException(String message) {
        super(message);
    }
}

class FieldSetMapperFactory {

    private final Map<String, Class> map;

    public FieldSetMapperFactory() {
        map = new HashMap<String, Class>(2);

        map.put("Employee", EmployeeFieldSetMapper.class);
        map.put("Department", DepartmentFieldSetMapper.class);
    }

    public FieldSetMapper getMapper(String type) {
        try {
            if (map.containsKey(type)) {
                return (FieldSetMapper) map.get(type).newInstance();
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }
}

class EmployeeFieldSetMapper implements FieldSetMapper<Employee> {

    @Override
    public Employee mapFieldSet(FieldSet fieldSet) {
        if (fieldSet == null) {
            return null;
        }

        Employee employee = new Employee();
        employee.setName(fieldSet.readString(0));
        employee.setAge(Integer.parseInt(fieldSet.readString(1)));
        employee.setSalary(Integer.parseInt(fieldSet.readString(2)));

        return employee;
    }
}

class DepartmentFieldSetMapper implements FieldSetMapper<Department> {

    @Override
    public Department mapFieldSet(FieldSet fieldSet) {
        if (fieldSet == null) {
            return null;
        }

        Department department = new Department();
        department.setCode(fieldSet.readString(0));
        department.setName(fieldSet.readString(1));

        return department;
    }
}

class StdInputReader<T> implements Reader<T> {

    private String input = null; // read line from file
    private final LineMapper<T> lineMapper; // Maps 1 line to domain object
    private final BufferedReader reader; // Reader to read file

    public StdInputReader(BufferedReader reader, LineMapper<T> lineMapper) {
        if (reader == null) {
            throw new IllegalArgumentException("Reader cannot be null");
        }
        if (lineMapper == null) {
            throw new IllegalArgumentException("Line mapper cannot be null");
        }

        this.reader = reader;
        this.lineMapper = lineMapper;
    }

    @Override
    public T read() throws UnexpectedInputException {
        try {
            while ((input = reader.readLine()) != null) {
                return lineMapper.mapLine(input);
            }
        } catch (IOException e) {
            throw new UnexpectedInputException(e.getMessage(), e);
        } catch (Exception e) {
            throw new UnexpectedInputException(e.getMessage(), e);
        }

        return null;
    }
}