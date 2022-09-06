package com.amazon.alexa.mobilytics.event.serializer.protobufhandlers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.mobilytics.configuration.ApplicationConfiguration;
import com.amazon.alexa.mobilytics.configuration.DeviceConfiguration;
import com.amazon.alexa.mobilytics.event.EventType;
import com.amazon.alexa.mobilytics.event.MobilyticsEvent;
import com.amazon.alexa.mobilytics.identity.MobilyticsUser;
import com.amazon.alexa.mobilytics.identity.MobilyticsUserProvider;
import com.amazon.alexa.mobilytics.marketplace.Marketplace;
import com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProto;
import com.amazon.alexa.mobilytics.util.Utils;
import com.google.common.base.Preconditions;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONObject;
@Singleton
/* loaded from: classes9.dex */
public class MwsPivotsProtobufHandler implements ProtobufHandler {
    private static final String APP_VERSION = "AppVersion";
    private static final String COUNTRY_CODE = "CountryCode";
    private static final String MARKETPLACE_ID_CODE = "MarketplaceIDCode";
    private static final String OS_TYPE = "OSType";
    private static final String PIVOT_ATTRIBUTE_NULL_VALUE = "NULL";
    private static final String SEPARATOR = ":";
    private final ApplicationConfiguration applicationConfiguration;
    private final DeviceConfiguration deviceConfiguration;
    private Map<String, String> pivots = new HashMap();
    private final MobilyticsUserProvider userProvider;

    @Inject
    public MwsPivotsProtobufHandler(@NonNull ApplicationConfiguration applicationConfiguration, @NonNull DeviceConfiguration deviceConfiguration, @NonNull MobilyticsUserProvider mobilyticsUserProvider) {
        this.applicationConfiguration = (ApplicationConfiguration) Preconditions.checkNotNull(applicationConfiguration);
        this.deviceConfiguration = (DeviceConfiguration) Preconditions.checkNotNull(deviceConfiguration);
        this.userProvider = (MobilyticsUserProvider) Preconditions.checkNotNull(mobilyticsUserProvider);
    }

    private JSONObject generatePivots() {
        MobilyticsUser user = this.userProvider.user();
        String country = this.deviceConfiguration.country();
        String versionName = this.applicationConfiguration.versionName();
        String operatingSystemType = this.deviceConfiguration.operatingSystemType();
        String name = user != null ? Marketplace.findMarketplaceById(user.marketplaceId(), Marketplace.US).name() : "US";
        if (!country.equals(this.pivots.get("CountryCode")) || !name.equals(this.pivots.get("MarketplaceIDCode"))) {
            this.pivots = Utils.computeOneDimensionalPivots(name, country, versionName, operatingSystemType);
        }
        Map<String, String> map = this.pivots;
        if (map == null || map.size() <= 0) {
            return null;
        }
        return new JSONObject((Map) this.pivots);
    }

    @Override // com.amazon.alexa.mobilytics.event.serializer.protobufhandlers.ProtobufHandler
    @Nullable
    public MobilyticsMessageProto serialize(@NonNull MobilyticsEvent mobilyticsEvent) {
        JSONObject generatePivots;
        if (!mobilyticsEvent.getEventType().equals(EventType.OPERATIONAL) || (generatePivots = generatePivots()) == null) {
            return null;
        }
        return MobilyticsMessageProto.newBuilder().setPivots(generatePivots.toString()).mo10084build();
    }
}
