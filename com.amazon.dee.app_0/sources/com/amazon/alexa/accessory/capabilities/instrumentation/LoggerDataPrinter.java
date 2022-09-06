package com.amazon.alexa.accessory.capabilities.instrumentation;

import com.amazon.alexa.accessory.internal.util.Logger;
/* loaded from: classes.dex */
public final class LoggerDataPrinter implements DataPrinter {
    @Override // com.amazon.alexa.accessory.capabilities.instrumentation.DataPrinter
    public void print(String str) {
        Logger.d(str);
    }
}
