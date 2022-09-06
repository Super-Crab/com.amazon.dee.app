package com.amazonaws.mobileconnectors.s3.transferutility;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public enum TransferType {
    UPLOAD,
    DOWNLOAD,
    ANY;

    public static TransferType getType(String str) {
        if (str.equalsIgnoreCase(UPLOAD.toString())) {
            return UPLOAD;
        }
        if (str.equalsIgnoreCase(DOWNLOAD.toString())) {
            return DOWNLOAD;
        }
        if (str.equalsIgnoreCase(ANY.toString())) {
            return ANY;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline75("Type ", str, " is not a recognized type"));
    }
}
