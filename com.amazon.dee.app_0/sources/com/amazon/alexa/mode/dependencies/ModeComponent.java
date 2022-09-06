package com.amazon.alexa.mode.dependencies;

import com.amazon.alexa.mode.service.DefaultModeServiceV2;
import dagger.Component;
@ModeScope
@Component(modules = {ModeModule.class})
/* loaded from: classes9.dex */
public interface ModeComponent {
    void inject(DefaultModeServiceV2 defaultModeServiceV2);
}
