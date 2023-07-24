package ca.fly.mtm.admin.util;


import org.apache.commons.lang3.RandomStringUtils;

import java.nio.ByteBuffer;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

public class StringUtils {

    public static final int UUID_SIZE = 10;

    public static String getRandomCharUUID() {

       return RandomStringUtils.randomAlphanumeric(UUID_SIZE).toUpperCase();

    }

    public static boolean isStringNUllorEmpty(String str)
    {
        return (str == null || str.isEmpty());
    }

    public static String getPublicReferenceNumber() {

        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }
}
