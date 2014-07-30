package am.ucom.dinning.util;

import java.io.UnsupportedEncodingException;

/**
 * class for encodeing
 *
 * @author nadya
 */
public class Encode {

    /**
     * private constructor...
     */
    private Encode() {

    }

    /**
     * method for encoding string to UTF-8
     *
     * @param value
     * @return
     */
    public static String encodeString(String value) {
        String newValue = null;
        try {
            if (value != null) {
                newValue = new String(value.getBytes("8859_1"), "UTF8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return newValue;
    }
}
