package com.amazon.alexa.api;

import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.alexa.client.annotations.Nullable;
/* loaded from: classes6.dex */
public class AlexaAudioMetadata implements Parcelable {
    private final AlexaProfile alexaProfile;
    @Nullable
    private final AlexaWakeWord alexaWakeWord;
    private final String audioFormat;
    public static final String DEFAULT_AUDIO_FORMAT = AudioFormat.AUDIO_L16_RATE_16000_CHANNELS_1.toString();
    public static final Parcelable.Creator<AlexaAudioMetadata> CREATOR = new Parcelable.Creator<AlexaAudioMetadata>() { // from class: com.amazon.alexa.api.AlexaAudioMetadata.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public AlexaAudioMetadata mo764createFromParcel(Parcel parcel) {
            return new AlexaAudioMetadata(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public AlexaAudioMetadata[] mo765newArray(int i) {
            return new AlexaAudioMetadata[i];
        }
    };

    /* loaded from: classes6.dex */
    public static class Builder {
        private AlexaWakeWord alexaWakeWord;
        private AlexaProfile alexaProfile = AlexaProfile.NEAR_FIELD;
        private String audioFormat = AlexaAudioMetadata.DEFAULT_AUDIO_FORMAT;

        public AlexaAudioMetadata build() {
            return new AlexaAudioMetadata(this.alexaProfile, this.alexaWakeWord, this.audioFormat);
        }

        public Builder setAlexaProfile(AlexaProfile alexaProfile) {
            this.alexaProfile = alexaProfile;
            return this;
        }

        public Builder setAlexaWakeWord(AlexaWakeWord alexaWakeWord) {
            this.alexaWakeWord = alexaWakeWord;
            return this;
        }

        public Builder setAudioFormat(String str) {
            this.audioFormat = str;
            return this;
        }
    }

    protected AlexaAudioMetadata(Parcel parcel) {
        this.alexaProfile = (AlexaProfile) parcel.readParcelable(AlexaProfile.class.getClassLoader());
        this.alexaWakeWord = (AlexaWakeWord) parcel.readParcelable(AlexaWakeWord.class.getClassLoader());
        this.audioFormat = parcel.readString();
    }

    public AlexaAudioMetadata(AlexaProfile alexaProfile) {
        this(alexaProfile, null, DEFAULT_AUDIO_FORMAT);
    }

    public AlexaAudioMetadata(AlexaProfile alexaProfile, AlexaWakeWord alexaWakeWord) {
        this(alexaProfile, alexaWakeWord, DEFAULT_AUDIO_FORMAT);
    }

    public AlexaAudioMetadata(AlexaProfile alexaProfile, @Nullable AlexaWakeWord alexaWakeWord, String str) {
        this.alexaProfile = alexaProfile;
        this.alexaWakeWord = alexaWakeWord;
        if (str == null) {
            this.audioFormat = DEFAULT_AUDIO_FORMAT;
        } else {
            this.audioFormat = str;
        }
    }

    public AlexaAudioMetadata(AlexaProfile alexaProfile, String str) {
        this(alexaProfile, null, str);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getAlexaAudioFormat() {
        return this.audioFormat;
    }

    public AlexaProfile getAlexaProfile() {
        return this.alexaProfile;
    }

    @Nullable
    public AlexaWakeWord getAlexaWakeword() {
        return this.alexaWakeWord;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.alexaProfile, i);
        parcel.writeParcelable(this.alexaWakeWord, i);
        parcel.writeString(this.audioFormat);
    }
}
