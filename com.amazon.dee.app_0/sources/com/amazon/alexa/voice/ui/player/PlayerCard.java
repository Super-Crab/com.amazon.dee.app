package com.amazon.alexa.voice.ui.player;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.imageutils.JfifUtil;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes11.dex */
public final class PlayerCard implements PlayerCardModel {
    public static final Parcelable.Creator<PlayerCard> CREATOR = new Parcelable.Creator<PlayerCard>() { // from class: com.amazon.alexa.voice.ui.player.PlayerCard.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public PlayerCard mo2782createFromParcel(Parcel parcel) {
            return new PlayerCard(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public PlayerCard[] mo2783newArray(int i) {
            return new PlayerCard[i];
        }
    };
    private final String artImageUrl;
    private final String audioItemId;
    private final String backgroundImageUrl;
    private final CharSequence description;
    private final CharSequence details;
    private final String playbackSource;
    private final boolean progressVisible;
    private final String providerLogoId;
    private final String providerName;
    private final CharSequence title;

    /* loaded from: classes11.dex */
    public static final class Builder {
        String artImageUrl;
        String audioItemId;
        String backgroundImageUrl;
        CharSequence description;
        CharSequence details;
        String playbackSource;
        boolean progressVisible;
        String providerLogoId;
        String providerName;
        CharSequence title;

        public Builder artImageUrl(String str) {
            this.artImageUrl = str;
            return this;
        }

        public Builder audioItemId(String str) {
            this.audioItemId = str;
            return this;
        }

        public Builder backgroundImageUrl(String str) {
            this.backgroundImageUrl = str;
            return this;
        }

        public PlayerCard build() {
            return new PlayerCard(this);
        }

        public Builder description(CharSequence charSequence) {
            this.description = charSequence;
            return this;
        }

        public Builder details(CharSequence charSequence) {
            this.details = charSequence;
            return this;
        }

        public Builder playbackSource(String str) {
            this.playbackSource = str;
            return this;
        }

        public Builder progressVisible(boolean z) {
            this.progressVisible = z;
            return this;
        }

        public Builder providerLogoId(String str) {
            this.providerLogoId = str;
            return this;
        }

        public Builder providerName(String str) {
            this.providerName = str;
            return this;
        }

        public Builder title(CharSequence charSequence) {
            this.title = charSequence;
            return this;
        }
    }

    PlayerCard(Builder builder) {
        this.title = builder.title;
        this.description = builder.description;
        this.details = builder.details;
        this.backgroundImageUrl = builder.backgroundImageUrl;
        this.artImageUrl = builder.artImageUrl;
        this.providerLogoId = builder.providerLogoId;
        this.providerName = builder.providerName;
        this.audioItemId = builder.audioItemId;
        this.progressVisible = builder.progressVisible;
        this.playbackSource = builder.playbackSource;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || PlayerCard.class != obj.getClass()) {
            return false;
        }
        PlayerCard playerCard = (PlayerCard) obj;
        CharSequence charSequence = this.title;
        if (charSequence == null ? playerCard.title != null : !charSequence.equals(playerCard.title)) {
            return false;
        }
        CharSequence charSequence2 = this.description;
        if (charSequence2 == null ? playerCard.description != null : !charSequence2.equals(playerCard.description)) {
            return false;
        }
        CharSequence charSequence3 = this.details;
        if (charSequence3 == null ? playerCard.details != null : !charSequence3.equals(playerCard.details)) {
            return false;
        }
        String str = this.backgroundImageUrl;
        if (str == null ? playerCard.backgroundImageUrl != null : !str.equals(playerCard.backgroundImageUrl)) {
            return false;
        }
        String str2 = this.artImageUrl;
        if (str2 == null ? playerCard.artImageUrl != null : !str2.equals(playerCard.artImageUrl)) {
            return false;
        }
        String str3 = this.providerLogoId;
        if (str3 == null ? playerCard.providerLogoId != null : !str3.equals(playerCard.providerLogoId)) {
            return false;
        }
        String str4 = this.providerName;
        if (str4 == null ? playerCard.providerName != null : !str4.equals(playerCard.providerName)) {
            return false;
        }
        String str5 = this.audioItemId;
        if (str5 == null ? playerCard.audioItemId != null : !str5.equals(playerCard.audioItemId)) {
            return false;
        }
        if (this.progressVisible != playerCard.progressVisible) {
            return false;
        }
        String str6 = this.playbackSource;
        String str7 = playerCard.playbackSource;
        return str6 == null ? str7 == null : str6.equals(str7);
    }

    @Override // com.amazon.alexa.voice.ui.player.PlayerCardModel
    public String getArtImageUrl() {
        return this.artImageUrl;
    }

    @Override // com.amazon.alexa.voice.ui.player.PlayerCardModel
    public String getAudioItemId() {
        return this.audioItemId;
    }

    @Override // com.amazon.alexa.voice.ui.player.PlayerCardModel
    public String getBackgroundImageUrl() {
        return this.backgroundImageUrl;
    }

    @Override // com.amazon.alexa.voice.ui.player.PlayerCardModel
    public CharSequence getDescription() {
        return this.description;
    }

    @Override // com.amazon.alexa.voice.ui.player.PlayerCardModel
    public CharSequence getDetails() {
        return this.details;
    }

    @Override // com.amazon.alexa.voice.ui.player.PlayerCardModel
    public String getPlaybackSource() {
        return this.playbackSource;
    }

    @Override // com.amazon.alexa.voice.ui.player.PlayerCardModel
    public String getProviderLogoId() {
        return this.providerLogoId;
    }

    @Override // com.amazon.alexa.voice.ui.player.PlayerCardModel
    public String getProviderName() {
        return this.providerName;
    }

    @Override // com.amazon.alexa.voice.ui.player.PlayerCardModel
    public CharSequence getTitle() {
        return this.title;
    }

    public int hashCode() {
        CharSequence charSequence = this.title;
        int i = 0;
        int hashCode = (JfifUtil.MARKER_EOI + (charSequence != null ? charSequence.hashCode() : 0)) * 31;
        CharSequence charSequence2 = this.description;
        int hashCode2 = (hashCode + (charSequence2 != null ? charSequence2.hashCode() : 0)) * 31;
        CharSequence charSequence3 = this.details;
        int hashCode3 = (hashCode2 + (charSequence3 != null ? charSequence3.hashCode() : 0)) * 31;
        String str = this.backgroundImageUrl;
        int hashCode4 = (hashCode3 + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.artImageUrl;
        int hashCode5 = (hashCode4 + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.providerLogoId;
        int hashCode6 = (hashCode5 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.providerName;
        int hashCode7 = (hashCode6 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.audioItemId;
        int hashCode8 = (((hashCode7 + (str5 != null ? str5.hashCode() : 0)) * 31) + (this.progressVisible ? 1 : 0)) * 31;
        String str6 = this.playbackSource;
        if (str6 != null) {
            i = str6.hashCode();
        }
        return hashCode8 + i;
    }

    @Override // com.amazon.alexa.voice.ui.player.PlayerCardModel
    public boolean isProgressVisible() {
        return this.progressVisible;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("PlayerCard{title=");
        outline107.append((Object) this.title);
        outline107.append(", description=");
        outline107.append((Object) this.description);
        outline107.append(", details=");
        outline107.append((Object) this.details);
        outline107.append(", backgroundImageUrl=");
        outline107.append(this.backgroundImageUrl);
        outline107.append(", artImageUrl=");
        outline107.append(this.artImageUrl);
        outline107.append(", providerLogoId=");
        outline107.append(this.providerLogoId);
        outline107.append(", providerName=");
        outline107.append(this.providerName);
        outline107.append(", audioItemId=");
        outline107.append(this.audioItemId);
        outline107.append(", progressVisible=");
        outline107.append(this.progressVisible);
        outline107.append(", playbackSource=");
        return GeneratedOutlineSupport1.outline89(outline107, this.playbackSource, JsonReaderKt.END_OBJ);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        TextUtils.writeToParcel(this.title, parcel, i);
        TextUtils.writeToParcel(this.description, parcel, i);
        TextUtils.writeToParcel(this.details, parcel, i);
        parcel.writeString(this.backgroundImageUrl);
        parcel.writeString(this.artImageUrl);
        parcel.writeString(this.providerLogoId);
        parcel.writeString(this.providerName);
        parcel.writeString(this.audioItemId);
        parcel.writeByte(this.progressVisible ? (byte) 1 : (byte) 0);
        parcel.writeString(this.playbackSource);
    }

    PlayerCard(Parcel parcel) {
        this.title = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.description = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.details = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.backgroundImageUrl = parcel.readString();
        this.artImageUrl = parcel.readString();
        this.providerLogoId = parcel.readString();
        this.providerName = parcel.readString();
        this.audioItemId = parcel.readString();
        this.progressVisible = parcel.readByte() != 0;
        this.playbackSource = parcel.readString();
    }
}
