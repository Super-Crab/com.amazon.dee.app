package com.amazon.alexa.device.setup.echo.bouncycastle.cms;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Encodable;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Set;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms.KEKRecipientInfo;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms.KeyAgreeRecipientInfo;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms.KeyTransRecipientInfo;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms.PasswordRecipientInfo;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms.RecipientInfo;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.AlgorithmIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.operator.DigestCalculator;
import com.amazonaws.services.s3.internal.crypto.JceEncryptionConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.KeyGenerator;
/* loaded from: classes.dex */
class CMSEnvelopedHelper {
    static final CMSEnvelopedHelper INSTANCE = new CMSEnvelopedHelper();
    private static final Map KEYSIZES = new HashMap();
    private static final Map BASE_CIPHER_NAMES = new HashMap();
    private static final Map CIPHER_ALG_NAMES = new HashMap();
    private static final Map MAC_ALG_NAMES = new HashMap();

    /* loaded from: classes.dex */
    static class CMSAuthenticatedSecureReadable implements CMSSecureReadable {
        private final AlgorithmIdentifier algorithm;
        private final CMSReadable readable;

        CMSAuthenticatedSecureReadable(AlgorithmIdentifier algorithmIdentifier, CMSReadable cMSReadable) {
            this.algorithm = algorithmIdentifier;
            this.readable = cMSReadable;
        }

        @Override // com.amazon.alexa.device.setup.echo.bouncycastle.cms.CMSSecureReadable
        public InputStream getInputStream() throws IOException, CMSException {
            return this.readable.getInputStream();
        }
    }

    /* loaded from: classes.dex */
    static class CMSDigestAuthenticatedSecureReadable implements CMSSecureReadable {
        private final DigestCalculator digestCalculator;
        private final CMSReadable readable;

        public CMSDigestAuthenticatedSecureReadable(DigestCalculator digestCalculator, CMSReadable cMSReadable) {
            this.digestCalculator = digestCalculator;
            this.readable = cMSReadable;
        }

        public byte[] getDigest() {
            return this.digestCalculator.getDigest();
        }

        @Override // com.amazon.alexa.device.setup.echo.bouncycastle.cms.CMSSecureReadable
        public InputStream getInputStream() throws IOException, CMSException {
            return new FilterInputStream(this.readable.getInputStream()) { // from class: com.amazon.alexa.device.setup.echo.bouncycastle.cms.CMSEnvelopedHelper.CMSDigestAuthenticatedSecureReadable.1
                @Override // java.io.FilterInputStream, java.io.InputStream
                public int read() throws IOException {
                    int read = ((FilterInputStream) this).in.read();
                    if (read >= 0) {
                        CMSDigestAuthenticatedSecureReadable.this.digestCalculator.getOutputStream().write(read);
                    }
                    return read;
                }

                @Override // java.io.FilterInputStream, java.io.InputStream
                public int read(byte[] bArr, int i, int i2) throws IOException {
                    int read = ((FilterInputStream) this).in.read(bArr, i, i2);
                    if (read >= 0) {
                        CMSDigestAuthenticatedSecureReadable.this.digestCalculator.getOutputStream().write(bArr, i, read);
                    }
                    return read;
                }
            };
        }
    }

    /* loaded from: classes.dex */
    static class CMSEnvelopedSecureReadable implements CMSSecureReadable {
        private final AlgorithmIdentifier algorithm;
        private final CMSReadable readable;

        /* JADX INFO: Access modifiers changed from: package-private */
        public CMSEnvelopedSecureReadable(AlgorithmIdentifier algorithmIdentifier, CMSReadable cMSReadable) {
            this.algorithm = algorithmIdentifier;
            this.readable = cMSReadable;
        }

        @Override // com.amazon.alexa.device.setup.echo.bouncycastle.cms.CMSSecureReadable
        public InputStream getInputStream() throws IOException, CMSException {
            return this.readable.getInputStream();
        }
    }

    static {
        KEYSIZES.put(CMSEnvelopedGenerator.DES_EDE3_CBC, new Integer(192));
        KEYSIZES.put(CMSEnvelopedGenerator.AES128_CBC, new Integer(128));
        KEYSIZES.put(CMSEnvelopedGenerator.AES192_CBC, new Integer(192));
        KEYSIZES.put(CMSEnvelopedGenerator.AES256_CBC, new Integer(256));
        BASE_CIPHER_NAMES.put(CMSEnvelopedGenerator.DES_EDE3_CBC, "DESEDE");
        BASE_CIPHER_NAMES.put(CMSEnvelopedGenerator.AES128_CBC, JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM);
        BASE_CIPHER_NAMES.put(CMSEnvelopedGenerator.AES192_CBC, JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM);
        BASE_CIPHER_NAMES.put(CMSEnvelopedGenerator.AES256_CBC, JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM);
        CIPHER_ALG_NAMES.put(CMSEnvelopedGenerator.DES_EDE3_CBC, "DESEDE/CBC/PKCS5Padding");
        CIPHER_ALG_NAMES.put(CMSEnvelopedGenerator.AES128_CBC, JceEncryptionConstants.SYMMETRIC_CIPHER_METHOD);
        CIPHER_ALG_NAMES.put(CMSEnvelopedGenerator.AES192_CBC, JceEncryptionConstants.SYMMETRIC_CIPHER_METHOD);
        CIPHER_ALG_NAMES.put(CMSEnvelopedGenerator.AES256_CBC, JceEncryptionConstants.SYMMETRIC_CIPHER_METHOD);
        MAC_ALG_NAMES.put(CMSEnvelopedGenerator.DES_EDE3_CBC, "DESEDEMac");
        MAC_ALG_NAMES.put(CMSEnvelopedGenerator.AES128_CBC, "AESMac");
        MAC_ALG_NAMES.put(CMSEnvelopedGenerator.AES192_CBC, "AESMac");
        MAC_ALG_NAMES.put(CMSEnvelopedGenerator.AES256_CBC, "AESMac");
    }

    CMSEnvelopedHelper() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static RecipientInformationStore buildRecipientInformationStore(ASN1Set aSN1Set, AlgorithmIdentifier algorithmIdentifier, CMSSecureReadable cMSSecureReadable) {
        return buildRecipientInformationStore(aSN1Set, algorithmIdentifier, cMSSecureReadable, null);
    }

    private KeyGenerator createKeyGenerator(String str, Provider provider) throws NoSuchAlgorithmException {
        if (provider != null) {
            return KeyGenerator.getInstance(str, provider);
        }
        return KeyGenerator.getInstance(str);
    }

    private static void readRecipientInfo(List list, RecipientInfo recipientInfo, AlgorithmIdentifier algorithmIdentifier, CMSSecureReadable cMSSecureReadable, AuthAttributesProvider authAttributesProvider) {
        ASN1Encodable info = recipientInfo.getInfo();
        if (info instanceof KeyTransRecipientInfo) {
            list.add(new KeyTransRecipientInformation((KeyTransRecipientInfo) info, algorithmIdentifier, cMSSecureReadable, authAttributesProvider));
        } else if (info instanceof KEKRecipientInfo) {
            list.add(new KEKRecipientInformation((KEKRecipientInfo) info, algorithmIdentifier, cMSSecureReadable, authAttributesProvider));
        } else if (info instanceof KeyAgreeRecipientInfo) {
            KeyAgreeRecipientInformation.readRecipientInfo(list, (KeyAgreeRecipientInfo) info, algorithmIdentifier, cMSSecureReadable, authAttributesProvider);
        } else if (!(info instanceof PasswordRecipientInfo)) {
        } else {
            list.add(new PasswordRecipientInformation((PasswordRecipientInfo) info, algorithmIdentifier, cMSSecureReadable, authAttributesProvider));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public KeyGenerator createSymmetricKeyGenerator(String str, Provider provider) throws NoSuchAlgorithmException {
        try {
            return createKeyGenerator(str, provider);
        } catch (NoSuchAlgorithmException e) {
            try {
                String str2 = (String) BASE_CIPHER_NAMES.get(str);
                if (str2 != null) {
                    return createKeyGenerator(str2, provider);
                }
            } catch (NoSuchAlgorithmException unused) {
            }
            if (provider != null) {
                return createSymmetricKeyGenerator(str, null);
            }
            throw e;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getKeySize(String str) {
        Integer num = (Integer) KEYSIZES.get(str);
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("no keysize for ", str));
    }

    static RecipientInformationStore buildRecipientInformationStore(ASN1Set aSN1Set, AlgorithmIdentifier algorithmIdentifier, CMSSecureReadable cMSSecureReadable, AuthAttributesProvider authAttributesProvider) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i != aSN1Set.size(); i++) {
            readRecipientInfo(arrayList, RecipientInfo.getInstance(aSN1Set.getObjectAt(i)), algorithmIdentifier, cMSSecureReadable, authAttributesProvider);
        }
        return new RecipientInformationStore(arrayList);
    }
}
