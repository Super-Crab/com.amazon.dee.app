package com.amazon.clouddrive.internal;

import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
/* loaded from: classes11.dex */
class AmazonCloudDriveLog {
    private static Logger LOG = getLogger();
    static final String LOGGER_NAME = "AmazonCloudDrive";

    private AmazonCloudDriveLog() {
        throw new UnsupportedOperationException("Do not instantiate this.");
    }

    private static Logger getLogger() {
        Enumeration<String> loggerNames = LogManager.getLogManager().getLoggerNames();
        while (loggerNames.hasMoreElements()) {
            if (loggerNames.nextElement().equals(LOGGER_NAME)) {
                return Logger.getLogger(LOGGER_NAME);
            }
        }
        Logger logger = Logger.getLogger(LOGGER_NAME);
        logger.setLevel(Level.SEVERE);
        return logger;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void w(String str, Throwable th) {
        LOG.log(Level.WARNING, str, th);
    }
}
