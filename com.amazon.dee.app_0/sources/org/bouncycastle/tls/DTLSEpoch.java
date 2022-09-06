package org.bouncycastle.tls;

import java.io.IOException;
import org.bouncycastle.tls.crypto.TlsCipher;
/* loaded from: classes5.dex */
class DTLSEpoch {
    private final TlsCipher cipher;
    private final int epoch;
    private final DTLSReplayWindow replayWindow = new DTLSReplayWindow();
    private long sequenceNumber = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DTLSEpoch(int i, TlsCipher tlsCipher) {
        if (i >= 0) {
            if (tlsCipher == null) {
                throw new IllegalArgumentException("'cipher' cannot be null");
            }
            this.epoch = i;
            this.cipher = tlsCipher;
            return;
        }
        throw new IllegalArgumentException("'epoch' must be >= 0");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized long allocateSequenceNumber() throws IOException {
        long j;
        if (this.sequenceNumber >= 281474976710656L) {
            throw new TlsFatalAlert((short) 80);
        }
        j = this.sequenceNumber;
        this.sequenceNumber = 1 + j;
        return j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TlsCipher getCipher() {
        return this.cipher;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getEpoch() {
        return this.epoch;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DTLSReplayWindow getReplayWindow() {
        return this.replayWindow;
    }

    synchronized long getSequenceNumber() {
        return this.sequenceNumber;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void setSequenceNumber(long j) {
        this.sequenceNumber = j;
    }
}
