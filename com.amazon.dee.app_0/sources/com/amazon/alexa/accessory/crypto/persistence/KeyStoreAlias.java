package com.amazon.alexa.accessory.crypto.persistence;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.google.common.base.Joiner;
/* loaded from: classes.dex */
final class KeyStoreAlias {
    private static final String SEPARATOR = "_";
    final String alias;

    /* loaded from: classes.dex */
    enum KeyType {
        ROOT_WRAPPER("root-wrapper"),
        AUTHENTICATION_WRAPPER("authentication-wrapper"),
        DECRYPTION_WRAPPER("decryption-wrapper"),
        WRAPPER("wrapper"),
        AUTHENTICATION("authentication"),
        DECRYPTION("decryption");
        
        private final String aliasForm;

        KeyType(String str) {
            this.aliasForm = str;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public KeyStoreAlias(String str) {
        Preconditions.notEmpty(str, "rawValue");
        this.alias = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static KeyStoreAlias composeFrom(String str, KeyType keyType, long j) {
        Preconditions.notNull(str, "accessoryId");
        Preconditions.notNull(keyType, "keyType");
        Preconditions.precondition(j > 0, "version");
        return new KeyStoreAlias(Joiner.on("_").join(str, keyType.aliasForm, Long.valueOf(j)));
    }
}
