package com.amazon.alexa.presence.bleconn.identity;

import com.amazon.alexa.presence.bleconn.identity.model.Key;
/* loaded from: classes9.dex */
public class MatchingRelationshipData {
    private final Key matchingKey;
    private final byte[] validationData;

    public MatchingRelationshipData(Key key, byte[] bArr) {
        this.matchingKey = key;
        this.validationData = bArr;
    }

    public Key getMatchingKey() {
        return this.matchingKey;
    }

    public byte[] getValidationData() {
        return this.validationData;
    }
}
