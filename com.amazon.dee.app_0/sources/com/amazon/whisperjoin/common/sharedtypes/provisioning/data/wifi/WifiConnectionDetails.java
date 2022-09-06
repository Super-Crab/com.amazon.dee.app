package com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.mode.metrics.DriveModeMetrics;
import com.amazon.whisperjoin.common.sharedtypes.utility.InputValidator;
import com.amazon.whisperjoin.protobuf.ErrorCodes;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class WifiConnectionDetails implements Parcelable {
    public static final Parcelable.ClassLoaderCreator<WifiConnectionDetails> CREATOR = new Parcelable.ClassLoaderCreator<WifiConnectionDetails>() { // from class: com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConnectionDetails.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public WifiConnectionDetails[] mo5459newArray(int i) {
            if (i >= 0) {
                return new WifiConnectionDetails[i];
            }
            throw new IllegalArgumentException("size cannot be negative.");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.ClassLoaderCreator
        /* renamed from: createFromParcel */
        public WifiConnectionDetails mo5458createFromParcel(Parcel parcel, ClassLoader classLoader) {
            if (parcel != null) {
                return new WifiConnectionDetails(parcel, classLoader);
            }
            throw new IllegalArgumentException("source cannot be null.");
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public WifiConnectionDetails mo5457createFromParcel(Parcel parcel) {
            if (parcel != null) {
                return new WifiConnectionDetails(parcel, WifiConnectionDetails.class.getClassLoader());
            }
            throw new IllegalArgumentException("source cannot be null.");
        }
    };
    private static final String KEY_MANAGEMENT_KEY = "key-mgmt";
    private static final String SSID_KEY = "ssid";
    private static final String STATE_KEY = "state";
    private String mSsid;
    private State mState;
    private WifiKeyManagement mWifiKeyManagement;

    /* renamed from: com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConnectionDetails$2  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$data$wifi$WifiConnectionDetails$State = new int[State.values().length];

        static {
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$data$wifi$WifiConnectionDetails$State[State.CONNECTION_FAILED_INTERNAL_ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$data$wifi$WifiConnectionDetails$State[State.CONNECTED_BEHIND_CAPTIVE_PORTAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$data$wifi$WifiConnectionDetails$State[State.CONNECTED_LIMITED_CONNECTIVITY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$data$wifi$WifiConnectionDetails$State[State.CONNECTION_FAILED_PSK_AUTHENTICATION.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$data$wifi$WifiConnectionDetails$State[State.CONNECTION_FAILED_AP_NOT_FOUND.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* loaded from: classes13.dex */
    public enum State implements Parcelable {
        INITIATING_CONNECTION_ATTEMPT(0, "Initiating connection attempt"),
        DISCONNECTED(1, DriveModeMetrics.ConnectionStatus.DISCONNECTED),
        CONNECTING(2, "Connecting"),
        ASSOCIATED(3, "Associated with AP"),
        CONNECTED(4, DriveModeMetrics.ConnectionStatus.CONNECTED),
        CONNECTION_FAILED_INTERNAL_ERROR(-1, "Connection failed due to internal error"),
        CONNECTED_BEHIND_CAPTIVE_PORTAL(-2, "Connected, but behind captive portal"),
        CONNECTED_LIMITED_CONNECTIVITY(-3, "Connected, but with limited connectivity"),
        CONNECTION_FAILED_PSK_AUTHENTICATION(-4, "Connection failed due to PSK authentation error"),
        CONNECTION_FAILED_AP_NOT_FOUND(-5, "Connection failed due to Access Point Not Found");
        
        public static final Parcelable.ClassLoaderCreator<State> CREATOR = new Parcelable.ClassLoaderCreator<State>() { // from class: com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConnectionDetails.State.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: newArray */
            public State[] mo5462newArray(int i) {
                if (i >= 0) {
                    return new State[i];
                }
                throw new IllegalArgumentException("size cannot be negative.");
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.ClassLoaderCreator
            /* renamed from: createFromParcel */
            public State mo5461createFromParcel(Parcel parcel, ClassLoader classLoader) {
                if (parcel != null) {
                    return State.fromInt(parcel.readInt());
                }
                throw new IllegalArgumentException("source cannot be null.");
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: createFromParcel */
            public State mo5460createFromParcel(Parcel parcel) {
                if (parcel != null) {
                    return State.fromInt(parcel.readInt());
                }
                throw new IllegalArgumentException("source cannot be null.");
            }
        };
        private final String mDescription;
        private final int mValue;

        State(int i, String str) {
            this.mValue = i;
            this.mDescription = str;
        }

        static State fromInt(int i) {
            switch (i) {
                case -5:
                    return CONNECTION_FAILED_AP_NOT_FOUND;
                case -4:
                    return CONNECTION_FAILED_PSK_AUTHENTICATION;
                case -3:
                    return CONNECTED_LIMITED_CONNECTIVITY;
                case -2:
                    return CONNECTED_BEHIND_CAPTIVE_PORTAL;
                case -1:
                    return CONNECTION_FAILED_INTERNAL_ERROR;
                case 0:
                    return INITIATING_CONNECTION_ATTEMPT;
                case 1:
                    return DISCONNECTED;
                case 2:
                    return CONNECTING;
                case 3:
                    return ASSOCIATED;
                case 4:
                    return CONNECTED;
                default:
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Unknown WifiConnectionDetails.State: ", i));
            }
        }

        public static ErrorCodes.ProvisioningErrorType getProvisioningError(State state) {
            switch (state.ordinal()) {
                case 5:
                    return ErrorCodes.ProvisioningErrorType.WIFI_CONN_ERROR_INTERNAL_ERROR;
                case 6:
                    return ErrorCodes.ProvisioningErrorType.WIFI_CONN_ERROR_CAPTIVE_PORTAL;
                case 7:
                    return ErrorCodes.ProvisioningErrorType.WIFI_CONN_ERROR_LIMITED_CONNECTIVITY;
                case 8:
                    return ErrorCodes.ProvisioningErrorType.WIFI_CONN_ERROR_BAD_PSK;
                case 9:
                    return ErrorCodes.ProvisioningErrorType.WIFI_CONN_ERROR_AP_NOT_FOUND;
                default:
                    throw new IllegalArgumentException("Unknown WifiConnectionDetails.State: " + state);
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

    public WifiConnectionDetails(String str, WifiKeyManagement wifiKeyManagement, State state) {
        if (str != null && (!str.startsWith(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED) || !str.endsWith(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED))) {
            throw new IllegalArgumentException("SSID is not quoted. Please use com.amazon.whisperjoin.utils.InputValidator to validate wifi parameters.");
        }
        this.mSsid = str == null ? null : GeneratedOutlineSupport1.outline51(str, 1, 1);
        this.mWifiKeyManagement = wifiKeyManagement;
        this.mState = state;
        validate();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof WifiConnectionDetails)) {
            if (obj == this) {
                return true;
            }
            WifiConnectionDetails wifiConnectionDetails = (WifiConnectionDetails) obj;
            String str = this.mSsid;
            return (str == null ? wifiConnectionDetails.mSsid == null : str.equals(wifiConnectionDetails.mSsid)) && this.mWifiKeyManagement == wifiConnectionDetails.mWifiKeyManagement && this.mState == wifiConnectionDetails.mState;
        }
        return false;
    }

    public State getConnectionState() {
        return this.mState;
    }

    public WifiKeyManagement getKeyManagement() {
        return this.mWifiKeyManagement;
    }

    public String getSsid() {
        return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED), this.mSsid, EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
    }

    public int hashCode() {
        String str = this.mSsid;
        int hashCode = str == null ? 0 : str.hashCode();
        return this.mState.ordinal() + ((this.mWifiKeyManagement.ordinal() + ((hashCode + 41) * 41)) * 41);
    }

    public void setConnectionState(State state) {
        this.mState = state;
        validate();
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("WifiConnectionDetails [state=");
        outline107.append(this.mState);
        outline107.append(", ssid=--REDACTED--, key-mgmt=");
        outline107.append(this.mWifiKeyManagement);
        outline107.append("]");
        return outline107.toString();
    }

    protected void validate() {
        if (this.mSsid != null && !InputValidator.isValidSsid(getSsid())) {
            throw new IllegalArgumentException("Invalid mSsid in wifi connection details. Please use com.amazon.whisperjoin.common.sharedtypes.utility.InputValidator to validate wifi parameters.");
        }
        WifiKeyManagement wifiKeyManagement = this.mWifiKeyManagement;
        if (wifiKeyManagement != null && !InputValidator.isValidWifiKeyManagement(wifiKeyManagement)) {
            throw new IllegalArgumentException("Invalid key management in wifi connection details. Please use com.amazon.whisperjoin.common.sharedtypes.utility.InputValidator to validate wifi parameters.");
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel != null) {
            Bundle bundle = new Bundle();
            bundle.putString("ssid", this.mSsid);
            bundle.putParcelable(KEY_MANAGEMENT_KEY, this.mWifiKeyManagement);
            bundle.putParcelable("state", this.mState);
            parcel.writeBundle(bundle);
            return;
        }
        throw new IllegalArgumentException("dest cannot be null.");
    }

    protected WifiConnectionDetails(Parcel parcel, ClassLoader classLoader) {
        Bundle readBundle = parcel.readBundle(classLoader);
        this.mSsid = readBundle.getString("ssid");
        this.mWifiKeyManagement = (WifiKeyManagement) readBundle.getParcelable(KEY_MANAGEMENT_KEY);
        this.mState = (State) readBundle.getParcelable("state");
        validate();
    }
}
