package com.amazon.alexa.hho.dependency;

import com.amazon.alexa.hho.api.HHOServiceImpl;
import dagger.Component;
import javax.inject.Singleton;
@Component(modules = {ApplicationModule.class, HHOModule.class})
@Singleton
/* loaded from: classes8.dex */
public interface HHOComponent {
    void inject(HHOServiceImpl hHOServiceImpl);
}
