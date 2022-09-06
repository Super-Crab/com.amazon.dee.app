package org.slf4j.event;

import com.amazon.alexa.voice.ui.driveMode.util.DriveModeVoxUiConstants;
import com.drew.metadata.wav.WavDirectory;
/* loaded from: classes5.dex */
public enum Level {
    ERROR(40, "ERROR"),
    WARN(30, DriveModeVoxUiConstants.WARN),
    INFO(20, WavDirectory.LIST_INFO),
    DEBUG(10, "DEBUG"),
    TRACE(0, "TRACE");
    
    private int levelInt;
    private String levelStr;

    Level(int i, String str) {
        this.levelInt = i;
        this.levelStr = str;
    }

    public int toInt() {
        return this.levelInt;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.levelStr;
    }
}
