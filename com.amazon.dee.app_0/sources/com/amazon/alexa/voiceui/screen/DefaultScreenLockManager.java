package com.amazon.alexa.voiceui.screen;

import android.content.Context;
import android.os.Handler;
import android.os.PowerManager;
import android.view.Window;
import com.amazon.alexa.voice.ui.speech.AttentionSystemContract;
import com.amazon.alexa.voice.ui.util.AlexaState;
import com.amazon.alexa.voiceui.driveMode.DriveModeStateProvider;
/* loaded from: classes11.dex */
public class DefaultScreenLockManager extends ScreenLockManager {
    private final AttentionSystemContract attentionSystemContract;
    private final DriveModeStateProvider driveModeStateProvider;

    public DefaultScreenLockManager(Window window, Handler handler, Context context, PowerManager powerManager, AttentionSystemContract attentionSystemContract, DriveModeStateProvider driveModeStateProvider) {
        super(window, handler, context, powerManager);
        this.attentionSystemContract = attentionSystemContract;
        this.driveModeStateProvider = driveModeStateProvider;
    }

    @Override // com.amazon.alexa.voiceui.screen.ScreenLockManager
    public boolean shouldDisableKeepScreenOn() {
        return AlexaState.IDLE.equals(this.attentionSystemContract.alexaState().get()) && !this.driveModeStateProvider.getDriveModeEnabled();
    }
}
