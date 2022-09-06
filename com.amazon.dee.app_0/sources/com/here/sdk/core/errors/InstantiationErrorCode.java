package com.here.sdk.core.errors;
/* loaded from: classes3.dex */
public enum InstantiationErrorCode {
    ILLEGAL_ARGUMENTS(1),
    FAILED(2),
    SHARED_SDK_ENGINE_NOT_INSTANTIATED(3),
    CACHE_FOLDER_ACCESS_DENIED(4),
    PERSISTENT_MAP_STORAGE_FOLDER_ACCESS_DENIED(5),
    FAILED_TO_LOCK_CACHE_FOLDER(6),
    FAILED_TO_LOCK_PERSISTENT_MAP_STORAGE_FOLDER(7);
    
    public final int value;

    InstantiationErrorCode(int i) {
        this.value = i;
    }
}
