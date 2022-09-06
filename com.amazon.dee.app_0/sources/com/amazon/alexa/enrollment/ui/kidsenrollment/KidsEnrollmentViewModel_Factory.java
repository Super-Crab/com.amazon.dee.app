package com.amazon.alexa.enrollment.ui.kidsenrollment;

import android.content.Context;
import com.amazon.alexa.enrollment.utils.PermissionsHelper;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class KidsEnrollmentViewModel_Factory implements Factory<KidsEnrollmentViewModel> {
    private final Provider<Context> contextProvider;
    private final Provider<PermissionsHelper> permissionsHelperProvider;

    public KidsEnrollmentViewModel_Factory(Provider<Context> provider, Provider<PermissionsHelper> provider2) {
        this.contextProvider = provider;
        this.permissionsHelperProvider = provider2;
    }

    public static KidsEnrollmentViewModel_Factory create(Provider<Context> provider, Provider<PermissionsHelper> provider2) {
        return new KidsEnrollmentViewModel_Factory(provider, provider2);
    }

    public static KidsEnrollmentViewModel newKidsEnrollmentViewModel(Context context, PermissionsHelper permissionsHelper) {
        return new KidsEnrollmentViewModel(context, permissionsHelper);
    }

    public static KidsEnrollmentViewModel provideInstance(Provider<Context> provider, Provider<PermissionsHelper> provider2) {
        return new KidsEnrollmentViewModel(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public KidsEnrollmentViewModel mo10268get() {
        return provideInstance(this.contextProvider, this.permissionsHelperProvider);
    }
}
