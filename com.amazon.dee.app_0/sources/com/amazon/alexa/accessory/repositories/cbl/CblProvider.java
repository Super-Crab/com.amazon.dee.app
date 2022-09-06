package com.amazon.alexa.accessory.repositories.cbl;

import com.amazon.alexa.accessory.protocol.Cbl;
/* loaded from: classes6.dex */
public interface CblProvider {
    void provideCblLoginState(Cbl.CblLoginState cblLoginState);

    void provideCblLoginStateError(Throwable th);
}
