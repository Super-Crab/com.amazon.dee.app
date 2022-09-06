package com.amazon.comms.calling.dependency;

import android.content.Context;
import androidx.annotation.NonNull;
/* loaded from: classes10.dex */
public final class DaggerWrapper {
    private static CallUiComponent libraryComponent;

    private DaggerWrapper() {
    }

    public static CallUiComponent getComponent() {
        return libraryComponent;
    }

    public static CallUiComponent initialize(@NonNull Context context) {
        libraryComponent = DaggerCallUiComponent.builder().applicationModule(new ApplicationModule(context)).build();
        return libraryComponent;
    }
}
