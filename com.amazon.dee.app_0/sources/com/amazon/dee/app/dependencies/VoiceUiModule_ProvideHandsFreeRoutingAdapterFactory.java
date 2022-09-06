package com.amazon.dee.app.dependencies;

import android.app.Activity;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.handsfree.vendor.bridge.VendorAPIWrapperProvider;
import com.amazon.alexa.voice.handsfree.HandsFreeRoutingAdapter;
import com.amazon.alexa.voice.handsfree.HandsFreeSettingsMetricRecorder;
import com.amazon.alexa.voice.handsfree.settings.providers.SettingsSetupFlowProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class VoiceUiModule_ProvideHandsFreeRoutingAdapterFactory implements Factory<HandsFreeRoutingAdapter> {
    private final Provider<Activity> activityProvider;
    private final Provider<AMPDInformationProvider> ampdInformationProvider;
    private final Provider<HandsFreeSettingsMetricRecorder> handsFreeSettingsMetricRecorderProvider;
    private final VoiceUiModule module;
    private final Provider<SettingsSetupFlowProvider> settingsSetupFlowProvider;
    private final Provider<VendorAPIWrapperProvider> vendorAPIWrapperProvider;

    public VoiceUiModule_ProvideHandsFreeRoutingAdapterFactory(VoiceUiModule voiceUiModule, Provider<Activity> provider, Provider<AMPDInformationProvider> provider2, Provider<VendorAPIWrapperProvider> provider3, Provider<SettingsSetupFlowProvider> provider4, Provider<HandsFreeSettingsMetricRecorder> provider5) {
        this.module = voiceUiModule;
        this.activityProvider = provider;
        this.ampdInformationProvider = provider2;
        this.vendorAPIWrapperProvider = provider3;
        this.settingsSetupFlowProvider = provider4;
        this.handsFreeSettingsMetricRecorderProvider = provider5;
    }

    public static VoiceUiModule_ProvideHandsFreeRoutingAdapterFactory create(VoiceUiModule voiceUiModule, Provider<Activity> provider, Provider<AMPDInformationProvider> provider2, Provider<VendorAPIWrapperProvider> provider3, Provider<SettingsSetupFlowProvider> provider4, Provider<HandsFreeSettingsMetricRecorder> provider5) {
        return new VoiceUiModule_ProvideHandsFreeRoutingAdapterFactory(voiceUiModule, provider, provider2, provider3, provider4, provider5);
    }

    public static HandsFreeRoutingAdapter provideInstance(VoiceUiModule voiceUiModule, Provider<Activity> provider, Provider<AMPDInformationProvider> provider2, Provider<VendorAPIWrapperProvider> provider3, Provider<SettingsSetupFlowProvider> provider4, Provider<HandsFreeSettingsMetricRecorder> provider5) {
        return proxyProvideHandsFreeRoutingAdapter(voiceUiModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get());
    }

    public static HandsFreeRoutingAdapter proxyProvideHandsFreeRoutingAdapter(VoiceUiModule voiceUiModule, Activity activity, AMPDInformationProvider aMPDInformationProvider, VendorAPIWrapperProvider vendorAPIWrapperProvider, SettingsSetupFlowProvider settingsSetupFlowProvider, HandsFreeSettingsMetricRecorder handsFreeSettingsMetricRecorder) {
        return (HandsFreeRoutingAdapter) Preconditions.checkNotNull(voiceUiModule.provideHandsFreeRoutingAdapter(activity, aMPDInformationProvider, vendorAPIWrapperProvider, settingsSetupFlowProvider, handsFreeSettingsMetricRecorder), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public HandsFreeRoutingAdapter mo10268get() {
        return provideInstance(this.module, this.activityProvider, this.ampdInformationProvider, this.vendorAPIWrapperProvider, this.settingsSetupFlowProvider, this.handsFreeSettingsMetricRecorderProvider);
    }
}
