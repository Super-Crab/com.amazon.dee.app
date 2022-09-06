package com.amazon.dee.app.elements;

import com.amazon.dee.app.ui.main.RNLogPrinter;
import com.facebook.react.bridge.DefaultNativeModuleCallExceptionHandler;
/* loaded from: classes12.dex */
public class AlexaNativeModuleCallExceptionHandler extends DefaultNativeModuleCallExceptionHandler {
    private final RNLogPrinter rnLogPrinter;

    public AlexaNativeModuleCallExceptionHandler(RNLogPrinter rNLogPrinter) {
        this.rnLogPrinter = rNLogPrinter;
    }

    @Override // com.facebook.react.bridge.DefaultNativeModuleCallExceptionHandler, com.facebook.react.bridge.NativeModuleCallExceptionHandler
    public void handleException(Exception exc) {
        this.rnLogPrinter.handleNativeModuleCallException(exc);
    }
}
