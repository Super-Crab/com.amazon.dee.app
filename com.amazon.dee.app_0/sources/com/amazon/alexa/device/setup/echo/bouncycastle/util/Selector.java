package com.amazon.alexa.device.setup.echo.bouncycastle.util;
/* loaded from: classes.dex */
public interface Selector extends Cloneable {
    Object clone();

    boolean match(Object obj);
}
