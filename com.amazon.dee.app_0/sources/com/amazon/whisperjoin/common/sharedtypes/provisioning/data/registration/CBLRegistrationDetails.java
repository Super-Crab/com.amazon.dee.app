package com.amazon.whisperjoin.common.sharedtypes.provisioning.data.registration;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class CBLRegistrationDetails implements Parcelable {
    public static final Parcelable.ClassLoaderCreator<CBLRegistrationDetails> CREATOR = new Parcelable.ClassLoaderCreator<CBLRegistrationDetails>() { // from class: com.amazon.whisperjoin.common.sharedtypes.provisioning.data.registration.CBLRegistrationDetails.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public CBLRegistrationDetails[] mo5447newArray(int i) {
            if (i >= 0) {
                return new CBLRegistrationDetails[i];
            }
            throw new IllegalArgumentException("size cannot be negative.");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.ClassLoaderCreator
        /* renamed from: createFromParcel */
        public CBLRegistrationDetails mo5446createFromParcel(Parcel parcel, ClassLoader classLoader) {
            if (parcel != null) {
                return new CBLRegistrationDetails(parcel, classLoader);
            }
            throw new IllegalArgumentException("source cannot be null.");
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public CBLRegistrationDetails mo5445createFromParcel(Parcel parcel) {
            if (parcel != null) {
                return new CBLRegistrationDetails(parcel, CBLRegistrationDetails.class.getClassLoader());
            }
            throw new IllegalArgumentException("source cannot be null.");
        }
    };
    private static final String HTTP_CODE_KEY = "http-code";
    private static final String MESSAGE_KEY = "message";
    private static final String STATE_KEY = "state";
    private final Integer mHttpCode;
    private final String mMessage;
    private final State mState;

    /* loaded from: classes13.dex */
    public enum State implements Parcelable {
        NOT_REGISTERED(0, "Not Registered"),
        COMPLETING_REGISTRATION(1, "Completing Registration"),
        REGISTRATION_COMPLETE(2, "Registered"),
        REGISTRATION_FAILED_TOKEN_INVALID(-1, "Registration Failed, Token Invalid"),
        REGISTRATION_FAILED_TOKEN_EXPIRED(-2, "Registration Failed, Token Expired"),
        REGISTRATION_FAILED_SERVER_NOT_REACHABLE(-3, "Registration Failed, Server Not Reachable"),
        REGISTRATION_FAILED_SERVER_ERROR(-4, "Registration Failed, Server Error"),
        REGISTRATION_FAILED_OTHER(-5, "Registration Failed, Other");
        
        public static final Parcelable.ClassLoaderCreator<State> CREATOR = new Parcelable.ClassLoaderCreator<State>() { // from class: com.amazon.whisperjoin.common.sharedtypes.provisioning.data.registration.CBLRegistrationDetails.State.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: newArray */
            public State[] mo5450newArray(int i) {
                if (i >= 0) {
                    return new State[i];
                }
                throw new IllegalArgumentException("size cannot be negative.");
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.ClassLoaderCreator
            /* renamed from: createFromParcel */
            public State mo5449createFromParcel(Parcel parcel, ClassLoader classLoader) {
                if (parcel != null) {
                    return State.fromInt(parcel.readInt());
                }
                throw new IllegalArgumentException("source cannot be null.");
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: createFromParcel */
            public State mo5448createFromParcel(Parcel parcel) {
                if (parcel != null) {
                    return State.fromInt(parcel.readInt());
                }
                throw new IllegalArgumentException("source cannot be null.");
            }
        };
        private String mDescription;
        private final int mValue;

        State(int i, String str) {
            this.mValue = i;
            this.mDescription = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static State fromInt(int i) {
            switch (i) {
                case -5:
                    return REGISTRATION_FAILED_OTHER;
                case -4:
                    return REGISTRATION_FAILED_SERVER_ERROR;
                case -3:
                    return REGISTRATION_FAILED_SERVER_NOT_REACHABLE;
                case -2:
                    return REGISTRATION_FAILED_TOKEN_EXPIRED;
                case -1:
                    return REGISTRATION_FAILED_TOKEN_INVALID;
                case 0:
                    return NOT_REGISTERED;
                case 1:
                    return COMPLETING_REGISTRATION;
                case 2:
                    return REGISTRATION_COMPLETE;
                default:
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Unknown CBLRegistrationDetails.State: ", i));
            }
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public String getDescription() {
            return this.mDescription;
        }

        public int getValue() {
            return this.mValue;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.mDescription;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            if (parcel != null) {
                parcel.writeInt(this.mValue);
                return;
            }
            throw new IllegalArgumentException("dest cannot be null.");
        }
    }

    public CBLRegistrationDetails(State state) {
        this(state, null, null);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Integer getHttpCode() {
        return this.mHttpCode;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public State getRegistrationState() {
        return this.mState;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CBLRegistrationDetails [state=");
        outline107.append(this.mState);
        outline107.append(", message=");
        outline107.append(this.mMessage);
        outline107.append(", http-code=");
        outline107.append(this.mHttpCode);
        outline107.append("]");
        return outline107.toString();
    }

    protected void validate() {
        Integer num = this.mHttpCode;
        if (num == null || num.intValue() >= 0) {
            return;
        }
        throw new IllegalArgumentException("Http code cannot be negative. Please use InputValidator methods to validate parameters.");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel != null) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("state", this.mState);
            bundle.putString("message", this.mMessage);
            Integer num = this.mHttpCode;
            if (num != null) {
                bundle.putInt(HTTP_CODE_KEY, num.intValue());
            }
            parcel.writeBundle(bundle);
            return;
        }
        throw new IllegalArgumentException("dest cannot be null.");
    }

    public CBLRegistrationDetails(State state, String str, Integer num) {
        this.mState = state;
        this.mMessage = str;
        this.mHttpCode = num;
        validate();
    }

    protected CBLRegistrationDetails(Parcel parcel, ClassLoader classLoader) {
        Bundle readBundle = parcel.readBundle(classLoader);
        this.mState = (State) readBundle.getParcelable("state");
        this.mMessage = readBundle.getString("message");
        this.mHttpCode = readBundle.containsKey(HTTP_CODE_KEY) ? Integer.valueOf(readBundle.getInt(HTTP_CODE_KEY)) : null;
        validate();
    }
}
