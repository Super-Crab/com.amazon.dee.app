package com.amazon.alexa.presence;

import com.amazon.deecomms.remoteConfig.ArcusConfig;
/* loaded from: classes9.dex */
public final class Presence {
    private static final String COMPONENT_NAME = "PresenceComponent";

    private Presence() {
    }

    private static String extractCallingClassFromStacktrace(StackTraceElement[] stackTraceElementArr) {
        if (stackTraceElementArr == null || stackTraceElementArr.length < 2) {
            return null;
        }
        String[] split = stackTraceElementArr[1].getClassName().split(ArcusConfig.PATH_SEPARATOR);
        return split[split.length - 1];
    }

    public static String tag() {
        return String.format("(%s)%s", COMPONENT_NAME, extractCallingClassFromStacktrace(new Throwable().fillInStackTrace().getStackTrace()));
    }
}
