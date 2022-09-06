package com.amazon.alexa.voice.utils;

import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.voice.ui.onedesign.util.Logger;
/* loaded from: classes11.dex */
public final class LogLevelHelper {
    private LogLevelHelper() {
    }

    public static void setLogLevel(EnvironmentService environmentService) {
        if (environmentService.isDebugBuild()) {
            Logger.setLevel(Logger.Level.DEBUG);
        }
    }
}
