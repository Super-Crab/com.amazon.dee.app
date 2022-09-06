package com.amazon.alexa.enrollment.utils;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.amazon.alexa.enrollment.R;
import com.amazon.alexa.enrollment.metrics.MetricsConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes7.dex */
public final class ActivityUtils {
    private static final String TAG = GeneratedOutlineSupport1.outline39(ActivityUtils.class, GeneratedOutlineSupport1.outline107(MetricsConstants.VOICE_ENROLL_LOGGING_PREFIX));

    private ActivityUtils() {
    }

    public static void addFragmentToActivity(FragmentManager fragmentManager, Fragment fragment, int i) {
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.add(i, fragment);
        beginTransaction.commit();
    }

    public static String getEnrollmentContext(Activity activity) {
        Bundle extras = activity.getIntent().getExtras();
        if (extras != null) {
            return extras.getString(ActivityConstants.ENROLLMENT_CONTEXT);
        }
        return null;
    }

    public static void setTranslucent(Activity activity) {
        activity.getWindow().setFlags(67108864, 67108864);
        int i = Build.VERSION.SDK_INT;
        activity.getWindow().setStatusBarColor(activity.getColor(R.color.training_background));
    }
}
