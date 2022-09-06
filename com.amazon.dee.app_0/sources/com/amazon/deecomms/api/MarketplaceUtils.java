package com.amazon.deecomms.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.remoteConfig.RemoteConfigKeys;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes12.dex */
public final class MarketplaceUtils {
    static final Set<String> KNOWN_PFMS;
    private static final Set<String> LOCAL_PFM_BLACKLIST;

    static {
        HashSet hashSet = new HashSet(4);
        hashSet.add("CN");
        hashSet.add("RU");
        LOCAL_PFM_BLACKLIST = Collections.unmodifiableSet(hashSet);
        HashSet hashSet2 = new HashSet(19);
        hashSet2.add("AE");
        hashSet2.add("AU");
        hashSet2.add("BR");
        hashSet2.add("CA");
        GeneratedOutlineSupport1.outline187(hashSet2, "CN", "DE", "ES", "FR");
        GeneratedOutlineSupport1.outline187(hashSet2, "GB", "ID", "IN", "IT");
        GeneratedOutlineSupport1.outline187(hashSet2, "JP", "MX", "NL", "RU");
        hashSet2.add("SA");
        hashSet2.add("TR");
        hashSet2.add("US");
        KNOWN_PFMS = Collections.unmodifiableSet(hashSet2);
    }

    private MarketplaceUtils() {
    }

    @NonNull
    public static String getCommsSupportedPFM(@NonNull String str) {
        return KNOWN_PFMS.contains(str) ? str : "Default";
    }

    public static Set<String> getKnownPfms() {
        return KNOWN_PFMS;
    }

    public static boolean isCommsSupportedInMarketplace(@Nullable String str) {
        return !LOCAL_PFM_BLACKLIST.contains(str) && !isPfmInCloudBlacklist(str);
    }

    static boolean isPfmInCloudBlacklist(@Nullable String str) {
        String configString = CommsDaggerWrapper.getComponent().getArcusConfig().getConfigString(RemoteConfigKeys.ACCESS_PFM_BLACKLIST);
        if (configString != null) {
            for (String str2 : configString.split("\\W")) {
                if (str2.equalsIgnoreCase(str)) {
                    return true;
                }
            }
        }
        return false;
    }
}
