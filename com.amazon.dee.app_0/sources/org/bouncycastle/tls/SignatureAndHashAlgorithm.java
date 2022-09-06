package org.bouncycastle.tls;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/* loaded from: classes5.dex */
public class SignatureAndHashAlgorithm {
    protected final short hash;
    protected final short signature;
    public static final SignatureAndHashAlgorithm ed25519 = new SignatureAndHashAlgorithm(8, 7);
    public static final SignatureAndHashAlgorithm ed448 = new SignatureAndHashAlgorithm(8, 8);
    public static final SignatureAndHashAlgorithm rsa_pss_rsae_sha256 = new SignatureAndHashAlgorithm(8, 4);
    public static final SignatureAndHashAlgorithm rsa_pss_rsae_sha384 = new SignatureAndHashAlgorithm(8, 5);
    public static final SignatureAndHashAlgorithm rsa_pss_rsae_sha512 = new SignatureAndHashAlgorithm(8, 6);
    public static final SignatureAndHashAlgorithm rsa_pss_pss_sha256 = new SignatureAndHashAlgorithm(8, 9);
    public static final SignatureAndHashAlgorithm rsa_pss_pss_sha384 = new SignatureAndHashAlgorithm(8, 10);
    public static final SignatureAndHashAlgorithm rsa_pss_pss_sha512 = new SignatureAndHashAlgorithm(8, 11);

    public SignatureAndHashAlgorithm(short s, short s2) {
        if ((s & 255) == s) {
            if ((s2 & 255) != s2) {
                throw new IllegalArgumentException("'signature' should be a uint8");
            }
            this.hash = s;
            this.signature = s2;
            return;
        }
        throw new IllegalArgumentException("'hash' should be a uint8");
    }

    public static SignatureAndHashAlgorithm getInstance(short s, short s2) {
        return s != 8 ? new SignatureAndHashAlgorithm(s, s2) : getInstanceIntrinsic(s2);
    }

    public static SignatureAndHashAlgorithm getInstanceIntrinsic(short s) {
        switch (s) {
            case 4:
                return rsa_pss_rsae_sha256;
            case 5:
                return rsa_pss_rsae_sha384;
            case 6:
                return rsa_pss_rsae_sha512;
            case 7:
                return ed25519;
            case 8:
                return ed448;
            case 9:
                return rsa_pss_pss_sha256;
            case 10:
                return rsa_pss_pss_sha384;
            case 11:
                return rsa_pss_pss_sha512;
            default:
                return new SignatureAndHashAlgorithm((short) 8, s);
        }
    }

    public static SignatureAndHashAlgorithm parse(InputStream inputStream) throws IOException {
        return getInstance(TlsUtils.readUint8(inputStream), TlsUtils.readUint8(inputStream));
    }

    public void encode(OutputStream outputStream) throws IOException {
        TlsUtils.writeUint8(getHash(), outputStream);
        TlsUtils.writeUint8(getSignature(), outputStream);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SignatureAndHashAlgorithm)) {
            return false;
        }
        SignatureAndHashAlgorithm signatureAndHashAlgorithm = (SignatureAndHashAlgorithm) obj;
        return signatureAndHashAlgorithm.getHash() == getHash() && signatureAndHashAlgorithm.getSignature() == getSignature();
    }

    public short getHash() {
        return this.hash;
    }

    public short getSignature() {
        return this.signature;
    }

    public int hashCode() {
        return (getHash() << 16) | getSignature();
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        outline107.append(HashAlgorithm.getText(this.hash));
        outline107.append(",");
        outline107.append(SignatureAlgorithm.getText(this.signature));
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }
}
