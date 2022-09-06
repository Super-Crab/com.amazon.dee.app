package com.amazon.alexa.voice.nowplaying;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.voice.nowplaying.bridge.VoiceBridgePayloadUtil;
import com.amazon.alexa.voice.ui.onedesign.util.JSONUtils;
import com.amazon.alexa.voice.ui.onedesign.util.Logger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Objects;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.json.JSONObject;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public final class PlayerInfoPayload {
    private static final String TAG = "PlayerInfoPayload";
    private final String album;
    private final String artImageUrl;
    private final String artist;
    private final String backgroundImageUrl;
    private final String mediaId;
    private final String playbackSource;
    private final String providerLogoId;
    private final String providerName;
    private final String title;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class Builder {
        String album;
        String artImageUrl;
        String artist;
        String backgroundImageUrl;
        String mediaId;
        String playbackSource;
        String providerLogoId;
        String providerName;
        String title;

        Builder() {
        }

        Builder album(String str) {
            this.album = str;
            return this;
        }

        Builder artImageUrl(String str) {
            this.artImageUrl = str;
            return this;
        }

        Builder artist(String str) {
            this.artist = str;
            return this;
        }

        Builder backgroundImageUrl(String str) {
            this.backgroundImageUrl = str;
            return this;
        }

        PlayerInfoPayload build() {
            String str = this.title;
            if (str == null) {
                str = "";
            }
            this.title = str;
            String str2 = this.artist;
            if (str2 == null) {
                str2 = "";
            }
            this.artist = str2;
            String str3 = this.album;
            if (str3 == null) {
                str3 = "";
            }
            this.album = str3;
            String str4 = this.backgroundImageUrl;
            if (str4 == null) {
                str4 = "";
            }
            this.backgroundImageUrl = str4;
            String str5 = this.artImageUrl;
            if (str5 == null) {
                str5 = "";
            }
            this.artImageUrl = str5;
            String str6 = this.providerLogoId;
            if (str6 == null) {
                str6 = "";
            }
            this.providerLogoId = str6;
            String str7 = this.providerName;
            if (str7 == null) {
                str7 = "";
            }
            this.providerName = str7;
            return new PlayerInfoPayload(this);
        }

        Builder mediaId(String str) {
            this.mediaId = str;
            return this;
        }

        Builder playbackSource(String str) {
            this.playbackSource = str;
            return this;
        }

        Builder providerLogoId(String str) {
            this.providerLogoId = str;
            return this;
        }

        Builder providerName(String str) {
            this.providerName = str;
            return this;
        }

        Builder title(String str) {
            this.title = str;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class Factory {
        /* JADX INFO: Access modifiers changed from: package-private */
        public PlayerInfoPayload create(@NonNull JSONObject jSONObject) {
            String optionalString;
            String optionalString2;
            String optionalString3;
            String optionalString4;
            String optionalString5;
            String optionalString6;
            Builder builder = new Builder();
            String optionalString7 = JSONUtils.optionalString(jSONObject, "mediaId");
            String optionalString8 = JSONUtils.optionalString(jSONObject, VoiceBridgePayloadUtil.PayloadKey.PLAYER_INFO_SOURCE);
            JSONObject optJSONObject = jSONObject.optJSONObject(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_INFO_TEXT);
            String str = null;
            if (optJSONObject == null) {
                Logger.debug("PlayerInfo is missing infoText object");
                optionalString3 = null;
                optionalString = null;
                optionalString2 = null;
            } else {
                optionalString = JSONUtils.optionalString(optJSONObject, "title", "");
                optionalString2 = JSONUtils.optionalString(optJSONObject, EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_SUB_TEXT_1, "");
                optionalString3 = JSONUtils.optionalString(optJSONObject, "subText2", "");
            }
            JSONObject optJSONObject2 = jSONObject.optJSONObject("template");
            if (optJSONObject2 == null) {
                Logger.debug("PlayerInfo is missing template object");
                optionalString5 = null;
                optionalString4 = null;
            } else {
                optionalString4 = JSONUtils.optionalString(optJSONObject2, VoiceBridgePayloadUtil.PayloadKey.PLAYER_INFO_BACKGROUND_IMAGE_URL, "");
                JSONObject optJSONObject3 = optJSONObject2.optJSONObject(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_ART);
                if (optJSONObject3 == null) {
                    Logger.debug("PlayerInfo is missing template.art object");
                    optionalString5 = null;
                } else {
                    optionalString5 = JSONUtils.optionalString(optJSONObject3, "url", "");
                }
            }
            JSONObject optJSONObject4 = jSONObject.optJSONObject(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PROVIDER);
            if (optJSONObject4 == null) {
                Logger.debug("PlayerInfo is missing provider object");
                optionalString6 = null;
            } else {
                optionalString6 = JSONUtils.optionalString(optJSONObject4, "providerName", "");
                JSONObject optJSONObject5 = optJSONObject4.optJSONObject("providerLogo");
                if (optJSONObject5 == null) {
                    Logger.debug("PlayerInfo is missing provider.providerLogo object");
                } else {
                    str = JSONUtils.optionalString(optJSONObject5, "iconId", "");
                }
            }
            return builder.title(optionalString).artist(optionalString2).album(optionalString3).backgroundImageUrl(optionalString4).artImageUrl(optionalString5).providerLogoId(str).providerName(optionalString6).mediaId(optionalString7).playbackSource(optionalString8).build();
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || PlayerInfoPayload.class != obj.getClass()) {
            return false;
        }
        PlayerInfoPayload playerInfoPayload = (PlayerInfoPayload) obj;
        if (!this.title.equals(playerInfoPayload.title) || !this.artist.equals(playerInfoPayload.artist) || !this.album.equals(playerInfoPayload.album) || !this.backgroundImageUrl.equals(playerInfoPayload.backgroundImageUrl) || !this.artImageUrl.equals(playerInfoPayload.artImageUrl) || !this.providerName.equals(playerInfoPayload.providerName) || !this.providerLogoId.equals(playerInfoPayload.providerLogoId)) {
            return false;
        }
        String str = this.mediaId;
        String str2 = playerInfoPayload.mediaId;
        return str != null ? str.equals(str2) : str2 == null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getAlbum() {
        return this.album;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getArtImageUrl() {
        return this.artImageUrl;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getArtist() {
        return this.artist;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getBackgroundImageUrl() {
        return this.backgroundImageUrl;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public String getMediaId() {
        return this.mediaId;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getPlaybackSource() {
        return this.playbackSource;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getProviderLogoId() {
        return this.providerLogoId;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getProviderName() {
        return this.providerName;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getTitle() {
        return this.title;
    }

    public int hashCode() {
        return Objects.hash(this.title, this.artist, this.album, this.backgroundImageUrl, this.artImageUrl, this.providerName, this.providerLogoId, this.mediaId);
    }

    public String toString() {
        String outline91 = this.mediaId == null ? "null" : GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("'"), this.mediaId, "'");
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("PlayerInfoPayload{title=\"");
        GeneratedOutlineSupport1.outline176(outline107, this.title, '\"', ", artist=\"");
        GeneratedOutlineSupport1.outline176(outline107, this.artist, '\"', ", album=\"");
        GeneratedOutlineSupport1.outline176(outline107, this.album, '\"', ", backgroundImageUrl=\"");
        GeneratedOutlineSupport1.outline176(outline107, this.backgroundImageUrl, '\"', ", artImageUrl=\"");
        GeneratedOutlineSupport1.outline176(outline107, this.artImageUrl, '\"', ", providerName=\"");
        GeneratedOutlineSupport1.outline176(outline107, this.providerName, '\"', ", providerLogoId=\"");
        outline107.append(this.providerLogoId);
        outline107.append('\"');
        outline107.append(", mediaId=");
        outline107.append(outline91);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }

    private PlayerInfoPayload(Builder builder) {
        this.title = builder.title;
        this.artist = builder.artist;
        this.album = builder.album;
        this.backgroundImageUrl = builder.backgroundImageUrl;
        this.artImageUrl = builder.artImageUrl;
        this.providerName = builder.providerName;
        this.providerLogoId = builder.providerLogoId;
        this.mediaId = builder.mediaId;
        this.playbackSource = builder.playbackSource;
    }
}
