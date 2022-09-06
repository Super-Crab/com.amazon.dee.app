package com.amazon.alexa.handsfree.uservoicerecognition.edgesv.dependencies;

import com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRContract;
import com.amazon.alexa.handsfree.uservoicerecognition.edgesv.EdgeSVUVRModule;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class FalcoUserVoiceRecognitionEdgeSVModule_ProvideUVRContractFactory implements Factory<UVRContract> {
    private final Provider<EdgeSVUVRModule> edgeSVUVRModuleProvider;
    private final FalcoUserVoiceRecognitionEdgeSVModule module;

    public FalcoUserVoiceRecognitionEdgeSVModule_ProvideUVRContractFactory(FalcoUserVoiceRecognitionEdgeSVModule falcoUserVoiceRecognitionEdgeSVModule, Provider<EdgeSVUVRModule> provider) {
        this.module = falcoUserVoiceRecognitionEdgeSVModule;
        this.edgeSVUVRModuleProvider = provider;
    }

    public static FalcoUserVoiceRecognitionEdgeSVModule_ProvideUVRContractFactory create(FalcoUserVoiceRecognitionEdgeSVModule falcoUserVoiceRecognitionEdgeSVModule, Provider<EdgeSVUVRModule> provider) {
        return new FalcoUserVoiceRecognitionEdgeSVModule_ProvideUVRContractFactory(falcoUserVoiceRecognitionEdgeSVModule, provider);
    }

    public static UVRContract provideInstance(FalcoUserVoiceRecognitionEdgeSVModule falcoUserVoiceRecognitionEdgeSVModule, Provider<EdgeSVUVRModule> provider) {
        return proxyProvideUVRContract(falcoUserVoiceRecognitionEdgeSVModule, provider.mo10268get());
    }

    public static UVRContract proxyProvideUVRContract(FalcoUserVoiceRecognitionEdgeSVModule falcoUserVoiceRecognitionEdgeSVModule, EdgeSVUVRModule edgeSVUVRModule) {
        return (UVRContract) Preconditions.checkNotNull(falcoUserVoiceRecognitionEdgeSVModule.provideUVRContract(edgeSVUVRModule), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public UVRContract mo10268get() {
        return provideInstance(this.module, this.edgeSVUVRModuleProvider);
    }
}
