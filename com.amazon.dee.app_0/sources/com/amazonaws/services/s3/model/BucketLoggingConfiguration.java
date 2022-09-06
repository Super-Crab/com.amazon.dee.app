package com.amazonaws.services.s3.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class BucketLoggingConfiguration implements Serializable {
    private String destinationBucketName = null;
    private String logFilePrefix = null;

    public BucketLoggingConfiguration() {
    }

    public String getDestinationBucketName() {
        return this.destinationBucketName;
    }

    public String getLogFilePrefix() {
        return this.logFilePrefix;
    }

    public boolean isLoggingEnabled() {
        return (this.destinationBucketName == null || this.logFilePrefix == null) ? false : true;
    }

    public void setDestinationBucketName(String str) {
        this.destinationBucketName = str;
    }

    public void setLogFilePrefix(String str) {
        if (str == null) {
            str = "";
        }
        this.logFilePrefix = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("LoggingConfiguration enabled=");
        outline107.append(isLoggingEnabled());
        String sb = outline107.toString();
        if (isLoggingEnabled()) {
            StringBuilder outline113 = GeneratedOutlineSupport1.outline113(sb, ", destinationBucketName=");
            outline113.append(getDestinationBucketName());
            outline113.append(", logFilePrefix=");
            outline113.append(getLogFilePrefix());
            return outline113.toString();
        }
        return sb;
    }

    public BucketLoggingConfiguration(String str, String str2) {
        setLogFilePrefix(str2);
        setDestinationBucketName(str);
    }
}
