package org.bouncycastle.openpgp.operator.bc;

import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.operator.PGPDigestCalculator;
import org.bouncycastle.openpgp.operator.PGPDigestCalculatorProvider;
/* loaded from: classes5.dex */
public class BcPGPDigestCalculatorProvider implements PGPDigestCalculatorProvider {

    /* loaded from: classes5.dex */
    private class DigestOutputStream extends OutputStream {
        private Digest dig;

        DigestOutputStream(Digest digest) {
            this.dig = digest;
        }

        byte[] getDigest() {
            byte[] bArr = new byte[this.dig.getDigestSize()];
            this.dig.doFinal(bArr, 0);
            return bArr;
        }

        @Override // java.io.OutputStream
        public void write(int i) throws IOException {
            this.dig.update((byte) i);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr) throws IOException {
            this.dig.update(bArr, 0, bArr.length);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws IOException {
            this.dig.update(bArr, i, i2);
        }
    }

    @Override // org.bouncycastle.openpgp.operator.PGPDigestCalculatorProvider
    public PGPDigestCalculator get(final int i) throws PGPException {
        final Digest createDigest = BcImplProvider.createDigest(i);
        final DigestOutputStream digestOutputStream = new DigestOutputStream(createDigest);
        return new PGPDigestCalculator() { // from class: org.bouncycastle.openpgp.operator.bc.BcPGPDigestCalculatorProvider.1
            @Override // org.bouncycastle.openpgp.operator.PGPDigestCalculator
            public int getAlgorithm() {
                return i;
            }

            @Override // org.bouncycastle.openpgp.operator.PGPDigestCalculator
            public byte[] getDigest() {
                return digestOutputStream.getDigest();
            }

            @Override // org.bouncycastle.openpgp.operator.PGPDigestCalculator
            public OutputStream getOutputStream() {
                return digestOutputStream;
            }

            @Override // org.bouncycastle.openpgp.operator.PGPDigestCalculator
            public void reset() {
                createDigest.reset();
            }
        };
    }
}
