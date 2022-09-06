package com.amazonaws.services.s3.model;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.internal.Constants;
import com.amazonaws.services.s3.internal.ObjectExpirationResult;
import com.amazonaws.services.s3.internal.ObjectRestoreResult;
import com.amazonaws.services.s3.internal.S3RequesterChargedResult;
import com.amazonaws.services.s3.internal.ServerSideEncryptionResult;
import com.amazonaws.util.DateUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
/* loaded from: classes13.dex */
public class ObjectMetadata implements ServerSideEncryptionResult, S3RequesterChargedResult, ObjectExpirationResult, ObjectRestoreResult, Cloneable, Serializable {
    public static final String AES_256_SERVER_SIDE_ENCRYPTION = SSEAlgorithm.AES256.getAlgorithm();
    public static final String KMS_SERVER_SIDE_ENCRYPTION = SSEAlgorithm.KMS.getAlgorithm();
    private Date expirationTime;
    private String expirationTimeRuleId;
    private Date httpExpiresDate;
    private Map<String, Object> metadata;
    private Boolean ongoingRestore;
    private Date restoreExpirationTime;
    private Map<String, String> userMetadata;

    public ObjectMetadata() {
        this.userMetadata = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        this.metadata = new TreeMap(String.CASE_INSENSITIVE_ORDER);
    }

    public void addUserMetadata(String str, String str2) {
        this.userMetadata.put(str, str2);
    }

    public String getCacheControl() {
        return (String) this.metadata.get("Cache-Control");
    }

    public String getContentDisposition() {
        return (String) this.metadata.get("Content-Disposition");
    }

    public String getContentEncoding() {
        return (String) this.metadata.get("Content-Encoding");
    }

    public String getContentLanguage() {
        return (String) this.metadata.get("Content-Language");
    }

    public long getContentLength() {
        Long l = (Long) this.metadata.get("Content-Length");
        if (l == null) {
            return 0L;
        }
        return l.longValue();
    }

    public String getContentMD5() {
        return (String) this.metadata.get("Content-MD5");
    }

    public Long[] getContentRange() {
        String str = (String) this.metadata.get("Content-Range");
        if (str != null) {
            String[] split = str.split("[ -/]+");
            try {
                return new Long[]{Long.valueOf(Long.parseLong(split[1])), Long.valueOf(Long.parseLong(split[2]))};
            } catch (NumberFormatException e) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to parse content range. Header 'Content-Range' has corrupted data");
                outline107.append(e.getMessage());
                throw new AmazonClientException(outline107.toString(), e);
            }
        }
        return null;
    }

    public String getContentType() {
        return (String) this.metadata.get("Content-Type");
    }

    public String getETag() {
        return (String) this.metadata.get("ETag");
    }

    @Override // com.amazonaws.services.s3.internal.ObjectExpirationResult
    public Date getExpirationTime() {
        return DateUtils.cloneDate(this.expirationTime);
    }

    @Override // com.amazonaws.services.s3.internal.ObjectExpirationResult
    public String getExpirationTimeRuleId() {
        return this.expirationTimeRuleId;
    }

    public Date getHttpExpiresDate() {
        return DateUtils.cloneDate(this.httpExpiresDate);
    }

    public long getInstanceLength() {
        int lastIndexOf;
        String str = (String) this.metadata.get("Content-Range");
        if (str != null && (lastIndexOf = str.lastIndexOf("/")) >= 0) {
            return Long.parseLong(str.substring(lastIndexOf + 1));
        }
        return getContentLength();
    }

    public Date getLastModified() {
        return DateUtils.cloneDate((Date) this.metadata.get("Last-Modified"));
    }

    @Override // com.amazonaws.services.s3.internal.ObjectRestoreResult
    public Boolean getOngoingRestore() {
        return this.ongoingRestore;
    }

    public Integer getPartCount() {
        return (Integer) this.metadata.get(Headers.S3_PARTS_COUNT);
    }

    public Map<String, Object> getRawMetadata() {
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        treeMap.putAll(this.metadata);
        return Collections.unmodifiableMap(treeMap);
    }

    public Object getRawMetadataValue(String str) {
        return this.metadata.get(str);
    }

    public String getReplicationStatus() {
        return (String) this.metadata.get(Headers.OBJECT_REPLICATION_STATUS);
    }

    @Override // com.amazonaws.services.s3.internal.ObjectRestoreResult
    public Date getRestoreExpirationTime() {
        return DateUtils.cloneDate(this.restoreExpirationTime);
    }

    @Override // com.amazonaws.services.s3.internal.ServerSideEncryptionResult
    public String getSSEAlgorithm() {
        return (String) this.metadata.get(Headers.SERVER_SIDE_ENCRYPTION);
    }

    public String getSSEAwsKmsKeyId() {
        return (String) this.metadata.get(Headers.SERVER_SIDE_ENCRYPTION_KMS_KEY_ID);
    }

    @Override // com.amazonaws.services.s3.internal.ServerSideEncryptionResult
    public String getSSECustomerAlgorithm() {
        return (String) this.metadata.get(Headers.SERVER_SIDE_ENCRYPTION_CUSTOMER_ALGORITHM);
    }

    @Override // com.amazonaws.services.s3.internal.ServerSideEncryptionResult
    public String getSSECustomerKeyMd5() {
        return (String) this.metadata.get(Headers.SERVER_SIDE_ENCRYPTION_CUSTOMER_KEY_MD5);
    }

    @Deprecated
    public String getServerSideEncryption() {
        return (String) this.metadata.get(Headers.SERVER_SIDE_ENCRYPTION);
    }

    public String getStorageClass() {
        Object obj = this.metadata.get(Headers.STORAGE_CLASS);
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    public String getUserMetaDataOf(String str) {
        Map<String, String> map = this.userMetadata;
        if (map == null) {
            return null;
        }
        return map.get(str);
    }

    public Map<String, String> getUserMetadata() {
        return this.userMetadata;
    }

    public String getVersionId() {
        return (String) this.metadata.get(Headers.S3_VERSION_ID);
    }

    @Override // com.amazonaws.services.s3.internal.S3RequesterChargedResult
    public boolean isRequesterCharged() {
        return this.metadata.get(Headers.REQUESTER_CHARGED_HEADER) != null;
    }

    public void setCacheControl(String str) {
        this.metadata.put("Cache-Control", str);
    }

    public void setContentDisposition(String str) {
        this.metadata.put("Content-Disposition", str);
    }

    public void setContentEncoding(String str) {
        this.metadata.put("Content-Encoding", str);
    }

    public void setContentLanguage(String str) {
        this.metadata.put("Content-Language", str);
    }

    public void setContentLength(long j) {
        this.metadata.put("Content-Length", Long.valueOf(j));
    }

    public void setContentMD5(String str) {
        if (str == null) {
            this.metadata.remove("Content-MD5");
        } else {
            this.metadata.put("Content-MD5", str);
        }
    }

    public void setContentType(String str) {
        this.metadata.put("Content-Type", str);
    }

    @Override // com.amazonaws.services.s3.internal.ObjectExpirationResult
    public void setExpirationTime(Date date) {
        this.expirationTime = date;
    }

    @Override // com.amazonaws.services.s3.internal.ObjectExpirationResult
    public void setExpirationTimeRuleId(String str) {
        this.expirationTimeRuleId = str;
    }

    public void setHeader(String str, Object obj) {
        this.metadata.put(str, obj);
    }

    public void setHttpExpiresDate(Date date) {
        this.httpExpiresDate = date;
    }

    public void setLastModified(Date date) {
        this.metadata.put("Last-Modified", date);
    }

    @Override // com.amazonaws.services.s3.internal.ObjectRestoreResult
    public void setOngoingRestore(boolean z) {
        this.ongoingRestore = Boolean.valueOf(z);
    }

    @Override // com.amazonaws.services.s3.internal.S3RequesterChargedResult
    public void setRequesterCharged(boolean z) {
        if (z) {
            this.metadata.put(Headers.REQUESTER_CHARGED_HEADER, Constants.REQUESTER_PAYS);
        }
    }

    @Override // com.amazonaws.services.s3.internal.ObjectRestoreResult
    public void setRestoreExpirationTime(Date date) {
        this.restoreExpirationTime = date;
    }

    @Override // com.amazonaws.services.s3.internal.ServerSideEncryptionResult
    public void setSSEAlgorithm(String str) {
        this.metadata.put(Headers.SERVER_SIDE_ENCRYPTION, str);
    }

    @Override // com.amazonaws.services.s3.internal.ServerSideEncryptionResult
    public void setSSECustomerAlgorithm(String str) {
        this.metadata.put(Headers.SERVER_SIDE_ENCRYPTION_CUSTOMER_ALGORITHM, str);
    }

    @Override // com.amazonaws.services.s3.internal.ServerSideEncryptionResult
    public void setSSECustomerKeyMd5(String str) {
        this.metadata.put(Headers.SERVER_SIDE_ENCRYPTION_CUSTOMER_KEY_MD5, str);
    }

    @Deprecated
    public void setServerSideEncryption(String str) {
        this.metadata.put(Headers.SERVER_SIDE_ENCRYPTION, str);
    }

    public void setStorageClass(StorageClass storageClass) {
        this.metadata.put(Headers.STORAGE_CLASS, storageClass);
    }

    public void setUserMetadata(Map<String, String> map) {
        this.userMetadata = map;
    }

    /* renamed from: clone */
    public ObjectMetadata m6723clone() {
        return new ObjectMetadata(this);
    }

    private ObjectMetadata(ObjectMetadata objectMetadata) {
        this.userMetadata = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        this.metadata = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        Map<String, String> map = objectMetadata.userMetadata;
        TreeMap treeMap = null;
        this.userMetadata = map == null ? null : new TreeMap(map);
        Map<String, Object> map2 = objectMetadata.metadata;
        this.metadata = map2 != null ? new TreeMap(map2) : treeMap;
        this.expirationTime = DateUtils.cloneDate(objectMetadata.expirationTime);
        this.expirationTimeRuleId = objectMetadata.expirationTimeRuleId;
        this.httpExpiresDate = DateUtils.cloneDate(objectMetadata.httpExpiresDate);
        this.ongoingRestore = objectMetadata.ongoingRestore;
        this.restoreExpirationTime = DateUtils.cloneDate(objectMetadata.restoreExpirationTime);
    }
}
