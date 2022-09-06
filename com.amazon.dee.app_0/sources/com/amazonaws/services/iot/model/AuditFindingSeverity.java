package com.amazonaws.services.iot.model;

import com.amazon.alexa.voice.ui.driveMode.util.DriveModeVoxUiConstants;
import com.amazon.devicesetupservice.scap.v1.BleConnectionPriority;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public enum AuditFindingSeverity {
    CRITICAL(DriveModeVoxUiConstants.CRITICAL),
    HIGH(BleConnectionPriority.HIGH),
    MEDIUM("MEDIUM"),
    LOW(BleConnectionPriority.LOW);
    
    private static final Map<String, AuditFindingSeverity> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put(DriveModeVoxUiConstants.CRITICAL, CRITICAL);
        enumMap.put(BleConnectionPriority.HIGH, HIGH);
        enumMap.put("MEDIUM", MEDIUM);
        enumMap.put(BleConnectionPriority.LOW, LOW);
    }

    AuditFindingSeverity(String str) {
        this.value = str;
    }

    public static AuditFindingSeverity fromValue(String str) {
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
