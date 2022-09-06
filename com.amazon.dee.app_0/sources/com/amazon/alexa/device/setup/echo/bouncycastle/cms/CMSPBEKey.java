package com.amazon.alexa.device.setup.echo.bouncycastle.cms;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.spec.InvalidParameterSpecException;
import javax.crypto.interfaces.PBEKey;
import javax.crypto.spec.PBEParameterSpec;
/* loaded from: classes.dex */
public abstract class CMSPBEKey implements PBEKey {
    private final int iterationCount;
    private final char[] password;
    private final byte[] salt;

    public CMSPBEKey(char[] cArr, byte[] bArr, int i) {
        this.password = cArr;
        this.salt = bArr;
        this.iterationCount = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static PBEParameterSpec getParamSpec(AlgorithmParameters algorithmParameters) throws InvalidAlgorithmParameterException {
        try {
            return (PBEParameterSpec) algorithmParameters.getParameterSpec(PBEParameterSpec.class);
        } catch (InvalidParameterSpecException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("cannot process PBE spec: ");
            outline107.append(e.getMessage());
            throw new InvalidAlgorithmParameterException(outline107.toString());
        }
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return "PKCS5S2";
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        return null;
    }

    abstract byte[] getEncoded(String str);

    @Override // java.security.Key
    public String getFormat() {
        return "RAW";
    }

    @Override // javax.crypto.interfaces.PBEKey
    public int getIterationCount() {
        return this.iterationCount;
    }

    @Override // javax.crypto.interfaces.PBEKey
    public char[] getPassword() {
        return this.password;
    }

    @Override // javax.crypto.interfaces.PBEKey
    public byte[] getSalt() {
        return this.salt;
    }

    public CMSPBEKey(char[] cArr, PBEParameterSpec pBEParameterSpec) {
        this(cArr, pBEParameterSpec.getSalt(), pBEParameterSpec.getIterationCount());
    }
}
