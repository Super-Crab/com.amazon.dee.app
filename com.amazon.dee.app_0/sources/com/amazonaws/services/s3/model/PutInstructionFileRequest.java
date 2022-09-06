package com.amazonaws.services.s3.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public final class PutInstructionFileRequest extends AmazonWebServiceRequest implements MaterialsDescriptionProvider, EncryptionMaterialsFactory {
    private AccessControlList accessControlList;
    private CannedAccessControlList cannedAcl;
    private final EncryptionMaterials encryptionMaterials;
    private final Map<String, String> matDesc;
    private String redirectLocation;
    private final S3ObjectId s3ObjectId;
    private String storageClass;
    private final String suffix;

    public PutInstructionFileRequest(S3ObjectId s3ObjectId, Map<String, String> map, String str) {
        Map<String, String> unmodifiableMap;
        if (s3ObjectId != null && !(s3ObjectId instanceof InstructionFileId)) {
            if (str != null && !str.trim().isEmpty()) {
                this.s3ObjectId = s3ObjectId;
                if (map == null) {
                    unmodifiableMap = Collections.EMPTY_MAP;
                } else {
                    unmodifiableMap = Collections.unmodifiableMap(new HashMap(map));
                }
                this.matDesc = unmodifiableMap;
                this.suffix = str;
                this.encryptionMaterials = null;
                return;
            }
            throw new IllegalArgumentException("suffix must be specified");
        }
        throw new IllegalArgumentException("Invalid s3 object id");
    }

    public PutObjectRequest createPutObjectRequest(S3Object s3Object) {
        if (s3Object.getBucketName().equals(this.s3ObjectId.getBucket()) && s3Object.getKey().equals(this.s3ObjectId.getKey())) {
            InstructionFileId instructionFileId = this.s3ObjectId.instructionFileId(this.suffix);
            return (PutObjectRequest) new PutObjectRequest(instructionFileId.getBucket(), instructionFileId.getKey(), this.redirectLocation).mo6726withAccessControlList(this.accessControlList).mo6728withCannedAcl(this.cannedAcl).mo6739withStorageClass(this.storageClass).mo6730withGeneralProgressListener(getGeneralProgressListener()).withRequestMetricCollector(getRequestMetricCollector());
        }
        throw new IllegalArgumentException("s3Object passed inconsistent with the instruction file being created");
    }

    public AccessControlList getAccessControlList() {
        return this.accessControlList;
    }

    public CannedAccessControlList getCannedAcl() {
        return this.cannedAcl;
    }

    @Override // com.amazonaws.services.s3.model.EncryptionMaterialsFactory
    public EncryptionMaterials getEncryptionMaterials() {
        return this.encryptionMaterials;
    }

    @Override // com.amazonaws.services.s3.model.MaterialsDescriptionProvider
    public Map<String, String> getMaterialsDescription() {
        Map<String, String> map = this.matDesc;
        return map == null ? this.encryptionMaterials.getMaterialsDescription() : map;
    }

    public String getRedirectLocation() {
        return this.redirectLocation;
    }

    public S3ObjectId getS3ObjectId() {
        return this.s3ObjectId;
    }

    public String getStorageClass() {
        return this.storageClass;
    }

    public String getSuffix() {
        return this.suffix;
    }

    public void setAccessControlList(AccessControlList accessControlList) {
        this.accessControlList = accessControlList;
    }

    public void setCannedAcl(CannedAccessControlList cannedAccessControlList) {
        this.cannedAcl = cannedAccessControlList;
    }

    public void setRedirectLocation(String str) {
        this.redirectLocation = str;
    }

    public void setStorageClass(String str) {
        this.storageClass = str;
    }

    public PutInstructionFileRequest withAccessControlList(AccessControlList accessControlList) {
        setAccessControlList(accessControlList);
        return this;
    }

    public PutInstructionFileRequest withCannedAcl(CannedAccessControlList cannedAccessControlList) {
        setCannedAcl(cannedAccessControlList);
        return this;
    }

    public PutInstructionFileRequest withRedirectLocation(String str) {
        this.redirectLocation = str;
        return this;
    }

    public PutInstructionFileRequest withStorageClass(String str) {
        setStorageClass(str);
        return this;
    }

    public void setStorageClass(StorageClass storageClass) {
        this.storageClass = storageClass.toString();
    }

    public PutInstructionFileRequest withStorageClass(StorageClass storageClass) {
        setStorageClass(storageClass);
        return this;
    }

    public PutInstructionFileRequest(S3ObjectId s3ObjectId, EncryptionMaterials encryptionMaterials, String str) {
        if (s3ObjectId != null && !(s3ObjectId instanceof InstructionFileId)) {
            if (str == null || str.trim().isEmpty()) {
                throw new IllegalArgumentException("suffix must be specified");
            }
            if (encryptionMaterials != null) {
                this.s3ObjectId = s3ObjectId;
                this.suffix = str;
                this.encryptionMaterials = encryptionMaterials;
                this.matDesc = null;
                return;
            }
            throw new IllegalArgumentException("encryption materials must be specified");
        }
        throw new IllegalArgumentException("Invalid s3 object id");
    }
}
