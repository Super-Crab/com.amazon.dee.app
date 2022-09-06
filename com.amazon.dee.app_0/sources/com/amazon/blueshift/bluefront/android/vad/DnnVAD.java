package com.amazon.blueshift.bluefront.android.vad;

import com.amazon.blueshift.bluefront.android.vad.config.DnnVADConfig;
import com.google.common.base.Preconditions;
import java.nio.ByteBuffer;
/* loaded from: classes11.dex */
public class DnnVAD extends AbstractVAD<DnnVADConfig> {
    public DnnVAD(int i, DnnVADConfig dnnVADConfig) {
        super(i, dnnVADConfig);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.blueshift.bluefront.android.vad.AbstractVAD
    public ByteBuffer setupVAD(DnnVADConfig dnnVADConfig) {
        Preconditions.checkNotNull(dnnVADConfig, "DnnVadConfig cannot be null.");
        ByteBuffer createVAD = createVAD(true);
        if (dnnVADConfig.getThreshold().isPresent()) {
            setDNNThreshold(createVAD, dnnVADConfig.getThreshold().get().floatValue());
        }
        return createVAD;
    }
}
