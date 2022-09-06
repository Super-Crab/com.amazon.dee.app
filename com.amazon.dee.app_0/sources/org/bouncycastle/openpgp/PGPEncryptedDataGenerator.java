package org.bouncycastle.openpgp;

import java.io.IOException;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import org.bouncycastle.bcpg.BCPGOutputStream;
import org.bouncycastle.bcpg.SymmetricKeyAlgorithmTags;
import org.bouncycastle.openpgp.operator.PGPDataEncryptorBuilder;
import org.bouncycastle.openpgp.operator.PGPDigestCalculator;
import org.bouncycastle.openpgp.operator.PGPKeyEncryptionMethodGenerator;
/* loaded from: classes5.dex */
public class PGPEncryptedDataGenerator implements SymmetricKeyAlgorithmTags, StreamGenerator {
    public static final int S2K_SHA1 = 2;
    public static final int S2K_SHA224 = 11;
    public static final int S2K_SHA256 = 8;
    public static final int S2K_SHA384 = 9;
    public static final int S2K_SHA512 = 10;
    private OutputStream cOut;
    private PGPDataEncryptorBuilder dataEncryptorBuilder;
    private int defAlgorithm;
    private PGPDigestCalculator digestCalc;
    private OutputStream genOut;
    private List methods;
    private boolean oldFormat;
    private BCPGOutputStream pOut;
    private SecureRandom rand;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public class ClosableBCPGOutputStream extends BCPGOutputStream {
        public ClosableBCPGOutputStream(OutputStream outputStream, int i, long j) throws IOException {
            super(outputStream, i, j);
        }

        public ClosableBCPGOutputStream(OutputStream outputStream, int i, long j, boolean z) throws IOException {
            super(outputStream, i, j, z);
        }

        public ClosableBCPGOutputStream(OutputStream outputStream, int i, byte[] bArr) throws IOException {
            super(outputStream, i, bArr);
        }

        @Override // org.bouncycastle.bcpg.BCPGOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            finish();
        }
    }

    public PGPEncryptedDataGenerator(PGPDataEncryptorBuilder pGPDataEncryptorBuilder) {
        this(pGPDataEncryptorBuilder, false);
    }

    public PGPEncryptedDataGenerator(PGPDataEncryptorBuilder pGPDataEncryptorBuilder, boolean z) {
        this.oldFormat = false;
        this.methods = new ArrayList();
        this.dataEncryptorBuilder = pGPDataEncryptorBuilder;
        this.oldFormat = z;
        this.defAlgorithm = this.dataEncryptorBuilder.getAlgorithm();
        this.rand = this.dataEncryptorBuilder.getSecureRandom();
    }

    private void addCheckSum(byte[] bArr) {
        int i = 0;
        for (int i2 = 1; i2 != bArr.length - 2; i2++) {
            i += bArr[i2] & 255;
        }
        bArr[bArr.length - 2] = (byte) (i >> 8);
        bArr[bArr.length - 1] = (byte) i;
    }

    private byte[] createSessionInfo(int i, byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length + 3];
        bArr2[0] = (byte) i;
        System.arraycopy(bArr, 0, bArr2, 1, bArr.length);
        addCheckSum(bArr2);
        return bArr2;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x011e A[Catch: Exception -> 0x015a, TryCatch #0 {Exception -> 0x015a, blocks: (B:16:0x00ac, B:18:0x00ba, B:20:0x00c0, B:21:0x00dc, B:27:0x0110, B:29:0x011e, B:30:0x012d, B:22:0x00e0, B:23:0x00f7, B:25:0x00fb, B:26:0x0107), top: B:39:0x00ac }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.io.OutputStream open(java.io.OutputStream r12, long r13, byte[] r15) throws java.io.IOException, org.bouncycastle.openpgp.PGPException, java.lang.IllegalStateException {
        /*
            Method dump skipped, instructions count: 371
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.openpgp.PGPEncryptedDataGenerator.open(java.io.OutputStream, long, byte[]):java.io.OutputStream");
    }

    public void addMethod(PGPKeyEncryptionMethodGenerator pGPKeyEncryptionMethodGenerator) {
        this.methods.add(pGPKeyEncryptionMethodGenerator);
    }

    @Override // org.bouncycastle.openpgp.StreamGenerator
    public void close() throws IOException {
        if (this.cOut != null) {
            if (this.digestCalc != null) {
                new BCPGOutputStream(this.genOut, 19, 20L).flush();
                this.cOut.write(this.digestCalc.getDigest());
            }
            this.cOut.close();
            this.cOut = null;
            this.pOut = null;
        }
    }

    public OutputStream open(OutputStream outputStream, long j) throws IOException, PGPException {
        return open(outputStream, j, null);
    }

    public OutputStream open(OutputStream outputStream, byte[] bArr) throws IOException, PGPException {
        return open(outputStream, 0L, bArr);
    }
}
