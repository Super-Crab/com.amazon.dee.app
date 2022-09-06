package com.amazon.deecomms.core;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.drivemode.api.DriveModeService;
import com.amazon.deecomms.alexa.AlexaInterface;
import com.amazon.deecomms.alexa.ModeSwitchHelper;
import com.amazon.deecomms.alexa.NoOpModeSwitchHelper;
import com.amazon.deecomms.alexa.VoxBridge;
import com.amazon.deecomms.alexa.fireos.DefaultModeSwitchHelper;
import com.amazon.deecomms.alexa.fireos.SimClientAlexaInterface;
import com.amazon.deecomms.alexa.voice.EventSenderImpl;
import com.amazon.deecomms.alexa.voice.IEventSender;
import com.amazon.deecomms.alexa.voice.usecase.EventSenderUseCase;
import com.amazon.deecomms.calling.util.VoxUtils;
import com.amazon.deecomms.common.metrics.MetricsService;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.perms.VoicePermissionsAuthority;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes12.dex */
public class AlexaModule {
    @Provides
    @Singleton
    public AlexaInterface providesAlexaInterface(@NonNull Context context) {
        if (Utils.isFireOS()) {
            return new SimClientAlexaInterface(context);
        }
        return new VoxBridge(context);
    }

    @Provides
    @Singleton
    public ModeSwitchHelper providesAlexaModeSwitchHelper(@NonNull Context context) {
        if (Utils.isFireOS() && context.getPackageManager().hasSystemFeature(com.amazon.comms.util.modeswitch.ModeSwitchHelper.MODE_SWITCH_PACKAGE)) {
            return new DefaultModeSwitchHelper(context);
        }
        return new NoOpModeSwitchHelper();
    }

    @Provides
    public DriveModeService providesDriveModeService() {
        return (DriveModeService) GeneratedOutlineSupport1.outline20(DriveModeService.class);
    }

    @Provides
    public IEventSender providesEventSender(EventSenderUseCase eventSenderUseCase, VoicePermissionsAuthority voicePermissionsAuthority, MetricsService metricsService) {
        return new EventSenderImpl(eventSenderUseCase, voicePermissionsAuthority, metricsService);
    }

    @Provides
    @Singleton
    public VoxUtils providesVoxUtils() {
        return new VoxUtils();
    }
}
