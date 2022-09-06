package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.identity.auth.accounts.AmazonAccountManager;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.DeviceDataKeys;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.api.RegistrationType;
import com.amazon.identity.auth.device.api.TokenKeys;
import com.amazon.identity.auth.device.api.TokenManagement;
import com.amazon.identity.auth.device.features.Feature;
import com.amazon.identity.auth.device.framework.AuthEndpointErrorParser;
import com.amazon.identity.auth.device.metrics.SSOMetrics;
import com.amazon.identity.auth.device.token.MAPCookie;
import com.amazon.identity.auth.device.token.OAuthTokenManager;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.identity.kcpsdk.auth.DeregisterDeviceErrorType;
import com.amazon.identity.kcpsdk.auth.ParseErrorException;
import com.amazon.identity.kcpsdk.auth.RegisterDeviceErrorType;
import com.amazon.identity.kcpsdk.auth.RegisterDeviceRequest;
import com.amazon.identity.kcpsdk.common.WebResponseParser;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class i {
    private final OAuthTokenManager B;
    private final ea at;
    private final TokenManagement au;
    private final MAPAccountManager av;
    private final cz aw;
    private final co ax;
    private final ha ay;
    private final ed o;
    private final AmazonAccountManager s;
    private final n v;
    private final gg w;
    private final w z;
    private static final Set<String> ar = new HashSet(Arrays.asList("com.amazon.alta.h2clientservice", "com.amazon.map.sample.one", "com.amazon.map.sample.two", "com.amazon.map.sample.three"));
    private static final String TAG = i.class.getName();
    private static final long as = jj.d(1, TimeUnit.MILLISECONDS);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DCP */
    /* renamed from: com.amazon.identity.auth.device.i$5  reason: invalid class name */
    /* loaded from: classes12.dex */
    public static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] aI;
        static final /* synthetic */ int[] aJ;
        static final /* synthetic */ int[] aK = new int[DeregisterDeviceErrorType.values().length];

        static {
            try {
                aK[DeregisterDeviceErrorType.DeregisterDeviceErrorTypeFailed.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            aJ = new int[RegistrationType.values().length];
            try {
                aJ[RegistrationType.WITH_LOGIN_CREDENTIALS.ordinal()] = 1;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                aJ[RegistrationType.WITH_DEVICE_SECRET.ordinal()] = 2;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                aJ[RegistrationType.FROM_ATMAIN.ordinal()] = 3;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                aJ[RegistrationType.FROM_AUTH_TOKEN.ordinal()] = 4;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                aJ[RegistrationType.FROM_ACCESS_TOKEN.ordinal()] = 5;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                aJ[RegistrationType.FROM_ADP_TOKEN.ordinal()] = 6;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                aJ[RegistrationType.WITH_EXPLICIT_URL.ordinal()] = 7;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                aJ[RegistrationType.REGISTER_DELEGATED_ACCOUNT.ordinal()] = 8;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                aJ[RegistrationType.WITH_DIRECTEDID_CREDENTIALS.ordinal()] = 9;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                aJ[RegistrationType.WITH_PRIMARY_DIRECTEDID_CREDENTIALS.ordinal()] = 10;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                aJ[RegistrationType.WITH_LINK_CODE.ordinal()] = 11;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                aJ[RegistrationType.WITH_SSO_CODE.ordinal()] = 12;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                aJ[RegistrationType.ANONYMOUS.ordinal()] = 13;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                aJ[RegistrationType.FROM_AUTHORIZATION_CODE.ordinal()] = 14;
            } catch (NoSuchFieldError unused15) {
            }
            aI = new int[RegisterDeviceErrorType.values().length];
            try {
                aI[RegisterDeviceErrorType.RegisterDeviceErrorTypeCustomerNotFound.ordinal()] = 1;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                aI[RegisterDeviceErrorType.RegisterDeviceErrorTypeDeviceAlreadyRegistered.ordinal()] = 2;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                aI[RegisterDeviceErrorType.RegisterDeviceErrorTypeDuplicateDeviceName.ordinal()] = 3;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                aI[RegisterDeviceErrorType.RegisterDeviceErrorTypeChallengeResponse.ordinal()] = 4;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                aI[RegisterDeviceErrorType.RegisterDeviceErrorTypeMissingValue.ordinal()] = 5;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                aI[RegisterDeviceErrorType.RegisterDeviceErrorTypeInvalidValue.ordinal()] = 6;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                aI[RegisterDeviceErrorType.RegisterDeviceErrorTypeProtocolError.ordinal()] = 7;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                aI[RegisterDeviceErrorType.RegisterDeviceErrorTypeMethodNotAllowed.ordinal()] = 8;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                aI[RegisterDeviceErrorType.RegisterDeviceErrorTypeServerError.ordinal()] = 9;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                aI[RegisterDeviceErrorType.RegisterDeviceErrorTypeNotImplemented.ordinal()] = 10;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                aI[RegisterDeviceErrorType.RegisterDeviceErrorTypeInvalidDirectedId.ordinal()] = 11;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                aI[RegisterDeviceErrorType.RegisterDeviceErrorTypeInvalidDevice.ordinal()] = 12;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                aI[RegisterDeviceErrorType.RegisterDeviceErrorTypeServerUnavailable.ordinal()] = 13;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                aI[RegisterDeviceErrorType.RegisterDeviceErrorTypeUnauthorizedPanda.ordinal()] = 14;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                aI[RegisterDeviceErrorType.RegisterDeviceErrorTypeInvalidTokenPanda.ordinal()] = 15;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                aI[RegisterDeviceErrorType.RegisterDeviceErrorTypeDeviceNotRegisteredPanda.ordinal()] = 16;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                aI[RegisterDeviceErrorType.RegisterDeviceErrorTypePrimaryAccountDeregisteredWhenRegisterSecondary.ordinal()] = 17;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                aI[RegisterDeviceErrorType.RegisterDeviceErrorTypeForbidden.ordinal()] = 18;
            } catch (NoSuchFieldError unused33) {
            }
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public interface a {
        void a(MAPError mAPError, String str, MAPAccountManager.RegistrationError registrationError, String str2, Bundle bundle);

        void b(String str, String str2, Bundle bundle);

        void t(String str);
    }

    public i(Context context) {
        if (context != null) {
            this.o = ed.M(context);
            this.s = new AmazonAccountManager(this.o);
            this.at = (ea) this.o.getSystemService("dcp_device_info");
            this.au = (TokenManagement) this.o.getSystemService("dcp_token_mangement");
            this.av = new MAPAccountManager(this.o);
            this.aw = (cz) this.o.getSystemService("sso_webservice_caller_creator");
            this.v = new n(this.o);
            this.B = new OAuthTokenManager(this.o);
            this.z = new w();
            this.ax = this.o.dW();
            this.w = ((gh) this.o.getSystemService("dcp_data_storage_factory")).dV();
            this.ay = new ha(this.o);
            return;
        }
        throw new IllegalArgumentException("context is null");
    }

    private boolean f(Bundle bundle) {
        return (!bundle.getBoolean(AccountConstants.KEY_RECOVERY_ATTEMPT) || !this.s.n()) ? (bundle.getBoolean("com.amazon.dcp.sso.AddAccount.options.AddAsSecondary") || bundle.getBoolean("com.amazon.dcp.sso.AddAccount.options.AddAsPrimary")) && this.s.n() : !TextUtils.equals(this.s.o(), bundle.getString("com.amazon.dcp.sso.property.account.acctId"));
    }

    private Bundle i() {
        return GeneratedOutlineSupport1.outline13("ignore.platform.restrictions", true);
    }

    private String u(String str) {
        return ie.n(this.o, str, null);
    }

    protected String g() {
        return this.at.cs();
    }

    protected String getDeviceSerialNumber() {
        return this.at.getDeviceSerialNumber();
    }

    protected boolean h() {
        return !jg.dE(this.at.f());
    }

    private boolean b(RegistrationType registrationType, Bundle bundle) {
        if (!bundle.getBoolean("com.amazon.dcp.sso.AddAccount.options.AddAsPrimary") || RegistrationType.FROM_ACCESS_TOKEN.equals(registrationType) || RegistrationType.WITH_LOGIN_CREDENTIALS.equals(registrationType) || RegistrationType.FROM_AUTH_TOKEN.equals(registrationType) || RegistrationType.WITH_DIRECTEDID_CREDENTIALS.equals(registrationType) || RegistrationType.WITH_EXPLICIT_URL.equals(registrationType) || RegistrationType.FROM_AUTHORIZATION_CODE.equals(registrationType)) {
            return false;
        }
        String str = TAG;
        io.e(str, registrationType + " is not currently supported to add primary accounts ");
        return true;
    }

    private RegisterDeviceRequest.DeviceAccountRole g(Bundle bundle) {
        if (bundle.getBoolean("com.amazon.dcp.sso.AddAccount.options.AddAsPrimary")) {
            return RegisterDeviceRequest.DeviceAccountRole.PRIMARY;
        }
        if (bundle.getBoolean("com.amazon.dcp.sso.AddAccount.options.AddAsSecondary")) {
            return RegisterDeviceRequest.DeviceAccountRole.SECONDARY;
        }
        return RegisterDeviceRequest.DeviceAccountRole.UNDEFINED;
    }

    public void a(final a aVar, String str, final String str2, br brVar, final boolean z, final j jVar, final ej ejVar, Bundle bundle) {
        if (aVar != null) {
            if (str2 == null) {
                aVar.a(MAPError.AccountError.DEREGISTER_FAILED, "Deregister failed because an account was not specified.", MAPAccountManager.RegistrationError.DEREGISTER_FAILED, "Deregister failed because an account was not specified.", null);
                return;
            }
            kj kjVar = new kj();
            kjVar.hn();
            kjVar.j(z);
            kjVar.R(bundle);
            a(kjVar.ho(), str2, str, brVar, new kl(), new kh() { // from class: com.amazon.identity.auth.device.i.2
                @Override // com.amazon.identity.auth.device.kh, com.amazon.identity.auth.device.ko
                public void a(Object obj) {
                    io.i(i.TAG, "Successfully completed the deregistration request");
                    kk kkVar = (kk) obj;
                    if (kkVar == null) {
                        aVar.a(MAPError.CommonError.INVALID_RESPONSE, "Error occurred during deregistration. Received a null response", MAPAccountManager.RegistrationError.UNRECOGNIZED, "Deregistration Error: Null response", null);
                    } else if (kkVar.hp() != null) {
                        if (AnonymousClass5.aK[kkVar.hp().hm().ordinal()] != 1) {
                            aVar.a(MAPError.CommonError.INVALID_RESPONSE, "Error occurred during deregistration. Received an unrecognizable response", MAPAccountManager.RegistrationError.UNRECOGNIZED, "Deregistration Error: Unrecognized response", null);
                        } else {
                            aVar.a(MAPError.AccountError.DEREGISTER_FAILED, "Error occurred during deregistration", MAPAccountManager.RegistrationError.DEREGISTER_FAILED, "Deregistration Error: Failed", null);
                        }
                    } else if (z) {
                        bs.P();
                        i.this.a(jVar, aVar, ejVar);
                    } else {
                        bs.aM(str2);
                        aVar.b(null, null, null);
                    }
                }

                @Override // com.amazon.identity.auth.device.kh, com.amazon.identity.auth.device.ko
                public void k() {
                    mq.incrementCounterAndRecord("NetworkError6:AccountRegistrar", new String[0]);
                    aVar.a(MAPError.CommonError.NETWORK_ERROR, "Network failure occurred while performing deregistration request", MAPAccountManager.RegistrationError.NETWORK_FAILURE, "Network failure performing deregistration request", null);
                }

                @Override // com.amazon.identity.auth.device.kh, com.amazon.identity.auth.device.ko
                public void l() {
                    aVar.a(MAPError.CommonError.PARSE_ERROR, "Parsing failure occurred while performing deregistration request", MAPAccountManager.RegistrationError.PARSE_ERROR, "Parsing failure performing deregistration request", null);
                }

                @Override // com.amazon.identity.auth.device.kh, com.amazon.identity.auth.device.ko
                public void onAuthenticationFailed() {
                    aVar.a(MAPError.AccountError.AUTHENTICATION_FAILED, "Authentication failure occurred while performing deregistration request", MAPAccountManager.RegistrationError.AUTHENTICATION_FAILED, "Authentication failure performing deregistration request", null);
                }
            }, aVar, ejVar);
            return;
        }
        throw new IllegalArgumentException("No response set. Could not deregister");
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    static abstract class b extends kh {
        protected final a aL;
        protected final MAPAccountManager av;
        protected final AmazonAccountManager s;

        public b(a aVar) {
            this.aL = aVar;
            this.av = null;
            this.s = null;
        }

        private Bundle m() {
            if (this.s == null || !j()) {
                return null;
            }
            String o = this.s.o();
            if (!TextUtils.isEmpty(o)) {
                return fp.eB().bT(o).bU("PrimaryAccountDeregisteredWhenRegisterSecondary").eC();
            }
            return null;
        }

        protected abstract void a(RegisterDeviceErrorType registerDeviceErrorType);

        @Override // com.amazon.identity.auth.device.kh, com.amazon.identity.auth.device.ko
        public void a(Object obj) {
            io.i(i.TAG, "Receive response from server side of the registration request, parsing the response.");
            lb lbVar = (lb) obj;
            if (lbVar == null) {
                this.aL.a(MAPError.CommonError.INVALID_RESPONSE, "Error occurred during registration. Received a null response", MAPAccountManager.RegistrationError.UNRECOGNIZED, "Registration Error: Null response", null);
            } else if (lbVar.hQ() != null) {
                la hQ = lbVar.hQ();
                String errorString = hQ.hz().getErrorString();
                io.e(i.TAG, "Error string: ".concat(String.valueOf(errorString)));
                kc hX = lbVar.hX();
                Bundle bundle = null;
                switch (AnonymousClass5.aI[hQ.hz().ordinal()]) {
                    case 1:
                        if (hX != null && hX.hj() != null) {
                            bundle = new Bundle();
                            hX.Q(bundle);
                        }
                        this.aL.a(MAPError.AccountError.CUSTOMER_NOT_FOUND, "Error occurred during registration. Customer not found. Invalid credentials.", MAPAccountManager.RegistrationError.CUSTOMER_NOT_FOUND, "Registration Error: Customer not found. Invalid credentials.", bundle);
                        break;
                    case 2:
                        this.aL.a(MAPError.AccountError.DEVICE_ALREADY_REGISTERED, "Error occurred during registration. Device already registered", MAPAccountManager.RegistrationError.DEVICE_ALREADY_REGISTERED, "Registration Error: Device already registered", null);
                        break;
                    case 3:
                        this.aL.a(MAPError.AccountError.DUPLICATE_DEVICE_NAME, "Error occurred during registration. Duplicate device name", MAPAccountManager.RegistrationError.DUPLICATE_DEVICE_NAME, "Registration Error: Duplicate device name", null);
                        break;
                    case 4:
                        io.i(i.TAG, "Registration Error: Challenge Response Received");
                        if (hX == null) {
                            this.aL.a(MAPError.AccountError.MISSING_CHALLENGE_EXCEPTION, "Error occurred during registration. Challenge Exception was missing.", MAPAccountManager.RegistrationError.UNRECOGNIZED, "Registration Error: Unknown. Challenge Exception was missing.", null);
                            break;
                        } else {
                            Bundle bundle2 = new Bundle();
                            bundle2.putBundle(MAPAccountManager.KEY_AUTHENTICATION_CHALLENGE, hX.toBundle());
                            a aVar = this.aL;
                            MAPError.AccountError accountError = MAPError.AccountError.AUTHENTICATION_CHALLENGED;
                            aVar.a(accountError, accountError.getErrorMessage(), MAPAccountManager.RegistrationError.AUTHENTICATION_CHALLENGED, null, bundle2);
                            break;
                        }
                    case 5:
                        this.aL.a(MAPError.CommonError.BAD_REQUEST, "One or more required values are missing", MAPAccountManager.RegistrationError.BAD_REQUEST, "MAP internal bug: One or more required values are missing", null);
                        break;
                    case 6:
                        this.aL.a(MAPError.CommonError.BAD_REQUEST, "Error occurred during registration. One or more required values are invalid", MAPAccountManager.RegistrationError.BAD_REQUEST, "Registration Error: One or more required values are invalid", null);
                        break;
                    case 7:
                        this.aL.a(MAPError.AccountError.UNSUPPORTED_PROTOCOL, "Protocol not supported. SSL required", MAPAccountManager.RegistrationError.UNRECOGNIZED, "MAP internal bug: The Protocol is not supported. SSL required", null);
                        break;
                    case 8:
                        this.aL.a(MAPError.AccountError.INVALID_HTTP_METHOD, "The HTTP method is not valid. For example, using POST instead of GET", MAPAccountManager.RegistrationError.UNRECOGNIZED, "MAP internal bug: The HTTP method is not valid. For example, using POST instead of GET", null);
                        break;
                    case 9:
                        this.aL.a(MAPError.CommonError.SERVER_ERROR, "Error occurred during registration. The server has encountered a runtime error, or the service is temporarily overloaded or unavailable, try again later", MAPAccountManager.RegistrationError.UNRECOGNIZED, "Registration Error: The server has encountered a runtime error, or the service is temporarily overloaded or unavailable, try again later", null);
                        break;
                    case 10:
                        this.aL.a(MAPError.CommonError.FEATURE_NOT_IMPLEMENTED, "The feature has not been implemented yet", MAPAccountManager.RegistrationError.UNRECOGNIZED, "MAP internal bug: The feature is not implemented", null);
                        break;
                    case 11:
                        this.aL.a(MAPError.CommonError.BAD_REQUEST, "Error occurred during registration. The directedId is invalid. Either the customer directedId is invalid, or the device directedId is invalid", MAPAccountManager.RegistrationError.BAD_REQUEST, "Registration Error: The directedId is invalid. e.g. The customer directedId is invalid. The device directedId is invalid", null);
                        break;
                    case 12:
                        this.aL.a(MAPError.CommonError.BAD_REQUEST, "Error occurred during registration. The device information is invalid.", MAPAccountManager.RegistrationError.BAD_REQUEST, "Registration Error: The device information is invalid.", null);
                        break;
                    case 13:
                        this.aL.a(MAPError.CommonError.SERVICE_UNAVAILABLE, "Error occurred during registration. The service is temporarily overloaded or unavailable, try again later", MAPAccountManager.RegistrationError.UNRECOGNIZED, "Registration Error: The service is temporarily overloaded or unavailable, try again later", null);
                        break;
                    case 14:
                        this.aL.a(MAPError.AccountError.AUTHENTICATION_FAILED, String.format("Error occurred during registration. Authentication failed with message: %s", errorString), MAPAccountManager.RegistrationError.AUTHENTICATION_FAILED, "Registration Error: ".concat(String.valueOf(errorString)), null);
                        break;
                    case 15:
                        this.aL.a(MAPError.AccountError.AUTHENTICATION_FAILED, String.format("Error occurred during registration. Authentication failed with message: %s", errorString), MAPAccountManager.RegistrationError.AUTHENTICATION_FAILED, "Registration Error: ".concat(String.valueOf(errorString)), null);
                        break;
                    case 16:
                        mq.b("PrimaryAccountDeregisteredWhenRegisterSecondaryWithPanda", new String[0]);
                        this.aL.a(MAPError.AccountError.ACCOUNT_ALREADY_DEREGISTERED, String.format("Error occurred during registration. %s", errorString), MAPAccountManager.RegistrationError.ALREADY_DEREGISTERED, "Registration Error: ".concat(String.valueOf(errorString)), m());
                        break;
                    case 17:
                        this.aL.a(MAPError.AccountError.ACCOUNT_ALREADY_DEREGISTERED, String.format("Error occurred during registration. %s", errorString), MAPAccountManager.RegistrationError.ALREADY_DEREGISTERED, "Registration Error: ".concat(String.valueOf(errorString)), m());
                        break;
                    case 18:
                        this.aL.a(MAPError.CommonError.BAD_REQUEST, String.format("Error occurred during registration. %s", errorString), MAPAccountManager.RegistrationError.BAD_REQUEST, "Registration error: ".concat(String.valueOf(errorString)), null);
                        break;
                    default:
                        this.aL.a(MAPError.CommonError.INVALID_RESPONSE, "Error occurred during registration. Unrecognized response. If this is a 1st party Amazon device and it is not corrected from a retry, please verify that your device has been fulfilled.", MAPAccountManager.RegistrationError.UNRECOGNIZED, "Registration Error: Unrecognized response. If this is a 1st party Amazon device and is not corrected from a retry, please verify that your device has been fulfilled.", null);
                        String unused = i.TAG;
                        io.gD();
                        break;
                }
                a(hQ.hz());
            } else {
                c b = b(lbVar);
                MAPAccountManager.RegistrationError registrationError = b.mLegacyError;
                if (registrationError == null) {
                    this.aL.b(b.getDirectedId(), b.getUserName(), b.l);
                    return;
                }
                a aVar2 = this.aL;
                MAPError mAPError = b.mError;
                String str = b.mErrorMessage;
                aVar2.a(mAPError, str, registrationError, "Registration Error: " + b.mLegacyError.toString(), null);
            }
        }

        protected abstract c b(lb lbVar);

        protected abstract boolean j();

        @Override // com.amazon.identity.auth.device.kh, com.amazon.identity.auth.device.ko
        public void k() {
            mq.incrementCounterAndRecord("NetworkError4:AccountRegistrar", new String[0]);
            this.aL.a(MAPError.CommonError.NETWORK_ERROR, "Network failure occurred while performing registration request", MAPAccountManager.RegistrationError.NETWORK_FAILURE, "Network failure performing registration request", null);
        }

        @Override // com.amazon.identity.auth.device.kh, com.amazon.identity.auth.device.ko
        public void l() {
            this.aL.a(MAPError.CommonError.PARSE_ERROR, "Parsing failure occurred while performing registration request", MAPAccountManager.RegistrationError.PARSE_ERROR, "Parsing failure performing registration request", null);
        }

        @Override // com.amazon.identity.auth.device.kh, com.amazon.identity.auth.device.ko
        public void onAuthenticationFailed() {
            this.aL.a(MAPError.AccountError.AUTHENTICATION_FAILED, "Authentication failure occurred while performing registration request", MAPAccountManager.RegistrationError.AUTHENTICATION_FAILED, "Authentication failure performing registration request", null);
        }

        public b(a aVar, MAPAccountManager mAPAccountManager) {
            this.aL = aVar;
            this.av = mAPAccountManager;
            this.s = null;
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    static class c {
        public Bundle l;
        public MAPError mError;
        public String mErrorMessage;
        public MAPAccountManager.RegistrationError mLegacyError;

        public c(MAPError mAPError, String str, MAPAccountManager.RegistrationError registrationError) {
            this.mLegacyError = registrationError;
            this.mError = mAPError;
            this.mErrorMessage = str;
        }

        private String v(String str) {
            Bundle bundle = this.l;
            if (bundle == null) {
                return null;
            }
            return bundle.getString(str);
        }

        public String getDirectedId() {
            return v("com.amazon.dcp.sso.property.account.acctId");
        }

        public String getUserName() {
            return v("com.amazon.dcp.sso.property.username");
        }

        public c(Bundle bundle) {
            this.l = bundle;
        }

        public c() {
        }
    }

    protected String f() {
        return this.at.f();
    }

    public void a(a aVar, RegistrationType registrationType, Bundle bundle, j jVar, ej ejVar) {
        if (registrationType != null) {
            Bundle bundle2 = bundle != null ? bundle : new Bundle();
            String o = this.s.o();
            if (o != null && bundle2.getBoolean("com.amazon.dcp.sso.AddAccount.options.AddAsPrimary")) {
                if (b(registrationType, bundle2)) {
                    aVar.a(MAPError.CommonError.BAD_REQUEST, "Invalid registration type for registering multiple primary", MAPAccountManager.RegistrationError.BAD_REQUEST, "Invalid registration type for registering multiple primary", null);
                    return;
                } else if (!mz.bb(this.o)) {
                    aVar.a(MAPError.CommonError.BAD_REQUEST, "Multiple primary account is not supported on 1P device", MAPAccountManager.RegistrationError.BAD_REQUEST, "Multiple primary account is not supported on 1P device", null);
                    return;
                }
            }
            if (!bundle2.getBoolean(AccountConstants.KEY_RECOVERY_ATTEMPT) && !bundle2.getBoolean(MAPAccountManager.KEY_DEREGISTERALL_AND_REGISTER_THIS_AS_PRIMARY_ACCOUNT) && o != null && !a(registrationType, bundle2)) {
                aVar.t(o);
                return;
            }
            a(registrationType, bundle2);
            boolean z = true;
            switch (AnonymousClass5.aJ[registrationType.ordinal()]) {
                case 1:
                    String string = bundle2.getString(MAPAccountManager.KEY_AMAZON_ACCOUNT_LOGIN_NAME);
                    String string2 = bundle2.getString(MAPAccountManager.KEY_AMAZON_ACCOUNT_PASSWORD);
                    if (string != null && string2 != null) {
                        if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
                            if (!f(bundle2)) {
                                if (this.ax.a(Feature.SplitRegistration)) {
                                    Bundle a2 = a(aVar, bundle2, ejVar);
                                    if (a2 == null) {
                                        return;
                                    }
                                    a(a2.getString("com.amazon.dcp.sso.AddAccount.options.AccessToken"), bundle2, jVar, aVar, RegisterDeviceRequest.CustomerAccountTokenType.ACCESS_TOKEN, ejVar);
                                    return;
                                }
                                RegisterDeviceRequest registerDeviceRequest = new RegisterDeviceRequest(this.o, bundle2);
                                registerDeviceRequest.ea(string);
                                registerDeviceRequest.dS(string2);
                                a(registerDeviceRequest, bundle2, string, jVar, aVar, false, ejVar);
                                return;
                            } else if (Feature.SecondaryRegistrationUsingPanda.fetchValueNoCache(this.o)) {
                                io.i(TAG, "Migrated secondary panda registration flow.");
                                RegisterDeviceRequest registerDeviceRequest2 = new RegisterDeviceRequest(this.o, bundle2);
                                registerDeviceRequest2.a(true, g(bundle2));
                                a(registerDeviceRequest2, bundle2, null, jVar, aVar, true, ejVar);
                                return;
                            } else {
                                io.i(TAG, "Legacy secondary registration flow.");
                                Bundle a3 = a(aVar, bundle2, ejVar);
                                if (a3 == null) {
                                    return;
                                }
                                String string3 = a3.getString("com.amazon.dcp.sso.property.account.acctId");
                                if (this.s.E(string3)) {
                                    io.e(TAG, "Secondary account already exists on the device");
                                    aVar.t(string3);
                                    return;
                                }
                                a(a3.getString("com.amazon.dcp.sso.AddAccount.options.AccessToken"), bundle2, jVar, aVar, RegisterDeviceRequest.CustomerAccountTokenType.ACCESS_TOKEN, ejVar);
                                return;
                            }
                        }
                        aVar.a(MAPError.CommonError.BAD_REQUEST, "No login or password provided", MAPAccountManager.RegistrationError.CUSTOMER_NOT_FOUND, "No login or password provided", null);
                        return;
                    }
                    aVar.a(MAPError.CommonError.BAD_REQUEST, "Must provide an Amazon login and password to register with it", MAPAccountManager.RegistrationError.BAD_REQUEST, "Must provide an Amazon login and password to register with it", null);
                    return;
                case 2:
                    String deviceSerialNumber = getDeviceSerialNumber();
                    String deviceType = this.at.getDeviceType();
                    String f = f();
                    if (jg.dE(f)) {
                        SSOMetrics.a(RegistrationType.WITH_DEVICE_SECRET);
                        aVar.a(MAPError.AccountError.MISSING_DEVICE_SECRET, "No device secret for registration", MAPAccountManager.RegistrationError.BAD_SECRET, "No device secret for registration", null);
                        return;
                    }
                    le leVar = new le();
                    leVar.ew(f);
                    leVar.eu(g());
                    leVar.dT(deviceType);
                    leVar.dU(deviceSerialNumber);
                    leVar.b(this.at.dR());
                    leVar.ei(u(deviceType));
                    leVar.ev("NoState");
                    leVar.dZ(lz.a(Locale.getDefault()));
                    leVar.l(ie.ay(this.o));
                    ko a4 = a(aVar, jVar, (String) null, deviceType, deviceSerialNumber);
                    leVar.e(ejVar);
                    md ho = leVar.ho();
                    if (ho != null) {
                        a(ho, new ld(), a4, aVar, ejVar);
                        return;
                    } else {
                        aVar.a(MAPError.CommonError.BAD_REQUEST, "Could not construct a valid pre-registration request", MAPAccountManager.RegistrationError.BAD_REQUEST, "Could not construct a valid pre-registration request", null);
                        return;
                    }
                case 3:
                    String string4 = bundle2.getString("com.amazon.dcp.sso.AddAccount.options.ATMain");
                    if (string4 == null) {
                        aVar.a(MAPError.TokenError.MISSING_TOKEN, "Must provide at-main to register with it", MAPAccountManager.RegistrationError.BAD_REQUEST, "Must provide at-main to register with it", null);
                        return;
                    } else {
                        a(string4, bundle2, jVar, aVar, RegisterDeviceRequest.CustomerAccountTokenType.AT_MAIN, ejVar);
                        return;
                    }
                case 4:
                    String string5 = bundle2.getString("com.amazon.dcp.sso.AddAccount.options.AuthToken");
                    String string6 = bundle2.getString("com.amazon.dcp.sso.AddAccount.options.AuthTokenClientContext");
                    String H = hr.H(bundle2);
                    if (!TextUtils.isEmpty(string5) && !TextUtils.isEmpty(string6) && !TextUtils.isEmpty(H)) {
                        if (!this.ax.a(Feature.RegistrationViaAuthToken)) {
                            aVar.a(MAPError.CommonError.UNSUPPORTED_OPERATION, "Registration via auth token is not supported on this platform", MAPAccountManager.RegistrationError.BAD_REQUEST, "Registration via auth token is not supported on this platform", null);
                            return;
                        } else {
                            a(string5, bundle2, jVar, aVar, RegisterDeviceRequest.CustomerAccountTokenType.AUTH_TOKEN, ejVar);
                            return;
                        }
                    }
                    aVar.a(MAPError.CommonError.BAD_REQUEST, "Must provide the auth token, the auth token context, and the auth token domain to register with it", MAPAccountManager.RegistrationError.BAD_REQUEST, "Must provide the auth token, the auth token context, and the auth token domain to register with it", null);
                    return;
                case 5:
                    String string7 = bundle2.getString("com.amazon.dcp.sso.AddAccount.options.AccessToken");
                    if (string7 == null) {
                        aVar.a(MAPError.TokenError.MISSING_TOKEN, "Must provide access token to register with it", MAPAccountManager.RegistrationError.BAD_REQUEST, "Must provide access token to register with it", null);
                        return;
                    } else {
                        a(string7, bundle2, jVar, aVar, RegisterDeviceRequest.CustomerAccountTokenType.ACCESS_TOKEN, ejVar);
                        return;
                    }
                case 6:
                    String string8 = bundle2.getString("adp_token");
                    String string9 = bundle2.getString("adp_private_key");
                    String string10 = bundle2.getString(DeviceDataKeys.KEY_DEVICE_SERIAL_NUMBER);
                    if (!TextUtils.isEmpty(string8) && !TextUtils.isEmpty(string9) && !TextUtils.isEmpty(string10)) {
                        String deviceType2 = this.at.getDeviceType();
                        lm lmVar = new lm();
                        lk lkVar = new lk(new bt(string8, string9));
                        ko a5 = a(aVar, jVar, (String) null, deviceType2, string10);
                        md ho2 = lmVar.ho();
                        if (ho2 != null) {
                            try {
                                z = true ^ lkVar.b(ho2);
                            } catch (Exception e) {
                                io.e(TAG, "Error occurred while trying to sign request with ADP token. Please make sure ADP token and private key are valid.", e);
                            }
                            if (z && !jk.gR()) {
                                MAPError.CommonError commonError = MAPError.CommonError.BAD_REQUEST;
                                aVar.a(commonError, commonError.getErrorMessage(), MAPAccountManager.RegistrationError.BAD_REQUEST, null, null);
                                return;
                            }
                            ho2.m(false);
                            a(ho2, new ln(), a5, aVar, ejVar);
                            return;
                        }
                        aVar.a(MAPError.CommonError.BAD_REQUEST, "Could not construct a register with ADP token request", MAPAccountManager.RegistrationError.BAD_REQUEST, "Could not construct a register with ADP token request", null);
                        return;
                    }
                    aVar.a(MAPError.CommonError.BAD_REQUEST, "One of the following information is missing from BootstrapSSO with ADP token request: 1- ADP Token, 2- Private key, 3- DSN", MAPAccountManager.RegistrationError.BAD_REQUEST, "One of the following information is missing from BootstrapSSO with ADP token request: 1- ADP Token, 2- Private key, 3- DSN", null);
                    return;
                case 7:
                    a(bundle2.getString("com.amazon.dcp.sso.AddAccount.options.URL"), false, jVar, aVar, ejVar);
                    return;
                case 8:
                    String string11 = bundle2.getString("calling_package");
                    String string12 = bundle2.getString("com.amazon.dcp.sso.property.account.delegateeaccount");
                    String string13 = bundle2.getString("com.amazon.dcp.sso.property.account.acctId");
                    if (!TextUtils.isEmpty(string12) && !TextUtils.isEmpty(string13)) {
                        if (mz.aZ(this.o)) {
                            if (!ar.contains(string11)) {
                                mq.incrementCounterAndRecord("NonWhitelistAppRegisterDelegatedAccount_".concat(String.valueOf(string11)), new String[0]);
                            }
                            if (bundle2.getInt(MAPAccountManager.KEY_PROFILE_MAPPING) == 0) {
                                io.e(TAG, "Profile id for delegated account on FireOS is missing!!!");
                                mq.incrementCounterAndRecord("DelegatedAccountProfileIdMissing_".concat(String.valueOf(string11)), new String[0]);
                            }
                        }
                        if (this.s.E(string13)) {
                            io.w(TAG, "The delegated account already exists on the device");
                            aVar.t(string13);
                            return;
                        }
                        bundle2.putBoolean("com.amazon.dcp.sso.AddAccount.options.AddAsSecondary", true);
                        if (TextUtils.isEmpty(string13)) {
                            aVar.a(MAPError.CommonError.BAD_REQUEST, "Delegated directedId missing", MAPAccountManager.RegistrationError.BAD_REQUEST, "Delegated directedId missing", null);
                            return;
                        }
                        RegisterDeviceRequest registerDeviceRequest3 = new RegisterDeviceRequest(this.o, bundle2);
                        registerDeviceRequest3.ef(string13);
                        registerDeviceRequest3.hC();
                        registerDeviceRequest3.a(f(bundle2), g(bundle2));
                        a(registerDeviceRequest3, bundle2, null, jVar, aVar, true, ejVar);
                        return;
                    }
                    String format = String.format("Either the delegated account %s or the delegatee account %s is not valid.", string13, string12);
                    aVar.a(MAPError.CommonError.BAD_REQUEST, format, MAPAccountManager.RegistrationError.BAD_REQUEST, format, null);
                    return;
                case 9:
                    String string14 = bundle2.getString("com.amazon.dcp.sso.property.account.acctId");
                    String string15 = bundle2.getString(MAPAccountManager.KEY_AMAZON_ACCOUNT_PASSWORD);
                    if (string14 != null && string15 != null) {
                        if (Feature.SecondaryRegistrationUsingPanda.fetchValueNoCache(this.o)) {
                            io.i(TAG, "Migrated panda secondary registration flow.");
                            mq.b("map_panda_secondary_registration", new String[0]);
                            if (!TextUtils.isEmpty(string14) && !TextUtils.isEmpty(string15)) {
                                if (this.s.p().isEmpty()) {
                                    aVar.a(MAPError.AccountError.CUSTOMER_NOT_FOUND, "The device is not registered. Can not add secondary account.", MAPAccountManager.RegistrationError.CUSTOMER_NOT_FOUND, "The device is not registered. Can not add secondary account.", null);
                                    return;
                                } else if (this.s.E(string14)) {
                                    io.e(TAG, "Secondary account already exists on the device");
                                    aVar.t(string14);
                                    return;
                                } else {
                                    RegisterDeviceRequest registerDeviceRequest4 = new RegisterDeviceRequest(this.o, bundle2);
                                    registerDeviceRequest4.a(RegisterDeviceRequest.RegisterEndpointEnum.Panda);
                                    registerDeviceRequest4.ef(string14);
                                    registerDeviceRequest4.eg(string15);
                                    registerDeviceRequest4.a(true, g(bundle2));
                                    a(registerDeviceRequest4, bundle2, null, jVar, aVar, true, ejVar);
                                    return;
                                }
                            }
                            aVar.a(MAPError.CommonError.BAD_REQUEST, "No login or password provided", MAPAccountManager.RegistrationError.CUSTOMER_NOT_FOUND, "No login or password provided", null);
                            return;
                        }
                        io.i(TAG, "Legacy secondary registration flow.");
                        mq.b("map_legacy_secondary_registration", new String[0]);
                        if (!TextUtils.isEmpty(string14) && !TextUtils.isEmpty(string15)) {
                            if (this.s.p().isEmpty()) {
                                aVar.a(MAPError.AccountError.REGISTER_FAILED, "The device is not registered. Can not add secondary account.", MAPAccountManager.RegistrationError.CUSTOMER_NOT_FOUND, "The device is not registered. Can not add secondary account.", null);
                                return;
                            } else if (this.s.E(string14)) {
                                io.e(TAG, "Secondary account already exists on the device");
                                aVar.t(string14);
                                return;
                            } else if (this.ax.a(Feature.SplitRegistrationWithDirectedId)) {
                                Bundle a6 = a(string14, string15, aVar, bundle2, ejVar);
                                if (a6 == null) {
                                    io.i(TAG, " null auth data was returned. registration is not successful.");
                                    return;
                                } else {
                                    a(a6.getString("com.amazon.dcp.sso.AddAccount.options.AccessToken"), bundle2, jVar, aVar, RegisterDeviceRequest.CustomerAccountTokenType.ACCESS_TOKEN, ejVar);
                                    return;
                                }
                            } else {
                                RegisterDeviceRequest registerDeviceRequest5 = new RegisterDeviceRequest(this.o, bundle2);
                                registerDeviceRequest5.a(RegisterDeviceRequest.RegisterEndpointEnum.Panda);
                                registerDeviceRequest5.ef(string14);
                                registerDeviceRequest5.eg(string15);
                                registerDeviceRequest5.a(true, g(bundle2));
                                a(registerDeviceRequest5, bundle2, null, jVar, aVar, true, ejVar);
                                return;
                            }
                        }
                        aVar.a(MAPError.CommonError.BAD_REQUEST, "No login or password provided", MAPAccountManager.RegistrationError.CUSTOMER_NOT_FOUND, "No login or password provided", null);
                        return;
                    }
                    aVar.a(MAPError.CommonError.BAD_REQUEST, "Must provide an Amazon directedId and password to register with it", MAPAccountManager.RegistrationError.BAD_REQUEST, "Must provide an Amazon directedId and password to register with it", null);
                    return;
                case 10:
                    String string16 = bundle2.getString("com.amazon.dcp.sso.property.account.primary.acctId");
                    String string17 = bundle2.getString(MAPAccountManager.KEY_AMAZON_ACCOUNT_PASSWORD);
                    if (!TextUtils.isEmpty(string16) && !TextUtils.isEmpty(string17)) {
                        if (!this.s.p().isEmpty()) {
                            aVar.a(MAPError.AccountError.DEVICE_ALREADY_REGISTERED, "The device is already registered. Can not add primary account.", MAPAccountManager.RegistrationError.DEVICE_ALREADY_REGISTERED, "The device is already registered. Can not add primary account.", null);
                            return;
                        }
                        RegisterDeviceRequest registerDeviceRequest6 = new RegisterDeviceRequest(this.o, bundle2);
                        registerDeviceRequest6.a(RegisterDeviceRequest.RegisterEndpointEnum.Panda);
                        registerDeviceRequest6.eb(string16);
                        registerDeviceRequest6.dS(string17);
                        registerDeviceRequest6.hB();
                        a(registerDeviceRequest6, bundle2, null, jVar, aVar, true, ejVar);
                        return;
                    }
                    aVar.a(MAPError.CommonError.BAD_REQUEST, "Must provide an Amazon directedId and password.", MAPAccountManager.RegistrationError.BAD_REQUEST, "Must provide an Amazon directedId and password.", null);
                    return;
                case 11:
                    RegisterDeviceRequest registerDeviceRequest7 = new RegisterDeviceRequest(this.o, bundle2);
                    String string18 = bundle2.getString(MAPAccountManager.KEY_PRE_AUTHORIZED_LINK_CODE);
                    if (!TextUtils.isEmpty(string18)) {
                        registerDeviceRequest7.el(string18);
                    } else {
                        String string19 = bundle2.getString(AccountConstants.KEY_CBL_PUBLIC_CODE);
                        String string20 = bundle2.getString(AccountConstants.KEY_CBL_PRIVATE_CODE);
                        registerDeviceRequest7.ek(string19);
                        registerDeviceRequest7.em(string20);
                    }
                    a(registerDeviceRequest7, bundle2, null, jVar, aVar, true, ejVar);
                    return;
                case 12:
                    RegisterDeviceRequest registerDeviceRequest8 = new RegisterDeviceRequest(this.o, bundle2);
                    String string21 = bundle2.getString("com.amazon.dcp.sso.property.account.acctId");
                    String string22 = bundle2.getString(MAPAccountManager.KEY_SSO_CODE);
                    String string23 = bundle2.getString(MAPAccountManager.KEY_SSO_BOOTSTRAPPED_FROM_DEVICE_TYPE);
                    String string24 = bundle2.getString(MAPAccountManager.KEY_SSO_BOOTSTRAPPED_FROM_DEVICE_SERIAL);
                    if (string21 != null && string22 != null) {
                        registerDeviceRequest8.er(string22);
                        registerDeviceRequest8.es(string21);
                        registerDeviceRequest8.ax(string23, string24);
                        a(registerDeviceRequest8, bundle2, null, jVar, aVar, true, ejVar);
                        return;
                    }
                    aVar.a(MAPError.CommonError.BAD_REQUEST, "Must provide an Amazon directedId and a valid SSO code to register with this option", MAPAccountManager.RegistrationError.BAD_REQUEST, "Must provide an Amazon directedId and a valid sso code to register with this option", null);
                    return;
                case 13:
                    mq.incrementCounterAndRecord("MAPRegisterAnonymousAccount:" + this.at.getDeviceType(), new String[0]);
                    RegisterDeviceRequest registerDeviceRequest9 = new RegisterDeviceRequest(this.o, new lc(new kv()));
                    String string25 = bundle2.getString(MAPAccountManager.KEY_ACCOUNT_COR);
                    if (TextUtils.isEmpty(string25)) {
                        aVar.a(MAPError.CommonError.BAD_REQUEST, "Must provide a valid Country of Residence (COR) to register with this option", MAPAccountManager.RegistrationError.BAD_REQUEST, "Must provide a valid COR to register with this option", null);
                        return;
                    }
                    if (!this.at.ct()) {
                        mq.incrementCounterAndRecord("MAPRegisterAnonymousAccount_NonDHAPlatform", new String[0]);
                    }
                    RegisterDeviceRequest.a aVar2 = new RegisterDeviceRequest.a();
                    aVar2.l(string25);
                    registerDeviceRequest9.a(aVar2);
                    a(registerDeviceRequest9, bundle2, null, jVar, aVar, true, ejVar);
                    return;
                case 14:
                    String string26 = bundle2.getString(AccountConstants.KEY_AUTHORIZATION_CODE);
                    String string27 = bundle2.getString(AccountConstants.KEY_CODE_VERIFIER);
                    String string28 = bundle2.getString("code_challenge_method");
                    String string29 = bundle2.getString("client_id");
                    if (!TextUtils.isEmpty(string26) && !TextUtils.isEmpty(string27) && !TextUtils.isEmpty(string28)) {
                        RegisterDeviceRequest registerDeviceRequest10 = new RegisterDeviceRequest(this.o, bundle2);
                        registerDeviceRequest10.en(string26);
                        registerDeviceRequest10.eo(string27);
                        registerDeviceRequest10.ep(string28);
                        registerDeviceRequest10.aW(string29);
                        registerDeviceRequest10.a(f(bundle2), g(bundle2));
                        a(registerDeviceRequest10, bundle2, null, jVar, aVar, true, ejVar);
                        return;
                    }
                    aVar.a(MAPError.CommonError.BAD_REQUEST, "No valid authorization code/code verifier/code challenge method", MAPAccountManager.RegistrationError.BAD_REQUEST, "No valid authorization code/code verifier/code challenge method", null);
                    return;
                default:
                    aVar.a(MAPError.CommonError.BAD_REQUEST, "Unrecognized or unsupported registration type.", MAPAccountManager.RegistrationError.BAD_REQUEST, "Unrecognized or unsupported registration type.", null);
                    return;
            }
        }
        throw new IllegalArgumentException("No registration type set. Could not register");
    }

    public void a(a aVar, String str, Bundle bundle, ej ejVar) {
        if (aVar != null) {
            a(bundle != null ? bundle.getString("com.amazon.dcp.sso.AddAccount.options.URL") : null, str, (String) null, aVar, ejVar);
            return;
        }
        throw new IllegalArgumentException("No response set. Could not update credentials");
    }

    public void a(a aVar, String str, String str2, Bundle bundle, ej ejVar) {
        if (str2 == null) {
            a(aVar, str, bundle, ejVar);
        } else {
            a(bundle != null ? bundle.getString("com.amazon.dcp.sso.AddAccount.options.URL") : null, str, str2, aVar, ejVar);
        }
    }

    private Bundle a(a aVar, Bundle bundle, ej ejVar) {
        Bundle bundle2;
        try {
            return this.v.a(bundle, ejVar);
        } catch (MAPCallbackErrorException e) {
            io.dm(TAG);
            fp a2 = fp.a(e);
            if (a2 != null && !bundle.getBoolean(AccountConstants.KEY_RECOVERY_ATTEMPT)) {
                aVar.a(MAPError.AccountError.CORRUPTED_ACCOUNT, "The primary account has been corrupted. It should be recovered", MAPAccountManager.RegistrationError.INTERNAL_ERROR, "Primary account corrupted, should recover", a2.eC());
                return null;
            }
            Bundle errorBundle = e.getErrorBundle();
            int i = errorBundle.getInt("com.amazon.dcp.sso.ErrorCode");
            Bundle bundle3 = errorBundle.getBundle(MAPAccountManager.KEY_AUTHENTICATION_CHALLENGE);
            MAPError error = e.getError() != null ? e.getError() : MAPError.CommonError.INTERNAL_ERROR;
            String errorMessage = error.getErrorMessage();
            if (bundle3 != null) {
                Bundle bundle4 = new Bundle();
                bundle4.putBundle(MAPAccountManager.KEY_AUTHENTICATION_CHALLENGE, bundle3);
                String string = bundle3.getString(MAPAccountManager.KEY_AUTH_DATA_ADDITIONAL_INFO);
                if (string != null) {
                    bundle4.putString(MAPAccountManager.KEY_AUTH_DATA_ADDITIONAL_INFO, string);
                }
                bundle2 = bundle4;
            } else {
                bundle2 = null;
            }
            aVar.a(error, errorMessage, MAPAccountManager.RegistrationError.fromValue(i), null, bundle2);
            return null;
        }
    }

    Bundle a(String str, String str2, a aVar, Bundle bundle, ej ejVar) {
        Bundle bundle2 = new Bundle();
        bundle2.putString(MAPAccountManager.KEY_SIGN_IN_ENDPOINT, bundle.getString(MAPAccountManager.KEY_SIGN_IN_ENDPOINT));
        bundle2.putBundle(MAPAccountManager.KEY_MARKETPLACE_BUNDLE, bundle.getBundle(MAPAccountManager.KEY_MARKETPLACE_BUNDLE));
        bundle2.putString("com.amazon.identity.ap.domain", bundle.getString("com.amazon.identity.ap.domain"));
        bundle2.putString("com.amazon.dcp.sso.property.account.acctId", str);
        bundle2.putString(MAPAccountManager.KEY_AMAZON_ACCOUNT_PASSWORD, str2);
        bundle2.putString("calling_package", bundle.getString("calling_package"));
        try {
            return this.v.a(bundle2, ejVar);
        } catch (MAPCallbackErrorException e) {
            fp a2 = fp.a(e);
            if (a2 != null) {
                aVar.a(MAPError.AccountError.CORRUPTED_ACCOUNT, "The primary account has been corrupted. It should be recovered", MAPAccountManager.RegistrationError.INTERNAL_ERROR, "Primary account corrupted, should recover", a2.eC());
                return null;
            }
            Bundle errorBundle = e.getErrorBundle();
            MAPError error = e.getError() != null ? e.getError() : MAPError.CommonError.INTERNAL_ERROR;
            String errorMessage = error.getErrorMessage();
            MAPAccountManager.RegistrationError fromValue = MAPAccountManager.RegistrationError.fromValue(errorBundle.getInt("com.amazon.dcp.sso.ErrorCode"));
            aVar.a(error, errorMessage, fromValue, "Error occurred while authenticating. Error code: " + MAPAccountManager.RegistrationError.fromValue(errorBundle.getInt("com.amazon.dcp.sso.ErrorCode")).getName(), errorBundle);
            return null;
        }
    }

    private void a(String str, Bundle bundle, j jVar, a aVar, RegisterDeviceRequest.CustomerAccountTokenType customerAccountTokenType, ej ejVar) {
        if (aVar != null) {
            if (TextUtils.isEmpty(str)) {
                aVar.a(MAPError.TokenError.MISSING_TOKEN, "Missing token", MAPAccountManager.RegistrationError.BAD_REQUEST, "Missing token", null);
                return;
            }
            RegisterDeviceRequest registerDeviceRequest = new RegisterDeviceRequest(this.o, bundle);
            registerDeviceRequest.ec(str);
            registerDeviceRequest.a(customerAccountTokenType);
            registerDeviceRequest.aV(bundle.getString("com.amazon.dcp.sso.AddAccount.options.AuthTokenClientContext"));
            registerDeviceRequest.a(f(bundle), g(bundle));
            a(registerDeviceRequest, bundle, null, jVar, aVar, false, ejVar);
            return;
        }
        throw new IllegalArgumentException("One or more arguments are null or empty");
    }

    private void a(RegisterDeviceRequest registerDeviceRequest, Bundle bundle, String str, j jVar, a aVar, boolean z, ej ejVar) {
        String J = hr.J(bundle);
        registerDeviceRequest.eq(J);
        List<MAPCookie> cJ = this.ay.cJ(J);
        String string = bundle.getString(AccountConstants.KEY_SID_COOKIE_VALUE);
        if (!TextUtils.isEmpty(string)) {
            cJ.add(ha.aa(string, J));
        }
        registerDeviceRequest.l(cJ);
        String deviceSerialNumber = getDeviceSerialNumber();
        registerDeviceRequest.dU(deviceSerialNumber);
        String deviceType = this.at.getDeviceType();
        registerDeviceRequest.dT(deviceType);
        registerDeviceRequest.a(this.at.dR());
        registerDeviceRequest.ei(u(deviceType));
        String string2 = bundle.getString("calling_package");
        if (string2 != null) {
            registerDeviceRequest.dV(string2);
            Long x = it.x(this.o, string2);
            if (x != null) {
                registerDeviceRequest.dW(Long.toString(x.longValue()));
            }
        }
        String string3 = bundle.getString("com.amazon.dcp.sso.property.account.delegateeaccount");
        if (TextUtils.isEmpty(string3)) {
            string3 = this.s.o();
        }
        String str2 = string3;
        if (z || this.ax.a(Feature.PandaRegistration)) {
            registerDeviceRequest.S(bundle);
            registerDeviceRequest.a(RegisterDeviceRequest.RegisterEndpointEnum.Panda);
            if (bundle.getBoolean(MAPAccountManager.KEY_DISABLE_GLOBAL_SIGNIN)) {
                registerDeviceRequest.k(false);
            } else {
                registerDeviceRequest.k(true);
            }
            registerDeviceRequest.dY(fm.h(this.o, deviceSerialNumber));
            registerDeviceRequest.z(fm.b(deviceType, deviceSerialNumber, ejVar));
            if (!mz.aY(this.o)) {
                registerDeviceRequest.ej(new cl(this.o).bQ());
            }
            if (registerDeviceRequest.hA()) {
                if (mz.aZ(this.o) && bundle.getInt(MAPAccountManager.KEY_PROFILE_MAPPING) == 0) {
                    io.e(TAG, "Profile id for secondary account on FireOS is missing!!!");
                    mq.incrementCounterAndRecord(String.format("SecondaryAccountProfileIDMissingOnFireOS_%s", string2), new String[0]);
                }
                try {
                    registerDeviceRequest.ed(this.B.a(str2, im.dl("com.amazon.dcp.sso.token.oauth.amazon.access_token"), (Bundle) null, ejVar));
                    String string4 = bundle.getString(MAPAccountManager.KEY_AMAZON_ACCOUNT_LOGIN_NAME);
                    String string5 = bundle.getString(MAPAccountManager.KEY_AMAZON_ACCOUNT_PASSWORD);
                    if (!TextUtils.isEmpty(string4) && !TextUtils.isEmpty(string5)) {
                        registerDeviceRequest.ee(string4);
                        registerDeviceRequest.eg(string5);
                    }
                } catch (OAuthTokenManager.OAuthTokenManagerException e) {
                    String format = String.format("Get error when getting the primary access token for secondary account registration %s.", e.bF());
                    AuthEndpointErrorParser.a fT = e.fT();
                    if (fT != null) {
                        if (fT.cG() == AuthEndpointErrorParser.AuthErrorType.InvalidToken) {
                            fp eE = e.eE();
                            if (eE != null) {
                                aVar.a(MAPError.AccountError.DEVICE_ALREADY_DEREGISTERED, String.format("Device already deregistered. %s", format), MAPAccountManager.RegistrationError.ALREADY_DEREGISTERED, format, eE.eC());
                                return;
                            } else {
                                aVar.a(MAPError.AccountError.DEVICE_ALREADY_DEREGISTERED, String.format("Device already deregistered. %s", format), MAPAccountManager.RegistrationError.ALREADY_DEREGISTERED, format, null);
                                return;
                            }
                        }
                        aVar.a(fT.cG().getError(), fT.cG().getErrorMessage(), fT.getRegistrationError(), format, null);
                        return;
                    }
                    aVar.a(MAPError.CommonError.BAD_REQUEST, String.format("Invalid request. %s", format), MAPAccountManager.RegistrationError.BAD_REQUEST, format, null);
                    return;
                }
            }
        }
        registerDeviceRequest.ew(f());
        registerDeviceRequest.dZ(ij.di(J));
        registerDeviceRequest.l(ie.ay(this.o));
        ko a2 = a(aVar, jVar, str, deviceType, deviceSerialNumber);
        RegisterDeviceRequest.RegisterEndpointEnum hI = registerDeviceRequest.hI();
        if (hI == RegisterDeviceRequest.RegisterEndpointEnum.FIRS) {
            registerDeviceRequest.e(ejVar);
        }
        if (hI == RegisterDeviceRequest.RegisterEndpointEnum.Panda) {
            registerDeviceRequest.ia();
        }
        registerDeviceRequest.d(bundle.getString("device_name"));
        md ho = registerDeviceRequest.ho();
        if (ho == null) {
            aVar.a(MAPError.CommonError.BAD_REQUEST, "Could not construct a valid registration request", MAPAccountManager.RegistrationError.BAD_REQUEST, "Could not construct a valid registration request", null);
        } else if (registerDeviceRequest.hI() == RegisterDeviceRequest.RegisterEndpointEnum.Panda) {
            a(ho, str2, registerDeviceRequest.hK(), a2, aVar, ejVar);
        } else {
            a(registerDeviceRequest, a2, ejVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(String str, boolean z, j jVar, a aVar, ej ejVar) {
        if (aVar != null) {
            String deviceSerialNumber = getDeviceSerialNumber();
            String deviceType = this.at.getDeviceType();
            lm lmVar = new lm();
            if (!TextUtils.isEmpty(str)) {
                lmVar.ez(str);
            }
            lmVar.l(ie.ay(this.o));
            if (z) {
                lmVar.ev("Transfer");
            }
            ko a2 = a(aVar, jVar, (String) null, deviceType, deviceSerialNumber);
            md ho = lmVar.ho();
            if (ho != null) {
                a(ho, new ld(), a2, aVar, ejVar);
                return;
            } else {
                aVar.a(MAPError.CommonError.BAD_REQUEST, "Could not construct a registration request from this todo item", MAPAccountManager.RegistrationError.BAD_REQUEST, "Could not construct a registration request from this todo item", null);
                return;
            }
        }
        throw new IllegalArgumentException("Listener is null");
    }

    private void a(String str, final String str2, String str3, a aVar, ej ejVar) {
        if (aVar != null) {
            if (!this.s.D(str2)) {
                MAPError.AccountError accountError = MAPError.AccountError.CUSTOMER_NOT_FOUND;
                aVar.a(accountError, accountError.getErrorMessage(), MAPAccountManager.RegistrationError.NO_ACCOUNT, "Account not currently registered", null);
                return;
            }
            lm lmVar = new lm();
            if (str != null) {
                lmVar.ez(str);
            }
            Long x = it.x(this.o, str3);
            if (x != null) {
                mc mcVar = new mc(Long.toString(x.longValue()));
                lmVar.ev("TodoItem.GET.NAMS");
                lmVar.c(mcVar);
            }
            lmVar.l(ie.ay(this.o));
            b bVar = new b(aVar) { // from class: com.amazon.identity.auth.device.i.1
                @Override // com.amazon.identity.auth.device.i.b
                protected void a(RegisterDeviceErrorType registerDeviceErrorType) {
                }

                @Override // com.amazon.identity.auth.device.i.b
                protected c b(lb lbVar) {
                    e eVar = new e();
                    eVar.b(lbVar.getUserName());
                    eVar.c(lbVar.hO());
                    eVar.d(lbVar.getDeviceName());
                    ax.a(i.this.o, str2, lbVar.getDeviceName());
                    String email = lbVar.getEmail();
                    if (email == null) {
                        io.i(i.TAG, "Was not able to updated device email since it was not returned");
                    } else {
                        eVar.o(email);
                    }
                    eVar.e(lbVar.hL());
                    eVar.f(lbVar.a());
                    eVar.setDirectedId(lbVar.getDirectedId());
                    gf gfVar = new gf(lbVar.hN());
                    eVar.g(gfVar.fb());
                    eVar.h(gfVar.fc());
                    return new c(eVar.b());
                }

                @Override // com.amazon.identity.auth.device.i.b
                protected boolean j() {
                    return false;
                }
            };
            md ho = lmVar.ho();
            if (ho != null) {
                a(ho, str2, str3, (br) null, new ln(), bVar, aVar, ejVar);
                return;
            } else {
                aVar.a(MAPError.CommonError.BAD_REQUEST, "Could not construct a updateCredentials request from this todo item", MAPAccountManager.RegistrationError.BAD_REQUEST, "Could not construct a updateCredentials request from this todo item", null);
                return;
            }
        }
        throw new IllegalArgumentException("One or more null parameters");
    }

    public void a(final j jVar, a aVar, ej ejVar) {
        if (!h()) {
            io.dm(TAG);
            aVar.b(null, null, null);
            return;
        }
        io.i(TAG, "Attempting to get anonymous credentials");
        String deviceSerialNumber = getDeviceSerialNumber();
        String deviceType = this.at.getDeviceType();
        le leVar = new le();
        leVar.ew(f());
        leVar.eu(g());
        leVar.dT(deviceType);
        leVar.dU(deviceSerialNumber);
        leVar.b(this.at.dR());
        leVar.ei(u(deviceType));
        leVar.dZ(lz.a(Locale.getDefault()));
        ko koVar = new b(aVar) { // from class: com.amazon.identity.auth.device.i.3
            @Override // com.amazon.identity.auth.device.i.b
            protected void a(RegisterDeviceErrorType registerDeviceErrorType) {
            }

            @Override // com.amazon.identity.auth.device.i.b
            protected c b(lb lbVar) {
                i.a(i.this, lbVar);
                if (i.a(i.this, lbVar, jVar)) {
                    return new c();
                }
                return new c(MAPError.AccountError.SETTING_CREDENTIALS_FAILED, "An error occurred while setting the credentials. This occurred because either no AccountRegistrarAuthenticator was given, or account credentials were given instead of anonymous credentials", MAPAccountManager.RegistrationError.UNRECOGNIZED);
            }

            @Override // com.amazon.identity.auth.device.i.b
            protected boolean j() {
                return false;
            }
        };
        leVar.e(ejVar);
        if (leVar.ho() != null) {
            a(leVar, koVar, ejVar);
        } else {
            aVar.a(MAPError.CommonError.INTERNAL_ERROR, "Could not construct a valid pre-registration request to get anonymous credentials", MAPAccountManager.RegistrationError.UNRECOGNIZED, "Could not construct a valid pre-registration request to get anonymous credentials", null);
        }
    }

    private ko a(final a aVar, final j jVar, final String str, final String str2, final String str3) {
        return new b(aVar, this.av) { // from class: com.amazon.identity.auth.device.i.4
            @Override // com.amazon.identity.auth.device.i.b
            protected void a(final RegisterDeviceErrorType registerDeviceErrorType) {
                if (registerDeviceErrorType == RegisterDeviceErrorType.RegisterDeviceErrorTypeDeviceNotRegisteredPanda || registerDeviceErrorType == RegisterDeviceErrorType.RegisterDeviceErrorTypePrimaryAccountDeregisteredWhenRegisterSecondary) {
                    if (this.av == null) {
                        String str4 = i.TAG;
                        io.e(str4, "MAPAccountManager not initialized. Not able to deregister the device due to error " + registerDeviceErrorType.name());
                    } else if (j()) {
                    } else {
                        this.av.deregisterDevice(new Callback() { // from class: com.amazon.identity.auth.device.i.4.1
                            @Override // com.amazon.identity.auth.device.api.Callback
                            public void onError(Bundle bundle) {
                                String str5 = i.TAG;
                                io.e(str5, "Got error while deregistering device in response to error : " + registerDeviceErrorType.name());
                                int i = bundle.getInt("com.amazon.dcp.sso.ErrorCode", -1);
                                String string = bundle.getString("com.amazon.dcp.sso.ErrorMessage");
                                io.e(i.TAG, "Error Code: ".concat(String.valueOf(i)));
                                io.e(i.TAG, "Error message: ".concat(String.valueOf(string)));
                            }

                            @Override // com.amazon.identity.auth.device.api.Callback
                            public void onSuccess(Bundle bundle) {
                                if (!bundle.containsKey("com.amazon.dcp.sso.ErrorCode")) {
                                    String str5 = i.TAG;
                                    io.e(str5, "Finished deregistering device in response to error : " + registerDeviceErrorType.name());
                                    return;
                                }
                                onError(bundle);
                            }
                        });
                    }
                }
            }

            @Override // com.amazon.identity.auth.device.i.b
            protected c b(lb lbVar) {
                i.a(i.this, lbVar);
                if (i.this.a(lbVar)) {
                    i.a(i.this, lbVar, jVar);
                    io.w(i.TAG, "Cannot register the device, because anonymous credentials were returned from the server. FRO has either already been called or this device is not associated with an account.");
                    return new c(MAPError.AccountError.REGISTER_FAILED, "Cannot register the device, because anonymous credentials were returned from the server. FRO has either already been called or this device is not associated with an account.", MAPAccountManager.RegistrationError.REGISTER_FAILED);
                }
                e eVar = new e();
                String str4 = str;
                if (str4 != null) {
                    eVar.a(str4);
                }
                String userName = lbVar.getUserName();
                if (TextUtils.isEmpty(userName)) {
                    userName = " ";
                }
                eVar.b(userName);
                eVar.c(lbVar.hO());
                eVar.d(lbVar.getDeviceName());
                eVar.o(lbVar.getEmail());
                eVar.e(lbVar.hL());
                if (lbVar.a() != null) {
                    io.i(i.TAG, "Registration returned server generated credentials.");
                    eVar.f(lbVar.a());
                } else {
                    aVar.a(MAPError.AccountError.REGISTER_FAILED, "No private key to set after register.", MAPAccountManager.RegistrationError.REGISTER_FAILED, "No private key to set after register.", null);
                }
                eVar.setDeviceSerialNumber(str3);
                eVar.setDeviceType(str2);
                eVar.setDirectedId(lbVar.getDirectedId());
                gf gfVar = new gf(lbVar.hN());
                eVar.g(gfVar.fb());
                eVar.h(gfVar.fc());
                eVar.i(lbVar.hP());
                eVar.j(lbVar.cb());
                eVar.k(lbVar.hU());
                eVar.l(lbVar.hR());
                eVar.m(lbVar.hS());
                eVar.n(lbVar.hT());
                eVar.a(lbVar.hV());
                eVar.a(lbVar.hW());
                eVar.p(lbVar.getAccessToken());
                eVar.c(lbVar.hM());
                eVar.q(lbVar.fW());
                return new c(eVar.c());
            }

            @Override // com.amazon.identity.auth.device.i.b
            protected boolean j() {
                return new ds(i.this.o).dy();
            }
        };
    }

    protected void a(lg lgVar, ko koVar, ej ejVar) {
        kp d = this.aw.d(this.s.o(), ejVar);
        try {
            lb lbVar = (lb) d.a(lgVar.ho(), new ld());
            if (lbVar != null) {
                String hY = lbVar.hY();
                if (hY != null) {
                    String str = TAG;
                    "The server timestamp is ".concat(hY);
                    io.dm(str);
                    mq.b("ClockSkewHappened", new String[0]);
                    lgVar.ex(hY);
                    lbVar = (lb) d.a(lgVar.ho(), new ld());
                }
                koVar.a(lbVar);
                return;
            }
            koVar.onAuthenticationFailed();
        } catch (ParseErrorException unused) {
            koVar.l();
        } catch (IOException unused2) {
            koVar.k();
        } catch (UnsupportedOperationException unused3) {
            koVar.k();
        }
    }

    private void a(md mdVar, WebResponseParser webResponseParser, ko koVar, a aVar, ej ejVar) {
        a(mdVar, this.s.o(), (String) null, (br) null, webResponseParser, koVar, aVar, ejVar);
    }

    private void a(md mdVar, String str, WebResponseParser webResponseParser, ko koVar, a aVar, ej ejVar) {
        a(mdVar, str, (String) null, (br) null, webResponseParser, koVar, aVar, ejVar);
    }

    protected void a(md mdVar, String str, String str2, br brVar, WebResponseParser webResponseParser, ko koVar, a aVar, ej ejVar) {
        kp b2;
        if (str2 == null) {
            b2 = this.aw.d(str, ejVar);
        } else {
            if (brVar == null) {
                brVar = a(str, str2, aVar);
            }
            b2 = this.aw.b(brVar, ejVar);
        }
        b2.b(mdVar, webResponseParser, koVar).cD();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(lb lbVar) {
        boolean z = lbVar.getDirectedId() == null && (lbVar.getUserName() == null || lbVar.getDeviceName() == null);
        io.i(TAG, "Is anonymous credentials received: ".concat(String.valueOf(z)));
        return z;
    }

    public br a(String str, String str2, a aVar) {
        try {
            return new bt(this.au.getValue(str, TokenKeys.getAdpTokenKeyForPackage(str2), i(), as), this.au.getValue(str, TokenKeys.getPrivateKeyKeyForPackage(str2), i(), as));
        } catch (MAPCallbackErrorException e) {
            io.e(TAG, "Getting ADP Token failed because of callback error. Error Bundle: ".concat(String.valueOf(e.getErrorBundle())));
            fp a2 = fp.a(e);
            if (a2 != null && aVar != null) {
                MAPError.CommonError commonError = MAPError.CommonError.INTERNAL_ERROR;
                aVar.a(commonError, commonError.getErrorMessage(), MAPAccountManager.RegistrationError.INTERNAL_ERROR, null, a2.eC());
            }
            return null;
        } catch (InterruptedException e2) {
            String str3 = TAG;
            io.e(str3, "Getting ADP Token failed because of InterruptedException: " + e2.getMessage());
            return null;
        } catch (ExecutionException e3) {
            String str4 = TAG;
            io.e(str4, "Getting ADP Token failed because of ExecutionException: " + e3.getMessage());
            return null;
        } catch (TimeoutException e4) {
            String str5 = TAG;
            io.e(str5, "Getting ADP Token failed because of TimeoutException: " + e4.getMessage());
            return null;
        }
    }

    private boolean a(RegistrationType registrationType, Bundle bundle) {
        if (bundle.getBoolean("com.amazon.dcp.sso.AddAccount.options.AddAsSecondary") || bundle.getBoolean("com.amazon.dcp.sso.AddAccount.options.AddAsPrimary")) {
            if (RegistrationType.FROM_ACCESS_TOKEN.equals(registrationType) || RegistrationType.WITH_LOGIN_CREDENTIALS.equals(registrationType) || RegistrationType.REGISTER_DELEGATED_ACCOUNT.equals(registrationType) || RegistrationType.FROM_AUTH_TOKEN.equals(registrationType) || RegistrationType.WITH_DIRECTEDID_CREDENTIALS.equals(registrationType) || RegistrationType.WITH_EXPLICIT_URL.equals(registrationType) || RegistrationType.WITH_LINK_CODE.equals(registrationType) || RegistrationType.FROM_AUTHORIZATION_CODE.equals(registrationType) || !bundle.getBoolean("com.amazon.dcp.sso.AddAccount.options.AddAsSecondary")) {
                return !b(registrationType, bundle);
            }
            String str = TAG;
            io.e(str, registrationType + " is not currently supported to add secondary accounts ");
            return false;
        }
        return false;
    }

    static /* synthetic */ void a(i iVar, lb lbVar) {
        if (lbVar.getDeviceInfo() != null) {
            for (Map.Entry<String, String> entry : lbVar.getDeviceInfo().entrySet()) {
                String str = TAG;
                io.i(str, "device attribute received: " + entry.getKey() + " value: " + entry.getValue());
                iVar.w.g("device.metadata", entry.getKey(), entry.getValue());
            }
            return;
        }
        io.i(TAG, "device info attribute is null in register Response.");
    }

    static /* synthetic */ boolean a(i iVar, lb lbVar, j jVar) {
        if (jVar == null) {
            io.e(TAG, "Could not save credentials because no AccountRegistrarAuthenticator was given.");
            return false;
        } else if (!iVar.a(lbVar)) {
            io.e(TAG, "Was expecting anonymous credentials, but received account credentials");
            return false;
        } else {
            jVar.a(lbVar.hL(), lbVar.a());
            return true;
        }
    }
}
