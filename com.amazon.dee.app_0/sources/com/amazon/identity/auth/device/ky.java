package com.amazon.identity.auth.device;

import com.amazon.device.information.contract.DeviceInformationContract;
import com.amazon.identity.auth.device.endpoint.AbstractJSONTokenResponse;
import com.amazon.identity.kcpsdk.auth.PandaError;
import com.amazon.identity.kcpsdk.auth.RegisterDeviceErrorType;
import com.dee.app.data.reactnative.bridges.HttpClientModule;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class ky extends kz<lb> {
    private static final String TAG = "com.amazon.identity.auth.device.ky";
    private static final List<String> sG = new ArrayList(Arrays.asList("account_pool", "device_name"));
    private String a;
    private String bP;
    private String bk;
    private String mAccessToken;
    private String mName;
    private String ne;
    private String ot;
    private String sH;
    private int sI;
    private JSONArray sJ;
    private String sK;
    private String sL;
    private String sM;
    private String sN;
    private String sO;
    private String sP;
    private String sQ;
    private String sR;
    private String sS;
    private String sT;
    private String sU;
    private final Set<String> sV = new HashSet(sG);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DCP */
    /* renamed from: com.amazon.identity.auth.device.ky$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] sW = new int[PandaError.values().length];

        static {
            try {
                sW[PandaError.PandaErrorMissingValue.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                sW[PandaError.PandaErrorCredentialError.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                sW[PandaError.PandaErrorInvalidValue.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                sW[PandaError.PandaErrorServerError.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                sW[PandaError.PandaErrorServiceUnavailable.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                sW[PandaError.PandaErrorForbidden.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                sW[PandaError.PandaErrorUnknown.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public ky(List<String> list) {
        this.sV.addAll(list);
        this.sJ = new JSONArray();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.identity.auth.device.kz
    /* renamed from: a */
    public lb b(PandaError pandaError) {
        RegisterDeviceErrorType registerDeviceErrorType;
        switch (AnonymousClass1.sW[pandaError.ordinal()]) {
            case 1:
                registerDeviceErrorType = RegisterDeviceErrorType.RegisterDeviceErrorTypeMissingValue;
                break;
            case 2:
                registerDeviceErrorType = RegisterDeviceErrorType.RegisterDeviceErrorTypeCustomerNotFound;
                break;
            case 3:
                registerDeviceErrorType = RegisterDeviceErrorType.RegisterDeviceErrorTypeInvalidValue;
                break;
            case 4:
                registerDeviceErrorType = RegisterDeviceErrorType.RegisterDeviceErrorTypeServerError;
                break;
            case 5:
                registerDeviceErrorType = RegisterDeviceErrorType.RegisterDeviceErrorTypeServerUnavailable;
                break;
            case 6:
                registerDeviceErrorType = RegisterDeviceErrorType.RegisterDeviceErrorTypeForbidden;
                io.gE();
                break;
            default:
                registerDeviceErrorType = RegisterDeviceErrorType.RegisterDeviceErrorTypeUnrecognizedPanda;
                break;
        }
        return b(registerDeviceErrorType);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.identity.auth.device.kz
    /* renamed from: u */
    public lb x(JSONObject jSONObject) throws JSONException {
        if (jSONObject.has("tokens")) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("tokens");
            if (jSONObject2.has(HttpClientModule.ElementsRequestKey.BEARER)) {
                JSONObject jSONObject3 = jSONObject2.getJSONObject(HttpClientModule.ElementsRequestKey.BEARER);
                this.ne = jSONObject3.getString(AbstractJSONTokenResponse.REFRESH_TOKEN);
                this.mAccessToken = jSONObject3.getString(AbstractJSONTokenResponse.ACCESS_TOKEN);
                this.sI = je.dz(jSONObject3.getString("expires_in"));
            }
            if (jSONObject2.has("mac_dms")) {
                JSONObject jSONObject4 = jSONObject2.getJSONObject("mac_dms");
                this.a = jSONObject4.getString("adp_token");
                this.sH = jSONObject4.getString("device_private_key");
            }
            if (jSONObject2.has("store_authentication_cookie")) {
                this.sK = jSONObject2.getJSONObject("store_authentication_cookie").optString("cookie", null);
            }
            if (jSONObject2.has("website_cookies")) {
                this.sJ = jSONObject2.getJSONArray("website_cookies");
            }
        }
        if (jSONObject.has("extensions")) {
            lb b = b(RegisterDeviceErrorType.RegisterDeviceErrorTypeUnrecognizedPanda);
            JSONObject jSONObject5 = jSONObject.getJSONObject("extensions");
            if (jSONObject5.has("customer_info")) {
                JSONObject jSONObject6 = jSONObject5.getJSONObject("customer_info");
                if (jSONObject6.has("account_pool")) {
                    this.sL = jSONObject6.getString("account_pool");
                } else if (this.sV.contains("account_pool")) {
                    io.e(TAG, " PandaResponseJsonParser: account pool is missing");
                }
                if (jSONObject6.has("country_of_residence")) {
                    this.sM = jSONObject6.getString("country_of_residence");
                }
                if (jSONObject6.has("source_of_country_of_residence")) {
                    this.sN = jSONObject6.getString("source_of_country_of_residence");
                }
                if (jSONObject6.has("home_region")) {
                    this.sO = jSONObject6.getString("home_region");
                }
                if (jSONObject6.has("name")) {
                    this.mName = jSONObject6.getString("name");
                } else if (this.sV.contains("name")) {
                    io.e(TAG, " PandaResponseJsonParser: name is missing");
                }
                if (jSONObject6.has("preferred_marketplace")) {
                    this.sP = jSONObject6.getString("preferred_marketplace");
                }
                if (jSONObject6.has("user_id")) {
                    this.sQ = jSONObject6.getString("user_id");
                }
                if (jSONObject6.has("given_name")) {
                    this.sR = jSONObject6.getString("given_name");
                } else if (this.sV.contains("given_name")) {
                    io.e(TAG, " PandaResponseJsonParser: given name is missing");
                }
                if (jSONObject5.has(DeviceInformationContract.DeviceInfo.CONTENT_DIRECTORY)) {
                    JSONObject jSONObject7 = jSONObject5.getJSONObject(DeviceInformationContract.DeviceInfo.CONTENT_DIRECTORY);
                    if (jSONObject7.has("device_name")) {
                        this.sS = jSONObject7.getString("device_name");
                    } else if (this.sV.contains("device_name")) {
                        io.e(TAG, " PandaResponseJsonParser: device name is missing");
                    }
                    if (jSONObject7.has("device_serial_number")) {
                        this.sT = jSONObject7.getString("device_serial_number");
                    }
                    if (jSONObject7.has("alias")) {
                        this.ot = jSONObject7.getString("alias");
                    }
                    if (jSONObject7.has("device_type")) {
                        this.bk = jSONObject7.getString("device_type");
                    }
                    if (jSONObject7.has("kindle_email_address")) {
                        this.sU = jSONObject7.getString("kindle_email_address");
                    }
                    b = null;
                } else {
                    io.e(TAG, " PandaResponseJsonParser: device info is missing");
                }
            } else {
                io.e(TAG, " PandaResponseJsonParser: customer info is missing");
            }
            if (b != null) {
                return b;
            }
        }
        this.bP = jSONObject.getString("customer_id");
        io.i(TAG, " PandaResponseJsonParser: success response received");
        lb lbVar = new lb(this.a, this.ne, this.mAccessToken, this.sI, this.sH, this.bP, null);
        lbVar.c(this.sJ);
        lbVar.et(this.sK);
        lbVar.k(this.sO);
        lbVar.j(this.sL);
        lbVar.l(this.sM);
        lbVar.m(this.sN);
        lbVar.n(this.sP);
        lbVar.b(this.mName);
        lbVar.c(this.sR);
        lbVar.d(this.sS);
        lbVar.a(this.sU);
        return lbVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.identity.auth.device.kz
    /* renamed from: v */
    public lb w(JSONObject jSONObject) throws JSONException {
        RegisterDeviceErrorType registerDeviceErrorType;
        io.a(TAG, " PandaResponseJsonParser: response received a %s error.", jSONObject.toString());
        String string = jSONObject.getString("code");
        if (string != null) {
            if (string.equals("ProtocolError")) {
                registerDeviceErrorType = RegisterDeviceErrorType.RegisterDeviceErrorTypeProtocolError;
            } else if (string.equals("MethodNotAllowed")) {
                registerDeviceErrorType = RegisterDeviceErrorType.RegisterDeviceErrorTypeMethodNotAllowed;
            } else if (string.equals("NotImplemented")) {
                registerDeviceErrorType = RegisterDeviceErrorType.RegisterDeviceErrorTypeNotImplemented;
            } else if (string.equals("InvalidDirectedId")) {
                registerDeviceErrorType = RegisterDeviceErrorType.RegisterDeviceErrorTypeInvalidDirectedId;
            } else if (string.equals("InvalidDevice")) {
                registerDeviceErrorType = RegisterDeviceErrorType.RegisterDeviceErrorTypeInvalidDevice;
            } else if (string.equals("DeviceAlreadyRegistered")) {
                registerDeviceErrorType = RegisterDeviceErrorType.RegisterDeviceErrorTypeDeviceAlreadyRegistered;
            } else if (string.equals("DuplicateDeviceName")) {
                registerDeviceErrorType = RegisterDeviceErrorType.RegisterDeviceErrorTypeDuplicateDeviceName;
            } else if (string.equals("InvalidToken")) {
                registerDeviceErrorType = RegisterDeviceErrorType.RegisterDeviceErrorTypeInvalidTokenPanda;
            } else if (string.equals("DeviceNotRegistered")) {
                registerDeviceErrorType = RegisterDeviceErrorType.RegisterDeviceErrorTypeDeviceNotRegisteredPanda;
            } else if (string.equals("Unauthorized")) {
                registerDeviceErrorType = RegisterDeviceErrorType.RegisterDeviceErrorTypeUnauthorizedPanda;
            }
            return b(registerDeviceErrorType);
        }
        registerDeviceErrorType = RegisterDeviceErrorType.RegisterDeviceErrorTypeUnrecognizedPanda;
        return b(registerDeviceErrorType);
    }

    private lb b(RegisterDeviceErrorType registerDeviceErrorType) {
        return new lb(null, null, null, 0, null, null, new la(registerDeviceErrorType));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.identity.auth.device.kz
    /* renamed from: a */
    public lb b(kc kcVar) throws JSONException {
        la laVar;
        String reason = kcVar.getReason();
        io.a(TAG, " PandaResponseJsonParser: response received a %s challenge.", reason);
        if (!"AuthenticationFailed".equals(reason) && !"InvalidAuthenticationData".equals(reason)) {
            laVar = new la(RegisterDeviceErrorType.RegisterDeviceErrorTypeChallengeResponse);
        } else {
            laVar = new la(RegisterDeviceErrorType.RegisterDeviceErrorTypeCustomerNotFound);
        }
        return new lb(kcVar, laVar);
    }
}
