package com.amazon.alexa.fitness.sdk;

import android.content.Context;
import com.amazon.alexa.fitness.dagger.StaticReleasePackageComponentHolder;
import dagger.Subcomponent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: FitnessSdk.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/alexa/fitness/sdk/FitnessSdk;", "", "()V", "sessionManager", "Lcom/amazon/alexa/fitness/sdk/SessionManager;", "initialize", "", "context", "Landroid/content/Context;", "SubComponent", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class FitnessSdk {
    private SessionManager sessionManager;

    /* compiled from: FitnessSdk.kt */
    @Subcomponent
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lcom/amazon/alexa/fitness/sdk/FitnessSdk$SubComponent;", "", "sessionManager", "Lcom/amazon/alexa/fitness/sdk/SessionManager;", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public interface SubComponent {
        @NotNull
        SessionManager sessionManager();
    }

    public final void initialize(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        StaticReleasePackageComponentHolder.initializePackageComponent(context);
        this.sessionManager = StaticReleasePackageComponentHolder.getPackageComponent().fitnessSdkSubComponent().sessionManager();
    }
}
