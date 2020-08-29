package org.psk.practice.aconex.gedcom.parser.mapper;

/**
 * The Interface LineMapper is for mapping lines (strings) to domain objects typically used to map lines read from a
 * file to domain objects on a per line basis. Implementations of this interface perform the actual work of parsing a
 * line without having to deal with how the line was obtained.
 *
 * @param <T> the generic type of domain object
 * @author Pinaki S Kabiraj
 */
public interface LineMapper<T> {

    /**
     * Implementations must implement this method to map the provided line to the parameter type T.
     *
     * @param line to be mapped
     * @return mapped object of type T
     */
    T mapLine(String line);
}
