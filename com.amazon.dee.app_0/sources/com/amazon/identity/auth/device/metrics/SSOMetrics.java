package com.amazon.identity.auth.device.metrics;

import android.content.Context;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.RegistrationType;
import com.amazon.identity.auth.device.mp;
import com.amazon.identity.auth.device.mq;
import com.amazon.identity.auth.device.mu;
import com.amazon.identity.auth.device.mv;
import com.amazonaws.cognito.clientcontext.datacollection.DataRecordKey;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class SSOMetrics {
    public static final String TAG = "com.amazon.identity.auth.device.metrics.SSOMetrics";
    private static Context mO;
    private static mu mP;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public enum AccountManagerFailure {
        BAD_ARGUMENTS(7, "BadArguments"),
        BAD_REQUEST(8, "BadRequest"),
        CANCELED(4, "Cancelled"),
        INVALID_RESPONSE(5, "InvalidResponse"),
        NETWORK_ERROR(3, "NetworkError"),
        REMOTE_EXCEPTION(1, "RemoteException"),
        UNSUPPORTED_OPERATION(6, "UnsupportedOperation"),
        UNRECOGNIZED(9, "UnrecognizedExternalError");
        
        private final int mErrorCode;
        private final String mName;

        AccountManagerFailure(int i, String str) {
            this.mErrorCode = i;
            this.mName = str;
        }

        public static AccountManagerFailure getFromAccountManagerErrorCode(int i) {
            AccountManagerFailure[] values;
            for (AccountManagerFailure accountManagerFailure : values()) {
                if (accountManagerFailure.getAccountManagerErrorCode() == i) {
                    return accountManagerFailure;
                }
            }
            return UNRECOGNIZED;
        }

        public int getAccountManagerErrorCode() {
            return this.mErrorCode;
        }

        public String getName() {
            return this.mName;
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public enum AccountProperties {
        DeviceName(DataRecordKey.MODEL),
        DeviceEmail("DeviceEmail"),
        General("General");
        
        private final String mName;

        AccountProperties(String str) {
            this.mName = str;
        }

        public String getName() {
            return this.mName;
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public enum CredentialActionFailureReason {
        RequestTimeOut("RequestTimeOut"),
        DidnotReturnAuthToken("DidnotReturnAuthToken"),
        UnrecognizedFailure("UnrecognizedFailure");
        
        private final String mName;

        CredentialActionFailureReason(String str) {
            this.mName = str;
        }

        public String getName() {
            return this.mName;
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public enum SetAccountPropertiesFailureReason {
        NoAmazonAccount("NoAmazonAccount"),
        InvalidRequest("InvalidRequest"),
        UnrecognizedFailure("UnrecognizedFailure");
        
        private final String mName;

        SetAccountPropertiesFailureReason(String str) {
            this.mName = str;
        }

        public String getName() {
            return this.mName;
        }
    }

    private SSOMetrics() {
    }

    public static void P(Context context) {
        mO = context.getApplicationContext();
    }

    public static void a(RegistrationType registrationType) {
        ey();
        c("RegistrationFailure", registrationType.getName() + MAPAccountManager.RegistrationError.BAD_SECRET.getName());
    }

    public static void bQ(String str) {
        ey();
        c("DeregistrationFailure", str);
    }

    public static mv bR(String str) {
        ey();
        return mv.a(mP, "DeregistrationSubAuthTime", str);
    }

    public static void c(String str, int i) {
        ey();
        c("WebViewLoadFailure", "Path:" + mp.eR(str), Integer.toString(i));
    }

    public static void d(String str, int i) {
        ey();
        c("WebViewLoadFailure", "SSLError:" + mp.eR(str), "SSLError:".concat(String.valueOf(i)));
    }

    public static void eA() {
        ey();
        c("RenameDeviceRequestSuccess", new String[0]);
    }

    private static synchronized void ey() {
        synchronized (SSOMetrics.class) {
            if (mO != null && mP == null) {
                mP = mq.aO(mO);
            }
        }
    }

    public static mv ez() {
        ey();
        return mv.a(mP, "DeregistrationTime", "TotalDeregistrationTime");
    }

    public static void m(int i) {
        String str;
        ey();
        String[] strArr = new String[1];
        switch (i) {
            case 1:
                str = "NetworkFailure";
                break;
            case 2:
                str = "AuthenticationFailed";
                break;
            case 3:
                str = "InvalidInput";
                break;
            case 4:
                str = "ParseError";
                break;
            case 5:
                str = "NameAlreadyUsed";
                break;
            case 6:
                str = "NoAmazonAccount";
                break;
            default:
                str = "Unrecognized";
                break;
        }
        strArr[0] = str;
        c("RenameDeviceRequestFailure", strArr);
    }

    public static void a(MAPAccountManager.RegistrationError registrationError) {
        String name;
        ey();
        String[] strArr = new String[1];
        if (registrationError != null) {
            name = registrationError.getName();
        } else {
            name = MAPAccountManager.RegistrationError.UNRECOGNIZED.getName();
        }
        strArr[0] = name;
        c("DeregistrationFailure", strArr);
    }

    private static void c(String str, String... strArr) {
        mu muVar = mP;
        if (muVar == null) {
            return;
        }
        muVar.a("GenericMetrics_SSO", str, strArr);
    }
}
