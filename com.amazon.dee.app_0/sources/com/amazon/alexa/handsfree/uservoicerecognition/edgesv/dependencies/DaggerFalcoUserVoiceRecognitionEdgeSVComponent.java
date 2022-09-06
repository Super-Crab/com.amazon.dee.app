package com.amazon.alexa.handsfree.uservoicerecognition.edgesv.dependencies;

import com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRContract;
import com.amazon.alexa.handsfree.uservoicerecognition.edgesv.EdgeSVUVRModule;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class DaggerFalcoUserVoiceRecognitionEdgeSVComponent implements FalcoUserVoiceRecognitionEdgeSVComponent {
    private Provider<EdgeSVUVRModule> provideEdgeSVUVRModuleProvider;
    private Provider<UVRContract> provideUVRContractProvider;

    /* loaded from: classes8.dex */
    public static final class Builder {
        private FalcoUserVoiceRecognitionEdgeSVModule falcoUserVoiceRecognitionEdgeSVModule;

        public FalcoUserVoiceRecognitionEdgeSVComponent build() {
            Preconditions.checkBuilderRequirement(this.falcoUserVoiceRecognitionEdgeSVModule, FalcoUserVoiceRecognitionEdgeSVModule.class);
            return new DaggerFalcoUserVoiceRecognitionEdgeSVComponent(this);
        }

        public Builder falcoUserVoiceRecognitionEdgeSVModule(FalcoUserVoiceRecognitionEdgeSVModule falcoUserVoiceRecognitionEdgeSVModule) {
            this.falcoUserVoiceRecognitionEdgeSVModule = (FalcoUserVoiceRecognitionEdgeSVModule) Preconditions.checkNotNull(falcoUserVoiceRecognitionEdgeSVModule);
            return this;
        }

        private Builder() {
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private void initialize(Builder builder) {
        this.provideEdgeSVUVRModuleProvider = DoubleCheck.provider(FalcoUserVoiceRecognitionEdgeSVModule_ProvideEdgeSVUVRModuleFactory.create(builder.falcoUserVoiceRecognitionEdgeSVModule));
        this.provideUVRContractProvider = DoubleCheck.provider(FalcoUserVoiceRecognitionEdgeSVModule_ProvideUVRContractFactory.create(builder.falcoUserVoiceRecognitionEdgeSVModule, this.provideEdgeSVUVRModuleProvider));
    }

    @Override // com.amazon.alexa.handsfree.uservoicerecognition.edgesv.dependencies.FalcoUserVoiceRecognitionEdgeSVComponent
    public EdgeSVUVRModule edgeSVUVRModule() {
        return this.provideEdgeSVUVRModuleProvider.mo10268get();
    }

    @Override // com.amazon.alexa.handsfree.uservoicerecognition.edgesv.dependencies.FalcoUserVoiceRecognitionEdgeSVComponent
    public UVRContract uvrContract() {
        return this.provideUVRContractProvider.mo10268get();
    }

    private DaggerFalcoUserVoiceRecognitionEdgeSVComponent(Builder builder) {
        initialize(builder);
    }
}
