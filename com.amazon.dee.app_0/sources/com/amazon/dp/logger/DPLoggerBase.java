package com.amazon.dp.logger;
/* loaded from: classes12.dex */
public abstract class DPLoggerBase {

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes12.dex */
    public enum DPLevel {
        FATAL,
        ERROR,
        WARN,
        INFO,
        DEBUG,
        TRACE,
        VERBOSE
    }

    private void logIfEnabled(DPLevel dPLevel, String str, String str2, Object... objArr) {
        logIfEnabled(dPLevel, new DPFormattedMessage(str, str2, objArr));
    }

    public void debug(String str, String str2, Object... objArr) {
        logIfEnabled(DPLevel.DEBUG, str, str2, objArr);
    }

    public void error(String str, String str2, Object... objArr) {
        logIfEnabled(DPLevel.ERROR, str, str2, objArr);
    }

    public void fatal(String str, String str2, Object... objArr) {
        logIfEnabled(DPLevel.FATAL, str, str2, objArr);
    }

    public void info(String str, String str2, Object... objArr) {
        logIfEnabled(DPLevel.INFO, str, str2, objArr);
    }

    public boolean isDebugEnabled() {
        return isEnabled(DPLevel.DEBUG);
    }

    protected abstract boolean isEnabled(DPLevel dPLevel);

    public boolean isErrorEnabled() {
        return isEnabled(DPLevel.ERROR);
    }

    public boolean isFatalEnabled() {
        return isEnabled(DPLevel.FATAL);
    }

    public boolean isInfoEnabled() {
        return isEnabled(DPLevel.INFO);
    }

    public boolean isTraceEnabled() {
        return isEnabled(DPLevel.TRACE);
    }

    public boolean isVerboseEnabled() {
        return isEnabled(DPLevel.VERBOSE);
    }

    public boolean isWarnEnabled() {
        return isEnabled(DPLevel.WARN);
    }

    protected abstract void log(DPLevel dPLevel, DPFormattedMessage dPFormattedMessage);

    public void trace(String str, String str2, Object... objArr) {
        logIfEnabled(DPLevel.TRACE, str, str2, objArr);
    }

    public void verbose(String str, String str2, Object... objArr) {
        logIfEnabled(DPLevel.VERBOSE, str, str2, objArr);
    }

    public void warn(String str, String str2, Object... objArr) {
        logIfEnabled(DPLevel.WARN, str, str2, objArr);
    }

    private void logIfEnabled(DPLevel dPLevel, DPFormattedMessage dPFormattedMessage) {
        if (isEnabled(dPLevel)) {
            log(dPLevel, dPFormattedMessage);
        }
    }

    public void debug(DPFormattedMessage dPFormattedMessage) {
        logIfEnabled(DPLevel.DEBUG, dPFormattedMessage);
    }

    public void error(DPFormattedMessage dPFormattedMessage) {
        logIfEnabled(DPLevel.ERROR, dPFormattedMessage);
    }

    public void fatal(DPFormattedMessage dPFormattedMessage) {
        logIfEnabled(DPLevel.FATAL, dPFormattedMessage);
    }

    public void info(DPFormattedMessage dPFormattedMessage) {
        logIfEnabled(DPLevel.INFO, dPFormattedMessage);
    }

    public void trace(DPFormattedMessage dPFormattedMessage) {
        logIfEnabled(DPLevel.TRACE, dPFormattedMessage);
    }

    public void verbose(DPFormattedMessage dPFormattedMessage) {
        logIfEnabled(DPLevel.VERBOSE, dPFormattedMessage);
    }

    public void warn(DPFormattedMessage dPFormattedMessage) {
        logIfEnabled(DPLevel.WARN, dPFormattedMessage);
    }
}
