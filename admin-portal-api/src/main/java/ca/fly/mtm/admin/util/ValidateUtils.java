package ca.fly.mtm.admin.util;


import ca.fly.mtm.admin.constants.PortalConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

@Slf4j
public class ValidateUtils {

    public static final String EMAIL_REG = "^([a-z0-9A-Z]+[_|\\-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    public static boolean isEmail(String str) {

        try {
            if (StringUtils.isEmpty(str)) {
                return false;
            }

            return str.matches(EMAIL_REG);
        } catch (Exception e) {
            log.error(" isEmail exception:{}", str, e);
        }
        return false;
    }


    public static String outSensitive(String str) {

        if (isEmail(str)) {
            return emailOutSensitive(str);
        } else {
            return mobileOutSensitive(str);
        }
    }

    public static String mobileOutSensitive(String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            return null;
        }
        if (mobile.length() == 11) {
            return mobile.replaceAll(PortalConstants.MASK_MOBILE_REG, "$1****$2");
        } else {
            return mobile.replaceAll(PortalConstants.MASK_SHORT_MOBILE_REG, "****$1");
        }
    }

    public static String emailOutSensitive(String email) {
        if (StringUtils.isEmpty(email)) {
            return null;
        }
        return email.replaceAll(PortalConstants.MASK_EMAIL_REG, "*");
    }

    public static boolean isNumeric(String str) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }

        try {
            new BigDecimal(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
