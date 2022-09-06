package com.amazon.identity.auth.device;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseIntArray;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPError;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class m {
    private static final SparseIntArray aS;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        aS = sparseIntArray;
        sparseIntArray.append(7, MAPAccountManager.RegistrationError.BAD_REQUEST.value());
        aS.append(8, MAPAccountManager.RegistrationError.BAD_REQUEST.value());
        aS.append(4, MAPAccountManager.RegistrationError.REGISTER_FAILED.value());
        aS.append(5, MAPAccountManager.RegistrationError.PARSE_ERROR.value());
        aS.append(3, MAPAccountManager.RegistrationError.NETWORK_FAILURE.value());
        aS.append(1, MAPAccountManager.RegistrationError.REGISTER_FAILED.value());
        aS.append(6, MAPAccountManager.RegistrationError.UNRECOGNIZED.value());
    }

    @Deprecated
    public static void a(Callback callback, MAPError mAPError, String str, int i, String str2, Bundle bundle) {
        if (callback == null) {
            return;
        }
        Bundle a = a(mAPError, str, i, str2);
        if (bundle != null) {
            a.putAll(bundle);
        }
        callback.onError(a);
    }

    @Deprecated
    public static Bundle b(MAPError mAPError, String str, int i, String str2) {
        Integer d = d(i);
        Bundle bundle = new Bundle();
        if (d != null) {
            bundle = a(mAPError, str, d.intValue(), str2);
        }
        bundle.putInt("errorCode", i);
        bundle.putString("errorMessage", str2);
        return bundle;
    }

    @Deprecated
    public static Integer d(int i) {
        int i2 = aS.get(i, Integer.MIN_VALUE);
        if (i2 == Integer.MIN_VALUE) {
            return null;
        }
        return Integer.valueOf(i2);
    }

    @Deprecated
    public static boolean h(Bundle bundle) {
        if (bundle == null) {
            return false;
        }
        return bundle.containsKey("com.amazon.dcp.sso.ErrorCode") || bundle.containsKey("errorCode") || bundle.containsKey(MAPError.KEY_ERROR_CODE);
    }

    @Deprecated
    public static Bundle w(String str) {
        Bundle a = a(MAPError.AccountError.ACCOUNT_ALREADY_REGISTERED, "Account has already been registered on this device", MAPAccountManager.RegistrationError.ACCOUNT_ALREADY_EXISTS.value(), "Account has already been registered on this device");
        if (!TextUtils.isEmpty(str)) {
            a.putString("com.amazon.dcp.sso.property.account.acctId", str);
        }
        return a;
    }

    @Deprecated
    public static Bundle a(MAPError mAPError, String str, int i, String str2) {
        Bundle bundle = new Bundle();
        bundle.putInt("com.amazon.dcp.sso.ErrorCode", i);
        bundle.putString("com.amazon.dcp.sso.ErrorMessage", str2);
        bundle.putInt(MAPError.KEY_ERROR_CODE, mAPError.getErrorCode());
        bundle.putString(MAPError.KEY_ERROR_MESSAGE, str);
        bundle.putString(MAPError.KEY_ERROR_TYPE, mAPError.getErrorType());
        return bundle;
    }

    @Deprecated
    public static void b(Callback callback, String str) {
        if (callback == null) {
            return;
        }
        callback.onError(w(str));
    }

    @Deprecated
    public static void a(Callback callback, MAPError mAPError, String str, int i, String str2) {
        if (callback == null) {
            return;
        }
        callback.onError(b(mAPError, str, i, str2));
    }

    @Deprecated
    public static void a(Callback callback, Bundle bundle) {
        if (callback == null) {
            return;
        }
        if (h(bundle)) {
            callback.onError(bundle);
        } else {
            callback.onSuccess(bundle);
        }
    }
}
