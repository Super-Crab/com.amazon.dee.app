package com.amazon.tarazed.core.sessionclient.sessioncache;

import android.annotation.TargetApi;
import android.content.Context;
import android.security.keystore.KeyGenParameterSpec;
import android.util.Base64;
import androidx.annotation.VisibleForTesting;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.sessionclient.model.createcredentials.CreateCredentialsResponse;
import com.amazonaws.services.s3.internal.crypto.JceEncryptionConstants;
import com.google.android.gms.stats.CodePackage;
import java.nio.charset.Charset;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.json.Json;
import org.jetbrains.annotations.NotNull;
/* compiled from: Post23SessionCredentialsCipher.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u001b2\u00020\u0001:\u0002\u001a\u001bB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\rH\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0003J\r\u0010\u0017\u001a\u00020\u0018H\u0001¢\u0006\u0002\b\u0019R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u00060\bR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/amazon/tarazed/core/sessionclient/sessioncache/Post23SessionCredentialsCipher;", "Lcom/amazon/tarazed/core/sessionclient/sessioncache/SessionCredentialsCipher;", "context", "Landroid/content/Context;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "(Landroid/content/Context;Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;)V", "converter", "Lcom/amazon/tarazed/core/sessionclient/sessioncache/Post23SessionCredentialsCipher$ByteArrayStringConverter;", "keyStore", "Ljava/security/KeyStore;", "kotlin.jvm.PlatformType", "decryptCredentials", "Lcom/amazon/tarazed/core/sessionclient/model/createcredentials/CreateCredentialsResponse;", "cachedCredentials", "Lcom/amazon/tarazed/core/sessionclient/sessioncache/CachedSessionCredentials;", "deleteKeyIfExists", "", "encryptCredentials", "Lcom/amazon/tarazed/core/sessionclient/sessioncache/EncryptedCredentialsData;", "credentials", "getKey", "Ljavax/crypto/SecretKey;", "getKeyGenParameterSpec", "Landroid/security/keystore/KeyGenParameterSpec;", "getKeyGenParameterSpec$TarazedMobileCore_release", "ByteArrayStringConverter", "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class Post23SessionCredentialsCipher implements SessionCredentialsCipher {
    private static final String AES_ENCRYPTION_TRANSFORMATION = "AES/GCM/NoPadding";
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final int IV_LENGTH = 12;
    private static final String KEY_ALIAS = "SessionCredentialsKey";
    private static final String KEY_STORE = "AndroidKeyStore";
    private static final String NULL = "NULL";
    private static final String TAG = "SessionCredsCipher";
    private static final int TAG_LENGTH = 128;
    private final Context context;
    private final ByteArrayStringConverter converter;
    private final KeyStore keyStore;
    private final TarazedSessionLogger logger;

    /* compiled from: Post23SessionCredentialsCipher.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0004¨\u0006\t"}, d2 = {"Lcom/amazon/tarazed/core/sessionclient/sessioncache/Post23SessionCredentialsCipher$ByteArrayStringConverter;", "", "(Lcom/amazon/tarazed/core/sessionclient/sessioncache/Post23SessionCredentialsCipher;)V", "toBytes", "", "str", "", "toString", "bytes", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public final class ByteArrayStringConverter {
        public ByteArrayStringConverter() {
        }

        @NotNull
        public final byte[] toBytes(@NotNull String str) {
            Intrinsics.checkParameterIsNotNull(str, "str");
            byte[] decode = Base64.decode(str, 0);
            Intrinsics.checkExpressionValueIsNotNull(decode, "Base64.decode(str, Base64.DEFAULT)");
            return decode;
        }

        @NotNull
        public final String toString(@NotNull byte[] bytes) {
            Intrinsics.checkParameterIsNotNull(bytes, "bytes");
            String encodeToString = Base64.encodeToString(bytes, 0);
            Intrinsics.checkExpressionValueIsNotNull(encodeToString, "Base64.encodeToString(bytes, Base64.DEFAULT)");
            return encodeToString;
        }
    }

    /* compiled from: Post23SessionCredentialsCipher.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/tarazed/core/sessionclient/sessioncache/Post23SessionCredentialsCipher$Companion;", "", "()V", "AES_ENCRYPTION_TRANSFORMATION", "", "IV_LENGTH", "", "KEY_ALIAS", "KEY_STORE", "NULL", "TAG", "TAG_LENGTH", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public Post23SessionCredentialsCipher(@NotNull Context context, @NotNull TarazedSessionLogger logger) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        this.context = context;
        this.logger = logger;
        this.converter = new ByteArrayStringConverter();
        KeyStore keyStore = KeyStore.getInstance(KEY_STORE);
        keyStore.load(null);
        this.keyStore = keyStore;
    }

    @TargetApi(23)
    private final SecretKey getKey() {
        this.logger.i(TAG, "Creating Session credentials key.");
        if (this.keyStore.containsAlias(KEY_ALIAS)) {
            Key key = this.keyStore.getKey(KEY_ALIAS, null);
            if (key == null) {
                throw new TypeCastException("null cannot be cast to non-null type javax.crypto.SecretKey");
            }
            return (SecretKey) key;
        }
        KeyGenParameterSpec keyGenParameterSpec$TarazedMobileCore_release = getKeyGenParameterSpec$TarazedMobileCore_release();
        KeyGenerator keyGenerator = KeyGenerator.getInstance(JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM, KEY_STORE);
        keyGenerator.init(keyGenParameterSpec$TarazedMobileCore_release);
        this.logger.i(TAG, "Created Session credentials key.");
        SecretKey generateKey = keyGenerator.generateKey();
        Intrinsics.checkExpressionValueIsNotNull(generateKey, "keyGenerator.generateKey()");
        return generateKey;
    }

    @Override // com.amazon.tarazed.core.sessionclient.sessioncache.SessionCredentialsCipher
    @NotNull
    public CreateCredentialsResponse decryptCredentials(@NotNull CachedSessionCredentials cachedCredentials) {
        Intrinsics.checkParameterIsNotNull(cachedCredentials, "cachedCredentials");
        this.logger.i(TAG, "Decrypting credentials.");
        SecretKey key = getKey();
        byte[] bytes = this.converter.toBytes(cachedCredentials.getIv());
        Cipher cipher = Cipher.getInstance(AES_ENCRYPTION_TRANSFORMATION);
        cipher.init(2, key, new GCMParameterSpec(128, bytes));
        byte[] decryptedCredentials = cipher.doFinal(this.converter.toBytes(cachedCredentials.getEncryptedCredentials()));
        Json nonstrict = Json.Default.getNonstrict();
        KSerializer<CreateCredentialsResponse> serializer = CreateCredentialsResponse.Companion.serializer();
        Intrinsics.checkExpressionValueIsNotNull(decryptedCredentials, "decryptedCredentials");
        CreateCredentialsResponse createCredentialsResponse = (CreateCredentialsResponse) nonstrict.parse(serializer, new String(decryptedCredentials, Charsets.UTF_8));
        this.logger.i(TAG, "Decrypted credentials.");
        return createCredentialsResponse;
    }

    @Override // com.amazon.tarazed.core.sessionclient.sessioncache.SessionCredentialsCipher
    public void deleteKeyIfExists() {
        try {
            if (!this.keyStore.containsAlias(KEY_ALIAS)) {
                return;
            }
            this.keyStore.deleteEntry(KEY_ALIAS);
            this.logger.i(TAG, "Session credentials key deleted.");
        } catch (KeyStoreException e) {
            this.logger.w(TAG, "SessionCredentialsKey does not exist.", e);
        }
    }

    @Override // com.amazon.tarazed.core.sessionclient.sessioncache.SessionCredentialsCipher
    @NotNull
    public EncryptedCredentialsData encryptCredentials(@NotNull CreateCredentialsResponse credentials) {
        Intrinsics.checkParameterIsNotNull(credentials, "credentials");
        this.logger.i(TAG, "Encrypting credentials.");
        SecretKey key = getKey();
        byte[] bArr = new byte[12];
        new SecureRandom().nextBytes(bArr);
        Cipher cipher = Cipher.getInstance(AES_ENCRYPTION_TRANSFORMATION);
        cipher.init(1, key, new GCMParameterSpec(128, bArr));
        String stringify = Json.Default.stringify(CreateCredentialsResponse.Companion.serializer(), credentials);
        Charset charset = Charsets.UTF_8;
        if (stringify != null) {
            byte[] bytes = stringify.getBytes(charset);
            Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
            byte[] encryptedCredentials = cipher.doFinal(bytes);
            String byteArrayStringConverter = this.converter.toString(bArr);
            ByteArrayStringConverter byteArrayStringConverter2 = this.converter;
            Intrinsics.checkExpressionValueIsNotNull(encryptedCredentials, "encryptedCredentials");
            EncryptedCredentialsData encryptedCredentialsData = new EncryptedCredentialsData("NULL", byteArrayStringConverter, byteArrayStringConverter2.toString(encryptedCredentials));
            this.logger.i(TAG, "Encrypted credentials.");
            return encryptedCredentialsData;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    @TargetApi(23)
    @VisibleForTesting
    @NotNull
    public final KeyGenParameterSpec getKeyGenParameterSpec$TarazedMobileCore_release() {
        KeyGenParameterSpec build = new KeyGenParameterSpec.Builder(KEY_ALIAS, 3).setBlockModes(CodePackage.GCM).setEncryptionPaddings("NoPadding").setRandomizedEncryptionRequired(false).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "KeyGenParameterSpec\n    …lse)\n            .build()");
        return build;
    }
}
