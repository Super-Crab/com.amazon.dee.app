package org.bouncycastle.est;
/* loaded from: classes4.dex */
public interface ESTClientProvider {
    boolean isTrusted();

    ESTClient makeClient() throws ESTException;
}
