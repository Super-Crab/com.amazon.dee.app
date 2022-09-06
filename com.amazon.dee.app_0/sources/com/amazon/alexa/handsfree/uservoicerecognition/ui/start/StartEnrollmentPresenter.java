package com.amazon.alexa.handsfree.uservoicerecognition.ui.start;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.enrollment.unified.speakerid.SpeakerIDSettingsProvider;
import com.amazon.alexa.handsfree.protocols.callback.CallbackErrorMetadata;
import com.amazon.alexa.handsfree.protocols.callback.ResponseCallback;
import com.amazon.alexa.handsfree.protocols.callback.ResultCallback;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.UVRModule;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRContract;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVREnroller;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRVendorSettings;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentType;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentTypeResolver;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.UserInfo;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.uservoicerecognition.ui.PartnerDisclaimerProvider;
import com.amazon.alexa.handsfree.uservoicerecognition.ui.start.StartEnrollmentContract;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
/* loaded from: classes8.dex */
public class StartEnrollmentPresenter {
    private static final String DEFAULT_DISCLAIMER_VALUE = "<oem_name>";
    private static final String TAG = "StartEnrollmentPresenter";
    private final Context mContext;
    private final Lazy<EnrollmentTypeResolver> mEnrollmentTypeResolverLazy;
    private boolean mIsEnrollmentStarting;
    private final SpeakerIDSettingsProvider mSpeakerIDSettingsProvider;
    private final StartEnrollmentMetricsRecorder mStartEnrollmentMetricsRecorder;
    private final UVREnroller mUVREnroller;
    private final UVRVendorSettings mUVRVendorSettings;
    private final StartEnrollmentContract.View mView;
    private VoicePrivacySettingState mVoicePrivacySettingState;

    /* renamed from: com.amazon.alexa.handsfree.uservoicerecognition.ui.start.StartEnrollmentPresenter$3  reason: invalid class name */
    /* loaded from: classes8.dex */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$handsfree$uservoicerecognition$ui$start$StartEnrollmentPresenter$VoicePrivacySettingState = new int[VoicePrivacySettingState.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$handsfree$uservoicerecognition$ui$start$StartEnrollmentPresenter$VoicePrivacySettingState[VoicePrivacySettingState.REQUEST_IN_PROGRESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$handsfree$uservoicerecognition$ui$start$StartEnrollmentPresenter$VoicePrivacySettingState[VoicePrivacySettingState.USER_OPTED_IN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$handsfree$uservoicerecognition$ui$start$StartEnrollmentPresenter$VoicePrivacySettingState[VoicePrivacySettingState.REQUEST_FAILURE.ordinal()] = 3;
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

    /* JADX INFO: Access modifiers changed from: package-private */
    public StartEnrollmentPresenter(@NonNull StartEnrollmentContract.View view, @NonNull Context context, @NonNull StartEnrollmentMetricsRecorder startEnrollmentMetricsRecorder) {
        this(view, context, UVRModule.INSTANCE.getUVRContract(), startEnrollmentMetricsRecorder, ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(context, FalcoProtocolComponent.class)).enrollmentTypeResolverLazy(), new SpeakerIDSettingsProvider(context));
    }

    public void checkUserOptedInForVoicePrivacySettings() {
        Log.i(TAG, "Check use opt in voice privacy setting");
        if (!is1PSVDecoupledEnrollment()) {
            setVoicePrivacySettingState(VoicePrivacySettingState.USER_NOT_OPTED_IN);
        } else {
            this.mSpeakerIDSettingsProvider.hasUserOptedInForVoicePrivacySettings(new ResultCallback<Boolean>() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.start.StartEnrollmentPresenter.2
                @Override // com.amazon.alexa.handsfree.protocols.callback.ResultCallback
                public void onError(@NonNull CallbackErrorMetadata callbackErrorMetadata) {
                    Log.i(StartEnrollmentPresenter.TAG, "Check use opt in voice privacy setting failed");
                    StartEnrollmentPresenter.this.setVoicePrivacySettingState(VoicePrivacySettingState.REQUEST_FAILURE);
                    if (StartEnrollmentPresenter.this.getIsEnrollmentStarting()) {
                        StartEnrollmentPresenter.this.continueEnrollment();
                    }
                }

                @Override // com.amazon.alexa.handsfree.protocols.callback.ResultCallback
                public void onResult(@NonNull Boolean bool) {
                    String str = StartEnrollmentPresenter.TAG;
                    Log.i(str, "onVoicePrivacySettingsCheckResult " + bool);
                    StartEnrollmentPresenter.this.setVoicePrivacySettingState(bool.booleanValue() ? VoicePrivacySettingState.USER_OPTED_IN : VoicePrivacySettingState.USER_NOT_OPTED_IN);
                    if (StartEnrollmentPresenter.this.getIsEnrollmentStarting()) {
                        StartEnrollmentPresenter.this.continueEnrollment();
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void continueEnrollment() {
        setIsEnrollmentStarting(true);
        VoicePrivacySettingState voicePrivacySettingState = getVoicePrivacySettingState();
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("VoicePrivacySettingState: ");
        outline107.append(voicePrivacySettingState.name());
        Log.i(str, outline107.toString());
        int ordinal = voicePrivacySettingState.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                this.mView.showStartEnrollmentError();
            } else if (ordinal != 2) {
                this.mUVREnroller.startUserVoiceEnrollment(UserInfo.DEFAULT_USER, new ResponseCallback() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.start.StartEnrollmentPresenter.1
                    @Override // com.amazon.alexa.handsfree.protocols.callback.ResponseCallback
                    public void onError(@NonNull CallbackErrorMetadata callbackErrorMetadata) {
                        String str2 = StartEnrollmentPresenter.TAG;
                        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("startUserVoiceEnrollment onError called with reason: ");
                        outline1072.append(callbackErrorMetadata.getErrorMessage());
                        Log.d(str2, outline1072.toString());
                        StartEnrollmentPresenter.this.mStartEnrollmentMetricsRecorder.logUserVoiceEnrollmentError(callbackErrorMetadata, "UVREnrollerStartEnrollment");
                        StartEnrollmentPresenter.this.mView.showStartEnrollmentError();
                        StartEnrollmentPresenter.this.setIsEnrollmentStarting(false);
                    }

                    @Override // com.amazon.alexa.handsfree.protocols.callback.ResponseCallback
                    public void onSuccess() {
                        Log.d(StartEnrollmentPresenter.TAG, "startUserVoiceEnrollment onSuccess called.");
                        StartEnrollmentPresenter.this.mView.continueEnrollment();
                    }
                });
            } else {
                this.mView.showStartEnrollmentVoicePrivacyOptedIn();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    @StringRes
    public Integer getDisclaimerUri(@NonNull String str) {
        PartnerDisclaimerProvider partnerDisclaimerProvider = getPartnerDisclaimerProvider(str);
        if (partnerDisclaimerProvider == null) {
            return null;
        }
        return Integer.valueOf(partnerDisclaimerProvider.getDisclaimerUriID());
    }

    @VisibleForTesting
    boolean getIsEnrollmentStarting() {
        return this.mIsEnrollmentStarting;
    }

    @Nullable
    @VisibleForTesting
    PartnerDisclaimerProvider getPartnerDisclaimerProvider(@NonNull String str) {
        PartnerDisclaimerProvider[] values;
        for (PartnerDisclaimerProvider partnerDisclaimerProvider : PartnerDisclaimerProvider.values()) {
            if (partnerDisclaimerProvider.getVoiceAppPackageName().equals(str)) {
                return partnerDisclaimerProvider;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public String getPartnerName(@NonNull String str) {
        PartnerDisclaimerProvider partnerDisclaimerProvider = getPartnerDisclaimerProvider(str);
        return partnerDisclaimerProvider == null ? DEFAULT_DISCLAIMER_VALUE : partnerDisclaimerProvider.getPartnerName();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getVoiceAppPackageName() {
        return UVRModule.INSTANCE.getVoiceAppPackageName();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public String getVoiceApplicationName(@NonNull Context context) {
        return UVRModule.INSTANCE.getVoiceApplicationName(context);
    }

    @VisibleForTesting
    VoicePrivacySettingState getVoicePrivacySettingState() {
        return this.mVoicePrivacySettingState;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public boolean is1PSVDecoupledEnrollment() {
        return this.mEnrollmentTypeResolverLazy.mo358get().getEnrollmentType().equals(EnrollmentType._1PSV_DECOUPLED);
    }

    @VisibleForTesting
    void setIsEnrollmentStarting(boolean z) {
        this.mIsEnrollmentStarting = z;
    }

    @VisibleForTesting
    void setVoicePrivacySettingState(VoicePrivacySettingState voicePrivacySettingState) {
        this.mVoicePrivacySettingState = voicePrivacySettingState;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean shouldShowContinueConfirmationDialog() {
        return this.mUVRVendorSettings.shouldShowContinueConfirmationDialog();
    }

    @VisibleForTesting
    StartEnrollmentPresenter(@NonNull StartEnrollmentContract.View view, @NonNull Context context, @NonNull UVRContract uVRContract, @NonNull StartEnrollmentMetricsRecorder startEnrollmentMetricsRecorder, @NonNull Lazy<EnrollmentTypeResolver> lazy, @NonNull SpeakerIDSettingsProvider speakerIDSettingsProvider) {
        this.mVoicePrivacySettingState = VoicePrivacySettingState.REQUEST_IN_PROGRESS;
        this.mView = view;
        this.mUVREnroller = uVRContract.getEnroller();
        this.mUVRVendorSettings = uVRContract.getVendorSettings();
        this.mContext = context;
        this.mStartEnrollmentMetricsRecorder = startEnrollmentMetricsRecorder;
        this.mEnrollmentTypeResolverLazy = lazy;
        this.mSpeakerIDSettingsProvider = speakerIDSettingsProvider;
    }
}
