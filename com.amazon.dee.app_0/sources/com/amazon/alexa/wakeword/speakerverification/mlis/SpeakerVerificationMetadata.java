package com.amazon.alexa.wakeword.speakerverification.mlis;
/* loaded from: classes11.dex */
public class SpeakerVerificationMetadata {
    private static final String METADATA_CONTENT_TYPE = "application/octet-stream";
    private static final int METADATA_PART_ID = 1;
    private byte[] metadata;
    private int id = 1;
    private String contentType = "application/octet-stream";

    public SpeakerVerificationMetadata(byte[] bArr) {
        this.metadata = bArr;
    }

    public String getContentType() {
        return this.contentType;
    }

    public int getId() {
        return this.id;
    }

    public byte[] getMetadata() {
        return this.metadata;
    }
}
