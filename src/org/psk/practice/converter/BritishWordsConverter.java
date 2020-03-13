package org.psk.practice.converter;

/**
 * The Class BritishWordsConverter to convert numbers to British words.
 *
 * @author pkabiraj
 */
public class BritishWordsConverter implements NumberToWordConverter {

    // To do validation of the input
    private final Validator validator;
    // The default processor, which internally will call appropriate processors.
    private final Processor processor;

    public BritishWordsConverter(Validator validator, Processor processor) {
        this.validator = validator;
        this.processor = processor;
    }

    @Override
    public void convert(String val) {
        if (validator.validate(val)) {
            System.out.println(val + " = " + processor.getName(val));
        } else {
            System.out.println("Please provide a valid input. " + validator.getErrorMessage());
        }
    }

    public static void main(String[] args) {
        // The values to be checked. Lowest to highest is specified
        String[] values = new String[]{"0", "4", "21", "4334", "50789", "652374", "7634507",
                "8000001", "8000101", "8001001", "8000067", "999999999", "99999999999912345",
                "-245", "4567.123", "123ab233", "12345678987654321234"};

        NumberToWordConverter converter = new BritishWordsConverter(new NumberValidator(), new DefaultProcessor());

        for (String val : values) {
            converter.convert(val);
        }
    }
}