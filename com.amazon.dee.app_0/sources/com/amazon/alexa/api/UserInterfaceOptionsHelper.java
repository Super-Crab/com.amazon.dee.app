package com.amazon.alexa.api;

import android.os.Bundle;
import androidx.annotation.Nullable;
/* loaded from: classes6.dex */
public final class UserInterfaceOptionsHelper {
    private UserInterfaceOptionsHelper() {
        throw new UnsupportedOperationException("Do not instantiate this class");
    }

    public static AlexaUserInterfaceOptions fromBundle(@Nullable Bundle bundle) {
        if (bundle == null) {
            return AlexaUserInterfaceOptions.builder().build();
        }
        return new AlexaUserInterfaceOptions(bundle);
    }

    public static Bundle getBundle(AlexaUserInterfaceOptions alexaUserInterfaceOptions) {
        return alexaUserInterfaceOptions.getBundle();
    }
}
