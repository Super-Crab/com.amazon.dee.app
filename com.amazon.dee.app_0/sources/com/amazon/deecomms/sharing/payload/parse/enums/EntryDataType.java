package com.amazon.deecomms.sharing.payload.parse.enums;
/* loaded from: classes12.dex */
public enum EntryDataType {
    HEADLESS_ENTRY_DATA("HeadlessEntryData"),
    MOBILE_ENTRY_DATA("MobileEntryData"),
    MOBILE_EVENT_ENTRY_DATA("MobileEventEntryData"),
    MULTI_MODAL_ENTRY_DATA("MultiModalEntryData");
    
    private final String serializedName;

    EntryDataType(String str) {
        this.serializedName = str;
    }

    public String getSerializedName() {
        return this.serializedName;
    }
}
