package com.amazon.alexa;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.WindowManager;
import com.amazon.alexa.auth.AccountManager;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.client.core.configuration.Feature;
import com.amazon.alexa.client.core.device.PersistentStorage;
import com.amazon.alexa.client.core.marketplace.MarketplaceAuthority;
import com.amazon.alexa.client.metrics.core.AlexaMetricsConstants;
import com.amazon.alexa.client.metrics.core.AppInformation;
import com.amazon.alexa.client.metrics.core.DeviceInformation;
import com.amazon.alexa.client.metrics.core.DirectedIDProvider;
import com.amazon.alexa.client.metrics.core.MetricsConnector;
import com.amazon.alexa.client.metrics.core.MetricsStatusProvider;
import com.amazon.alexa.client.metrics.dcm.DCMConfiguration;
import com.amazon.alexa.feature.consumer.api.FeatureQuery;
import com.amazon.alexa.preload.attribution.DefaultPreloadAttributionManager;
import com.amazon.alexa.preload.attribution.PreloadAttributionManager;
import com.amazon.alexa.utils.TimeProvider;
import com.amazon.client.metrics.common.AndroidMetricsFactoryImpl;
import com.amazon.client.metrics.common.MetricsFactory;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Regions;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import java.util.Arrays;
import javax.inject.Named;
import javax.inject.Singleton;
/* compiled from: MetricsModule.java */
@Module
/* loaded from: classes.dex */
public class kbj {
    @Provides
    @Singleton
    @Named(AlexaMetricsConstants.MetricsComponents.KINESIS)
    public MetricsStatusProvider BIo(Lazy<gSO> lazy) {
        return new taB(lazy, Feature.ALEXA_VOX_ANDROID_DISABLE_MOBILYTICS_V2_ATTEMPT_2);
    }

    @Provides
    @Singleton
    @Named("voice_interaction")
    public PersistentStorage jiA(Context context) {
        return new EKZ(context.getSharedPreferences("voice_interaction", 0));
    }

    @Provides
    @Singleton
    @Named(AlexaMetricsConstants.MetricsComponents.MOBILYTICS_V2)
    public MetricsStatusProvider zQM(Lazy<gSO> lazy) {
        return new taB(lazy, Feature.ALEXA_VOX_ANDROID_DISABLE_MOBILYTICS_V2_ATTEMPT_2, true);
    }

    @Provides
    @Singleton
    public DirectedIDProvider zZm(tol tolVar) {
        return tolVar;
    }

    @Provides
    @Singleton
    public TimeProvider zZm() {
        return new TimeProvider();
    }

    @Provides
    @Singleton
    @Named("text_interaction")
    public PersistentStorage zyO(Context context) {
        return new EKZ(context.getSharedPreferences("text_interaction", 0));
    }

    @Provides
    @Singleton
    @Named("device.information")
    public PersistentStorage BIo(Context context) {
        return new EKZ(context.getSharedPreferences("device.information", 0));
    }

    @Provides
    @Singleton
    @Named(AlexaMetricsConstants.MetricsComponents.METRICS_DATA)
    public PersistentStorage zQM(Context context) {
        return new EKZ(context.getSharedPreferences(AlexaMetricsConstants.MetricsComponents.METRICS_DATA, 0));
    }

    @Provides
    @Singleton
    public Fsz zZm(Context context, AlexaClientEventBus alexaClientEventBus, qxC qxc, @Named("service.metrics.alexaservice") Lazy<PersistentStorage> lazy, paF paf, TimeProvider timeProvider, DeviceInformation deviceInformation, PreloadAttributionManager preloadAttributionManager, C0211jTe c0211jTe, Kcd kcd, PackageManager packageManager, PWS pws, bwE bwe, KPv kPv, GQk gQk, IUt iUt, Dtt dtt) {
        return new Fsz(context, alexaClientEventBus, qxc, lazy, paf, timeProvider, deviceInformation, preloadAttributionManager, c0211jTe, kcd, packageManager, pws, bwe, kPv, gQk, iUt, dtt);
    }

    @Provides
    @Singleton
    public pbo zZm(Context context, AlexaClientEventBus alexaClientEventBus) {
        return new pbo(context, alexaClientEventBus);
    }

    @Provides
    @Singleton
    public DeviceInformation zZm(Context context, TelephonyManager telephonyManager, WindowManager windowManager, ActivityManager activityManager, qxC qxc, AppInformation appInformation, @Named("device.information") Lazy<PersistentStorage> lazy) {
        return new DeviceInformation(context, activityManager, telephonyManager, windowManager, qxc, appInformation, lazy, new ZGX(this));
    }

    @Provides
    @Singleton
    public paF zZm(DeviceInformation deviceInformation, @Named("service.metrics.alexaservice") Lazy<PersistentStorage> lazy, @Named("DCM") MetricsConnector metricsConnector, @Named("kinesis") MetricsConnector metricsConnector2, @Named("mobilytics_v2") MetricsConnector metricsConnector3, Lazy<PreloadAttributionManager> lazy2, MarketplaceAuthority marketplaceAuthority, @Named("androidId") String str) {
        return new paF(deviceInformation, lazy, Arrays.asList(metricsConnector, metricsConnector2, metricsConnector3), lazy2, marketplaceAuthority, str);
    }

    @Provides
    @Singleton
    @Named(AlexaMetricsConstants.MetricsComponents.DCM)
    public MetricsStatusProvider zZm(Lazy<gSO> lazy) {
        return new taB(lazy, Feature.ALEXA_VOX_ANDROID_DISABLE_MOBILYTICS_V2_ATTEMPT_2);
    }

    @Provides
    @Singleton
    public MetricsFactory zZm(Context context, DeviceInformation deviceInformation, Lazy<ClientConfiguration> lazy) {
        MetricsFactory androidMetricsFactoryImpl = AndroidMetricsFactoryImpl.getInstance(context);
        DCMConfiguration dCMConfiguration = new DCMConfiguration(lazy);
        AndroidMetricsFactoryImpl.setMetricsConfiguration(dCMConfiguration.getMetricsConfiguration());
        AndroidMetricsFactoryImpl.setDeviceType(context, dCMConfiguration.getDeviceType());
        AndroidMetricsFactoryImpl.setDeviceId(context, deviceInformation.getId());
        return androidMetricsFactoryImpl;
    }

    @Provides
    @Singleton
    public MarketplaceAuthority zZm(AccountManager accountManager, @Named("service.metrics.alexaservice") Lazy<PersistentStorage> lazy) {
        return new MarketplaceAuthority(accountManager, lazy);
    }

    @Provides
    @Singleton
    public PreloadAttributionManager zZm(Context context, FeatureQuery featureQuery) {
        return new DefaultPreloadAttributionManager(context, null, new Zrn(this, featureQuery));
    }

    @Provides
    @Singleton
    public AWSCredentialsProvider zZm(Context context, Lazy<ClientConfiguration> lazy) {
        return new CognitoCachingCredentialsProvider(context, lazy.mo358get().getMobilyticsKinesisCognitoIdentityPoolId(), Regions.fromName(lazy.mo358get().getMobilyticsKinesisRegion()));
    }

    @Provides
    @SuppressLint({"HardwareIds"})
    @Named("androidId")
    @Singleton
    public String zZm(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), "android_id");
    }
}
