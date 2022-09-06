package com.amazon.alexa.wakeword;

import com.amazon.alexa.utils.validation.Preconditions;
/* loaded from: classes11.dex */
public class ClassificationData {
    private final byte[] metadata;
    private final byte[] profileId;
    private final int score;

    public ClassificationData(byte[] bArr, int i, byte[] bArr2) {
        Preconditions.notNull(bArr, "profileId is null");
        Preconditions.notNull(bArr2, "metadata is null");
        this.profileId = bArr;
        this.score = i;
        this.metadata = bArr2;
    }

    public byte[] getMetadata() {
        return this.metadata;
    }

    public byte[] getProfileId() {
        return this.profileId;
    }

    public int getScore() {
        return this.score;
    }
}
