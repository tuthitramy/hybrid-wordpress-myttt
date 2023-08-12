package commons;

import java.io.File;

public class GlobalConstants {
    public static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final String UPLOAD_FILE = PROJECT_PATH + "\\uploadFiles";
    public static final long SHORT_TIMEOUT = 5;
    public static final long LONG_TIMEOUT = 30;
    public static final String SYSTEM_USER_ID ="mngr507353";
    public static final String SYSTEM_PASSWORD ="gebEqEj";

    public static final String REPORTNG_SCREENSHOT=PROJECT_PATH + File.separator+ "ReportNGScreenShots"+File.separator;
    public static final String JAVA_VERSION = System.getProperty("java.version");
    public static final String OS_NAME = System.getProperty("os.name").toLowerCase();
}
