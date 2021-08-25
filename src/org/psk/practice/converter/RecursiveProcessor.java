package org.psk.practice.converter;

public class RecursiveProcessor implements Processor {

    private static final String[] UNIT_TOKENS =
            new String[]{"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven",
                         "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] TENS_TOKENS = new String[]{"Twenty", "Thirty", "Fourty", "Fifty", "Sixty", "Seventy",
                                                      "Eighty", "Ninety"};

    @Override
    public String getName(final String value) {
        return numberToWords(Integer.parseInt(value));
    }

    private String one(int num) {
        if (num < 10) {
            return UNIT_TOKENS[num];
        }
        return "";
    }

    private String twoLessThan20(int num) {
        if (num < 20 && num > 9) {
            return UNIT_TOKENS[num];
        }
        return "";
    }

    private String ten(int num) {
        if (num > 1 && num < 10) {
            return TENS_TOKENS[num - 2];
        }
        return "";
    }

    private String two(int num) {
        if (num == 0) {
            return "";
        } else if (num < 10) {
            return one(num);
        } else if (num < 20) {
            return twoLessThan20(num);
        } else {
            int tenner = num / 10;
            int rest = num - tenner * 10;
            if (rest != 0) {
                return ten(tenner) + " " + one(rest);
            } else {
                return ten(tenner);
            }
        }
    }

    private String three(int num) {
        int hundred = num / 100;
        int rest = num - hundred * 100;
        String res = "";
        if (hundred * rest != 0) {
            res = one(hundred) + " Hundred " + two(rest);
        } else if ((hundred == 0) && (rest != 0)) {
            res = two(rest);
        } else if ((hundred != 0) && (rest == 0)) {
            res = one(hundred) + " Hundred";
        }
        return res;
    }

    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }

        int billion = num / 1000000000;
        int million = (num - billion * 1000000000) / 1000000;
        int thousand = (num - billion * 1000000000 - million * 1000000) / 1000;
        int rest = num - billion * 1000000000 - million * 1000000 - thousand * 1000;

        String result = "";
        if (billion != 0) {
            result = three(billion) + " Billion";
        }
        if (million != 0) {
            if (!result.isEmpty()) {
                result += " ";
            }
            result += three(million) + " Million";
        }
        if (thousand != 0) {
            if (!result.isEmpty()) {
                result += " ";
            }
            result += three(thousand) + " Thousand";
        }
        if (rest != 0) {
            if (!result.isEmpty()) {
                result += " ";
            }
            result += three(rest);
        }
        return result;
    }
}
