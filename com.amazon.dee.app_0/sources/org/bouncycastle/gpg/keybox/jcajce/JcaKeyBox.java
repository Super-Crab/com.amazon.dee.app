package org.bouncycastle.gpg.keybox.jcajce;

import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import org.bouncycastle.gpg.keybox.BlobVerifier;
import org.bouncycastle.gpg.keybox.KeyBox;
import org.bouncycastle.openpgp.operator.KeyFingerPrintCalculator;
/* loaded from: classes4.dex */
public class JcaKeyBox extends KeyBox {
    /* JADX INFO: Access modifiers changed from: package-private */
    public JcaKeyBox(InputStream inputStream, KeyFingerPrintCalculator keyFingerPrintCalculator, BlobVerifier blobVerifier) throws IOException {
        super(inputStream, keyFingerPrintCalculator, blobVerifier);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public JcaKeyBox(byte[] bArr, KeyFingerPrintCalculator keyFingerPrintCalculator, BlobVerifier blobVerifier) throws IOException, NoSuchProviderException, NoSuchAlgorithmException {
        super(bArr, keyFingerPrintCalculator, blobVerifier);
    }
}
