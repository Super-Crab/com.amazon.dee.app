package com.amazon.alexa.smarthomecameras.dependencies.modules;

import com.google.common.base.Preconditions;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes10.dex */
public class SmartAlertEventModule {
    private String imageUrl;

    public SmartAlertEventModule(String str) {
        Preconditions.checkNotNull(str, "imageUrl cannot be null");
        this.imageUrl = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    public String providesImageUrl() {
        return this.imageUrl;
    }
}
