package org.bouncycastle.crypto.macs;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.apache.logging.log4j.util.ProcessIdUtil;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.digests.SkeinEngine;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.SkeinParameters;
/* loaded from: classes4.dex */
public class SkeinMac implements Mac {
    public static final int SKEIN_1024 = 1024;
    public static final int SKEIN_256 = 256;
    public static final int SKEIN_512 = 512;
    private SkeinEngine engine;

    public SkeinMac(int i, int i2) {
        this.engine = new SkeinEngine(i, i2);
    }

    public SkeinMac(SkeinMac skeinMac) {
        this.engine = new SkeinEngine(skeinMac.engine);
    }

    @Override // org.bouncycastle.crypto.Mac
    public int doFinal(byte[] bArr, int i) {
        return this.engine.doFinal(bArr, i);
    }

    @Override // org.bouncycastle.crypto.Mac
    public String getAlgorithmName() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Skein-MAC-");
        outline107.append(this.engine.getBlockSize() * 8);
        outline107.append(ProcessIdUtil.DEFAULT_PROCESSID);
        outline107.append(this.engine.getOutputSize() * 8);
        return outline107.toString();
    }

    @Override // org.bouncycastle.crypto.Mac
    public int getMacSize() {
        return this.engine.getOutputSize();
    }

    @Override // org.bouncycastle.crypto.Mac
    public void init(CipherParameters cipherParameters) throws IllegalArgumentException {
        SkeinParameters build;
        if (cipherParameters instanceof SkeinParameters) {
            build = (SkeinParameters) cipherParameters;
        } else if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline102(cipherParameters, GeneratedOutlineSupport1.outline107("Invalid parameter passed to Skein MAC init - ")));
        } else {
            build = new SkeinParameters.Builder().setKey(((KeyParameter) cipherParameters).getKey()).build();
        }
        if (build.getKey() != null) {
            this.engine.init(build);
            return;
        }
        throw new IllegalArgumentException("Skein MAC requires a key parameter.");
    }

    @Override // org.bouncycastle.crypto.Mac
    public void reset() {
        this.engine.reset();
    }

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte b) {
        this.engine.update(b);
    }

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte[] bArr, int i, int i2) {
        this.engine.update(bArr, i, i2);
    }
}
