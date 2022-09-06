package org.bouncycastle.jcajce.provider.symmetric;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import javax.crypto.spec.PBEParameterSpec;
import org.bouncycastle.asn1.pkcs.PBEParameter;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameters;
import org.bouncycastle.jcajce.provider.util.AlgorithmProvider;
/* loaded from: classes4.dex */
public class PBEPBKDF1 {

    /* loaded from: classes4.dex */
    public static class AlgParams extends BaseAlgorithmParameters {
        PBEParameter params;

        @Override // java.security.AlgorithmParametersSpi
        protected byte[] engineGetEncoded() {
            try {
                return this.params.getEncoded("DER");
            } catch (IOException e) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Oooops! ");
                outline107.append(e.toString());
                throw new RuntimeException(outline107.toString());
            }
        }

        @Override // java.security.AlgorithmParametersSpi
        protected byte[] engineGetEncoded(String str) {
            if (isASN1FormatString(str)) {
                return engineGetEncoded();
            }
            return null;
        }

        @Override // java.security.AlgorithmParametersSpi
        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec) throws InvalidParameterSpecException {
            if (algorithmParameterSpec instanceof PBEParameterSpec) {
                PBEParameterSpec pBEParameterSpec = (PBEParameterSpec) algorithmParameterSpec;
                this.params = new PBEParameter(pBEParameterSpec.getSalt(), pBEParameterSpec.getIterationCount());
                return;
            }
            throw new InvalidParameterSpecException("PBEParameterSpec required to initialise a PBKDF1 PBE parameters algorithm parameters object");
        }

        @Override // java.security.AlgorithmParametersSpi
        protected void engineInit(byte[] bArr) throws IOException {
            this.params = PBEParameter.getInstance(bArr);
        }

        @Override // java.security.AlgorithmParametersSpi
        protected void engineInit(byte[] bArr, String str) throws IOException {
            if (isASN1FormatString(str)) {
                engineInit(bArr);
                return;
            }
            throw new IOException("Unknown parameters format in PBKDF2 parameters object");
        }

        @Override // java.security.AlgorithmParametersSpi
        protected String engineToString() {
            return "PBKDF1 Parameters";
        }

        @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameters
        protected AlgorithmParameterSpec localEngineGetParameterSpec(Class cls) throws InvalidParameterSpecException {
            if (cls == PBEParameterSpec.class || cls == AlgorithmParameterSpec.class) {
                return new PBEParameterSpec(this.params.getSalt(), this.params.getIterationCount().intValue());
            }
            throw new InvalidParameterSpecException("unknown parameter spec passed to PBKDF1 PBE parameters object.");
        }
    }

    /* loaded from: classes4.dex */
    public static class Mappings extends AlgorithmProvider {
        private static final String PREFIX = PBEPBKDF1.class.getName();

        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            StringBuilder outline117 = GeneratedOutlineSupport1.outline117(new StringBuilder(), PREFIX, "$AlgParams", configurableProvider, "AlgorithmParameters.PBKDF1");
            outline117.append("Alg.Alias.AlgorithmParameters.");
            GeneratedOutlineSupport1.outline183(GeneratedOutlineSupport1.outline119(GeneratedOutlineSupport1.outline119(GeneratedOutlineSupport1.outline119(GeneratedOutlineSupport1.outline119(outline117, PKCSObjectIdentifiers.pbeWithMD2AndDES_CBC, configurableProvider, "PBKDF1", "Alg.Alias.AlgorithmParameters."), PKCSObjectIdentifiers.pbeWithMD5AndDES_CBC, configurableProvider, "PBKDF1", "Alg.Alias.AlgorithmParameters."), PKCSObjectIdentifiers.pbeWithMD5AndRC2_CBC, configurableProvider, "PBKDF1", "Alg.Alias.AlgorithmParameters."), PKCSObjectIdentifiers.pbeWithSHA1AndDES_CBC, configurableProvider, "PBKDF1", "Alg.Alias.AlgorithmParameters."), PKCSObjectIdentifiers.pbeWithSHA1AndRC2_CBC, configurableProvider, "PBKDF1");
        }
    }

    private PBEPBKDF1() {
    }
}
