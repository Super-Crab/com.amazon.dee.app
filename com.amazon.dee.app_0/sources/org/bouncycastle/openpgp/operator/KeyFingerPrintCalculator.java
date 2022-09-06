package org.bouncycastle.openpgp.operator;

import org.bouncycastle.bcpg.PublicKeyPacket;
import org.bouncycastle.openpgp.PGPException;
/* loaded from: classes5.dex */
public interface KeyFingerPrintCalculator {
    byte[] calculateFingerprint(PublicKeyPacket publicKeyPacket) throws PGPException;
}
