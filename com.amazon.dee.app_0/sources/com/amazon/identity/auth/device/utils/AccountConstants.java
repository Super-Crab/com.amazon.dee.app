package com.amazon.identity.auth.device.utils;

import java.util.Arrays;
import java.util.List;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class AccountConstants {
    public static final String ACCOUNT_USERDATA_KEY_PREFIX = "com.amazon.dcp.sso.property.account";
    public static final String ACTION_ACCOUNT_ADD_INTENT = "com.amazon.dcp.sso.AddAccount";
    public static final String ACTION_AMAZON_ACCOUNT_ADDED_INTENT = "com.amazon.dcp.sso.action.account.added";
    public static final String ACTION_AMAZON_ACCOUNT_ADDED_ON_DEVICE_INTENT = "com.amazon.identity.auth.account.added.on.device";
    public static final String ACTION_AMAZON_ACCOUNT_PROPERTY_CHANGED_INTENT = "com.amazon.dcp.sso.action.AmazonAccountPropertyService.property.changed";
    public static final String ACTION_AMAZON_ACCOUNT_REMOVED_INTENT = "com.amazon.dcp.sso.action.account.removed";
    public static final String ACTION_AMAZON_ACCOUNT_REMOVED_ON_DEVICE_INTENT = "com.amazon.identity.auth.account.removed.on.device";
    public static final String ACTION_AMAZON_CALL_ACCOUNT_REGISTRAR = "com.amazon.dcp.sso.action.AmazonAccountRegistrar";
    public static final String ACTION_AMAZON_SECONDARY_ACCOUNT_ADDED_INTENT = "com.amazon.dcp.sso.action.secondary.account.added";
    public static final String ACTION_AMAZON_SECONDARY_ACCOUNT_REMOVED_INTENT = "com.amazon.dcp.sso.action.secondary.account.removed";
    public static final String ACTION_AMAZON_SSO_INFORMATION = "com.amazon.dcp.sso.action.SSO_POST_SPLIT_INFORMATION";
    public static final String ACTION_CONFIRM_CREDENTIAL_INTENT = "com.amazon.dcp.sso.ConfirmCredential";
    public static final String ACTION_SUB_AUTHENTICATOR_INTENT = "com.amazon.dcp.sso.AccountSubAuthenticator";
    public static final String ACTION_USER_SWITCHED = "android.intent.action.USER_SWITCHED";
    public static final String ADD_ACCOUNT_FROM_UPDATE_CREDENTIALS = "com.amazon.dcp.sso.RegisterViaUpdateCreds";
    public static final String ADD_ACCOUNT_PARAMETERS = "com.amazon.dcp.sso.addAccountParameters";
    public static final String ADD_ACCOUNT_USING_DEVICE_SECRET = "com.amazon.dcp.sso.AddAccountUsingDeviceSecret";
    public static final String AMAZON_ACCOUNT_TYPE = "com.amazon.account";
    public static final String AP_OPTION_KEY_ASSOC_HANDLE = "com.amazon.dcp.sso.ap.assoc_handle";
    public static final String AP_OPTION_KEY_PAGEID = "com.amazon.dcp.sso.ap.pageid";
    public static final String AP_OPTION_KEY_PREFIX = "com.amazon.dcp.sso.ap";
    public static final String AP_OPTION_KEY_SESSIONID = "com.amazon.dcp.sso.ap.sessionid";
    public static final String AP_OPTION_KEY_UBID = "com.amazon.dcp.sso.ap.ubid";
    public static final String CLIENT_DOMAIN_DEVICE_LEGACY = "DeviceLegacy";
    public static final String COOKIE_TOKEN_TYPE_PREFIX = "com.amazon.dcp.sso.token.cookie";
    public static final int DEREGISTER_SUB_AUTH_ERROR_GET_AUTH_TOKEN_CALLED = 105;
    public static final String DEVICE_TOKEN_TYPE_PREFIX = "com.amazon.dcp.sso.token.device";
    public static final int DMS_EXCHANGE_ERROR_AUTHENTICATION_FAILED = 103;
    public static final int DMS_EXCHANGE_ERROR_CUSTOMER_NOT_FOUND = 100;
    public static final int DMS_EXCHANGE_ERROR_DEVICE_ALREADY_REGISTERED = 101;
    public static final int DMS_EXCHANGE_ERROR_DUPLICATE_DEVICE_NAME = 102;
    public static final int DMS_EXCHANGE_ERROR_UNKNOWN_FAILURE = 104;
    public static final String DMS_SUB_AUTHENTICATOR_XML_FILE_NAME = "dms_sub_authenticator";
    public static final String EXTRA_AMAZON_ACCOUNT_CHANGED_NAME = "com.amazon.dcp.sso.extra.account.name";
    public static final String EXTRA_AMAZON_ACCOUNT_CHANGED_TYPE = "com.amazon.dcp.sso.extra.account.type";
    public static final String EXTRA_TOKENS_KEY_PREFIX = "com.amazon.dcp.sso.property.account.extratokens";
    public static final String INTENT_ACTION_GET_DEVICE_CREDENTIALS = "com.amazon.dcp.sso.action.GET_DEVICE_CREDENTIALS";
    public static final String INTENT_ACTION_SESSION_USER_ADD_SERVICE = "com.amazon.dcp.sso.action.central.session.user.add";
    public static final String INTENT_ACTION_SESSION_USER_CHANGE_SERVICE = "com.amazon.dcp.sso.action.central.session.user.change";
    public static final String INTENT_ACTION_SESSION_USER_REMOVE_SERVICE = "com.amazon.dcp.sso.action.central.session.user.remove";
    public static final String INTENT_ACTION_SET_COR_PFM = "com.amazon.dcp.sso.action.SET_COR_PFM";
    public static final String INTENT_EXTRA_SESSION_USER_ACCOUNTS = "new.session.user.accounts";
    public static final String INTENT_EXTRA_SESSION_USER_ACCOUNTS_OLD = "session.user.accounts";
    public static final String KEY_ACCOUNT_IDENTIFIER = "com.amazon.dcp.sso.property.account.acctId";
    public static final String KEY_ACCOUNT_STATUS = "com.amazon.dcp.sso.property.account.ACCOUNT_STATUS";
    public static final String KEY_ACCOUNT_UUID = "com.amazon.dcp.sso.property.account.UUID";
    public static final String KEY_ACTOR_CONVERTEDTYPE = "actor.converted.type";
    public static final String KEY_ACTOR_ENTITYTYPE = "actor.entity.type";
    public static final String KEY_ACTOR_SUBTYPE = "actor.sub.type";
    public static final String KEY_ADD_ACCOUNT_ACCESS_TOKEN = "com.amazon.dcp.sso.AddAccount.options.AccessToken";
    public static final String KEY_ADD_ACCOUNT_ATMAIN = "com.amazon.dcp.sso.AddAccount.options.ATMain";
    public static final String KEY_ADD_ACCOUNT_AUTHTOKENTYPE = "com.amazon.dcp.sso.addAccountParameters.authTokenType";
    public static final String KEY_ADD_ACCOUNT_AUTH_TOKEN = "com.amazon.dcp.sso.AddAccount.options.AuthToken";
    public static final String KEY_ADD_ACCOUNT_AUTH_TOKEN_CONTEXT = "com.amazon.dcp.sso.AddAccount.options.AuthTokenClientContext";
    public static final String KEY_ADD_ACCOUNT_CALLER_INFORMATION = "com.amazon.dcp.sso.addAccountParameters.caller";
    public static final String KEY_ADD_ACCOUNT_OPTIONS = "com.amazon.dcp.sso.addAccountParameters.options";
    public static final String KEY_ADD_ACCOUNT_REQUIREDFEATURES = "com.amazon.dcp.sso.addAccountParameters.requiredFeatures";
    public static final String KEY_ADD_ACCOUNT_URL = "com.amazon.dcp.sso.AddAccount.options.URL";
    public static final String KEY_ADD_AS_PRIMARY_ACCOUNT = "com.amazon.dcp.sso.AddAccount.options.AddAsPrimary";
    public static final String KEY_ADD_AS_SECONDARY_ACCOUNT = "com.amazon.dcp.sso.AddAccount.options.AddAsSecondary";
    public static final String KEY_ADP_PRIVATE_KEY = "adp_private_key";
    public static final String KEY_ADP_TOKEN = "adp_token";
    public static final String KEY_AUTHORIZATION_CODE = "authorization_code";
    public static final String KEY_AUTHORIZATION_CODE_TO_ACCESS_TOKEN_EXCHANGE = "authorizationCode";
    public static final String KEY_CBL_PRIVATE_CODE = "cbl_private_code";
    public static final String KEY_CBL_PUBLIC_CODE = "cbl_public_code";
    public static final String KEY_CLIENT_DOMAIN = "client_domain";
    public static final String KEY_CLIENT_ID = "client_id";
    public static final String KEY_CODE_CHALLENGE = "code_challenge";
    public static final String KEY_CODE_CHALLENGE_METHOD = "code_challenge_method";
    public static final String KEY_CODE_VERIFIER = "code_verifier";
    public static final String KEY_COR = "com.amazon.dcp.sso.property.account.cor";
    public static final String KEY_COR_PFM_ACCOUNT = "cor.pfm.account";
    public static final String KEY_COR_PFM_DIRECTED_ID = "cor.pfm.directedid";
    public static final String KEY_COR_SOURCE = "com.amazon.dcp.sso.property.account.sourceofcor";
    public static final String KEY_CUSTOMER_REGION = "com.amazon.dcp.sso.property.account.customer_region";
    public static final String KEY_DELEGATEE_ACCOUNT = "com.amazon.dcp.sso.property.account.delegateeaccount";
    public static final String KEY_DELEGATION_DOMAIN = "com.amazon.dcp.sso.property.account.delegationDomain";
    public static final String KEY_DEVICE_ACCOUNT_ROLE = "com.amazon.dcp.sso.property.deviceaccountrole";
    public static final String KEY_DEVICE_CREDENTIALS = "com.amazon.dcp.sso.token.device.credentialsmap";
    public static final String KEY_DEVICE_DEVICE_EMAIL = "com.amazon.dcp.sso.property.deviceemail";
    public static final String KEY_DEVICE_DEVICE_NAME = "com.amazon.dcp.sso.property.devicename";
    public static final String KEY_DEVICE_FIRST_NAME = "com.amazon.dcp.sso.property.firstname";
    public static final String KEY_DEVICE_USER_NAME = "com.amazon.dcp.sso.property.username";
    public static final String KEY_DMS_ERROR_CODE = "com.amazon.dcp.sso.dms.ErrorCode";
    public static final String KEY_DMS_ERROR_MESSAGE = "com.amazon.dcp.sso.dms.ErrorMessage";
    public static final String KEY_PFM = "com.amazon.dcp.sso.property.account.pfm";
    public static final String KEY_PRIMARY_ACCOUNT_IDENTIFIER = "com.amazon.dcp.sso.property.account.primary.acctId";
    public static final String KEY_RECOVERY_ATTEMPT = "account_recover_attempt";
    public static final String KEY_REGISTRATION_TYPE = "registration_type";
    public static final String KEY_SECONDARY_AMAZON_ACCOUNT = "com.amazon.dcp.sso.property.secondary";
    public static final String KEY_SESSION_USER_AMAZON_ACCOUNT = "com.amazon.dcp.sso.property.sessionuser";
    public static final String KEY_SET_COOKIE_FOR_AUTHENTICATE_ACCOUNT_WITH_UI = "set_cookie_for_authenticate_account_with_ui";
    public static final String KEY_SID_COOKIE_VALUE = "sid_cookie_value";
    public static final String KEY_TOKEN_ENCRYPT_KEY = "com.amazon.dcp.sso.property.encryptKey";
    public static final String KEY_TOKEN_UPGRADE_TYPE_AUTHORIZATION_CODE = "authorizationCode";
    public static final String KEY_USER_EMAIL = "com.amazon.dcp.sso.property.account.useremail";
    public static final String KEY_WEBSITE_COOKIES_JSON_ARRAY = "website_cookies_json_array";
    public static final String PERMISSION_AMAZON_ACCOUNT_CHANGED = "com.amazon.dcp.sso.permission.account.changed";
    public static final String PERMISSION_AMAZON_ACCOUNT_PROPERTY_CHANGED = "com.amazon.dcp.sso.permission.AmazonAccountPropertyService.property.changed";
    public static final String PERMISSION_AMAZON_MULTIPLE_PROFILE_AWARE = "com.amazon.identity.permission.MULTIPLE_PROFILE_AWARE";
    public static final String PERMISSION_APPLICATION_ACCOUNT_FOR_APPLICATION_CHANGED = "com.amazon.dcp.sso.permission.applicationforaccount.changed";
    public static final String PERMISSION_MANAGE_COR_PFM = "com.amazon.dcp.sso.permission.MANAGE_COR_PFM";
    public static final String SUB_AUTHENTICATOR_ACCOUNT_TYPE_ATTRIBUTE = "accountType";
    public static final String SUB_AUTHENTICATOR_ATTRIBUTES_NAME = "account-sub-authenticator";
    public static final String SUB_AUTHENTICATOR_DEVICE_TYPE_NAME = "device-type";
    public static final String SUB_AUTHENTICATOR_DEVICE_TYPE_NAME_ATTRIBUTE = "name";
    public static final String SUB_AUTHENTICATOR_METADATA_NAME = "com.amazon.dcp.sso.AccountSubAuthenticator";
    public static final String SUB_AUTHENTICATOR_MULTIPLE_ACCOUNT_AWARE = "multiple-account-aware";
    public static final String SUB_AUTHENTICATOR_SUB_AUTH_NAME = "subAuth";
    public static final String SUB_AUTHENTICATOR_SUB_AUTH_NAME_ATTRIBUTE = "name";
    public static final String SUB_AUTHENTICATOR_TOKEN_TYPES_NAME = "token-types";
    public static final String SUB_AUTHENTICATOR_TOKEN_TYPE_NAME = "token-type";
    public static final String SUB_AUTHENTICATOR_TOKEN_TYPE_NAME_ATTRIBUTE = "name";
    public static final String SUB_AUTHENTICATOR_TYPE_ATTRIBUTE = "type";
    public static final String TOKEN_TYPE_ACCOUNT_POOL = "com.amazon.dcp.sso.token.device.accountpool";
    public static final String TOKEN_TYPE_ACTOR_COOKIES = "com.amazon.dcp.sso.token.amazon.actor.cookies";
    public static final String TOKEN_TYPE_AMAZON_COM_COOKIES_TOKEN = "com.amazon.dcp.sso.token.cookie.amazonComCookies";
    public static final String TOKEN_TYPE_AMAZON_MOBILE_AUTH_ENCRYPTION_KEY = "com.amazon.mobile.auth.encryption_key";
    public static final String TOKEN_TYPE_AMAZON_MOBILE_AUTH_ENCRYPTION_KEY_CREATION_TIME = "com.amazon.mobile.auth.encryption_key.creation_time";
    public static final String TOKEN_TYPE_AMAZON_MOBILE_AUTH_ENCRYPTION_KEY_IDENTIFIER = "com.amazon.mobile.auth.encryption_key.identifier";
    public static final String TOKEN_TYPE_AMAZON_MOBILE_AUTH_ENCRYPTION_KEY_VERSION = "com.amazon.mobile.auth.encryption_key.version";
    public static final String TOKEN_TYPE_COOKIES = "com.amazon.dcp.sso.token.amazon.cookies";
    public static final String TOKEN_TYPE_COOKIE_ATMAIN_TOKEN = "com.amazon.dcp.sso.token.cookie.atmain";
    public static final String TOKEN_TYPE_COOKIE_MFA_SID_TOKEN = "com.amazon.dcp.sso.token.cookie.sid";
    public static final String TOKEN_TYPE_COOKIE_XFSN = "com.amazon.identity.cookies.xfsn";
    public static final String TOKEN_TYPE_COOKIE_XMAIN_TOKEN = "com.amazon.dcp.sso.token.cookie.xmain";
    public static final String TOKEN_TYPE_DEVICE_AMAZON_ACTOR_ACCESS_TOKEN = "com.amazon.dcp.sso.token.oauth.amazon.actor.access_token";
    public static final String TOKEN_TYPE_DEVICE_AMAZON_ACTOR_REFRESH_TOKEN = "com.amazon.dcp.sso.token.oauth.amazon.actor.refresh_token";
    public static final String TOKEN_TYPE_DEVICE_AMAZON_ATZ_ACCESS_TOKEN = "com.amazon.dcp.sso.token.oauth.atz.access_token";
    public static final String TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN = "com.amazon.dcp.sso.token.oauth.amazon.access_token";
    public static final String TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN_EXPIRES_AT = "com.amazon.dcp.sso.token.oauth.amazon.access_token.expires_at";
    public static final String TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN_REFRESHED_AT = "com.amazon.dcp.sso.token.oauth.amazon.access_token.refreshed_at";
    public static final String TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ATZ_TOKEN_EXPIRES_AT = "com.amazon.dcp.sso.token.oauth.amazon.atz_token.expires_at";
    public static final String TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ATZ_TOKEN_REFRESHED_AT = "com.amazon.dcp.sso.token.oauth.amazon.atz_token.refreshed_at";
    public static final String TOKEN_TYPE_DEVICE_AMAZON_OAUTH_REFRESH_TOKEN = "com.amazon.dcp.sso.token.oauth.amazon.refresh_token";
    public static final String TOKEN_TYPE_DEVICE_NAME = "com.amazon.dcp.sso.token.device.devicename";
    public static final String TOKEN_TYPE_DEVICE_SERIAL_NUMBER = "com.amazon.dcp.sso.token.device.deviceserialname";
    public static final String TOKEN_TYPE_DEVICE_TYPE = "com.amazon.dcp.sso.token.devicedevicetype";
    public static final String TOKEN_TYPE_PREFIX = "com.amazon.dcp.sso.token";
    public static final String TOKEN_TYPE_XMAIN_AND_XACB_COOKIES_TOKEN = "com.amazon.dcp.sso.token.cookie.xmainAndXabcCookies";
    public static final String USERDATA_KEY_PREFIX = "com.amazon.dcp.sso.property";
    public static final String TOKEN_TYPE_DEVICE_ADP_TOKEN = "com.amazon.dcp.sso.token.device.adptoken";
    public static final String TOKEN_TYPE_DEVICE_PRIVATE_KEY = "com.amazon.dcp.sso.token.device.privatekey";
    public static final List<String> TOKEN_TYPE_DEVICE_ADP_TOKEN_AND_PRIVATE_KEY_LIST = Arrays.asList(TOKEN_TYPE_DEVICE_ADP_TOKEN, TOKEN_TYPE_DEVICE_PRIVATE_KEY);

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public enum DeviceRenameError {
        NETWORK_FAILURE(0, "NetworkFailure"),
        AUTHENTICATION_FAILED(1, "AuthenticationFailed"),
        PARSE_ERROR(2, "ParseError"),
        NO_AMAZON_ACCOUNT(3, "NoAmazonAccount"),
        NAME_ALREADY_USED(4, "NameAlreadyUsed"),
        INVALID_INPUT(5, "InvalidInputs"),
        UNRECOGNIZED(6, "Unrecognized");
        
        private final String mName;
        private final int mValue;

        DeviceRenameError(int i, String str) {
            this.mValue = i;
            this.mName = str;
        }

        public static DeviceRenameError fromValue(int i) {
            DeviceRenameError[] values;
            for (DeviceRenameError deviceRenameError : values()) {
                if (deviceRenameError.value() == i) {
                    return deviceRenameError;
                }
            }
            throw new IndexOutOfBoundsException();
        }

        public String getName() {
            return this.mName;
        }

        public int value() {
            return this.mValue;
        }
    }

    private AccountConstants() {
    }
}
