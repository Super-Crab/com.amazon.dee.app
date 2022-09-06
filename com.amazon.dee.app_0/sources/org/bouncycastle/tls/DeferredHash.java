package org.bouncycastle.tls;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import org.bouncycastle.tls.crypto.TlsHash;
import org.bouncycastle.util.Shorts;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public class DeferredHash implements TlsHandshakeHash {
    protected static final int BUFFERING_HASH_LIMIT = 4;
    private DigestInputBuffer buf;
    protected TlsContext context;
    private boolean forceBuffering;
    private Hashtable hashes;
    private boolean sealed;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DeferredHash(TlsContext tlsContext) {
        this.context = tlsContext;
        this.buf = new DigestInputBuffer();
        this.hashes = new Hashtable();
        this.forceBuffering = false;
        this.sealed = false;
    }

    private DeferredHash(TlsContext tlsContext, Hashtable hashtable) {
        this.context = tlsContext;
        this.buf = null;
        this.hashes = hashtable;
        this.forceBuffering = false;
        this.sealed = true;
    }

    @Override // org.bouncycastle.tls.crypto.TlsHash
    public byte[] calculateHash() {
        throw new IllegalStateException("Use fork() to get a definite hash");
    }

    protected void checkStopBuffering() {
        if (this.forceBuffering || !this.sealed || this.buf == null || this.hashes.size() > 4) {
            return;
        }
        Enumeration elements = this.hashes.elements();
        while (elements.hasMoreElements()) {
            this.buf.updateDigest((TlsHash) elements.nextElement());
        }
        this.buf = null;
    }

    protected void checkTrackingHash(Short sh) {
        if (!this.hashes.containsKey(sh)) {
            this.hashes.put(sh, this.context.getCrypto().createHash(sh.shortValue()));
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsHash
    public Object clone() {
        throw new IllegalStateException("attempt to clone a DeferredHash");
    }

    protected TlsHash cloneHash(Short sh) {
        return (TlsHash) ((TlsHash) this.hashes.get(sh)).clone();
    }

    protected TlsHash cloneHash(short s) {
        return cloneHash(Shorts.valueOf(s));
    }

    protected void cloneHash(Hashtable hashtable, Short sh) {
        TlsHash cloneHash = cloneHash(sh);
        DigestInputBuffer digestInputBuffer = this.buf;
        if (digestInputBuffer != null) {
            digestInputBuffer.updateDigest(cloneHash);
        }
        hashtable.put(sh, cloneHash);
    }

    protected void cloneHash(Hashtable hashtable, short s) {
        cloneHash(hashtable, Shorts.valueOf(s));
    }

    @Override // org.bouncycastle.tls.TlsHandshakeHash
    public void copyBufferTo(OutputStream outputStream) throws IOException {
        DigestInputBuffer digestInputBuffer = this.buf;
        if (digestInputBuffer != null) {
            digestInputBuffer.copyTo(outputStream);
            return;
        }
        throw new IllegalStateException("Not buffering");
    }

    @Override // org.bouncycastle.tls.TlsHandshakeHash
    public void forceBuffering() {
        if (!this.sealed) {
            this.forceBuffering = true;
            return;
        }
        throw new IllegalStateException("Too late to force buffering");
    }

    @Override // org.bouncycastle.tls.TlsHandshakeHash
    public TlsHash forkPRFHash() {
        checkStopBuffering();
        SecurityParameters securityParametersHandshake = this.context.getSecurityParametersHandshake();
        int pRFAlgorithm = securityParametersHandshake.getPRFAlgorithm();
        TlsHash combinedHash = (pRFAlgorithm == 0 || pRFAlgorithm == 1) ? new CombinedHash(this.context, cloneHash((short) 1), cloneHash((short) 2)) : cloneHash(securityParametersHandshake.getPRFHashAlgorithm());
        DigestInputBuffer digestInputBuffer = this.buf;
        if (digestInputBuffer != null) {
            digestInputBuffer.updateDigest(combinedHash);
        }
        return combinedHash;
    }

    @Override // org.bouncycastle.tls.TlsHandshakeHash
    public byte[] getFinalHash(short s) {
        TlsHash tlsHash = (TlsHash) this.hashes.get(Shorts.valueOf(s));
        if (tlsHash == null) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("HashAlgorithm.");
            outline107.append(HashAlgorithm.getText(s));
            outline107.append(" is not being tracked");
            throw new IllegalStateException(outline107.toString());
        }
        checkStopBuffering();
        TlsHash tlsHash2 = (TlsHash) tlsHash.clone();
        DigestInputBuffer digestInputBuffer = this.buf;
        if (digestInputBuffer != null) {
            digestInputBuffer.updateDigest(tlsHash2);
        }
        return tlsHash2.calculateHash();
    }

    @Override // org.bouncycastle.tls.TlsHandshakeHash
    public void notifyPRFDetermined() {
        SecurityParameters securityParametersHandshake = this.context.getSecurityParametersHandshake();
        int pRFAlgorithm = securityParametersHandshake.getPRFAlgorithm();
        if (pRFAlgorithm == 0 || pRFAlgorithm == 1) {
            checkTrackingHash(Shorts.valueOf((short) 1));
            checkTrackingHash(Shorts.valueOf((short) 2));
            return;
        }
        checkTrackingHash(Shorts.valueOf(securityParametersHandshake.getPRFHashAlgorithm()));
        if (!TlsUtils.isTLSv13(securityParametersHandshake.getNegotiatedVersion())) {
            return;
        }
        sealHashAlgorithms();
    }

    @Override // org.bouncycastle.tls.crypto.TlsHash
    public void reset() {
        DigestInputBuffer digestInputBuffer = this.buf;
        if (digestInputBuffer != null) {
            digestInputBuffer.reset();
            return;
        }
        Enumeration elements = this.hashes.elements();
        while (elements.hasMoreElements()) {
            ((TlsHash) elements.nextElement()).reset();
        }
    }

    @Override // org.bouncycastle.tls.TlsHandshakeHash
    public void sealHashAlgorithms() {
        if (!this.sealed) {
            this.sealed = true;
            checkStopBuffering();
            return;
        }
        throw new IllegalStateException("Already sealed");
    }

    @Override // org.bouncycastle.tls.TlsHandshakeHash
    public TlsHandshakeHash stopTracking() {
        short s;
        SecurityParameters securityParametersHandshake = this.context.getSecurityParametersHandshake();
        Hashtable hashtable = new Hashtable();
        int pRFAlgorithm = securityParametersHandshake.getPRFAlgorithm();
        if (pRFAlgorithm == 0 || pRFAlgorithm == 1) {
            cloneHash(hashtable, (short) 1);
            s = 2;
        } else {
            s = securityParametersHandshake.getPRFHashAlgorithm();
        }
        cloneHash(hashtable, s);
        return new DeferredHash(this.context, hashtable);
    }

    @Override // org.bouncycastle.tls.TlsHandshakeHash
    public void trackHashAlgorithm(short s) {
        if (!this.sealed) {
            checkTrackingHash(Shorts.valueOf(s));
            return;
        }
        throw new IllegalStateException("Too late to track more hash algorithms");
    }

    @Override // org.bouncycastle.tls.crypto.TlsHash
    public void update(byte[] bArr, int i, int i2) {
        DigestInputBuffer digestInputBuffer = this.buf;
        if (digestInputBuffer != null) {
            digestInputBuffer.write(bArr, i, i2);
            return;
        }
        Enumeration elements = this.hashes.elements();
        while (elements.hasMoreElements()) {
            ((TlsHash) elements.nextElement()).update(bArr, i, i2);
        }
    }
}
