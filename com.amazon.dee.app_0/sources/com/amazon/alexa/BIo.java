package com.amazon.alexa;

import android.util.Log;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.client.core.configuration.Feature;
import com.amazon.alexa.feature.consumer.api.FeatureFlagListener;
/* compiled from: AlexaService.java */
/* loaded from: classes.dex */
public class BIo implements Runnable {
    public final /* synthetic */ AlexaService BIo;
    public final /* synthetic */ ExtendedClient zZm;

    public BIo(AlexaService alexaService, ExtendedClient extendedClient) {
        this.BIo = alexaService;
        this.zZm = extendedClient;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void zZm(boolean z) {
        if (z) {
            this.BIo.Qgh.zQM(z);
            this.BIo.Tbw.zQM();
            return;
        }
        this.BIo.JTe.zZm();
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.BIo.jiA.getToken();
            if (this.BIo.jiA.zyO()) {
                this.BIo.noQ.zZm(Feature.ALEXA_VOX_ANDROID_DLS, new FeatureFlagListener() { // from class: com.amazon.alexa.-$$Lambda$BIo$5XkKpLxVAiCIOUVL7qSbOs6x1U8
                    @Override // com.amazon.alexa.feature.consumer.api.FeatureFlagListener
                    public final void onFeatureServiceReady(boolean z) {
                        BIo.this.zZm(z);
                    }
                });
            } else {
                Log.w(AlexaService.zZm, "No account registered. Stopping service");
                this.BIo.yPL.BIo(this.zZm);
                this.BIo.BIo(true);
            }
        } catch (Exception e) {
            Log.e(AlexaService.zZm, "Caught exception while checking for login state: ", e);
            this.BIo.yPL.BIo(this.zZm);
            this.BIo.BIo(true);
        }
    }
}
