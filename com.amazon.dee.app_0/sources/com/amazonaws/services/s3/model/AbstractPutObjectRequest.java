package com.amazonaws.services.s3.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
/* loaded from: classes13.dex */
public abstract class AbstractPutObjectRequest extends AmazonWebServiceRequest implements SSECustomerKeyProvider, SSEAwsKeyManagementParamsProvider, S3DataSource, Serializable {
    private AccessControlList accessControlList;
    private String bucketName;
    private CannedAccessControlList cannedAcl;
    private File file;
    private transient InputStream inputStream;
    private String key;
    private ObjectMetadata metadata;
    private String redirectLocation;
    private SSEAwsKeyManagementParams sseAwsKeyManagementParams;
    private SSECustomerKey sseCustomerKey;
    private String storageClass;
    private ObjectTagging tagging;

    public AbstractPutObjectRequest(String str, String str2, File file) {
        this.bucketName = str;
        this.key = str2;
        this.file = file;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final <T extends AbstractPutObjectRequest> T copyPutObjectBaseTo(T t) {
        copyBaseTo(t);
        ObjectMetadata metadata = getMetadata();
        return (T) t.mo6726withAccessControlList(getAccessControlList()).mo6728withCannedAcl(getCannedAcl()).mo6731withInputStream(getInputStream()).mo6733withMetadata(metadata == null ? null : metadata.m6723clone()).mo6735withRedirectLocation(getRedirectLocation()).mo6739withStorageClass(getStorageClass()).mo6736withSSEAwsKeyManagementParams(getSSEAwsKeyManagementParams()).mo6737withSSECustomerKey(getSSECustomerKey());
    }

    public AccessControlList getAccessControlList() {
        return this.accessControlList;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public CannedAccessControlList getCannedAcl() {
        return this.cannedAcl;
    }

    @Override // com.amazonaws.services.s3.model.S3DataSource
    public File getFile() {
        return this.file;
    }

    @Override // com.amazonaws.services.s3.model.S3DataSource
    public InputStream getInputStream() {
        return this.inputStream;
    }

    public String getKey() {
        return this.key;
    }

    public ObjectMetadata getMetadata() {
        return this.metadata;
    }

    @Deprecated
    public ProgressListener getProgressListener() {
        com.amazonaws.event.ProgressListener generalProgressListener = getGeneralProgressListener();
        if (generalProgressListener instanceof LegacyS3ProgressListener) {
            return ((LegacyS3ProgressListener) generalProgressListener).unwrap();
        }
        return null;
    }

    public String getRedirectLocation() {
        return this.redirectLocation;
    }

    @Override // com.amazonaws.services.s3.model.SSEAwsKeyManagementParamsProvider
    public SSEAwsKeyManagementParams getSSEAwsKeyManagementParams() {
        return this.sseAwsKeyManagementParams;
    }

    @Override // com.amazonaws.services.s3.model.SSECustomerKeyProvider
    public SSECustomerKey getSSECustomerKey() {
        return this.sseCustomerKey;
    }

    public String getStorageClass() {
        return this.storageClass;
    }

    public ObjectTagging getTagging() {
        return this.tagging;
    }

    public void setAccessControlList(AccessControlList accessControlList) {
        this.accessControlList = accessControlList;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public void setCannedAcl(CannedAccessControlList cannedAccessControlList) {
        this.cannedAcl = cannedAccessControlList;
    }

    @Override // com.amazonaws.services.s3.model.S3DataSource
    public void setFile(File file) {
        this.file = file;
    }

    @Override // com.amazonaws.services.s3.model.S3DataSource
    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public void setMetadata(ObjectMetadata objectMetadata) {
        this.metadata = objectMetadata;
    }

    @Deprecated
    public void setProgressListener(ProgressListener progressListener) {
        setGeneralProgressListener(new LegacyS3ProgressListener(progressListener));
    }

    public void setRedirectLocation(String str) {
        this.redirectLocation = str;
    }

    public void setSSEAwsKeyManagementParams(SSEAwsKeyManagementParams sSEAwsKeyManagementParams) {
        if (sSEAwsKeyManagementParams != null && this.sseCustomerKey != null) {
            throw new IllegalArgumentException("Either SSECustomerKey or SSEAwsKeyManagementParams must not be set at the same time.");
        }
        this.sseAwsKeyManagementParams = sSEAwsKeyManagementParams;
    }

    public void setSSECustomerKey(SSECustomerKey sSECustomerKey) {
        if (sSECustomerKey != null && this.sseAwsKeyManagementParams != null) {
            throw new IllegalArgumentException("Either SSECustomerKey or SSEAwsKeyManagementParams must not be set at the same time.");
        }
        this.sseCustomerKey = sSECustomerKey;
    }

    public void setStorageClass(String str) {
        this.storageClass = str;
    }

    public void setTagging(ObjectTagging objectTagging) {
        this.tagging = objectTagging;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: withAccessControlList */
    public <T extends AbstractPutObjectRequest> T mo6726withAccessControlList(AccessControlList accessControlList) {
        setAccessControlList(accessControlList);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: withBucketName */
    public <T extends AbstractPutObjectRequest> T mo6727withBucketName(String str) {
        setBucketName(str);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: withCannedAcl */
    public <T extends AbstractPutObjectRequest> T mo6728withCannedAcl(CannedAccessControlList cannedAccessControlList) {
        setCannedAcl(cannedAccessControlList);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: withFile */
    public <T extends AbstractPutObjectRequest> T mo6729withFile(File file) {
        setFile(file);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: withInputStream */
    public <T extends AbstractPutObjectRequest> T mo6731withInputStream(InputStream inputStream) {
        setInputStream(inputStream);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: withKey */
    public <T extends AbstractPutObjectRequest> T mo6732withKey(String str) {
        setKey(str);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: withMetadata */
    public <T extends AbstractPutObjectRequest> T mo6733withMetadata(ObjectMetadata objectMetadata) {
        setMetadata(objectMetadata);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    /* renamed from: withProgressListener */
    public <T extends AbstractPutObjectRequest> T mo6734withProgressListener(ProgressListener progressListener) {
        setProgressListener(progressListener);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: withRedirectLocation */
    public <T extends AbstractPutObjectRequest> T mo6735withRedirectLocation(String str) {
        this.redirectLocation = str;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: withSSEAwsKeyManagementParams */
    public <T extends AbstractPutObjectRequest> T mo6736withSSEAwsKeyManagementParams(SSEAwsKeyManagementParams sSEAwsKeyManagementParams) {
        setSSEAwsKeyManagementParams(sSEAwsKeyManagementParams);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: withSSECustomerKey */
    public <T extends AbstractPutObjectRequest> T mo6737withSSECustomerKey(SSECustomerKey sSECustomerKey) {
        setSSECustomerKey(sSECustomerKey);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: withStorageClass */
    public <T extends AbstractPutObjectRequest> T mo6739withStorageClass(String str) {
        setStorageClass(str);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: withTagging */
    public <T extends AbstractPutObjectRequest> T mo6740withTagging(ObjectTagging objectTagging) {
        setTagging(objectTagging);
        return this;
    }

    public void setStorageClass(StorageClass storageClass) {
        this.storageClass = storageClass.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: withStorageClass */
    public <T extends AbstractPutObjectRequest> T mo6738withStorageClass(StorageClass storageClass) {
        setStorageClass(storageClass);
        return this;
    }

    @Override // com.amazonaws.AmazonWebServiceRequest
    /* renamed from: clone  reason: collision with other method in class */
    public AbstractPutObjectRequest mo6742clone() {
        return (AbstractPutObjectRequest) super.m6646clone();
    }

    public AbstractPutObjectRequest(String str, String str2, String str3) {
        this.bucketName = str;
        this.key = str2;
        this.redirectLocation = str3;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractPutObjectRequest(String str, String str2, InputStream inputStream, ObjectMetadata objectMetadata) {
        this.bucketName = str;
        this.key = str2;
        this.inputStream = inputStream;
        this.metadata = objectMetadata;
    }
}
