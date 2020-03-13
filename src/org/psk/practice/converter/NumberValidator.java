package org.psk.practice.converter;

/**
 * The Class NumberValidator to validate string input for number.
 *
 * @author pkabiraj
 */
public class NumberValidator implements Validator {

    private String errorMessage = null;
    private final Checker[] checkers;

    /**
     * Instantiates a new number validator.
     */
    public NumberValidator() {
        // Instantiate and assign all the checkers
        checkers = new Checker[4]; // It should be configurable
        checkers[0] = new NumberChecker();
        checkers[1] = new NegativeNumberChecker();
        checkers[2] = new DecimalNumberChecker();
        checkers[3] = new LimitChecker();
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public boolean validate(String input) {
        for (Checker checker : checkers) {
            if (!checker.check(input)) {
                errorMessage = checker.getErrorMessage();
                return false;
            }
        }
        return true;
    }

    /**
     * The Interface Checker which will do the checking of the input.
     */
    private interface Checker {

        /**
         * Check the input for validity.
         *
         * @param input the input
         * @return true, if successful
         */
        boolean check(String input);

        /**
         * Gets the error message.
         *
         * @return the error message
         */
        String getErrorMessage();
    }

    /**
     * The Class NumberChecker to check if the input is a valid number or not.
     */
    private static class NumberChecker implements Checker {
        private String errorMessage = null;

        @Override
        public boolean check(String input) {
            char[] charArray = input.toCharArray();
            for (char ch : charArray) {
                // TODO The code can be improved
                if (ch != '-' && ch != '.') {
                    if (!Character.isDigit(ch)) {
                        errorMessage = "The input " + input + " is not a number.";
                        return false;
                    }
                }
            }
            return true;
        }

        @Override
        public String getErrorMessage() {
            return errorMessage;
        }
    }

    /**
     * The Class NegativeNumberChecker to check if the number is a negative number or not.
     */
    private static class NegativeNumberChecker implements Checker {
        private String errorMessage = null;

        @Override
        public boolean check(String input) {
            if (input.charAt(0) == '-') {
                errorMessage = "The number " + input
                        + " is a negative number. We support only positive number.";
                return false;
            }
            return true;
        }

        @Override
        public String getErrorMessage() {
            return errorMessage;
        }
    }

    /**
     * The Class DecimalNumberChecker to check if the number is a decimal number or not.
     */
    private static class DecimalNumberChecker implements Checker {
        private String errorMessage = null;

        @Override
        public boolean check(String input) {
            if (input.contains(".")) {
                errorMessage = "The number " + input
                        + " is a decimal number. We support only non-decimal numbers.";
                return false;
            }
            return true;
        }

        @Override
        public String getErrorMessage() {
            return errorMessage;
        }
    }

    /**
     * The Class LimitChecker to check if the input exceeds the supporting number limit or not.
     */
    private static class LimitChecker implements Checker {
        private String errorMessage = null;

        @Override
        public boolean check(String input) {
            if (input.length() > ConverterUtil.LIMIT) {
                errorMessage = "Then number " + input + " exceeds the limit we currently support. We support upto "
                        + ConverterUtil.LIMIT + " digit numbers.";
                return false;
            }
            return true;
        }

        @Override
        public String getErrorMessage() {
            return errorMessage;
        }
    }
}