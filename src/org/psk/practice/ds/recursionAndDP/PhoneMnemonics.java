package org.psk.practice.ds.recursionAndDP;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Each digit, apart from 0 and 1, in a phone keypad corresponds to one of three or four letters of the alphabet,
 * asshown in Figure 7.1 on the next page. Since words are easier to remember than numbers, it is natural to ask if a 7
 * or 10-digit phone number can be represented by a word. For example, "2276696" corresponds to "ACRONYM" as well as
 * "ABPOMZN".</p>
 * <p>Write a program which takes as input a phone number, specified as a string of digits, and returns all
 * possible character sequences that correspond to the phone number. The cell phone keypad is specified by a mapping
 * that takes a digit and returns the corresponding set of characters. The character sequences do not have to be legal
 * words or phrases.</p>
 */
public class PhoneMnemonics {

    // The mapping from digit to corresponding characters.
    private static final String[] MAPPING
            = {"0", "1", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};

    public static void main(String[] args) {
        System.out.println(phoneMnemonic("227"));
        System.out.println(phoneMnemonic("2126"));
        System.out.println(phoneMnemonic("2276696"));
    }

    public static List<String> phoneMnemonic(String phoneNumber) {
        char[] partialMnemonic = new char[phoneNumber.length()];
        List<String> mnemonics = new ArrayList<>();
        phoneMnemonicHelper(phoneNumber, 0, partialMnemonic, mnemonics);
        return mnemonics;
    }

    private static void phoneMnemonicHelper(String phoneNumber, int digit,
                                            char[] partialMnemonic,
                                            List<String> mnemonics) {
        if (digit == phoneNumber.length()) {
            // All digits are processed , so add partialMnemonic to mnemonics.
            // (We add a copy since subsequent calls modify partialMnemonic.)
            mnemonics.add(new String(partialMnemonic));
        } else {
            // Try all possible characters for this digit.
            for (int i = 0; i < MAPPING[phoneNumber.charAt(digit) - '0'].length(); ++i) {
                char c = MAPPING[phoneNumber.charAt(digit) - '0'].charAt(i);
                partialMnemonic[digit] = c;
                phoneMnemonicHelper(phoneNumber, digit + 1, partialMnemonic, mnemonics);
            }
        }
    }
}
