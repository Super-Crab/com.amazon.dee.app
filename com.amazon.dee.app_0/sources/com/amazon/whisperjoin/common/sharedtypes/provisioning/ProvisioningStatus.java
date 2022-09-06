package com.amazon.whisperjoin.common.sharedtypes.provisioning;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public final class ProvisioningStatus implements Parcelable {
    public static final Parcelable.ClassLoaderCreator CREATOR = new Parcelable.ClassLoaderCreator() { // from class: com.amazon.whisperjoin.common.sharedtypes.provisioning.ProvisioningStatus.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public ProvisioningStatus[] mo5430newArray(int i) {
            if (i >= 0) {
                return new ProvisioningStatus[i];
            }
            throw new IllegalArgumentException("size cannot be negative.");
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public ProvisioningStatus mo5428createFromParcel(Parcel parcel) {
            if (parcel != null) {
                return new ProvisioningStatus(parcel, ProvisioningStatus.class.getClassLoader());
            }
            throw new IllegalArgumentException("source cannot be null.");
        }

        @Override // android.os.Parcelable.ClassLoaderCreator
        /* renamed from: createFromParcel */
        public ProvisioningStatus mo5429createFromParcel(Parcel parcel, ClassLoader classLoader) {
            if (parcel != null) {
                return new ProvisioningStatus(parcel, classLoader);
            }
            throw new IllegalArgumentException("source cannot be null.");
        }
    };
    private final boolean mInsecureModeSupported;
    private State mState;

    /* loaded from: classes13.dex */
    public enum State implements Parcelable {
        PROVISIONING_SESSION_IDLE(0, "Provisioning Session Idle"),
        WAITING_FOR_PROVISIONER(1, "Wait for Provisioner"),
        AUTHORIZING_PROVISIONER(2, "Authorizing Provisioner"),
        ACTIVELY_PROVISIONING(3, "Actively Provisioning"),
        PROVISIONING_COMPLETE(4, "Provisioning Complete"),
        PROVISIONING_TERMINATED(5, "Provisioning Terminated"),
        CONNECTED_TO_PROVISIONER(6, "Connected to Provisioner"),
        ADVERTISEMENT_TIMEOUT(7, "Advertisement Timeout"),
        PROVISIONING_TIMEOUT(8, "Provisioning Timeout");
        
        public static final Parcelable.Creator<State> CREATOR = new Parcelable.Creator<State>() { // from class: com.amazon.whisperjoin.common.sharedtypes.provisioning.ProvisioningStatus.State.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            /* renamed from: createFromParcel */
            public State mo5431createFromParcel(Parcel parcel) {
                return State.fromInt(parcel.readInt());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            /* renamed from: newArray */
            public State[] mo5432newArray(int i) {
                return new State[i];
            }
        };
        private final String mString;
        private final int mValue;

        State(int i, String str) {
            this.mValue = i;
            this.mString = str;
        }

        public static State fromInt(int i) {
            switch (i) {
                case 0:
                    return PROVISIONING_SESSION_IDLE;
                case 1:
                    return WAITING_FOR_PROVISIONER;
                case 2:
                    return AUTHORIZING_PROVISIONER;
                case 3:
                    return ACTIVELY_PROVISIONING;
                case 4:
                    return PROVISIONING_COMPLETE;
                case 5:
                    return PROVISIONING_TERMINATED;
                case 6:
                    return CONNECTED_TO_PROVISIONER;
                case 7:
                    return ADVERTISEMENT_TIMEOUT;
                case 8:
                    return PROVISIONING_TIMEOUT;
                default:
                    throw new IllegalArgumentException("Unknown State encountered.");
            }
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public String getString() {
            return this.mString;
        }

        public int getValue() {
            return this.mValue;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.mString;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mValue);
        }
    }

    public ProvisioningStatus(State state, boolean z) {
        this.mState = state;
        this.mInsecureModeSupported = z;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public State getProvisioningState() {
        return this.mState;
    }

    public boolean insecureModeSupported() {
        return this.mInsecureModeSupported;
    }

    public void setProvisioningState(State state) {
        this.mState = state;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ProvisioningStatus [state=");
        outline107.append(this.mState);
        outline107.append(", insecure-mode-supported=");
        return GeneratedOutlineSupport1.outline97(outline107, this.mInsecureModeSupported, "]");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel != null) {
            parcel.writeParcelable(this.mState, i);
            parcel.writeByte(this.mInsecureModeSupported ? (byte) 1 : (byte) 0);
            return;
        }
        throw new IllegalArgumentException("dest cannot be null.");
    }

    public ProvisioningStatus(Parcel parcel, ClassLoader classLoader) {
        this.mState = (State) parcel.readParcelable(classLoader);
        this.mInsecureModeSupported = parcel.readByte() != 1 ? false : true;
    }
}
