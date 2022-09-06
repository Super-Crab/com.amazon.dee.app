package com.amazon.alexa.accessory.repositories.device;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes6.dex */
public enum Features {
    DISPLAY_CAPTION_FEATURE(15),
    DISPLAY_CARD_FEATURE(16),
    SUPPRESSING_SPEECH_RESPONSE_FEATURE(22);
    
    private final int featureId;

    Features(int i) {
        this.featureId = i;
    }

    public static Features fromValue(int i) throws IllegalArgumentException {
        if (i == 15) {
            return DISPLAY_CAPTION_FEATURE;
        }
        if (i == 16) {
            return DISPLAY_CARD_FEATURE;
        }
        if (i == 22) {
            return SUPPRESSING_SPEECH_RESPONSE_FEATURE;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Could not find feature for feature id: ", i));
    }

    public int value() {
        return this.featureId;
    }
}
