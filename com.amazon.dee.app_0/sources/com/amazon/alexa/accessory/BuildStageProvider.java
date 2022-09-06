package com.amazon.alexa.accessory;

import com.amazon.alexa.accessory.internal.util.Logger;
import java.util.Locale;
/* loaded from: classes.dex */
public interface BuildStageProvider {

    /* loaded from: classes.dex */
    public enum BuildStage {
        PROD,
        GAMMA,
        BETA,
        ALPHA,
        UNKNOWN;

        public static BuildStage getBuildStage(String str) {
            try {
                if (str == null) {
                    return UNKNOWN;
                }
                return valueOf(str.toUpperCase(Locale.US));
            } catch (IllegalArgumentException e) {
                Logger.e("Unknown build stage!", e);
                return UNKNOWN;
            }
        }
    }

    BuildStage getBuildStage();
}
