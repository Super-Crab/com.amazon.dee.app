package com.amazonaws.services.s3.model;

import io.ktor.client.utils.CacheControl;
/* loaded from: classes13.dex */
public enum CannedAccessControlList {
    Private(CacheControl.PRIVATE),
    PublicRead("public-read"),
    PublicReadWrite("public-read-write"),
    AuthenticatedRead("authenticated-read"),
    LogDeliveryWrite("log-delivery-write"),
    BucketOwnerRead("bucket-owner-read"),
    BucketOwnerFullControl("bucket-owner-full-control"),
    AwsExecRead("aws-exec-read");
    
    private final String cannedAclHeader;

    CannedAccessControlList(String str) {
        this.cannedAclHeader = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.cannedAclHeader;
    }
}
