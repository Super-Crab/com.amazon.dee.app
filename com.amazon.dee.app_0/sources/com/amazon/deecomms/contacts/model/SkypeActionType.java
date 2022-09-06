package com.amazon.deecomms.contacts.model;

import androidx.annotation.NonNull;
/* loaded from: classes12.dex */
public enum SkypeActionType {
    Call("call"),
    Chat("chat");
    
    private final String value;

    SkypeActionType(String str) {
        this.value = str;
    }

    public static boolean isValidAction(@NonNull String str) {
        for (SkypeActionType skypeActionType : values()) {
            if (skypeActionType.getValue().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public String getValue() {
        return this.value;
    }
}
