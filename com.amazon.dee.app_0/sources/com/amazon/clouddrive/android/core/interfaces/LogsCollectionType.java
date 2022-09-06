package com.amazon.clouddrive.android.core.interfaces;

import androidx.annotation.NonNull;
/* loaded from: classes11.dex */
public enum LogsCollectionType {
    DEVICE_LOGS("Device Logs"),
    DEVICE_EVENTS("Device Events"),
    BUILD_CONFIGURATION("Build Configuration"),
    FLAVOR("Build Flavor"),
    FLAVOR_VERSION_CODE("Flavor Version Code"),
    FLAVOR_VERSION_NAME("Flavor Version Name"),
    VERSION_CODE("Version"),
    DEVICE_STORAGE_INFO("Storage Information"),
    TOTAL_INTERNAL_MEMORY("Total Internal Memory In Bytes"),
    AVAILABLE_INTERNAL_MEMORY("Available Internal Memory In Bytes"),
    WEBLABS("Weblab Allocations"),
    PREFERENCES("Shared Preferences"),
    PREFERENCES_DEFAULT("Shared Preferences (Default)"),
    PREFERENCES_ACCOUNT("Shared Preferences (Account)"),
    PREFERENCES_KINDLE("Shared Preferences (Kindle)"),
    PREFERENCES_REMOTE_CONFIG("Shared Preferences (Remote Config)"),
    PREFERENCES_UPLOAD_MIGRATION("Shared Preferences (Upload Migration)"),
    UPLOADER("Uploader Information"),
    UPLOADER_FILE_QUEUE_STATUS("Uploader File & Queue Status"),
    UPLOADER_QUEUE_BLOCKERS("Queue Blockers"),
    UPLOADER_REQUEST_BLOCKERS("Request Blockers"),
    UPLOADER_STORAGE_INFORMATION("Uploader Storage Information");
    
    private final String id;

    LogsCollectionType(String str) {
        this.id = str;
    }

    @Override // java.lang.Enum
    @NonNull
    public String toString() {
        return this.id;
    }
}
