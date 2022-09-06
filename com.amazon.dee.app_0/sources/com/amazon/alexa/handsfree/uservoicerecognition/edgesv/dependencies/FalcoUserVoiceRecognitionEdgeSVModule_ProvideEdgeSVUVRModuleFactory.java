package com.amazon.alexa.handsfree.uservoicerecognition.edgesv.dependencies;

import com.amazon.alexa.handsfree.uservoicerecognition.edgesv.EdgeSVUVRModule;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes8.dex */
public final class FalcoUserVoiceRecognitionEdgeSVModule_ProvideEdgeSVUVRModuleFactory implements Factory<EdgeSVUVRModule> {
    private final FalcoUserVoiceRecognitionEdgeSVModule module;

    public FalcoUserVoiceRecognitionEdgeSVModule_ProvideEdgeSVUVRModuleFactory(FalcoUserVoiceRecognitionEdgeSVModule falcoUserVoiceRecognitionEdgeSVModule) {
        this.module = falcoUserVoiceRecognitionEdgeSVModule;
    }

    public static FalcoUserVoiceRecognitionEdgeSVModule_ProvideEdgeSVUVRModuleFactory create(FalcoUserVoiceRecognitionEdgeSVModule falcoUserVoiceRecognitionEdgeSVModule) {
        return new FalcoUserVoiceRecognitionEdgeSVModule_ProvideEdgeSVUVRModuleFactory(falcoUserVoiceRecognitionEdgeSVModule);
    }

    public static EdgeSVUVRModule provideInstance(FalcoUserVoiceRecognitionEdgeSVModule falcoUserVoiceRecognitionEdgeSVModule) {
        return proxyProvideEdgeSVUVRModule(falcoUserVoiceRecognitionEdgeSVModule);
    }

    public static EdgeSVUVRModule proxyProvideEdgeSVUVRModule(FalcoUserVoiceRecognitionEdgeSVModule falcoUserVoiceRecognitionEdgeSVModule) {
        return (EdgeSVUVRModule) Preconditions.checkNotNull(falcoUserVoiceRecognitionEdgeSVModule.provideEdgeSVUVRModule(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EdgeSVUVRModule mo10268get() {
        return provideInstance(this.module);
    }
}
