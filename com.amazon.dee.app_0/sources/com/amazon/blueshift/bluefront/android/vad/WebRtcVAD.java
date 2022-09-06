package com.amazon.blueshift.bluefront.android.vad;

import com.amazon.blueshift.bluefront.android.vad.config.WebRtcVADConfig;
import java.nio.ByteBuffer;
/* loaded from: classes11.dex */
public final class WebRtcVAD extends AbstractVAD<WebRtcVADConfig> {
    public WebRtcVAD(int i, WebRtcVADConfig webRtcVADConfig) {
        super(i, webRtcVADConfig);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.blueshift.bluefront.android.vad.AbstractVAD
    public ByteBuffer setupVAD(WebRtcVADConfig webRtcVADConfig) {
        ByteBuffer createVAD = createVAD(false);
        if (webRtcVADConfig.getCustomizedAggression().isPresent()) {
            WebRtcVADConfig.Aggression aggression = webRtcVADConfig.getCustomizedAggression().get();
            setWebRtcCustomizedMode(createVAD, aggression.getOverHangMax1(), aggression.getOverHangMax2(), aggression.getLocalThreshold(), aggression.getGlobalThreshold());
        } else {
            setWebRtcMode(createVAD, webRtcVADConfig.getAggressionMode().ordinal());
        }
        return createVAD;
    }
}
