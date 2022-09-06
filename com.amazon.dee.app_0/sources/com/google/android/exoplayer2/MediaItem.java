package com.google.android.exoplayer2;

import android.net.Uri;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.exoplayer2.MediaMetadata;
import com.google.android.exoplayer2.offline.StreamKey;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
/* loaded from: classes2.dex */
public final class MediaItem {
    public final ClippingProperties clippingProperties;
    public final LiveConfiguration liveConfiguration;
    public final String mediaId;
    public final MediaMetadata mediaMetadata;
    @Nullable
    public final PlaybackProperties playbackProperties;

    /* loaded from: classes2.dex */
    public static final class AdsConfiguration {
        public final Uri adTagUri;
        @Nullable
        public final Object adsId;

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AdsConfiguration)) {
                return false;
            }
            AdsConfiguration adsConfiguration = (AdsConfiguration) obj;
            return this.adTagUri.equals(adsConfiguration.adTagUri) && Util.areEqual(this.adsId, adsConfiguration.adsId);
        }

        public int hashCode() {
            int hashCode = this.adTagUri.hashCode() * 31;
            Object obj = this.adsId;
            return hashCode + (obj != null ? obj.hashCode() : 0);
        }

        private AdsConfiguration(Uri uri, @Nullable Object obj) {
            this.adTagUri = uri;
            this.adsId = obj;
        }
    }

    /* loaded from: classes2.dex */
    public static final class Builder {
        @Nullable
        private Uri adTagUri;
        @Nullable
        private Object adsId;
        private long clipEndPositionMs;
        private boolean clipRelativeToDefaultPosition;
        private boolean clipRelativeToLiveWindow;
        private long clipStartPositionMs;
        private boolean clipStartsAtKeyFrame;
        @Nullable
        private String customCacheKey;
        private boolean drmForceDefaultLicenseUri;
        @Nullable
        private byte[] drmKeySetId;
        private Map<String, String> drmLicenseRequestHeaders;
        @Nullable
        private Uri drmLicenseUri;
        private boolean drmMultiSession;
        private boolean drmPlayClearContentWithoutKey;
        private List<Integer> drmSessionForClearTypes;
        @Nullable
        private UUID drmUuid;
        private long liveMaxOffsetMs;
        private float liveMaxPlaybackSpeed;
        private long liveMinOffsetMs;
        private float liveMinPlaybackSpeed;
        private long liveTargetOffsetMs;
        @Nullable
        private String mediaId;
        @Nullable
        private MediaMetadata mediaMetadata;
        @Nullable
        private String mimeType;
        private List<StreamKey> streamKeys;
        private List<Subtitle> subtitles;
        @Nullable
        private Object tag;
        @Nullable
        private Uri uri;

        public MediaItem build() {
            PlaybackProperties playbackProperties;
            Assertions.checkState(this.drmLicenseUri == null || this.drmUuid != null);
            Uri uri = this.uri;
            if (uri != null) {
                String str = this.mimeType;
                UUID uuid = this.drmUuid;
                DrmConfiguration drmConfiguration = uuid != null ? new DrmConfiguration(uuid, this.drmLicenseUri, this.drmLicenseRequestHeaders, this.drmMultiSession, this.drmForceDefaultLicenseUri, this.drmPlayClearContentWithoutKey, this.drmSessionForClearTypes, this.drmKeySetId) : null;
                Uri uri2 = this.adTagUri;
                PlaybackProperties playbackProperties2 = new PlaybackProperties(uri, str, drmConfiguration, uri2 != null ? new AdsConfiguration(uri2, this.adsId) : null, this.streamKeys, this.customCacheKey, this.subtitles, this.tag);
                String str2 = this.mediaId;
                if (str2 == null) {
                    str2 = uri.toString();
                }
                this.mediaId = str2;
                playbackProperties = playbackProperties2;
            } else {
                playbackProperties = null;
            }
            String str3 = (String) Assertions.checkNotNull(this.mediaId);
            ClippingProperties clippingProperties = new ClippingProperties(this.clipStartPositionMs, this.clipEndPositionMs, this.clipRelativeToLiveWindow, this.clipRelativeToDefaultPosition, this.clipStartsAtKeyFrame);
            LiveConfiguration liveConfiguration = new LiveConfiguration(this.liveTargetOffsetMs, this.liveMinOffsetMs, this.liveMaxOffsetMs, this.liveMinPlaybackSpeed, this.liveMaxPlaybackSpeed);
            MediaMetadata mediaMetadata = this.mediaMetadata;
            if (mediaMetadata == null) {
                mediaMetadata = new MediaMetadata.Builder().build();
            }
            return new MediaItem(str3, clippingProperties, playbackProperties, liveConfiguration, mediaMetadata);
        }

        public Builder setAdTagUri(@Nullable String str) {
            return setAdTagUri(str != null ? Uri.parse(str) : null);
        }

        public Builder setClipEndPositionMs(long j) {
            Assertions.checkArgument(j == Long.MIN_VALUE || j >= 0);
            this.clipEndPositionMs = j;
            return this;
        }

        public Builder setClipRelativeToDefaultPosition(boolean z) {
            this.clipRelativeToDefaultPosition = z;
            return this;
        }

        public Builder setClipRelativeToLiveWindow(boolean z) {
            this.clipRelativeToLiveWindow = z;
            return this;
        }

        public Builder setClipStartPositionMs(long j) {
            Assertions.checkArgument(j >= 0);
            this.clipStartPositionMs = j;
            return this;
        }

        public Builder setClipStartsAtKeyFrame(boolean z) {
            this.clipStartsAtKeyFrame = z;
            return this;
        }

        public Builder setCustomCacheKey(@Nullable String str) {
            this.customCacheKey = str;
            return this;
        }

        public Builder setDrmForceDefaultLicenseUri(boolean z) {
            this.drmForceDefaultLicenseUri = z;
            return this;
        }

        public Builder setDrmKeySetId(@Nullable byte[] bArr) {
            this.drmKeySetId = bArr != null ? Arrays.copyOf(bArr, bArr.length) : null;
            return this;
        }

        public Builder setDrmLicenseRequestHeaders(@Nullable Map<String, String> map) {
            Map<String, String> emptyMap;
            if (map != null && !map.isEmpty()) {
                emptyMap = Collections.unmodifiableMap(new HashMap(map));
            } else {
                emptyMap = Collections.emptyMap();
            }
            this.drmLicenseRequestHeaders = emptyMap;
            return this;
        }

        public Builder setDrmLicenseUri(@Nullable Uri uri) {
            this.drmLicenseUri = uri;
            return this;
        }

        public Builder setDrmMultiSession(boolean z) {
            this.drmMultiSession = z;
            return this;
        }

        public Builder setDrmPlayClearContentWithoutKey(boolean z) {
            this.drmPlayClearContentWithoutKey = z;
            return this;
        }

        public Builder setDrmSessionForClearPeriods(boolean z) {
            setDrmSessionForClearTypes(z ? Arrays.asList(2, 1) : Collections.emptyList());
            return this;
        }

        public Builder setDrmSessionForClearTypes(@Nullable List<Integer> list) {
            List<Integer> emptyList;
            if (list != null && !list.isEmpty()) {
                emptyList = Collections.unmodifiableList(new ArrayList(list));
            } else {
                emptyList = Collections.emptyList();
            }
            this.drmSessionForClearTypes = emptyList;
            return this;
        }

        public Builder setDrmUuid(@Nullable UUID uuid) {
            this.drmUuid = uuid;
            return this;
        }

        public Builder setLiveMaxOffsetMs(long j) {
            this.liveMaxOffsetMs = j;
            return this;
        }

        public Builder setLiveMaxPlaybackSpeed(float f) {
            this.liveMaxPlaybackSpeed = f;
            return this;
        }

        public Builder setLiveMinOffsetMs(long j) {
            this.liveMinOffsetMs = j;
            return this;
        }

        public Builder setLiveMinPlaybackSpeed(float f) {
            this.liveMinPlaybackSpeed = f;
            return this;
        }

        public Builder setLiveTargetOffsetMs(long j) {
            this.liveTargetOffsetMs = j;
            return this;
        }

        public Builder setMediaId(@Nullable String str) {
            this.mediaId = str;
            return this;
        }

        public Builder setMediaMetadata(MediaMetadata mediaMetadata) {
            this.mediaMetadata = mediaMetadata;
            return this;
        }

        public Builder setMimeType(@Nullable String str) {
            this.mimeType = str;
            return this;
        }

        public Builder setStreamKeys(@Nullable List<StreamKey> list) {
            List<StreamKey> emptyList;
            if (list != null && !list.isEmpty()) {
                emptyList = Collections.unmodifiableList(new ArrayList(list));
            } else {
                emptyList = Collections.emptyList();
            }
            this.streamKeys = emptyList;
            return this;
        }

        public Builder setSubtitles(@Nullable List<Subtitle> list) {
            List<Subtitle> emptyList;
            if (list != null && !list.isEmpty()) {
                emptyList = Collections.unmodifiableList(new ArrayList(list));
            } else {
                emptyList = Collections.emptyList();
            }
            this.subtitles = emptyList;
            return this;
        }

        public Builder setTag(@Nullable Object obj) {
            this.tag = obj;
            return this;
        }

        public Builder setUri(@Nullable String str) {
            return setUri(str == null ? null : Uri.parse(str));
        }

        public Builder() {
            this.clipEndPositionMs = Long.MIN_VALUE;
            this.drmSessionForClearTypes = Collections.emptyList();
            this.drmLicenseRequestHeaders = Collections.emptyMap();
            this.streamKeys = Collections.emptyList();
            this.subtitles = Collections.emptyList();
            this.liveTargetOffsetMs = C.TIME_UNSET;
            this.liveMinOffsetMs = C.TIME_UNSET;
            this.liveMaxOffsetMs = C.TIME_UNSET;
            this.liveMinPlaybackSpeed = -3.4028235E38f;
            this.liveMaxPlaybackSpeed = -3.4028235E38f;
        }

        public Builder setAdTagUri(@Nullable Uri uri) {
            return setAdTagUri(uri, null);
        }

        public Builder setDrmLicenseUri(@Nullable String str) {
            this.drmLicenseUri = str == null ? null : Uri.parse(str);
            return this;
        }

        public Builder setUri(@Nullable Uri uri) {
            this.uri = uri;
            return this;
        }

        public Builder setAdTagUri(@Nullable Uri uri, @Nullable Object obj) {
            this.adTagUri = uri;
            this.adsId = obj;
            return this;
        }

        private Builder(MediaItem mediaItem) {
            this();
            ClippingProperties clippingProperties = mediaItem.clippingProperties;
            this.clipEndPositionMs = clippingProperties.endPositionMs;
            this.clipRelativeToLiveWindow = clippingProperties.relativeToLiveWindow;
            this.clipRelativeToDefaultPosition = clippingProperties.relativeToDefaultPosition;
            this.clipStartPositionMs = clippingProperties.startPositionMs;
            this.clipStartsAtKeyFrame = clippingProperties.startsAtKeyFrame;
            this.mediaId = mediaItem.mediaId;
            this.mediaMetadata = mediaItem.mediaMetadata;
            LiveConfiguration liveConfiguration = mediaItem.liveConfiguration;
            this.liveTargetOffsetMs = liveConfiguration.targetOffsetMs;
            this.liveMinOffsetMs = liveConfiguration.minOffsetMs;
            this.liveMaxOffsetMs = liveConfiguration.maxOffsetMs;
            this.liveMinPlaybackSpeed = liveConfiguration.minPlaybackSpeed;
            this.liveMaxPlaybackSpeed = liveConfiguration.maxPlaybackSpeed;
            PlaybackProperties playbackProperties = mediaItem.playbackProperties;
            if (playbackProperties != null) {
                this.customCacheKey = playbackProperties.customCacheKey;
                this.mimeType = playbackProperties.mimeType;
                this.uri = playbackProperties.uri;
                this.streamKeys = playbackProperties.streamKeys;
                this.subtitles = playbackProperties.subtitles;
                this.tag = playbackProperties.tag;
                DrmConfiguration drmConfiguration = playbackProperties.drmConfiguration;
                if (drmConfiguration != null) {
                    this.drmLicenseUri = drmConfiguration.licenseUri;
                    this.drmLicenseRequestHeaders = drmConfiguration.requestHeaders;
                    this.drmMultiSession = drmConfiguration.multiSession;
                    this.drmForceDefaultLicenseUri = drmConfiguration.forceDefaultLicenseUri;
                    this.drmPlayClearContentWithoutKey = drmConfiguration.playClearContentWithoutKey;
                    this.drmSessionForClearTypes = drmConfiguration.sessionForClearTypes;
                    this.drmUuid = drmConfiguration.uuid;
                    this.drmKeySetId = drmConfiguration.getKeySetId();
                }
                AdsConfiguration adsConfiguration = playbackProperties.adsConfiguration;
                if (adsConfiguration == null) {
                    return;
                }
                this.adTagUri = adsConfiguration.adTagUri;
                this.adsId = adsConfiguration.adsId;
            }
        }
    }

    /* loaded from: classes2.dex */
    public static final class ClippingProperties {
        public final long endPositionMs;
        public final boolean relativeToDefaultPosition;
        public final boolean relativeToLiveWindow;
        public final long startPositionMs;
        public final boolean startsAtKeyFrame;

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ClippingProperties)) {
                return false;
            }
            ClippingProperties clippingProperties = (ClippingProperties) obj;
            return this.startPositionMs == clippingProperties.startPositionMs && this.endPositionMs == clippingProperties.endPositionMs && this.relativeToLiveWindow == clippingProperties.relativeToLiveWindow && this.relativeToDefaultPosition == clippingProperties.relativeToDefaultPosition && this.startsAtKeyFrame == clippingProperties.startsAtKeyFrame;
        }

        public int hashCode() {
            long j = this.startPositionMs;
            long j2 = this.endPositionMs;
            return (((((((((int) (j ^ (j >>> 32))) * 31) + ((int) ((j2 >>> 32) ^ j2))) * 31) + (this.relativeToLiveWindow ? 1 : 0)) * 31) + (this.relativeToDefaultPosition ? 1 : 0)) * 31) + (this.startsAtKeyFrame ? 1 : 0);
        }

        private ClippingProperties(long j, long j2, boolean z, boolean z2, boolean z3) {
            this.startPositionMs = j;
            this.endPositionMs = j2;
            this.relativeToLiveWindow = z;
            this.relativeToDefaultPosition = z2;
            this.startsAtKeyFrame = z3;
        }
    }

    /* loaded from: classes2.dex */
    public static final class DrmConfiguration {
        public final boolean forceDefaultLicenseUri;
        @Nullable
        private final byte[] keySetId;
        @Nullable
        public final Uri licenseUri;
        public final boolean multiSession;
        public final boolean playClearContentWithoutKey;
        public final Map<String, String> requestHeaders;
        public final List<Integer> sessionForClearTypes;
        public final UUID uuid;

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof DrmConfiguration)) {
                return false;
            }
            DrmConfiguration drmConfiguration = (DrmConfiguration) obj;
            return this.uuid.equals(drmConfiguration.uuid) && Util.areEqual(this.licenseUri, drmConfiguration.licenseUri) && Util.areEqual(this.requestHeaders, drmConfiguration.requestHeaders) && this.multiSession == drmConfiguration.multiSession && this.forceDefaultLicenseUri == drmConfiguration.forceDefaultLicenseUri && this.playClearContentWithoutKey == drmConfiguration.playClearContentWithoutKey && this.sessionForClearTypes.equals(drmConfiguration.sessionForClearTypes) && Arrays.equals(this.keySetId, drmConfiguration.keySetId);
        }

        @Nullable
        public byte[] getKeySetId() {
            byte[] bArr = this.keySetId;
            if (bArr != null) {
                return Arrays.copyOf(bArr, bArr.length);
            }
            return null;
        }

        public int hashCode() {
            int hashCode = this.uuid.hashCode() * 31;
            Uri uri = this.licenseUri;
            int hashCode2 = uri != null ? uri.hashCode() : 0;
            int hashCode3 = this.requestHeaders.hashCode();
            return Arrays.hashCode(this.keySetId) + ((this.sessionForClearTypes.hashCode() + ((((((((hashCode3 + ((hashCode + hashCode2) * 31)) * 31) + (this.multiSession ? 1 : 0)) * 31) + (this.forceDefaultLicenseUri ? 1 : 0)) * 31) + (this.playClearContentWithoutKey ? 1 : 0)) * 31)) * 31);
        }

        private DrmConfiguration(UUID uuid, @Nullable Uri uri, Map<String, String> map, boolean z, boolean z2, boolean z3, List<Integer> list, @Nullable byte[] bArr) {
            Assertions.checkArgument(!z2 || uri != null);
            this.uuid = uuid;
            this.licenseUri = uri;
            this.requestHeaders = map;
            this.multiSession = z;
            this.forceDefaultLicenseUri = z2;
            this.playClearContentWithoutKey = z3;
            this.sessionForClearTypes = list;
            this.keySetId = bArr != null ? Arrays.copyOf(bArr, bArr.length) : null;
        }
    }

    /* loaded from: classes2.dex */
    public static final class LiveConfiguration {
        public static final LiveConfiguration UNSET = new LiveConfiguration(C.TIME_UNSET, C.TIME_UNSET, C.TIME_UNSET, -3.4028235E38f, -3.4028235E38f);
        public final long maxOffsetMs;
        public final float maxPlaybackSpeed;
        public final long minOffsetMs;
        public final float minPlaybackSpeed;
        public final long targetOffsetMs;

        public LiveConfiguration(long j, long j2, long j3, float f, float f2) {
            this.targetOffsetMs = j;
            this.minOffsetMs = j2;
            this.maxOffsetMs = j3;
            this.minPlaybackSpeed = f;
            this.maxPlaybackSpeed = f2;
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof LiveConfiguration)) {
                return false;
            }
            LiveConfiguration liveConfiguration = (LiveConfiguration) obj;
            return this.targetOffsetMs == liveConfiguration.targetOffsetMs && this.minOffsetMs == liveConfiguration.minOffsetMs && this.maxOffsetMs == liveConfiguration.maxOffsetMs && this.minPlaybackSpeed == liveConfiguration.minPlaybackSpeed && this.maxPlaybackSpeed == liveConfiguration.maxPlaybackSpeed;
        }

        public int hashCode() {
            long j = this.targetOffsetMs;
            long j2 = this.minOffsetMs;
            long j3 = this.maxOffsetMs;
            int i = ((((((int) (j ^ (j >>> 32))) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + ((int) ((j3 >>> 32) ^ j3))) * 31;
            float f = this.minPlaybackSpeed;
            int i2 = 0;
            int floatToIntBits = (i + (f != 0.0f ? Float.floatToIntBits(f) : 0)) * 31;
            float f2 = this.maxPlaybackSpeed;
            if (f2 != 0.0f) {
                i2 = Float.floatToIntBits(f2);
            }
            return floatToIntBits + i2;
        }
    }

    /* loaded from: classes2.dex */
    public static final class PlaybackProperties {
        @Nullable
        public final AdsConfiguration adsConfiguration;
        @Nullable
        public final String customCacheKey;
        @Nullable
        public final DrmConfiguration drmConfiguration;
        @Nullable
        public final String mimeType;
        public final List<StreamKey> streamKeys;
        public final List<Subtitle> subtitles;
        @Nullable
        public final Object tag;
        public final Uri uri;

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof PlaybackProperties)) {
                return false;
            }
            PlaybackProperties playbackProperties = (PlaybackProperties) obj;
            return this.uri.equals(playbackProperties.uri) && Util.areEqual(this.mimeType, playbackProperties.mimeType) && Util.areEqual(this.drmConfiguration, playbackProperties.drmConfiguration) && Util.areEqual(this.adsConfiguration, playbackProperties.adsConfiguration) && this.streamKeys.equals(playbackProperties.streamKeys) && Util.areEqual(this.customCacheKey, playbackProperties.customCacheKey) && this.subtitles.equals(playbackProperties.subtitles) && Util.areEqual(this.tag, playbackProperties.tag);
        }

        public int hashCode() {
            int hashCode = this.uri.hashCode() * 31;
            String str = this.mimeType;
            int i = 0;
            int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
            DrmConfiguration drmConfiguration = this.drmConfiguration;
            int hashCode3 = (hashCode2 + (drmConfiguration == null ? 0 : drmConfiguration.hashCode())) * 31;
            AdsConfiguration adsConfiguration = this.adsConfiguration;
            int hashCode4 = (this.streamKeys.hashCode() + ((hashCode3 + (adsConfiguration == null ? 0 : adsConfiguration.hashCode())) * 31)) * 31;
            String str2 = this.customCacheKey;
            int hashCode5 = (this.subtitles.hashCode() + ((hashCode4 + (str2 == null ? 0 : str2.hashCode())) * 31)) * 31;
            Object obj = this.tag;
            if (obj != null) {
                i = obj.hashCode();
            }
            return hashCode5 + i;
        }

        private PlaybackProperties(Uri uri, @Nullable String str, @Nullable DrmConfiguration drmConfiguration, @Nullable AdsConfiguration adsConfiguration, List<StreamKey> list, @Nullable String str2, List<Subtitle> list2, @Nullable Object obj) {
            this.uri = uri;
            this.mimeType = str;
            this.drmConfiguration = drmConfiguration;
            this.adsConfiguration = adsConfiguration;
            this.streamKeys = list;
            this.customCacheKey = str2;
            this.subtitles = list2;
            this.tag = obj;
        }
    }

    /* loaded from: classes2.dex */
    public static final class Subtitle {
        @Nullable
        public final String label;
        @Nullable
        public final String language;
        public final String mimeType;
        public final int roleFlags;
        public final int selectionFlags;
        public final Uri uri;

        public Subtitle(Uri uri, String str, @Nullable String str2) {
            this(uri, str, str2, 0);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Subtitle)) {
                return false;
            }
            Subtitle subtitle = (Subtitle) obj;
            return this.uri.equals(subtitle.uri) && this.mimeType.equals(subtitle.mimeType) && Util.areEqual(this.language, subtitle.language) && this.selectionFlags == subtitle.selectionFlags && this.roleFlags == subtitle.roleFlags && Util.areEqual(this.label, subtitle.label);
        }

        public int hashCode() {
            int outline7 = GeneratedOutlineSupport1.outline7(this.mimeType, this.uri.hashCode() * 31, 31);
            String str = this.language;
            int i = 0;
            int hashCode = (((((outline7 + (str == null ? 0 : str.hashCode())) * 31) + this.selectionFlags) * 31) + this.roleFlags) * 31;
            String str2 = this.label;
            if (str2 != null) {
                i = str2.hashCode();
            }
            return hashCode + i;
        }

        public Subtitle(Uri uri, String str, @Nullable String str2, int i) {
            this(uri, str, str2, i, 0, null);
        }

        public Subtitle(Uri uri, String str, @Nullable String str2, int i, int i2, @Nullable String str3) {
            this.uri = uri;
            this.mimeType = str;
            this.language = str2;
            this.selectionFlags = i;
            this.roleFlags = i2;
            this.label = str3;
        }
    }

    public static MediaItem fromUri(String str) {
        return new Builder().setUri(str).build();
    }

    public Builder buildUpon() {
        return new Builder();
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaItem)) {
            return false;
        }
        MediaItem mediaItem = (MediaItem) obj;
        return Util.areEqual(this.mediaId, mediaItem.mediaId) && this.clippingProperties.equals(mediaItem.clippingProperties) && Util.areEqual(this.playbackProperties, mediaItem.playbackProperties) && Util.areEqual(this.liveConfiguration, mediaItem.liveConfiguration) && Util.areEqual(this.mediaMetadata, mediaItem.mediaMetadata);
    }

    public int hashCode() {
        int hashCode = this.mediaId.hashCode() * 31;
        PlaybackProperties playbackProperties = this.playbackProperties;
        int hashCode2 = playbackProperties != null ? playbackProperties.hashCode() : 0;
        int hashCode3 = this.liveConfiguration.hashCode();
        return this.mediaMetadata.hashCode() + ((this.clippingProperties.hashCode() + ((hashCode3 + ((hashCode + hashCode2) * 31)) * 31)) * 31);
    }

    private MediaItem(String str, ClippingProperties clippingProperties, @Nullable PlaybackProperties playbackProperties, LiveConfiguration liveConfiguration, MediaMetadata mediaMetadata) {
        this.mediaId = str;
        this.playbackProperties = playbackProperties;
        this.liveConfiguration = liveConfiguration;
        this.mediaMetadata = mediaMetadata;
        this.clippingProperties = clippingProperties;
    }

    public static MediaItem fromUri(Uri uri) {
        return new Builder().setUri(uri).build();
    }
}
