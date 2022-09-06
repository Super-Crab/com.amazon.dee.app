package com.amazon.alexa.handsfree.uservoicerecognition.edgesv.dependencies;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRContract;
import com.amazon.alexa.handsfree.uservoicerecognition.edgesv.EdgeSVUVRModule;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes8.dex */
public class FalcoUserVoiceRecognitionEdgeSVModule {
    private final Context mContext;

    public FalcoUserVoiceRecognitionEdgeSVModule(@NonNull Context context) {
        this.mContext = context;
    }

    @Provides
    @FalcoUserVoiceRecognitionEdgeSVScope
    public EdgeSVUVRModule provideEdgeSVUVRModule() {
        return EdgeSVUVRModule.getInstance();
    }

    @Provides
    @FalcoUserVoiceRecognitionEdgeSVScope
    public UVRContract provideUVRContract(@NonNull EdgeSVUVRModule edgeSVUVRModule) {
        return edgeSVUVRModule.getUVRContract(this.mContext);
    }
}
