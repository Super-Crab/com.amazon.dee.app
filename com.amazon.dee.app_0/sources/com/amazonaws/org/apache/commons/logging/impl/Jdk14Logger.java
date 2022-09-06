package com.amazonaws.org.apache.commons.logging.impl;

import com.amazonaws.org.apache.commons.logging.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
/* loaded from: classes13.dex */
public class Jdk14Logger implements Log, Serializable {
    protected static final Level dummyLevel = Level.FINE;
    protected transient Logger logger;
    protected String name;

    public Jdk14Logger(String str) {
        this.logger = null;
        this.name = null;
        this.name = str;
        this.logger = getLogger();
    }

    private void log(Level level, String str, Throwable th) {
        String str2;
        Logger logger = getLogger();
        if (logger.isLoggable(level)) {
            StackTraceElement[] outline195 = GeneratedOutlineSupport1.outline195();
            String str3 = "unknown";
            if (outline195 == null || outline195.length <= 2) {
                str2 = str3;
            } else {
                StackTraceElement stackTraceElement = outline195[2];
                str3 = stackTraceElement.getClassName();
                str2 = stackTraceElement.getMethodName();
            }
            if (th == null) {
                logger.logp(level, str3, str2, str);
            } else {
                logger.logp(level, str3, str2, str, th);
            }
        }
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public void debug(Object obj) {
        log(Level.FINE, String.valueOf(obj), null);
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public void error(Object obj) {
        log(Level.SEVERE, String.valueOf(obj), null);
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public void fatal(Object obj) {
        log(Level.SEVERE, String.valueOf(obj), null);
    }

    public Logger getLogger() {
        if (this.logger == null) {
            this.logger = Logger.getLogger(this.name);
        }
        return this.logger;
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public void info(Object obj) {
        log(Level.INFO, String.valueOf(obj), null);
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public boolean isDebugEnabled() {
        return getLogger().isLoggable(Level.FINE);
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public boolean isErrorEnabled() {
        return getLogger().isLoggable(Level.SEVERE);
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public boolean isFatalEnabled() {
        return getLogger().isLoggable(Level.SEVERE);
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public boolean isInfoEnabled() {
        return getLogger().isLoggable(Level.INFO);
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public boolean isTraceEnabled() {
        return getLogger().isLoggable(Level.FINEST);
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public boolean isWarnEnabled() {
        return getLogger().isLoggable(Level.WARNING);
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public void trace(Object obj) {
        log(Level.FINEST, String.valueOf(obj), null);
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public void warn(Object obj) {
        log(Level.WARNING, String.valueOf(obj), null);
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public void debug(Object obj, Throwable th) {
        log(Level.FINE, String.valueOf(obj), th);
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public void error(Object obj, Throwable th) {
        log(Level.SEVERE, String.valueOf(obj), th);
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public void fatal(Object obj, Throwable th) {
        log(Level.SEVERE, String.valueOf(obj), th);
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public void info(Object obj, Throwable th) {
        log(Level.INFO, String.valueOf(obj), th);
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public void trace(Object obj, Throwable th) {
        log(Level.FINEST, String.valueOf(obj), th);
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public void warn(Object obj, Throwable th) {
        log(Level.WARNING, String.valueOf(obj), th);
    }
}
