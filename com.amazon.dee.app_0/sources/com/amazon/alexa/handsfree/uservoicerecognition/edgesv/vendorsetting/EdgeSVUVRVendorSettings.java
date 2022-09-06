package com.amazon.alexa.handsfree.uservoicerecognition.edgesv.vendorsetting;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.enrollment.unified.UnifiedSettingsProvider;
import com.amazon.alexa.handsfree.protocols.callback.CallbackErrorMetadata;
import com.amazon.alexa.handsfree.protocols.callback.ResponseCallback;
import com.amazon.alexa.handsfree.protocols.callback.ResultCallback;
import com.amazon.alexa.handsfree.protocols.settings.WakeWordSettingsManager;
import com.amazon.alexa.handsfree.protocols.settings.WakeWordSettingsManagerProvider;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UnifiedUVRVendorSettings;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.UserInfo;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.uservoicerecognition.edgesv.cache.EnrollmentStateCache;
import com.amazon.alexa.handsfree.uservoicerecognition.edgesv.connection.EdgeSVUVRConnector;
import com.amazon.alexa.handsfree.uservoicerecognition.edgesv.speakeridcheck.SpeakerIDCheckScheduler;
import com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes8.dex */
public class EdgeSVUVRVendorSettings implements UnifiedUVRVendorSettings {
    @VisibleForTesting
    static final long LONG_TIME_TO_LIVE = 86400000;
    @VisibleForTesting
    static final long SHORT_TIME_TO_LIVE = 120000;
    private static final String TAG = "EdgeSVVendorSettings";
    private boolean isRefreshingEnrollmentState;
    private final Context mContext;
    private final EdgeSVUVRConnector mEdgeSVUVRConnector;
    private final EnrollmentStateCache mEnrollmentStateCache;
    private final SpeakerIDCheckScheduler mSpeakerIDCheckScheduler;
    private final UnifiedSettingsProvider mUnifiedSettingsProvider;

    public EdgeSVUVRVendorSettings(@NonNull Context context, @NonNull SpeakerVerificationMetricsListener speakerVerificationMetricsListener, @NonNull EnrollmentStateCache enrollmentStateCache, @NonNull EdgeSVUVRConnector edgeSVUVRConnector) {
        this.isRefreshingEnrollmentState = false;
        this.mContext = context;
        this.mUnifiedSettingsProvider = new UnifiedSettingsProvider(context, speakerVerificationMetricsListener);
        this.mEnrollmentStateCache = enrollmentStateCache;
        this.mEdgeSVUVRConnector = edgeSVUVRConnector;
        this.mSpeakerIDCheckScheduler = new SpeakerIDCheckScheduler();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void disableHandsFree(@NonNull ResponseCallback responseCallback) {
        getWakeWordSettingsManager().setHandsFreeState(false, responseCallback, TAG);
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRVendorSettings
    public void disableAntiSpoofVerification(@NonNull ResponseCallback responseCallback) {
        responseCallback.onError(new CallbackErrorMetadata("Functionality not available."));
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRVendorSettings
    public void disableUVR(@NonNull UserInfo userInfo, @NonNull ResponseCallback responseCallback) {
        responseCallback.onSuccess();
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRVendorSettings
    public void enableAntiSpoofVerification(@NonNull ResponseCallback responseCallback) {
        responseCallback.onError(new CallbackErrorMetadata("Functionality not available."));
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRVendorSettings
    public void enableUVR(@NonNull UserInfo userInfo, @NonNull ResponseCallback responseCallback) {
        responseCallback.onSuccess();
        this.mSpeakerIDCheckScheduler.scheduleChecker(this.mContext);
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRVendorSettings
    @NonNull
    public List<UserInfo> getEnrolledUsers() {
        return new ArrayList();
    }

    @VisibleForTesting
    WakeWordSettingsManager getWakeWordSettingsManager() {
        return WakeWordSettingsManagerProvider.getInstance().get();
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRVendorSettings
    public boolean isAntiSpoofEnabled() {
        return false;
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRVendorSettings
    public boolean isEnrollmentResumeSupported() {
        return true;
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UnifiedUVRVendorSettings
    public void isSpeakerIDEnrolled(@NonNull ResultCallback<Boolean> resultCallback) {
        this.mUnifiedSettingsProvider.isSpeakerIDEnrolled(resultCallback);
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRVendorSettings
    public boolean isUVRAvailable() {
        return true;
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRVendorSettings
    public boolean isUVREnrolled(@NonNull UserInfo userInfo) {
        if (!this.mEnrollmentStateCache.isCacheExpired(120000L)) {
            return this.mEnrollmentStateCache.getEnrollmentState();
        }
        if (this.mEdgeSVUVRConnector.isBlocking()) {
            boolean isUVREnrolled = this.mUnifiedSettingsProvider.isUVREnrolled();
            if (!isUVREnrolled) {
                removeUVRModel();
            }
            this.mEnrollmentStateCache.setEnrollmentState(isUVREnrolled);
            return isUVREnrolled;
        }
        if (!this.isRefreshingEnrollmentState && this.mEnrollmentStateCache.isCacheExpired(86400000L)) {
            this.isRefreshingEnrollmentState = true;
            this.mUnifiedSettingsProvider.isUVREnrolled(new ResultCallback<Boolean>() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.edgesv.vendorsetting.EdgeSVUVRVendorSettings.1
                @Override // com.amazon.alexa.handsfree.protocols.callback.ResultCallback
                public void onError(@NonNull CallbackErrorMetadata callbackErrorMetadata) {
                    EdgeSVUVRVendorSettings.this.isRefreshingEnrollmentState = false;
                }

                @Override // com.amazon.alexa.handsfree.protocols.callback.ResultCallback
                public void onResult(@NonNull Boolean bool) {
                    if (!bool.booleanValue()) {
                        EdgeSVUVRVendorSettings.this.removeUVRModel();
                    }
                    EdgeSVUVRVendorSettings.this.mEnrollmentStateCache.setEnrollmentState(bool.booleanValue());
                    EdgeSVUVRVendorSettings.this.isRefreshingEnrollmentState = false;
                }
            });
        }
        return this.mEnrollmentStateCache.getEnrollmentState();
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRVendorSettings
    public boolean isUVRMandatory() {
        return true;
    }

    @VisibleForTesting
    void removeUVRModel() {
        removeUVRModel(UserInfo.DEFAULT_USER, new ResponseCallback() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.edgesv.vendorsetting.EdgeSVUVRVendorSettings.2
            @Override // com.amazon.alexa.handsfree.protocols.callback.ResponseCallback
            public void onError(@NonNull CallbackErrorMetadata callbackErrorMetadata) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onError: failed to remove UVR model. Reason: ");
                outline107.append(callbackErrorMetadata.getErrorMessage());
                Log.e(EdgeSVUVRVendorSettings.TAG, outline107.toString());
            }

            @Override // com.amazon.alexa.handsfree.protocols.callback.ResponseCallback
            public void onSuccess() {
                Log.i(EdgeSVUVRVendorSettings.TAG, "onSuccess: UVR model removed!");
            }
        });
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRVendorSettings
    public boolean shouldShowContinueConfirmationDialog() {
        return true;
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRVendorSettings
    public void removeUVRModel(@NonNull UserInfo userInfo, @NonNull final ResponseCallback responseCallback) {
        this.mUnifiedSettingsProvider.removeUVRModel(new ResponseCallback() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.edgesv.vendorsetting.EdgeSVUVRVendorSettings.3
            @Override // com.amazon.alexa.handsfree.protocols.callback.ResponseCallback
            public void onError(@NonNull CallbackErrorMetadata callbackErrorMetadata) {
                responseCallback.onError(callbackErrorMetadata);
            }

            @Override // com.amazon.alexa.handsfree.protocols.callback.ResponseCallback
            public void onSuccess() {
                EdgeSVUVRVendorSettings.this.mEnrollmentStateCache.setEnrollmentState(false);
                EdgeSVUVRVendorSettings.this.disableHandsFree(responseCallback);
            }
        });
    }

    @VisibleForTesting
    EdgeSVUVRVendorSettings(@NonNull Context context, @NonNull EnrollmentStateCache enrollmentStateCache, @NonNull EdgeSVUVRConnector edgeSVUVRConnector, @NonNull UnifiedSettingsProvider unifiedSettingsProvider, @NonNull SpeakerIDCheckScheduler speakerIDCheckScheduler) {
        this.isRefreshingEnrollmentState = false;
        this.mContext = context;
        this.mUnifiedSettingsProvider = unifiedSettingsProvider;
        this.mEnrollmentStateCache = enrollmentStateCache;
        this.mEdgeSVUVRConnector = edgeSVUVRConnector;
        this.mSpeakerIDCheckScheduler = speakerIDCheckScheduler;
    }
}
