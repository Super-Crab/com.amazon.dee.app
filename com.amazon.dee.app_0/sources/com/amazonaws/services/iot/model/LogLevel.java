package com.amazonaws.services.iot.model;

import com.amazon.alexa.voice.ui.driveMode.util.DriveModeVoxUiConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.metadata.wav.WavDirectory;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public enum LogLevel {
    DEBUG("DEBUG"),
    INFO(WavDirectory.LIST_INFO),
    ERROR("ERROR"),
    WARN(DriveModeVoxUiConstants.WARN),
    DISABLED("DISABLED");
    
    private static final Map<String, LogLevel> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("DEBUG", DEBUG);
        enumMap.put(WavDirectory.LIST_INFO, INFO);
        enumMap.put("ERROR", ERROR);
        enumMap.put(DriveModeVoxUiConstants.WARN, WARN);
        enumMap.put("DISABLED", DISABLED);
    }

    LogLevel(String str) {
        this.value = str;
    }

    public static LogLevel fromValue(String str) {
        if (str != null && !str.isEmpty()) {
            if (enumMap.containsKey(str)) {
                return enumMap.get(str);
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline75("Cannot create enum from ", str, " value!"));
        }
        throw new IllegalArgumentException("Value cannot be null or empty!");
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }
}
