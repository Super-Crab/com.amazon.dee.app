package com.amazon.identity.auth.device;

import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.dee.app.BuildConfig;
import com.amazon.deecomms.common.Constants;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.locale.LocaleInfo;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class cn extends EnvironmentUtils {
    private static final String TAG = "com.amazon.identity.auth.device.cn";

    static {
        EnvironmentUtils.iw.put(BuildConfig.AUTH_HOST_MX, "amzn_device_mx");
        EnvironmentUtils.iw.put(BuildConfig.AUTH_HOST_UK, "amzn_device_uk");
        EnvironmentUtils.iw.put(BuildConfig.AUTH_HOST_CA, "amzn_device_ca");
        EnvironmentUtils.iw.put(BuildConfig.AUTH_HOST_DE, "amzn_device_de");
        EnvironmentUtils.iw.put(BuildConfig.AUTH_HOST_FR, "amzn_device_fr");
        EnvironmentUtils.iw.put(BuildConfig.AUTH_HOST_IT, "amzn_device_it");
        EnvironmentUtils.iw.put(BuildConfig.AUTH_HOST_IN, "amzn_device_in");
        EnvironmentUtils.iw.put(BuildConfig.AUTH_HOST_ES, "amzn_device_es");
        EnvironmentUtils.iw.put(BuildConfig.AUTH_HOST_JP, "amzn_device_jp");
        EnvironmentUtils.iw.put(BuildConfig.AUTH_HOST_CN, "amzn_device_cn");
        EnvironmentUtils.iw.put(BuildConfig.AUTH_HOST_BR, "amzn_device_br");
        EnvironmentUtils.iw.put(BuildConfig.AUTH_HOST_NL, "amzn_device_nl");
        EnvironmentUtils.iw.put(BuildConfig.AUTH_HOST_AU, "amzn_device_au");
        EnvironmentUtils.iw.put(BuildConfig.AUTH_HOST_RU, "amzn_device_ru");
        EnvironmentUtils.iw.put("www.amazon.com.sa", "amzn_device_sa");
        EnvironmentUtils.iw.put("www.amazon.ae", "amzn_device_ae");
        EnvironmentUtils.iw.put("www.amazon.se", "amzn_device_se");
        EnvironmentUtils.iw.put("www.amazon.pl", "amzn_device_pl");
    }

    @Override // com.amazon.identity.auth.device.env.EnvironmentUtils
    public String ba(String str) {
        if (TextUtils.isEmpty(str)) {
            io.dm(TAG);
            return BuildConfig.AUTH_HOST;
        } else if (str.startsWith(".")) {
            return "www".concat(str);
        } else {
            if (str.startsWith("amazon.")) {
                return "www.".concat(str);
            }
            if (str.startsWith("www")) {
                return str;
            }
            throw new RuntimeException("Input was non-empty and doesn't look like a valid partial domain in production: " + str + ". If you were attempting to hit devo or pre-prod, please try following the steps to switch again.");
        }
    }

    @Override // com.amazon.identity.auth.device.env.EnvironmentUtils
    public String bb(String str) {
        if (str.startsWith("www.")) {
            return str.substring(3);
        }
        return !str.startsWith(".") ? ".".concat(str) : str;
    }

    @Override // com.amazon.identity.auth.device.env.EnvironmentUtils
    public boolean bc(String str) {
        return str.matches("api.amazon.*");
    }

    @Override // com.amazon.identity.auth.device.env.EnvironmentUtils
    public String ch() {
        return ".amazon.com";
    }

    @Override // com.amazon.identity.auth.device.env.EnvironmentUtils
    public String ci() {
        return Constants.AUTH_DOMAIN_UK;
    }

    @Override // com.amazon.identity.auth.device.env.EnvironmentUtils
    public String cj() {
        return Constants.AUTH_DOMAIN_JP;
    }

    @Override // com.amazon.identity.auth.device.env.EnvironmentUtils
    public String ck() {
        return ".amazon.cn";
    }

    @Override // com.amazon.identity.auth.device.env.EnvironmentUtils
    public String cl() {
        return BuildConfig.AUTH_HOST;
    }

    @Override // com.amazon.identity.auth.device.env.EnvironmentUtils
    public String cm() {
        return "firs-ta-g7g.amazon.com";
    }

    @Override // com.amazon.identity.auth.device.env.EnvironmentUtils
    public String cn() {
        return "dcape-na.amazon.com";
    }

    @Override // com.amazon.identity.auth.device.env.EnvironmentUtils
    public String co() {
        return LocaleInfo.ObfuscatedMarketplaceId.US_MARKETPLACE_ID;
    }

    @Override // com.amazon.identity.auth.device.env.EnvironmentUtils
    public String getPandaHost(String str) {
        if (TextUtils.isEmpty(str)) {
            io.dm(TAG);
            return "api.amazon.com";
        } else if (str.startsWith(".")) {
            return "api".concat(str);
        } else {
            if (str.startsWith("amazon.")) {
                return "api.".concat(str);
            }
            if (str.startsWith("api.")) {
                return str;
            }
            if (str.startsWith("www")) {
                return GeneratedOutlineSupport1.outline55(str, 3, new StringBuilder("api"));
            }
            throw new RuntimeException("Input was non-empty and doesn't look like a valid partial domain in production: " + str + ". If you were attempting to hit devo or pre-prod, please try following the steps to switch again.");
        }
    }

    @Override // com.amazon.identity.auth.device.env.EnvironmentUtils
    public String w(Bundle bundle) {
        return y(bundle);
    }

    @Override // com.amazon.identity.auth.device.env.EnvironmentUtils
    public String z(Bundle bundle) {
        return x(bundle);
    }
}
