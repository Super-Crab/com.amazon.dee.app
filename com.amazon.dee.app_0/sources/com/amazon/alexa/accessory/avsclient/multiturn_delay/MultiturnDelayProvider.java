package com.amazon.alexa.accessory.avsclient.multiturn_delay;
/* loaded from: classes.dex */
public interface MultiturnDelayProvider {
    void activate();

    void deactivate();

    int getMultiturnDelay(String str);
}
