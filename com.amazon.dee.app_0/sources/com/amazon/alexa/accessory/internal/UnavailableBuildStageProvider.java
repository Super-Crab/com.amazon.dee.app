package com.amazon.alexa.accessory.internal;

import com.amazon.alexa.accessory.BuildStageProvider;
/* loaded from: classes.dex */
public class UnavailableBuildStageProvider implements BuildStageProvider {
    @Override // com.amazon.alexa.accessory.BuildStageProvider
    public BuildStageProvider.BuildStage getBuildStage() {
        return BuildStageProvider.BuildStage.UNKNOWN;
    }
}
