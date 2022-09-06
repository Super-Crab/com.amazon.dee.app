package com.amazon.alexa.accessory.transport.codecs.V3;

import com.amazon.alexa.accessory.crypto.CryptoBundle;
import com.amazon.alexa.accessory.crypto.CryptoBundleProvider;
import com.amazon.alexa.accessory.crypto.cipher.SupportedCipherSuite;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.transport.codecs.V3.V3TransportCodec;
import com.google.common.io.BaseEncoding;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import javax.crypto.AEADBadTagException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
/* loaded from: classes6.dex */
public class V3TransactionDataDecryptor {
    private static final int AES_BLOCK_CIPHER_IV_LENGTH_BYTES = 16;
    private static final int AES_GCM_CIPHER_IV_LENGTH_BYTES = 12;
    private static final BaseEncoding HEX_ENCODER = BaseEncoding.base16().upperCase();
    private static final Map<SupportedCipherSuite, CipherSuiteSpecificPayloadParser> PAYLOAD_PARSERS;
    private static final int SHA256_MAC_LENGTH_BYTES = 32;
    private final CryptoBundleProvider cryptoCache;
    private final SecureRandom random;

    /* loaded from: classes6.dex */
    private static final class AESBlockCipherSHA256PayloadParser implements CipherSuiteSpecificPayloadParser {
        private AESBlockCipherSHA256PayloadParser() {
        }

        @Override // com.amazon.alexa.accessory.transport.codecs.V3.V3TransactionDataDecryptor.CipherSuiteSpecificPayloadParser
        public EncryptedPayloadFormat parse(byte[] bArr) throws EncryptedPayloadParsingException {
            if (48 <= bArr.length) {
                return new EncryptedPayloadFormat(0, 16, 16, (bArr.length - 16) - 32, bArr.length - 32, 32);
            }
            throw new EncryptedPayloadParsingException(String.format("Encrypted payload is of insufficient length to include an IV and a MAC. MinimumRequired: %d bytes; Actual: %d bytes", 48, Integer.valueOf(bArr.length)));
        }
    }

    /* loaded from: classes6.dex */
    private static final class AESGCMCipherPayloadParser implements CipherSuiteSpecificPayloadParser {
        private AESGCMCipherPayloadParser() {
        }

        @Override // com.amazon.alexa.accessory.transport.codecs.V3.V3TransactionDataDecryptor.CipherSuiteSpecificPayloadParser
        public EncryptedPayloadFormat parse(byte[] bArr) throws EncryptedPayloadParsingException {
            if (12 <= bArr.length) {
                return new EncryptedPayloadFormat(0, 12, 12, bArr.length - 12, bArr.length, 0);
            }
            throw new EncryptedPayloadParsingException(String.format("Encrypted GCM payload is of insufficient length to include an IV. MinimumRequired: %d bytes; Actual: %d bytes", 16, Integer.valueOf(bArr.length)));
        }
    }

    /* loaded from: classes6.dex */
    private interface CipherSuiteSpecificPayloadParser {
        EncryptedPayloadFormat parse(byte[] bArr) throws EncryptedPayloadParsingException;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static final class EncryptedPayloadFormat {
        private final int cipherTextLength;
        private final int cipherTextOffset;
        private final int ivLength;
        private final int ivOffset;
        private final int macLength;
        private final int macOffset;

        private EncryptedPayloadFormat(int i, int i2, int i3, int i4, int i5, int i6) {
            this.ivOffset = i;
            this.ivLength = i2;
            this.cipherTextOffset = i3;
            this.cipherTextLength = i4;
            this.macOffset = i5;
            this.macLength = i6;
        }
    }

    /* loaded from: classes6.dex */
    public static final class EncryptedPayloadParsingException extends V3TransportCodec.V3TransportException {
        @Override // com.amazon.alexa.accessory.transport.codecs.V3.V3TransportCodec.V3TransportException
        public /* bridge */ /* synthetic */ int getTransportVersion() {
            return super.getTransportVersion();
        }

        private EncryptedPayloadParsingException(String str) {
            super(str);
        }
    }

    static {
        EnumMap enumMap = new EnumMap(SupportedCipherSuite.class);
        enumMap.put((EnumMap) SupportedCipherSuite.SYMMETRIC_WITH_AES_128_CBC_PKCS7PADDING_SHA256, (SupportedCipherSuite) new AESBlockCipherSHA256PayloadParser());
        enumMap.put((EnumMap) SupportedCipherSuite.SYMMETRIC_WITH_AES_256_CBC_PKCS7PADDING_SHA256, (SupportedCipherSuite) new AESBlockCipherSHA256PayloadParser());
        enumMap.put((EnumMap) SupportedCipherSuite.SYMMETRIC_WITH_AES_128_GCM_SHA256, (SupportedCipherSuite) new AESGCMCipherPayloadParser());
        enumMap.put((EnumMap) SupportedCipherSuite.SYMMETRIC_WITH_AES_256_GCM_SHA256, (SupportedCipherSuite) new AESGCMCipherPayloadParser());
        PAYLOAD_PARSERS = Collections.unmodifiableMap(enumMap);
    }

    public V3TransactionDataDecryptor(CryptoBundleProvider cryptoBundleProvider) {
        this(cryptoBundleProvider, new SecureRandom());
    }

    private void authenticatePayload(byte[] bArr, Key key, String str, EncryptedPayloadFormat encryptedPayloadFormat) throws AuthenticationException {
        if (encryptedPayloadFormat.macLength <= 0) {
            return;
        }
        String encode = HEX_ENCODER.encode(bArr, encryptedPayloadFormat.macOffset, encryptedPayloadFormat.macLength);
        try {
            Mac mac = Mac.getInstance(str);
            mac.init(key);
            mac.update(bArr, encryptedPayloadFormat.ivOffset, encryptedPayloadFormat.ivLength);
            mac.update(bArr, encryptedPayloadFormat.cipherTextOffset, encryptedPayloadFormat.cipherTextLength);
            String encode2 = HEX_ENCODER.encode(mac.doFinal());
            if (encode.equals(encode2)) {
                return;
            }
            throw new AuthenticationException(String.format("Computed HMAC did not match payload HMAC; expectedHMAC: %s, computedHMAC: %s", encode, encode2));
        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
            throw new AuthenticationException(String.format("Error computing HMAC to authenticate the transaction payload; expectedHMAC: %s", encode), e);
        }
    }

    public byte[] decryptDataBlob(byte[] bArr) throws DecryptionException, AuthenticationException, EncryptedPayloadParsingException {
        CryptoBundle fetchCryptoBundle = this.cryptoCache.fetchCryptoBundle();
        if (fetchCryptoBundle != null) {
            SupportedCipherSuite cipherSuite = fetchCryptoBundle.getCipherSuite();
            EncryptedPayloadFormat parse = PAYLOAD_PARSERS.get(cipherSuite).parse(bArr);
            authenticatePayload(bArr, fetchCryptoBundle.getAuthenticationKey(), cipherSuite.macAlgorithm, parse);
            AlgorithmParameterSpec build = cipherSuite.algorithmParameterSpecBuilder.build(bArr, parse.ivOffset, parse.ivLength);
            try {
                Cipher cipher = Cipher.getInstance(fetchCryptoBundle.getCipherTransform());
                cipher.init(2, fetchCryptoBundle.getDecryptionKey(), build, this.random);
                return cipher.doFinal(bArr, parse.cipherTextOffset, parse.cipherTextLength);
            } catch (InvalidAlgorithmParameterException e) {
                e = e;
                throw new DecryptionException(String.format("Error decrypting transaction payload; cipherAlgorithm: %s", fetchCryptoBundle.getCipherTransform()), e);
            } catch (InvalidKeyException e2) {
                e = e2;
                throw new DecryptionException(String.format("Error decrypting transaction payload; cipherAlgorithm: %s", fetchCryptoBundle.getCipherTransform()), e);
            } catch (NoSuchAlgorithmException e3) {
                e = e3;
                throw new DecryptionException(String.format("Error decrypting transaction payload; cipherAlgorithm: %s", fetchCryptoBundle.getCipherTransform()), e);
            } catch (AEADBadTagException e4) {
                throw new AuthenticationException("Error validating the authentication tag", e4);
            } catch (BadPaddingException e5) {
                e = e5;
                throw new DecryptionException(String.format("Error decrypting transaction payload; cipherAlgorithm: %s", fetchCryptoBundle.getCipherTransform()), e);
            } catch (IllegalBlockSizeException e6) {
                e = e6;
                throw new DecryptionException(String.format("Error decrypting transaction payload; cipherAlgorithm: %s", fetchCryptoBundle.getCipherTransform()), e);
            } catch (NoSuchPaddingException e7) {
                e = e7;
                throw new DecryptionException(String.format("Error decrypting transaction payload; cipherAlgorithm: %s", fetchCryptoBundle.getCipherTransform()), e);
            }
        }
        throw new DecryptionException("Error decrypting transaction payload - No Keys Available");
    }

    /* loaded from: classes6.dex */
    public static final class AuthenticationException extends V3TransportCodec.V3TransportException {
        @Override // com.amazon.alexa.accessory.transport.codecs.V3.V3TransportCodec.V3TransportException
        public /* bridge */ /* synthetic */ int getTransportVersion() {
            return super.getTransportVersion();
        }

        private AuthenticationException(String str) {
            super(str);
        }

        private AuthenticationException(String str, Throwable th) {
            this(str);
            initCause(th);
        }
    }

    /* loaded from: classes6.dex */
    public static final class DecryptionException extends V3TransportCodec.V3TransportException {
        @Override // com.amazon.alexa.accessory.transport.codecs.V3.V3TransportCodec.V3TransportException
        public /* bridge */ /* synthetic */ int getTransportVersion() {
            return super.getTransportVersion();
        }

        private DecryptionException(String str) {
            super(str);
        }

        private DecryptionException(String str, Throwable th) {
            this(str);
            initCause(th);
        }
    }

    public V3TransactionDataDecryptor(CryptoBundleProvider cryptoBundleProvider, SecureRandom secureRandom) {
        Preconditions.notNull(cryptoBundleProvider, "cryptoCache");
        Preconditions.notNull(secureRandom, "random");
        this.cryptoCache = cryptoBundleProvider;
        this.random = secureRandom;
    }
}
