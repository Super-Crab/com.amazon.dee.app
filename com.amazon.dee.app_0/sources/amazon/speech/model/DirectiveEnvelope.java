package amazon.speech.model;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public class DirectiveEnvelope implements Parcelable {
    public static final Parcelable.Creator<DirectiveEnvelope> CREATOR = new Parcelable.Creator<DirectiveEnvelope>() { // from class: amazon.speech.model.DirectiveEnvelope.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public DirectiveEnvelope mo25createFromParcel(Parcel parcel) {
            return new DirectiveEnvelope(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public DirectiveEnvelope[] mo26newArray(int i) {
            return new DirectiveEnvelope[i];
        }
    };
    private final String mDirectiveId;
    private final String mFullJson;
    private final boolean mIsBlocking;
    private final String mKeys;
    private final String mName;
    private final String mNamespace;
    private final String mPayload;
    private final String mPayloadVersion;
    private final String mSequenceId;

    public DirectiveEnvelope(String str, String str2, String str3, String str4, String str5, String str6) {
        this(str, str2, str3, str4, str5, null, null, str6, false);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getDirectiveId() {
        return this.mDirectiveId;
    }

    public String getFullJson() {
        return this.mFullJson;
    }

    public String getKeys() {
        return this.mKeys;
    }

    public String getName() {
        return this.mName;
    }

    public String getNamespace() {
        return this.mNamespace;
    }

    public String getPayload() {
        return this.mPayload;
    }

    public String getPayloadVersion() {
        return this.mPayloadVersion;
    }

    public String getSequenceId() {
        return this.mSequenceId;
    }

    public boolean isBlocking() {
        return this.mIsBlocking;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mNamespace);
        parcel.writeString(this.mName);
        parcel.writeString(this.mPayload);
        parcel.writeString(this.mKeys);
        parcel.writeString(this.mPayloadVersion);
        parcel.writeString(this.mDirectiveId);
        parcel.writeString(this.mSequenceId);
        parcel.writeString(this.mFullJson);
        parcel.writeByte(this.mIsBlocking ? (byte) 1 : (byte) 0);
    }

    public DirectiveEnvelope(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean z) {
        if (str != null) {
            if (str2 == null) {
                throw new IllegalArgumentException("name cannot be null");
            }
            if (str3 != null) {
                this.mNamespace = str;
                this.mName = str2;
                this.mPayload = str3;
                this.mKeys = str4;
                this.mPayloadVersion = str5;
                this.mDirectiveId = str6;
                this.mSequenceId = str7;
                this.mFullJson = str8;
                this.mIsBlocking = z;
                return;
            }
            throw new IllegalArgumentException("payload cannot be null");
        }
        throw new IllegalArgumentException("namespace cannot be null");
    }

    public DirectiveEnvelope(Parcel parcel) {
        this(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readByte() != 0);
    }
}
