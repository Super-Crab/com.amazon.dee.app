package com.amazon.alexa.logging;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: LogHandler.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b`\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/amazon/alexa/logging/LogHandler;", "Lcom/amazon/alexa/logging/LogFilter;", "log", "", "record", "Lcom/amazon/alexa/logging/LogRecord;", "AlexaMobileAndroidLogging_release"}, k = 1, mv = {1, 1, 10})
/* loaded from: classes9.dex */
public interface LogHandler extends LogFilter {
    int log(@NotNull LogRecord logRecord);
}
