package com.amazonaws.services.s3.internal.crypto;

import com.amazonaws.services.s3.model.CryptoMode;
import java.security.SecureRandom;
@Deprecated
/* loaded from: classes13.dex */
final class S3CryptoScheme {
    static final String AES = "AES";
    static final String RSA = "RSA";
    private static final SecureRandom SRAND = new SecureRandom();
    private final ContentCryptoScheme contentCryptoScheme;
    private final S3KeyWrapScheme kwScheme;

    /* renamed from: com.amazonaws.services.s3.internal.crypto.S3CryptoScheme$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazonaws$services$s3$model$CryptoMode = new int[CryptoMode.values().length];

        static {
            try {
                $SwitchMap$com$amazonaws$services$s3$model$CryptoMode[CryptoMode.EncryptionOnly.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazonaws$services$s3$model$CryptoMode[CryptoMode.AuthenticatedEncryption.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazonaws$services$s3$model$CryptoMode[CryptoMode.StrictAuthenticatedEncryption.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    S3CryptoScheme(ContentCryptoScheme contentCryptoScheme) {
        this.contentCryptoScheme = contentCryptoScheme;
        this.kwScheme = new S3KeyWrapScheme();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static S3CryptoScheme from(CryptoMode cryptoMode) {
        int ordinal = cryptoMode.ordinal();
        if (ordinal != 0) {
            if (ordinal != 1 && ordinal != 2) {
                throw new IllegalStateException();
            }
            return new S3CryptoScheme(ContentCryptoScheme.AES_GCM, new S3KeyWrapScheme());
        }
        return new S3CryptoScheme(ContentCryptoScheme.AES_CBC, S3KeyWrapScheme.NONE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isAesGcm(String str) {
        return ContentCryptoScheme.AES_GCM.getCipherAlgorithm().equals(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ContentCryptoScheme getContentCryptoScheme() {
        return this.contentCryptoScheme;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public S3KeyWrapScheme getKeyWrapScheme() {
        return this.kwScheme;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SecureRandom getSecureRandom() {
        return SRAND;
    }

    private S3CryptoScheme(ContentCryptoScheme contentCryptoScheme, S3KeyWrapScheme s3KeyWrapScheme) {
        this.contentCryptoScheme = contentCryptoScheme;
        this.kwScheme = s3KeyWrapScheme;
    }
}
