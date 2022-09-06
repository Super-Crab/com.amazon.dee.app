package com.amazon.alexa.enrollment.ui.introduction;

import android.content.Context;
import com.amazon.alexa.enrollment.api.EnrollmentAPI;
import com.amazon.alexa.enrollment.utils.PermissionsHelper;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class EnrollmentIntroductionViewModel_Factory implements Factory<EnrollmentIntroductionViewModel> {
    private final Provider<Context> contextProvider;
    private final Provider<EnrollmentAPI> enrollmentAPIProvider;
    private final Provider<PermissionsHelper> permissionsHelperProvider;

    public EnrollmentIntroductionViewModel_Factory(Provider<Context> provider, Provider<EnrollmentAPI> provider2, Provider<PermissionsHelper> provider3) {
        this.contextProvider = provider;
        this.enrollmentAPIProvider = provider2;
        this.permissionsHelperProvider = provider3;
    }

    public static EnrollmentIntroductionViewModel_Factory create(Provider<Context> provider, Provider<EnrollmentAPI> provider2, Provider<PermissionsHelper> provider3) {
        return new EnrollmentIntroductionViewModel_Factory(provider, provider2, provider3);
    }

    public static EnrollmentIntroductionViewModel newEnrollmentIntroductionViewModel(Context context, EnrollmentAPI enrollmentAPI, PermissionsHelper permissionsHelper) {
        return new EnrollmentIntroductionViewModel(context, enrollmentAPI, permissionsHelper);
    }

    public static EnrollmentIntroductionViewModel provideInstance(Provider<Context> provider, Provider<EnrollmentAPI> provider2, Provider<PermissionsHelper> provider3) {
        return new EnrollmentIntroductionViewModel(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EnrollmentIntroductionViewModel mo10268get() {
        return provideInstance(this.contextProvider, this.enrollmentAPIProvider, this.permissionsHelperProvider);
    }
}
