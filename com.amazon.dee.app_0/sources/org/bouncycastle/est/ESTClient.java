package org.bouncycastle.est;

import java.io.IOException;
/* loaded from: classes4.dex */
public interface ESTClient {
    ESTResponse doRequest(ESTRequest eSTRequest) throws IOException;
}
