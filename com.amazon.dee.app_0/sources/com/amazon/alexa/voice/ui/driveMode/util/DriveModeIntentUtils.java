package com.amazon.alexa.voice.ui.driveMode.util;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.amazon.alexa.voice.ui.onedesign.util.Logger;
import com.amazon.regulator.ViewController;
/* loaded from: classes11.dex */
public final class DriveModeIntentUtils {
    private DriveModeIntentUtils() {
        throw new IllegalStateException("No instances!");
    }

    public static void makePhoneCall(Context context, String str) {
        if (context != null) {
            Intent intent = new Intent("android.intent.action.CALL", Uri.parse(str));
            intent.setFlags(268435456);
            try {
                context.startActivity(intent);
                return;
            } catch (ActivityNotFoundException e) {
                Logger.error("Failed to start activity to make phone call " + str, e);
                return;
            }
        }
        throw new IllegalStateException("Cannot open url, context is null.");
    }

    public static void makePhoneCall(ViewController viewController, String str) {
        if (viewController.getContext() != null) {
            makePhoneCall(viewController.getContext(), str);
            return;
        }
        throw new IllegalStateException("Cannot make phone call when a controller is destroyed");
    }
}
