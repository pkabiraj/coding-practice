package org.psk.practice.converter;

/**
 * The Class DefaultProcessor to process any input value to its corresponding word representation.
 *
 * @author pkabiraj
 */
public class DefaultProcessor implements Processor {

    private static final String ZERO_TOKEN = "Zero";
    private static final String UNION_AND = "And ";
    // Change the number and add Unit enum accordingly if you want bigger nos.
    private final Processor processor = new BigNumberProcessor(ConverterUtil.LIMIT);

    @Override
    public String getName(String value) {
        String name = processor.getName(value);
        if (name.isEmpty()) {
            name = ZERO_TOKEN;
        }
        return name;
    }

    /**
     * The Class UnitProcessor to process two digit numbers upto 19.
     */
    private class UnitProcessor implements Processor {

        private final String[] TOKENS = new String[]{"", "One", "Two", "Three", "Four", "Five",
                                                     "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve",
                                                     "Thirteen", "Fourteen",
                                                     "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};

        @Override
        public String getName(String value) {
            int number = Integer.valueOf(value, 10);
            number %= 100;

            return TOKENS[number % 20];
        }
    }

    /**
     * The Class TensProcessor to process 2 digit numbers less greater than 19.
     */
    private class TensProcessor implements Processor {

        private final String[] TOKENS = new String[]{"Twenty", "Thirty", "Fourty", "Fifty",
                                                     "Sixty", "Seventy", "Eighty", "Ninety"};
        private final Processor unitProcessor = new UnitProcessor();

        @Override
        public String getName(String value) {
            SentenceBuilder builder = new SentenceBuilder();
            boolean tensFound = false;
            int number = Integer.valueOf(value, 10);
            number %= 100; // keep only two digits
            if (number >= 20) {
                builder.add(TOKENS[number / 10 - 2]);
                number %= 10;
                tensFound = true;
            } else {
                number %= 20;
            }

            if (number != 0) {
                if (tensFound) {
                    builder.add(SEPARATOR);
                }
                builder.add(unitProcessor.getName(Integer.toString(number)));
            }

            return builder.toSentence();
        }
    }

    /**
     * The Class HundredProcessor to process 3 digit numbers.
     */
    private class HundredProcessor implements Processor {

        private final int EXPONENT = 2;
        private final Processor unitProcessor = new UnitProcessor();
        private final Processor tensProcessor = new TensProcessor();

        @Override
        public String getName(String value) {
            SentenceBuilder builder = new SentenceBuilder();
            int number;
            if (value.isEmpty()) {
                number = 0;
            } else {
                number = Integer.valueOf(value, 10);
            }
            number %= 1000; // keep at least three digits

            if (number >= 100) {
                builder.add(unitProcessor.getName(Integer.toString(number / 100))).add(SEPARATOR)
                        .add(ConverterUtil.getName(EXPONENT));
            }
            String tensName = tensProcessor.getName(Integer.toString(number % 100));

            if (!tensName.isEmpty()) {
                if (number >= 100) {
                    builder.add(SEPARATOR).add(UNION_AND);
                }
            }
            builder.add(tensName);

            return builder.toSentence();
        }
    }

    /**
     * The Class BigNumberProcessor to process more than 3 digit numbers.
     */
    private class BigNumberProcessor implements Processor {

        private final Processor hundredProcessor = new HundredProcessor();
        private final Processor lowProcessor;
        private final int exponent;

        /**
         * Instantiates a new big number processor.
         *
         * @param exponent the exponent
         */
        public BigNumberProcessor(int exponent) {
            if (exponent <= 3) {
                lowProcessor = hundredProcessor;
            } else {
                lowProcessor = new BigNumberProcessor(exponent - 3);
            }
            this.exponent = exponent;
        }

        /**
         * Gets the word token.
         *
         * @return the token
         */
        public String getToken() {
            return ConverterUtil.getName(exponent);
        }

        @Override
        public String getName(String value) {
            SentenceBuilder builder = new SentenceBuilder();
            String high, low;
            if (value.length() < exponent) {
                high = "";
                low = value;
            } else {
                int index = value.length() - exponent;
                high = value.substring(0, index);
                low = value.substring(index);
            }

            String highName = hundredProcessor.getName(high);
            String lowName = lowProcessor.getName(low);
            if (!highName.isEmpty()) {
                builder.add(highName).add(SEPARATOR).add(getToken());
                if (!lowName.isEmpty()) {
                    builder.add(SEPARATOR);
                    if (lowName.split("[" + SEPARATOR + "]").length <= 2) {
                        builder.add(UNION_AND);
                    }
                }
            }
            if (!lowName.isEmpty()) {
                builder.add(lowName);
            }
            return builder.toSentence();
        }
    }
}