package org.psk.practice.aconex.gedcom.parser.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * General IO stream manipulation utilities.
 *
 * @author Pinaki S Kabiraj
 */
public final class IOUtils {

    private IOUtils() {
        // Utility class
    }

    /**
     * Unconditionally close a <code>Closeable</code>.
     * <p>
     * Equivalent to {@link Closeable#close()}, except any exceptions will be ignored. This is typically used in finally
     * blocks.
     * <p>
     * Example code:
     *
     * <pre>
     * Closeable closeable = null;
     * try {
     * 	closeable = new FileReader(&quot;foo.txt&quot;);
     * 	// process closeable
     * 	closeable.close();
     * } catch (Exception e) {
     * 	// error handling
     * } finally {
     * 	IOUtils.closeQuietly(closeable);
     * }
     * </pre>
     *
     * @param closeable the object to close, may be null or already closed
     * @since Commons IO 2.0
     */
    public static void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException ioe) {
            // ignore
        }
    }
}
