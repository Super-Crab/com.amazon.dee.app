package org.bouncycastle.gpg.keybox.jcajce;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.bouncycastle.gpg.keybox.BlobVerifier;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class JcaBlobVerifier implements BlobVerifier {
    private final MessageDigest md5Digest;
    private final MessageDigest sha1Digest;

    /* JADX INFO: Access modifiers changed from: package-private */
    public JcaBlobVerifier(JcaJceHelper jcaJceHelper) throws NoSuchProviderException, NoSuchAlgorithmException {
        MessageDigest messageDigest;
        this.sha1Digest = jcaJceHelper.createMessageDigest("SHA-1");
        try {
            messageDigest = jcaJceHelper.createMessageDigest(MessageDigestAlgorithms.MD5);
        } catch (NoSuchAlgorithmException unused) {
            messageDigest = null;
        }
        this.md5Digest = messageDigest;
    }

    @Override // org.bouncycastle.gpg.keybox.BlobVerifier
    public boolean isMatched(byte[] bArr, byte[] bArr2) {
        this.sha1Digest.update(bArr, 0, bArr.length);
        byte[] digest = this.sha1Digest.digest();
        if (!Arrays.constantTimeAreEqual(digest, bArr2)) {
            if (bArr2[0] != 0 || bArr2[1] != 0 || bArr2[2] != 0 || bArr2[3] != 0) {
                return false;
            }
            this.md5Digest.update(bArr, 0, bArr.length);
            Arrays.fill(digest, (byte) 0);
            try {
                this.md5Digest.digest(digest, 4, this.md5Digest.getDigestLength());
                return Arrays.constantTimeAreEqual(digest, bArr2);
            } catch (DigestException e) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("internal buffer to small: ");
                outline107.append(e.getMessage());
                throw new IllegalStateException(outline107.toString(), e);
            }
        }
        return true;
    }
}
