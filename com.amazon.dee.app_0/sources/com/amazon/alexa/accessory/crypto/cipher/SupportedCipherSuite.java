package com.amazon.alexa.accessory.crypto.cipher;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazonaws.services.s3.internal.crypto.JceEncryptionConstants;
/* loaded from: classes.dex */
public enum SupportedCipherSuite {
    SYMMETRIC_WITH_AES_256_GCM_SHA256("SYMMETRIC_WITH_AES_256_GCM_SHA256", "Symmetric", "HmacSHA256", JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM, 32, AlgorithmParameterSpecBuilder.GCM_PARAMETER_BUILDER, "HmacSHA256", 32),
    SYMMETRIC_WITH_AES_256_CBC_PKCS7PADDING_SHA256("SYMMETRIC_WITH_AES_256_CBC_PKCS7PADDING_SHA256", "Symmetric", "HmacSHA256", JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM, 32, AlgorithmParameterSpecBuilder.IV_PARAMETER_BUILDER, "HmacSHA256", 32),
    SYMMETRIC_WITH_AES_128_GCM_SHA256("SYMMETRIC_WITH_AES_128_GCM_SHA256", "Symmetric", "HmacSHA256", JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM, 16, AlgorithmParameterSpecBuilder.GCM_PARAMETER_BUILDER, "HmacSHA256", 32),
    SYMMETRIC_WITH_AES_128_CBC_PKCS7PADDING_SHA256("SYMMETRIC_WITH_AES_128_CBC_PKCS7PADDING_SHA256", "Symmetric", "HmacSHA256", JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM, 16, AlgorithmParameterSpecBuilder.IV_PARAMETER_BUILDER, "HmacSHA256", 32);
    
    public final AlgorithmParameterSpecBuilder algorithmParameterSpecBuilder;
    public final String descriptor;
    public final String encryptionAlgorithm;
    public final int encryptionKeyStrengthInBytes;
    public final String keyExchangeAlgorithm;
    public final String keyExchangeSignatureAlgorithm;
    public final String macAlgorithm;
    public final int macKeyStrengthInBytes;

    SupportedCipherSuite(String str, String str2, String str3, String str4, int i, AlgorithmParameterSpecBuilder algorithmParameterSpecBuilder, String str5, int i2) {
        Preconditions.notNull(str, "descriptor");
        Preconditions.notNull(str2, "keyExchangeAlgorithm");
        Preconditions.notNull(str3, "keyExchangeSignatureAlgorithm");
        Preconditions.notNull(str4, "encryptionAlgorithm");
        Preconditions.notNegative(i, "encryptionKeyStrengthInBytes");
        Preconditions.notNull(algorithmParameterSpecBuilder, "algorithmParameterSpecBuilder");
        Preconditions.notNull(str5, "macAlgorithm");
        Preconditions.notNegative(i2, "macKeyStrengthInBytes");
        this.descriptor = str;
        this.keyExchangeAlgorithm = str2;
        this.keyExchangeSignatureAlgorithm = str3;
        this.encryptionAlgorithm = str4;
        this.encryptionKeyStrengthInBytes = i;
        this.algorithmParameterSpecBuilder = algorithmParameterSpecBuilder;
        this.macAlgorithm = str5;
        this.macKeyStrengthInBytes = i2;
    }
}
