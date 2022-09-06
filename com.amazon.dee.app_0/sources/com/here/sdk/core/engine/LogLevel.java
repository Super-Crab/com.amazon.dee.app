package com.here.sdk.core.engine;
/* loaded from: classes3.dex */
public enum LogLevel {
    LOG_LEVEL_TRACE(0),
    LOG_LEVEL_DEBUG(1),
    LOG_LEVEL_INFO(2),
    LOG_LEVEL_WARNING(3),
    LOG_LEVEL_ERROR(4),
    LOG_LEVEL_FATAL(5),
    LOG_LEVEL_OFF(6);
    
    public final int value;

    LogLevel(int i) {
        this.value = i;
    }
}
