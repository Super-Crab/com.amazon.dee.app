package org.bouncycastle.openpgp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bouncycastle.bcpg.SignatureSubpacket;
import org.bouncycastle.bcpg.sig.EmbeddedSignature;
import org.bouncycastle.bcpg.sig.Exportable;
import org.bouncycastle.bcpg.sig.Features;
import org.bouncycastle.bcpg.sig.IntendedRecipientFingerprint;
import org.bouncycastle.bcpg.sig.IssuerFingerprint;
import org.bouncycastle.bcpg.sig.IssuerKeyID;
import org.bouncycastle.bcpg.sig.KeyExpirationTime;
import org.bouncycastle.bcpg.sig.KeyFlags;
import org.bouncycastle.bcpg.sig.NotationData;
import org.bouncycastle.bcpg.sig.PreferredAlgorithms;
import org.bouncycastle.bcpg.sig.PrimaryUserID;
import org.bouncycastle.bcpg.sig.Revocable;
import org.bouncycastle.bcpg.sig.RevocationKey;
import org.bouncycastle.bcpg.sig.RevocationReason;
import org.bouncycastle.bcpg.sig.SignatureCreationTime;
import org.bouncycastle.bcpg.sig.SignatureExpirationTime;
import org.bouncycastle.bcpg.sig.SignatureTarget;
import org.bouncycastle.bcpg.sig.SignerUserID;
import org.bouncycastle.bcpg.sig.TrustSignature;
/* loaded from: classes5.dex */
public class PGPSignatureSubpacketGenerator {
    List list = new ArrayList();

    public PGPSignatureSubpacketVector generate() {
        List list = this.list;
        return new PGPSignatureSubpacketVector((SignatureSubpacket[]) list.toArray(new SignatureSubpacket[list.size()]));
    }

    public void setEmbeddedSignature(boolean z, PGPSignature pGPSignature) throws IOException {
        byte[] encoded = pGPSignature.getEncoded();
        byte[] bArr = new byte[encoded.length + (-1) > 256 ? encoded.length - 3 : encoded.length - 2];
        System.arraycopy(encoded, encoded.length - bArr.length, bArr, 0, bArr.length);
        this.list.add(new EmbeddedSignature(z, false, bArr));
    }

    public void setExportable(boolean z, boolean z2) {
        this.list.add(new Exportable(z, z2));
    }

    public void setFeature(boolean z, byte b) {
        this.list.add(new Features(z, b));
    }

    public void setIntendedRecipientFingerprint(boolean z, PGPPublicKey pGPPublicKey) {
        this.list.add(new IntendedRecipientFingerprint(z, pGPPublicKey.getVersion(), pGPPublicKey.getFingerprint()));
    }

    public void setIssuerFingerprint(boolean z, PGPPublicKey pGPPublicKey) {
        this.list.add(new IssuerFingerprint(z, pGPPublicKey.getVersion(), pGPPublicKey.getFingerprint()));
    }

    public void setIssuerFingerprint(boolean z, PGPSecretKey pGPSecretKey) {
        setIssuerFingerprint(z, pGPSecretKey.getPublicKey());
    }

    public void setIssuerKeyID(boolean z, long j) {
        this.list.add(new IssuerKeyID(z, j));
    }

    public void setKeyExpirationTime(boolean z, long j) {
        this.list.add(new KeyExpirationTime(z, j));
    }

    public void setKeyFlags(boolean z, int i) {
        this.list.add(new KeyFlags(z, i));
    }

    public void setNotationData(boolean z, boolean z2, String str, String str2) {
        this.list.add(new NotationData(z, z2, str, str2));
    }

    public void setPreferredCompressionAlgorithms(boolean z, int[] iArr) {
        this.list.add(new PreferredAlgorithms(22, z, iArr));
    }

    public void setPreferredHashAlgorithms(boolean z, int[] iArr) {
        this.list.add(new PreferredAlgorithms(21, z, iArr));
    }

    public void setPreferredSymmetricAlgorithms(boolean z, int[] iArr) {
        this.list.add(new PreferredAlgorithms(11, z, iArr));
    }

    public void setPrimaryUserID(boolean z, boolean z2) {
        this.list.add(new PrimaryUserID(z, z2));
    }

    public void setRevocable(boolean z, boolean z2) {
        this.list.add(new Revocable(z, z2));
    }

    public void setRevocationKey(boolean z, int i, byte[] bArr) {
        this.list.add(new RevocationKey(z, Byte.MIN_VALUE, i, bArr));
    }

    public void setRevocationReason(boolean z, byte b, String str) {
        this.list.add(new RevocationReason(z, b, str));
    }

    public void setSignatureCreationTime(boolean z, Date date) {
        this.list.add(new SignatureCreationTime(z, date));
    }

    public void setSignatureExpirationTime(boolean z, long j) {
        this.list.add(new SignatureExpirationTime(z, j));
    }

    public void setSignatureTarget(boolean z, int i, int i2, byte[] bArr) {
        this.list.add(new SignatureTarget(z, i, i2, bArr));
    }

    public void setSignerUserID(boolean z, String str) {
        if (str != null) {
            this.list.add(new SignerUserID(z, str));
            return;
        }
        throw new IllegalArgumentException("attempt to set null SignerUserID");
    }

    public void setSignerUserID(boolean z, byte[] bArr) {
        if (bArr != null) {
            this.list.add(new SignerUserID(z, false, bArr));
            return;
        }
        throw new IllegalArgumentException("attempt to set null SignerUserID");
    }

    public void setTrust(boolean z, int i, int i2) {
        this.list.add(new TrustSignature(z, i, i2));
    }
}
