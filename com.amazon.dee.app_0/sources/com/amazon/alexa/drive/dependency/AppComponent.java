package com.amazon.alexa.drive.dependency;

import dagger.Component;
@AppScope
@Component(modules = {NavigationDataModule.class})
/* loaded from: classes7.dex */
public interface AppComponent {
    DriveModeRootComponent getDriveModeRootComponent(AndroidModule androidModule, RoutingModule routingModule);
}
