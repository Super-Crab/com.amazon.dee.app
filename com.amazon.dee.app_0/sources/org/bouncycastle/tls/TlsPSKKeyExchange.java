package org.bouncycastle.tls;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.bouncycastle.tls.crypto.TlsAgreement;
import org.bouncycastle.tls.crypto.TlsCertificate;
import org.bouncycastle.tls.crypto.TlsCryptoParameters;
import org.bouncycastle.tls.crypto.TlsDHConfig;
import org.bouncycastle.tls.crypto.TlsECConfig;
import org.bouncycastle.tls.crypto.TlsSecret;
import org.bouncycastle.util.Arrays;
/* loaded from: classes5.dex */
public class TlsPSKKeyExchange extends AbstractTlsKeyExchange {
    protected TlsAgreement agreement;
    protected TlsDHConfig dhConfig;
    protected TlsDHGroupVerifier dhGroupVerifier;
    protected TlsECConfig ecConfig;
    protected TlsSecret preMasterSecret;
    protected byte[] psk;
    protected TlsPSKIdentity pskIdentity;
    protected TlsPSKIdentityManager pskIdentityManager;
    protected byte[] psk_identity_hint;
    protected TlsCertificate serverCertificate;
    protected TlsCredentialedDecryptor serverCredentials;

    public TlsPSKKeyExchange(int i, TlsPSKIdentity tlsPSKIdentity, TlsDHGroupVerifier tlsDHGroupVerifier) {
        this(i, tlsPSKIdentity, null, tlsDHGroupVerifier, null, null);
    }

    private TlsPSKKeyExchange(int i, TlsPSKIdentity tlsPSKIdentity, TlsPSKIdentityManager tlsPSKIdentityManager, TlsDHGroupVerifier tlsDHGroupVerifier, TlsDHConfig tlsDHConfig, TlsECConfig tlsECConfig) {
        super(checkKeyExchange(i));
        this.psk_identity_hint = null;
        this.psk = null;
        this.serverCredentials = null;
        this.pskIdentity = tlsPSKIdentity;
        this.pskIdentityManager = tlsPSKIdentityManager;
        this.dhGroupVerifier = tlsDHGroupVerifier;
        this.dhConfig = tlsDHConfig;
        this.ecConfig = tlsECConfig;
    }

    public TlsPSKKeyExchange(int i, TlsPSKIdentityManager tlsPSKIdentityManager, TlsDHConfig tlsDHConfig, TlsECConfig tlsECConfig) {
        this(i, null, tlsPSKIdentityManager, null, tlsDHConfig, tlsECConfig);
    }

    private static int checkKeyExchange(int i) {
        if (i != 24) {
            switch (i) {
                case 13:
                case 14:
                case 15:
                    break;
                default:
                    throw new IllegalArgumentException("unsupported key exchange algorithm");
            }
        }
        return i;
    }

    @Override // org.bouncycastle.tls.TlsKeyExchange
    public void generateClientKeyExchange(OutputStream outputStream) throws IOException {
        byte[] bArr = this.psk_identity_hint;
        if (bArr == null) {
            this.pskIdentity.skipIdentityHint();
        } else {
            this.pskIdentity.notifyIdentityHint(bArr);
        }
        byte[] pSKIdentity = this.pskIdentity.getPSKIdentity();
        if (pSKIdentity != null) {
            this.psk = this.pskIdentity.getPSK();
            if (this.psk == null) {
                throw new TlsFatalAlert((short) 80);
            }
            TlsUtils.writeOpaque16(pSKIdentity, outputStream);
            this.context.getSecurityParametersHandshake().pskIdentity = Arrays.clone(pSKIdentity);
            int i = this.keyExchange;
            if (i == 14) {
                generateEphemeralDH(outputStream);
                return;
            } else if (i == 24) {
                generateEphemeralECDH(outputStream);
                return;
            } else if (i != 15) {
                return;
            } else {
                this.preMasterSecret = TlsRSAUtils.generateEncryptedPreMasterSecret(this.context, this.serverCertificate, outputStream);
                return;
            }
        }
        throw new TlsFatalAlert((short) 80);
    }

    protected void generateEphemeralDH(OutputStream outputStream) throws IOException {
        TlsUtils.writeOpaque16(this.agreement.generateEphemeral(), outputStream);
    }

    protected void generateEphemeralECDH(OutputStream outputStream) throws IOException {
        TlsUtils.writeOpaque8(this.agreement.generateEphemeral(), outputStream);
    }

    protected byte[] generateOtherSecret(int i) throws IOException {
        TlsAgreement tlsAgreement;
        TlsSecret tlsSecret;
        int i2 = this.keyExchange;
        if (i2 == 13) {
            return new byte[i];
        }
        if ((i2 == 14 || i2 == 24) && (tlsAgreement = this.agreement) != null) {
            return tlsAgreement.calculateSecret().extract();
        }
        if (this.keyExchange == 15 && (tlsSecret = this.preMasterSecret) != null) {
            return tlsSecret.extract();
        }
        throw new TlsFatalAlert((short) 80);
    }

    @Override // org.bouncycastle.tls.TlsKeyExchange
    public TlsSecret generatePreMasterSecret() throws IOException {
        byte[] generateOtherSecret = generateOtherSecret(this.psk.length);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(generateOtherSecret.length + 4 + this.psk.length);
        TlsUtils.writeOpaque16(generateOtherSecret, byteArrayOutputStream);
        TlsUtils.writeOpaque16(this.psk, byteArrayOutputStream);
        Arrays.fill(this.psk, (byte) 0);
        this.psk = null;
        return this.context.getCrypto().createSecret(byteArrayOutputStream.toByteArray());
    }

    @Override // org.bouncycastle.tls.AbstractTlsKeyExchange, org.bouncycastle.tls.TlsKeyExchange
    public byte[] generateServerKeyExchange() throws IOException {
        this.psk_identity_hint = this.pskIdentityManager.getHint();
        if (this.psk_identity_hint != null || requiresServerKeyExchange()) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = this.psk_identity_hint;
            if (bArr == null) {
                TlsUtils.writeOpaque16(TlsUtils.EMPTY_BYTES, byteArrayOutputStream);
            } else {
                TlsUtils.writeOpaque16(bArr, byteArrayOutputStream);
            }
            int i = this.keyExchange;
            if (i == 14) {
                TlsDHConfig tlsDHConfig = this.dhConfig;
                if (tlsDHConfig == null) {
                    throw new TlsFatalAlert((short) 80);
                }
                TlsDHUtils.writeDHConfig(tlsDHConfig, byteArrayOutputStream);
                this.agreement = this.context.getCrypto().createDHDomain(this.dhConfig).createDH();
                generateEphemeralDH(byteArrayOutputStream);
            } else if (i == 24) {
                TlsECConfig tlsECConfig = this.ecConfig;
                if (tlsECConfig == null) {
                    throw new TlsFatalAlert((short) 80);
                }
                TlsECCUtils.writeECConfig(tlsECConfig, byteArrayOutputStream);
                this.agreement = this.context.getCrypto().createECDomain(this.ecConfig).createECDH();
                generateEphemeralECDH(byteArrayOutputStream);
            }
            return byteArrayOutputStream.toByteArray();
        }
        return null;
    }

    @Override // org.bouncycastle.tls.TlsKeyExchange
    public void processClientCredentials(TlsCredentials tlsCredentials) throws IOException {
        throw new TlsFatalAlert((short) 80);
    }

    @Override // org.bouncycastle.tls.AbstractTlsKeyExchange, org.bouncycastle.tls.TlsKeyExchange
    public void processClientKeyExchange(InputStream inputStream) throws IOException {
        byte[] readOpaque16 = TlsUtils.readOpaque16(inputStream);
        this.psk = this.pskIdentityManager.getPSK(readOpaque16);
        if (this.psk != null) {
            this.context.getSecurityParametersHandshake().pskIdentity = readOpaque16;
            int i = this.keyExchange;
            if (i == 14) {
                processEphemeralDH(TlsUtils.readOpaque16(inputStream, 1));
                return;
            } else if (i == 24) {
                processEphemeralECDH(TlsUtils.readOpaque8(inputStream, 1));
                return;
            } else if (i != 15) {
                return;
            } else {
                this.preMasterSecret = this.serverCredentials.decrypt(new TlsCryptoParameters(this.context), TlsUtils.readEncryptedPMS(this.context, inputStream));
                return;
            }
        }
        throw new TlsFatalAlert(AlertDescription.unknown_psk_identity);
    }

    protected void processEphemeralDH(byte[] bArr) throws IOException {
        this.agreement.receivePeerValue(bArr);
    }

    protected void processEphemeralECDH(byte[] bArr) throws IOException {
        TlsECCUtils.checkPointEncoding(this.ecConfig.getNamedGroup(), bArr);
        this.agreement.receivePeerValue(bArr);
    }

    @Override // org.bouncycastle.tls.AbstractTlsKeyExchange, org.bouncycastle.tls.TlsKeyExchange
    public void processServerCertificate(Certificate certificate) throws IOException {
        if (this.keyExchange == 15) {
            this.serverCertificate = certificate.getCertificateAt(0).useInRole(0, this.keyExchange);
            return;
        }
        throw new TlsFatalAlert((short) 10);
    }

    @Override // org.bouncycastle.tls.TlsKeyExchange
    public void processServerCredentials(TlsCredentials tlsCredentials) throws IOException {
        if (this.keyExchange == 15) {
            this.serverCredentials = TlsUtils.requireDecryptorCredentials(tlsCredentials);
            return;
        }
        throw new TlsFatalAlert((short) 80);
    }

    @Override // org.bouncycastle.tls.AbstractTlsKeyExchange, org.bouncycastle.tls.TlsKeyExchange
    public void processServerKeyExchange(InputStream inputStream) throws IOException {
        this.psk_identity_hint = TlsUtils.readOpaque16(inputStream);
        int i = this.keyExchange;
        if (i == 14) {
            this.dhConfig = TlsDHUtils.receiveDHConfig(this.context, this.dhGroupVerifier, inputStream);
            byte[] readOpaque16 = TlsUtils.readOpaque16(inputStream, 1);
            this.agreement = this.context.getCrypto().createDHDomain(this.dhConfig).createDH();
            processEphemeralDH(readOpaque16);
        } else if (i != 24) {
        } else {
            this.ecConfig = TlsECCUtils.receiveECDHConfig(this.context, inputStream);
            byte[] readOpaque8 = TlsUtils.readOpaque8(inputStream, 1);
            this.agreement = this.context.getCrypto().createECDomain(this.ecConfig).createECDH();
            processEphemeralECDH(readOpaque8);
        }
    }

    @Override // org.bouncycastle.tls.AbstractTlsKeyExchange, org.bouncycastle.tls.TlsKeyExchange
    public boolean requiresServerKeyExchange() {
        int i = this.keyExchange;
        return i == 14 || i == 24;
    }

    @Override // org.bouncycastle.tls.TlsKeyExchange
    public void skipServerCredentials() throws IOException {
        if (this.keyExchange != 15) {
            return;
        }
        throw new TlsFatalAlert((short) 80);
    }
}
