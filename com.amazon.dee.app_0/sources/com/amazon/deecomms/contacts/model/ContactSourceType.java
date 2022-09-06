package com.amazon.deecomms.contacts.model;

import androidx.annotation.NonNull;
/* loaded from: classes12.dex */
public enum ContactSourceType {
    WhitelistedContact("whitelistedContact"),
    ManuallyAddedContact("manuallyAddedContact"),
    SyncedContact("syncedContact"),
    VoiceAddedContact("voiceAddedContact"),
    AutoAddedContact("autoAddedContact"),
    BulkImportedContact("bulkImportedContact"),
    SkypeContact("skypeContact"),
    MergedContact("mergedContact"),
    UnsetContact("");
    
    @NonNull
    private final String name;

    ContactSourceType(@NonNull String str) {
        this.name = str;
    }

    @NonNull
    public String getName() {
        return this.name;
    }
}
