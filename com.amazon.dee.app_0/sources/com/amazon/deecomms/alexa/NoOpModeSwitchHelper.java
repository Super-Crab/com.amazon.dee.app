package com.amazon.deecomms.alexa;
/* loaded from: classes12.dex */
public final class NoOpModeSwitchHelper implements ModeSwitchHelper {
    @Override // com.amazon.deecomms.alexa.ModeSwitchHelper
    public void detectAndReactToBackgroundModeSwitch() {
    }

    @Override // com.amazon.deecomms.alexa.ModeSwitchHelper
    public boolean isMultiModalMode() {
        return false;
    }

    @Override // com.amazon.deecomms.alexa.ModeSwitchHelper
    public boolean isTabletMode() {
        return true;
    }

    @Override // com.amazon.deecomms.alexa.ModeSwitchHelper
    public void transitionToMultiModalMode(String str) {
    }

    @Override // com.amazon.deecomms.alexa.ModeSwitchHelper
    public void transitionToTabletMode(String str) {
    }
}
