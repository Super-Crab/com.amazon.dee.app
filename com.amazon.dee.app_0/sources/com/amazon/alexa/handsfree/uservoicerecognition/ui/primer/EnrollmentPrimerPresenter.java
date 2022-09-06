package com.amazon.alexa.handsfree.uservoicerecognition.ui.primer;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AlertDialog;
import com.amazon.alexa.enrollment.unified.speakerid.SpeakerIDSettingsProvider;
import com.amazon.alexa.handsfree.protocols.callback.CallbackErrorMetadata;
import com.amazon.alexa.handsfree.protocols.callback.ResponseCallback;
import com.amazon.alexa.handsfree.protocols.callback.ResultCallback;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.UVRModule;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVREnroller;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRVendorSettings;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UnifiedUVRVendorSettings;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentType;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentTypeResolver;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.UserInfo;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.uservoicerecognition.R;
import com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.EnrollmentPrimerContract;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
/* loaded from: classes8.dex */
public class EnrollmentPrimerPresenter {
    @VisibleForTesting
    static final String TAG = "EnrollmentPrimerPresenter";
    private final EnrollmentPrimerMetricsRecorder mEnrollmentPrimerMetricsRecorder;
    private final Lazy<EnrollmentTypeResolver> mEnrollmentTypeResolverLazy;
    private boolean mIsContinueButtonPressed;
    private boolean mIsEnrollmentCheckSuccessful;
    private boolean mIsEnrollmentStarting;
    private final SpeakerIDSettingsProvider mSpeakerIDSettingsProvider;
    private final UVREnroller mUVREnroller;
    private final UVRVendorSettings mUVRVendorSettings;
    private final EnrollmentPrimerContract.View mView;
    private VoicePrivacySettingState mVoicePrivacySettingState;

    /* renamed from: com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.EnrollmentPrimerPresenter$10  reason: invalid class name */
    /* loaded from: classes8.dex */
    static /* synthetic */ class AnonymousClass10 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$handsfree$uservoicerecognition$ui$primer$EnrollmentPrimerPresenter$VoicePrivacySettingState = new int[VoicePrivacySettingState.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$handsfree$uservoicerecognition$ui$primer$EnrollmentPrimerPresenter$VoicePrivacySettingState[VoicePrivacySettingState.REQUEST_IN_PROGRESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$handsfree$uservoicerecognition$ui$primer$EnrollmentPrimerPresenter$VoicePrivacySettingState[VoicePrivacySettingState.USER_OPTED_IN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$handsfree$uservoicerecognition$ui$primer$EnrollmentPrimerPresenter$VoicePrivacySettingState[VoicePrivacySettingState.REQUEST_FAILURE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes8.dex */
    public enum VoicePrivacySettingState {
        REQUEST_IN_PROGRESS,
        REQUEST_FAILURE,
        USER_OPTED_IN,
        USER_NOT_OPTED_IN
    }

    public EnrollmentPrimerPresenter(@NonNull EnrollmentPrimerContract.View view, @NonNull Context context) {
        this(view, UVRModule.INSTANCE.getUVRContract().getEnroller(), UVRModule.INSTANCE.getUVRContract().getVendorSettings(), new EnrollmentPrimerMetricsRecorder(context), new SpeakerIDSettingsProvider(context), ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(context, FalcoProtocolComponent.class)).enrollmentTypeResolverLazy());
    }

    private boolean isConnectedToNetwork(@NonNull Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void checkUserOptedInForVoicePrivacySettings() {
        Log.i(TAG, "Check use opt in voice privacy setting");
        this.mSpeakerIDSettingsProvider.hasUserOptedInForVoicePrivacySettings(new ResultCallback<Boolean>() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.EnrollmentPrimerPresenter.9
            @Override // com.amazon.alexa.handsfree.protocols.callback.ResultCallback
            public void onError(@NonNull CallbackErrorMetadata callbackErrorMetadata) {
                Log.i(EnrollmentPrimerPresenter.TAG, "Check use opt in voice privacy setting failed");
                EnrollmentPrimerPresenter.this.setVoicePrivacySettingState(VoicePrivacySettingState.REQUEST_FAILURE);
                if (EnrollmentPrimerPresenter.this.getIsEnrollmentStarting()) {
                    EnrollmentPrimerPresenter.this.continueEnrollment();
                }
            }

            @Override // com.amazon.alexa.handsfree.protocols.callback.ResultCallback
            public void onResult(@NonNull Boolean bool) {
                String str = EnrollmentPrimerPresenter.TAG;
                Log.i(str, "onVoicePrivacySettingsCheckResult " + bool);
                EnrollmentPrimerPresenter.this.setVoicePrivacySettingState(bool.booleanValue() ? VoicePrivacySettingState.USER_OPTED_IN : VoicePrivacySettingState.USER_NOT_OPTED_IN);
                if (EnrollmentPrimerPresenter.this.getIsEnrollmentStarting()) {
                    EnrollmentPrimerPresenter.this.continueEnrollment();
                }
            }
        });
    }

    public void continueButtonOnClick(@NonNull Context context) {
        setIsContinueButtonPressed(true);
        if (getIsEnrollmentStarting()) {
            return;
        }
        if (!getIsEnrollmentCheckSuccessful() && !is1PSVDecoupledEnrollment()) {
            Log.i(TAG, "Continue button clicked, but enrollment check is not complete.");
            this.mView.showLoading();
            this.mEnrollmentPrimerMetricsRecorder.logGettingReadyPageLoadMetric();
            return;
        }
        Log.i(TAG, "inside primerContinueButtonOnClick");
        this.mEnrollmentPrimerMetricsRecorder.logContinueButtonClickMetric();
        Log.i(TAG, "logged continue button click");
        handleContinueAction(context);
    }

    void continueEnrollment() {
        Log.i(TAG, "StartUserVoiceEnrollment called");
        this.mView.showLoading();
        this.mEnrollmentPrimerMetricsRecorder.logGettingReadyPageLoadMetric();
        setIsEnrollmentStarting(true);
        VoicePrivacySettingState voicePrivacySettingState = getVoicePrivacySettingState();
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("VoicePrivacySettingState: ");
        outline107.append(voicePrivacySettingState.name());
        Log.i(str, outline107.toString());
        int ordinal = voicePrivacySettingState.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                this.mView.showInternetAlertDialogWithRetry();
                this.mView.hideLoading();
            } else if (ordinal != 2) {
                this.mUVREnroller.startUserVoiceEnrollment(UserInfo.DEFAULT_USER, new ResponseCallback() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.EnrollmentPrimerPresenter.1
                    @Override // com.amazon.alexa.handsfree.protocols.callback.ResponseCallback
                    public void onError(@NonNull CallbackErrorMetadata callbackErrorMetadata) {
                        EnrollmentPrimerPresenter.this.mView.hideLoading();
                        Log.e(EnrollmentPrimerPresenter.TAG, String.format("startUserVoiceEnrollment error: %s", callbackErrorMetadata.getErrorMessage()));
                        EnrollmentPrimerPresenter.this.mEnrollmentPrimerMetricsRecorder.logStartUserVoiceEnrollmentErrorMetric();
                        EnrollmentPrimerPresenter.this.mView.showStartEnrollmentError();
                        EnrollmentPrimerPresenter.this.setIsEnrollmentStarting(false);
                    }

                    @Override // com.amazon.alexa.handsfree.protocols.callback.ResponseCallback
                    public void onSuccess() {
                        Log.d(EnrollmentPrimerPresenter.TAG, "startUserVoiceEnrollment onSuccess called.");
                        EnrollmentPrimerPresenter.this.mEnrollmentPrimerMetricsRecorder.logStartUserVoiceEnrollmentSuccessMetric();
                        EnrollmentPrimerPresenter.this.mView.continueEnrollment();
                    }
                });
            } else {
                this.mView.showStartEnrollmentVoicePrivacyOptedIn();
            }
        }
    }

    AlertDialog.Builder getAlertDialogBuilder(@NonNull Context context) {
        return new AlertDialog.Builder(context);
    }

    public EnrollmentPrimerMetricsRecorder getEnrollmentPrimerMetricsRecorder() {
        return this.mEnrollmentPrimerMetricsRecorder;
    }

    AlertDialog.Builder getInternetAlertDialogBuilder(@NonNull Context context) {
        return getAlertDialogBuilder(context).setTitle(R.string.primer_internet_alert_dialog_title).setMessage(R.string.primer_internet_alert_dialog_description).setPositiveButton(R.string.primer_internet_alert_dialog_confirm_button, new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.EnrollmentPrimerPresenter.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(@NonNull DialogInterface dialogInterface, int i) {
                Log.i(EnrollmentPrimerPresenter.TAG, "Internet AlertDialog onClick: ok button");
                EnrollmentPrimerPresenter.this.mEnrollmentPrimerMetricsRecorder.logInternetDialogPositiveClickMetric();
            }
        }).setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.EnrollmentPrimerPresenter.2
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(@NonNull DialogInterface dialogInterface) {
                EnrollmentPrimerPresenter.this.mEnrollmentPrimerMetricsRecorder.logInternetDialogDismissClickMetric();
            }
        });
    }

    AlertDialog.Builder getInternetAlertDialogBuilderWithRetry(@NonNull Context context) {
        return getAlertDialogBuilder(context).setTitle(R.string.primer_internet_alert_dialog_title).setMessage(R.string.primer_internet_alert_dialog_description).setPositiveButton(R.string.uvr_steps_internet_alert_dialog_retry_button, new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.EnrollmentPrimerPresenter.5
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(@NonNull DialogInterface dialogInterface, int i) {
                Log.i(EnrollmentPrimerPresenter.TAG, "Internet AlertDialog onClick: retry button");
                EnrollmentPrimerPresenter.this.mEnrollmentPrimerMetricsRecorder.logInternetDialogPositiveClickMetric();
                if (!EnrollmentPrimerPresenter.this.is1PSVDecoupledEnrollment() && !EnrollmentPrimerPresenter.this.getIsEnrollmentCheckSuccessful()) {
                    Log.e(EnrollmentPrimerPresenter.TAG, "Retrying fetchEnrollmentStatus...");
                    EnrollmentPrimerPresenter.this.mView.fetchEnrollmentStatus();
                }
                if (EnrollmentPrimerPresenter.this.getVoicePrivacySettingState() == VoicePrivacySettingState.REQUEST_FAILURE) {
                    Log.e(EnrollmentPrimerPresenter.TAG, "Retrying getVoicePrivacySettingState...");
                    EnrollmentPrimerPresenter.this.checkUserOptedInForVoicePrivacySettings();
                }
            }
        }).setNegativeButton(R.string.skip, new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.EnrollmentPrimerPresenter.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(@NonNull DialogInterface dialogInterface, int i) {
                Log.i(EnrollmentPrimerPresenter.TAG, "Internet AlertDialog onClick: skip button");
                EnrollmentPrimerPresenter.this.mEnrollmentPrimerMetricsRecorder.logInternetDialogNegativeClickMetric();
                EnrollmentPrimerPresenter.this.mView.skipEnrollment();
            }
        }).setCancelable(false);
    }

    public boolean getIsContinueButtonPressed() {
        return this.mIsContinueButtonPressed;
    }

    public boolean getIsEnrollmentCheckSuccessful() {
        return this.mIsEnrollmentCheckSuccessful;
    }

    @VisibleForTesting
    boolean getIsEnrollmentStarting() {
        return this.mIsEnrollmentStarting;
    }

    AlertDialog.Builder getSkipButtonAlertDialogBuilder(@NonNull Context context) {
        return getAlertDialogBuilder(context).setTitle(R.string.primer_skip_alert_dialog_title).setMessage(R.string.primer_skip_alert_dialog_description).setPositiveButton(R.string.primer_skip_alert_dialog_positive_button, new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.EnrollmentPrimerPresenter.8
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(@NonNull DialogInterface dialogInterface, int i) {
                Log.i(EnrollmentPrimerPresenter.TAG, "Skip AlertDialog onClick: continue button");
                EnrollmentPrimerPresenter.this.mEnrollmentPrimerMetricsRecorder.logSkipDialogPositiveButtonClickMetric();
                EnrollmentPrimerPresenter.this.mView.skipEnrollment();
            }
        }).setNegativeButton(R.string.primer_skip_alert_dialog_negative_button, new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.EnrollmentPrimerPresenter.7
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(@NonNull DialogInterface dialogInterface, int i) {
                Log.i(EnrollmentPrimerPresenter.TAG, "Skip AlertDialog onClick: cancel button");
                EnrollmentPrimerPresenter.this.mEnrollmentPrimerMetricsRecorder.logSkipDialogNegativeButtonClickMetric();
            }
        }).setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.EnrollmentPrimerPresenter.6
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(@NonNull DialogInterface dialogInterface) {
                EnrollmentPrimerPresenter.this.mEnrollmentPrimerMetricsRecorder.logSkipDialogDismissClickMetric();
            }
        });
    }

    @VisibleForTesting
    VoicePrivacySettingState getVoicePrivacySettingState() {
        return this.mVoicePrivacySettingState;
    }

    @VisibleForTesting
    void handleContinueAction(@NonNull Context context) {
        if (!is1PSVDecoupledEnrollment() && !isConnectedToNetwork(context)) {
            showInternetAlertDialog(context);
        } else {
            continueEnrollment();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public boolean is1PSVDecoupledEnrollment() {
        return this.mEnrollmentTypeResolverLazy.mo358get().getEnrollmentType().equals(EnrollmentType._1PSV_DECOUPLED);
    }

    public void isSpeakerIDEnrolled(@NonNull ResultCallback<Boolean> resultCallback) {
        Log.i(TAG, "querying voice profile exists status...");
        if (this.mUVRVendorSettings instanceof UnifiedUVRVendorSettings) {
            Log.d(TAG, "IS instanceof UnifiedUVREnroller");
            ((UnifiedUVRVendorSettings) this.mUVRVendorSettings).isSpeakerIDEnrolled(resultCallback);
            return;
        }
        resultCallback.onResult(false);
        Log.d(TAG, "Not checking if voice profile exists because this device will not do UnifiedEnrollment");
    }

    public void setIsContinueButtonPressed(@NonNull boolean z) {
        this.mIsContinueButtonPressed = z;
    }

    public void setIsEnrollmentCheckSuccessful(@NonNull boolean z) {
        this.mIsEnrollmentCheckSuccessful = z;
    }

    @VisibleForTesting
    void setIsEnrollmentStarting(boolean z) {
        this.mIsEnrollmentStarting = z;
    }

    @VisibleForTesting
    void setVoicePrivacySettingState(VoicePrivacySettingState voicePrivacySettingState) {
        this.mVoicePrivacySettingState = voicePrivacySettingState;
    }

    void showInternetAlertDialog(@NonNull Context context) {
        this.mEnrollmentPrimerMetricsRecorder.logInternetDialogShownMetric();
        getInternetAlertDialogBuilder(context).show();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void showInternetAlertDialogWithRetry(@NonNull Context context) {
        this.mEnrollmentPrimerMetricsRecorder.logInternetDialogShownMetric();
        getInternetAlertDialogBuilderWithRetry(context).show();
    }

    public void skipButtonOnClick(@NonNull Context context) {
        this.mEnrollmentPrimerMetricsRecorder.logSkipButtonClickMetric();
        getSkipButtonAlertDialogBuilder(context).show();
    }

    public EnrollmentPrimerPresenter(@NonNull EnrollmentPrimerContract.View view, @NonNull UVREnroller uVREnroller, @NonNull UVRVendorSettings uVRVendorSettings, @NonNull EnrollmentPrimerMetricsRecorder enrollmentPrimerMetricsRecorder, @NonNull SpeakerIDSettingsProvider speakerIDSettingsProvider, @NonNull Lazy<EnrollmentTypeResolver> lazy) {
        this.mVoicePrivacySettingState = VoicePrivacySettingState.REQUEST_IN_PROGRESS;
        this.mIsEnrollmentCheckSuccessful = false;
        this.mIsContinueButtonPressed = false;
        this.mView = view;
        this.mUVREnroller = uVREnroller;
        this.mUVRVendorSettings = uVRVendorSettings;
        this.mEnrollmentPrimerMetricsRecorder = enrollmentPrimerMetricsRecorder;
        this.mSpeakerIDSettingsProvider = speakerIDSettingsProvider;
        this.mEnrollmentTypeResolverLazy = lazy;
    }
}
