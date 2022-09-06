package com.amazon.alexa.voiceui.voice;

import androidx.annotation.Nullable;
import com.amazon.alexa.api.AlexaUserInterfaceOptions;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes11.dex */
public class AlexaUserInterfaceOptionsTracker {
    private static AlexaUserInterfaceOptions alexaUserInterfaceOptions;

    @Nullable
    public AlexaUserInterfaceOptions getAlexaUserInterfaceOptions() {
        AlexaUserInterfaceOptions alexaUserInterfaceOptions2 = alexaUserInterfaceOptions;
        return alexaUserInterfaceOptions2 == null ? alexaUserInterfaceOptions2 : AlexaUserInterfaceOptions.builder().setTheme(alexaUserInterfaceOptions.getTheme()).setTypingEnabled(alexaUserInterfaceOptions.getTypingEnabled()).build();
    }

    public void setAlexaUserInterfaceOptions(AlexaUserInterfaceOptions alexaUserInterfaceOptions2) {
        alexaUserInterfaceOptions = alexaUserInterfaceOptions2;
    }
}
