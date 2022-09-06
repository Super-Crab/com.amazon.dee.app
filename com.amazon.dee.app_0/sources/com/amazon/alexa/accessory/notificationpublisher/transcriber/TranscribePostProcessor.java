package com.amazon.alexa.accessory.notificationpublisher.transcriber;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public final class TranscribePostProcessor {
    private static final String TAG = "TranscribePostProcessor";

    @VisibleForTesting
    String capitalizeFirstCharacter(@NonNull String str) {
        try {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline157("capitalizeFirstCharacter - Exception: ", e, TAG);
            return str;
        }
    }

    @VisibleForTesting
    String capitalizeFirstPersonPronoun(@NonNull String str) {
        try {
            return str.replace(" i ", " I ");
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline157("capitalizeFirstPersonPronoun - Exception: ", e, TAG);
            return str;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String postProcessTranscribedText(@NonNull String str) {
        GeneratedOutlineSupport1.outline165("postProcessTranscribedText - ", str, TAG);
        try {
            return capitalizeFirstCharacter(capitalizeFirstPersonPronoun(str));
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline157("postProcessTranscribedText - Exception: ", e, TAG);
            return str;
        }
    }
}
