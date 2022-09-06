package com.amazon.alexa.handsfree.uservoicerecognition.ui.finalstep;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.RadioGroup;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AlertDialog;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentType;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentTypeResolver;
import com.amazon.alexa.handsfree.settings.contract.handsfreesettings.PreferenceCallback;
import com.amazon.alexa.handsfree.settings.contract.handsfreesettings.ResponsePreferenceCallback;
import com.amazon.alexa.handsfree.settings.contract.handsfreesettings.lockscreen.LockScreenSettingContract;
import com.amazon.alexa.handsfree.ui.utils.AlertDialogBuilderFactory;
import com.amazon.alexa.handsfree.uservoicerecognition.R;
import dagger.Lazy;
/* loaded from: classes8.dex */
public class LockScreenSettingView implements LockScreenSettingContract.View, RadioGroup.OnCheckedChangeListener {
    private static final String TAG = LockScreenSettingView.class.getSimpleName();
    private final AlertDialogBuilderFactory mAlertDialogBuilderFactory;
    private final Lazy<EnrollmentTypeResolver> mEnrollmentTypeResolverLazy;
    private final FinalStepMetricsRecorder mFinalStepMetricsRecorder;
    private final RadioGroup mLockScreenRadio;
    private PreferenceCallback mPreferenceCallback;

    public LockScreenSettingView(@NonNull RadioGroup radioGroup, @NonNull Context context, @NonNull FinalStepMetricsRecorder finalStepMetricsRecorder) {
        this(radioGroup, finalStepMetricsRecorder, new AlertDialogBuilderFactory(context, R.style.AlertDialogBackground), ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(context, FalcoProtocolComponent.class)).enrollmentTypeResolverLazy());
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.lockscreen.LockScreenSettingContract.View
    public boolean isBlockSensitiveRequest() {
        return false;
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public void onCheckedChanged(@NonNull RadioGroup radioGroup, int i) {
        if (i == R.id.response_while_locked_off) {
            PreferenceCallback preferenceCallback = this.mPreferenceCallback;
            if (preferenceCallback != null) {
                preferenceCallback.onPreferenceChanged(false);
            }
            this.mFinalStepMetricsRecorder.logClickOnDisableRespondOnLockScreenRadio();
            return;
        }
        setActive(false);
        showConfirmationDialog();
        this.mFinalStepMetricsRecorder.logClickOnEnableRespondOnLockScreenRadio();
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.lockscreen.LockScreenSettingContract.View
    public void onVoiceProfileSettingChange(boolean z) {
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.lockscreen.LockScreenSettingContract.View
    public void setActive(String str) {
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.lockscreen.LockScreenSettingContract.View
    public void setActive(boolean z) {
        setSwitchValue(z);
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.lockscreen.LockScreenSettingContract.View
    public void setEnabled(boolean z) {
        this.mLockScreenRadio.setEnabled(z);
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.lockscreen.LockScreenSettingContract.View
    public void setRemotelyDisabled() {
        this.mLockScreenRadio.setVisibility(4);
    }

    @VisibleForTesting
    void setSwitchValue(boolean z) {
        this.mLockScreenRadio.setOnCheckedChangeListener(null);
        this.mLockScreenRadio.check(z ? R.id.response_while_locked_on : R.id.response_while_locked_off);
        this.mLockScreenRadio.setOnCheckedChangeListener(this);
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.lockscreen.LockScreenSettingContract.View
    public void setupListener(@NonNull PreferenceCallback preferenceCallback) {
        this.mPreferenceCallback = preferenceCallback;
        this.mPreferenceCallback.onPreferenceChanged(false);
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.lockscreen.LockScreenSettingContract.View
    public void setupListener(@NonNull ResponsePreferenceCallback responsePreferenceCallback) {
    }

    @VisibleForTesting
    void showConfirmationDialog() {
        EnrollmentTypeResolver mo358get = this.mEnrollmentTypeResolverLazy.mo358get();
        AlertDialog.Builder create = this.mAlertDialogBuilderFactory.create();
        if (mo358get != null && mo358get.getSpeakerVerificationEnrollmentType() == EnrollmentType._1PSV) {
            create.setMessage(R.string.uvr_enrollment_complete_dialog_message_edgesv);
        } else {
            create.setMessage(R.string.uvr_enrollment_complete_dialog_message);
        }
        create.setTitle(R.string.uvr_enrollment_complete_dialog_title).setPositiveButton(R.string.uvr_enrollment_complete_dialog_allow, new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.finalstep.LockScreenSettingView.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(@NonNull DialogInterface dialogInterface, int i) {
                if (LockScreenSettingView.this.mPreferenceCallback != null) {
                    LockScreenSettingView.this.mPreferenceCallback.onPreferenceChanged(true);
                }
                LockScreenSettingView.this.setActive(true);
                LockScreenSettingView.this.mFinalStepMetricsRecorder.logAllowRespondOnLockScreenDialog();
            }
        }).setNegativeButton(R.string.uvr_enrollment_complete_dialog_deny, new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.finalstep.LockScreenSettingView.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(@NonNull DialogInterface dialogInterface, int i) {
                LockScreenSettingView.this.mFinalStepMetricsRecorder.logDenyRespondOnLockScreenDialog();
            }
        }).setCancelable(false).show();
        this.mFinalStepMetricsRecorder.logShowConfirmationDialog();
    }

    @VisibleForTesting
    LockScreenSettingView(@NonNull RadioGroup radioGroup, @NonNull FinalStepMetricsRecorder finalStepMetricsRecorder, @NonNull AlertDialogBuilderFactory alertDialogBuilderFactory, @NonNull Lazy<EnrollmentTypeResolver> lazy) {
        this.mLockScreenRadio = radioGroup;
        this.mLockScreenRadio.setOnCheckedChangeListener(this);
        this.mFinalStepMetricsRecorder = finalStepMetricsRecorder;
        this.mEnrollmentTypeResolverLazy = lazy;
        this.mAlertDialogBuilderFactory = alertDialogBuilderFactory;
    }
}
