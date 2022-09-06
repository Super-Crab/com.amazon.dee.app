package com.amazon.alexa.device.setup.echo.bouncycastle.util;

import java.util.Collection;
/* loaded from: classes.dex */
public interface Store {
    Collection getMatches(Selector selector) throws StoreException;
}
