package com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.configuration.Configuration;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.configuration.PreferencesConfiguration;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.http.SDKInfoHandler;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.idresolver.Id;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.idresolver.SharedPrefsUniqueIdService;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.idresolver.UniqueIdService;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.AndroidSystem;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.System;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.util.SDKInfo;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery.DefaultDeliveryClient;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery.DeliveryClient;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.mobileanalytics.AmazonMobileAnalyticsClient;
@Deprecated
/* loaded from: classes13.dex */
public class DefaultAnalyticsContext implements AnalyticsContext {
    private final Configuration configuration;
    private final Context context;
    private final DeliveryClient deliveryClient;
    private final AmazonMobileAnalyticsClient ersClient;
    private final SDKInfo sdkInfo;
    private final System system;
    private Id uniqueId;
    private final UniqueIdService uniqueIdService;

    public DefaultAnalyticsContext(AmazonMobileAnalyticsClient amazonMobileAnalyticsClient, Context context, Regions regions, String str, SDKInfo sDKInfo, boolean z) {
        this.sdkInfo = sDKInfo;
        this.system = new AndroidSystem(context, str);
        this.uniqueIdService = new SharedPrefsUniqueIdService(str, context);
        this.uniqueId = this.uniqueIdService.getUniqueId(this);
        amazonMobileAnalyticsClient.setServiceNameIntern("mobileanalytics");
        amazonMobileAnalyticsClient.setRegion(Region.getRegion(regions));
        this.ersClient = amazonMobileAnalyticsClient;
        amazonMobileAnalyticsClient.addRequestHandler(new SDKInfoHandler(sDKInfo));
        this.configuration = PreferencesConfiguration.newInstance(this);
        this.deliveryClient = DefaultDeliveryClient.newInstance(this, z);
        this.context = context;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.AnalyticsContext
    public Configuration getConfiguration() {
        return this.configuration;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.AnalyticsContext
    public DeliveryClient getDeliveryClient() {
        return this.deliveryClient;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.AnalyticsContext
    public AmazonMobileAnalyticsClient getERSClient() {
        return this.ersClient;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.AnalyticsContext
    public String getNetworkType() {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.context.getSystemService("connectivity")).getActiveNetworkInfo();
            return (activeNetworkInfo == null || !activeNetworkInfo.isConnected() || !activeNetworkInfo.isAvailable() || activeNetworkInfo.getTypeName() == null) ? "Unknown" : activeNetworkInfo.getTypeName();
        } catch (Exception unused) {
            return "Unknown";
        }
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.AnalyticsContext
    public SDKInfo getSDKInfo() {
        return this.sdkInfo;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.AnalyticsContext
    public System getSystem() {
        return this.system;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.AnalyticsContext
    public Id getUniqueId() {
        return this.uniqueId;
    }
}
