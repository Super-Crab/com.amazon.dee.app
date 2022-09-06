package com.amazon.dee.app.dependencies;

import com.amazon.alexa.drivemode.api.DriveModeService;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes12.dex */
public class DriveModeApplicationModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @ApplicationScope
    public DriveModeService provideDriveModeService() {
        Optional optional = ComponentRegistry.getInstance().get(DriveModeService.class);
        Preconditions.checkNotNull(optional);
        return (DriveModeService) optional.get();
    }
}
