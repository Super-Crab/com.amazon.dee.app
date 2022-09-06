package com.amazon.alexa.identity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.CookieKeys;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import okhttp3.Cookie;
import rx.SingleSubscriber;
import rx.Subscriber;
/* loaded from: classes9.dex */
public class MAPCookiesCallback implements Callback {
    @VisibleForTesting
    static final String ACCESS_TOKEN_COOKIE_NAME = "neksec";
    private static final String[] EMPTY_STRING_ARRAY = new String[0];
    private String accessTokenCookieDomain;
    private boolean isSingle;
    private SingleSubscriber<? super String[]> singleSubscriber;
    private Subscriber<? super String[]> subscriber;

    /* renamed from: com.amazon.alexa.identity.MAPCookiesCallback$1  reason: invalid class name */
    /* loaded from: classes9.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$identity$auth$device$api$MAPAccountManager$RegistrationError = new int[MAPAccountManager.RegistrationError.values().length];

        static {
            try {
                $SwitchMap$com$amazon$identity$auth$device$api$MAPAccountManager$RegistrationError[MAPAccountManager.RegistrationError.REGISTER_FAILED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$identity$auth$device$api$MAPAccountManager$RegistrationError[MAPAccountManager.RegistrationError.NETWORK_FAILURE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$identity$auth$device$api$MAPAccountManager$RegistrationError[MAPAccountManager.RegistrationError.PARSE_ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$identity$auth$device$api$MAPAccountManager$RegistrationError[MAPAccountManager.RegistrationError.ACCOUNT_ALREADY_EXISTS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$identity$auth$device$api$MAPAccountManager$RegistrationError[MAPAccountManager.RegistrationError.BAD_REQUEST.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$identity$auth$device$api$MAPAccountManager$RegistrationError[MAPAccountManager.RegistrationError.AUTHENTICATION_FAILED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$identity$auth$device$api$MAPAccountManager$RegistrationError[MAPAccountManager.RegistrationError.CUSTOMER_NOT_FOUND.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$identity$auth$device$api$MAPAccountManager$RegistrationError[MAPAccountManager.RegistrationError.NO_ACCOUNT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$identity$auth$device$api$MAPAccountManager$RegistrationError[MAPAccountManager.RegistrationError.AUTHENTICATION_CHALLENGED.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$identity$auth$device$api$MAPAccountManager$RegistrationError[MAPAccountManager.RegistrationError.REQUIRED_3P_AUTHENTICATION.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$identity$auth$device$api$MAPAccountManager$RegistrationError[MAPAccountManager.RegistrationError.BAD_SECRET.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$identity$auth$device$api$MAPAccountManager$RegistrationError[MAPAccountManager.RegistrationError.DEVICE_ALREADY_REGISTERED.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$identity$auth$device$api$MAPAccountManager$RegistrationError[MAPAccountManager.RegistrationError.DUPLICATE_DEVICE_NAME.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$identity$auth$device$api$MAPAccountManager$RegistrationError[MAPAccountManager.RegistrationError.DEREGISTER_FAILED.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$amazon$identity$auth$device$api$MAPAccountManager$RegistrationError[MAPAccountManager.RegistrationError.DELEGATEE_ACCOUNT_ALREADY_DEREGISTERED.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$amazon$identity$auth$device$api$MAPAccountManager$RegistrationError[MAPAccountManager.RegistrationError.ALREADY_DEREGISTERED.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$amazon$identity$auth$device$api$MAPAccountManager$RegistrationError[MAPAccountManager.RegistrationError.UNRECOGNIZED.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$amazon$identity$auth$device$api$MAPAccountManager$RegistrationError[MAPAccountManager.RegistrationError.UI_NOT_FOUND.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$amazon$identity$auth$device$api$MAPAccountManager$RegistrationError[MAPAccountManager.RegistrationError.INTERNAL_ERROR.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
        }
    }

    public MAPCookiesCallback(Subscriber<? super String[]> subscriber, CrashMetadata crashMetadata) {
        this.isSingle = false;
        this.isSingle = false;
        this.subscriber = subscriber;
    }

    private ArrayList<String> bundleGetSafeStringArrayList(String str, Bundle bundle) {
        String[] stringArray = bundle.getStringArray(str);
        if (stringArray != null) {
            ArrayList<String> arrayList = new ArrayList<>(stringArray.length + 1);
            Collections.addAll(arrayList, stringArray);
            return arrayList;
        }
        return new ArrayList<>(1);
    }

    private String generateAuthTokenCookie(String str) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(10, 1);
        return new Cookie.Builder().name(ACCESS_TOKEN_COOKIE_NAME).value(str).domain(this.accessTokenCookieDomain).expiresAt(calendar.getTime().getTime()).secure().httpOnly().build().toString();
    }

    public void includeAccessTokenCookieWithDomain(String str) {
        this.accessTokenCookieDomain = str;
    }

    @Override // com.amazon.identity.auth.device.api.Callback
    public void onError(Bundle bundle) {
        MAPAccountManager.RegistrationError fromValue;
        AccountException accountException;
        String string = bundle.getString("com.amazon.dcp.sso.ErrorMessage");
        Object obj = bundle.get("com.amazon.dcp.sso.ErrorCode");
        Object obj2 = bundle.get(MAPAccountManager.KEY_ERROR_CODE_WEBVIEW_SSL_ERROR);
        String str = MAPIdentityService.TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(Utils.V2_LOG_PREFIX);
        outline107.append(Utils.safeFormat("MAPR5 Auth Event: Authentication error: %s : %s", obj, string));
        Log.e(str, outline107.toString());
        AccountException accountException2 = new AccountException(0);
        if ((obj instanceof Integer) && (fromValue = MAPAccountManager.RegistrationError.fromValue(((Integer) obj).intValue(), null)) != null) {
            switch (fromValue.ordinal()) {
                case 0:
                    accountException2 = new AccountException(6);
                    break;
                case 1:
                    if (obj2 != null) {
                        accountException2 = new AccountException(4);
                        break;
                    } else {
                        accountException2 = new AccountException(2);
                        break;
                    }
                case 2:
                    accountException2 = new AccountException(1);
                    break;
                case 3:
                    if (string.contains("Received Error code InvalidToken from the server")) {
                        accountException2 = new AccountException(1);
                        break;
                    } else {
                        accountException = new AccountException(3);
                        accountException2 = accountException;
                        break;
                    }
                case 4:
                    accountException2 = new AccountException(7);
                    break;
                case 5:
                    accountException2 = new AccountException(12);
                    break;
                case 6:
                    accountException2 = new AccountException(13);
                    break;
                case 7:
                    accountException2 = new AccountException(14);
                    break;
                case 8:
                case 14:
                case 17:
                default:
                    accountException2 = new AccountException(0);
                    break;
                case 9:
                    if ("Registration canceled".equals(string)) {
                        accountException2 = new AccountException(5);
                        break;
                    } else {
                        accountException2 = new AccountException(1);
                        break;
                    }
                case 10:
                    accountException = new AccountException(3);
                    accountException2 = accountException;
                    break;
                case 11:
                    accountException2 = new AccountException(16);
                    break;
                case 12:
                    accountException2 = new AccountException(11);
                    break;
                case 13:
                    accountException2 = new AccountException(8);
                    break;
                case 15:
                    accountException2 = new AccountException(15);
                    break;
                case 16:
                    accountException2 = new AccountException(9);
                    break;
                case 18:
                    accountException2 = new AccountException(10);
                    break;
            }
        }
        this.subscriber.onError(accountException2);
    }

    @Override // com.amazon.identity.auth.device.api.Callback
    public void onSuccess(Bundle bundle) {
        ArrayList<String> bundleGetSafeStringArrayList = bundleGetSafeStringArrayList(CookieKeys.KEY_COOKIES, bundle);
        String string = bundle.getString("com.amazon.dcp.sso.AddAccount.options.AccessToken");
        if (!TextUtils.isEmpty(this.accessTokenCookieDomain) && !TextUtils.isEmpty(string)) {
            bundleGetSafeStringArrayList.add(generateAuthTokenCookie(string));
        }
        if (bundleGetSafeStringArrayList.isEmpty()) {
            Log.i(MAPIdentityService.TAG, "IdentityV2 : User is authenticated and cookies are not available");
            if (this.isSingle) {
                this.singleSubscriber.onSuccess(null);
                return;
            }
            this.subscriber.onNext(null);
            this.subscriber.onCompleted();
            return;
        }
        Log.i(MAPIdentityService.TAG, "IdentityV2 : User is authenticated and cookies were retrieved successfully");
        try {
            if (this.isSingle) {
                this.singleSubscriber.onSuccess(bundleGetSafeStringArrayList.toArray(EMPTY_STRING_ARRAY));
            } else {
                this.subscriber.onNext(bundleGetSafeStringArrayList.toArray(EMPTY_STRING_ARRAY));
            }
        } catch (NullPointerException e) {
            Log.e(MAPCookiesCallback.class.getSimpleName(), "IdentityV2 : Null Pointer", e);
        }
        if (this.isSingle) {
            return;
        }
        this.subscriber.onCompleted();
    }

    public MAPCookiesCallback(SingleSubscriber<? super String[]> singleSubscriber, CrashMetadata crashMetadata) {
        this.isSingle = false;
        this.isSingle = true;
        this.singleSubscriber = singleSubscriber;
    }
}
