package com.amazon.tarazed.dagger.injectors;

import android.content.Context;
import com.amazon.tarazed.dagger.components.DaggerLibraryComponent;
import com.amazon.tarazed.dagger.components.LibraryComponent;
import com.amazon.tarazed.dagger.modules.LibraryModule;
/* loaded from: classes13.dex */
public class LibraryInjector {
    private static LibraryComponent component;

    public static LibraryComponent getComponent() {
        LibraryComponent libraryComponent = component;
        if (libraryComponent != null) {
            return libraryComponent;
        }
        throw new IllegalStateException("LibraryComponent has not been initialized!");
    }

    public static void initComponent(Context context) {
        if (component == null) {
            GlobalInjector.initComponent(context);
            component = DaggerLibraryComponent.builder().globalComponent(GlobalInjector.getComponent()).libraryModule(new LibraryModule(context)).build();
        }
    }
}
