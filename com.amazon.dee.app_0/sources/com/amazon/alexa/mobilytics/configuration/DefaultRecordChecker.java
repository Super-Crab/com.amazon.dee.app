package com.amazon.alexa.mobilytics.configuration;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.amazon.alexa.mobilytics.event.MobilyticsEvent;
import com.amazon.alexa.mobilytics.identity.MobilyticsUser;
import com.amazon.alexa.mobilytics.identity.MobilyticsUserProvider;
import com.amazon.alexa.mobilytics.marketplace.Marketplace;
import com.amazon.alexa.mobilytics.util.Log;
import com.amazon.alexa.mobilytics.util.Utils;
import com.google.common.base.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.security.SecureRandom;
import java.util.List;
import java.util.regex.Pattern;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes9.dex */
public class DefaultRecordChecker implements RecordChecker {
    private static final String DCM_DEFAULT = "dcm-default";
    private static final String KINESIS_DEFAULT = "OE";
    private static final String TAG = Log.tag(DefaultRecordChecker.class);
    private final ApplicationConfiguration applicationConfiguration;
    private Config config;
    private final ConfigManager configManager;
    private Config defaultConfig;
    private final DefaultDeviceConfiguration defaultDeviceConfiguration;
    private Marketplace marketplace;
    private final SecureRandom random = new SecureRandom();
    private final MobilyticsUserProvider userProvider;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface Regex {
        public static final String ANY = ".*";
        public static final String EMPTY = "<null>";
    }

    @Inject
    public DefaultRecordChecker(@NonNull DefaultDeviceConfiguration defaultDeviceConfiguration, @NonNull ApplicationConfiguration applicationConfiguration, @NonNull ConfigManager configManager, @NonNull MobilyticsUserProvider mobilyticsUserProvider) {
        this.defaultDeviceConfiguration = (DefaultDeviceConfiguration) Preconditions.checkNotNull(defaultDeviceConfiguration);
        this.applicationConfiguration = (ApplicationConfiguration) Preconditions.checkNotNull(applicationConfiguration);
        this.configManager = (ConfigManager) Preconditions.checkNotNull(configManager);
        this.userProvider = (MobilyticsUserProvider) Preconditions.checkNotNull(mobilyticsUserProvider);
        this.defaultConfig = this.configManager.defaultConfig();
    }

    private boolean checkValue(@Nullable String str, @NonNull String str2) {
        return str2.equals(".*") || (str == null && str2.equalsIgnoreCase(Regex.EMPTY)) || (str != null && Pattern.matches(str2, str));
    }

    private boolean compareVersion(@NonNull Config.Blacklist.AppVersion appVersion, @NonNull String str) {
        String version = appVersion.version();
        String compare = appVersion.compare();
        int compareDottedStrings = Utils.compareDottedStrings(str, version);
        if (compareDottedStrings != -1 || !Config.Compare.LESS_THAN.equals(compare)) {
            if (compareDottedStrings == 1 && Config.Compare.GREATER_THAN.equals(compare)) {
                return true;
            }
            return compareDottedStrings == 0 && Config.Compare.EQUAL_TO.equals(compare);
        }
        return true;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Nullable
    private String eventValue(@NonNull MobilyticsEvent mobilyticsEvent, @NonNull String str) {
        char c;
        switch (str.hashCode()) {
            case -1018981992:
                if (str.equals(Config.RouteKeys.EVENT_COMPONENT)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -473110462:
                if (str.equals(Config.RouteKeys.EVENT_SUBCOMPONENT)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 984174864:
                if (str.equals(Config.RouteKeys.EVENT_NAME)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 984376767:
                if (str.equals("event_type")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1123703902:
                if (str.equals(Config.RouteKeys.EVENT_CHANNEL)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c != 0) {
            if (c == 1) {
                return mobilyticsEvent.getEventName();
            }
            if (c == 2) {
                return mobilyticsEvent.getComponent();
            }
            if (c == 3) {
                return mobilyticsEvent.getSubComponent();
            }
            if (c == 4) {
                return mobilyticsEvent.getChannelName();
            }
            return null;
        }
        return mobilyticsEvent.getEventType();
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x0012  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean isEventMatching(@androidx.annotation.NonNull com.amazon.alexa.mobilytics.configuration.Config.Route r3, @androidx.annotation.NonNull com.amazon.alexa.mobilytics.event.MobilyticsEvent r4) {
        /*
            r2 = this;
            java.util.Map r3 = r3.eventPropertyMap()
            java.util.Set r3 = r3.entrySet()
            java.util.Iterator r3 = r3.iterator()
        Lc:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L32
            java.lang.Object r0 = r3.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            java.lang.Object r1 = r0.getKey()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.String r1 = r2.eventValue(r4, r1)
            if (r1 == 0) goto L30
            java.lang.Object r0 = r0.getValue()
            java.lang.String r0 = (java.lang.String) r0
            boolean r0 = java.util.regex.Pattern.matches(r0, r1)
            if (r0 != 0) goto Lc
        L30:
            r3 = 0
            return r3
        L32:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.mobilytics.configuration.DefaultRecordChecker.isEventMatching(com.amazon.alexa.mobilytics.configuration.Config$Route, com.amazon.alexa.mobilytics.event.MobilyticsEvent):boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onUserChanged(@Nullable MobilyticsUser mobilyticsUser) {
        Log.enter();
        if (mobilyticsUser != null) {
            this.marketplace = Marketplace.findMarketplaceById(mobilyticsUser.marketplaceId(), Marketplace.US);
        }
        this.config = this.configManager.config();
        Log.leave();
    }

    @Override // com.amazon.alexa.mobilytics.configuration.RecordChecker
    public void initialize() {
        this.config = this.configManager.config();
        this.userProvider.addListener(new MobilyticsUserProvider.Listener() { // from class: com.amazon.alexa.mobilytics.configuration.-$$Lambda$DefaultRecordChecker$rmXMuLQ0waekH2qOpaerpk28RUE
            @Override // com.amazon.alexa.mobilytics.identity.MobilyticsUserProvider.Listener
            public final void onUserChanged(MobilyticsUser mobilyticsUser) {
                DefaultRecordChecker.this.onUserChanged(mobilyticsUser);
            }
        });
    }

    @Override // com.amazon.alexa.mobilytics.configuration.RecordChecker
    public boolean isBlacklisted(@NonNull MobilyticsEvent mobilyticsEvent) {
        Config config = this.config;
        if (config != null && config.blacklists() != null) {
            List<Config.Blacklist> blacklists = this.config.blacklists();
            String versionName = this.applicationConfiguration.versionName();
            String str = null;
            Marketplace marketplace = this.marketplace;
            if (marketplace != null) {
                str = marketplace.id;
            }
            for (Config.Blacklist blacklist : blacklists) {
                double nextDouble = this.random.nextDouble();
                if (checkValue(mobilyticsEvent.getChannelName(), blacklist.channelName()) && checkValue(mobilyticsEvent.getEventName(), blacklist.eventName()) && checkValue(str, blacklist.marketplace()) && checkValue(this.defaultDeviceConfiguration.operatingSystemType(), blacklist.osType()) && checkValue(mobilyticsEvent.getSubComponent(), blacklist.subcomponent()) && compareVersion(blacklist.appVersion(), versionName) && nextDouble > blacklist.emissionFactor()) {
                    Log.i(TAG, "Event with event name [%s] blacklisted", mobilyticsEvent.getEventName());
                    return true;
                }
            }
        }
        return false;
    }

    @Override // com.amazon.alexa.mobilytics.configuration.RecordChecker
    public boolean shouldBeSent(@NonNull KinesisEndpoint kinesisEndpoint, @NonNull MobilyticsEvent mobilyticsEvent) {
        List<Config.Route> routes;
        if (isBlacklisted(mobilyticsEvent)) {
            return false;
        }
        Config config = this.config;
        if (config != null && config.routes() != null) {
            routes = this.config.routes();
        } else {
            routes = this.defaultConfig.routes();
        }
        for (Config.Route route : routes) {
            if (isEventMatching(route, mobilyticsEvent)) {
                return kinesisEndpoint.tag().equals(route.tag());
            }
        }
        return kinesisEndpoint.tag().equals(KINESIS_DEFAULT);
    }

    @Override // com.amazon.alexa.mobilytics.configuration.RecordChecker
    public boolean shouldBeSent(@NonNull DCMEndpoint dCMEndpoint, @NonNull MobilyticsEvent mobilyticsEvent) {
        List<Config.DcmRoute> dcmRoutes;
        if (isBlacklisted(mobilyticsEvent)) {
            return false;
        }
        Config config = this.config;
        if (config != null && config.dcmRoutes() != null) {
            dcmRoutes = this.config.dcmRoutes();
        } else {
            dcmRoutes = this.defaultConfig.dcmRoutes();
        }
        for (Config.DcmRoute dcmRoute : dcmRoutes) {
            String eventValue = eventValue(mobilyticsEvent, dcmRoute.key());
            if (eventValue != null && Pattern.matches(dcmRoute.value(), eventValue) && dCMEndpoint.tag().equals(dcmRoute.tag())) {
                return true;
            }
        }
        return dCMEndpoint.tag().equals(DCM_DEFAULT);
    }
}
