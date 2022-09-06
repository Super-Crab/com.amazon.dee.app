package com.amazon.dee.app.dependencies;

import android.app.Activity;
import com.google.android.play.core.review.ReviewManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class MainModule_ProvideReviewManagerFactory implements Factory<ReviewManager> {
    private final Provider<Activity> activityProvider;
    private final MainModule module;

    public MainModule_ProvideReviewManagerFactory(MainModule mainModule, Provider<Activity> provider) {
        this.module = mainModule;
        this.activityProvider = provider;
    }

    public static MainModule_ProvideReviewManagerFactory create(MainModule mainModule, Provider<Activity> provider) {
        return new MainModule_ProvideReviewManagerFactory(mainModule, provider);
    }

    public static ReviewManager provideInstance(MainModule mainModule, Provider<Activity> provider) {
        return proxyProvideReviewManager(mainModule, provider.mo10268get());
    }

    public static ReviewManager proxyProvideReviewManager(MainModule mainModule, Activity activity) {
        return (ReviewManager) Preconditions.checkNotNull(mainModule.provideReviewManager(activity), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ReviewManager mo10268get() {
        return provideInstance(this.module, this.activityProvider);
    }
}
