package com.amazon.alexa.voice.ui;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.mosaic.components.ThemeUtil;
/* loaded from: classes11.dex */
public class VoiceProcess {
    private static final String TAG = "VoiceProcess";

    public void initialize(@NonNull Context context, @NonNull EventBus eventBus) {
        ThemeUtil.initTheme(null);
        new VoiceEventBusRebroadcastReceiver(context, eventBus.getPublisher(false)).start();
        eventBus.subscribe(ThemeUtil.getThemePreferenceSubscriber(context));
    }
}
