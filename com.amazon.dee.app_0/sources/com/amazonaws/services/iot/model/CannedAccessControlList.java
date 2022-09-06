package com.amazonaws.services.iot.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import io.ktor.client.utils.CacheControl;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public enum CannedAccessControlList {
    Private(CacheControl.PRIVATE),
    PublicRead("public-read"),
    PublicReadWrite("public-read-write"),
    AwsExecRead("aws-exec-read"),
    AuthenticatedRead("authenticated-read"),
    BucketOwnerRead("bucket-owner-read"),
    BucketOwnerFullControl("bucket-owner-full-control"),
    LogDeliveryWrite("log-delivery-write");
    
    private static final Map<String, CannedAccessControlList> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put(CacheControl.PRIVATE, Private);
        enumMap.put("public-read", PublicRead);
        enumMap.put("public-read-write", PublicReadWrite);
        enumMap.put("aws-exec-read", AwsExecRead);
        enumMap.put("authenticated-read", AuthenticatedRead);
        enumMap.put("bucket-owner-read", BucketOwnerRead);
        enumMap.put("bucket-owner-full-control", BucketOwnerFullControl);
        enumMap.put("log-delivery-write", LogDeliveryWrite);
    }

    CannedAccessControlList(String str) {
        this.value = str;
    }

    public static CannedAccessControlList fromValue(String str) {
        if (str != null && !str.isEmpty()) {
            if (enumMap.containsKey(str)) {
                return enumMap.get(str);
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline75("Cannot create enum from ", str, " value!"));
        }
        throw new IllegalArgumentException("Value cannot be null or empty!");
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }
}
