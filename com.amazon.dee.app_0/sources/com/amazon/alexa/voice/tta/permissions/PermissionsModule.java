package com.amazon.alexa.voice.tta.permissions;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes11.dex */
public class PermissionsModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public PermissionsUtil providesPermissionsUtil() {
        return new PermissionsUtil();
    }
}
