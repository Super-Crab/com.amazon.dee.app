package com.amazon.alexa.handsfree.uservoicerecognition.edgesv.speakeridcheck;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.JobIntentService;
import androidx.core.app.SafeDequeueJobIntentService;
import com.amazon.alexa.enrollment.unified.speakerid.SpeakerIDSettingsProvider;
import com.amazon.alexa.handsfree.protocols.Initializer;
import com.amazon.alexa.handsfree.protocols.InitializerProvider;
import com.amazon.alexa.handsfree.protocols.callback.CallbackErrorMetadata;
import com.amazon.alexa.handsfree.protocols.callback.ResponseCallback;
import com.amazon.alexa.handsfree.protocols.callback.ResultCallback;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentType;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentTypeResolver;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.UserInfo;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.uservoicerecognition.edgesv.EdgeSVUVRModule;
import com.amazon.alexa.handsfree.uservoicerecognition.edgesv.metrics.EdgeSVSpeakerIDCheckMetricsReporter;
import com.amazon.alexa.handsfree.uservoicerecognition.edgesv.vendorsetting.EdgeSVUVRVendorSettings;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
/* loaded from: classes8.dex */
public class SpeakerIDCheckJobIntentService extends SafeDequeueJobIntentService {
    private static final int JOB_ID = 32046;
    private static final String TAG = "SpeakerIDCheckService";
    private EdgeSVSpeakerIDCheckMetricsReporter mEdgeSVSpeakerIDCheckMetricsReporter;
    private EdgeSVUVRVendorSettings mEdgeSVUVRVendorSettings;
    private Lazy<EnrollmentTypeResolver> mEnrollmentTypeResolverLazy;
    private final Initializer mInitializer;
    private final SpeakerIDCheckScheduler mSpeakerIDCheckScheduler;
    private SpeakerIDSettingsProvider mSpeakerIDSettingsProvider;

    public SpeakerIDCheckJobIntentService() {
        this.mInitializer = InitializerProvider.getInitializer();
        this.mSpeakerIDCheckScheduler = new SpeakerIDCheckScheduler();
    }

    public static void enqueueWork(@NonNull Context context) {
        JobIntentService.enqueueWork(context, SpeakerIDCheckJobIntentService.class, (int) JOB_ID, new Intent());
    }

    @Override // androidx.core.app.SafeDequeueJobIntentService, androidx.core.app.JobIntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mEnrollmentTypeResolverLazy = ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(getApplicationContext(), FalcoProtocolComponent.class)).enrollmentTypeResolverLazy();
        this.mInitializer.initialize(this);
        this.mEdgeSVUVRVendorSettings = (EdgeSVUVRVendorSettings) EdgeSVUVRModule.getInstance().getUVRContract(this).getVendorSettings();
        this.mEdgeSVSpeakerIDCheckMetricsReporter = new EdgeSVSpeakerIDCheckMetricsReporter(this);
        this.mSpeakerIDSettingsProvider = new SpeakerIDSettingsProvider(this);
    }

    @Override // androidx.core.app.JobIntentService
    protected void onHandleWork(@NonNull Intent intent) {
        this.mEdgeSVSpeakerIDCheckMetricsReporter.sendSpeakerIDCheckStart();
        if (this.mEdgeSVUVRVendorSettings == null) {
            Log.e(TAG, "onHandleWork: mEdgeSVUVRVendorSettings is null!");
            return;
        }
        EnrollmentTypeResolver mo358get = this.mEnrollmentTypeResolverLazy.mo358get();
        if (mo358get != null && mo358get.getEnrollmentType() == EnrollmentType._1PSV_DECOUPLED) {
            Log.i(TAG, "Check if user opted in voice privacy setting.");
            remove1PSVDataIfUserOptedInForVoicePrivacySettings();
            return;
        }
        Log.i(TAG, "Check if speaker id exists.");
        remove1PSVDataIfSpeakerIDIsMissing();
    }

    @VisibleForTesting
    void remove1PSVData() {
        this.mEdgeSVUVRVendorSettings.removeUVRModel(UserInfo.DEFAULT_USER, new ResponseCallback() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.edgesv.speakeridcheck.SpeakerIDCheckJobIntentService.3
            @Override // com.amazon.alexa.handsfree.protocols.callback.ResponseCallback
            public void onError(@NonNull CallbackErrorMetadata callbackErrorMetadata) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onError: failed to remove UVR model. Reason: ");
                outline107.append(callbackErrorMetadata.getErrorMessage());
                Log.e(SpeakerIDCheckJobIntentService.TAG, outline107.toString());
                SpeakerIDCheckJobIntentService.this.mEdgeSVSpeakerIDCheckMetricsReporter.sendUVRModelDeleteFailure();
            }

            @Override // com.amazon.alexa.handsfree.protocols.callback.ResponseCallback
            public void onSuccess() {
                Log.i(SpeakerIDCheckJobIntentService.TAG, "onSuccess: local UVR model removed!");
                SpeakerIDCheckJobIntentService.this.mEdgeSVSpeakerIDCheckMetricsReporter.sendUVRModelDeleteSuccess();
            }
        });
    }

    @VisibleForTesting
    void remove1PSVDataIfSpeakerIDIsMissing() {
        this.mEdgeSVUVRVendorSettings.isSpeakerIDEnrolled(new ResultCallback<Boolean>() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.edgesv.speakeridcheck.SpeakerIDCheckJobIntentService.2
            @Override // com.amazon.alexa.handsfree.protocols.callback.ResultCallback
            public void onError(@NonNull CallbackErrorMetadata callbackErrorMetadata) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onError: failed to get speaker id enrollment status, reschedule the checker. Reason: ");
                outline107.append(callbackErrorMetadata.getErrorMessage());
                Log.e(SpeakerIDCheckJobIntentService.TAG, outline107.toString());
                SpeakerIDCheckJobIntentService.this.mSpeakerIDCheckScheduler.scheduleChecker(SpeakerIDCheckJobIntentService.this);
            }

            @Override // com.amazon.alexa.handsfree.protocols.callback.ResultCallback
            public void onResult(@Nullable Boolean bool) {
                if (!bool.booleanValue()) {
                    SpeakerIDCheckJobIntentService.this.remove1PSVData();
                    return;
                }
                Log.i(SpeakerIDCheckJobIntentService.TAG, "onResult: speaker ID still enrolled, schedule another checker.");
                SpeakerIDCheckJobIntentService.this.mSpeakerIDCheckScheduler.scheduleChecker(SpeakerIDCheckJobIntentService.this);
            }
        });
    }

    @VisibleForTesting
    void remove1PSVDataIfUserOptedInForVoicePrivacySettings() {
        this.mSpeakerIDSettingsProvider.hasUserOptedInForVoicePrivacySettings(new ResultCallback<Boolean>() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.edgesv.speakeridcheck.SpeakerIDCheckJobIntentService.1
            @Override // com.amazon.alexa.handsfree.protocols.callback.ResultCallback
            public void onError(@NonNull CallbackErrorMetadata callbackErrorMetadata) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Check use opt in voice privacy setting failed, reschedule the checker. Reason: ");
                outline107.append(callbackErrorMetadata.getErrorMessage());
                Log.e(SpeakerIDCheckJobIntentService.TAG, outline107.toString());
                SpeakerIDCheckJobIntentService.this.mSpeakerIDCheckScheduler.scheduleChecker(SpeakerIDCheckJobIntentService.this);
            }

            @Override // com.amazon.alexa.handsfree.protocols.callback.ResultCallback
            public void onResult(Boolean bool) {
                if (bool.booleanValue()) {
                    SpeakerIDCheckJobIntentService.this.remove1PSVData();
                    return;
                }
                Log.i(SpeakerIDCheckJobIntentService.TAG, "onResult: user not opted in voice privacy setting, schedule another checker.");
                SpeakerIDCheckJobIntentService.this.mSpeakerIDCheckScheduler.scheduleChecker(SpeakerIDCheckJobIntentService.this);
            }
        });
    }

    @VisibleForTesting
    SpeakerIDCheckJobIntentService(@NonNull Initializer initializer, @Nullable EdgeSVUVRVendorSettings edgeSVUVRVendorSettings, @NonNull SpeakerIDCheckScheduler speakerIDCheckScheduler, @NonNull EdgeSVSpeakerIDCheckMetricsReporter edgeSVSpeakerIDCheckMetricsReporter, @NonNull Lazy<EnrollmentTypeResolver> lazy, @NonNull SpeakerIDSettingsProvider speakerIDSettingsProvider) {
        this.mInitializer = initializer;
        this.mEdgeSVUVRVendorSettings = edgeSVUVRVendorSettings;
        this.mSpeakerIDCheckScheduler = speakerIDCheckScheduler;
        this.mEdgeSVSpeakerIDCheckMetricsReporter = edgeSVSpeakerIDCheckMetricsReporter;
        this.mEnrollmentTypeResolverLazy = lazy;
        this.mSpeakerIDSettingsProvider = speakerIDSettingsProvider;
    }
}
