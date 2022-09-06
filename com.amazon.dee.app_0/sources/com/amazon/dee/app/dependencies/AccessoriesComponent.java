package com.amazon.dee.app.dependencies;

import com.amazon.alexa.accessory.Accessories;
import dagger.Component;
@Component(modules = {AccessoriesAlexaModule.class, AccessoryModule.class})
@ApplicationScope
/* loaded from: classes12.dex */
public interface AccessoriesComponent {
    Accessories accessories();
}
