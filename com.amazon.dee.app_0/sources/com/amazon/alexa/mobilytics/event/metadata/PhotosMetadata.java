package com.amazon.alexa.mobilytics.event.metadata;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.mobilytics.protobuf.EventDetailsProto;
/* loaded from: classes9.dex */
public class PhotosMetadata implements DefaultEventMetadata {
    private Long backupCheckpointCount;
    private String backupCompleteType;
    private String backupMethod;
    private String dataFetchFailedErrorCode;
    private String deduplicationSource;
    private Long maxConcurrentUploadOperations;
    private String mediaType;
    private final String metadataType = EventMetadataType.PHOTOS;
    private Long operationError;
    private String responseErrorCode;
    private String uploadContext;
    private String uploadSessionType;

    @DCMAttributeName("BackupCheckpointCount")
    public String getBackupCheckpointCount() {
        return String.valueOf(this.backupCheckpointCount);
    }

    @DCMAttributeName("BackupCompleteType")
    public String getBackupCompleteType() {
        return this.backupCompleteType;
    }

    @DCMAttributeName("BackupMethod")
    public String getBackupMethod() {
        return this.backupMethod;
    }

    @DCMAttributeName("DataFetchFailedErrorCode")
    public String getDataFetchFailedErrorCode() {
        return this.dataFetchFailedErrorCode;
    }

    @DCMAttributeName("DeduplicationSource")
    public String getDeduplicationSource() {
        return this.deduplicationSource;
    }

    @DCMAttributeName("MaxConcurrentUploadOperations")
    public String getMaxConcurrentUploadOperations() {
        return String.valueOf(this.maxConcurrentUploadOperations);
    }

    @DCMAttributeName("MediaType")
    public String getMediaType() {
        return this.mediaType;
    }

    @Override // com.amazon.alexa.mobilytics.event.metadata.EventMetadata
    public String getMetadataType() {
        return EventMetadataType.PHOTOS;
    }

    @DCMAttributeName("OperationError")
    public String getOperationError() {
        return String.valueOf(this.operationError);
    }

    @DCMAttributeName("ResponseErrorCode")
    public String getResponseErrorCode() {
        return this.responseErrorCode;
    }

    @DCMAttributeName("UploadContext")
    public String getUploadContext() {
        return this.uploadContext;
    }

    @DCMAttributeName("UploadSessionType")
    public String getUploadSessionType() {
        return this.uploadSessionType;
    }

    @Override // com.amazon.alexa.mobilytics.event.metadata.DefaultEventMetadata
    @NonNull
    public EventDetailsProto.Metadata serialize() {
        EventDetailsProto.Metadata.Builder newBuilder = EventDetailsProto.Metadata.newBuilder();
        EventDetailsProto.Metadata.Photos.Builder newBuilder2 = EventDetailsProto.Metadata.Photos.newBuilder();
        String str = this.uploadSessionType;
        if (str != null) {
            newBuilder2.setUploadSessionType(str);
        }
        String str2 = this.backupMethod;
        if (str2 != null) {
            newBuilder2.setBackupMethod(str2);
        }
        Long l = this.maxConcurrentUploadOperations;
        if (l != null) {
            newBuilder2.setMaxConcurrentUploadOperations(l.longValue());
        }
        Long l2 = this.operationError;
        if (l2 != null) {
            newBuilder2.setOperationError(l2.longValue());
        }
        String str3 = this.mediaType;
        if (str3 != null) {
            newBuilder2.setMediaType(str3);
        }
        String str4 = this.dataFetchFailedErrorCode;
        if (str4 != null) {
            newBuilder2.setDataFetchFailedErrorCode(str4);
        }
        String str5 = this.responseErrorCode;
        if (str5 != null) {
            newBuilder2.setResponseErrorCode(str5);
        }
        String str6 = this.deduplicationSource;
        if (str6 != null) {
            newBuilder2.setDeduplicationSource(str6);
        }
        String str7 = this.backupCompleteType;
        if (str7 != null) {
            newBuilder2.setBackupCompleteType(str7);
        }
        Long l3 = this.backupCheckpointCount;
        if (l3 != null) {
            newBuilder2.setBackupCheckpointCount(l3.longValue());
        }
        String str8 = this.uploadContext;
        if (str8 != null) {
            newBuilder2.setUploadContext(str8);
        }
        newBuilder.setPhotos(newBuilder2);
        return newBuilder.mo10084build();
    }

    public PhotosMetadata withBackupCheckpointCount(@Nullable Long l) {
        this.backupCheckpointCount = l;
        return this;
    }

    public PhotosMetadata withBackupCompleteType(@Nullable String str) {
        this.backupCompleteType = str;
        return this;
    }

    public PhotosMetadata withBackupMethod(@Nullable String str) {
        this.backupMethod = str;
        return this;
    }

    public PhotosMetadata withDataFetchFailedErrorCode(@Nullable String str) {
        this.dataFetchFailedErrorCode = str;
        return this;
    }

    public PhotosMetadata withDeduplicationSource(@Nullable String str) {
        this.deduplicationSource = str;
        return this;
    }

    public PhotosMetadata withMaxConcurrentUploadOperations(@Nullable Long l) {
        this.maxConcurrentUploadOperations = l;
        return this;
    }

    public PhotosMetadata withMediaType(@Nullable String str) {
        this.mediaType = str;
        return this;
    }

    public PhotosMetadata withOperationError(@Nullable Long l) {
        this.operationError = l;
        return this;
    }

    public PhotosMetadata withResponseErrorCode(@Nullable String str) {
        this.responseErrorCode = str;
        return this;
    }

    public PhotosMetadata withUploadContext(@Nullable String str) {
        this.uploadContext = str;
        return this;
    }

    public PhotosMetadata withUploadSessionType(@Nullable String str) {
        this.uploadSessionType = str;
        return this;
    }
}
