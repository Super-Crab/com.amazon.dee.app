package com.amazonaws.logging;
/* loaded from: classes13.dex */
public class ApacheCommonsLogging implements Log {
    private com.amazonaws.org.apache.commons.logging.Log log;
    private Class logClass;
    private String logString;

    public ApacheCommonsLogging(Class cls) {
        this.logClass = cls;
        this.log = com.amazonaws.org.apache.commons.logging.LogFactory.getLog(cls);
    }

    @Override // com.amazonaws.logging.Log
    public void debug(Object obj) {
        this.log.debug(obj);
    }

    @Override // com.amazonaws.logging.Log
    public void error(Object obj) {
        this.log.error(obj);
    }

    @Override // com.amazonaws.logging.Log
    public void info(Object obj) {
        this.log.info(obj);
    }

    @Override // com.amazonaws.logging.Log
    public boolean isDebugEnabled() {
        return this.log.isDebugEnabled();
    }

    @Override // com.amazonaws.logging.Log
    public boolean isErrorEnabled() {
        return this.log.isErrorEnabled();
    }

    @Override // com.amazonaws.logging.Log
    public boolean isInfoEnabled() {
        return this.log.isInfoEnabled();
    }

    @Override // com.amazonaws.logging.Log
    public boolean isTraceEnabled() {
        return this.log.isTraceEnabled();
    }

    @Override // com.amazonaws.logging.Log
    public boolean isWarnEnabled() {
        return this.log.isWarnEnabled();
    }

    @Override // com.amazonaws.logging.Log
    public void trace(Object obj) {
        this.log.trace(obj);
    }

    @Override // com.amazonaws.logging.Log
    public void warn(Object obj) {
        this.log.warn(obj);
    }

    @Override // com.amazonaws.logging.Log
    public void debug(Object obj, Throwable th) {
        this.log.debug(obj, th);
    }

    @Override // com.amazonaws.logging.Log
    public void error(Object obj, Throwable th) {
        this.log.error(obj, th);
    }

    @Override // com.amazonaws.logging.Log
    public void info(Object obj, Throwable th) {
        this.log.info(obj, th);
    }

    @Override // com.amazonaws.logging.Log
    public void trace(Object obj, Throwable th) {
        this.log.trace(obj, th);
    }

    @Override // com.amazonaws.logging.Log
    public void warn(Object obj, Throwable th) {
        this.log.warn(obj, th);
    }

    public ApacheCommonsLogging(String str) {
        this.logString = str;
        this.log = com.amazonaws.org.apache.commons.logging.LogFactory.getLog(str);
    }
}
