package com.amazon.alexa.voice.ui.onedesign.util;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.amazon.regulator.ViewController;
/* loaded from: classes11.dex */
public final class IntentUtils {
    private IntentUtils() {
        throw new IllegalStateException("No instances!");
    }

    public static void openLinkUrl(Context context, String str) {
        if (context != null) {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
            intent.setFlags(268435456);
            try {
                context.startActivity(intent);
                return;
            } catch (ActivityNotFoundException e) {
                Logger.error("Failed to start activity to open " + str, e);
                return;
            }
        }
        throw new IllegalStateException("Cannot open url, context is null.");
    }

    public static void openLinkUrl(ViewController viewController, String str) {
        if (viewController.getContext() != null) {
            openLinkUrl(viewController.getContext(), str);
            return;
        }
        throw new IllegalStateException("Cannot open url when a controller is destroyed");
    }
}
