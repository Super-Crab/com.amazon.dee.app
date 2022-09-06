package com.amazon.alexa.enrollment.module.library;

import android.content.Context;
import com.amazon.alexa.enrollment.api.EnrollmentAPI;
import com.amazon.alexa.enrollment.ui.introduction.EnrollmentIntroductionViewModel;
import com.amazon.alexa.enrollment.utils.PermissionsHelper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class EnrollmentViewModelModule_ProvideEnrollmentIntroViewModelFactory implements Factory<EnrollmentIntroductionViewModel> {
    private final Provider<Context> contextProvider;
    private final Provider<EnrollmentAPI> enrollmentAPIProvider;
    private final EnrollmentViewModelModule module;
    private final Provider<PermissionsHelper> permissionsHelperProvider;

    public EnrollmentViewModelModule_ProvideEnrollmentIntroViewModelFactory(EnrollmentViewModelModule enrollmentViewModelModule, Provider<Context> provider, Provider<EnrollmentAPI> provider2, Provider<PermissionsHelper> provider3) {
        this.module = enrollmentViewModelModule;
        this.contextProvider = provider;
        this.enrollmentAPIProvider = provider2;
        this.permissionsHelperProvider = provider3;
    }

    public static EnrollmentViewModelModule_ProvideEnrollmentIntroViewModelFactory create(EnrollmentViewModelModule enrollmentViewModelModule, Provider<Context> provider, Provider<EnrollmentAPI> provider2, Provider<PermissionsHelper> provider3) {
        return new EnrollmentViewModelModule_ProvideEnrollmentIntroViewModelFactory(enrollmentViewModelModule, provider, provider2, provider3);
    }

    public static EnrollmentIntroductionViewModel provideInstance(EnrollmentViewModelModule enrollmentViewModelModule, Provider<Context> provider, Provider<EnrollmentAPI> provider2, Provider<PermissionsHelper> provider3) {
        return proxyProvideEnrollmentIntroViewModel(enrollmentViewModelModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static EnrollmentIntroductionViewModel proxyProvideEnrollmentIntroViewModel(EnrollmentViewModelModule enrollmentViewModelModule, Context context, EnrollmentAPI enrollmentAPI, PermissionsHelper permissionsHelper) {
        return (EnrollmentIntroductionViewModel) Preconditions.checkNotNull(enrollmentViewModelModule.provideEnrollmentIntroViewModel(context, enrollmentAPI, permissionsHelper), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EnrollmentIntroductionViewModel mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.enrollmentAPIProvider, this.permissionsHelperProvider);
    }
}
