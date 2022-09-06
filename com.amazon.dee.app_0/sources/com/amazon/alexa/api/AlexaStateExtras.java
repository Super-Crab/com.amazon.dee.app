package com.amazon.alexa.api;

import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.alexa.client.annotations.Nullable;
/* loaded from: classes6.dex */
public class AlexaStateExtras implements Parcelable {
    public static final Parcelable.Creator<AlexaStateExtras> CREATOR = new Parcelable.Creator<AlexaStateExtras>() { // from class: com.amazon.alexa.api.AlexaStateExtras.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public AlexaStateExtras mo818createFromParcel(Parcel parcel) {
            return new AlexaStateExtras(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public AlexaStateExtras[] mo819newArray(int i) {
            return new AlexaStateExtras[i];
        }
    };
    @Nullable
    private String wakeword;

    protected AlexaStateExtras(Parcel parcel) {
        this.wakeword = parcel.readString();
    }

    public AlexaStateExtras(String str) {
        this.wakeword = str;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Nullable
    public String getWakeword() {
        return this.wakeword;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.wakeword);
    }
}
