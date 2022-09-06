package com.amazonaws.services.s3.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class S3ObjectId implements Serializable {
    private final String bucket;
    private final String key;
    private final String versionId;

    public S3ObjectId(String str, String str2) {
        this(str, str2, null);
    }

    public String getBucket() {
        return this.bucket;
    }

    public String getKey() {
        return this.key;
    }

    public String getVersionId() {
        return this.versionId;
    }

    public InstructionFileId instructionFileId() {
        return instructionFileId(null);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("bucket: ");
        outline107.append(this.bucket);
        outline107.append(", key: ");
        outline107.append(this.key);
        outline107.append(", versionId: ");
        outline107.append(this.versionId);
        return outline107.toString();
    }

    public S3ObjectId(String str, String str2, String str3) {
        if (str != null && str2 != null) {
            this.bucket = str;
            this.key = str2;
            this.versionId = str3;
            return;
        }
        throw new IllegalArgumentException("bucket and key must be specified");
    }

    public InstructionFileId instructionFileId(String str) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(GeneratedOutlineSupport1.outline91(new StringBuilder(), this.key, "."));
        if (str == null || str.trim().length() == 0) {
            str = "instruction";
        }
        outline107.append(str);
        return new InstructionFileId(this.bucket, outline107.toString(), this.versionId);
    }

    public S3ObjectId(S3ObjectIdBuilder s3ObjectIdBuilder) {
        this.bucket = s3ObjectIdBuilder.getBucket();
        this.key = s3ObjectIdBuilder.getKey();
        this.versionId = s3ObjectIdBuilder.getVersionId();
    }
}
