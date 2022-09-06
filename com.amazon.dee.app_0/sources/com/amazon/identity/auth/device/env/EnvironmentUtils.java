package com.amazon.identity.auth.device.env;

import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.deecomms.common.Constants;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.cn;
import com.amazon.identity.auth.device.hr;
import com.amazon.identity.auth.device.io;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public abstract class EnvironmentUtils {
    public static final Map<String, String> iw = new HashMap();
    static final Map<String, String> ix = new HashMap();
    static volatile EnvironmentUtils iy = new cn();
    private static final String TAG = EnvironmentUtils.class.getName();

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public enum Environment {
        prod
    }

    static {
        ix.put(".amazon.com", ".amazon.com");
        ix.put(Constants.AUTH_DOMAIN_CA, Constants.AUTH_DOMAIN_CA);
        ix.put(Constants.AUTH_DOMAIN_BR, Constants.AUTH_DOMAIN_BR);
        ix.put(Constants.AUTH_DOMAIN_MX, Constants.AUTH_DOMAIN_MX);
        ix.put(Constants.AUTH_DOMAIN_AU, Constants.AUTH_DOMAIN_AU);
        ix.put(Constants.AUTH_DOMAIN_JP, Constants.AUTH_DOMAIN_JP);
        ix.put(".amazon.com.sg", Constants.AUTH_DOMAIN_JP);
        ix.put(".amazon.sg", Constants.AUTH_DOMAIN_JP);
        ix.put(".amazon.cn", ".amazon.cn");
        ix.put(Constants.AUTH_DOMAIN_NL, Constants.AUTH_DOMAIN_NL);
        ix.put(Constants.AUTH_DOMAIN_IT, Constants.AUTH_DOMAIN_IT);
        ix.put(Constants.AUTH_DOMAIN_DE, Constants.AUTH_DOMAIN_DE);
        ix.put(Constants.AUTH_DOMAIN_UK, Constants.AUTH_DOMAIN_UK);
        ix.put(Constants.AUTH_DOMAIN_ES, Constants.AUTH_DOMAIN_ES);
        ix.put(Constants.AUTH_DOMAIN_FR, Constants.AUTH_DOMAIN_FR);
        ix.put(Constants.AUTH_DOMAIN_IN, Constants.AUTH_DOMAIN_IN);
        ix.put(".amazon.com.tr", Constants.AUTH_DOMAIN_UK);
        ix.put(".amazon.eg", Constants.AUTH_DOMAIN_UK);
        ix.put(".amazon.ae", Constants.AUTH_DOMAIN_UK);
        ix.put(".amazon.sa", Constants.AUTH_DOMAIN_UK);
        ix.put(".amazon.se", Constants.AUTH_DOMAIN_UK);
        ix.put(".amazon.pl", Constants.AUTH_DOMAIN_UK);
    }

    public static EnvironmentUtils cd() {
        return iy;
    }

    public static void ce() {
        Environment environment = Environment.prod;
        iy = new cn();
    }

    public static boolean cf() {
        return true;
    }

    public static Set<String> cg() {
        HashSet hashSet = new HashSet(ix.keySet());
        GeneratedOutlineSupport1.outline187(hashSet, ".primevideo.com", ".primevideo.co.uk", ".primevideo.co.jp", ".primevideo.de");
        hashSet.add(".primevideo.in");
        return hashSet;
    }

    public String A(Bundle bundle) {
        Bundle bundle2;
        if (bundle != null && (bundle2 = bundle.getBundle(MAPAccountManager.KEY_MARKETPLACE_BUNDLE)) != null) {
            String string = bundle2.getString(MAPAccountManager.KEY_MARKETPLACE_DOMAIN);
            if (!TextUtils.isEmpty(string)) {
                io.i(TAG, "Using client passed marketplace domain root: ".concat(String.valueOf(string)));
                return string;
            }
        }
        return bf(hr.H(bundle));
    }

    public abstract String ba(String str);

    public abstract String bb(String str);

    public abstract boolean bc(String str);

    public String bd(String str) {
        return iw.get(str);
    }

    public String be(String str) {
        if (TextUtils.isEmpty(str)) {
            return ch();
        }
        String bb = bb(str);
        if (!ix.containsKey(bb)) {
            return null;
        }
        return ix.get(bb);
    }

    public String bf(String str) {
        return getPandaHost(str);
    }

    public abstract String ch();

    public abstract String ci();

    public abstract String cj();

    public abstract String ck();

    public abstract String cl();

    public abstract String cm();

    public abstract String cn();

    public abstract String co();

    public abstract String getPandaHost(String str);

    public URL n(String str, String str2) throws MalformedURLException {
        return new URL("https", str, 443, str2);
    }

    protected abstract String w(Bundle bundle);

    public String x(Bundle bundle) {
        String w = w(bundle);
        return !TextUtils.isEmpty(w) ? w : ba(hr.H(bundle));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String y(Bundle bundle) {
        if (bundle != null) {
            String string = bundle.getString(MAPAccountManager.KEY_SIGN_IN_ENDPOINT);
            if (TextUtils.isEmpty(string)) {
                return null;
            }
            return string;
        }
        return null;
    }

    public abstract String z(Bundle bundle);
}
