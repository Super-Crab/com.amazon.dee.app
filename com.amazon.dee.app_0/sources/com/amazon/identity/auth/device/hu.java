package com.amazon.identity.auth.device;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.utils.AccountConstants;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class hu {
    private static final String TAG = "com.amazon.identity.auth.device.hu";

    private hu() {
    }

    public static String a(Context context, Account account) {
        return a(ai(context), account);
    }

    private static hq ai(Context context) {
        return (hq) ed.M(context).getSystemService("dcp_account_manager");
    }

    public static void b(Context context, Bundle bundle) {
        if (bundle != null && bundle.containsKey(MAPAccountManager.KEY_AMAZON_ACCOUNT_LOGIN_NAME) && bundle.containsKey(AccountConstants.SUB_AUTHENTICATOR_ACCOUNT_TYPE_ATTRIBUTE) && !bundle.containsKey("com.amazon.dcp.sso.property.account.acctId")) {
            String j = j(context, bundle.getString(AccountConstants.SUB_AUTHENTICATOR_ACCOUNT_TYPE_ATTRIBUTE), bundle.getString(MAPAccountManager.KEY_AMAZON_ACCOUNT_LOGIN_NAME));
            if (!TextUtils.isEmpty(j)) {
                bundle.putString("com.amazon.dcp.sso.property.account.acctId", j);
            } else {
                io.e(TAG, "Could not get directed id from android account");
            }
        }
    }

    public static Bundle c(String str, String str2, Bundle bundle) {
        if (str != null) {
            bundle.putString(MAPAccountManager.KEY_AMAZON_ACCOUNT_LOGIN_NAME, str);
        }
        bundle.putString(AccountConstants.SUB_AUTHENTICATOR_ACCOUNT_TYPE_ATTRIBUTE, AccountConstants.AMAZON_ACCOUNT_TYPE);
        bundle.putString("com.amazon.dcp.sso.property.account.acctId", str2);
        return bundle;
    }

    public static boolean db(String str) {
        if (dc(str)) {
            return false;
        }
        return AccountConstants.TOKEN_TYPE_COOKIE_XMAIN_TOKEN.equals(str) || "com.amazon.dcp.sso.token.cookie.xmainAndXabcCookies".equals(str) || str.startsWith(AccountConstants.DEVICE_TOKEN_TYPE_PREFIX) || str.startsWith("com.amazon.dcp.sso.property.account.extratokens") || "com.amazon.identity.cookies.xfsn".equals(str);
    }

    public static boolean dc(String str) {
        return AccountConstants.TOKEN_TYPE_DEVICE_ADP_TOKEN.equals(str) || AccountConstants.TOKEN_TYPE_DEVICE_PRIVATE_KEY.equals(str) || AccountConstants.KEY_DEVICE_CREDENTIALS.equals(str);
    }

    public static String j(Context context, String str, String str2) {
        if (str == null || str2 == null) {
            return null;
        }
        return a(ai(context), new Account(str2, str));
    }

    public static void k(Context context, String str, String str2) {
        he b;
        Account o = o(context, str);
        if (o == null || (b = ((gw) ed.M(context).getSystemService("dcp_token_cache_holder")).b(o)) == null) {
            return;
        }
        b.invalidateAuthToken(str2);
    }

    public static Account o(Context context, String str) {
        return ed.M(context).dV().ce(str);
    }

    public static String a(hq hqVar, Account account) {
        if (account == null || !hqVar.d(account)) {
            return null;
        }
        return b(hqVar, account);
    }

    public static String a(gg ggVar, String str) {
        Set<String> eT = ggVar.eT();
        String str2 = str;
        int i = 1;
        while (eT.contains(str2)) {
            i++;
            str2 = String.format(Locale.US, "%s %d", str, Integer.valueOf(i));
        }
        return str2;
    }

    public static Bundle c(Context context, String str, Bundle bundle) {
        Account o = o(context, str);
        return c(o == null ? null : o.name, str, bundle);
    }

    public static Map<String, Account> b(hq hqVar) {
        Account[] accountsByType = hqVar.getAccountsByType(AccountConstants.AMAZON_ACCOUNT_TYPE);
        HashMap hashMap = new HashMap();
        for (Account account : accountsByType) {
            hashMap.put(b(hqVar, account), account);
        }
        return hashMap;
    }

    private static String b(hq hqVar, Account account) {
        String c = hqVar.c(account, "com.amazon.dcp.sso.property.account.acctId");
        if (c == null) {
            String uuid = UUID.randomUUID().toString();
            hqVar.setUserData(account, "com.amazon.dcp.sso.property.account.acctId", uuid);
            return uuid;
        }
        return c;
    }
}
