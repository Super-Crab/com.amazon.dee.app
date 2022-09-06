package com.amazon.identity.auth.device.framework;

import android.text.TextUtils;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.endpoint.AbstractJSONTokenResponse;
import com.amazon.identity.auth.device.ik;
import com.amazon.identity.auth.device.io;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class AuthEndpointErrorParser {
    public static final a iS = new a(AuthErrorType.GenericError, "Generic Error", "Generic Error", "No Error Index", "No Request Id");
    private static final String TAG = AuthEndpointErrorParser.class.getName();

    /* JADX WARN: Init of enum AuthenticationChallenged can be incorrect */
    /* JADX WARN: Init of enum DeviceAlreadyRegistered can be incorrect */
    /* JADX WARN: Init of enum DuplicateDeviceName can be incorrect */
    /* JADX WARN: Init of enum NetworkFailure can be incorrect */
    /* JADX WARN: Init of enum NotImplemented can be incorrect */
    /* JADX WARN: Init of enum ParseError can be incorrect */
    /* JADX WARN: Init of enum ServiceUnavailable can be incorrect */
    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public enum AuthErrorType {
        MissingValue("MissingValue", false, MAPAccountManager.RegistrationError.BAD_REQUEST, MAPError.CommonError.BAD_REQUEST, "Missing value"),
        InvalidValue("InvalidValue", false, MAPAccountManager.RegistrationError.BAD_REQUEST, MAPError.CommonError.BAD_REQUEST, "Invalid value"),
        InvalidToken("InvalidToken", false, MAPAccountManager.RegistrationError.BAD_REQUEST, MAPError.CommonError.BAD_REQUEST, "Invalid token"),
        ProtocolError("ProtocolError", false, MAPAccountManager.RegistrationError.BAD_REQUEST, MAPError.AccountError.UNSUPPORTED_PROTOCOL, "Protocol error"),
        CredentialError("CredentialError", false, MAPAccountManager.RegistrationError.CUSTOMER_NOT_FOUND, MAPError.AccountError.CUSTOMER_NOT_FOUND, "Credential error"),
        Forbidden("Forbidden", false, MAPAccountManager.RegistrationError.BAD_REQUEST, MAPError.CommonError.BAD_REQUEST, "Forbidden"),
        MethodNotAllowed("MethodNotAllowed", false, MAPAccountManager.RegistrationError.BAD_REQUEST, MAPError.CommonError.UNSUPPORTED_OPERATION, "Method not allowed"),
        ServerError("ServerError", true, MAPAccountManager.RegistrationError.UNRECOGNIZED, MAPError.CommonError.SERVER_ERROR, "Server error"),
        ServiceUnavailable("ServiceUnavailable", true, r6, r7, r7.getErrorMessage()),
        NotImplemented("NotImplemented", false, r14, r15, r15.getErrorMessage()),
        InvalidDirectedId("InvalidDirectedId", false, MAPAccountManager.RegistrationError.BAD_REQUEST, MAPError.CommonError.BAD_REQUEST, "Invalid directedId"),
        DeviceAlreadyRegistered("DeviceAlreadyRegistered", false, r14, r15, r15.getErrorMessage()),
        DuplicateDeviceName("DuplicateDeviceName", false, r6, r7, r7.getErrorMessage()),
        GenericError,
        AuthenticationChallenged("AuthenticationChallenged", false, r8, r9, r9.getErrorMessage()),
        ParseError("ParseError", true, r16, r17, r17.getErrorMessage()),
        BuildRequestFailure("BuildRequestFailure", false, MAPAccountManager.RegistrationError.BAD_REQUEST, MAPError.CommonError.BAD_REQUEST, "Build request failure"),
        NetworkFailure("NetworkFailure", true, r15, r16, r16.getErrorMessage()),
        ActorNotAssociated("ActorNotAssociated", false, MAPAccountManager.RegistrationError.ACTOR_NOT_ASSOCIATED, MAPError.CommonError.BAD_REQUEST, "Actor is not associated with account"),
        InvalidActorToken("InvalidActorToken", true, MAPAccountManager.RegistrationError.INTERNAL_ERROR, MAPError.CommonError.INTERNAL_ERROR, "Actor token is invalid"),
        InvalidRequest("InvalidRequest", false, MAPAccountManager.RegistrationError.BAD_REQUEST, MAPError.CommonError.BAD_REQUEST, "The input is invalid");
        
        private final String mCode;
        private final MAPError mError;
        private final String mErrorMessage;
        private final MAPAccountManager.RegistrationError mLegacyError;
        private final boolean mRetry;

        static {
            MAPAccountManager.RegistrationError registrationError = MAPAccountManager.RegistrationError.UNRECOGNIZED;
            MAPError.CommonError commonError = MAPError.CommonError.SERVICE_UNAVAILABLE;
            MAPAccountManager.RegistrationError registrationError2 = MAPAccountManager.RegistrationError.BAD_REQUEST;
            MAPError.CommonError commonError2 = MAPError.CommonError.FEATURE_NOT_IMPLEMENTED;
            MAPAccountManager.RegistrationError registrationError3 = MAPAccountManager.RegistrationError.DEVICE_ALREADY_REGISTERED;
            MAPError.AccountError accountError = MAPError.AccountError.DEVICE_ALREADY_REGISTERED;
            MAPAccountManager.RegistrationError registrationError4 = MAPAccountManager.RegistrationError.DUPLICATE_DEVICE_NAME;
            MAPError.AccountError accountError2 = MAPError.AccountError.DUPLICATE_DEVICE_NAME;
            MAPAccountManager.RegistrationError registrationError5 = MAPAccountManager.RegistrationError.AUTHENTICATION_CHALLENGED;
            MAPError.AccountError accountError3 = MAPError.AccountError.AUTHENTICATION_CHALLENGED;
            MAPAccountManager.RegistrationError registrationError6 = MAPAccountManager.RegistrationError.PARSE_ERROR;
            MAPError.CommonError commonError3 = MAPError.CommonError.PARSE_ERROR;
            MAPAccountManager.RegistrationError registrationError7 = MAPAccountManager.RegistrationError.NETWORK_FAILURE;
            MAPError.CommonError commonError4 = MAPError.CommonError.NETWORK_ERROR;
        }

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        AuthErrorType() {
            /*
                r8 = this;
                com.amazon.identity.auth.device.api.MAPAccountManager$RegistrationError r5 = com.amazon.identity.auth.device.api.MAPAccountManager.RegistrationError.UNRECOGNIZED
                com.amazon.identity.auth.device.api.MAPError$CommonError r6 = com.amazon.identity.auth.device.api.MAPError.CommonError.INTERNAL_ERROR
                java.lang.String r7 = r6.getErrorMessage()
                java.lang.String r3 = "GenericError"
                r4 = 0
                r0 = r8
                r1 = r9
                r2 = r10
                r0.<init>(r3, r4, r5, r6, r7)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.framework.AuthEndpointErrorParser.AuthErrorType.<init>(java.lang.String, int):void");
        }

        public static AuthErrorType getAuthErrorTypeFromCode(String str) {
            AuthErrorType[] values;
            for (AuthErrorType authErrorType : values()) {
                if (authErrorType.getCode().equals(str)) {
                    return authErrorType;
                }
            }
            return GenericError;
        }

        public String getCode() {
            return this.mCode;
        }

        public MAPError getError() {
            return this.mError;
        }

        public String getErrorMessage() {
            return this.mErrorMessage;
        }

        public MAPAccountManager.RegistrationError getRegistrationError() {
            return this.mLegacyError;
        }

        public boolean getRetry() {
            return this.mRetry;
        }

        AuthErrorType(String str, boolean z, MAPAccountManager.RegistrationError registrationError, MAPError mAPError, String str2) {
            this.mCode = str;
            this.mRetry = z;
            this.mLegacyError = registrationError;
            this.mError = mAPError;
            this.mErrorMessage = str2;
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class a {
        private final AuthErrorType iT;
        private final String iU;
        private final String iV;
        private final String iW;
        private final String iX;

        public a(AuthErrorType authErrorType, String str, String str2, String str3, String str4) {
            this.iT = authErrorType;
            this.iU = str;
            this.iV = str2;
            this.iW = str3;
            this.iX = str4;
        }

        public AuthErrorType cG() {
            return this.iT;
        }

        public String cH() {
            return this.iV;
        }

        public String cI() {
            return this.iW;
        }

        public String getMessage() {
            return this.iU;
        }

        public MAPAccountManager.RegistrationError getRegistrationError() {
            return this.iT.getRegistrationError();
        }
    }

    public static boolean a(Integer num) {
        return num == null || num.intValue() < 200 || num.intValue() >= 300;
    }

    private a f(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        JSONObject jSONObject2 = jSONObject.getJSONObject("response");
        String a2 = ik.a(jSONObject2, AbstractJSONTokenResponse.REQUEST_ID, null);
        JSONObject jSONObject3 = jSONObject2.getJSONObject("error");
        AuthErrorType authErrorTypeFromCode = AuthErrorType.getAuthErrorTypeFromCode(jSONObject3.getString("code"));
        String a3 = ik.a(jSONObject3, "message", null);
        String a4 = ik.a(jSONObject3, MessagingControllerConstant.MESSAGING_CONTROLLER_DETAIL_KEY, null);
        String a5 = ik.a(jSONObject3, "index", null);
        if (!TextUtils.isEmpty(a5)) {
            io.e(TAG, "Error Index was received: ".concat(String.valueOf(a5)));
            io.dm(TAG);
        }
        return new a(authErrorTypeFromCode, a3, a4, a5, a2);
    }

    public a g(JSONObject jSONObject) {
        try {
            return f(jSONObject);
        } catch (JSONException e) {
            String str = "Given JSON is not in Auth Error format. Error: " + e.getMessage();
            io.dm(TAG);
            return new a(AuthErrorType.ParseError, str, null, null, null);
        }
    }

    public String h(JSONObject jSONObject) {
        try {
            a f = f(jSONObject);
            if (f == null) {
                return null;
            }
            return f.cG().getCode();
        } catch (JSONException unused) {
            return null;
        }
    }

    public boolean i(JSONObject jSONObject) {
        if (jSONObject == null) {
            return false;
        }
        try {
            return jSONObject.getJSONObject("response").has("challenge");
        } catch (JSONException unused) {
            return false;
        }
    }
}
