package org.bouncycastle.crypto.digests;

import com.amazon.alexa.client.metrics.core.AppInformation;
import java.io.ByteArrayOutputStream;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class NullDigest implements Digest {
    private OpenByteArrayOutputStream bOut = new OpenByteArrayOutputStream();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class OpenByteArrayOutputStream extends ByteArrayOutputStream {
        private OpenByteArrayOutputStream() {
        }

        void copy(byte[] bArr, int i) {
            System.arraycopy(((ByteArrayOutputStream) this).buf, 0, bArr, i, size());
        }

        @Override // java.io.ByteArrayOutputStream
        public void reset() {
            super.reset();
            Arrays.clear(((ByteArrayOutputStream) this).buf);
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        int size = this.bOut.size();
        this.bOut.copy(bArr, i);
        reset();
        return size;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return AppInformation.NULL;
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return this.bOut.size();
    }

    @Override // org.bouncycastle.crypto.Digest
    public void reset() {
        this.bOut.reset();
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b) {
        this.bOut.write(b);
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int i, int i2) {
        this.bOut.write(bArr, i, i2);
    }
}
