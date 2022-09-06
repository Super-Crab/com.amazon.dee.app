package com.amazon.alexa.mobilytics;

import com.amazon.alexa.mobilytics.dependencies.MobilyticsModule;
import dagger.Component;
import javax.inject.Singleton;
@Component(modules = {MobilyticsModule.class})
@Singleton
/* loaded from: classes9.dex */
public interface MobilyticsComponent {
    Mobilytics mobilytics();
}
