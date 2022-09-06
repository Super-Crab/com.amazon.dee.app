package com.amazon.dee.app.dependencies;

import android.app.Activity;
import android.content.Context;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.handsfree.vendor.bridge.VendorAPIWrapperProvider;
import com.amazon.alexa.voice.handsfree.HandsFreeRoutingAdapter;
import com.amazon.alexa.voice.handsfree.HandsFreeSettingsMetricRecorder;
import com.amazon.alexa.voice.handsfree.HandsFreeSetup;
import com.amazon.alexa.voice.handsfree.settings.providers.SettingsSetupFlowProvider;
import com.amazon.alexa.voice.routing.VoiceRoutingAdapter;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes12.dex */
public class VoiceUiModule {
    @Provides
    @MainScope
    public AMPDInformationProvider provideAMPDInformationProvider(Context context) {
        return AMPDInformationProvider.getInstance(context);
    }

    @Provides
    @MainScope
    public HandsFreeRoutingAdapter provideHandsFreeRoutingAdapter(Activity activity, AMPDInformationProvider aMPDInformationProvider, VendorAPIWrapperProvider vendorAPIWrapperProvider, SettingsSetupFlowProvider settingsSetupFlowProvider, HandsFreeSettingsMetricRecorder handsFreeSettingsMetricRecorder) {
        return new HandsFreeRoutingAdapter(activity, aMPDInformationProvider, vendorAPIWrapperProvider, settingsSetupFlowProvider, handsFreeSettingsMetricRecorder);
    }

    @Provides
    @MainScope
    public HandsFreeSettingsMetricRecorder provideHandsFreeSettingsMetricRecorder(Context context) {
        return new HandsFreeSettingsMetricRecorder(context);
    }

    @Provides
    @MainScope
    public HandsFreeSetup provideHandsFreeSetup(Context context) {
        return new HandsFreeSetup(context);
    }

    @Provides
    @MainScope
    public SettingsSetupFlowProvider provideSettingsSetupFlowProvider() {
        return new SettingsSetupFlowProvider();
    }

    @Provides
    @MainScope
    public VendorAPIWrapperProvider provideVendorAPIWrapperProvider(Context context) {
        return VendorAPIWrapperProvider.getInstance(context);
    }

    @Provides
    @MainScope
    public VoiceRoutingAdapter provideVoiceRoutingAdapter(Activity activity) {
        return new VoiceRoutingAdapter(activity);
    }
}
