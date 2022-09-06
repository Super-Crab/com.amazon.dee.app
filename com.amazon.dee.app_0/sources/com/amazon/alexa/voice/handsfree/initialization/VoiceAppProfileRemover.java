package com.amazon.alexa.voice.handsfree.initialization;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.handsfree.devices.constants.VoiceApp;
import com.amazon.alexa.handsfree.protocols.callback.CallbackErrorMetadata;
import com.amazon.alexa.handsfree.protocols.callback.ResponseCallback;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRConnector;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRContract;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentStatus;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentStatusManager;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.UserInfo;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.vendor.bridge.VendorAPIWrapper;
import com.amazon.alexa.handsfree.vendor.bridge.VendorAPIWrapperProvider;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
/* loaded from: classes11.dex */
public class VoiceAppProfileRemover {
    @VisibleForTesting
    static final String DELETE_LEFTOVER_UVR_PROFILE_METRIC = "DELETE_LEFTOVER_UVR_PROFILE";
    private static final long DELETE_PROFILE_DELAY_MILLIS = 6000;
    @VisibleForTesting
    static final Integer METRO_SKIP_VERSION = 31;
    private static final String TAG = VoiceAppProfileRemover.class.getSimpleName();
    private final AMPDInformationProvider mAmpdInformationProvider;
    private final EnrollmentStatusManager mEnrollmentStatusManager;
    private final MetricsBuilderProvider mMetricsBuilderProvider;
    private final ScheduledExecutorService mScheduledExecutorService;
    private ScheduledFuture<?> mScheduledFuture;
    private final VendorAPIWrapperProvider mVendorAPIWrapperProvider;

    @Inject
    public VoiceAppProfileRemover(@NonNull Context context) {
        this(VendorAPIWrapperProvider.getInstance(context), Executors.newSingleThreadScheduledExecutor(), MetricsBuilderProvider.getInstance(context), EnrollmentStatusManager.getInstance(context), AMPDInformationProvider.getInstance(context));
    }

    public void removeVoiceAppProfile(@NonNull final Context context) {
        if (VoiceApp.METRO.getPackageName().equals(this.mAmpdInformationProvider.getVoiceAppPackageName()) && METRO_SKIP_VERSION.equals(this.mAmpdInformationProvider.getVoiceAppVersion())) {
            Log.d(TAG, "Will not attempt to remove 3PSV Voice Profile due to HFA version being 1.2.00");
            return;
        }
        ScheduledFuture<?> scheduledFuture = this.mScheduledFuture;
        if (scheduledFuture != null && !scheduledFuture.isDone()) {
            Log.d(TAG, "Task is already scheduled");
            return;
        }
        VendorAPIWrapper supportedAPIWrapper = this.mVendorAPIWrapperProvider.getSupportedAPIWrapper();
        if (supportedAPIWrapper == null) {
            return;
        }
        final UVRContract uVRContract = supportedAPIWrapper.getUVRContract(context);
        final UVRConnector uVRConnector = uVRContract.getUVRConnector();
        uVRContract.getUVRConnector().startConnection(context, false);
        Log.d(TAG, "Scheduling task to remove 3PSV voice profile");
        final ResponseCallback responseCallback = new ResponseCallback() { // from class: com.amazon.alexa.voice.handsfree.initialization.VoiceAppProfileRemover.1
            @Override // com.amazon.alexa.handsfree.protocols.callback.ResponseCallback
            public void onError(@NonNull CallbackErrorMetadata callbackErrorMetadata) {
                Log.e(VoiceAppProfileRemover.TAG, "Failed to remove previously existing 3PSV voice profiles");
                VoiceAppProfileRemover.this.mMetricsBuilderProvider.newBuilder().withPercentileMetricFailure(VoiceAppProfileRemover.TAG, VoiceAppProfileRemover.DELETE_LEFTOVER_UVR_PROFILE_METRIC).emit(context);
                uVRConnector.endConnection(context);
            }

            @Override // com.amazon.alexa.handsfree.protocols.callback.ResponseCallback
            public void onSuccess() {
                Log.d(VoiceAppProfileRemover.TAG, "Successfully removed previously existing 3PSV voice profiles");
                VoiceAppProfileRemover.this.mMetricsBuilderProvider.newBuilder().withPercentileMetricSuccess(VoiceAppProfileRemover.TAG, VoiceAppProfileRemover.DELETE_LEFTOVER_UVR_PROFILE_METRIC).emit(context);
                uVRConnector.endConnection(context);
                VoiceAppProfileRemover.this.mEnrollmentStatusManager.setEnrollmentStatus(EnrollmentStatus.SETUP_NOT_SET);
            }
        };
        this.mScheduledFuture = this.mScheduledExecutorService.schedule(new Runnable() { // from class: com.amazon.alexa.voice.handsfree.initialization.-$$Lambda$VoiceAppProfileRemover$Hgua4V1VcaWmFJ8YiGEM7Hi3x-w
            @Override // java.lang.Runnable
            public final void run() {
                UVRContract.this.getVendorSettings().removeUVRModel(UserInfo.DEFAULT_USER, responseCallback);
            }
        }, DELETE_PROFILE_DELAY_MILLIS, TimeUnit.MILLISECONDS);
    }

    @VisibleForTesting
    VoiceAppProfileRemover(@NonNull VendorAPIWrapperProvider vendorAPIWrapperProvider, @NonNull ScheduledExecutorService scheduledExecutorService, @NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull EnrollmentStatusManager enrollmentStatusManager, @NonNull AMPDInformationProvider aMPDInformationProvider) {
        this.mVendorAPIWrapperProvider = vendorAPIWrapperProvider;
        this.mScheduledExecutorService = scheduledExecutorService;
        this.mMetricsBuilderProvider = metricsBuilderProvider;
        this.mEnrollmentStatusManager = enrollmentStatusManager;
        this.mAmpdInformationProvider = aMPDInformationProvider;
    }
}
