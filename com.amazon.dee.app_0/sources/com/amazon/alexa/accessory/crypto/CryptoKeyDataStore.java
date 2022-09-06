package com.amazon.alexa.accessory.crypto;

import com.amazon.alexa.accessory.crypto.cipher.Nonce;
import com.amazon.alexa.accessory.crypto.cipher.SupportedCipherSuite;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableSet;
import java.security.Key;
import java.util.List;
/* loaded from: classes.dex */
public interface CryptoKeyDataStore {

    /* loaded from: classes.dex */
    public static class CryptoKeyDataStoreIOException extends CryptoKeyDataStoreException {
        public CryptoKeyDataStoreIOException(String str, String str2) {
            super(str, str2);
        }

        public CryptoKeyDataStoreIOException(String str, String str2, Throwable th) {
            super(str, str2, th);
        }
    }

    /* loaded from: classes.dex */
    public static class InvalidStoreStateException extends CryptoKeyDataStoreException {
        public InvalidStoreStateException(String str, String str2) {
            super(str, str2);
        }

        public InvalidStoreStateException(String str, String str2, Throwable th) {
            super(str, str2, th);
        }
    }

    /* loaded from: classes.dex */
    public static class KeysAlreadyExistException extends CryptoKeyDataStoreException {
        public KeysAlreadyExistException(String str, String str2) {
            super(str, str2);
        }
    }

    /* loaded from: classes.dex */
    public static final class ListResult {
        public final List<String> identifiers;
        public final PaginationToken paginationToken;

        public ListResult(List<String> list, PaginationToken paginationToken) {
            Preconditions.notNull(list, "identifiers");
            Preconditions.notNull(paginationToken, "paginationToken");
            this.identifiers = list;
            this.paginationToken = paginationToken;
        }
    }

    /* loaded from: classes.dex */
    public static final class NegotiatedData {
        public final Key authenticationKey;
        public final SupportedCipherSuite cipherSuite;
        public final String cipherTransform;
        public final Key encryptionKey;
        public final int keyExchangeProtocolVersion;
        public final long keyExchangeTimestampMillis;
        public final long lastKeyRotationTimestampMillis;
        public final Key rootKey;

        public NegotiatedData(int i, SupportedCipherSuite supportedCipherSuite, String str, Key key, Key key2, Key key3, long j, long j2) {
            boolean z = false;
            Preconditions.precondition(i > 0, "keyExchangeProtocolVersion");
            Preconditions.notNull(supportedCipherSuite, "cipherSuite");
            Preconditions.notNull(key, "rootKey");
            Preconditions.notNull(key2, "encryptionKey");
            Preconditions.notNull(key3, "authenticationKey");
            Preconditions.precondition(j > 0, "keyExchangeTimestampMillis");
            Preconditions.precondition(j2 >= 0 ? true : z, "lastKeyRotationTimestampMillis");
            Preconditions.precondition(!Strings.isNullOrEmpty(str), "cipherTransform");
            this.keyExchangeProtocolVersion = i;
            this.cipherSuite = supportedCipherSuite;
            this.cipherTransform = str;
            this.rootKey = key;
            this.encryptionKey = key2;
            this.authenticationKey = key3;
            this.keyExchangeTimestampMillis = j;
            this.lastKeyRotationTimestampMillis = j2;
        }
    }

    /* loaded from: classes.dex */
    public static final class PaginationToken {
        public static final PaginationToken EMPTY = new PaginationToken("");
        public final String value;

        public PaginationToken(String str) {
            Preconditions.notNull(str, "value");
            this.value = str;
        }
    }

    /* loaded from: classes.dex */
    public static class UnrecognizedAccessoryException extends CryptoKeyDataStoreException {
        public UnrecognizedAccessoryException(String str, String str2) {
            super(str, str2);
        }
    }

    NegotiatedData generateKeys(String str, Nonce nonce, Nonce nonce2, SupportedCipherSuite supportedCipherSuite, int i) throws KeysAlreadyExistException, InvalidInputsException;

    NegotiatedData getData(String str);

    ListResult listNegotiatedAccessories(int i, PaginationToken paginationToken) throws InvalidInputsException;

    void removeData(Iterable<String> iterable);

    void removeData(String str);

    NegotiatedData rotateDerivedKeys(String str, Nonce nonce, Nonce nonce2) throws UnrecognizedAccessoryException, InvalidInputsException;

    /* loaded from: classes.dex */
    public static abstract class CryptoKeyDataStoreException extends RuntimeException {
        private final String accessoryId;

        protected CryptoKeyDataStoreException(String str, String str2) {
            super(str2);
            this.accessoryId = str;
        }

        public String getAccessoryId() {
            return this.accessoryId;
        }

        protected CryptoKeyDataStoreException(String str, String str2, Throwable th) {
            this(str, str2);
            initCause(th);
        }
    }

    /* loaded from: classes.dex */
    public static class InvalidInputsException extends CryptoKeyDataStoreException {
        private final Iterable<String> invalidFieldNames;

        /* loaded from: classes.dex */
        public static class Accumulator {
            private final String accessoryId;
            private final ImmutableSet.Builder<String> fieldNameBuilder = ImmutableSet.builder();

            protected Accumulator(String str) {
                this.accessoryId = str;
            }

            public Accumulator accumulateField(boolean z, String str) {
                if (!z) {
                    this.fieldNameBuilder.mo7849add((ImmutableSet.Builder<String>) str);
                    Logger.e("CryptoKeyDataStore.InvalidInputsException - Invalid Input Field: %s", str);
                }
                return this;
            }

            public void throwIfInvalid(String str) throws InvalidInputsException {
                ImmutableSet<String> mo7852build = this.fieldNameBuilder.mo7852build();
                if (!mo7852build.isEmpty()) {
                    StringBuilder outline113 = GeneratedOutlineSupport1.outline113(Strings.isNullOrEmpty(str) ? "" : GeneratedOutlineSupport1.outline72(str, "; "), "Field Names: ");
                    outline113.append(Joiner.on(",").join(mo7852build));
                    throw new InvalidInputsException(this.accessoryId, mo7852build, outline113.toString());
                }
            }
        }

        public InvalidInputsException(String str, Iterable<String> iterable, String str2) {
            super(str, str2);
            this.invalidFieldNames = ImmutableSet.copyOf(iterable);
        }

        public static Accumulator accumulator(String str) {
            return new Accumulator(str);
        }

        public Iterable<String> getInvalidFieldNames() {
            return this.invalidFieldNames;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public InvalidInputsException(String str, Iterable<String> iterable, String str2, Throwable th) {
            this(str, iterable, str2);
            initCause(th);
        }
    }
}
