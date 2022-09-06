package com.amazon.tarazed.dagger.injectors;

import android.content.Context;
import com.amazon.tarazed.dagger.components.DaggerGlobalComponent;
import com.amazon.tarazed.dagger.components.GlobalComponent;
import com.amazon.tarazed.dagger.modules.GlobalModule;
/* loaded from: classes13.dex */
public class GlobalInjector {
    private static GlobalComponent component;

    public static GlobalComponent getComponent() {
        GlobalComponent globalComponent = component;
        if (globalComponent != null) {
            return globalComponent;
        }
        throw new IllegalStateException("GlobalComponent has not been initialized!");
    }

    public static void initComponent(Context context) {
        if (component == null) {
            component = DaggerGlobalComponent.builder().globalModule(new GlobalModule(context)).build();
        }
    }
}
