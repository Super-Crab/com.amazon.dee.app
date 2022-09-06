package org.bouncycastle.tls.crypto.impl.bc;
/* loaded from: classes5.dex */
class Exceptions {
    Exceptions() {
    }

    static IllegalArgumentException illegalArgumentException(String str, Throwable th) {
        return new IllegalArgumentException(str, th);
    }
}
