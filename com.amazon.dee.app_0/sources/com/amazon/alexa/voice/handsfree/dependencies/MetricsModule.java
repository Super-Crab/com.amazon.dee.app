package com.amazon.alexa.voice.handsfree.dependencies;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.metrics.utils.AlexaApplicationInformationProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfScope;
import com.amazon.alexa.handsfree.protocols.metrics.MetricsEmitterConfig;
import com.amazon.alexa.handsfree.protocols.utils.ApplicationInformationProvider;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes11.dex */
public class MetricsModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    @AhfScope
    @Provides
    public ApplicationInformationProvider provideApplicationInformationProvider(@NonNull Context context) {
        AlexaApplicationInformationProvider alexaApplicationInformationProvider = new AlexaApplicationInformationProvider(context);
        alexaApplicationInformationProvider.setFalcoBuildCode(-1);
        return alexaApplicationInformationProvider;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @AhfScope
    @Provides
    public MetricsEmitterConfig provideMetricsEmitterConfig() {
        return new MetricsEmitterConfig() { // from class: com.amazon.alexa.voice.handsfree.dependencies.MetricsModule.1
            @Override // com.amazon.alexa.handsfree.protocols.metrics.MetricsEmitterConfig
            public boolean canEmitMetrics() {
                return true;
            }
        };
    }
}
