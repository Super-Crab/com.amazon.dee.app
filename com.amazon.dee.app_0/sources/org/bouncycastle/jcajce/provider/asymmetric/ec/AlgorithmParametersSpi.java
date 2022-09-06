package org.bouncycastle.jcajce.provider.asymmetric.ec;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.math.BigInteger;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import org.bouncycastle.asn1.ASN1Null;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.x9.ECNamedCurveTable;
import org.bouncycastle.asn1.x9.X962Parameters;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.asn1.x9.X9ECPoint;
import org.bouncycastle.jcajce.provider.asymmetric.util.EC5Util;
import org.bouncycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveSpec;
import org.bouncycastle.math.ec.ECCurve;
/* loaded from: classes4.dex */
public class AlgorithmParametersSpi extends java.security.AlgorithmParametersSpi {
    private String curveName;
    private ECParameterSpec ecParameterSpec;

    @Override // java.security.AlgorithmParametersSpi
    protected byte[] engineGetEncoded() throws IOException {
        return engineGetEncoded("ASN.1");
    }

    @Override // java.security.AlgorithmParametersSpi
    protected byte[] engineGetEncoded(String str) throws IOException {
        X962Parameters x962Parameters;
        if (isASN1FormatString(str)) {
            ECParameterSpec eCParameterSpec = this.ecParameterSpec;
            if (eCParameterSpec == null) {
                x962Parameters = new X962Parameters((ASN1Null) DERNull.INSTANCE);
            } else {
                String str2 = this.curveName;
                if (str2 != null) {
                    x962Parameters = new X962Parameters(ECUtil.getNamedCurveOid(str2));
                } else {
                    org.bouncycastle.jce.spec.ECParameterSpec convertSpec = EC5Util.convertSpec(eCParameterSpec);
                    x962Parameters = new X962Parameters(new X9ECParameters(convertSpec.getCurve(), new X9ECPoint(convertSpec.getG(), false), convertSpec.getN(), convertSpec.getH(), convertSpec.getSeed()));
                }
            }
            return x962Parameters.getEncoded();
        }
        throw new IOException(GeneratedOutlineSupport1.outline72("Unknown parameters format in AlgorithmParameters object: ", str));
    }

    @Override // java.security.AlgorithmParametersSpi
    protected <T extends AlgorithmParameterSpec> T engineGetParameterSpec(Class<T> cls) throws InvalidParameterSpecException {
        if (ECParameterSpec.class.isAssignableFrom(cls) || cls == AlgorithmParameterSpec.class) {
            return this.ecParameterSpec;
        }
        if (ECGenParameterSpec.class.isAssignableFrom(cls)) {
            String str = this.curveName;
            if (str != null) {
                ASN1ObjectIdentifier namedCurveOid = ECUtil.getNamedCurveOid(str);
                return namedCurveOid != null ? new ECGenParameterSpec(namedCurveOid.getId()) : new ECGenParameterSpec(this.curveName);
            }
            ASN1ObjectIdentifier namedCurveOid2 = ECUtil.getNamedCurveOid(EC5Util.convertSpec(this.ecParameterSpec));
            if (namedCurveOid2 != null) {
                return new ECGenParameterSpec(namedCurveOid2.getId());
            }
        }
        throw new InvalidParameterSpecException(GeneratedOutlineSupport1.outline38(cls, GeneratedOutlineSupport1.outline107("EC AlgorithmParameters cannot convert to ")));
    }

    @Override // java.security.AlgorithmParametersSpi
    protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec) throws InvalidParameterSpecException {
        if (!(algorithmParameterSpec instanceof ECGenParameterSpec)) {
            if (algorithmParameterSpec instanceof ECParameterSpec) {
                this.curveName = algorithmParameterSpec instanceof ECNamedCurveSpec ? ((ECNamedCurveSpec) algorithmParameterSpec).getName() : null;
                this.ecParameterSpec = (ECParameterSpec) algorithmParameterSpec;
                return;
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AlgorithmParameterSpec class not recognized: ");
            outline107.append(algorithmParameterSpec.getClass().getName());
            throw new InvalidParameterSpecException(outline107.toString());
        }
        ECGenParameterSpec eCGenParameterSpec = (ECGenParameterSpec) algorithmParameterSpec;
        X9ECParameters domainParametersFromGenSpec = ECUtils.getDomainParametersFromGenSpec(eCGenParameterSpec);
        if (domainParametersFromGenSpec == null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("EC curve name not recognized: ");
            outline1072.append(eCGenParameterSpec.getName());
            throw new InvalidParameterSpecException(outline1072.toString());
        }
        this.curveName = eCGenParameterSpec.getName();
        ECParameterSpec convertToSpec = EC5Util.convertToSpec(domainParametersFromGenSpec);
        this.ecParameterSpec = new ECNamedCurveSpec(this.curveName, convertToSpec.getCurve(), convertToSpec.getGenerator(), convertToSpec.getOrder(), BigInteger.valueOf(convertToSpec.getCofactor()));
    }

    @Override // java.security.AlgorithmParametersSpi
    protected void engineInit(byte[] bArr) throws IOException {
        engineInit(bArr, "ASN.1");
    }

    @Override // java.security.AlgorithmParametersSpi
    protected void engineInit(byte[] bArr, String str) throws IOException {
        if (isASN1FormatString(str)) {
            X962Parameters x962Parameters = X962Parameters.getInstance(bArr);
            ECCurve curve = EC5Util.getCurve(BouncyCastleProvider.CONFIGURATION, x962Parameters);
            if (x962Parameters.isNamedCurve()) {
                ASN1ObjectIdentifier aSN1ObjectIdentifier = ASN1ObjectIdentifier.getInstance(x962Parameters.getParameters());
                this.curveName = ECNamedCurveTable.getName(aSN1ObjectIdentifier);
                if (this.curveName == null) {
                    this.curveName = aSN1ObjectIdentifier.getId();
                }
            }
            this.ecParameterSpec = EC5Util.convertToSpec(x962Parameters, curve);
            return;
        }
        throw new IOException(GeneratedOutlineSupport1.outline72("Unknown encoded parameters format in AlgorithmParameters object: ", str));
    }

    @Override // java.security.AlgorithmParametersSpi
    protected String engineToString() {
        return "EC Parameters";
    }

    protected boolean isASN1FormatString(String str) {
        return str == null || str.equals("ASN.1");
    }
}