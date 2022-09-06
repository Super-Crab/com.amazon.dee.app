package com.amazon.identity.auth.device;

import android.content.Context;
import com.amazon.identity.auth.attributes.CORPFMResponse;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.locale.LocaleInfo;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class aq {
    private static final String TAG = "com.amazon.identity.auth.device.aq";
    private final Context mContext;

    public aq(Context context) {
        this.mContext = context;
    }

    private String an(String str) {
        return aw().cs(str);
    }

    private gp aw() {
        return new gp(this.mContext, "default_cor_pfm_store");
    }

    private String c(String str, String str2) {
        io.dm(TAG);
        String an = an(str);
        if (an != null) {
            String str3 = TAG;
            String.format("Returning Device Default Cor/Pfm from Shared Preference for key: %s and value: %s", str, an);
            io.dm(str3);
            return an;
        }
        String str4 = TAG;
        String.format("Returning Device Default Cor/Pfm from default settings for key: %s and value: %s", str, str2);
        io.dm(str4);
        return str2;
    }

    public void a(CORPFMResponse cORPFMResponse) {
        gp aw = aw();
        aw.U("default.cor", cORPFMResponse.ai());
        aw.U("default.pfm", cORPFMResponse.ak());
    }

    public boolean as() {
        gp aw = aw();
        return aw.contains("default.cor") || aw.contains("default.pfm");
    }

    public String at() {
        return c("default.cor", "US");
    }

    public String au() {
        return c("default.pfm", LocaleInfo.ObfuscatedMarketplaceId.US_MARKETPLACE_ID);
    }

    public CORPFMResponse av() {
        return new CORPFMResponse(at(), au(), CORPFMResponse.ComputationConfidenceValue.DEVICE_BASED_GUESS, (Long) (-1L));
    }
}
