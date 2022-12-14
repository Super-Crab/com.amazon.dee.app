package com.amazonaws.org.apache.commons.logging.impl;

import com.amazonaws.org.apache.commons.logging.Log;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class NoOpLog implements Log, Serializable {
    public NoOpLog() {
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public void debug(Object obj) {
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public void debug(Object obj, Throwable th) {
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public void error(Object obj) {
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public void error(Object obj, Throwable th) {
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public void fatal(Object obj) {
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public void fatal(Object obj, Throwable th) {
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public void info(Object obj) {
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public void info(Object obj, Throwable th) {
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public final boolean isDebugEnabled() {
        return false;
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public final boolean isErrorEnabled() {
        return false;
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public final boolean isFatalEnabled() {
        return false;
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public final boolean isInfoEnabled() {
        return false;
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public final boolean isTraceEnabled() {
        return false;
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public final boolean isWarnEnabled() {
        return false;
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public void trace(Object obj) {
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public void trace(Object obj, Throwable th) {
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public void warn(Object obj) {
    }

    @Override // com.amazonaws.org.apache.commons.logging.Log
    public void warn(Object obj, Throwable th) {
    }

    public NoOpLog(String str) {
    }
}
