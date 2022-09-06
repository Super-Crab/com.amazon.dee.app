package com.amazon.alexa.sharing.presenter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.sharing.Constants;
import com.amazon.alexa.sharing.comms.CommsMetricsEmitter;
import com.amazon.comms.log.CommsLogger;
import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
import java.util.Collections;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes10.dex */
public class FallbackStrategyPresenter {
    private static final String METRICS_FALL_BACK_TO_HOME_PAGE_ATTEMPT = "comms.cx.%s.FallbackStrategyPresenter.routeHomeFallbackAttempt.count";
    private static final String METRICS_FALL_BACK_TO_HOME_PAGE_FAIL = "comms.cx.%s.FallbackStrategyPresenter.routeHomeFallbackFail.count";
    private static final String METRICS_FALL_BACK_TO_HOME_PAGE_SUCCESS = "comms.cx.%s.FallbackStrategyPresenter.routeHomeFallbackSuccess.count";
    private CommsMetricsEmitter metricsEmitter;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, FallbackStrategyPresenter.class);
    private static final String CLZ_NAME = FallbackStrategyPresenter.class.getSimpleName();

    @Inject
    public FallbackStrategyPresenter(Lazy<AlexaCommsCoreMetricsService> lazy) {
        this.metricsEmitter = new CommsMetricsEmitter(lazy, CLZ_NAME);
    }

    @VisibleForTesting
    Intent createIntentForMainActivity(@NonNull Context context) {
        try {
            return new Intent(context, Class.forName("com.amazon.dee.app.ui.main.MainActivity"));
        } catch (ClassNotFoundException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Main activity not found: ");
            outline107.append(e.getCause());
            throw new IllegalArgumentException(outline107.toString());
        }
    }

    public void showErrorToUser(@NonNull Context context, @NonNull String str) {
        LOG.w("The user was shown this message as the app redirected to home screen: %s", str);
        showErrorToUser(context, str, new Handler(Looper.getMainLooper()));
    }

    @VisibleForTesting
    Intent startMainActivity(@NonNull Context context, @NonNull String str) {
        Intent createIntentForMainActivity = createIntentForMainActivity(context);
        createIntentForMainActivity.setData(Uri.parse(str));
        createIntentForMainActivity.setFlags(268435456);
        createIntentForMainActivity.setAction("android.intent.action.VIEW");
        createIntentForMainActivity.addCategory("android.intent.category.DEFAULT");
        context.startActivity(createIntentForMainActivity);
        return createIntentForMainActivity;
    }

    public Intent startMainActivityWithRouteToHome(@NonNull String str, @NonNull Context context) {
        this.metricsEmitter.recordOccurrenceMetric(String.format(METRICS_FALL_BACK_TO_HOME_PAGE_ATTEMPT, str), Collections.EMPTY_MAP);
        try {
            Intent startMainActivity = startMainActivity(context, Constants.ALEXA_APP_HOME_PAGE_PATH);
            this.metricsEmitter.recordOccurrenceMetric(String.format(METRICS_FALL_BACK_TO_HOME_PAGE_SUCCESS, str), Collections.EMPTY_MAP);
            return startMainActivity;
        } catch (Throwable th) {
            this.metricsEmitter.recordOccurrenceMetric(String.format(METRICS_FALL_BACK_TO_HOME_PAGE_FAIL, str), Collections.EMPTY_MAP);
            throw th;
        }
    }

    @VisibleForTesting
    void showErrorToUser(@NonNull final Context context, @NonNull final String str, @NonNull Handler handler) {
        LOG.d("Showing toast message: %s", str);
        handler.post(new Runnable() { // from class: com.amazon.alexa.sharing.presenter.-$$Lambda$FallbackStrategyPresenter$-NuBbgE9N_58pAYVaxStiX6YAJg
            @Override // java.lang.Runnable
            public final void run() {
                Toast.makeText(context, str, 1).show();
            }
        });
    }
}
