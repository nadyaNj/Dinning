/*
 * SecurityUtil		1.0 8/24/11 3:47 PM
 *
 * Copyright (c) UCom.
 *
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of UCom.
 * ("Confidential Information").  You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with UCom.
 */
package am.ucom.dinning.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;


/**
 * Utility class for providing security common functionality implementation.
 *
 * @author Hayk Hayryan
 * @version 1.0 9/2/12 12:00 PM
 */
public class SecurityUtil {

    /*
     * Private constructor for blocking object instantiation of Utility class.
     * The Utility methods must be used only as static.
     */
    private SecurityUtil() {
    }


    /**
     * Temporary password generation.
     *
     * @return String - temporary UUID password value max size 8 digits.
     */
    public static String generateTmpPassword() {
        String uuid = generateUUID();

        String[] strings = uuid.split("-");

        return strings[(strings.length - 1)].length() <= 8 ? strings[(strings.length - 1)] : strings[(strings.length - 1)].substring(0, 8);
    }

    /**
     * String value bytes encryption based on SHA1 algorithm and building encrypted bytes hex value.
     *
     * @param value - String value for encryption
     * @return String - SHA1 algorithm based hexadecimal encrypted value;
     * @throws NoSuchAlgorithmException - thrown when encryption algorithm not found.
     */
    public static String encryptString(String value) throws NoSuchAlgorithmException {
        byte[] valueBytes = value.getBytes();
        MessageDigest md = MessageDigest.getInstance("SHA1");
        md.update(valueBytes, 0, valueBytes.length);

        byte[] encodedBytes = md.digest();

        return buildHexString(encodedBytes);
    }

    /**
     * Building bytes to hexadecimal String value.
     *
     * @param buf - byte array
     * @return String - hexadecimal value of provided bytes as parameter.
     */
    public static String buildHexString(byte[] buf) {
        char[] hexChar = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        StringBuilder strBuilder = new StringBuilder(buf.length * 2);
        for (byte aBuf : buf) {
            strBuilder.append(hexChar[(aBuf & 0xf0) >>> 4]);
            strBuilder.append(':');
            strBuilder.append(hexChar[aBuf & 0x0f]);
        }
        return strBuilder.toString().toUpperCase();
    }

    /**
     * Generating UUID value
     *
     * @return String - UUID value as String.
     */
    private static String generateUUID() {

        return UUID.randomUUID().toString();
    }
}
