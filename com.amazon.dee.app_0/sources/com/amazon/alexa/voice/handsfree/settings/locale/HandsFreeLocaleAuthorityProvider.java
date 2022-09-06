package com.amazon.alexa.voice.handsfree.settings.locale;

import android.content.Context;
import androidx.annotation.NonNull;
/* loaded from: classes11.dex */
public class HandsFreeLocaleAuthorityProvider {
    private static HandsFreeLocaleAuthority mInstance;

    private HandsFreeLocaleAuthorityProvider() {
    }

    public static synchronized HandsFreeLocaleAuthority getInstance(@NonNull Context context) {
        HandsFreeLocaleAuthority handsFreeLocaleAuthority;
        synchronized (HandsFreeLocaleAuthorityProvider.class) {
            if (mInstance == null) {
                mInstance = new HandsFreeLocaleAuthority(context.getApplicationContext());
            }
            handsFreeLocaleAuthority = mInstance;
        }
        return handsFreeLocaleAuthority;
    }
}
