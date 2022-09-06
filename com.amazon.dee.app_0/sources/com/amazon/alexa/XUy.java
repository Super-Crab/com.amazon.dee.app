package com.amazon.alexa;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import com.amazon.alexa.api.AlexaAudioMetadata;
import com.amazon.alexa.api.AlexaProfile;
import com.amazon.alexa.api.AlexaWakeWord;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* compiled from: UserSpeechMetadataVerifier.java */
/* loaded from: classes.dex */
public final class XUy {
    public static final String zZm = "XUy";

    public static boolean zZm(@Nullable AlexaAudioMetadata alexaAudioMetadata) {
        if (alexaAudioMetadata == null) {
            Log.e(zZm, "AlexaAudioMetadata rejected because it was null.");
            return false;
        }
        AlexaProfile alexaProfile = alexaAudioMetadata.getAlexaProfile();
        String alexaAudioFormat = alexaAudioMetadata.getAlexaAudioFormat();
        if (alexaProfile == null) {
            Log.e(zZm, "AlexaAudioMetadata rejected due to null AlexaProfile.");
            return false;
        } else if (TextUtils.isEmpty(alexaAudioFormat)) {
            Log.e(zZm, "AlexaAudioMetadata rejected due to empty AlexaAudioFormat.");
            return false;
        } else {
            AlexaWakeWord alexaWakeword = alexaAudioMetadata.getAlexaWakeword();
            if (alexaWakeword != null) {
                long startIndexInSamples = alexaWakeword.getStartIndexInSamples();
                long endIndexInSamples = alexaWakeword.getEndIndexInSamples();
                if (startIndexInSamples >= 0 && endIndexInSamples >= 0 && endIndexInSamples >= startIndexInSamples) {
                    if (TextUtils.isEmpty(alexaWakeword.getWakeWordName())) {
                        Log.e(zZm, "AlexaAudioMetadata rejected due to empty wake word name.");
                        return false;
                    }
                } else {
                    String str = zZm;
                    StringBuilder outline111 = GeneratedOutlineSupport1.outline111("AlexaAudioMetadata rejected due to invalid wake word indices. Start: ", startIndexInSamples, ", end: ");
                    outline111.append(endIndexInSamples);
                    outline111.append(".");
                    Log.e(str, outline111.toString());
                    return false;
                }
            }
            return true;
        }
    }
}
