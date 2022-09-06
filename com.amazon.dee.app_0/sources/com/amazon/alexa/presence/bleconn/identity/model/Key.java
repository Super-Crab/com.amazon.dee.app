package com.amazon.alexa.presence.bleconn.identity.model;

import com.amazon.alexa.presence.bleconn.helpers.LoggingHelper;
/* loaded from: classes9.dex */
public class Key {
    private String key;
    private Integer typeId;
    private ValidTimePeriod validTimePeriod;

    public Key() {
    }

    public String getKey() {
        return this.key;
    }

    public Integer getTypeId() {
        return this.typeId;
    }

    public ValidTimePeriod getValidTimePeriod() {
        return this.validTimePeriod;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public void setTypeId(Integer num) {
        this.typeId = num;
    }

    public void setValidTimePeriod(ValidTimePeriod validTimePeriod) {
        this.validTimePeriod = validTimePeriod;
    }

    public String toString() {
        return LoggingHelper.dump(this);
    }

    public Key(String str, ValidTimePeriod validTimePeriod) {
        this.key = str;
        this.validTimePeriod = validTimePeriod;
    }
}
