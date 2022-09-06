package com.amazon.alexa.handsfree.settings.contract.dependencies;

import android.content.Intent;
import androidx.annotation.Nullable;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentProtocol;
import com.amazon.alexa.handsfree.settings.contract.SettingsContract;
import com.amazon.alexa.handsfree.settings.contract.SettingsSetupFlowContract;
import dagger.Subcomponent;
import javax.inject.Named;
@Subcomponent
/* loaded from: classes8.dex */
public interface FalcoSettingContractComponent extends AhfComponentProtocol {
    public static final String AUDIO_PROVIDER_SERVICE = "AudioProviderService";

    @Nullable
    @Named(AUDIO_PROVIDER_SERVICE)
    Intent audioProviderServiceIntent();

    @Nullable
    SettingsContract settingsContract();

    @Nullable
    SettingsSetupFlowContract settingsSetupFlowContract();
}
