package com.facebook.react.uimanager;

import com.facebook.yoga.YogaConfig;
import com.facebook.yoga.YogaConfigFactory;
/* loaded from: classes2.dex */
public class ReactYogaConfigProvider {
    private static YogaConfig YOGA_CONFIG;

    public static YogaConfig get() {
        if (YOGA_CONFIG == null) {
            YOGA_CONFIG = YogaConfigFactory.create();
            YOGA_CONFIG.setPointScaleFactor(0.0f);
            YOGA_CONFIG.setUseLegacyStretchBehaviour(true);
        }
        return YOGA_CONFIG;
    }
}
