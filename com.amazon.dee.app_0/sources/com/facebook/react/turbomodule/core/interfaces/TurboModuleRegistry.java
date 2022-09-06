package com.facebook.react.turbomodule.core.interfaces;

import androidx.annotation.Nullable;
import java.util.Collection;
import java.util.List;
/* loaded from: classes2.dex */
public interface TurboModuleRegistry {
    List<String> getEagerInitModuleNames();

    @Nullable
    TurboModule getModule(String str);

    Collection<TurboModule> getModules();

    boolean hasModule(String str);
}
