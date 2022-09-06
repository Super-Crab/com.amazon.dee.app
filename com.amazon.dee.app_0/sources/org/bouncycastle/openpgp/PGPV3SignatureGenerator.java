package org.bouncycastle.openpgp;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.Date;
import org.bouncycastle.bcpg.MPInteger;
import org.bouncycastle.bcpg.OnePassSignaturePacket;
import org.bouncycastle.bcpg.SignaturePacket;
import org.bouncycastle.openpgp.operator.PGPContentSigner;
import org.bouncycastle.openpgp.operator.PGPContentSignerBuilder;
/* loaded from: classes5.dex */
public class PGPV3SignatureGenerator {
    private PGPContentSigner contentSigner;
    private PGPContentSignerBuilder contentSignerBuilder;
    private byte lastb;
    private int providedKeyAlgorithm = -1;
    private OutputStream sigOut;
    private int sigType;

    public PGPV3SignatureGenerator(PGPContentSignerBuilder pGPContentSignerBuilder) {
        this.contentSignerBuilder = pGPContentSignerBuilder;
    }

    private void blockUpdate(byte[] bArr, int i, int i2) {
        try {
            this.sigOut.write(bArr, i, i2);
        } catch (IOException e) {
            throw new PGPRuntimeOperationException(GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("unable to update signature: ")), e);
        }
    }

    private void byteUpdate(byte b) {
        try {
            this.sigOut.write(b);
        } catch (IOException e) {
            throw new PGPRuntimeOperationException(GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("unable to update signature: ")), e);
        }
    }

    public PGPSignature generate() throws PGPException {
        long time = new Date().getTime() / 1000;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(this.sigType);
        byteArrayOutputStream.write((byte) (time >> 24));
        byteArrayOutputStream.write((byte) (time >> 16));
        byteArrayOutputStream.write((byte) (time >> 8));
        byteArrayOutputStream.write((byte) time);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        blockUpdate(byteArray, 0, byteArray.length);
        MPInteger[] dsaSigToMpi = (this.contentSigner.getKeyAlgorithm() == 3 || this.contentSigner.getKeyAlgorithm() == 1) ? new MPInteger[]{new MPInteger(new BigInteger(1, this.contentSigner.getSignature()))} : PGPUtil.dsaSigToMpi(this.contentSigner.getSignature());
        byte[] digest = this.contentSigner.getDigest();
        return new PGPSignature(new SignaturePacket(3, this.contentSigner.getType(), this.contentSigner.getKeyID(), this.contentSigner.getKeyAlgorithm(), this.contentSigner.getHashAlgorithm(), time * 1000, new byte[]{digest[0], digest[1]}, dsaSigToMpi));
    }

    public PGPOnePassSignature generateOnePassVersion(boolean z) throws PGPException {
        return new PGPOnePassSignature(new OnePassSignaturePacket(this.sigType, this.contentSigner.getHashAlgorithm(), this.contentSigner.getKeyAlgorithm(), this.contentSigner.getKeyID(), z));
    }

    public void init(int i, PGPPrivateKey pGPPrivateKey) throws PGPException {
        this.contentSigner = this.contentSignerBuilder.build(i, pGPPrivateKey);
        this.sigOut = this.contentSigner.getOutputStream();
        this.sigType = this.contentSigner.getType();
        this.lastb = (byte) 0;
        int i2 = this.providedKeyAlgorithm;
        if (i2 < 0 || i2 == this.contentSigner.getKeyAlgorithm()) {
            return;
        }
        throw new PGPException("key algorithm mismatch");
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0016, code lost:
        if (r3.lastb != 13) goto L5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void update(byte r4) {
        /*
            r3 = this;
            int r0 = r3.sigType
            r1 = 1
            if (r0 != r1) goto L1f
            r0 = 10
            r1 = 13
            if (r4 != r1) goto L12
        Lb:
            r3.byteUpdate(r1)
            r3.byteUpdate(r0)
            goto L1c
        L12:
            if (r4 != r0) goto L19
            byte r2 = r3.lastb
            if (r2 == r1) goto L1c
            goto Lb
        L19:
            r3.byteUpdate(r4)
        L1c:
            r3.lastb = r4
            goto L22
        L1f:
            r3.byteUpdate(r4)
        L22:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.openpgp.PGPV3SignatureGenerator.update(byte):void");
    }

    public void update(byte[] bArr) {
        update(bArr, 0, bArr.length);
    }

    public void update(byte[] bArr, int i, int i2) {
        if (this.sigType != 1) {
            blockUpdate(bArr, i, i2);
            return;
        }
        int i3 = i2 + i;
        while (i != i3) {
            update(bArr[i]);
            i++;
        }
    }
}
