package com.amazon.alexa.enrollment.module.library;

import android.content.Context;
import com.amazon.alexa.enrollment.ui.kidsenrollment.KidsEnrollmentViewModel;
import com.amazon.alexa.enrollment.utils.PermissionsHelper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class EnrollmentViewModelModule_ProvideKidsEnrollmentViewModelFactory implements Factory<KidsEnrollmentViewModel> {
    private final Provider<Context> contextProvider;
    private final EnrollmentViewModelModule module;
    private final Provider<PermissionsHelper> permissionsHelperProvider;

    public EnrollmentViewModelModule_ProvideKidsEnrollmentViewModelFactory(EnrollmentViewModelModule enrollmentViewModelModule, Provider<Context> provider, Provider<PermissionsHelper> provider2) {
        this.module = enrollmentViewModelModule;
        this.contextProvider = provider;
        this.permissionsHelperProvider = provider2;
    }

    public static EnrollmentViewModelModule_ProvideKidsEnrollmentViewModelFactory create(EnrollmentViewModelModule enrollmentViewModelModule, Provider<Context> provider, Provider<PermissionsHelper> provider2) {
        return new EnrollmentViewModelModule_ProvideKidsEnrollmentViewModelFactory(enrollmentViewModelModule, provider, provider2);
    }

    public static KidsEnrollmentViewModel provideInstance(EnrollmentViewModelModule enrollmentViewModelModule, Provider<Context> provider, Provider<PermissionsHelper> provider2) {
        return proxyProvideKidsEnrollmentViewModel(enrollmentViewModelModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static KidsEnrollmentViewModel proxyProvideKidsEnrollmentViewModel(EnrollmentViewModelModule enrollmentViewModelModule, Context context, PermissionsHelper permissionsHelper) {
        return (KidsEnrollmentViewModel) Preconditions.checkNotNull(enrollmentViewModelModule.provideKidsEnrollmentViewModel(context, permissionsHelper), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public KidsEnrollmentViewModel mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.permissionsHelperProvider);
    }
}
