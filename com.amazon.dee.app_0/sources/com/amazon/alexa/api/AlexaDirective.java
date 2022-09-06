package com.amazon.alexa.api;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes6.dex */
public class AlexaDirective implements Parcelable {
    public static final Parcelable.Creator<AlexaDirective> CREATOR = new Parcelable.Creator<AlexaDirective>() { // from class: com.amazon.alexa.api.AlexaDirective.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public AlexaDirective mo787createFromParcel(Parcel parcel) {
            return new AlexaDirective(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public AlexaDirective[] mo788newArray(int i) {
            return new AlexaDirective[i];
        }
    };
    private final AlexaHeader alexaHeader;
    private final AlexaPayload alexaPayload;

    protected AlexaDirective(Parcel parcel) {
        this.alexaHeader = (AlexaHeader) parcel.readParcelable(AlexaHeader.class.getClassLoader());
        this.alexaPayload = (AlexaPayload) parcel.readParcelable(AlexaPayload.class.getClassLoader());
    }

    public AlexaDirective(AlexaHeader alexaHeader, AlexaPayload alexaPayload) {
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

    public String getName() {
        return this.alexaHeader.getName();
    }

    public String getNamespace() {
        return this.alexaHeader.getNamespace();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.alexaHeader, i);
        parcel.writeParcelable(this.alexaPayload, i);
    }
}
