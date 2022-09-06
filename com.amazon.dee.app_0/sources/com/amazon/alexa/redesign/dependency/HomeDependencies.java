package com.amazon.alexa.redesign.dependency;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.redesign.debug.DebugMenuActivity;
import com.amazon.alexa.redesign.view.HomeRedesignViewController;
import com.amazon.alexa.redesign.view.container.HomeViewController;
import com.amazon.regulator.Component;
/* loaded from: classes10.dex */
public final class HomeDependencies {
    @VisibleForTesting
    static HomeComponent homeComponent;

    private HomeDependencies() {
    }

    public static synchronized void initialize(Component component) {
        synchronized (HomeDependencies.class) {
            homeComponent = DaggerHomeComponent.builder().applicationModule(new ApplicationModule(component)).build();
        }
    }

    public static synchronized void inject(DebugMenuActivity debugMenuActivity) {
        synchronized (HomeDependencies.class) {
            homeComponent.inject(debugMenuActivity);
        }
    }

    public static synchronized void inject(HomeRedesignViewController homeRedesignViewController) {
        synchronized (HomeDependencies.class) {
            homeComponent.inject(homeRedesignViewController);
        }
    }

    public static synchronized void inject(HomeViewController homeViewController) {
        synchronized (HomeDependencies.class) {
            homeComponent.inject(homeViewController);
        }
    }
}
