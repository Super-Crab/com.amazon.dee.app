package com.amazon.dee.app.services.photos;

import android.util.Log;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.location.utils.EndpointsUtil;
import com.amazon.dee.app.BuildConfig;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.http.UrlResolver;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes12.dex */
public class AlexaPhotosBackgroundServiceUrlResolver implements UrlResolver {
    private static final String ALPHA_KEY = "alpha";
    private static final String BETA_KEY = "beta";
    private static final String GAMMA_KEY = "gamma";
    private static final String PROD_KEY = "prod";
    private static final String TAG = "AlexaPhotosUrlResolver";
    private static final String defaultEndpoint = "https://api.amazonalexa.com";
    private static final Map<String, Map<String, String>> urlConfiguration = new HashMap();
    private final IdentityService identityService;

    static {
        HashMap outline134 = GeneratedOutlineSupport1.outline134("BR", "https://apbs-1ie-pdx-pd.integ.amazon.com", "IN", "https://apbs-1ie-pdx-pd.integ.amazon.com");
        outline134.put("MX", "https://apbs-1ie-pdx-pd.integ.amazon.com");
        urlConfiguration.put("alpha", outline134);
        HashMap hashMap = new HashMap();
        hashMap.put("BR", EndpointsUtil.ENDPOINT_NA_BETA);
        hashMap.put("IN", EndpointsUtil.ENDPOINT_EU_BETA);
        hashMap.put("MX", EndpointsUtil.ENDPOINT_NA_BETA);
        urlConfiguration.put("beta", hashMap);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("BR", EndpointsUtil.ENDPOINT_NA_GAMMA);
        hashMap2.put("IN", EndpointsUtil.ENDPOINT_EU_GAMMA);
        hashMap2.put("MX", EndpointsUtil.ENDPOINT_NA_GAMMA);
        urlConfiguration.put("gamma", hashMap2);
        HashMap hashMap3 = new HashMap();
        hashMap3.put("BR", "https://api.amazonalexa.com");
        hashMap3.put("IN", EndpointsUtil.ENDPOINT_EU_PROD);
        hashMap3.put("MX", "https://api.amazonalexa.com");
        urlConfiguration.put("prod", hashMap3);
    }

    public AlexaPhotosBackgroundServiceUrlResolver(IdentityService identityService) {
        this.identityService = identityService;
    }

    @Override // com.dee.app.http.UrlResolver
    public String resolve(String str) {
        String str2;
        if (str == null || !str.startsWith("http")) {
            if (this.identityService.getUser(TAG) == null) {
                Log.e(TAG, "User Identity is null while uploading background image");
                return null;
            }
            String countryCode = this.identityService.getUser(TAG).getEffectiveMarketplace().getCountryCode().toString();
            if (BuildConfig.IS_ALPHA_ENVIRONMENT) {
                str2 = "alpha";
            } else if (BuildConfig.IS_BETA_ENVIRONMENT) {
                str2 = "beta";
            } else {
                str2 = BuildConfig.IS_GAMMA_ENVIRONMENT ? "gamma" : "prod";
            }
            String str3 = urlConfiguration.get(str2).get(countryCode);
            if (str3 == null) {
                str3 = "https://api.amazonalexa.com";
            }
            return GeneratedOutlineSupport1.outline72(str3, str);
        }
        return str;
    }
}
