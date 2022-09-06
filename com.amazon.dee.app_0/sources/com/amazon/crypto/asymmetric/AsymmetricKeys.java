package com.amazon.crypto.asymmetric;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.crypto.utils.Preconditions;
import com.amazon.whispercloak.KeyUtils;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.Objects;
/* loaded from: classes12.dex */
public final class AsymmetricKeys {
    private final PublishedKey publishedKey;
    private final WithheldKey withheldKey;

    private AsymmetricKeys(PublishedKey publishedKey, WithheldKey withheldKey) {
        Preconditions.notNull(publishedKey);
        Preconditions.notNull(withheldKey);
        this.publishedKey = publishedKey;
        this.withheldKey = withheldKey;
    }

    @NonNull
    public static AsymmetricKeys from(@NonNull PublishedKey publishedKey, @NonNull WithheldKey withheldKey) {
        Preconditions.notNull(publishedKey, withheldKey);
        return new AsymmetricKeys(publishedKey, withheldKey);
    }

    @NonNull
    public static AsymmetricKeys generate() {
        return generate(2048);
    }

    public boolean equals(Object obj) {
        if (obj == null || !obj.getClass().equals(AsymmetricKeys.class)) {
            return false;
        }
        AsymmetricKeys asymmetricKeys = (AsymmetricKeys) obj;
        return publishedKey().equals(asymmetricKeys.publishedKey()) && withheldKey().equals(asymmetricKeys.withheldKey());
    }

    public int hashCode() {
        return Objects.hash(this.publishedKey, this.withheldKey);
    }

    @NonNull
    public PublishedKey publishedKey() {
        return this.publishedKey;
    }

    @NonNull
    public WithheldKey withheldKey() {
        return this.withheldKey;
    }

    @NonNull
    @VisibleForTesting
    static AsymmetricKeys generate(int i) {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KeyUtils.ALGORITHM_RSA);
            keyPairGenerator.initialize(i);
            KeyPair genKeyPair = keyPairGenerator.genKeyPair();
            return new AsymmetricKeys(PublishedKey.from(genKeyPair.getPublic()), WithheldKey.from(genKeyPair.getPrivate()));
        } catch (GeneralSecurityException e) {
            throw new SecurityException(e);
        }
    }
}
