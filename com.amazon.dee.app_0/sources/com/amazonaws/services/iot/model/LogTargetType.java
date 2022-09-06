package com.amazonaws.services.iot.model;

import com.amazon.alexa.redesign.view.carousel.ChipIconTitleViewHolder;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public enum LogTargetType {
    DEFAULT(ChipIconTitleViewHolder.STATE_DEFAULT),
    THING_GROUP("THING_GROUP");
    
    private static final Map<String, LogTargetType> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put(ChipIconTitleViewHolder.STATE_DEFAULT, DEFAULT);
        enumMap.put("THING_GROUP", THING_GROUP);
    }

    LogTargetType(String str) {
        this.value = str;
    }

    public static LogTargetType fromValue(String str) {
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
