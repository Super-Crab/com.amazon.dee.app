package com.amazon.alexa.voiceui;
/* loaded from: classes11.dex */
public class DefaultBackButtonPressHandler implements BackButtonPressHandler {
    private final RouterDelegate routerDelegate;

    public DefaultBackButtonPressHandler(RouterDelegate routerDelegate) {
        this.routerDelegate = routerDelegate;
    }

    @Override // com.amazon.alexa.voiceui.BackButtonPressHandler
    public boolean onBackButtonPressed() {
        return this.routerDelegate.handleBack();
    }
}
