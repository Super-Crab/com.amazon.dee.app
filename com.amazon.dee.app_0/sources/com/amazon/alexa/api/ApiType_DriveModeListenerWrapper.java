package com.amazon.alexa.api;
/* loaded from: classes6.dex */
class ApiType_DriveModeListenerWrapper extends aq implements AlexaDriveModeListener {
    private final AlexaDriveModeListener alexaDriveModeListener;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ApiType_DriveModeListenerWrapper(AlexaDriveModeListener alexaDriveModeListener) {
        this.alexaDriveModeListener = alexaDriveModeListener;
    }

    @Override // com.amazon.alexa.api.aq, com.amazon.alexa.api.AlexaDriveModeListener
    public void onDriveModeEnabled(boolean z) {
        this.alexaDriveModeListener.onDriveModeEnabled(z);
    }

    @Override // com.amazon.alexa.api.aq, com.amazon.alexa.api.AlexaDriveModeListener
    public void onDriveModeState(DriveModeState driveModeState) {
        this.alexaDriveModeListener.onDriveModeState(driveModeState);
    }

    @Override // com.amazon.alexa.api.aq, com.amazon.alexa.api.AlexaDriveModeListener
    public void onDriveModeThemeChanged(boolean z) {
        this.alexaDriveModeListener.onDriveModeThemeChanged(z);
    }
}
