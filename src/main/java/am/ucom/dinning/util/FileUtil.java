package am.ucom.dinning.util;

import java.io.*;

/**
 * Class for resize image
 *
 * @author hharyan
 */

public class FileUtil {

    /**
     * private constructor...
     */
    private FileUtil() {
    }

    /**
     * method for resizing
     *
     * @param copyFile
     * @param resultFile
     * @throws IOException
     */
    public static void fileCopy(final File copyFile, final File resultFile) throws IOException {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(copyFile);
            out = new FileOutputStream(resultFile);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}