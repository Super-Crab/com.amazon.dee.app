package com.here.sdk.routing;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.here.NativeBase;
import com.here.sdk.core.GeoBox;
import com.here.sdk.core.GeoCoordinates;
import java.util.List;
/* loaded from: classes3.dex */
public final class Section extends NativeBase {
    private List<Link> cache_getLinks;
    private List<Maneuver> cache_getManeuvers;
    private List<Notice> cache_getNotices;
    private List<GeoCoordinates> cache_getPolyline;
    private List<PostAction> cache_getPostActions;
    private List<TrafficSpeed> cache_getTrafficSpeeds;
    private boolean is_cached_getLinks;
    private boolean is_cached_getManeuvers;
    private boolean is_cached_getNotices;
    private boolean is_cached_getPolyline;
    private boolean is_cached_getPostActions;
    private boolean is_cached_getTrafficSpeeds;

    protected Section(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.routing.Section.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                Section.disposeNativeHandle(j2);
            }
        });
        this.is_cached_getPolyline = false;
        this.is_cached_getManeuvers = false;
        this.is_cached_getTrafficSpeeds = false;
        this.is_cached_getPostActions = false;
        this.is_cached_getNotices = false;
        this.is_cached_getLinks = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    private native List<Link> getLinks_private();

    private native List<Maneuver> getManeuvers_private();

    private native List<Notice> getNotices_private();

    private native List<GeoCoordinates> getPolyline_private();

    private native List<PostAction> getPostActions_private();

    private native List<TrafficSpeed> getTrafficSpeeds_private();

    @NonNull
    public native Arrival getArrival();

    @NonNull
    public native GeoBox getBoundingBox();

    @NonNull
    public native Departure getDeparture();

    public native int getDurationInSeconds();

    @Nullable
    public native EVDetails getEvDetails();

    @NonNull
    native List<Maneuver> getGuidanceManeuvers();

    public native int getLengthInMeters();

    @NonNull
    List<Link> getLinks() {
        if (!this.is_cached_getLinks) {
            this.cache_getLinks = getLinks_private();
            this.is_cached_getLinks = true;
        }
        return this.cache_getLinks;
    }

    @NonNull
    public List<Maneuver> getManeuvers() {
        if (!this.is_cached_getManeuvers) {
            this.cache_getManeuvers = getManeuvers_private();
            this.is_cached_getManeuvers = true;
        }
        return this.cache_getManeuvers;
    }

    @NonNull
    public List<Notice> getNotices() {
        if (!this.is_cached_getNotices) {
            this.cache_getNotices = getNotices_private();
            this.is_cached_getNotices = true;
        }
        return this.cache_getNotices;
    }

    @NonNull
    public List<GeoCoordinates> getPolyline() {
        if (!this.is_cached_getPolyline) {
            this.cache_getPolyline = getPolyline_private();
            this.is_cached_getPolyline = true;
        }
        return this.cache_getPolyline;
    }

    @NonNull
    public List<PostAction> getPostActions() {
        if (!this.is_cached_getPostActions) {
            this.cache_getPostActions = getPostActions_private();
            this.is_cached_getPostActions = true;
        }
        return this.cache_getPostActions;
    }

    @NonNull
    public native SectionTransportMode getSectionTransportMode();

    public native int getTrafficDelayInSeconds();

    @NonNull
    public List<TrafficSpeed> getTrafficSpeeds() {
        if (!this.is_cached_getTrafficSpeeds) {
            this.cache_getTrafficSpeeds = getTrafficSpeeds_private();
            this.is_cached_getTrafficSpeeds = true;
        }
        return this.cache_getTrafficSpeeds;
    }

    @NonNull
    public native TransportMode getTransportMode();
}
