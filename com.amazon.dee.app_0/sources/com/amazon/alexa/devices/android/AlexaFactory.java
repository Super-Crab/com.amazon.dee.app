package com.amazon.alexa.devices.android;

import android.content.Context;
import com.amazon.alexa.devices.Alexa;
import com.amazon.alexa.devices.AlexaException;
/* loaded from: classes6.dex */
public final class AlexaFactory {
    public static final Alexa getInstance(Context context) throws AlexaException {
        return AndroidAlexa.getInstance(context);
    }
}
