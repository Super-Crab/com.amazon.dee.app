package com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.util;

import org.apache.logging.log4j.util.ProcessIdUtil;
@Deprecated
/* loaded from: classes13.dex */
public class SDKInfo {
    private final String name;
    private final String version;

    public SDKInfo(String str, String str2) {
        this.name = str;
        this.version = str2;
    }

    public String getName() {
        return this.name;
    }

    public String getVersion() {
        return this.version;
    }

    public String toString() {
        return this.name + ProcessIdUtil.DEFAULT_PROCESSID + this.version;
    }
}
