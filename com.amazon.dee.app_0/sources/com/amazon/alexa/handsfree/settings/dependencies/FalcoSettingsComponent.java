package com.amazon.alexa.handsfree.settings.dependencies;

import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentProtocol;
import com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig.RemoteConfigManager;
import com.amazon.alexa.handsfree.settings.handsfreesettings.AlexaSettingsActivity;
import com.amazon.alexa.handsfree.settings.handsfreesettings.AlexaSettingsPreferenceFragment;
import com.amazon.alexa.handsfree.settings.handsfreesettings.lockscreen.RespondWhileLockedActivity;
import com.amazon.alexa.handsfree.settings.locale.VoiceAppLocalesProvider;
import dagger.Subcomponent;
@Subcomponent
/* loaded from: classes8.dex */
public interface FalcoSettingsComponent extends AhfComponentProtocol {
    void inject(AlexaSettingsActivity alexaSettingsActivity);

    void inject(AlexaSettingsPreferenceFragment alexaSettingsPreferenceFragment);

    void inject(RespondWhileLockedActivity respondWhileLockedActivity);

    RemoteConfigManager remoteConfigManager();

    VoiceAppLocalesProvider voiceAppLocalesProvider();
}
