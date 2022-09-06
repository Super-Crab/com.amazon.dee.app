package com.amazon.alexa.redesign.dependency;

import com.amazon.alexa.redesign.debug.DebugMenuActivity;
import com.amazon.alexa.redesign.view.HomeRedesignViewController;
import com.amazon.alexa.redesign.view.container.HomeViewController;
import dagger.Component;
import javax.inject.Singleton;
@Component(modules = {ApplicationModule.class, RepositoryModule.class, ServiceClientModule.class, CacheModule.class})
@Singleton
/* loaded from: classes10.dex */
public interface HomeComponent {
    void inject(DebugMenuActivity debugMenuActivity);

    void inject(HomeRedesignViewController homeRedesignViewController);

    void inject(HomeViewController homeViewController);
}
