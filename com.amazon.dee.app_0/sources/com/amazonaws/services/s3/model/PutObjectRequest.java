package com.amazonaws.services.s3.model;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class PutObjectRequest extends AbstractPutObjectRequest implements Serializable {
    private boolean isRequesterPays;

    public PutObjectRequest(String str, String str2, File file) {
        super(str, str2, file);
    }

    public boolean isRequesterPays() {
        return this.isRequesterPays;
    }

    public void setRequesterPays(boolean z) {
        this.isRequesterPays = z;
    }

    public PutObjectRequest withRequesterPays(boolean z) {
        setRequesterPays(z);
        return this;
    }

    public PutObjectRequest(String str, String str2, String str3) {
        super(str, str2, str3);
    }

    @Override // com.amazonaws.services.s3.model.AbstractPutObjectRequest
    /* renamed from: withAccessControlList  reason: collision with other method in class */
    public PutObjectRequest mo6726withAccessControlList(AccessControlList accessControlList) {
        return (PutObjectRequest) super.mo6726withAccessControlList(accessControlList);
    }

    @Override // com.amazonaws.services.s3.model.AbstractPutObjectRequest
    /* renamed from: withBucketName  reason: collision with other method in class */
    public PutObjectRequest mo6727withBucketName(String str) {
        return (PutObjectRequest) super.mo6727withBucketName(str);
    }

    @Override // com.amazonaws.services.s3.model.AbstractPutObjectRequest
    /* renamed from: withCannedAcl  reason: collision with other method in class */
    public PutObjectRequest mo6728withCannedAcl(CannedAccessControlList cannedAccessControlList) {
        return (PutObjectRequest) super.mo6728withCannedAcl(cannedAccessControlList);
    }

    @Override // com.amazonaws.services.s3.model.AbstractPutObjectRequest
    /* renamed from: withFile  reason: collision with other method in class */
    public PutObjectRequest mo6729withFile(File file) {
        return (PutObjectRequest) super.mo6729withFile(file);
    }

    @Override // com.amazonaws.AmazonWebServiceRequest
    /* renamed from: withGeneralProgressListener  reason: collision with other method in class */
    public PutObjectRequest mo6730withGeneralProgressListener(com.amazonaws.event.ProgressListener progressListener) {
        return (PutObjectRequest) super.mo6730withGeneralProgressListener(progressListener);
    }

    @Override // com.amazonaws.services.s3.model.AbstractPutObjectRequest
    /* renamed from: withInputStream  reason: collision with other method in class */
    public PutObjectRequest mo6731withInputStream(InputStream inputStream) {
        return (PutObjectRequest) super.mo6731withInputStream(inputStream);
    }

    @Override // com.amazonaws.services.s3.model.AbstractPutObjectRequest
    /* renamed from: withKey  reason: collision with other method in class */
    public PutObjectRequest mo6732withKey(String str) {
        return (PutObjectRequest) super.mo6732withKey(str);
    }

    @Override // com.amazonaws.services.s3.model.AbstractPutObjectRequest
    /* renamed from: withMetadata  reason: collision with other method in class */
    public PutObjectRequest mo6733withMetadata(ObjectMetadata objectMetadata) {
        return (PutObjectRequest) super.mo6733withMetadata(objectMetadata);
    }

    @Override // com.amazonaws.services.s3.model.AbstractPutObjectRequest
    @Deprecated
    /* renamed from: withProgressListener  reason: collision with other method in class */
    public PutObjectRequest mo6734withProgressListener(ProgressListener progressListener) {
        return (PutObjectRequest) super.mo6734withProgressListener(progressListener);
    }

    @Override // com.amazonaws.services.s3.model.AbstractPutObjectRequest
    /* renamed from: withRedirectLocation  reason: collision with other method in class */
    public PutObjectRequest mo6735withRedirectLocation(String str) {
        return (PutObjectRequest) super.mo6735withRedirectLocation(str);
    }

    @Override // com.amazonaws.services.s3.model.AbstractPutObjectRequest
    /* renamed from: withSSEAwsKeyManagementParams  reason: collision with other method in class */
    public PutObjectRequest mo6736withSSEAwsKeyManagementParams(SSEAwsKeyManagementParams sSEAwsKeyManagementParams) {
        return (PutObjectRequest) super.mo6736withSSEAwsKeyManagementParams(sSEAwsKeyManagementParams);
    }

    @Override // com.amazonaws.services.s3.model.AbstractPutObjectRequest
    /* renamed from: withSSECustomerKey  reason: collision with other method in class */
    public PutObjectRequest mo6737withSSECustomerKey(SSECustomerKey sSECustomerKey) {
        return (PutObjectRequest) super.mo6737withSSECustomerKey(sSECustomerKey);
    }

    @Override // com.amazonaws.services.s3.model.AbstractPutObjectRequest
    /* renamed from: withTagging  reason: collision with other method in class */
    public PutObjectRequest mo6740withTagging(ObjectTagging objectTagging) {
        super.setTagging(objectTagging);
        return this;
    }

    public PutObjectRequest(String str, String str2, InputStream inputStream, ObjectMetadata objectMetadata) {
        super(str, str2, inputStream, objectMetadata);
    }

    @Override // com.amazonaws.services.s3.model.AbstractPutObjectRequest
    /* renamed from: withStorageClass  reason: collision with other method in class */
    public PutObjectRequest mo6739withStorageClass(String str) {
        return (PutObjectRequest) super.mo6739withStorageClass(str);
    }

    @Override // com.amazonaws.services.s3.model.AbstractPutObjectRequest, com.amazonaws.AmazonWebServiceRequest
    /* renamed from: clone */
    public PutObjectRequest mo6742clone() {
        return (PutObjectRequest) copyPutObjectBaseTo((PutObjectRequest) super.mo6742clone());
    }

    @Override // com.amazonaws.services.s3.model.AbstractPutObjectRequest
    /* renamed from: withStorageClass  reason: collision with other method in class */
    public PutObjectRequest mo6738withStorageClass(StorageClass storageClass) {
        return (PutObjectRequest) super.mo6738withStorageClass(storageClass);
    }
}
