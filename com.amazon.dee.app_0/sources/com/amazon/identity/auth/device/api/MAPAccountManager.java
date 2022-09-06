package com.amazon.identity.auth.device.api;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.dee.app.BuildConfig;
import com.amazon.deecomms.common.Constants;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.attribute.DeviceAttribute;
import com.amazon.identity.auth.device.bi;
import com.amazon.identity.auth.device.bl;
import com.amazon.identity.auth.device.bn;
import com.amazon.identity.auth.device.da;
import com.amazon.identity.auth.device.ed;
import com.amazon.identity.auth.device.ej;
import com.amazon.identity.auth.device.endpoint.OpenIdRequest;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazon.identity.auth.device.f;
import com.amazon.identity.auth.device.fo;
import com.amazon.identity.auth.device.g;
import com.amazon.identity.auth.device.hf;
import com.amazon.identity.auth.device.hw;
import com.amazon.identity.auth.device.in;
import com.amazon.identity.auth.device.io;
import com.amazon.identity.auth.device.iw;
import com.amazon.identity.auth.device.ji;
import com.amazon.identity.auth.device.m;
import com.amazon.identity.auth.device.mq;
import com.amazon.identity.auth.device.mv;
import com.amazon.identity.auth.device.mz;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class MAPAccountManager {
    @FireOsSdk
    public static final String ACCOUNT_ADDED_ON_DEVICE_INTENT_ACTION = "com.amazon.identity.auth.account.added.on.device";
    @FireOsSdk
    public static final String ACCOUNT_CHANGED_ON_DEVICE_INTENT_ACTION = "com.amazon.identity.action.ACCOUNT_CHANGED_ON_DEVICE";
    @FireOsSdk
    public static final String ACCOUNT_FOR_KEY_HAS_CHANGED_INTENT_ACTION = "com.amazon.identity.action.ACCOUNT_FOR_KEY";
    @FireOsSdk
    public static final String ACCOUNT_FOR_PACKAGE_HAS_CHANGED_INTENT_ACTION = "com.amazon.identity.action.ACCOUNT_FOR_PACKAGE_CHANGED";
    @FireOsSdk
    public static final String ACCOUNT_REMOVED_ON_DEVICE_INTENT_ACTION = "com.amazon.identity.auth.account.removed.on.device";
    @FireOsSdk
    public static final String KEY_3P_CALLBACK_QUERY_PARAM = "3pCallbackQuery";
    @FireOsSdk
    public static final String KEY_3P_LOGIN_URL_STRING = "thirdPartyLoginUrl";
    @FireOsSdk
    public static final String KEY_ACCESS_TOKEN = "com.amazon.dcp.sso.AddAccount.options.AccessToken";
    @FireOsSdk
    public static final String KEY_ACCOUNT_COR = "account_cor";
    @FireOsSdk
    public static final String KEY_ACCOUNT_MISSING_ATTRIBUTES = "AccountMissingAttributes";
    @FireOsSdk
    public static final String KEY_ACCOUNT_RECOVERY_BY_USING_NEW_ACCOUNT = "account_recovery_by_using_new_account";
    @FireOsSdk
    public static final String KEY_ACCOUNT_RECOVER_CONTEXT_BUNDLE = "com.amazon.identity.mobi.account.recover.context";
    @FireOsSdk
    public static final String KEY_ACCOUNT_STATUS_POLICY = "account_status_policy";
    @FireOsSdk
    public static final String KEY_ACTOR_ID_FOR_PREAUTHORIZED_LINK_CODE = "actor_id_for_preauthorized_link_code";
    @FireOsSdk
    public static final String KEY_ADDITIONAL_RETURN_TO_URL_PARAMETERS = "additionalReturnToUrlParams";
    @FireOsSdk
    public static final String KEY_ADDITIONAL_SIGNIN_DOMAINS = "signin_domains";
    @FireOsSdk
    public static final String KEY_ADD_ACCOUNT_AMAZON_DOMAIN = "com.amazon.identity.ap.domain";
    @FireOsSdk
    public static final String KEY_ADP_TOKEN = "adp_token";
    @FireOsSdk
    public static final String KEY_ALLOW_ADDING_MULTIPLE_ACCOUNT = "com.amazon.dcp.sso.AddAccount.options.AddAsSecondary";
    public static final String KEY_ALLOW_ADDING_MULTIPLE_PRIMARY_ACCOUNT = "com.amazon.dcp.sso.AddAccount.options.AddAsPrimary";
    @FireOsSdk
    public static final String KEY_ALLOW_ALL_SIGNIN_PATHS = "allow_all_signin_paths";
    @FireOsSdk
    public static final String KEY_AMAZON_ACCOUNT_LOGIN_NAME = "authAccount";
    @FireOsSdk
    public static final String KEY_AMAZON_ACCOUNT_PASSWORD = "password";
    @FireOsSdk
    public static final String KEY_AMAZON_DOMAIN = "com.amazon.identity.ap.domain";
    @FireOsSdk
    @Deprecated
    public static final String KEY_AP_ASSOC_HANDLE = "com.amazon.identity.ap.assoc_handle";
    @FireOsSdk
    @Deprecated
    public static final String KEY_AP_PAGEID = "com.amazon.identity.ap.pageid";
    @FireOsSdk
    @Deprecated
    public static final String KEY_AT_MAIN = "com.amazon.dcp.sso.AddAccount.options.ATMain";
    @FireOsSdk
    public static final String KEY_AUTHENTICATION_CHALLENGE = "com.amazon.identity.auth.ChallengeException";
    @FireOsSdk
    public static final String KEY_AUTH_DATA_ADDITIONAL_INFO = "auth_data_additional_info";
    @FireOsSdk
    public static final String KEY_AUTH_TOKEN = "com.amazon.dcp.sso.AddAccount.options.AuthToken";
    @FireOsSdk
    public static final String KEY_AUTH_TOKEN_CONTEXT = "com.amazon.dcp.sso.AddAccount.options.AuthTokenClientContext";
    @FireOsSdk
    public static final String KEY_CLAIM_TYPE = "claim_type";
    @FireOsSdk
    public static final String KEY_CLIENT_EVENT_CONTEXT = "com.amazon.dcp.sso.extra.client_event_context";
    @FireOsSdk
    public static final String KEY_CLIENT_EVENT_CONTEXT_NAMESPACE = "com.amazon.dcp.sso.extra.client_event_context.namespace";
    @FireOsSdk
    public static final String KEY_CLIENT_EVENT_CONTEXT_PROPERTIES = "com.amazon.dcp.sso.extra.client_event_context.properties";
    @FireOsSdk
    public static final String KEY_COLOR_CODE_FOR_FEDERATED_CUSTOM_TAB = "color_code";
    @FireOsSdk
    public static final String KEY_DEREGISTERALL_AND_REGISTER_THIS_AS_PRIMARY_ACCOUNT = "deregisterall_register_this_as_primary";
    @FireOsSdk
    public static final String KEY_DEREGISTRATION_METADATA = "deregistration_metadata";
    @FireOsSdk
    public static final String KEY_DEVICE_NAME = "device_name";
    @FireOsSdk
    public static final String KEY_DIRECTED_ID = "com.amazon.dcp.sso.property.account.acctId";
    @FireOsSdk
    public static final String KEY_DIRECTED_ID_CONFIRM = "directed_id_confirm";
    @FireOsSdk
    public static final String KEY_DIRECTED_ID_DELEGATEE = "com.amazon.dcp.sso.property.account.delegateeaccount";
    @FireOsSdk
    public static final String KEY_DIRECTED_ID_POST_ACCOUNT_CHANGE = "key_directed_id_post_account_change";
    @FireOsSdk
    public static final String KEY_DISABLE_GLOBAL_SIGNIN = "disable_global_signin";
    @FireOsSdk
    public static final String KEY_DISABLE_REGISTER_WITHUI_PRE_POPULATION = "disable_user_name_pre_population";
    @FireOsSdk
    public static final String KEY_DISABLE_USERNAME_AUTO_SUGGESTION = "disable_user_name_auto_suggestion";
    @FireOsSdk
    public static final String KEY_DMS_GET_CREDENTIALS_URL = "com.amazon.dcp.sso.AddAccount.options.URL";
    @FireOsSdk
    public static final String KEY_ENSURE_ACCOUNT_STATE_ATTRIBUTES = "EnsureAccountStateAttributes";
    @FireOsSdk
    public static final String KEY_ERROR_CODE = "com.amazon.dcp.sso.ErrorCode";
    @FireOsSdk
    public static final String KEY_ERROR_CODE_WEBVIEW_SSL_ERROR = "com.amazon.identity.WebViewSSLErrorCode";
    @FireOsSdk
    public static final String KEY_ERROR_DOMAIN_PATH_WEBVIEW_SSL_ERROR = "com.amazon.identity.WebViewSSLErrorDomainPath";
    @FireOsSdk
    public static final String KEY_ERROR_MESSAGE = "com.amazon.dcp.sso.ErrorMessage";
    @FireOsSdk
    @Deprecated
    public static final String KEY_EXTRA_DIRECTED_ID = "com.amazon.dcp.sso.extra.account.directed_id";
    @FireOsSdk
    public static final String KEY_EXTRA_KEY = "account_key";
    @FireOsSdk
    public static final String KEY_EXTRA_PROFILES_ACCOUNT_ADDED_OR_REMOVED_BELONG_TO = "com.amazon.identity.auth.extra.account.profiles";
    @FireOsSdk
    public static final String KEY_FEDERATED_AUTH_CONFIG = "federated_auth_config";
    @FireOsSdk
    public static final String KEY_GET_LINK_CODE_WITH_LENGTH = "get_link_code_with_length";
    @FireOsSdk
    public static final String KEY_IGNORE_NAME_FOR_ISOLATED_APP = "ignore_name_for_isolated_app";
    @FireOsSdk
    public static final String KEY_INTENT = "intent";
    @FireOsSdk
    public static final String KEY_IS_CALLBACK_FROM_3P_PARAM = "isCallbackFrom3pLogin";
    @FireOsSdk
    public static final String KEY_IS_NEW_ACCOUNT = "com.amazon.identity.auth.device.accountManager.newaccount";
    @FireOsSdk
    public static final String KEY_LINK_CODE = "link_code";
    @FireOsSdk
    public static final String KEY_LINK_CODE_DOMAIN = "link_code_domain";
    @FireOsSdk
    public static final String KEY_LINK_CODE_POLLING_INTERVAL = "link_code_polling_interval";
    @FireOsSdk
    public static final String KEY_LINK_CODE_TIME_TO_LIVE = "link_code_expiry";
    @FireOsSdk
    public static final String KEY_MARKETPLACE_BUNDLE = "marketplace_bundle";
    @FireOsSdk
    public static final String KEY_MARKETPLACE_DOMAIN = "marketplace_domain";
    @FireOsSdk
    public static final String KEY_OVERRIDE_RETURN_TO_DOMAIN = "return_to_domain";
    @FireOsSdk
    public static final String KEY_PRE_AUTHORIZED_LINK_CODE = "pre_authorized_link_code";
    @FireOsSdk
    public static final String KEY_PRIMARY_DIRECTED_ID = "com.amazon.dcp.sso.property.account.primary.acctId";
    @FireOsSdk
    public static final String KEY_PRIVATE_KEY = "adp_private_key";
    @FireOsSdk
    public static final String KEY_PROFILE_MAPPING = "profile_mapping";
    @FireOsSdk
    public static final String KEY_REGISTRATION_COOKIE_DOMAIN = "key_registration_cookie_domain";
    @FireOsSdk
    public static final String KEY_REGISTRATION_DOMAIN = "registration_domain";
    @FireOsSdk
    public static final String KEY_RESULT_CODE = "result code";
    @FireOsSdk
    public static final String KEY_RESUME_AUTHENTICATION_URL = "resume_authentication_url";
    @FireOsSdk
    public static final String KEY_SERVER_SIDE_DEREGISTRATION_RESULT = "server_side_deregistration_result";
    @FireOsSdk
    public static final String KEY_SIGN_IN_ENDPOINT = "key_sign_in_full_endpoint";
    @FireOsSdk
    public static final String KEY_SSO_ACCOUNTS_LIST = "ssoAccountsList";
    @FireOsSdk
    public static final String KEY_SSO_ACCOUNT_CUSTOMER_NAME = "accountCustomerName";
    @FireOsSdk
    public static final String KEY_SSO_ACCOUNT_DIRECTED_ID = "accountDirectedId";
    @FireOsSdk
    public static final String KEY_SSO_ACCOUNT_LOGIN = "accountLoginName";
    @FireOsSdk
    public static final String KEY_SSO_BOOTSTRAPPED_FROM_DEVICE_SERIAL = "bootstrapHostDSN";
    @FireOsSdk
    public static final String KEY_SSO_BOOTSTRAPPED_FROM_DEVICE_TYPE = "bootstrapHostDeviceType";
    @FireOsSdk
    public static final String KEY_SSO_CODE = "ssoCode";
    @FireOsSdk
    public static final String KEY_SSO_CODE_TIME_TO_LIVE = "ssoCodeExpiresIn";
    @FireOsSdk
    public static final String PRIMARY_AMAZON_ACCOUNT_ADDED_INTENT_ACTION = "com.amazon.dcp.sso.action.account.added";
    @FireOsSdk
    public static final String PRIMARY_AMAZON_ACCOUNT_REMOVED_INTENT_ACTION = "com.amazon.dcp.sso.action.account.removed";
    @FireOsSdk
    public static final String SECONDARY_AMAZON_ACCOUNT_ADDED_INTENT_ACTION = "com.amazon.dcp.sso.action.secondary.account.added";
    @FireOsSdk
    public static final String SECONDARY_AMAZON_ACCOUNT_REMOVED_INTENT_ACTION = "com.amazon.dcp.sso.action.secondary.account.removed";
    private final Object[] fD = new Object[0];
    private f gg;
    private final ed o;
    private static final Set<String> gf = new HashSet(Arrays.asList("com.amazon.map.sample.one", "com.amazon.map.sample.two", "com.amazon.map.sample", "com.amazon.map.client.sample.three", "com.amazon.kindle.otter.oobe", "com.amazon.kindle.otter.settings", "com.amazon.avod", "com.amazon.alta.h2debug", "com.amazon.venezia", "com.amazon.kor.demo", "com.amazon.h2settingsfortablet", "com.amazon.tv.oobe", "com.googlecode.android_scripting", "com.amazon.aiv.us", "com.amazon.aiv.eu", "com.amazon.aiv.fe", "com.amazon.aiv.blast", "com.amazon.asxr", "com.android.settings", "com.amazon.alexa.multimodal.tv", "com.amazon.demoman.app.android"));
    private static final String TAG = MAPAccountManager.class.getName();
    private static final String fL = MAPAccountManager.class.getSimpleName();

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static final class AuthPortalActivityUIOptions {
        @FireOsSdk
        public static final String KEY_ABOVE_LOCKSCREEN = "AuthPortalActivityUIOptions.aboveLockScreen";
        @Deprecated
        public static final String KEY_AMAZON_SCREEN_MODES = "AuthPortalActivityUIOptions.amazonScreenModes";
        @FireOsSdk
        public static final String KEY_INVERT_SPINNER = "progressbar_invert_spinner";
        @FireOsSdk
        public static final String KEY_IS_FULLSCREEN = "AuthPortalActivityUIOptions.fullscreen";
        @FireOsSdk
        public static final String KEY_PROGRESSBAR_BACKGROUND_RESOURCE = "progressbar_background_resource";
        @FireOsSdk
        public static final String KEY_PROGRESSBAR_FADE = "progressbar_fade";
        @FireOsSdk
        public static final String KEY_PROGRESSBAR_POSITION = "progressbar_position";
        @FireOsSdk
        public static final String KEY_PROGRESSBAR_PRIMARY_COLOR = "progressbar_primary_color";
        @FireOsSdk
        public static final String KEY_PROGRESSBAR_RESOURCE = "progressbar_resource";
        @FireOsSdk
        public static final String KEY_PROGRESSBAR_SECONDARY_COLOR = "progressbar_secondary_color";
        @FireOsSdk
        public static final String KEY_PROGRESSBAR_STATE = "progressbar_state";
        @FireOsSdk
        public static final String KEY_PROGRESSBAR_STRETCH = "progressbar_stretch";
        @FireOsSdk
        public static final String KEY_REQUESTED_ORIENTATION = "AuthPortalActivityUIOptions.requestedOrientation";
        @FireOsSdk
        public static final String KEY_SPLASH_SCREEN_RESOURCE = "splashscreen_resource";
        @FireOsSdk
        public static final String KEY_SPLASH_SCREEN_SCALE_TYPE = "splashscreen_scale_type";
        @FireOsSdk
        public static final String KEY_SYSTEM_UI_VISIBILITY = "AuthPortalActivityUIOptions.systemUiVisibility";
        @FireOsSdk
        public static final String KEY_WINDOW_FLAGS = "AuthPortalActivityUIOptions.windowFlags";

        /* compiled from: DCP */
        /* loaded from: classes12.dex */
        public enum ProgressBarState {
            OFF("off"),
            PROGRESS_BAR("progress_bar"),
            SPINNER_SMALL("spinner_small"),
            SPINNER_MEDIUM("spinner_medium"),
            SPINNER_LARGE("spinner_large");
            
            private String mValue;

            ProgressBarState(String str) {
                this.mValue = str;
            }

            public static ProgressBarState get(String str) {
                ProgressBarState[] values;
                for (ProgressBarState progressBarState : values()) {
                    if (progressBarState.getValue().equals(str)) {
                        return progressBarState;
                    }
                }
                io.c(MAPAccountManager.TAG, "Invalid ProgressBarState value: %s", str);
                return null;
            }

            public String getValue() {
                return this.mValue;
            }

            @Override // java.lang.Enum
            @FireOsSdk
            public String toString() {
                return getValue();
            }
        }

        /* compiled from: DCP */
        /* loaded from: classes12.dex */
        public enum ScreenPosition {
            TOP_LEFT("top_left"),
            TOP_CENTER("top_center"),
            TOP_RIGHT("top_right"),
            CENTER_LEFT("center_left"),
            CENTER_CENTER("center_center"),
            CENTER_RIGHT("center_right"),
            BOTTOM_LEFT("bottom_left"),
            BOTTOM_CENTER("bottom_center"),
            BOTTOM_RIGHT("bottom_right");
            
            private String mValue;

            ScreenPosition(String str) {
                this.mValue = str;
            }

            public static ScreenPosition get(String str) {
                ScreenPosition[] values;
                for (ScreenPosition screenPosition : values()) {
                    if (screenPosition.getValue().equals(str)) {
                        return screenPosition;
                    }
                }
                io.c(MAPAccountManager.TAG, "Invalid ScreenPosition value: %s", str);
                return null;
            }

            public String getValue() {
                return this.mValue;
            }

            @Override // java.lang.Enum
            @FireOsSdk
            public String toString() {
                return getValue();
            }
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static final class AuthPortalOptions {
        public static final String KEY_ADDITIONAL_SIGN_IN_PARAMETERS = "com.amazon.identity.ap.additionalSignInParameters";
        @FireOsSdk
        @Deprecated
        public static final String KEY_ASSOCIATION_HANDLE = "com.amazon.identity.ap.assoc_handle";
        @FireOsSdk
        @Deprecated
        public static final String KEY_CLIENT_CONTEXT = "com.amazon.identity.ap.clientContext";
        @FireOsSdk
        public static final String KEY_DOMAIN = "com.amazon.identity.ap.domain";
        @FireOsSdk
        @Deprecated
        public static final String KEY_PAGEID = "com.amazon.identity.ap.pageid";
        public static final String KEY_PREFIX = "com.amazon.identity.ap";
        @FireOsSdk
        public static final String KEY_REQUEST_PARAMETERS = "com.amazon.identity.ap.request.parameters";
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public enum BootstrapError {
        NO_SERVICE_AVAILABLE(0, "NoServiceAvailable"),
        TIMEOUT_SERVICE(1, "NoResponseReceived"),
        NO_ACCOUNT(2, "NoAccount"),
        NO_SIGNATURE(3, "NoSignatureObtained"),
        INVALID_RESPONSE(4, "InvalidResponseFromServer"),
        NETWORK_FAILURE(5, "NetworkFailed"),
        PARSE_ERROR(6, "ErrorParsingResponse"),
        BOOTSTRAP_NOT_ALLOWED(7, "BootstrapNotAllowed"),
        SERVICE_ERROR(8, "ServiceError"),
        FRAUDULENT_PACKAGE(9, "FraudulentPackage"),
        UNCATEGORIZED_ERROR(10, "UncategorizedError");
        
        private final String mName;
        private final int mValue;

        BootstrapError(int i, String str) {
            this.mValue = i;
            this.mName = str;
        }

        @FireOsSdk
        public static BootstrapError fromValue(int i) {
            BootstrapError fromValueHelper = fromValueHelper(i);
            if (fromValueHelper != null) {
                return fromValueHelper;
            }
            return null;
        }

        private static BootstrapError fromValueHelper(int i) {
            BootstrapError[] values;
            for (BootstrapError bootstrapError : values()) {
                if (bootstrapError.value() == i) {
                    return bootstrapError;
                }
            }
            return null;
        }

        @FireOsSdk
        public String getName() {
            return this.mName;
        }

        @FireOsSdk
        public int value() {
            return this.mValue;
        }

        @FireOsSdk
        public static BootstrapError fromValue(int i, BootstrapError bootstrapError) {
            BootstrapError fromValueHelper = fromValueHelper(i);
            return fromValueHelper != null ? fromValueHelper : bootstrapError;
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public interface MAPAccountChangeObserver {
        @FireOsSdk
        void onAccountChange(AccountChangeEvent accountChangeEvent);
    }

    @FireOsSdk
    public MAPAccountManager(Context context) {
        MAPInit.getInstance(context).initialize();
        this.o = ed.M(context);
    }

    private MAPFuture<Bundle> a(bl blVar, String str) {
        Bundle bundle = new Bundle();
        bundle.putInt("com.amazon.dcp.sso.ErrorCode", RegistrationError.BAD_REQUEST.value());
        bundle.putString("com.amazon.dcp.sso.ErrorMessage", str);
        bundle.putInt(MAPError.KEY_ERROR_CODE, MAPError.CommonError.BAD_REQUEST.getErrorCode());
        bundle.putString(MAPError.KEY_ERROR_MESSAGE, str);
        bundle.putString(MAPError.KEY_ERROR_TYPE, MAPError.CommonError.BAD_REQUEST.getErrorType());
        blVar.onError(bundle);
        return blVar;
    }

    private void aF(String str) {
        io.e(TAG, str);
        throw new IllegalArgumentException(str);
    }

    private boolean b(Context context, Bundle bundle, Bundle bundle2, Callback callback) {
        if (bundle == null) {
            callback.onError(m.a(MAPError.CommonError.BAD_REQUEST, "Cannot recover an account with a null recovery context.", RegistrationError.BAD_REQUEST.value(), "Cannot recover an account with a null recovery context."));
            return false;
        }
        String string = bundle.getString("com.amazon.dcp.sso.property.account.acctId");
        String str = TAG;
        "Trying to recover account for directed-id : ".concat(String.valueOf(string));
        io.dm(str);
        if (TextUtils.isEmpty(string)) {
            callback.onError(m.a(MAPError.CommonError.BAD_REQUEST, "Cannot recover account for an empty directedId.", RegistrationError.BAD_REQUEST.value(), "Cannot recover account for an empty directedId."));
            return false;
        } else if (!isAccountRegistered(string)) {
            String format = String.format("Customer %s is not registered. Unable to recover account.", string);
            callback.onError(m.a(MAPError.AccountError.CUSTOMER_NOT_FOUND, format, RegistrationError.CUSTOMER_NOT_FOUND.value(), format));
            return false;
        } else if (context != null || (bundle2 != null && (bundle2.containsKey(KEY_LINK_CODE) || bundle2.containsKey(KEY_PRE_AUTHORIZED_LINK_CODE)))) {
            return true;
        } else {
            callback.onError(hf.a(MAPError.CommonError.BAD_REQUEST, "Null context reference passed. Cannot trigger a recover account confirm credential flow.", RegistrationError.BAD_REQUEST, "Null context reference passed. Cannot trigger a recover account confirm credential flow."));
            return false;
        }
    }

    private f bi() {
        f fVar;
        synchronized (this.fD) {
            if (this.gg == null) {
                this.gg = g.a(this.o);
            }
            fVar = this.gg;
        }
        return fVar;
    }

    static /* synthetic */ void d(Bundle bundle, Bundle bundle2) {
        String x = EnvironmentUtils.cd().x(bundle);
        String bd = EnvironmentUtils.cd().bd(x);
        if (x.equals(BuildConfig.AUTH_HOST) || x.equals(Constants.AUTH_DOMAIN_DEVELOPMENT) || x.equals("pre-prod.amazon.com")) {
            return;
        }
        Bundle bundle3 = bundle2.getBundle(AuthPortalOptions.KEY_REQUEST_PARAMETERS);
        if (bundle3 == null) {
            bundle3 = new Bundle();
        }
        if (TextUtils.isEmpty(bundle3.getString("openid.assoc_handle"))) {
            if (!TextUtils.isEmpty(bundle2.getString("com.amazon.identity.ap.assoc_handle"))) {
                bundle3.putString("openid.assoc_handle", bundle2.getString("com.amazon.identity.ap.assoc_handle"));
            } else {
                if (TextUtils.isEmpty(bd)) {
                    bd = "amzn_device_android";
                }
                bundle3.putString("openid.assoc_handle", bd);
            }
        }
        if (TextUtils.isEmpty(bundle3.getString("pageId"))) {
            if (!TextUtils.isEmpty(bundle2.getString("com.amazon.identity.ap.pageid"))) {
                bundle3.putString("pageId", bundle2.getString("com.amazon.identity.ap.pageid"));
            } else {
                bundle3.putString("pageId", "amzn_device_common_dark");
            }
        }
        bundle2.putBundle(AuthPortalOptions.KEY_REQUEST_PARAMETERS, bundle3);
    }

    private boolean s(Bundle bundle) {
        return bundle != null && bundle.containsKey(KEY_RESUME_AUTHENTICATION_URL) && !TextUtils.isEmpty(bundle.getString(KEY_RESUME_AUTHENTICATION_URL));
    }

    private Bundle t(Bundle bundle) {
        Bundle K = hw.K(bundle);
        K.putString("calling_package", this.o.getPackageName());
        K.putInt("calling_profile", da.cz());
        return K;
    }

    private String u(Bundle bundle) {
        if (bundle != null) {
            if (bundle.getString(KEY_CLIENT_EVENT_CONTEXT_NAMESPACE) == null) {
                return "No namespace provided in the client event context";
            }
            if (bundle.getBundle(KEY_CLIENT_EVENT_CONTEXT_PROPERTIES) != null) {
                return null;
            }
            return "No properties provided in the client event context";
        }
        return null;
    }

    @Deprecated
    public MAPFuture<Bundle> authenticateAccount(Bundle bundle, Callback callback) {
        bl blVar = new bl(callback);
        ej by = ej.by("AuthenticateAccountWithoutActivity");
        Bundle t = t(bundle);
        if (gf.contains(this.o.getPackageName())) {
            bi().a(t, mq.a(by, by.ea(), blVar, this.o), by);
            return blVar;
        }
        return a(blVar, "This API has been deprecated. Please use our new API MAPAccountManager.authenticateAccount(Activity, SigninOptions, Bundle, Bundle, Callback). Please refer to the authenticateAccount API Java doc for more details.");
    }

    @FireOsSdk
    public MAPFuture<Bundle> authenticateAccountWithUI(Activity activity, SigninOption signinOption, Bundle bundle, Callback callback) {
        bl f = bl.f(callback);
        ej by = ej.by(a(bundle, "AuthenticateAccountWithUI:" + signinOption.name()));
        bi().b(activity, signinOption, bundle, mq.a(by, by.ea(), callback, this.o, s(bundle)), by);
        return f;
    }

    @FireOsSdk
    public MAPFuture<Bundle> authorizeLinkCode(Bundle bundle, Callback callback) {
        ej by = ej.by("AuthorizeLinkCode");
        mv ea = by.ea();
        if (bundle == null) {
            bundle = new Bundle();
        }
        bl f = bl.f(callback);
        new bn(this.o).f(bundle, mq.a(by, ea, f, this.o), by);
        return f;
    }

    @FireOsSdk
    public MAPFuture<Bundle> bootStrapSSO(Callback callback) {
        ej by = ej.by("bootstrapSSO");
        mv ea = by.ea();
        bl f = bl.f(callback);
        new bi(this.o, mq.a(by, ea, f)).br();
        return f;
    }

    @Deprecated
    public MAPFuture<Bundle> confirmCredential(Activity activity, String str, Bundle bundle, Callback callback) {
        bl blVar = new bl(callback);
        ej by = ej.by("DeregisterDevice");
        bi().a(activity, str, bundle, mq.a(by, by.ea(), blVar, this.o), by);
        return blVar;
    }

    @FireOsSdk
    public MAPFuture<Bundle> deregisterAccount(String str, Callback callback) {
        return deregisterAccount(str, callback, null);
    }

    @FireOsSdk
    public void deregisterAccountChangeObserver(MAPAccountChangeObserver mAPAccountChangeObserver) {
        ej by = ej.by("UnregisterAccountChangeObserver");
        mv ea = by.ea();
        fo.b(this.o, mAPAccountChangeObserver);
        ea.stop();
        by.eb();
    }

    @FireOsSdk
    public MAPFuture<Bundle> deregisterDevice(Callback callback) {
        return deregisterDevice(callback, null);
    }

    @FireOsSdk
    public MAPFuture<Bundle> ensureAccountState(Activity activity, String str, Bundle bundle, Callback callback) {
        bl f = bl.f(callback);
        ej by = ej.by("EnsureAccountState");
        mv ea = by.ea();
        if (activity == null) {
            aF("For ensureAccountState activity can not be null");
        } else if (TextUtils.isEmpty(str)) {
            aF("For ensureAccountState directedId can not be null");
        } else if (bundle == null) {
            aF("For ensureAccountState options can not be null");
        } else {
            String string = bundle.getString("com.amazon.identity.ap.domain");
            Bundle bundle2 = bundle.getBundle(AuthPortalOptions.KEY_REQUEST_PARAMETERS);
            if (bundle2 != null && !bundle2.isEmpty()) {
                String string2 = bundle2.getString("openid.assoc_handle");
                ArrayList<String> stringArrayList = bundle.getStringArrayList(KEY_ENSURE_ACCOUNT_STATE_ATTRIBUTES);
                if (TextUtils.isEmpty(string)) {
                    aF("For ensureAccountState domain can not be null");
                } else if (TextUtils.isEmpty(string2)) {
                    aF("For ensureAccountState associationHandle can not be null");
                } else if (stringArrayList == null || stringArrayList.isEmpty()) {
                    aF("For ensureAccountState assertExistingAttributes can not be null");
                }
            } else {
                aF("For ensureAccountState requestParameters which contains associationHandle can not be null");
            }
        }
        if (!isAccountRegistered(str)) {
            io.e(TAG, "The given account is not registered");
            String format = String.format("Given directedId %s is not registered.", str);
            callback.onError(m.a(MAPError.AccountError.CUSTOMER_NOT_FOUND, format, RegistrationError.CUSTOMER_NOT_FOUND.value(), format));
            by.eb();
        } else {
            bi().b(activity, str, bundle, mq.a(by, ea, f, this.o, true), by);
        }
        return f;
    }

    @Deprecated
    public String ensureAmazonAccount(Activity activity) {
        synchronized (this.fD) {
            if (!isDeviceRegistered()) {
                io.i(TAG, "No amazon account found. Try to create one.");
                if (!ji.gP()) {
                    try {
                        registerAccountWithUI(activity, SigninOption.WebviewSignin, null, null).get();
                        return getAccount();
                    } catch (Exception e) {
                        io.e(TAG, "Got exception when creating amazon account.", e);
                        return null;
                    }
                }
                io.e(TAG, "Invoked on main thread.");
                throw new IllegalStateException("Do not run on main thread.");
            }
            io.i(TAG, "Already have an amazon account.");
            return getAccount();
        }
    }

    @FireOsSdk
    public String generateCNEPUrl(Context context, Bundle bundle) {
        Uri parse;
        mq.incrementCounterAndRecord("MAPGenerateCNEPUrl:" + this.o.getPackageName(), new String[0]);
        String m = OpenIdRequest.m(mz.bl(context), iw.c(context, DeviceAttribute.CentralDeviceType));
        String string = bundle.getString(KEY_DIRECTED_ID_CONFIRM);
        OpenIdRequest openIdRequest = new OpenIdRequest(m, OpenIdRequest.REQUEST_TYPE.CNEP, bundle);
        String bS = openIdRequest.bS();
        if (TextUtils.isEmpty(string) || (parse = Uri.parse(bS)) == null) {
            return bS;
        }
        openIdRequest.aR(parse.getScheme() + "://" + parse.getHost() + "/ap/id/" + string);
        return openIdRequest.bS();
    }

    @FireOsSdk
    public String generateForgotPasswordUrl(Bundle bundle) {
        mq.incrementCounterAndRecord("MAPGenerateForgotPasswordUrl:" + this.o.getPackageName(), new String[0]);
        ej by = ej.by("generateForgotPasswordUrl");
        mv ea = by.ea();
        try {
            return new OpenIdRequest(null, OpenIdRequest.REQUEST_TYPE.FORGOT_PASSWORD, bundle).bS();
        } finally {
            ea.stop();
            by.eb();
        }
    }

    @FireOsSdk
    public MAPFuture<Bundle> generateLinkCode(Bundle bundle, Callback callback) {
        ej by = ej.by("GenerateLinkCode");
        mv ea = by.ea();
        if (bundle == null) {
            bundle = new Bundle();
        }
        bl f = bl.f(callback);
        new bn(this.o).d(bundle, mq.a(by, ea, f, this.o), by);
        return f;
    }

    @FireOsSdk
    public MAPFuture<Bundle> generatePreAuthorizedLinkCode(Bundle bundle, Callback callback) {
        ej by = ej.by("GeneratePreAuthorizedLinkCode");
        mv ea = by.ea();
        if (bundle == null) {
            bundle = new Bundle();
        }
        bl f = bl.f(callback);
        new bn(this.o).e(bundle, mq.a(by, ea, f, this.o), by);
        return f;
    }

    @FireOsSdk
    public String getAccount() {
        mv aE = mq.aE(fL, "getAccount");
        try {
            return bi().r(this.o.getPackageName());
        } finally {
            aE.stop();
        }
    }

    @FireOsSdk
    public Set<String> getAccounts() {
        mv aE = mq.aE(fL, "getAccounts");
        try {
            return bi().getAccounts();
        } finally {
            aE.stop();
        }
    }

    @FireOsSdk
    @Deprecated
    public String getPrimaryAccount() {
        mv aE = mq.aE(fL, "getPrimaryAccount");
        try {
            return bi().getPrimaryAccount();
        } finally {
            aE.stop();
        }
    }

    @FireOsSdk
    public boolean isAccountRegistered(String str) {
        mv aE = mq.aE(fL, "isAccountRegistered");
        try {
            return bi().isAccountRegistered(str);
        } finally {
            aE.stop();
        }
    }

    @FireOsSdk
    @Deprecated
    public boolean isDeviceRegistered() {
        mv aE = mq.aE(fL, "isDeviceRegistered");
        try {
            return bi().isDeviceRegistered();
        } finally {
            aE.stop();
        }
    }

    @FireOsSdk
    public MAPFuture<Bundle> recoverAccount(Context context, Bundle bundle, Bundle bundle2, Callback callback) {
        return a(context, bundle, bundle2, callback);
    }

    @FireOsSdk
    public MAPFuture<Bundle> registerAccount(RegistrationType registrationType, Bundle bundle, Callback callback) {
        ej by = ej.by("RegisterAccountWithoutActivity:".concat(String.valueOf(mq.b(registrationType))));
        bl f = bl.f(callback);
        if ((RegistrationType.WITH_LOGIN_CREDENTIALS == registrationType || RegistrationType.WITH_DIRECTEDID_CREDENTIALS == registrationType || RegistrationType.WITH_PRIMARY_DIRECTEDID_CREDENTIALS == registrationType) && !gf.contains(this.o.getPackageName())) {
            return a(f, "Invalid RegistrationType. RegisterAccount API with RegistrationType:" + registrationType.getName() + " has been removed. Please use our new API MAPAccountManager.registerAccount(Activity, Bundle, Bundle, Callback). Please refer to the registerAccount API Java doc for more details.");
        }
        if (RegistrationType.WITH_LINK_CODE == registrationType) {
            String string = bundle.getString(KEY_LINK_CODE);
            String string2 = bundle.getString(KEY_PRE_AUTHORIZED_LINK_CODE);
            if (TextUtils.isEmpty(string) && TextUtils.isEmpty(string2)) {
                io.i(TAG, "No link code passed in the registerAccount API WITH_LINK_CODE");
                return a(f, "A required parameter link code was not passed in the API. Call MAPAccountManager#generateLinkCode() or MAPAccountManager#generatePreAuthorizedLinkCode() to get a link code based on your use-case and pass them in the key MAPAccountManager#KEY_LINK_CODE or MAPAccountManager#KEY_PRE_AUTHORIZED_LINK_CODE respectively.");
            } else if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
                io.i(TAG, "Multiple link code keys set");
                return a(f, "You cannot set both the keys MAPAccountManager#KEY_LINK_CODE and MAPAccountManager#KEY_PRE_AUTHORIZED_LINK_CODE. Based on the type of link code you have, set the appropriate key as specified in the javadoc of RegistrationType#WITH_LINK_CODE.");
            } else if (!TextUtils.isEmpty(string)) {
                bn.a q = bn.q(this.o);
                if (q == null) {
                    return a(f, "The link code that MAP had has expired or it has not been generated yet. Please call MAPAccountManager#generateLinkCode to generate the link code.");
                }
                if (!q.hs.equals(string)) {
                    return a(f, "The link code does not match the one that MAP has. Please call MAPAccountManager#generateLinkCode to get the link code.");
                }
                f = bn.a(this.o, f);
                bundle.putString(AccountConstants.KEY_CBL_PUBLIC_CODE, q.hs);
                bundle.putString(AccountConstants.KEY_CBL_PRIVATE_CODE, q.ht);
            }
        }
        mv ea = by.ea();
        bi().a(registrationType, t(bundle), mq.a(by, ea, f, this.o), by);
        return f;
    }

    @FireOsSdk
    public void registerAccountChangeObserver(MAPAccountChangeObserver mAPAccountChangeObserver) {
        ej by = ej.by("RegisterAccountChangeObserver");
        mv ea = by.ea();
        fo.a(this.o, mAPAccountChangeObserver);
        ea.stop();
        by.eb();
    }

    @FireOsSdk
    public MAPFuture<Bundle> registerAccountWithUI(Activity activity, SigninOption signinOption, Bundle bundle, Callback callback) {
        in.a(signinOption, "option");
        ej by = ej.by(a(bundle, "RegisterAccountWithUI:" + signinOption.name()));
        mv ea = by.ea();
        bl f = bl.f(callback);
        bi().a(activity, signinOption, bundle, mq.a(by, ea, f, this.o, s(bundle)), by);
        return f;
    }

    @Deprecated
    public MAPFuture<Bundle> registerDelegatedAccount(String str, String str2, Bundle bundle, Callback callback) {
        bl f = bl.f(callback);
        ej by = ej.by("registerDelegatedAccount");
        mv bz = by.bz("registerDelegatedAccount_totalTime");
        by.bA("registerDelegatedAccount_count");
        io.i(TAG, "registerDelegatedAccount() is deprecated. Please use registerAccount().");
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString("com.amazon.dcp.sso.property.account.acctId", str2);
        bundle.putString("com.amazon.dcp.sso.property.account.delegateeaccount", str);
        bundle.putBoolean("com.amazon.dcp.sso.AddAccount.options.AddAsSecondary", true);
        bi().a(RegistrationType.REGISTER_DELEGATED_ACCOUNT, bundle, mq.a(by, bz, f, this.o), by);
        return f;
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public enum RegistrationError {
        ACCOUNT_ALREADY_EXISTS(0, "AccountAlreadyExists"),
        NETWORK_FAILURE(1, "NetworkFailure"),
        AUTHENTICATION_FAILED(2, "AuthenticationFailed"),
        PARSE_ERROR(3, "ParseError"),
        CUSTOMER_NOT_FOUND(4, "CustomerNotFound"),
        DEVICE_ALREADY_REGISTERED(5, "DeviceAlreadyRegistered"),
        DUPLICATE_DEVICE_NAME(6, "DuplicateDeviceName"),
        DEREGISTER_FAILED(7, "DeregisterFailed"),
        UNRECOGNIZED(8, "Unrecognized"),
        REGISTER_FAILED(9, "RegisterFailed"),
        BAD_REQUEST(10, "BadRequest"),
        ALREADY_DEREGISTERED(11, "AlreadyDeregistered"),
        BAD_SECRET(12, "BadSecret"),
        NO_ACCOUNT(13, "NoAccount"),
        UI_NOT_FOUND(14, "UINotFound"),
        DELEGATEE_ACCOUNT_ALREADY_DEREGISTERED(15, "DelegateeAccountAlreadyDeregistered"),
        AUTHENTICATION_CHALLENGED(16, "AuthenticationChallenged"),
        INTERNAL_ERROR(17, "InternalError"),
        REQUIRED_3P_AUTHENTICATION(18, "Required3PAuthentication"),
        ACTOR_NOT_ASSOCIATED(19, "ActorNotAssociated"),
        LEGACY_ERROR_CODE_NOT_SUPPORTED_ERROR(20, "LegacyErrorCodeNotSupportedError");
        
        private final String mName;
        private final int mValue;

        RegistrationError(int i, String str) {
            this.mValue = i;
            this.mName = str;
        }

        @FireOsSdk
        public static RegistrationError fromValue(int i) {
            RegistrationError fromValueHelper = fromValueHelper(i);
            if (fromValueHelper != null) {
                return fromValueHelper;
            }
            throw new IndexOutOfBoundsException();
        }

        private static RegistrationError fromValueHelper(int i) {
            RegistrationError[] values;
            for (RegistrationError registrationError : values()) {
                if (registrationError.value() == i) {
                    return registrationError;
                }
            }
            return null;
        }

        @FireOsSdk
        public String getName() {
            return this.mName;
        }

        @FireOsSdk
        public int value() {
            return this.mValue;
        }

        @FireOsSdk
        public static RegistrationError fromValue(int i, RegistrationError registrationError) {
            RegistrationError fromValueHelper = fromValueHelper(i);
            return fromValueHelper != null ? fromValueHelper : registrationError;
        }
    }

    @FireOsSdk
    public MAPFuture<Bundle> deregisterAccount(String str, Callback callback, Bundle bundle) {
        String u;
        io.a(TAG, "deregisterAccount called by %s", this.o.getPackageName());
        if (bundle != null && (u = u(bundle.getBundle(KEY_CLIENT_EVENT_CONTEXT))) != null) {
            return a(bl.f(callback), u);
        }
        ej by = ej.by("DeregisterAccount");
        return bi().a(str, mq.a(by, by.ea(), callback, this.o), by, bundle);
    }

    @FireOsSdk
    public MAPFuture<Bundle> deregisterDevice(Callback callback, Bundle bundle) {
        String u;
        io.a(TAG, "deregisterDevice called by %s", this.o.getPackageName());
        if (bundle != null && (u = u(bundle.getBundle(KEY_CLIENT_EVENT_CONTEXT))) != null) {
            return a(bl.f(callback), u);
        }
        ej by = ej.by("DeregisterDevice");
        return bi().a(mq.a(by, by.ea(), callback, this.o), by, bundle);
    }

    @Deprecated
    public MAPFuture<Bundle> recoverAccount(Activity activity, Bundle bundle, Bundle bundle2, Callback callback) {
        return a(activity, bundle, bundle2, callback);
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00b6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.amazon.identity.auth.device.api.MAPFuture<android.os.Bundle> a(android.content.Context r11, android.os.Bundle r12, android.os.Bundle r13, com.amazon.identity.auth.device.api.Callback r14) {
        /*
            r10 = this;
            com.amazon.identity.auth.device.bl r2 = new com.amazon.identity.auth.device.bl
            r2.<init>(r14)
            java.lang.String r4 = "RecoverAccount"
            com.amazon.identity.auth.device.ej r5 = com.amazon.identity.auth.device.ej.by(r4)
            boolean r6 = r10.b(r11, r12, r13, r2)
            if (r6 == 0) goto Ld9
            com.amazon.identity.auth.device.fp r6 = com.amazon.identity.auth.device.fp.E(r12)
            java.lang.String r6 = r6.getDirectedId()
            if (r13 == 0) goto L1d
            r0 = r13
            goto L22
        L1d:
            android.os.Bundle r0 = new android.os.Bundle
            r0.<init>()
        L22:
            com.amazon.identity.auth.device.hw.N(r0)
            android.os.Bundle r7 = new android.os.Bundle
            r7.<init>()
            java.lang.String r8 = "com.amazon.dcp.sso.property.account.acctId"
            r7.putString(r8, r6)
            r8 = 1
            java.lang.String r9 = "account_recover_attempt"
            r7.putBoolean(r9, r8)
            r7.putAll(r0)
            java.lang.String r0 = "link_code"
            boolean r9 = r7.containsKey(r0)
            if (r9 != 0) goto L49
            java.lang.String r9 = "pre_authorized_link_code"
            boolean r9 = r7.containsKey(r9)
            if (r9 == 0) goto L65
        L49:
            java.lang.String r9 = r10.getPrimaryAccount()
            boolean r6 = r6.equals(r9)
            if (r6 != 0) goto L65
            com.amazon.identity.auth.device.api.MAPError$CommonError r4 = com.amazon.identity.auth.device.api.MAPError.CommonError.BAD_REQUEST
            com.amazon.identity.auth.device.api.MAPAccountManager$RegistrationError r0 = com.amazon.identity.auth.device.api.MAPAccountManager.RegistrationError.BAD_REQUEST
            int r6 = r0.value()
            r8 = 0
            java.lang.String r5 = "Recovering account using CBL only supports primary account"
            java.lang.String r7 = "Recovering account using CBL only supports primary account"
            r3 = r14
            com.amazon.identity.auth.device.m.a(r3, r4, r5, r6, r7, r8)
            return r2
        L65:
            boolean r3 = r7.containsKey(r0)
            if (r3 == 0) goto La9
            java.lang.String r0 = r7.getString(r0)
            boolean r3 = android.text.TextUtils.isEmpty(r0)
            if (r3 != 0) goto La9
            com.amazon.identity.auth.device.ed r3 = r10.o
            com.amazon.identity.auth.device.bn$a r3 = com.amazon.identity.auth.device.bn.q(r3)
            if (r3 != 0) goto L84
            java.lang.String r0 = "The link code that MAP had has expired or it has not been generated yet. Please call MAPAccountManager#generateLinkCode to generate the link code."
            com.amazon.identity.auth.device.api.MAPFuture r0 = r10.a(r2, r0)
            return r0
        L84:
            java.lang.String r6 = r3.hs
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L93
            java.lang.String r0 = "The link code does not match the one that MAP has. Please call MAPAccountManager#generateLinkCode to get the link code."
            com.amazon.identity.auth.device.api.MAPFuture r0 = r10.a(r2, r0)
            return r0
        L93:
            com.amazon.identity.auth.device.ed r0 = r10.o
            com.amazon.identity.auth.device.bl r0 = com.amazon.identity.auth.device.bn.a(r0, r2)
            java.lang.String r2 = r3.hs
            java.lang.String r6 = "cbl_public_code"
            r7.putString(r6, r2)
            java.lang.String r2 = r3.ht
            java.lang.String r3 = "cbl_private_code"
            r7.putString(r3, r2)
            r6 = r0
            goto Laa
        La9:
            r6 = r2
        Laa:
            com.amazon.identity.auth.device.mv r0 = r5.ea()
            com.amazon.identity.auth.device.ed r2 = r10.o
            com.amazon.identity.auth.device.api.Callback r8 = com.amazon.identity.auth.device.mq.a(r5, r0, r6, r2, r8)
            if (r11 == 0) goto Lcd
            java.lang.String r0 = com.amazon.identity.auth.device.api.MAPAccountManager.TAG
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Recover account called by: "
            r2.<init>(r3)
            java.lang.String r3 = r11.getPackageName()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            com.amazon.identity.auth.device.io.i(r0, r2)
        Lcd:
            com.amazon.identity.auth.device.f r0 = r10.bi()
            r1 = r11
            r2 = r12
            r3 = r7
            r4 = r8
            r0.a(r1, r2, r3, r4, r5)
            r2 = r6
        Ld9:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.api.MAPAccountManager.a(android.content.Context, android.os.Bundle, android.os.Bundle, com.amazon.identity.auth.device.api.Callback):com.amazon.identity.auth.device.api.MAPFuture");
    }

    @FireOsSdk
    public MAPFuture<Bundle> authenticateAccount(final Activity activity, final SigninOption signinOption, final Bundle bundle, final Bundle bundle2, Callback callback) {
        final bl blVar = new bl(callback);
        ej by = ej.by("AuthenticateAccount");
        if (activity == null) {
            return a(blVar, "Activity passed should not be null. Please pass non null activity");
        }
        bi().a(t(bundle), mq.a(by, by.ea(), new Callback() { // from class: com.amazon.identity.auth.device.api.MAPAccountManager.2
            @Override // com.amazon.identity.auth.device.api.Callback
            public void onError(Bundle bundle3) {
                if (bundle3.getInt(MAPError.KEY_ERROR_CODE, -1) == MAPError.AccountError.AUTHENTICATION_CHALLENGED.getErrorCode()) {
                    Bundle bundle4 = bundle3.getBundle(MAPAccountManager.KEY_AUTHENTICATION_CHALLENGE);
                    Bundle bundle5 = new Bundle();
                    Bundle bundle6 = bundle2;
                    if (bundle6 != null) {
                        bundle5.putAll(bundle6);
                    }
                    MAPAccountManager.d(bundle, bundle5);
                    if (bundle.containsKey("com.amazon.dcp.sso.property.account.acctId") && !bundle5.containsKey("com.amazon.dcp.sso.property.account.acctId")) {
                        bundle5.putString("com.amazon.dcp.sso.property.account.acctId", bundle.getString("com.amazon.dcp.sso.property.account.acctId"));
                    }
                    bundle5.putBundle(MAPAccountManager.KEY_AUTHENTICATION_CHALLENGE, bundle4);
                    MAPAccountManager.this.authenticateAccountWithUI(activity, signinOption, bundle5, blVar);
                    return;
                }
                blVar.onError(bundle3);
            }

            @Override // com.amazon.identity.auth.device.api.Callback
            public void onSuccess(Bundle bundle3) {
                blVar.onSuccess(bundle3);
            }
        }, this.o), by);
        return blVar;
    }

    private String b(Bundle bundle, String str) {
        return s(bundle) ? GeneratedOutlineSupport1.outline72(str, ":ResumeUrl") : str;
    }

    @FireOsSdk
    public MAPFuture<Bundle> registerAccount(final Activity activity, final Bundle bundle, final Bundle bundle2, Callback callback) {
        String u;
        final bl f = bl.f(callback);
        if (activity == null) {
            return a(f, "Activity passed should not be null. Please pass non null activity");
        }
        if (bundle != null && (u = u(bundle.getBundle(KEY_CLIENT_EVENT_CONTEXT))) != null) {
            a(f, u);
        }
        RegistrationType registrationType = RegistrationType.WITH_LOGIN_CREDENTIALS;
        if (bundle != null && bundle.containsKey("com.amazon.dcp.sso.property.account.acctId") && bundle.containsKey("com.amazon.dcp.sso.property.account.primary.acctId")) {
            return a(f, "You pass account direct id for both secondary and primary registration: MAPAccountManager.KEY_DIRECTED_ID and MAPAccountManager.KEY_PRIMARY_DIRECTED_ID.\nPlease pass either of it.");
        }
        if (bundle != null && bundle.containsKey("com.amazon.dcp.sso.property.account.primary.acctId")) {
            registrationType = RegistrationType.WITH_PRIMARY_DIRECTEDID_CREDENTIALS;
        }
        if (bundle != null && bundle.containsKey("com.amazon.dcp.sso.property.account.acctId")) {
            registrationType = RegistrationType.WITH_DIRECTEDID_CREDENTIALS;
        }
        RegistrationType registrationType2 = registrationType;
        final ej by = ej.by("RegisterAccount:" + registrationType2.name());
        bi().a(registrationType2, t(bundle), mq.a(by, by.ea(), new Callback() { // from class: com.amazon.identity.auth.device.api.MAPAccountManager.1
            @Override // com.amazon.identity.auth.device.api.Callback
            public void onError(Bundle bundle3) {
                if (bundle3.getInt(MAPError.KEY_ERROR_CODE, -1) == MAPError.AccountError.AUTHENTICATION_CHALLENGED.getErrorCode()) {
                    Bundle bundle4 = bundle3.getBundle(MAPAccountManager.KEY_AUTHENTICATION_CHALLENGE);
                    io.dm(MAPAccountManager.TAG);
                    String str = MAPAccountManager.TAG;
                    new StringBuilder("challenge: ").append(hw.M(bundle4));
                    io.dm(str);
                    Bundle bundle5 = bundle2;
                    if (bundle5 == null) {
                        bundle5 = new Bundle();
                    }
                    MAPAccountManager.d(bundle, bundle5);
                    bundle5.putBundle(MAPAccountManager.KEY_AUTHENTICATION_CHALLENGE, bundle4);
                    MAPAccountManager.this.registerAccountWithUI(activity, SigninOption.WebviewSignin, bundle5, f);
                    return;
                }
                f.onError(bundle3);
            }

            @Override // com.amazon.identity.auth.device.api.Callback
            public void onSuccess(final Bundle bundle3) {
                Bundle bundle4 = bundle2;
                if (bundle4 != null && bundle4.containsKey(MAPAccountManager.KEY_ENSURE_ACCOUNT_STATE_ATTRIBUTES)) {
                    io.i(MAPAccountManager.TAG, "Register account is done, going to check whether the account has assert attributes");
                    final String string = bundle3.getString("com.amazon.dcp.sso.property.account.acctId");
                    if (TextUtils.isEmpty(string)) {
                        io.i(MAPAccountManager.TAG, "Did not get the directedID in the reg success respone so we are going to fix the account we get from getAccount(). It should not happen on Gen6+ and normal 3P devices.");
                        string = MAPAccountManager.this.getAccount();
                    }
                    Bundle bundle5 = new Bundle();
                    bundle5.putString("com.amazon.identity.ap.domain", bundle.getString("com.amazon.identity.ap.domain"));
                    bundle5.putBundle(AuthPortalOptions.KEY_REQUEST_PARAMETERS, bundle2.getBundle(AuthPortalOptions.KEY_REQUEST_PARAMETERS));
                    bundle5.putStringArrayList(MAPAccountManager.KEY_ENSURE_ACCOUNT_STATE_ATTRIBUTES, bundle2.getStringArrayList(MAPAccountManager.KEY_ENSURE_ACCOUNT_STATE_ATTRIBUTES));
                    Callback callback2 = new Callback() { // from class: com.amazon.identity.auth.device.api.MAPAccountManager.1.1
                        @Override // com.amazon.identity.auth.device.api.Callback
                        public void onError(Bundle bundle6) {
                            try {
                                by.bA("DeregisterAfterEnsuringAccountStateFail");
                                io.i(MAPAccountManager.TAG, "Going to degister the account, since the account doesn't have assert attributes");
                                MAPAccountManager.this.deregisterAccount(string, null).get();
                            } catch (Exception unused) {
                                io.e(MAPAccountManager.TAG, "Exception happened when try to degister account because of missing assert attributes");
                            }
                            f.onError(bundle6);
                        }

                        @Override // com.amazon.identity.auth.device.api.Callback
                        public void onSuccess(Bundle bundle6) {
                            bundle3.putAll(bundle6);
                            f.onSuccess(bundle3);
                        }
                    };
                    by.bA("RegisterAccountWithEnsuringAccountState");
                    MAPAccountManager.this.ensureAccountState(activity, string, bundle5, callback2);
                    return;
                }
                f.onSuccess(bundle3);
            }
        }, this.o), by);
        return f;
    }

    private String a(Bundle bundle, String str) {
        Bundle bundle2;
        if (bundle != null && bundle.containsKey(KEY_AUTHENTICATION_CHALLENGE) && (bundle2 = bundle.getBundle(KEY_AUTHENTICATION_CHALLENGE)) != null) {
            String string = bundle2.getString("com.amazon.identity.auth.ChallengeException.Reason");
            if (TextUtils.isEmpty(string)) {
                string = "Challenge";
            }
            String outline75 = GeneratedOutlineSupport1.outline75(str, ":", string);
            String string2 = bundle2.getString("com.amazon.identity.auth.ChallengeException.requiredAuthenticationMethod");
            if (!TextUtils.isEmpty(string2)) {
                StringBuilder sb = new StringBuilder();
                sb.append(outline75);
                sb.append(":");
                sb.append(string2);
            }
        }
        return b(bundle, str);
    }
}
