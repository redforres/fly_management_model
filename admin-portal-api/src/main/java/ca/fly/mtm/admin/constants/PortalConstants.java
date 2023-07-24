package ca.fly.mtm.admin.constants;

import java.util.EnumMap;

public class PortalConstants {

        public static final String MASK_EMAIL_REG = "(?<=.).(?=[^@]*?.@)"; // (?<=.{3}).(?=.*@) or (?<=.{2}).(?=[^@]*?.@)
        public static final String MASK_MOBILE_REG = "(\\d{3})\\d{4}(\\d{4})"; // \d(?=\d{4})
        public static final String MASK_SHORT_MOBILE_REG = "\\d*(\\d{4})";

        public static final String APPLICANT_TYPE_PRIMARY = "P";
        public static final String APPLICANT_TYPE_JOIN = "J";

        public static final String OPSDocURL = "https://cacgikdcapmdw16.cihs.ad.gov.on.ca";

        public static final EnumMap<EventType, String> eventTypesDescMap = new EnumMap<>(EventType.class);
        static{
                eventTypesDescMap.put(EventType.LICENCE_UPDATE, "Licence number added/updated");
                eventTypesDescMap.put(EventType.MARRIAGEINFO_UPDATE, "Marriage date/updated updated");
                eventTypesDescMap.put(EventType.APPOINTMENT_UPDATE, "Appointment scheduled/updated");
                eventTypesDescMap.put(EventType.STATUS_UPDATE, "Application status updated");
                eventTypesDescMap.put(EventType.PERSONALINFO_UPDATE, "Personal info updated");
                eventTypesDescMap.put(EventType.CONTACTINFO_UPDATE, "Contact info updated");
                eventTypesDescMap.put(EventType.PARENTSINFO_UPDATE, "Parents info updated");
                eventTypesDescMap.put(EventType.DOCUMENTS_UPDATE, "Documents added/updated");

        }
}
