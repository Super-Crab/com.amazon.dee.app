package com.amazon.alexa.api;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.amazon.alexa.api.AlexaUserInterfaceOptions;
/* loaded from: classes6.dex */
public final class AlexaUserInterfaceOptionsUtils {
    private AlexaUserInterfaceOptionsUtils() {
        throw new UnsupportedOperationException();
    }

    public static AlexaUserInterfaceOptions getAlexaUserInterfaceOptions(boolean z, @Nullable String str, boolean z2, boolean z3) {
        AlexaUserInterfaceOptions.Builder typingEnabled = AlexaUserInterfaceOptions.builder().setHintsEnabled(z).setTheme(z2 ? AlexaUserInterfaceOptions.Theme.MINIMAL : AlexaUserInterfaceOptions.Theme.DEFAULT).setTypingEnabled(z3);
        if (str != null) {
            typingEnabled.setHintText(str);
        }
        return typingEnabled.build();
    }

    public static Bundle getBundle(AlexaUserInterfaceOptions alexaUserInterfaceOptions) {
        return alexaUserInterfaceOptions.getBundle();
    }
}
