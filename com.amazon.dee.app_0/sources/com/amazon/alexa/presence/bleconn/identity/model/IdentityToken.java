package com.amazon.alexa.presence.bleconn.identity.model;

import com.amazon.alexa.presence.bleconn.helpers.LoggingHelper;
/* loaded from: classes9.dex */
public class IdentityToken {
    private String rollingProximityIdentifierToken;
    private ValidTimePeriod validTimePeriod;

    public IdentityToken() {
    }

    public String getRollingProximityIdentifierToken() {
        return this.rollingProximityIdentifierToken;
    }

    public ValidTimePeriod getValidTimePeriod() {
        return this.validTimePeriod;
    }

    public void setRollingProximityIdentifierToken(String str) {
        this.rollingProximityIdentifierToken = str;
    }

    public void setValidTimePeriod(ValidTimePeriod validTimePeriod) {
        this.validTimePeriod = validTimePeriod;
    }

    public String toString() {
        return LoggingHelper.dump(this);
    }

    public IdentityToken(String str, ValidTimePeriod validTimePeriod) {
        this.rollingProximityIdentifierToken = str;
        this.validTimePeriod = validTimePeriod;
    }
}
