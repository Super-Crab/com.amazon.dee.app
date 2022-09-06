package com.amazon.alexa.handsfree.uservoicerecognition.edgesv.dependencies;

import com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRContract;
import com.amazon.alexa.handsfree.uservoicerecognition.edgesv.EdgeSVUVRModule;
import dagger.Component;
@Component(modules = {FalcoUserVoiceRecognitionEdgeSVModule.class})
@FalcoUserVoiceRecognitionEdgeSVScope
/* loaded from: classes8.dex */
public interface FalcoUserVoiceRecognitionEdgeSVComponent {
    EdgeSVUVRModule edgeSVUVRModule();

    UVRContract uvrContract();
}
