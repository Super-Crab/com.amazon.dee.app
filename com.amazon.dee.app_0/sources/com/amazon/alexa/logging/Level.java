package com.amazon.alexa.logging;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.voice.ui.driveMode.util.DriveModeVoxUiConstants;
import com.drew.metadata.wav.WavDirectory;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: Level.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0011"}, d2 = {"Lcom/amazon/alexa/logging/Level;", "", "value", "", "repr", "", "(Ljava/lang/String;IILjava/lang/String;)V", "getRepr$AlexaMobileAndroidLogging_release", "()Ljava/lang/String;", "getValue$AlexaMobileAndroidLogging_release", "()I", "VERBOSE", "DEBUG", WavDirectory.LIST_INFO, DriveModeVoxUiConstants.WARN, "ERROR", "ASSERT", "AlexaMobileAndroidLogging_release"}, k = 1, mv = {1, 1, 10})
/* loaded from: classes9.dex */
public enum Level {
    VERBOSE(2, ExifInterface.GPS_MEASUREMENT_INTERRUPTED),
    DEBUG(3, "D"),
    INFO(4, "I"),
    WARN(5, ExifInterface.LONGITUDE_WEST),
    ERROR(6, ExifInterface.LONGITUDE_EAST),
    ASSERT(7, ExifInterface.GPS_MEASUREMENT_IN_PROGRESS);
    
    @NotNull
    private final String repr;
    private final int value;

    Level(int i, @NotNull String repr) {
        Intrinsics.checkParameterIsNotNull(repr, "repr");
        this.value = i;
        this.repr = repr;
    }

    @NotNull
    public final String getRepr$AlexaMobileAndroidLogging_release() {
        return this.repr;
    }

    public final int getValue$AlexaMobileAndroidLogging_release() {
        return this.value;
    }
}
