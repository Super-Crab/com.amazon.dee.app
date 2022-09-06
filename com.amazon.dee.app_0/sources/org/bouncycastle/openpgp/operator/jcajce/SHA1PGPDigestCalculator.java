package org.bouncycastle.openpgp.operator.jcajce;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.bouncycastle.jcajce.io.OutputStreamFactory;
import org.bouncycastle.openpgp.operator.PGPDigestCalculator;
/* loaded from: classes5.dex */
class SHA1PGPDigestCalculator implements PGPDigestCalculator {
    private MessageDigest digest;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SHA1PGPDigestCalculator() {
        try {
            this.digest = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("cannot find SHA-1: ");
            outline107.append(e.getMessage());
            throw new IllegalStateException(outline107.toString());
        }
    }

    @Override // org.bouncycastle.openpgp.operator.PGPDigestCalculator
    public int getAlgorithm() {
        return 2;
    }

    @Override // org.bouncycastle.openpgp.operator.PGPDigestCalculator
    public byte[] getDigest() {
        return this.digest.digest();
    }

    @Override // org.bouncycastle.openpgp.operator.PGPDigestCalculator
    public OutputStream getOutputStream() {
        return OutputStreamFactory.createStream(this.digest);
    }

    @Override // org.bouncycastle.openpgp.operator.PGPDigestCalculator
    public void reset() {
        this.digest.reset();
    }
}
