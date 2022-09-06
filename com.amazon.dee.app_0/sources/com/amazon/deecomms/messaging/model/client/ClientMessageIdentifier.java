package com.amazon.deecomms.messaging.model.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes12.dex */
public class ClientMessageIdentifier implements Parcelable {
    public static final Parcelable.Creator<ClientMessageIdentifier> CREATOR = new Parcelable.Creator<ClientMessageIdentifier>() { // from class: com.amazon.deecomms.messaging.model.client.ClientMessageIdentifier.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public ClientMessageIdentifier mo3819createFromParcel(Parcel parcel) {
            return new ClientMessageIdentifier(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public ClientMessageIdentifier[] mo3820newArray(int i) {
            return new ClientMessageIdentifier[i];
        }
    };
    @JsonIgnore
    private long mClientID;
    @JsonIgnore
    private String mMediaID;
    private long mMessageID;

    public ClientMessageIdentifier(String str, long j, long j2) {
        this.mMediaID = str;
        this.mClientID = j;
        this.mMessageID = j2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ClientMessageIdentifier.class != obj.getClass()) {
            return false;
        }
        ClientMessageIdentifier clientMessageIdentifier = (ClientMessageIdentifier) obj;
        if (this.mClientID != clientMessageIdentifier.mClientID || this.mMessageID != clientMessageIdentifier.mMessageID) {
            return false;
        }
        return Objects.equal(this.mMediaID, clientMessageIdentifier.mMediaID);
    }

    public long getClientID() {
        return this.mClientID;
    }

    public String getMediaID() {
        return this.mMediaID;
    }

    public long getMessageID() {
        return this.mMessageID;
    }

    public int hashCode() {
        return Objects.hashCode(this.mMediaID, Long.valueOf(this.mClientID), Long.valueOf(this.mMessageID));
    }

    public void setClientID(long j) {
        this.mClientID = j;
    }

    public void setMediaID(String str) {
        this.mMediaID = str;
    }

    public void setMessageID(long j) {
        this.mMessageID = j;
    }

    public String toString() {
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("ClientMessageIdentifier { mediaId : '");
        outline1.append(this.mMediaID);
        outline1.append("', clientId: ");
        outline1.append(this.mClientID);
        outline1.append(", messageId: ");
        outline1.append(this.mMessageID);
        outline1.append(JsonReaderKt.END_OBJ);
        return outline1.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mMediaID);
        parcel.writeLong(this.mClientID);
        parcel.writeLong(this.mMessageID);
    }

    protected ClientMessageIdentifier(Parcel parcel) {
        this.mMediaID = parcel.readString();
        this.mClientID = parcel.readLong();
        this.mMessageID = parcel.readLong();
    }
}
