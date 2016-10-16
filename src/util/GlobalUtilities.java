package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;



public abstract class GlobalUtilities {
    /** UAE mobile number patteren. */
    private static String MOBILE_NUMBER_PATTEREN = "971(50|55|56|52|54)[0-9]{7}$";
    
    /** UAE phone number patteren. */
    private static String PHONE_NUMBER_PATTEREN = "0[2-9][0-9]{7}$";
    
    /** UAE mail number patteren. */
    private static String EMAIL_NUMBER_PATTEREN = "^[a-zA-Z0-9\\_\\-\\']+(\\.[a-zA-Z0-9\\_\\-\\']+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{2,3})$";
    
    /** English character pattern */
    private static String ENGLISH_CHARACTER_PATTEREN = "[A-Za-z0-9\\_\\.\\(\\)]";
    
    /** EID English character pattern */
    private static String EID_ENGLISH_CHARACTER_PATTEREN = "[A-Za-z0-9\\_\\.\\-\\(\\)]";
    
    /** English character without special character pattern. */
    private static String ENGLISH_CHARACTER_WITHOUT_SPEC_CHAR_PATTEREN = "[A-Za-z0-9\\-]";
    
    /** Eid number pattern.*/
    private static String EID_NUMBER_PATTERN = "^\\d{3}\\-\\d{4}\\-\\d{7}\\-\\d{1}$";
    
    public static final String GEN_INQ_SRV_GMTPLUS4_FORMAT_REQUESTED = "GeneralInquiryService.GMTPlus4FormatRequested";
    public static final String GMTPLUS4_FORMAT_REQUESTED = "default.GMTPlus4FormatRequested";

    /** Used to pare/format date web layer dates. */
    public static final SimpleDateFormat DATE_FORMAT = 
      new SimpleDateFormat("dd-MM-yyyy", Locale.US);

    /** Used to pare/format date web layer dates. */
    public static final SimpleDateFormat SHORT_DATE_FORMAT = 
      new SimpleDateFormat("dd/MM", Locale.US);
    public static final SimpleDateFormat HOURS24_DAY_MONTH_FORMAT
               = new SimpleDateFormat("HH dd/MM", Locale.US);
    
    public static final SimpleDateFormat HOURS12_DAY_MONTH_FORMAT
               = new SimpleDateFormat("hh:mm aa dd/MM", Locale.US);
    
    /** Used to pare/format date web layer dates. */
    public static final SimpleDateFormat YEAR_MONTH_DAY_DATE_FORMAT = 
      new SimpleDateFormat("yyyy-MM-dd", Locale.US);

    /** Used to pare/format date database layer dates. */
    public static final SimpleDateFormat ORACLE_DATE_FORMAT =
      new SimpleDateFormat("dd/MM/yyyy", Locale.US);
      
    /** Used to pare/format time web layer dates. */
    public static final SimpleDateFormat TIME_FORMAT = 
      new SimpleDateFormat("H:mm", Locale.US);
    
    /** Used to pare/format time web layer dates. */
    public static final SimpleDateFormat TIME_IN_SECONDS_FORMAT = 
      new SimpleDateFormat("HH:mm:ss", Locale.US);
    
    /** Date Time Format */ 
    public static final SimpleDateFormat DATE_TIME_FORMAT = 
      new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.US);    

    /** Default date format: EEE, dd MMMM yyyy hh:mm:ss */
    public static final SimpleDateFormat DATE_TIME_SECONDS_FORMAT = 
                    new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
                    
    /** Default date format: EEE, dd MMMM yyyy hh:mm:ss.SSS */
    public static final SimpleDateFormat DATE_TIME_MILLISECONDS_FORMAT = 
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
                    
    /** ORACLE date format: EEE, dd MMMM yyyy hh:mm:ss */
    public static final SimpleDateFormat DATE_TIME_SECONDS_ORACLE_FORMAT = 
                    new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    
    /** HH:mm:ss*/
    public static final SimpleDateFormat TIME_FORMAT_SIMPLE_FORMAT = 
                    new SimpleDateFormat("HH:mm:ss");           
    /** IPAddress pattern : 111.111.111.111 */
    private static String IPADDRESS_PATTERN = "^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$";
    
    /** Mac Address pattern : aa-bb-cc-aa-bb-cc */
    private static String MACADDRESS_PATTERN = "^([0-9A-F]{2}[:-]){5}([0-9A-F]{2})$";
    
    /** Device Key Pattern : dddd-dddd-dddd-dddd */
    private static String DEVICE_KEY_PATTERN = "^(\\d{1,4})\\-(\\d{1,4})\\-(\\d{1,4})$";
    
    /** Service Key Pattern : dddd-dddd-dddd-dddddddd-dddd */
    private static String SERVICE_KEY_PATTERN = "^(\\d{1,4})\\-(\\d{1,4})\\-(\\d{1,4})\\-(\\d{1,8})\\-(\\d{1,4})$";
    
    /** Used to pare/format time web layer dates. */
    public static final SimpleDateFormat TIME_24_FORMAT = new SimpleDateFormat("HH:mm", Locale.US);    
      
    /** Used to pare/format time web layer dates. */
    public static final SimpleDateFormat FULL_TIME_24_FORMAT = new SimpleDateFormat("HH:mm:ss", Locale.US);  
      
    /** EID first three number. */  
    public static final String EID_FIRST_NUM = "784";

    public static final String CONTENT_TYPE = "application/pdf"; 
    public static final String CONTENT_TYPE_XLS = "application/octet-stream";

    /** Used to pare/format time using the default application time format. */
    private static final SimpleDateFormat TIME_12_FORMAT =
        new SimpleDateFormat("hh:mm", Locale.US);    
    
    /** Used to pare/format time using the default application time format. */
    private static final SimpleDateFormat TIME_12_FORMAT_AM_PM = new SimpleDateFormat("hh:mm aa", Locale.US);    
	
    
    public static final String GMTPLUS4_FORMAT = "EEE MMM dd HH:mm:ss z yyyy";
    public static final String GMTPLUS4_TIMEZONE = "GMT+04:00";
	

    /**
     * Clear time from date object.
     * 
     * @param date Date object to be processed.
     * @return Date without time or null if the date parameter was null.
     */
	public static Date clearTime(Date date) {
        if (date == null) {
            return null;
        }

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);        
        
		return cal.getTime();
	}
	
//    /**
//     * Get Long object or null if the value is null or empty string.
//     * 
//     * @param value String value to be tested and parsed.
//     * @return Long object or null if the value is null or empty string.
//     */
//    public static Long getLong(Object value) {
//        if (value == null) {
//            return null;
//        }
//        
//        if (value instanceof Long) {
//            return (Long) value;
//        }
//        
//        if (value instanceof Number) {
//            return new Long(((Number)value).longValue());
//        }
//        
//        String str = getString(value);
//        if (isBlankOrNull(str)) {
//            return null;
//        }
//        
//        return new Long(str.trim());
//    }
    /**
     * Checks if the field contains a long value.
     * 
     * @param value String value to be checked.
     * @return true if the field contains a long value.
     */
    public static boolean isLong(String value) {
       if (isBlankOrNull(value)) {
           return false;
       }

       try {
           Long.parseLong(value);
           return true;
       } catch (Exception ex) {
           return false;
       }
    }
//    /**
//     * Get string representation of this object
//     * 
//     * @param obj Object to be parsed,
//     * @return string representation of this object
//     */
//    public static String getString(Object obj) {
//        
//        if (obj == null) {
//            return null;
//        }
//        
//        if(obj instanceof Date){
//            boolean isGMTFormatRequested = false;
//            try{
//                isGMTFormatRequested = ServiceLocator.getInstance().getConfig()
//                                                    .getBooleanProperty(GMTPLUS4_FORMAT_REQUESTED);
//            }catch(Exception ex){
//                ex.printStackTrace();
//            }
//            if(isGMTFormatRequested){
//                DateFormat formatter = new SimpleDateFormat(GMTPLUS4_FORMAT,Locale.ENGLISH);
//                formatter.setTimeZone(TimeZone.getTimeZone(GMTPLUS4_TIMEZONE));
//                
//                return formatter.format(obj);
//            }else{
//                return obj.toString();
//            }
//        }
//        
//        return obj.toString();
//    }
//    

	
	/**
     * Checks if the field is not null or contains only blank spaces.
     * 
     * @param value String value to be checked.
     * @return true if the field is not null or blank.
     */
    public static boolean isBlankOrNull(String value) {
       return ((value == null) || (value.trim().length() == 0) || (value.trim().equals("null")));
    }
    
    /**
     * Get Integer object or null if the value is null or empty string.
     * 
     * @param value String value to be tested and parsed.
     * @return Integer object or null if the value is null or empty string.
     */
    public static Integer getInteger(String value) {
        if (isBlankOrNull(value)) {
            return null;
        }
        
        return new Integer(value.trim());
    }
    
    /**
     * Get Integer object or null if the value is null or empty string.
     * 
     * @param value String value to be tested and parsed.
     * @return Integer object or null if the value is null or empty string.
     */
    public static Integer getInteger(Object value) {
        if (value==null) {
            return null;
        }
        
        return new Integer(value.toString());
    }

}
