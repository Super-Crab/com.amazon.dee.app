package com.amazon.dee.app.services.messaging;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsOperationalEvent;
import com.amazon.alexa.mobilytics.event.operational.OperationalEventType;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.crypto.KeyManager;
import com.amazon.crypto.asymmetric.AsymmetricKeys;
import com.amazon.crypto.asymmetric.AsymmetricKeysManager;
import com.amazon.crypto.symmetric.SymmetricKey;
import com.amazon.crypto.utils.Base64;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import dagger.Lazy;
import java.nio.charset.StandardCharsets;
/* loaded from: classes12.dex */
public final class DefaultMessageCrypto implements MessageCrypto {
    private static final String CRYPTO_SETTINGS_NAMESPACE = "AlexaMobileMessageCryptoSettings";
    private static final String HAS_ATTEMPTED_KEY_GEN = "hasAttemptedKeyGen";
    private static final String MESSAGE_CRYPTO_KEY_PAIR_ALIAS = "AlexaMobileMessageCryptoKeys";
    private static final String TAG = "DefaultMessageCrypto";
    private final CryptoFactory cryptoFactory;
    private final PersistentStorage cryptoSettings;
    private final KeyManager<AsymmetricKeys> keyManager;
    private final Lazy<Mobilytics> mobilytics;

    public DefaultMessageCrypto(@NonNull Lazy<Mobilytics> lazy, @NonNull PersistentStorage.Factory factory, @NonNull Context context, @NonNull CryptoFactory cryptoFactory) {
        this(lazy, factory.create(CRYPTO_SETTINGS_NAMESPACE), getManager(context), cryptoFactory);
    }

    private static KeyManager<AsymmetricKeys> getManager(Context context) {
        try {
            return AsymmetricKeysManager.with(context);
        } catch (UnsupportedOperationException unused) {
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0034 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0035 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.amazon.crypto.asymmetric.AsymmetricKeys managedKeys(java.lang.String r8) {
        /*
            r7 = this;
            java.lang.String r0 = "CRYPTO_RETRIEVE_KEY_PAIR_ERROR_PERCENT"
            java.lang.String r1 = "CRYPTO_GENERATE_KEY_PAIR_ERROR_PERCENT"
            java.lang.String r2 = "hasAttemptedKeyGen"
            com.amazon.crypto.KeyManager<com.amazon.crypto.asymmetric.AsymmetricKeys> r3 = r7.keyManager
            r4 = 0
            r5 = 1
            if (r3 != 0) goto Le
            r3 = r5
            goto Lf
        Le:
            r3 = r4
        Lf:
            java.lang.String r6 = "CRYPTO_NOT_AVAILABLE_PERCENT"
            r7.recordPercentOccurrence(r6, r3)
            com.amazon.crypto.KeyManager<com.amazon.crypto.asymmetric.AsymmetricKeys> r3 = r7.keyManager
            r6 = 0
            if (r3 != 0) goto L1a
            return r6
        L1a:
            boolean r3 = r3.contains(r8)     // Catch: java.lang.RuntimeException -> L2e
            if (r3 == 0) goto L2c
            com.amazon.crypto.KeyManager<com.amazon.crypto.asymmetric.AsymmetricKeys> r3 = r7.keyManager     // Catch: java.lang.RuntimeException -> L2e
            java.lang.Object r3 = r3.mo3299retrieve(r8)     // Catch: java.lang.RuntimeException -> L2e
            com.amazon.crypto.asymmetric.AsymmetricKeys r3 = (com.amazon.crypto.asymmetric.AsymmetricKeys) r3     // Catch: java.lang.RuntimeException -> L2e
            r7.recordPercentOccurrence(r0, r4)     // Catch: java.lang.RuntimeException -> L2f
            goto L32
        L2c:
            r3 = r6
            goto L32
        L2e:
            r3 = r6
        L2f:
            r7.recordPercentOccurrence(r0, r5)
        L32:
            if (r3 == 0) goto L35
            return r3
        L35:
            com.amazon.crypto.KeyManager<com.amazon.crypto.asymmetric.AsymmetricKeys> r0 = r7.keyManager     // Catch: java.lang.Throwable -> L59 java.lang.RuntimeException -> L5b
            java.lang.Object r8 = r0.mo3298generate(r8)     // Catch: java.lang.Throwable -> L59 java.lang.RuntimeException -> L5b
            com.amazon.crypto.asymmetric.AsymmetricKeys r8 = (com.amazon.crypto.asymmetric.AsymmetricKeys) r8     // Catch: java.lang.Throwable -> L59 java.lang.RuntimeException -> L5b
            com.amazon.alexa.protocols.storage.PersistentStorage r0 = r7.cryptoSettings
            boolean r0 = r0.getBoolean(r2, r4)
            if (r0 != 0) goto L7c
            if (r8 != 0) goto L48
            r4 = r5
        L48:
            r7.recordPercentOccurrence(r1, r4)
            com.amazon.alexa.protocols.storage.PersistentStorage r0 = r7.cryptoSettings
            com.amazon.alexa.protocols.storage.PersistentStorage$Transaction r0 = r0.edit()
            com.amazon.alexa.protocols.storage.PersistentStorage$Transaction r0 = r0.set(r2, r5)
            r0.commit()
            goto L7c
        L59:
            r8 = move-exception
            goto L7f
        L5b:
            r8 = move-exception
            java.lang.String r0 = "DefaultMessageCrypto"
            java.lang.String r3 = "Messaging key generation failed."
            android.util.Log.w(r0, r3, r8)     // Catch: java.lang.Throwable -> L7d
            com.amazon.alexa.protocols.storage.PersistentStorage r8 = r7.cryptoSettings
            boolean r8 = r8.getBoolean(r2, r4)
            if (r8 != 0) goto L7b
            r7.recordPercentOccurrence(r1, r5)
            com.amazon.alexa.protocols.storage.PersistentStorage r8 = r7.cryptoSettings
            com.amazon.alexa.protocols.storage.PersistentStorage$Transaction r8 = r8.edit()
            com.amazon.alexa.protocols.storage.PersistentStorage$Transaction r8 = r8.set(r2, r5)
            r8.commit()
        L7b:
            r8 = r6
        L7c:
            return r8
        L7d:
            r8 = move-exception
            r3 = r6
        L7f:
            com.amazon.alexa.protocols.storage.PersistentStorage r0 = r7.cryptoSettings
            boolean r0 = r0.getBoolean(r2, r4)
            if (r0 != 0) goto L9a
            if (r3 != 0) goto L8a
            r4 = r5
        L8a:
            r7.recordPercentOccurrence(r1, r4)
            com.amazon.alexa.protocols.storage.PersistentStorage r0 = r7.cryptoSettings
            com.amazon.alexa.protocols.storage.PersistentStorage$Transaction r0 = r0.edit()
            com.amazon.alexa.protocols.storage.PersistentStorage$Transaction r0 = r0.set(r2, r5)
            r0.commit()
        L9a:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.dee.app.services.messaging.DefaultMessageCrypto.managedKeys(java.lang.String):com.amazon.crypto.asymmetric.AsymmetricKeys");
    }

    private void recordEvent(@NonNull String str) {
        MobilyticsOperationalEvent createOperationalEvent = this.mobilytics.mo358get().createOperationalEvent(str, OperationalEventType.DIAGNOSTIC, "PushNotifications", AlexaMetricsConstants.MetricsComponents.NOTIFICATIONS_CRYPTO_HANDLER_SUBCOMPONENT, "1235005e-4e8f-4ef2-82bc-34157415015b");
        if (createOperationalEvent != null) {
            createOperationalEvent.setChannelName("coreapp");
            this.mobilytics.mo358get().recordOperationalEvent(createOperationalEvent);
        }
    }

    private void recordPercentOccurrence(@NonNull String str, boolean z) {
        this.mobilytics.mo358get().recordPercentOccurrence(str, z, "PushNotifications", AlexaMetricsConstants.MetricsComponents.NOTIFICATIONS_CRYPTO_HANDLER_SUBCOMPONENT, "1235005e-4e8f-4ef2-82bc-34157415015b");
    }

    @Override // com.amazon.dee.app.services.messaging.MessageCrypto
    @Nullable
    public String decrypt(@Nullable String str, @NonNull String str2) {
        recordEvent(AlexaMetricsConstants.MetricEvents.CRYPTO_DECRYPT_MESSAGE_EVENT);
        AsymmetricKeys managedKeys = managedKeys(MESSAGE_CRYPTO_KEY_PAIR_ALIAS);
        if (managedKeys == null) {
            return null;
        }
        try {
            String str3 = new String(this.cryptoFactory.createSymmetricDecryptor(SymmetricKey.from(this.cryptoFactory.createAsymmetricDecryptor(managedKeys.withheldKey()).decrypt(Base64.decode(str2.getBytes(StandardCharsets.UTF_8))))).decrypt(Base64.decode(str.getBytes(StandardCharsets.UTF_8))), StandardCharsets.UTF_8);
            recordPercentOccurrence(AlexaMetricsConstants.MetricEvents.CRYPTO_DECRYPT_MESSAGE_ERROR_PERCENT, false);
            return str3;
        } catch (RuntimeException unused) {
            recordPercentOccurrence(AlexaMetricsConstants.MetricEvents.CRYPTO_DECRYPT_MESSAGE_ERROR_PERCENT, true);
            return null;
        }
    }

    @Override // com.amazon.dee.app.services.messaging.MessageCrypto
    public void expireKeyPair() {
        recordEvent(AlexaMetricsConstants.MetricEvents.CRYPTO_EXPIRE_KEY_PAIR_EVENT);
        this.cryptoSettings.edit().set(HAS_ATTEMPTED_KEY_GEN, false).commit();
        recordPercentOccurrence(AlexaMetricsConstants.MetricEvents.CRYPTO_NOT_AVAILABLE_PERCENT, this.keyManager == null);
        KeyManager<AsymmetricKeys> keyManager = this.keyManager;
        if (keyManager == null) {
            return;
        }
        try {
            if (!keyManager.contains(MESSAGE_CRYPTO_KEY_PAIR_ALIAS)) {
                return;
            }
            this.keyManager.remove(MESSAGE_CRYPTO_KEY_PAIR_ALIAS);
            recordPercentOccurrence(AlexaMetricsConstants.MetricEvents.CRYPTO_EXPIRE_KEY_PAIR_ERROR_PERCENT, false);
        } catch (RuntimeException unused) {
            recordPercentOccurrence(AlexaMetricsConstants.MetricEvents.CRYPTO_EXPIRE_KEY_PAIR_ERROR_PERCENT, true);
        }
    }

    @Override // com.amazon.dee.app.services.messaging.MessageCrypto
    @Nullable
    public String getPublicKey() {
        recordEvent(AlexaMetricsConstants.MetricEvents.CRYPTO_GET_PUBLIC_KEY_EVENT);
        AsymmetricKeys managedKeys = managedKeys(MESSAGE_CRYPTO_KEY_PAIR_ALIAS);
        if (managedKeys == null) {
            return null;
        }
        return new String(Base64.encode(managedKeys.publishedKey().toBytes()), StandardCharsets.UTF_8);
    }

    @VisibleForTesting
    DefaultMessageCrypto(@NonNull Lazy<Mobilytics> lazy, @NonNull PersistentStorage persistentStorage, @NonNull KeyManager<AsymmetricKeys> keyManager, @NonNull CryptoFactory cryptoFactory) {
        this.mobilytics = lazy;
        this.cryptoSettings = persistentStorage;
        this.keyManager = keyManager;
        this.cryptoFactory = cryptoFactory;
    }
}
