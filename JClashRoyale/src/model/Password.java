package model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Password class, handles actions on password like hashing, parsing, etc
 * @author Adibov & Armin Gh
 * @version 1.0
 */
public class Password {
    String hashedPassword;

    /**
     * class constructor, hash the given password and save it
     * @param password given password
     */
    public Password(String password) {
        this.hashedPassword = Password.hashPassword(password);
    }

    /**
     * hash the given password using sha256 algorithm
     * @param password given password
     * @return hashed password
     */
    public static String hashPassword(String password) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return Password.bytesToHex(encodedHash);
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * convert given byte array to string
     * @param hash given byte array
     * @return result string
     */
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    /**
     * override toString method
     * @return result string
     */
    @Override
    public String toString() {
        return hashedPassword;
    }
}
