package com.amazon.alexa.enrollment.unified.speakerid.provider;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.enrollment.unified.speakerid.api.EnrollmentAPI;
import com.amazon.alexa.enrollment.unified.speakerid.metrics.AmpdMetricsRecorder;
import com.amazon.alexa.enrollment.unified.speakerid.training.EnrollmentTrainingViewModel;
import com.amazon.alexa.handsfree.protocols.utils.AlexaLocaleStore;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.PersonIdProvider;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.http.CoralService;
import com.google.common.base.Optional;
import dagger.Lazy;
import java.util.Locale;
import okhttp3.OkHttpClient;
/* loaded from: classes7.dex */
public class SpeakerIDAPIProvider {
    public static final String EXTRA_VOICE_ENROLLMENT_CONTEXT = "VOX_ENROLLMENT_FROM_HANDS_FREE_SETUP";
    private static final String TAG = "SpkrIDEnrollmentProvidr";
    private final ComponentRegistry mComponentRegistry;
    private final Context mContext;

    public SpeakerIDAPIProvider(@NonNull Context context) {
        this(context, ComponentRegistry.getInstance());
    }

    @Nullable
    private AmpdMetricsRecorder provideEnrollmentMetricsRecorder() {
        final Optional optional = this.mComponentRegistry.get(Mobilytics.class);
        if (!optional.isPresent()) {
            Log.i(TAG, "Mobilytics is not in component registry");
            return null;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Mobilytics instance: ");
        outline107.append(optional.get());
        Log.i(TAG, outline107.toString());
        optional.getClass();
        AmpdMetricsRecorder ampdMetricsRecorder = new AmpdMetricsRecorder(new Lazy() { // from class: com.amazon.alexa.enrollment.unified.speakerid.provider.-$$Lambda$PeSQRb6BVzIuOZjTOH42rNGR7PA
            @Override // dagger.Lazy
            /* renamed from: get */
            public final Object mo358get() {
                return (Mobilytics) Optional.this.get();
            }
        });
        ampdMetricsRecorder.initializeMetricsContext(EXTRA_VOICE_ENROLLMENT_CONTEXT);
        return ampdMetricsRecorder;
    }

    @VisibleForTesting
    Locale getLocale() {
        Locale locale = new AlexaLocaleStore(this.mContext).getLocales().get(0);
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("The locale for EnrollmentAPI is ");
        outline107.append(locale.toLanguageTag());
        Log.d(TAG, outline107.toString());
        return locale;
    }

    @Nullable
    public EnrollmentAPI provideEnrollmentAPI() {
        Optional optional = this.mComponentRegistry.get(CoralService.class);
        if (!optional.isPresent()) {
            Log.e(TAG, "CoralService is not in component registry");
            return null;
        }
        Optional optional2 = this.mComponentRegistry.get(DeviceInformation.class);
        if (!optional2.isPresent()) {
            Log.e(TAG, "DeviceInformation is not in component registry");
            return null;
        }
        Optional optional3 = this.mComponentRegistry.get(PersonIdProvider.class);
        if (!optional3.isPresent()) {
            Log.e(TAG, "PersonIdProvider is not in component registry");
            return null;
        }
        Optional optional4 = this.mComponentRegistry.get(IdentityService.class);
        if (!optional4.isPresent()) {
            Log.e(TAG, "IdentityService is not in component registry");
            return null;
        }
        Optional optional5 = this.mComponentRegistry.get(EnvironmentService.class);
        if (!optional5.isPresent()) {
            Log.e(TAG, "EnvironmentService is not in component registry");
            return null;
        }
        AmpdMetricsRecorder provideEnrollmentMetricsRecorder = provideEnrollmentMetricsRecorder();
        if (provideEnrollmentMetricsRecorder == null) {
            Log.e(TAG, "Unable to create EnrollmentMetricsRecorder instance, returning null EnrollmentAPI");
            return null;
        }
        return new EnrollmentAPI((CoralService) optional.get(), (DeviceInformation) optional2.get(), (PersonIdProvider) optional3.get(), (IdentityService) optional4.get(), getLocale(), provideEnrollmentMetricsRecorder, (EnvironmentService) optional5.get(), new OkHttpClient());
    }

    @Nullable
    public EnrollmentTrainingViewModel provideEnrollmentTrainingViewModel() {
        AmpdMetricsRecorder provideEnrollmentMetricsRecorder = provideEnrollmentMetricsRecorder();
        if (provideEnrollmentMetricsRecorder == null) {
            Log.e(TAG, "Unable to create EnrollmentMetricsRecorder instance, returning null EnrollmentTrainingViewModel");
            return null;
        }
        EnrollmentAPI provideEnrollmentAPI = provideEnrollmentAPI();
        if (provideEnrollmentAPI == null) {
            Log.e(TAG, "Unable to create EnrollmentAPI instance, returning null");
            return null;
        }
        return new EnrollmentTrainingViewModel(provideEnrollmentAPI, provideEnrollmentMetricsRecorder);
    }

    @VisibleForTesting
    SpeakerIDAPIProvider(@NonNull Context context, @NonNull ComponentRegistry componentRegistry) {
        this.mContext = context;
        this.mComponentRegistry = componentRegistry;
    }
}
