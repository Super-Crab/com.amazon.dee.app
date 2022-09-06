package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.dee.app.BuildConfig;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.authorization.RegionUtil;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import java.util.HashSet;
import java.util.Set;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class hr {
    private static final String TAG = "com.amazon.identity.auth.device.hr";

    private hr() {
    }

    public static String H(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String string = bundle.getString("sign_in_domain");
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        String string2 = bundle.getString("com.amazon.identity.ap.domain");
        return TextUtils.isEmpty(string2) ? bundle.getString("com.amazon.dcp.sso.AddAccount.options.AmazonDomain") : string2;
    }

    public static String I(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String string = bundle.getString(MAPAccountManager.KEY_REGISTRATION_DOMAIN);
        return !TextUtils.isEmpty(string) ? string : H(bundle);
    }

    public static String J(Bundle bundle) {
        if (bundle != null) {
            String string = bundle.getString(MAPAccountManager.KEY_REGISTRATION_COOKIE_DOMAIN);
            if (!TextUtils.isEmpty(string)) {
                return string;
            }
        }
        String H = H(bundle);
        return TextUtils.isEmpty(H) ? ".amazon.com" : H;
    }

    public static String bb(String str) {
        return EnvironmentUtils.cd().bb(str);
    }

    public static String c(ed edVar, String str) {
        String b = edVar.dV().b(str, "key_panda_endpoint");
        if (!TextUtils.isEmpty(b)) {
            return b;
        }
        mq.b("getPandaHostForDirectId_FromLegacyDB", new String[0]);
        String b2 = edVar.dV().b(str, "x-amzn-identity-auth-domain");
        if (TextUtils.isEmpty(b2)) {
            b2 = edVar.dV().b(str, "authDomain");
            io.i(TAG, "Cannot get panda registration domain with AccountManagerConstants.PANDA_DOMAIN_KEY, fall back to sign in domain: ".concat(String.valueOf(b2)));
        }
        io.i(TAG, String.format("Use legacy partial domain %s in db to construct Panda host", b2));
        return EnvironmentUtils.cd().getPandaHost(b2);
    }

    public static String cX(String str) {
        if (TextUtils.isEmpty(str)) {
            io.i(TAG, "Empty customer region, returning null domain ");
            return null;
        }
        EnvironmentUtils cd = EnvironmentUtils.cd();
        if (str.equalsIgnoreCase(RegionUtil.REGION_STRING_NA)) {
            return cd.ch();
        }
        if (str.equalsIgnoreCase(RegionUtil.REGION_STRING_EU)) {
            return cd.ci();
        }
        if (str.equalsIgnoreCase(RegionUtil.REGION_STRING_FE)) {
            return cd.cj();
        }
        if (str.equalsIgnoreCase("CN")) {
            return cd.ck();
        }
        io.e(TAG, "Ignoring unknown customer region:  ".concat(str));
        return null;
    }

    public static String cY(String str) {
        if (TextUtils.isEmpty(str)) {
            io.i(TAG, "Empty account pool, returning null domain ");
            return null;
        }
        EnvironmentUtils cd = EnvironmentUtils.cd();
        if (str.equalsIgnoreCase("Amazon")) {
            return cd.ch();
        }
        if (str.equalsIgnoreCase("AmazonCN")) {
            return cd.ck();
        }
        if (str.equalsIgnoreCase("AmazonJP")) {
            return cd.cj();
        }
        io.e(TAG, "Ignoring unknown account pool:  ".concat(str));
        return null;
    }

    public static String cZ(String str) {
        if (TextUtils.isEmpty(str)) {
            io.dm(TAG);
            return ".amazon.com";
        } else if (str.startsWith(".")) {
            return str;
        } else {
            if (str.startsWith("amazon.")) {
                return ".".concat(str);
            }
            if (str.startsWith("www")) {
                return str.substring(3);
            }
            if (str.contains(".amazon")) {
                return str.substring(str.indexOf(".amazon"));
            }
            throw new RuntimeException("Input was non-empty and doesn't look like a valid url: ".concat(str));
        }
    }

    public static String da(String str) {
        if (TextUtils.equals("na.account.amazon.com", str)) {
            mq.incrementCounterAndRecord("ConvertLWADomain:NA", new String[0]);
            return BuildConfig.AUTH_HOST;
        } else if (TextUtils.equals("eu.account.amazon.com", str)) {
            mq.incrementCounterAndRecord("ConvertLWADomain:EU", new String[0]);
            return BuildConfig.AUTH_HOST_UK;
        } else if (!TextUtils.equals("apac.account.amazon.com", str)) {
            return str;
        } else {
            mq.incrementCounterAndRecord("ConvertLWADomain:FE", new String[0]);
            return BuildConfig.AUTH_HOST_JP;
        }
    }

    public static Set<String> i(Set<String> set) {
        int indexOf;
        HashSet hashSet = new HashSet();
        for (String str : set) {
            String bb = EnvironmentUtils.cd().bb(str);
            if (!TextUtils.isEmpty(bb) && (indexOf = bb.indexOf(58)) != -1) {
                bb = bb.substring(0, indexOf);
            }
            hashSet.add(bb);
        }
        return hashSet;
    }

    public static String m(Context context, String str) {
        String b = ed.M(context).dV().b(str, "key_auth_portal_endpoint");
        if (!TextUtils.isEmpty(b)) {
            return b;
        }
        mq.b("getAuthPortalHostForDirectedId_FromLegacyDB", new String[0]);
        return EnvironmentUtils.cd().ba(ed.M(context).dV().b(str, "authDomain"));
    }

    public static String n(Context context, String str) {
        String b = ed.M(context).dV().b(str, "key_panda_marketplace_header");
        if (!TextUtils.isEmpty(b)) {
            return b;
        }
        mq.b("getMarketplaceHeaderForDirectedId_FromLegacyDB", new String[0]);
        return EnvironmentUtils.cd().getPandaHost(ed.M(context).dV().b(str, "authDomain"));
    }
}
