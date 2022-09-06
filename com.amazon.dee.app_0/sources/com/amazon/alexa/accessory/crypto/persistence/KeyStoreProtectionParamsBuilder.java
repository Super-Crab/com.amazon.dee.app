package com.amazon.alexa.accessory.crypto.persistence;

import android.security.keystore.KeyProtection;
import androidx.annotation.RequiresApi;
import com.amazon.alexa.accessory.crypto.cipher.SupportedCipherSuite;
import com.google.common.base.Supplier;
import java.security.KeyStore;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
@RequiresApi(api = 23)
/* loaded from: classes.dex */
class KeyStoreProtectionParamsBuilder {
    private static final Supplier<KeyStore.ProtectionParameter> AES_CBC_PKCS_7_PADDING_PROTECTION_PARAMS = $$Lambda$KeyStoreProtectionParamsBuilder$z3zsBik47MuTQr9cDIj7cDfAkU.INSTANCE;
    private static final Supplier<KeyStore.ProtectionParameter> AES_GCM_NOPADDING_PROTECTION_PARAMS = $$Lambda$KeyStoreProtectionParamsBuilder$3JPKZIAgTAynt0tJMbYHD_POA.INSTANCE;
    private static final Map<SupportedCipherSuite, Supplier<KeyStore.ProtectionParameter>> DECRYPTION_PROTECTION_PARAMS = Collections.unmodifiableMap(new EnumMap<SupportedCipherSuite, Supplier<KeyStore.ProtectionParameter>>(SupportedCipherSuite.class) { // from class: com.amazon.alexa.accessory.crypto.persistence.KeyStoreProtectionParamsBuilder.1
        {
            put((AnonymousClass1) SupportedCipherSuite.SYMMETRIC_WITH_AES_128_CBC_PKCS7PADDING_SHA256, (SupportedCipherSuite) KeyStoreProtectionParamsBuilder.AES_CBC_PKCS_7_PADDING_PROTECTION_PARAMS);
            put((AnonymousClass1) SupportedCipherSuite.SYMMETRIC_WITH_AES_256_CBC_PKCS7PADDING_SHA256, (SupportedCipherSuite) KeyStoreProtectionParamsBuilder.AES_CBC_PKCS_7_PADDING_PROTECTION_PARAMS);
            put((AnonymousClass1) SupportedCipherSuite.SYMMETRIC_WITH_AES_128_GCM_SHA256, (SupportedCipherSuite) KeyStoreProtectionParamsBuilder.AES_GCM_NOPADDING_PROTECTION_PARAMS);
            put((AnonymousClass1) SupportedCipherSuite.SYMMETRIC_WITH_AES_256_GCM_SHA256, (SupportedCipherSuite) KeyStoreProtectionParamsBuilder.AES_GCM_NOPADDING_PROTECTION_PARAMS);
        }
    });

    KeyStore.ProtectionParameter forAuthenticationKey(SupportedCipherSuite supportedCipherSuite) {
        return new KeyProtection.Builder(12).setDigests("SHA-256").build();
    }

    KeyStore.ProtectionParameter forDecryptionKey(SupportedCipherSuite supportedCipherSuite) {
        return DECRYPTION_PROTECTION_PARAMS.get(supportedCipherSuite).mo8291get();
    }
}
