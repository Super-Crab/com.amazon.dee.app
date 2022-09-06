package com.amazon.alexa.voice.ui.onedesign.list;
/* loaded from: classes11.dex */
public enum ListType {
    SHOPPING("shopping"),
    TODO("todo"),
    UNKNOWN("unknown");
    
    private final String listTypeAsString;

    ListType(String str) {
        this.listTypeAsString = str;
    }

    public static ListType from(String str) {
        ListType[] values;
        for (ListType listType : values()) {
            if (listType.listTypeAsString.equals(str)) {
                return listType;
            }
        }
        return UNKNOWN;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.listTypeAsString;
    }
}
