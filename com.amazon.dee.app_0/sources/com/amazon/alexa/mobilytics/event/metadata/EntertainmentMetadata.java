package com.amazon.alexa.mobilytics.event.metadata;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.mobilytics.protobuf.EventDetailsProto;
/* loaded from: classes9.dex */
public class EntertainmentMetadata implements DefaultEventMetadata {
    private String itemID;
    private Long itemIndex;
    private final String metadataType = "entertainment";
    private String pageID;
    private String sectionID;
    private Long sectionIndex;

    @Nullable
    public String getItemID() {
        return this.itemID;
    }

    @Nullable
    public Long getItemIndex() {
        return this.itemIndex;
    }

    @Override // com.amazon.alexa.mobilytics.event.metadata.EventMetadata
    public String getMetadataType() {
        return "entertainment";
    }

    @Nullable
    public String getPageID() {
        return this.pageID;
    }

    @Nullable
    public String getSectionID() {
        return this.sectionID;
    }

    @Nullable
    public Long getSectionIndex() {
        return this.sectionIndex;
    }

    @Override // com.amazon.alexa.mobilytics.event.metadata.DefaultEventMetadata
    @NonNull
    public EventDetailsProto.Metadata serialize() {
        EventDetailsProto.Metadata.Builder newBuilder = EventDetailsProto.Metadata.newBuilder();
        EventDetailsProto.Metadata.Entertainemnt.Builder newBuilder2 = EventDetailsProto.Metadata.Entertainemnt.newBuilder();
        String str = this.pageID;
        if (str != null) {
            newBuilder2.setPageID(str);
        }
        String str2 = this.sectionID;
        if (str2 != null) {
            newBuilder2.setSectionID(str2);
        }
        String str3 = this.itemID;
        if (str3 != null) {
            newBuilder2.setItemID(str3);
        }
        Long l = this.sectionIndex;
        if (l != null) {
            newBuilder2.setSectionIndex(l.longValue());
        }
        Long l2 = this.itemIndex;
        if (l2 != null) {
            newBuilder2.setItemIndex(l2.longValue());
        }
        newBuilder.setEntertainemnt(newBuilder2);
        return newBuilder.mo10084build();
    }

    public EntertainmentMetadata withItemID(@Nullable String str) {
        this.itemID = str;
        return this;
    }

    public EntertainmentMetadata withItemIndex(@Nullable Long l) {
        this.itemIndex = l;
        return this;
    }

    public EntertainmentMetadata withPageID(@Nullable String str) {
        this.pageID = str;
        return this;
    }

    public EntertainmentMetadata withSectionID(@Nullable String str) {
        this.sectionID = str;
        return this;
    }

    public EntertainmentMetadata withSectionIndex(@Nullable Long l) {
        this.sectionIndex = l;
        return this;
    }
}
