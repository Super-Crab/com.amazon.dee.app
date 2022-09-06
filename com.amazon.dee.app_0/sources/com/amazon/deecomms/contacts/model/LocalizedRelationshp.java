package com.amazon.deecomms.contacts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes12.dex */
public class LocalizedRelationshp {
    @JsonProperty("localizedValue")
    private String localizedValue;
    @JsonProperty("key")
    private String rawValue;

    public String getLocalizedValue() {
        return this.localizedValue;
    }

    public String getRawValue() {
        return this.rawValue;
    }

    public void setLocalizedValue(String str) {
        this.localizedValue = str;
    }

    public void setRawValue(String str) {
        this.rawValue = str;
    }
}
