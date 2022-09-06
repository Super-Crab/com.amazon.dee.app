package org.bouncycastle.tls.crypto.impl.bc;

import java.io.IOException;
import org.bouncycastle.asn1.x9.ECNamedCurveTable;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.agreement.ECDHBasicAgreement;
import org.bouncycastle.crypto.ec.CustomNamedCurves;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.tls.NamedGroup;
import org.bouncycastle.tls.TlsFatalAlert;
import org.bouncycastle.tls.crypto.TlsAgreement;
import org.bouncycastle.tls.crypto.TlsECConfig;
import org.bouncycastle.tls.crypto.TlsECDomain;
import org.bouncycastle.util.BigIntegers;
/* loaded from: classes5.dex */
public class BcTlsECDomain implements TlsECDomain {
    protected final BcTlsCrypto crypto;
    protected final TlsECConfig ecConfig;
    protected final ECDomainParameters ecDomainParameters;

    public BcTlsECDomain(BcTlsCrypto bcTlsCrypto, TlsECConfig tlsECConfig) {
        this.crypto = bcTlsCrypto;
        this.ecConfig = tlsECConfig;
        this.ecDomainParameters = getDomainParameters(tlsECConfig);
    }

    public static BcTlsSecret calculateBasicAgreement(BcTlsCrypto bcTlsCrypto, ECPrivateKeyParameters eCPrivateKeyParameters, ECPublicKeyParameters eCPublicKeyParameters) {
        ECDHBasicAgreement eCDHBasicAgreement = new ECDHBasicAgreement();
        eCDHBasicAgreement.init(eCPrivateKeyParameters);
        return bcTlsCrypto.adoptLocalSecret(BigIntegers.asUnsignedByteArray(eCDHBasicAgreement.getFieldSize(), eCDHBasicAgreement.calculateAgreement(eCPublicKeyParameters)));
    }

    public static ECDomainParameters getDomainParameters(int i) {
        if (!NamedGroup.refersToASpecificCurve(i)) {
            return null;
        }
        String name = NamedGroup.getName(i);
        X9ECParameters byName = CustomNamedCurves.getByName(name);
        if (byName != null || (byName = ECNamedCurveTable.getByName(name)) != null) {
            return new ECDomainParameters(byName.getCurve(), byName.getG(), byName.getN(), byName.getH(), byName.getSeed());
        }
        return null;
    }

    public static ECDomainParameters getDomainParameters(TlsECConfig tlsECConfig) {
        return getDomainParameters(tlsECConfig.getNamedGroup());
    }

    public BcTlsSecret calculateECDHAgreement(ECPrivateKeyParameters eCPrivateKeyParameters, ECPublicKeyParameters eCPublicKeyParameters) {
        return calculateBasicAgreement(this.crypto, eCPrivateKeyParameters, eCPublicKeyParameters);
    }

    @Override // org.bouncycastle.tls.crypto.TlsECDomain
    public TlsAgreement createECDH() {
        return new BcTlsECDH(this);
    }

    public ECPoint decodePoint(byte[] bArr) throws IOException {
        return this.ecDomainParameters.getCurve().decodePoint(bArr);
    }

    public ECPublicKeyParameters decodePublicKey(byte[] bArr) throws IOException {
        try {
            return new ECPublicKeyParameters(decodePoint(bArr), this.ecDomainParameters);
        } catch (RuntimeException e) {
            throw new TlsFatalAlert((short) 47, (Throwable) e);
        }
    }

    public byte[] encodePoint(ECPoint eCPoint) throws IOException {
        return eCPoint.getEncoded(false);
    }

    public byte[] encodePublicKey(ECPublicKeyParameters eCPublicKeyParameters) throws IOException {
        return encodePoint(eCPublicKeyParameters.getQ());
    }

    public AsymmetricCipherKeyPair generateKeyPair() {
        ECKeyPairGenerator eCKeyPairGenerator = new ECKeyPairGenerator();
        eCKeyPairGenerator.init(new ECKeyGenerationParameters(this.ecDomainParameters, this.crypto.getSecureRandom()));
        return eCKeyPairGenerator.generateKeyPair();
    }
}
