package com.amazon.alexa.api;

import com.amazon.alexa.client.annotations.Nullable;
/* loaded from: classes6.dex */
class h {
    public static boolean a(@Nullable AlexaAudioMetadata alexaAudioMetadata) {
        if (alexaAudioMetadata == null) {
            return false;
        }
        AlexaProfile alexaProfile = alexaAudioMetadata.getAlexaProfile();
        String alexaAudioFormat = alexaAudioMetadata.getAlexaAudioFormat();
        if (alexaProfile != null && !a(alexaAudioFormat)) {
            return a(alexaAudioMetadata.getAlexaWakeword());
        }
        return false;
    }

    public static boolean a(@Nullable AlexaWakeWord alexaWakeWord) {
        if (alexaWakeWord == null) {
            return true;
        }
        long startIndexInSamples = alexaWakeWord.getStartIndexInSamples();
        long endIndexInSamples = alexaWakeWord.getEndIndexInSamples();
        if (startIndexInSamples >= 0 && endIndexInSamples >= 0 && endIndexInSamples >= startIndexInSamples) {
            return !a(alexaWakeWord.getWakeWordName());
        }
        return false;
    }

    private static boolean a(String str) {
        return str == null || str.isEmpty();
    }
}
