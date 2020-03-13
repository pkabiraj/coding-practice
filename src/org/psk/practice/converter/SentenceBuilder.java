package org.psk.practice.converter;

/**
 * The Class SentenceBuilder to build sentence.
 *
 * @author pkabiraj
 */
public class SentenceBuilder {

    private String sentence;

    /**
     * Instantiates a new sentence builder.
     */
    public SentenceBuilder() {
        sentence = "";
    }

    /**
     * Adds the input word at the end of the existing sentence.
     *
     * @param word the word
     * @return this object
     */
    public SentenceBuilder add(String word) {
        sentence = sentence.concat(word);

        return this;
    }

    /**
     * Return the complete sentence.
     *
     * @return the sentence
     */
    public String toSentence() {
        return sentence;
    }
}