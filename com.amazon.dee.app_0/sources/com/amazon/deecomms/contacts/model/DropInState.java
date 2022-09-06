package com.amazon.deecomms.contacts.model;

import com.amazon.clouddrive.cdasdk.aps.account.FeatureState;
import com.amazon.deecomms.common.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
/* loaded from: classes12.dex */
public enum DropInState {
    ON("enabled"),
    OFF(FeatureState.DISABLED);
    
    @JsonProperty(Constants.CONTACT_PREF_DROP_IN)
    private final String value;

    DropInState(String str) {
        this.value = str;
    }

    public static DropInState fromValue(String str) {
        DropInState[] values;
        for (DropInState dropInState : values()) {
            if (dropInState.value.equals(str)) {
                return dropInState;
            }
        }
        return OFF;
    }
}
