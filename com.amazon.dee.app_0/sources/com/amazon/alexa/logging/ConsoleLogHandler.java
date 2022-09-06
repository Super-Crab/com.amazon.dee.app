package com.amazon.alexa.logging;

import android.util.Log;
import com.amazon.alexa.accessorykit.ModelTransformer;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ConsoleLogHandler.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/logging/ConsoleLogHandler;", "Lcom/amazon/alexa/logging/LogHandler;", "()V", "isLoggable", "", "tag", "Lcom/amazon/alexa/logging/Tag;", ModelTransformer.KEY_BATTERY_LEVEL, "Lcom/amazon/alexa/logging/Level;", "log", "", "record", "Lcom/amazon/alexa/logging/LogRecord;", "AlexaMobileAndroidLogging_release"}, k = 1, mv = {1, 1, 10})
/* loaded from: classes9.dex */
public final class ConsoleLogHandler implements LogHandler {

    @Metadata(bv = {1, 0, 2}, k = 3, mv = {1, 1, 10})
    /* loaded from: classes9.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Level.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[Level.ASSERT.ordinal()] = 1;
            $EnumSwitchMapping$0[Level.DEBUG.ordinal()] = 2;
            $EnumSwitchMapping$0[Level.ERROR.ordinal()] = 3;
            $EnumSwitchMapping$0[Level.INFO.ordinal()] = 4;
            $EnumSwitchMapping$0[Level.VERBOSE.ordinal()] = 5;
            $EnumSwitchMapping$0[Level.WARN.ordinal()] = 6;
            $EnumSwitchMapping$1 = new int[Level.values().length];
            $EnumSwitchMapping$1[Level.ASSERT.ordinal()] = 1;
            $EnumSwitchMapping$1[Level.DEBUG.ordinal()] = 2;
            $EnumSwitchMapping$1[Level.ERROR.ordinal()] = 3;
            $EnumSwitchMapping$1[Level.INFO.ordinal()] = 4;
            $EnumSwitchMapping$1[Level.VERBOSE.ordinal()] = 5;
            $EnumSwitchMapping$1[Level.WARN.ordinal()] = 6;
        }
    }

    @Override // com.amazon.alexa.logging.LogFilter
    public boolean isLoggable(@NotNull Tag tag, @NotNull Level level) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(level, "level");
        return Log.isLoggable(tag.getSafeShort$AlexaMobileAndroidLogging_release(), level.getValue$AlexaMobileAndroidLogging_release());
    }

    @Override // com.amazon.alexa.logging.LogHandler
    public int log(@NotNull LogRecord record) {
        Intrinsics.checkParameterIsNotNull(record, "record");
        String safeShort$AlexaMobileAndroidLogging_release = record.getTag().getSafeShort$AlexaMobileAndroidLogging_release();
        if (record.getTr() != null) {
            switch (WhenMappings.$EnumSwitchMapping$0[record.getLevel().ordinal()]) {
                case 1:
                    return Log.wtf(safeShort$AlexaMobileAndroidLogging_release, record.getMessage(), record.getTr());
                case 2:
                    return Log.d(safeShort$AlexaMobileAndroidLogging_release, record.getMessage(), record.getTr());
                case 3:
                    return Log.e(safeShort$AlexaMobileAndroidLogging_release, record.getMessage(), record.getTr());
                case 4:
                    return Log.i(safeShort$AlexaMobileAndroidLogging_release, record.getMessage(), record.getTr());
                case 5:
                    return Log.v(safeShort$AlexaMobileAndroidLogging_release, record.getMessage(), record.getTr());
                case 6:
                    return Log.w(safeShort$AlexaMobileAndroidLogging_release, record.getMessage(), record.getTr());
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }
        switch (WhenMappings.$EnumSwitchMapping$1[record.getLevel().ordinal()]) {
            case 1:
                return Log.wtf(safeShort$AlexaMobileAndroidLogging_release, record.getMessage());
            case 2:
                return Log.d(safeShort$AlexaMobileAndroidLogging_release, record.getMessage());
            case 3:
                return Log.e(safeShort$AlexaMobileAndroidLogging_release, record.getMessage());
            case 4:
                return Log.i(safeShort$AlexaMobileAndroidLogging_release, record.getMessage());
            case 5:
                return Log.v(safeShort$AlexaMobileAndroidLogging_release, record.getMessage());
            case 6:
                return Log.w(safeShort$AlexaMobileAndroidLogging_release, record.getMessage());
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
