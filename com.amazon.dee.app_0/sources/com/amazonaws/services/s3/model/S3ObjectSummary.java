package com.amazonaws.services.s3.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Date;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes13.dex */
public class S3ObjectSummary {
    protected String bucketName;
    protected String eTag;
    protected String key;
    protected Date lastModified;
    protected Owner owner;
    protected long size;
    protected String storageClass;

    public String getBucketName() {
        return this.bucketName;
    }

    public String getETag() {
        return this.eTag;
    }

    public String getKey() {
        return this.key;
    }

    public Date getLastModified() {
        return this.lastModified;
    }

    public Owner getOwner() {
        return this.owner;
    }

    public long getSize() {
        return this.size;
    }

    public String getStorageClass() {
        return this.storageClass;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public void setETag(String str) {
        this.eTag = str;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public void setLastModified(Date date) {
        this.lastModified = date;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public void setSize(long j) {
        this.size = j;
    }

    public void setStorageClass(String str) {
        this.storageClass = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("S3ObjectSummary{bucketName='");
        GeneratedOutlineSupport1.outline176(outline107, this.bucketName, Chars.QUOTE, ", key='");
        GeneratedOutlineSupport1.outline176(outline107, this.key, Chars.QUOTE, ", eTag='");
        GeneratedOutlineSupport1.outline176(outline107, this.eTag, Chars.QUOTE, ", size=");
        outline107.append(this.size);
        outline107.append(", lastModified=");
        outline107.append(this.lastModified);
        outline107.append(", storageClass='");
        GeneratedOutlineSupport1.outline176(outline107, this.storageClass, Chars.QUOTE, ", owner=");
        outline107.append(this.owner);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
