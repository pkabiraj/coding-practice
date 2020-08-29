package org.psk.practice.aconex.gedcom.parser.writer;

import java.util.List;

/**
 * <p>
 * Basic interface for generic output operations. Class implementing this interface will be responsible for serializing
 * objects as necessary. Generally, it is responsibility of implementing class to decide which technology to use for
 * mapping and how it should be configured.
 * </p>
 * <p>
 * The write method is responsible for making sure that any internal buffers are flushed. If a transaction is active it
 * will also usually be necessary to discard the output on a subsequent rollback. The resource to which the writer is
 * sending data should normally be able to handle this itself.
 * </p>
 *
 * @param <T> the generic type representing domain object
 * @author Pinaki S Kabiraj
 */
public interface ItemWriter<T> {

    /**
     * Process the supplied data element. Will not be called with any null items in normal operation.
     */
    void write(List<? extends T> items);

    /**
     * If any resource needs to be open, open here to read.
     */
    void open();

    /**
     * If any resources are needed for the reader to operate they need to be destroyed here.
     */
    void close();
}
