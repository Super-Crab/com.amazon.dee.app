package org.bouncycastle.jcajce.provider.symmetric.util;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import javax.crypto.spec.IvParameterSpec;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class IvAlgorithmParameters extends BaseAlgorithmParameters {
    private byte[] iv;

    @Override // java.security.AlgorithmParametersSpi
    protected byte[] engineGetEncoded() throws IOException {
        return engineGetEncoded("ASN.1");
    }

    @Override // java.security.AlgorithmParametersSpi
    protected byte[] engineGetEncoded(String str) throws IOException {
        if (isASN1FormatString(str)) {
            return new DEROctetString(engineGetEncoded("RAW")).getEncoded();
        }
        if (!str.equals("RAW")) {
            return null;
        }
        return Arrays.clone(this.iv);
    }

    @Override // java.security.AlgorithmParametersSpi
    protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec) throws InvalidParameterSpecException {
        if (algorithmParameterSpec instanceof IvParameterSpec) {
            this.iv = ((IvParameterSpec) algorithmParameterSpec).getIV();
            return;
        }
        throw new InvalidParameterSpecException("IvParameterSpec required to initialise a IV parameters algorithm parameters object");
    }

    @Override // java.security.AlgorithmParametersSpi
    protected void engineInit(byte[] bArr) throws IOException {
        if (bArr.length % 8 != 0 && bArr[0] == 4 && bArr[1] == bArr.length - 2) {
            bArr = ((ASN1OctetString) ASN1Primitive.fromByteArray(bArr)).getOctets();
        }
        this.iv = Arrays.clone(bArr);
    }

    @Override // java.security.AlgorithmParametersSpi
    protected void engineInit(byte[] bArr, String str) throws IOException {
        if (isASN1FormatString(str)) {
            try {
                engineInit(((ASN1OctetString) ASN1Primitive.fromByteArray(bArr)).getOctets());
            } catch (Exception e) {
                throw new IOException(GeneratedOutlineSupport1.outline68("Exception decoding: ", e));
            }
        } else if (!str.equals("RAW")) {
            throw new IOException("Unknown parameters format in IV parameters object");
        } else {
            engineInit(bArr);
        }
    }

    @Override // java.security.AlgorithmParametersSpi
    protected String engineToString() {
        return "IV Parameters";
    }

    @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameters
    protected AlgorithmParameterSpec localEngineGetParameterSpec(Class cls) throws InvalidParameterSpecException {
        if (cls == IvParameterSpec.class || cls == AlgorithmParameterSpec.class) {
            return new IvParameterSpec(this.iv);
        }
        throw new InvalidParameterSpecException("unknown parameter spec passed to IV parameters object.");
    }
}
