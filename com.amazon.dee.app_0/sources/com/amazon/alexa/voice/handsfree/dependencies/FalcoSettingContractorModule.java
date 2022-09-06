package com.amazon.alexa.voice.handsfree.dependencies;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.Nullable;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfScope;
import com.amazon.alexa.handsfree.settings.contract.SettingsContract;
import com.amazon.alexa.handsfree.settings.contract.SettingsSetupFlowContract;
import com.amazon.alexa.handsfree.settings.contract.dependencies.FalcoSettingContractComponent;
import com.amazon.alexa.handsfree.vendor.bridge.VendorAPIWrapperProvider;
import com.amazon.alexa.voice.handsfree.settings.providers.SettingsSetupFlowProvider;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
@Module
/* loaded from: classes11.dex */
public class FalcoSettingContractorModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    @AhfScope
    @Provides
    @Named(FalcoSettingContractComponent.AUDIO_PROVIDER_SERVICE)
    @Nullable
    public Intent provideAudioProviderServiceIntent(Context context, VendorAPIWrapperProvider vendorAPIWrapperProvider) {
        return vendorAPIWrapperProvider.getSupportedAPIWrapper().getAudioProviderServiceIntent(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    @AhfScope
    @Provides
    public SettingsContract provideSettingsContract(VendorAPIWrapperProvider vendorAPIWrapperProvider) {
        return vendorAPIWrapperProvider.getSupportedAPIWrapper().getSettingsContract();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @AhfScope
    @Provides
    public SettingsSetupFlowContract provideSettingsSetupFlowContract() {
        return new SettingsSetupFlowProvider();
    }
}
