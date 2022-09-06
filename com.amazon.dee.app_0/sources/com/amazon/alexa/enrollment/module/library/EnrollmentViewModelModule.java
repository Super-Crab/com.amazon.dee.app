package com.amazon.alexa.enrollment.module.library;

import android.content.Context;
import com.amazon.alexa.enrollment.api.EnrollmentAPI;
import com.amazon.alexa.enrollment.metrics.EnrollmentMetricsRecorder;
import com.amazon.alexa.enrollment.ui.introduction.EnrollmentIntroductionViewModel;
import com.amazon.alexa.enrollment.ui.kidsenrollment.KidsEnrollmentViewModel;
import com.amazon.alexa.enrollment.ui.training.EnrollmentTrainingViewModel;
import com.amazon.alexa.enrollment.utils.PermissionsHelper;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes7.dex */
public class EnrollmentViewModelModule {
    @Provides
    public EnrollmentIntroductionViewModel provideEnrollmentIntroViewModel(Context context, EnrollmentAPI enrollmentAPI, PermissionsHelper permissionsHelper) {
        return new EnrollmentIntroductionViewModel(context, enrollmentAPI, permissionsHelper);
    }

    @Provides
    public EnrollmentTrainingViewModel provideEnrollmentTrainingViewModel(Context context, EnrollmentAPI enrollmentAPI, EnrollmentMetricsRecorder enrollmentMetricsRecorder) {
        return new EnrollmentTrainingViewModel(context, enrollmentAPI, enrollmentMetricsRecorder);
    }

    @Provides
    public KidsEnrollmentViewModel provideKidsEnrollmentViewModel(Context context, PermissionsHelper permissionsHelper) {
        return new KidsEnrollmentViewModel(context, permissionsHelper);
    }
}
