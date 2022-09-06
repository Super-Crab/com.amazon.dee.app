package com.amazon.alexa.voice.ui.player;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.imageutils.JfifUtil;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes11.dex */
public final class TrackInformation implements TrackInformationModel {
    public static final Parcelable.Creator<TrackInformation> CREATOR = new Parcelable.Creator<TrackInformation>() { // from class: com.amazon.alexa.voice.ui.player.TrackInformation.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public TrackInformation mo2784createFromParcel(Parcel parcel) {
            return new TrackInformation(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public TrackInformation[] mo2785newArray(int i) {
            return new TrackInformation[i];
        }
    };
    private final String artImageUrl;
    private final String audioItemId;
    private final CharSequence description;
    private final CharSequence details;
    private final int maxPosition;
    private final String providerIconId;
    private final CharSequence providerName;
    private final boolean seekable;
    private final CharSequence title;
    private final CharSequence totalTime;

    /* loaded from: classes11.dex */
    public static final class Builder {
        String artImageUrl;
        String audioItemId;
        CharSequence description;
        CharSequence details;
        int maxPosition;
        String providerIconId;
        CharSequence providerName;
        boolean seekable;
        CharSequence title;
        CharSequence totalTime;

        public Builder artImageUrl(String str) {
            this.artImageUrl = str;
            return this;
        }

        public Builder audioItemId(String str) {
            this.audioItemId = str;
            return this;
        }

        public TrackInformation build() {
            return new TrackInformation(this);
        }

        public Builder description(CharSequence charSequence) {
            this.description = charSequence;
            return this;
        }

        public Builder details(CharSequence charSequence) {
            this.details = charSequence;
            return this;
        }

        public Builder maxPosition(int i) {
            this.maxPosition = i;
            return this;
        }

        public Builder providerIconId(String str) {
            this.providerIconId = str;
            return this;
        }

        public Builder providerName(CharSequence charSequence) {
            this.providerName = charSequence;
            return this;
        }

        public Builder seekable(boolean z) {
            this.seekable = z;
            return this;
        }

        public Builder title(CharSequence charSequence) {
            this.title = charSequence;
            return this;
        }

        public Builder totalTime(CharSequence charSequence) {
            this.totalTime = charSequence;
            return this;
        }
    }

    TrackInformation(Builder builder) {
        this.title = builder.title;
        this.description = builder.description;
        this.details = builder.details;
        this.artImageUrl = builder.artImageUrl;
        this.providerIconId = builder.providerIconId;
        this.providerName = builder.providerName;
        this.totalTime = builder.totalTime;
        this.maxPosition = builder.maxPosition;
        this.audioItemId = builder.audioItemId;
        this.seekable = builder.seekable;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || TrackInformation.class != obj.getClass()) {
            return false;
        }
        TrackInformation trackInformation = (TrackInformation) obj;
        CharSequence charSequence = this.title;
        if (charSequence == null ? trackInformation.title != null : !charSequence.equals(trackInformation.title)) {
            return false;
        }
        CharSequence charSequence2 = this.description;
        if (charSequence2 == null ? trackInformation.description != null : !charSequence2.equals(trackInformation.description)) {
            return false;
        }
        CharSequence charSequence3 = this.details;
        if (charSequence3 == null ? trackInformation.details != null : !charSequence3.equals(trackInformation.details)) {
            return false;
        }
        String str = this.artImageUrl;
        if (str == null ? trackInformation.artImageUrl != null : !str.equals(trackInformation.artImageUrl)) {
            return false;
        }
        String str2 = this.providerIconId;
        if (str2 == null ? trackInformation.providerIconId != null : !str2.equals(trackInformation.providerIconId)) {
            return false;
        }
        CharSequence charSequence4 = this.providerName;
        if (charSequence4 == null ? trackInformation.providerName != null : !charSequence4.equals(trackInformation.providerName)) {
            return false;
        }
        CharSequence charSequence5 = this.totalTime;
        if (charSequence5 == null ? trackInformation.totalTime != null : !charSequence5.equals(trackInformation.totalTime)) {
            return false;
        }
        if (this.maxPosition != trackInformation.maxPosition) {
            return false;
        }
        String str3 = this.audioItemId;
        if (str3 == null ? trackInformation.audioItemId != null : !str3.equals(trackInformation.audioItemId)) {
            return false;
        }
        return this.seekable == trackInformation.seekable;
    }

    @Override // com.amazon.alexa.voice.ui.player.TrackInformationModel
    @Nullable
    public String getArtImageUrl() {
        return this.artImageUrl;
    }

    @Override // com.amazon.alexa.voice.ui.player.TrackInformationModel
    @Nullable
    public String getAudioItemId() {
        return this.audioItemId;
    }

    @Override // com.amazon.alexa.voice.ui.player.TrackInformationModel
    public CharSequence getDescription() {
        return this.description;
    }

    @Override // com.amazon.alexa.voice.ui.player.TrackInformationModel
    public CharSequence getDetails() {
        return this.details;
    }

    @Override // com.amazon.alexa.voice.ui.player.TrackInformationModel
    public int getMaxPosition() {
        return this.maxPosition;
    }

    @Override // com.amazon.alexa.voice.ui.player.TrackInformationModel
    @Nullable
    public String getProviderIconId() {
        return this.providerIconId;
    }

    @Override // com.amazon.alexa.voice.ui.player.TrackInformationModel
    @Nullable
    public CharSequence getProviderName() {
        return this.providerName;
    }

    @Override // com.amazon.alexa.voice.ui.player.TrackInformationModel
    public CharSequence getTitle() {
        return this.title;
    }

    @Override // com.amazon.alexa.voice.ui.player.TrackInformationModel
    public CharSequence getTotalTime() {
        return this.totalTime;
    }

    public int hashCode() {
        CharSequence charSequence = this.title;
        int i = 0;
        int hashCode = (JfifUtil.MARKER_EOI + (charSequence != null ? charSequence.hashCode() : 0)) * 31;
        CharSequence charSequence2 = this.description;
        int hashCode2 = (hashCode + (charSequence2 != null ? charSequence2.hashCode() : 0)) * 31;
        CharSequence charSequence3 = this.details;
        int hashCode3 = (hashCode2 + (charSequence3 != null ? charSequence3.hashCode() : 0)) * 31;
        String str = this.artImageUrl;
        int hashCode4 = (hashCode3 + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.providerIconId;
        int hashCode5 = (hashCode4 + (str2 != null ? str2.hashCode() : 0)) * 31;
        CharSequence charSequence4 = this.providerName;
        int hashCode6 = (hashCode5 + (charSequence4 != null ? charSequence4.hashCode() : 0)) * 31;
        CharSequence charSequence5 = this.totalTime;
        int hashCode7 = (((hashCode6 + (charSequence5 != null ? charSequence5.hashCode() : 0)) * 31) + this.maxPosition) * 31;
        String str3 = this.audioItemId;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return ((hashCode7 + i) * 31) + (this.seekable ? 1 : 0);
    }

    @Override // com.amazon.alexa.voice.ui.player.TrackInformationModel
    public boolean isSeekable() {
        return this.seekable;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TrackInformation{title=");
        outline107.append((Object) this.title);
        outline107.append(", description=");
        outline107.append((Object) this.description);
        outline107.append(", details=");
        outline107.append((Object) this.details);
        outline107.append(", artImageUrl=");
        outline107.append(this.artImageUrl);
        outline107.append(", providerIconId=");
        outline107.append(this.providerIconId);
        outline107.append(", providerName=");
        outline107.append((Object) this.providerName);
        outline107.append(", totalTime=");
        outline107.append((Object) this.totalTime);
        outline107.append(", maxPosition=");
        outline107.append(this.maxPosition);
        outline107.append(", audioItemId=");
        outline107.append(this.audioItemId);
        outline107.append(", seekable=");
        outline107.append(this.seekable);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        TextUtils.writeToParcel(this.title, parcel, i);
        TextUtils.writeToParcel(this.description, parcel, i);
        TextUtils.writeToParcel(this.details, parcel, i);
        parcel.writeString(this.artImageUrl);
        parcel.writeString(this.providerIconId);
        TextUtils.writeToParcel(this.providerName, parcel, i);
        TextUtils.writeToParcel(this.totalTime, parcel, i);
        parcel.writeInt(this.maxPosition);
        parcel.writeString(this.audioItemId);
        parcel.writeByte(this.seekable ? (byte) 1 : (byte) 0);
    }

    TrackInformation(Parcel parcel) {
        this.title = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.description = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.details = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.artImageUrl = parcel.readString();
        this.providerIconId = parcel.readString();
        this.providerName = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.totalTime = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.maxPosition = parcel.readInt();
        this.audioItemId = parcel.readString();
        this.seekable = parcel.readByte() != 0;
    }
}
