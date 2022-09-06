package com.amazon.crypto.asymmetric;

import com.amazonaws.services.s3.internal.crypto.S3KeyWrapScheme;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.MGF1ParameterSpec;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
/* loaded from: classes12.dex */
public enum AsymmetricCryptoParameters {
    SHA1(Constants.SHA1_PARAMETER_SPEC, S3KeyWrapScheme.RSA_ECB_OAEP_WITH_SHA256_AND_MGF1_PADDING),
    SHA256(Constants.SHA256_PARAMETER_SPEC, S3KeyWrapScheme.RSA_ECB_OAEP_WITH_SHA256_AND_MGF1_PADDING);
    
    private final AlgorithmParameterSpec parameterSpec;
    private final String transformation;

    /* loaded from: classes12.dex */
    private static final class Constants {
        private static final String DEFAULT_TRANSFORMATION = "RSA/ECB/OAEPWithSHA-256AndMGF1Padding";
        private static final AlgorithmParameterSpec SHA1_PARAMETER_SPEC = new OAEPParameterSpec("SHA-1", "MGF1", MGF1ParameterSpec.SHA1, PSource.PSpecified.DEFAULT);
        private static final AlgorithmParameterSpec SHA256_PARAMETER_SPEC = new OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, PSource.PSpecified.DEFAULT);

        private Constants() {
        }
    }

    AsymmetricCryptoParameters(AlgorithmParameterSpec algorithmParameterSpec, String str) {
        this.parameterSpec = algorithmParameterSpec;
        this.transformation = str;
    }

    public AlgorithmParameterSpec getParameterSpec() {
        return this.parameterSpec;
    }

    public String getTransformation() {
        return this.transformation;
    }
}
