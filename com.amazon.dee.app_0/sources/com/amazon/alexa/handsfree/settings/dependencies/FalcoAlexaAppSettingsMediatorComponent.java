package com.amazon.alexa.handsfree.settings.dependencies;

import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentProtocol;
import com.amazon.alexa.handsfree.settings.voxsettings.VoxSettingsJobIntentService;
import com.amazon.alexa.handsfree.settings.voxsettings.VoxSettingsSetRetryAttemptReceiver;
import dagger.Subcomponent;
@Subcomponent
/* loaded from: classes8.dex */
public interface FalcoAlexaAppSettingsMediatorComponent extends AhfComponentProtocol {
    void inject(VoxSettingsJobIntentService voxSettingsJobIntentService);

    void inject(VoxSettingsSetRetryAttemptReceiver voxSettingsSetRetryAttemptReceiver);
}
