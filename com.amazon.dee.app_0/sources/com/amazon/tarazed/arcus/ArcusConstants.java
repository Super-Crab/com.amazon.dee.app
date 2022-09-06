package com.amazon.tarazed.arcus;

import com.amazon.tarazed.type.Marketplace;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.joda.time.DateTimeConstants;
import org.json.JSONObject;
/* loaded from: classes13.dex */
public class ArcusConstants {
    public static final String APP_ID = "arn:aws:remote-config:us-west-2:617769722613:appConfig:a9nagb6s";
    static final int DEFAULT_ARCUS_SYNC_INTERVAL_SEC = 86400;
    private static final int DEFAULT_FORCE_SYNC_MAX_INITIAL_DELAY_SEC = 86400;
    static final String DEFAULT_HELP_CONTENT_URL = "https://www.amazon.com/gp/help/customer/display.html?ie=UTF8&nodeId=GPXURFBMZKPVCQET";
    public static final JSONObject DEFAULT_SUPPORTED_CONFIGURATION = new JSONObject((Map) new HashMap<String, Object>() { // from class: com.amazon.tarazed.arcus.ArcusConstants.1
        {
            put(ArcusConstants.KEY_IS_TARAZED_ENABLED, Boolean.TRUE);
            put(ArcusConstants.KEY_NOTIFICATION_HOSTNAME, ArcusConstants.getDefaultHostnameByMarketplaceMap());
            Integer valueOf = Integer.valueOf((int) DateTimeConstants.SECONDS_PER_DAY);
            put(ArcusConstants.KEY_FORCE_SYNC_MAX_INITIAL_DELAY_SEC, valueOf);
            put(ArcusConstants.KEY_ARCUS_SYNC_INTERVAL_SEC, valueOf);
            put(ArcusConstants.KEY_HELP_CONTENT_URL, ArcusConstants.getDefaultHelpContentUrlByMarketplaceMap());
        }
    });
    public static final JSONObject DEFAULT_UNSUPPORTED_CONFIGURATION = new JSONObject((Map) new HashMap<String, Object>() { // from class: com.amazon.tarazed.arcus.ArcusConstants.2
        {
            put(ArcusConstants.KEY_IS_TARAZED_ENABLED, Boolean.FALSE);
        }
    });
    private static final String DUB_NOTIFICATION_HOSTNAME = "dub.notification.mayday-screen-sharing.cs.a2z.com";
    private static final String IAD_NOTIFICATION_HOSTNAME = "iad.notification.mayday-screen-sharing.cs.a2z.com";
    static final String KEY_ARCUS_SYNC_INTERVAL_SEC = "arcusSyncIntervalSec";
    public static final String KEY_END_SESSION_TIMEOUT_ON_3P_APP_BACKGROUND = "endSessionTimeoutOn3PAppBackground";
    public static final String KEY_FORCE_SYNC_MAX_INITIAL_DELAY_SEC = "forceSyncMaxInitialDelaySec";
    static final String KEY_HELP_CONTENT_URL = "helpContentUrl";
    static final String KEY_IS_HARDWARE_VIDEO_ENCODING_ENABLED = "isHardwareVideoEncodingEnabled";
    public static final String KEY_IS_TARAZED_ENABLED = "isTarazedEnabled";
    static final String KEY_MAX_VIDEO_BITRATE_KBPS = "maxVideoBitrateKbps";
    static final String KEY_MAX_VIDEO_DIMENSION = "maxVideoDimension";
    static final String KEY_MIN_VIDEO_DIMENSION = "minVideoDimension";
    static final String KEY_NOTIFICATION_HOSTNAME = "notificationHostnameByMarketplace";
    static final String KEY_PRIMER_TIMEOUT_SEC = "primerTimeOutSec";
    static final String KEY_VIDEO_FRAMERATE = "videoFramerate";
    private static final String PDX_NOTIFICATION_HOSTNAME = "pdx.notification.mayday-screen-sharing.cs.a2z.com";
    static final int RESULT_FAIL = 1;
    static final int RESULT_OK = 0;

    private static String formatHelpContentUrl(String str) {
        return DEFAULT_HELP_CONTENT_URL.replace(".com", str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Map<String, String> getDefaultHelpContentUrlByMarketplaceMap() {
        HashMap hashMap = new HashMap();
        hashMap.put(Marketplace.US.getProdMarketplaceId(), DEFAULT_HELP_CONTENT_URL);
        hashMap.put(Marketplace.CA.getProdMarketplaceId(), formatHelpContentUrl(".ca"));
        hashMap.put(Marketplace.UK.getProdMarketplaceId(), formatHelpContentUrl(".co.uk"));
        hashMap.put(Marketplace.DE.getProdMarketplaceId(), formatHelpContentUrl(".de"));
        hashMap.put(Marketplace.FR.getProdMarketplaceId(), formatHelpContentUrl(".fr"));
        hashMap.put(Marketplace.ES.getProdMarketplaceId(), formatHelpContentUrl(".es"));
        hashMap.put(Marketplace.ES.getDevoMarketplaceId(), formatHelpContentUrl(".es"));
        hashMap.put(Marketplace.IN.getProdMarketplaceId(), formatHelpContentUrl(".in"));
        hashMap.put(Marketplace.IN.getDevoMarketplaceId(), formatHelpContentUrl(".in"));
        hashMap.put(Marketplace.IT.getProdMarketplaceId(), formatHelpContentUrl(".it"));
        hashMap.put(Marketplace.IT.getDevoMarketplaceId(), formatHelpContentUrl(".it"));
        hashMap.put(Marketplace.JP.getProdMarketplaceId(), formatHelpContentUrl(".co.jp"));
        hashMap.put(Marketplace.AU.getProdMarketplaceId(), formatHelpContentUrl(".com.au"));
        return Collections.unmodifiableMap(hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Map<String, String> getDefaultHostnameByMarketplaceMap() {
        HashMap hashMap = new HashMap();
        hashMap.put(Marketplace.US.getProdMarketplaceId(), IAD_NOTIFICATION_HOSTNAME);
        hashMap.put(Marketplace.CA.getProdMarketplaceId(), IAD_NOTIFICATION_HOSTNAME);
        hashMap.put(Marketplace.UK.getProdMarketplaceId(), DUB_NOTIFICATION_HOSTNAME);
        hashMap.put(Marketplace.DE.getProdMarketplaceId(), DUB_NOTIFICATION_HOSTNAME);
        hashMap.put(Marketplace.FR.getProdMarketplaceId(), DUB_NOTIFICATION_HOSTNAME);
        hashMap.put(Marketplace.ES.getProdMarketplaceId(), DUB_NOTIFICATION_HOSTNAME);
        hashMap.put(Marketplace.ES.getDevoMarketplaceId(), DUB_NOTIFICATION_HOSTNAME);
        hashMap.put(Marketplace.IN.getProdMarketplaceId(), DUB_NOTIFICATION_HOSTNAME);
        hashMap.put(Marketplace.IN.getDevoMarketplaceId(), DUB_NOTIFICATION_HOSTNAME);
        hashMap.put(Marketplace.IT.getProdMarketplaceId(), DUB_NOTIFICATION_HOSTNAME);
        hashMap.put(Marketplace.IT.getDevoMarketplaceId(), DUB_NOTIFICATION_HOSTNAME);
        hashMap.put(Marketplace.JP.getProdMarketplaceId(), PDX_NOTIFICATION_HOSTNAME);
        hashMap.put(Marketplace.AU.getProdMarketplaceId(), PDX_NOTIFICATION_HOSTNAME);
        return Collections.unmodifiableMap(hashMap);
    }
}
