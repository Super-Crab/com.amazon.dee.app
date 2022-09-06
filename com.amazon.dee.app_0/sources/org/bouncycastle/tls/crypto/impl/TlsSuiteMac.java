package org.bouncycastle.tls.crypto.impl;
/* loaded from: classes5.dex */
public interface TlsSuiteMac {
    byte[] calculateMac(long j, short s, byte[] bArr, int i, int i2);

    byte[] calculateMacConstantTime(long j, short s, byte[] bArr, int i, int i2, int i3, byte[] bArr2);

    int getSize();
}
