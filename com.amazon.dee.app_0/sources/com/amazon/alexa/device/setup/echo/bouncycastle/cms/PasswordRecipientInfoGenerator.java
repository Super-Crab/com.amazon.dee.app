package com.amazon.alexa.device.setup.echo.bouncycastle.cms;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Encodable;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1EncodableVector;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1ObjectIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DEROctetString;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERSequence;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms.PasswordRecipientInfo;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms.RecipientInfo;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.pkcs.PBKDF2Params;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.AlgorithmIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.crypto.PBEParametersGenerator;
import com.amazon.alexa.device.setup.echo.bouncycastle.crypto.generators.PKCS5S2ParametersGenerator;
import com.amazon.alexa.device.setup.echo.bouncycastle.crypto.params.KeyParameter;
import com.amazon.alexa.device.setup.echo.bouncycastle.operator.GenericKey;
import java.security.SecureRandom;
/* loaded from: classes.dex */
public abstract class PasswordRecipientInfoGenerator implements RecipientInfoGenerator {
    private final int blockSize;
    private final ASN1ObjectIdentifier kekAlgorithm;
    private AlgorithmIdentifier keyDerivationAlgorithm;
    private final int keySize;
    private final char[] password;
    private SecureRandom random;
    private int schemeID;

    /* JADX INFO: Access modifiers changed from: protected */
    public PasswordRecipientInfoGenerator(ASN1ObjectIdentifier aSN1ObjectIdentifier, char[] cArr) {
        this(aSN1ObjectIdentifier, cArr, getKeySize(aSN1ObjectIdentifier), ((Integer) PasswordRecipientInformation.BLOCKSIZES.get(aSN1ObjectIdentifier)).intValue());
    }

    private static int getKeySize(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        Integer num = (Integer) PasswordRecipientInformation.KEYSIZES.get(aSN1ObjectIdentifier);
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalArgumentException("cannot find key size for algorithm: " + aSN1ObjectIdentifier);
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.cms.RecipientInfoGenerator
    public RecipientInfo generate(GenericKey genericKey) throws CMSException {
        byte[] key;
        byte[] bArr = new byte[this.blockSize];
        if (this.random == null) {
            this.random = new SecureRandom();
        }
        this.random.nextBytes(bArr);
        if (this.keyDerivationAlgorithm == null) {
            byte[] bArr2 = new byte[20];
            this.random.nextBytes(bArr2);
            this.keyDerivationAlgorithm = new AlgorithmIdentifier(PKCSObjectIdentifiers.id_PBKDF2, (ASN1Encodable) new PBKDF2Params(bArr2, 1024));
        }
        PBKDF2Params pBKDF2Params = PBKDF2Params.getInstance(this.keyDerivationAlgorithm.getParameters());
        if (this.schemeID == 0) {
            PKCS5S2ParametersGenerator pKCS5S2ParametersGenerator = new PKCS5S2ParametersGenerator();
            pKCS5S2ParametersGenerator.init(PBEParametersGenerator.PKCS5PasswordToBytes(this.password), pBKDF2Params.getSalt(), pBKDF2Params.getIterationCount().intValue());
            key = ((KeyParameter) pKCS5S2ParametersGenerator.generateDerivedParameters(this.keySize)).getKey();
        } else {
            PKCS5S2ParametersGenerator pKCS5S2ParametersGenerator2 = new PKCS5S2ParametersGenerator();
            pKCS5S2ParametersGenerator2.init(PBEParametersGenerator.PKCS5PasswordToUTF8Bytes(this.password), pBKDF2Params.getSalt(), pBKDF2Params.getIterationCount().intValue());
            key = ((KeyParameter) pKCS5S2ParametersGenerator2.generateDerivedParameters(this.keySize)).getKey();
        }
        DEROctetString dEROctetString = new DEROctetString(generateEncryptedBytes(new AlgorithmIdentifier(this.kekAlgorithm, (ASN1Encodable) new DEROctetString(bArr)), key, genericKey));
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.kekAlgorithm);
        aSN1EncodableVector.add(new DEROctetString(bArr));
        return new RecipientInfo(new PasswordRecipientInfo(this.keyDerivationAlgorithm, new AlgorithmIdentifier(PKCSObjectIdentifiers.id_alg_PWRI_KEK, (ASN1Encodable) new DERSequence(aSN1EncodableVector)), dEROctetString));
    }

    protected abstract byte[] generateEncryptedBytes(AlgorithmIdentifier algorithmIdentifier, byte[] bArr, GenericKey genericKey) throws CMSException;

    public PasswordRecipientInfoGenerator setPasswordConversionScheme(int i) {
        this.schemeID = i;
        return this;
    }

    public PasswordRecipientInfoGenerator setSaltAndIterationCount(byte[] bArr, int i) {
        this.keyDerivationAlgorithm = new AlgorithmIdentifier(PKCSObjectIdentifiers.id_PBKDF2, (ASN1Encodable) new PBKDF2Params(bArr, i));
        return this;
    }

    public PasswordRecipientInfoGenerator setSecureRandom(SecureRandom secureRandom) {
        this.random = secureRandom;
        return this;
    }

    protected PasswordRecipientInfoGenerator(ASN1ObjectIdentifier aSN1ObjectIdentifier, char[] cArr, int i, int i2) {
        this.password = cArr;
        this.schemeID = 1;
        this.kekAlgorithm = aSN1ObjectIdentifier;
        this.keySize = i;
        this.blockSize = i2;
    }
}
