package com.amazon.alexa.accessory.crypto;

import com.amazon.alexa.accessory.crypto.CryptoKeyDataStore;
import com.amazon.alexa.accessory.crypto.awscryptoutils.Hkdf;
import com.amazon.alexa.accessory.crypto.cipher.Nonce;
import com.amazon.alexa.accessory.crypto.cipher.SupportedCipherSuite;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.metrics.Stopwatch;
import com.amazonaws.services.s3.internal.crypto.JceEncryptionConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.collect.ImmutableSet;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
/* loaded from: classes.dex */
public class PersistenceBackedCryptoKeyDataStore implements CryptoKeyDataStore {
    private static final String HKDF_AUTHENTICATION_KEY_INFO = "authenticate";
    private static final String HKDF_ENCRYPTION_KEY_INFO = "encrypt";
    private final CipherTransformer cipherTransformer;
    private final Stopwatch.CurrentTimeSupplier clock;
    private final Object guard;
    private final NegotiatedDataPersistor persistenceLayer;
    private final SecureRandom secureRandom;

    /* loaded from: classes.dex */
    public static class AndroidKeyStoreCipherTransformer implements CipherTransformer {
        private static final Map<SupportedCipherSuite, String> TRANSFORM_MAP = Collections.unmodifiableMap(new EnumMap<SupportedCipherSuite, String>(DefaultSecurityProviderCipherTransformer.TRANSFORM_MAP) { // from class: com.amazon.alexa.accessory.crypto.PersistenceBackedCryptoKeyDataStore.AndroidKeyStoreCipherTransformer.1
            {
                put((AnonymousClass1) SupportedCipherSuite.SYMMETRIC_WITH_AES_128_CBC_PKCS7PADDING_SHA256, (SupportedCipherSuite) "AES/CBC/PKCS7Padding");
                put((AnonymousClass1) SupportedCipherSuite.SYMMETRIC_WITH_AES_256_CBC_PKCS7PADDING_SHA256, (SupportedCipherSuite) "AES/CBC/PKCS7Padding");
            }
        });

        @Override // com.amazon.alexa.accessory.crypto.PersistenceBackedCryptoKeyDataStore.CipherTransformer
        public String getTransform(SupportedCipherSuite supportedCipherSuite) {
            return TRANSFORM_MAP.get(supportedCipherSuite);
        }
    }

    /* loaded from: classes.dex */
    public interface CipherTransformer {
        String getTransform(SupportedCipherSuite supportedCipherSuite);
    }

    /* loaded from: classes.dex */
    public static class DefaultSecurityProviderCipherTransformer implements CipherTransformer {
        static final Map<SupportedCipherSuite, String> TRANSFORM_MAP = Collections.unmodifiableMap(new EnumMap<SupportedCipherSuite, String>(SupportedCipherSuite.class) { // from class: com.amazon.alexa.accessory.crypto.PersistenceBackedCryptoKeyDataStore.DefaultSecurityProviderCipherTransformer.1
            {
                put((AnonymousClass1) SupportedCipherSuite.SYMMETRIC_WITH_AES_128_CBC_PKCS7PADDING_SHA256, (SupportedCipherSuite) JceEncryptionConstants.SYMMETRIC_CIPHER_METHOD);
                put((AnonymousClass1) SupportedCipherSuite.SYMMETRIC_WITH_AES_256_CBC_PKCS7PADDING_SHA256, (SupportedCipherSuite) JceEncryptionConstants.SYMMETRIC_CIPHER_METHOD);
                put((AnonymousClass1) SupportedCipherSuite.SYMMETRIC_WITH_AES_128_GCM_SHA256, (SupportedCipherSuite) "AES/GCM/NoPadding");
                put((AnonymousClass1) SupportedCipherSuite.SYMMETRIC_WITH_AES_256_GCM_SHA256, (SupportedCipherSuite) "AES/GCM/NoPadding");
            }
        });

        @Override // com.amazon.alexa.accessory.crypto.PersistenceBackedCryptoKeyDataStore.CipherTransformer
        public String getTransform(SupportedCipherSuite supportedCipherSuite) {
            return TRANSFORM_MAP.get(supportedCipherSuite);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class DerivedKeys {
        private final Key authenticationKey;
        private final Key encryptionKey;

        private DerivedKeys(Key key, Key key2) {
            this.encryptionKey = key;
            this.authenticationKey = key2;
        }
    }

    public PersistenceBackedCryptoKeyDataStore(NegotiatedDataPersistor negotiatedDataPersistor, CipherTransformer cipherTransformer) {
        this(negotiatedDataPersistor, new SecureRandom(), $$Lambda$D1z_ytlzYvthAoeLOQGoy8VTB4U.INSTANCE, cipherTransformer);
    }

    private static DerivedKeys deriveKeys(SupportedCipherSuite supportedCipherSuite, Key key, Nonce nonce, Nonce nonce2, String str) throws CryptoKeyDataStore.InvalidInputsException {
        try {
            Hkdf hkdf = Hkdf.getInstance(supportedCipherSuite.macAlgorithm);
            hkdf.init(key.getEncoded(), nonce.getNonceValue().concat(nonce2.getNonceValue()).toByteArray());
            return new DerivedKeys(new SecretKeySpec(hkdf.deriveKey("encrypt", supportedCipherSuite.encryptionKeyStrengthInBytes), supportedCipherSuite.encryptionAlgorithm), new SecretKeySpec(hkdf.deriveKey(HKDF_AUTHENTICATION_KEY_INFO, supportedCipherSuite.macKeyStrengthInBytes), supportedCipherSuite.macAlgorithm));
        } catch (NoSuchAlgorithmException e) {
            ImmutableSet of = ImmutableSet.of("cipherSuite");
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("The specified cipher-suite has a mac algorithm not supported by HKDF: ");
            outline107.append(supportedCipherSuite.macAlgorithm);
            throw new CryptoKeyDataStore.InvalidInputsException(str, of, outline107.toString(), e);
        }
    }

    @Override // com.amazon.alexa.accessory.crypto.CryptoKeyDataStore
    public CryptoKeyDataStore.NegotiatedData generateKeys(String str, Nonce nonce, Nonce nonce2, SupportedCipherSuite supportedCipherSuite, int i) throws CryptoKeyDataStore.KeysAlreadyExistException, CryptoKeyDataStore.InvalidInputsException {
        CryptoKeyDataStore.NegotiatedData data;
        boolean z = true;
        CryptoKeyDataStore.InvalidInputsException.Accumulator accumulateField = CryptoKeyDataStore.InvalidInputsException.accumulator(str).accumulateField(str != null, "accessoryId").accumulateField(nonce != null, "nonceAccessory").accumulateField(nonce2 != null, "nonceApp").accumulateField(supportedCipherSuite != null, "cipherSuite");
        if (i <= 0) {
            z = false;
        }
        accumulateField.accumulateField(z, "keyExchangeProtocolVersion").throwIfInvalid("MemoryCryptoKeyDataStore.generateKeys - Invalid Inputs");
        synchronized (this.guard) {
            if (!this.persistenceLayer.hasData(str)) {
                long currentTimeMillis = this.clock.getCurrentTimeMillis();
                int max = Math.max(supportedCipherSuite.encryptionKeyStrengthInBytes, supportedCipherSuite.macKeyStrengthInBytes) * 8;
                try {
                    KeyGenerator keyGenerator = KeyGenerator.getInstance(supportedCipherSuite.encryptionAlgorithm);
                    keyGenerator.init(max, this.secureRandom);
                    SecretKey generateKey = keyGenerator.generateKey();
                    DerivedKeys deriveKeys = deriveKeys(supportedCipherSuite, generateKey, nonce, nonce2, str);
                    this.persistenceLayer.putData(str, new CryptoKeyDataStore.NegotiatedData(i, supportedCipherSuite, this.cipherTransformer.getTransform(supportedCipherSuite), generateKey, deriveKeys.encryptionKey, deriveKeys.authenticationKey, currentTimeMillis, 0L));
                    data = this.persistenceLayer.getData(str);
                } catch (NoSuchAlgorithmException e) {
                    throw new CryptoKeyDataStore.InvalidInputsException(str, ImmutableSet.of("cipherSuite"), "The encryption algorithm specified was not valid: " + supportedCipherSuite.encryptionAlgorithm, e);
                }
            } else {
                throw new CryptoKeyDataStore.KeysAlreadyExistException(str, "The KeyStore already contains a root-key on record for the accessory " + str);
            }
        }
        return data;
    }

    @Override // com.amazon.alexa.accessory.crypto.CryptoKeyDataStore
    public CryptoKeyDataStore.NegotiatedData getData(String str) {
        CryptoKeyDataStore.NegotiatedData data;
        if (str == null) {
            Logger.e("MemoryCryptoKeyDataStore.getData - null accessoryId");
            return null;
        }
        synchronized (this.guard) {
            data = this.persistenceLayer.getData(str);
        }
        return data;
    }

    @Override // com.amazon.alexa.accessory.crypto.CryptoKeyDataStore
    public CryptoKeyDataStore.ListResult listNegotiatedAccessories(int i, CryptoKeyDataStore.PaginationToken paginationToken) {
        CryptoKeyDataStore.ListResult listData;
        synchronized (this.guard) {
            listData = this.persistenceLayer.listData(i, paginationToken);
        }
        return listData;
    }

    @Override // com.amazon.alexa.accessory.crypto.CryptoKeyDataStore
    public void removeData(String str) {
        removeData(Collections.singletonList(str));
    }

    @Override // com.amazon.alexa.accessory.crypto.CryptoKeyDataStore
    public CryptoKeyDataStore.NegotiatedData rotateDerivedKeys(String str, Nonce nonce, Nonce nonce2) throws CryptoKeyDataStore.UnrecognizedAccessoryException, CryptoKeyDataStore.InvalidInputsException {
        CryptoKeyDataStore.NegotiatedData data;
        boolean z = true;
        CryptoKeyDataStore.InvalidInputsException.Accumulator accumulateField = CryptoKeyDataStore.InvalidInputsException.accumulator(str).accumulateField(str != null, "accessoryId").accumulateField(nonce != null, "nonceAccessory");
        if (nonce2 == null) {
            z = false;
        }
        accumulateField.accumulateField(z, "nonceApp").throwIfInvalid("MemoryCryptoKeyDataStore.rotateDerivedKeys - Invalid Inputs");
        synchronized (this.guard) {
            CryptoKeyDataStore.NegotiatedData data2 = this.persistenceLayer.getData(str);
            if (data2 != null) {
                long currentTimeMillis = this.clock.getCurrentTimeMillis();
                DerivedKeys deriveKeys = deriveKeys(data2.cipherSuite, data2.rootKey, nonce, nonce2, str);
                int i = data2.keyExchangeProtocolVersion;
                SupportedCipherSuite supportedCipherSuite = data2.cipherSuite;
                this.persistenceLayer.putData(str, new CryptoKeyDataStore.NegotiatedData(i, supportedCipherSuite, this.cipherTransformer.getTransform(supportedCipherSuite), data2.rootKey, deriveKeys.encryptionKey, deriveKeys.authenticationKey, data2.keyExchangeTimestampMillis, currentTimeMillis));
                data = this.persistenceLayer.getData(str);
            } else {
                throw new CryptoKeyDataStore.UnrecognizedAccessoryException(str, "The CryptoKeyDataStore has no keys on record for accessory " + str);
            }
        }
        return data;
    }

    public PersistenceBackedCryptoKeyDataStore(NegotiatedDataPersistor negotiatedDataPersistor, SecureRandom secureRandom, Stopwatch.CurrentTimeSupplier currentTimeSupplier, CipherTransformer cipherTransformer) {
        Preconditions.notNull(negotiatedDataPersistor, "persistenceLayer");
        Preconditions.notNull(secureRandom, "secureRandom");
        Preconditions.notNull(currentTimeSupplier, "clock");
        this.persistenceLayer = negotiatedDataPersistor;
        this.secureRandom = secureRandom;
        this.clock = currentTimeSupplier;
        this.cipherTransformer = cipherTransformer;
        this.guard = new Object();
    }

    @Override // com.amazon.alexa.accessory.crypto.CryptoKeyDataStore
    public void removeData(Iterable<String> iterable) {
        if (iterable == null) {
            return;
        }
        synchronized (this.guard) {
            this.persistenceLayer.deleteData(iterable);
        }
    }
}
