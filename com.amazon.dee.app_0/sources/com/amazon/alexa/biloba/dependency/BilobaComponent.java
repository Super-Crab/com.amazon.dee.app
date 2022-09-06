package com.amazon.alexa.biloba.dependency;

import com.amazon.alexa.biloba.dependency.BilobaViewComponent;
import com.amazon.alexa.biloba.model.Configuration;
import dagger.Component;
import javax.inject.Singleton;
@Component(modules = {ApplicationModule.class, ServiceModule.class, StorageModule.class, AndroidModule.class, SubcomponentsModule.class, Configuration.class, TimeZoneModule.class, PersonIdentityModule.class, AlertConfigurationModule.class})
@Singleton
/* loaded from: classes6.dex */
public interface BilobaComponent {
    BilobaViewComponent.Factory bilobaViewComponent();
}
