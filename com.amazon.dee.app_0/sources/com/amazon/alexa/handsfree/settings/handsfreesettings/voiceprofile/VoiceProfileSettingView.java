package com.amazon.alexa.handsfree.settings.handsfreesettings.voiceprofile;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.text.Html;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AlertDialog;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.UVRModule;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentType;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentTypeResolver;
import com.amazon.alexa.handsfree.settings.R;
import com.amazon.alexa.handsfree.settings.contract.handsfreesettings.PreferenceCallback;
import com.amazon.alexa.handsfree.settings.contract.handsfreesettings.voiceprofile.VoiceProfileSettingContract;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import dagger.Lazy;
/* loaded from: classes8.dex */
public class VoiceProfileSettingView implements VoiceProfileSettingContract.View {
    private static final int FFFFFF = 16777215;
    @VisibleForTesting
    static final String UVR_ACTION = "com.amazon.alexa.handsfree.UVR";
    private final Context mContext;
    private final Preference mCreateProfileView;
    private final Lazy<EnrollmentTypeResolver> mEnrollmentTypeResolverLazy;
    private PreferenceCallback mLockScreenCallback;
    private final Preference mLockScreenView;
    private final PreferenceScreen mPreferenceScreen;
    private final Preference mRemoveProfileView;

    public VoiceProfileSettingView(@NonNull PreferenceScreen preferenceScreen, @NonNull Preference preference, @NonNull Preference preference2, @NonNull Preference preference3, @NonNull Context context) {
        this(preferenceScreen, preference, preference2, preference3, context, null, ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(context, FalcoProtocolComponent.class)).enrollmentTypeResolverLazy());
    }

    @VisibleForTesting
    void confirmDeleteProfile(@NonNull final PreferenceCallback preferenceCallback) {
        String string;
        EnrollmentTypeResolver mo358get = this.mEnrollmentTypeResolverLazy.mo358get();
        if (mo358get != null && mo358get.getSpeakerVerificationEnrollmentType() != EnrollmentType._3PSV && mo358get.getEnrollmentType() != EnrollmentType._1PSV_DECOUPLED) {
            preferenceCallback.onPreferenceChanged(true);
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext, R.style.AlertDialogBackground);
        builder.setMessage(Html.fromHtml(String.format("<p style=\"color:#%s\">%s</p>", Integer.toHexString(ThemeUtil.getColorFromAttribute(this.mContext, R.attr.mosaicNeutral10) & 16777215), this.mContext.getString(R.string.delete_profile_message))));
        if (mo358get.getEnrollmentType() == EnrollmentType._1PSV_DECOUPLED) {
            string = this.mContext.getString(R.string.delete_profile_title_decoupled);
        } else {
            Context context = this.mContext;
            string = context.getString(R.string.delete_profile_title, UVRModule.INSTANCE.getVoiceApplicationName(context));
        }
        builder.setTitle(string).setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.handsfree.settings.handsfreesettings.voiceprofile.VoiceProfileSettingView.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                preferenceCallback.onPreferenceChanged(true);
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.handsfree.settings.handsfreesettings.voiceprofile.VoiceProfileSettingView.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                preferenceCallback.onPreferenceChanged(false);
            }
        }).create().show();
    }

    @VisibleForTesting
    void displayToast(@StringRes final int i) {
        ((Activity) this.mContext).runOnUiThread(new Runnable() { // from class: com.amazon.alexa.handsfree.settings.handsfreesettings.voiceprofile.VoiceProfileSettingView.5
            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(VoiceProfileSettingView.this.mContext, i, 0).show();
            }
        });
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.voiceprofile.VoiceProfileSettingContract.View
    public void setMandatoryVoiceProfileNotEnrolled() {
        this.mPreferenceScreen.removePreference(this.mRemoveProfileView);
        this.mPreferenceScreen.removePreference(this.mCreateProfileView);
        this.mPreferenceScreen.removePreference(this.mLockScreenView);
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.voiceprofile.VoiceProfileSettingContract.View
    public void setOptionalVoiceProfileNotEnrolled() {
        this.mPreferenceScreen.addPreference(this.mCreateProfileView);
        this.mPreferenceScreen.removePreference(this.mRemoveProfileView);
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.voiceprofile.VoiceProfileSettingContract.View
    public void setRemotelyDisabled() {
        this.mCreateProfileView.setEnabled(false);
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.voiceprofile.VoiceProfileSettingContract.View
    public void setUIAddVoiceProfileSettingsAndSetActive() {
        this.mPreferenceScreen.addPreference(this.mRemoveProfileView);
        this.mPreferenceScreen.addPreference(this.mLockScreenView);
        this.mPreferenceScreen.removePreference(this.mCreateProfileView);
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.voiceprofile.VoiceProfileSettingContract.View
    public void setUIRemoveVoiceProfileSettingsAndSetInactive() {
        this.mPreferenceScreen.removePreference(this.mRemoveProfileView);
        this.mPreferenceScreen.removePreference(this.mCreateProfileView);
        this.mPreferenceScreen.removePreference(this.mLockScreenView);
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.voiceprofile.VoiceProfileSettingContract.View
    public void setVoiceProfileEnrolled() {
        this.mPreferenceScreen.addPreference(this.mRemoveProfileView);
        this.mPreferenceScreen.removePreference(this.mCreateProfileView);
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.voiceprofile.VoiceProfileSettingContract.View
    public void setupCreateVoiceProfilePreference(@NonNull final PreferenceCallback preferenceCallback) {
        this.mCreateProfileView.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.amazon.alexa.handsfree.settings.handsfreesettings.voiceprofile.VoiceProfileSettingView.1
            @Override // android.preference.Preference.OnPreferenceClickListener
            public boolean onPreferenceClick(@NonNull Preference preference) {
                Intent intent = new Intent(VoiceProfileSettingView.UVR_ACTION);
                preferenceCallback.onPreferenceChanged(true);
                VoiceProfileSettingView.this.mContext.startActivity(intent);
                return true;
            }
        });
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.voiceprofile.VoiceProfileSettingContract.View
    public void setupDeleteVoiceProfilePreference(@NonNull final PreferenceCallback preferenceCallback) {
        this.mRemoveProfileView.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.amazon.alexa.handsfree.settings.handsfreesettings.voiceprofile.VoiceProfileSettingView.2
            @Override // android.preference.Preference.OnPreferenceClickListener
            public boolean onPreferenceClick(@NonNull Preference preference) {
                VoiceProfileSettingView.this.confirmDeleteProfile(preferenceCallback);
                return true;
            }
        });
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.voiceprofile.VoiceProfileSettingContract.View
    public void setupLockScreenCallback(@NonNull PreferenceCallback preferenceCallback) {
        this.mLockScreenCallback = preferenceCallback;
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.voiceprofile.VoiceProfileSettingContract.View
    public void showDeleteFail() {
        displayToast(R.string.delete_profile_failed);
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.voiceprofile.VoiceProfileSettingContract.View
    public void showDeleteSuccess() {
        displayToast(R.string.delete_profile_success);
        this.mLockScreenCallback.onPreferenceChanged(true);
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.voiceprofile.VoiceProfileSettingContract.View
    public void updateViews(boolean z) {
        this.mCreateProfileView.setEnabled(z);
    }

    @VisibleForTesting
    VoiceProfileSettingView(@NonNull PreferenceScreen preferenceScreen, @NonNull Preference preference, @NonNull Preference preference2, @NonNull Preference preference3, @NonNull Context context, @Nullable PreferenceCallback preferenceCallback, @NonNull Lazy<EnrollmentTypeResolver> lazy) {
        this.mPreferenceScreen = preferenceScreen;
        this.mCreateProfileView = preference;
        this.mRemoveProfileView = preference2;
        this.mLockScreenView = preference3;
        this.mContext = context;
        this.mLockScreenCallback = preferenceCallback;
        this.mEnrollmentTypeResolverLazy = lazy;
    }
}
