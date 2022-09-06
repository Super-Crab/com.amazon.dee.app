package com.amazon.alexa.logging;

import com.amazon.alexa.accessorykit.ModelTransformer;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: Logger.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcom/amazon/alexa/logging/LogFilter;", "", "isLoggable", "", "tag", "Lcom/amazon/alexa/logging/Tag;", ModelTransformer.KEY_BATTERY_LEVEL, "Lcom/amazon/alexa/logging/Level;", "AlexaMobileAndroidLogging_release"}, k = 1, mv = {1, 1, 10})
/* loaded from: classes9.dex */
public interface LogFilter {
    boolean isLoggable(@NotNull Tag tag, @NotNull Level level);
}
