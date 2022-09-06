package com.amazon.alexa.api;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.alexa.api.Bundles;
import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.utils.validation.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes6.dex */
public final class AlexaMediaPlaybackMetadata {
    @Nullable
    private final Uri albumArtUri;
    @Nullable
    private final String albumName;
    @Nullable
    private final String artistName;
    private final long currentPosition;
    private final long duration;
    @NonNull
    private final String mediaItemIdentifier;
    @Nullable
    private final String mediaServiceProvider;
    @Nullable
    private final Uri secondaryArtUri;
    @Nullable
    private final String songName;

    /* loaded from: classes6.dex */
    static class Adapter implements u<AlexaMediaPlaybackMetadata> {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes6.dex */
        public enum PlaybackBundleKeys implements Bundles.Key {
            AUDIO_ITEM_IDENTIFIER,
            ALBUM_NAME,
            ALBUM_ART_URI,
            SECONDARY_ART_URI,
            SONG_NAME,
            ARTIST_NAME,
            DURATION,
            CURRENT_POSITION,
            MEDIA_SERVICE_PROVIDER
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.api.u
        /* renamed from: createFromBundle */
        public AlexaMediaPlaybackMetadata mo844createFromBundle(Bundle bundle) {
            return AlexaMediaPlaybackMetadata.builder(Bundles.getString(bundle, PlaybackBundleKeys.AUDIO_ITEM_IDENTIFIER)).setSongName(Bundles.getOptionalString(bundle, PlaybackBundleKeys.SONG_NAME)).setMediaServiceProvider(Bundles.getOptionalString(bundle, PlaybackBundleKeys.MEDIA_SERVICE_PROVIDER)).setArtistName(Bundles.getOptionalString(bundle, PlaybackBundleKeys.ARTIST_NAME)).setAlbumName(Bundles.getOptionalString(bundle, PlaybackBundleKeys.ALBUM_NAME)).setAlbumArtUri((Uri) Bundles.getOptionalParcelable(bundle, PlaybackBundleKeys.ALBUM_ART_URI, Uri.class)).setSecondaryArtUri((Uri) Bundles.getOptionalParcelable(bundle, PlaybackBundleKeys.SECONDARY_ART_URI, Uri.class)).setDuration(Bundles.getLong(bundle, PlaybackBundleKeys.DURATION)).setCurrentPosition(Bundles.getLong(bundle, PlaybackBundleKeys.CURRENT_POSITION)).build();
        }

        @Override // com.amazon.alexa.api.u
        public Bundle toBundle(AlexaMediaPlaybackMetadata alexaMediaPlaybackMetadata) {
            Bundle bundle = new Bundle();
            bundle.putString(PlaybackBundleKeys.AUDIO_ITEM_IDENTIFIER.name(), alexaMediaPlaybackMetadata.getMediaItemIdentifier());
            bundle.putString(PlaybackBundleKeys.ALBUM_NAME.name(), alexaMediaPlaybackMetadata.getAlbumName());
            bundle.putString(PlaybackBundleKeys.ARTIST_NAME.name(), alexaMediaPlaybackMetadata.getArtistName());
            bundle.putString(PlaybackBundleKeys.SONG_NAME.name(), alexaMediaPlaybackMetadata.getSongName());
            bundle.putString(PlaybackBundleKeys.MEDIA_SERVICE_PROVIDER.name(), alexaMediaPlaybackMetadata.getMediaServiceProvider());
            bundle.putLong(PlaybackBundleKeys.DURATION.name(), alexaMediaPlaybackMetadata.getDuration());
            bundle.putLong(PlaybackBundleKeys.CURRENT_POSITION.name(), alexaMediaPlaybackMetadata.getCurrentPosition());
            bundle.putParcelable(PlaybackBundleKeys.ALBUM_ART_URI.name(), alexaMediaPlaybackMetadata.getAlbumArtUri());
            bundle.putParcelable(PlaybackBundleKeys.SECONDARY_ART_URI.name(), alexaMediaPlaybackMetadata.getSecondaryArtUri());
            return bundle;
        }
    }

    /* loaded from: classes6.dex */
    public static final class Builder {
        private Uri albumArtUri;
        private String albumName;
        private String artistName;
        private long currentPosition;
        private long duration;
        private final String mediaItemIdentifier;
        private String mediaServiceProvider;
        private Uri secondaryArtUri;
        private String songName;

        private Builder(String str) {
            this.mediaItemIdentifier = str;
        }

        public AlexaMediaPlaybackMetadata build() {
            Preconditions.isFalse(TextUtils.isEmpty(this.mediaItemIdentifier), "media item identifier cannot be empty");
            return new AlexaMediaPlaybackMetadata(this);
        }

        public Builder setAlbumArtUri(Uri uri) {
            this.albumArtUri = uri;
            return this;
        }

        public Builder setAlbumName(String str) {
            this.albumName = str;
            return this;
        }

        public Builder setArtistName(String str) {
            this.artistName = str;
            return this;
        }

        public Builder setCurrentPosition(long j) {
            this.currentPosition = j;
            return this;
        }

        public Builder setDuration(long j) {
            this.duration = j;
            return this;
        }

        public Builder setMediaServiceProvider(String str) {
            this.mediaServiceProvider = str;
            return this;
        }

        public Builder setSecondaryArtUri(Uri uri) {
            this.secondaryArtUri = uri;
            return this;
        }

        public Builder setSongName(String str) {
            this.songName = str;
            return this;
        }
    }

    private AlexaMediaPlaybackMetadata(Builder builder) {
        this.mediaItemIdentifier = builder.mediaItemIdentifier;
        this.songName = builder.songName;
        this.albumName = builder.albumName;
        this.albumArtUri = builder.albumArtUri;
        this.secondaryArtUri = builder.secondaryArtUri;
        this.artistName = builder.artistName;
        this.mediaServiceProvider = builder.mediaServiceProvider;
        this.duration = builder.duration;
        this.currentPosition = builder.currentPosition;
    }

    public static Builder builder(String str) {
        return new Builder(str);
    }

    @Nullable
    public Uri getAlbumArtUri() {
        return this.albumArtUri;
    }

    @Nullable
    public String getAlbumName() {
        return this.albumName;
    }

    @Nullable
    public String getArtistName() {
        return this.artistName;
    }

    public long getCurrentPosition() {
        return this.currentPosition;
    }

    public long getDuration() {
        return this.duration;
    }

    @NonNull
    public String getMediaItemIdentifier() {
        return this.mediaItemIdentifier;
    }

    @Nullable
    public String getMediaServiceProvider() {
        return this.mediaServiceProvider;
    }

    @Nullable
    public Uri getSecondaryArtUri() {
        return this.secondaryArtUri;
    }

    @Nullable
    public String getSongName() {
        return this.songName;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AlexaMediaPlaybackMetadata [mediaItemIdentifier=");
        outline107.append(this.mediaItemIdentifier);
        outline107.append(", songName=");
        outline107.append(this.songName);
        outline107.append(", mediaServiceProvider=");
        outline107.append(this.mediaServiceProvider);
        outline107.append(", artistName=");
        outline107.append(this.artistName);
        outline107.append(", albumName=");
        outline107.append(this.albumName);
        outline107.append(", albumArtUri=");
        outline107.append(this.albumArtUri);
        outline107.append(", secondaryArtUri=");
        outline107.append(this.secondaryArtUri);
        outline107.append(", duration=");
        outline107.append(this.duration);
        outline107.append(", currentPosition=");
        return GeneratedOutlineSupport1.outline87(outline107, this.currentPosition, "]");
    }
}
