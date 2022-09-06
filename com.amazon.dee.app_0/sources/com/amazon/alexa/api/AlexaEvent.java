package com.amazon.alexa.api;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes6.dex */
public class AlexaEvent implements Parcelable {
    public static final Parcelable.Creator<AlexaEvent> CREATOR = new Parcelable.Creator<AlexaEvent>() { // from class: com.amazon.alexa.api.AlexaEvent.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public AlexaEvent mo789createFromParcel(Parcel parcel) {
            return new AlexaEvent(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public AlexaEvent[] mo790newArray(int i) {
            return new AlexaEvent[i];
        }
    };
    private final AlexaHeader alexaHeader;
    private final AlexaPayload alexaPayload;

    protected AlexaEvent(Parcel parcel) {
        this.alexaHeader = (AlexaHeader) parcel.readParcelable(AlexaHeader.class.getClassLoader());
        this.alexaPayload = (AlexaPayload) parcel.readParcelable(AlexaPayload.class.getClassLoader());
    }

    public AlexaEvent(AlexaHeader alexaHeader, AlexaPayload alexaPayload) {
        this.alexaHeader = alexaHeader;
        this.alexaPayload = alexaPayload;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public AlexaHeader getAlexaHeader() {
        return this.alexaHeader;
    }

    public AlexaPayload getAlexaPayload() {
        return this.alexaPayload;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.alexaHeader, i);
        parcel.writeParcelable(this.alexaPayload, i);
    }
}
