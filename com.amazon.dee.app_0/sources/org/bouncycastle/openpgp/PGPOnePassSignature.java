package org.bouncycastle.openpgp;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.bcpg.BCPGInputStream;
import org.bouncycastle.bcpg.BCPGOutputStream;
import org.bouncycastle.bcpg.OnePassSignaturePacket;
import org.bouncycastle.bcpg.Packet;
import org.bouncycastle.openpgp.operator.PGPContentVerifier;
import org.bouncycastle.openpgp.operator.PGPContentVerifierBuilderProvider;
/* loaded from: classes5.dex */
public class PGPOnePassSignature {
    private byte lastb;
    private OutputStream sigOut;
    private OnePassSignaturePacket sigPack;
    private int signatureType;
    private PGPContentVerifier verifier;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PGPOnePassSignature(BCPGInputStream bCPGInputStream) throws IOException, PGPException {
        this(cast(bCPGInputStream.readPacket()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PGPOnePassSignature(OnePassSignaturePacket onePassSignaturePacket) throws PGPException {
        this.sigPack = onePassSignaturePacket;
        this.signatureType = onePassSignaturePacket.getSignatureType();
    }

    private void blockUpdate(byte[] bArr, int i, int i2) {
        try {
            this.sigOut.write(bArr, i, i2);
        } catch (IOException e) {
            throw new PGPRuntimeOperationException(e.getMessage(), e);
        }
    }

    private void byteUpdate(byte b) {
        try {
            this.sigOut.write(b);
        } catch (IOException e) {
            throw new PGPRuntimeOperationException(e.getMessage(), e);
        }
    }

    private static OnePassSignaturePacket cast(Packet packet) throws IOException {
        if (packet instanceof OnePassSignaturePacket) {
            return (OnePassSignaturePacket) packet;
        }
        throw new IOException("unexpected packet in stream: " + packet);
    }

    public void encode(OutputStream outputStream) throws IOException {
        (outputStream instanceof BCPGOutputStream ? (BCPGOutputStream) outputStream : new BCPGOutputStream(outputStream)).writePacket(this.sigPack);
    }

    public byte[] getEncoded() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        encode(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public int getHashAlgorithm() {
        return this.sigPack.getHashAlgorithm();
    }

    public int getKeyAlgorithm() {
        return this.sigPack.getKeyAlgorithm();
    }

    public long getKeyID() {
        return this.sigPack.getKeyID();
    }

    public int getSignatureType() {
        return this.sigPack.getSignatureType();
    }

    public void init(PGPContentVerifierBuilderProvider pGPContentVerifierBuilderProvider, PGPPublicKey pGPPublicKey) throws PGPException {
        this.verifier = pGPContentVerifierBuilderProvider.get(this.sigPack.getKeyAlgorithm(), this.sigPack.getHashAlgorithm()).build(pGPPublicKey);
        this.lastb = (byte) 0;
        this.sigOut = this.verifier.getOutputStream();
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
            int r0 = r3.signatureType
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
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.openpgp.PGPOnePassSignature.update(byte):void");
    }

    public void update(byte[] bArr) {
        if (this.signatureType != 1) {
            blockUpdate(bArr, 0, bArr.length);
            return;
        }
        for (int i = 0; i != bArr.length; i++) {
            update(bArr[i]);
        }
    }

    public void update(byte[] bArr, int i, int i2) {
        if (this.signatureType != 1) {
            blockUpdate(bArr, i, i2);
            return;
        }
        int i3 = i2 + i;
        while (i != i3) {
            update(bArr[i]);
            i++;
        }
    }

    public boolean verify(PGPSignature pGPSignature) throws PGPException {
        try {
            this.sigOut.write(pGPSignature.getSignatureTrailer());
            this.sigOut.close();
            return this.verifier.verify(pGPSignature.getSignature());
        } catch (IOException e) {
            throw new PGPException(GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("unable to add trailer: ")), e);
        }
    }
}
