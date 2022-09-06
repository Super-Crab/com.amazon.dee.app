package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
/* compiled from: EventTypes.java */
/* loaded from: classes.dex */
public enum XuC {
    LAUNCH_TARGET_ATTEMPTED("LaunchTargetAttempted"),
    LAUNCH_TARGET_FAILED("LaunchTargetFailed"),
    DISAMBIGUATE_AND_LAUNCH_TARGET_ATTEMPTED("DisambiguateAndLaunchTargetAttempted"),
    DISAMBIGUATE_AND_LAUNCH_TARGET_FAILED("DisambiguateAndLaunchTargetFailed");
    
    public final String suffix;

    XuC(String str) {
        this.suffix = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return String.format("%s.%s", AvsApiConstants.Alexa.Launcher.zZm.getValue(), this.suffix);
    }
}
