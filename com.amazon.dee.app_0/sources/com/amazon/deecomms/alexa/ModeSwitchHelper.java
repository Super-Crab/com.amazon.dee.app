package com.amazon.deecomms.alexa;
/* loaded from: classes12.dex */
public interface ModeSwitchHelper {
    void detectAndReactToBackgroundModeSwitch();

    boolean isMultiModalMode();

    boolean isTabletMode();

    void transitionToMultiModalMode(String str);

    void transitionToTabletMode(String str);
}
