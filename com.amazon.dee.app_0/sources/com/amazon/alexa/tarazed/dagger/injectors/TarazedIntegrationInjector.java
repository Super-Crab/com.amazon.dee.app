package com.amazon.alexa.tarazed.dagger.injectors;

import android.content.Context;
import com.amazon.alexa.tarazed.dagger.components.DaggerTarazedIntegrationComponent;
import com.amazon.alexa.tarazed.dagger.components.TarazedIntegrationComponent;
import com.amazon.alexa.tarazed.dagger.modules.TarazedIntegrationModule;
import com.amazon.tarazed.dagger.injectors.GlobalInjector;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: TarazedIntegrationInjector.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/tarazed/dagger/injectors/TarazedIntegrationInjector;", "", "()V", "getComponent", "Lcom/amazon/alexa/tarazed/dagger/components/TarazedIntegrationComponent;", "context", "Landroid/content/Context;", "AlexaMobileAndroidTarazedIntegration_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes10.dex */
public final class TarazedIntegrationInjector {
    public static final TarazedIntegrationInjector INSTANCE = new TarazedIntegrationInjector();

    private TarazedIntegrationInjector() {
    }

    @NotNull
    public final TarazedIntegrationComponent getComponent(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        GlobalInjector.initComponent(context);
        TarazedIntegrationComponent build = DaggerTarazedIntegrationComponent.builder().globalComponent(GlobalInjector.getComponent()).tarazedIntegrationModule(new TarazedIntegrationModule(context)).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "DaggerTarazedIntegration…xt))\n            .build()");
        return build;
    }
}
