package com.amazon.alexa.handsfree.settings.handsfreesettings.lockscreen;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AlertDialog;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeComponent;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeUserIdentity;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentType;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentTypeResolver;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.settings.R;
import com.amazon.alexa.handsfree.settings.contract.handsfreesettings.PreferenceCallback;
import com.amazon.alexa.handsfree.settings.contract.handsfreesettings.ResponsePreferenceCallback;
import com.amazon.alexa.handsfree.settings.contract.handsfreesettings.lockscreen.LockScreenSettingContract;
import com.amazon.alexa.handsfree.settings.voxsettings.VoxSettingsEnqueuer;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import dagger.Lazy;
/* loaded from: classes8.dex */
public class LockScreenSettingView implements LockScreenSettingContract.View {
    private static final int FFFFFF = 16777215;
    private static final String TAG = "LockScreenSettingView";
    private final AMPDInformationProvider mAMPDInformationProvider;
    private final Context mContext;
    private final Lazy<EnrollmentTypeResolver> mEnrollmentTypeResolverLazy;
    private final HandsFreeUserIdentity mHandsFreeUser;
    private Preference.OnPreferenceClickListener mPreferenceClickListener;
    private final PreferenceScreen mPreferenceScreen;
    private RadioGroup.OnCheckedChangeListener mRadioGroupChangeListener;
    private final View mRadioGroupView;
    private final Preference mRespondWhileLockedPreference;
    private final SwitchPreference mSwitchPreference;
    private final VoxSettingsEnqueuer mVoxSettingsEnqueuer;

    public LockScreenSettingView(@NonNull PreferenceScreen preferenceScreen, @NonNull Preference preference, @NonNull SwitchPreference switchPreference, @NonNull VoxSettingsEnqueuer voxSettingsEnqueuer, @NonNull Context context, @NonNull HandsFreeUserIdentity handsFreeUserIdentity, @NonNull AMPDInformationProvider aMPDInformationProvider) {
        this(preferenceScreen, preference, switchPreference, ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(context, FalcoProtocolComponent.class)).enrollmentTypeResolverLazy(), voxSettingsEnqueuer, context, handsFreeUserIdentity, aMPDInformationProvider);
    }

    @VisibleForTesting
    AlertDialog createAlertDialogForSwitch(@NonNull SwitchPreference switchPreference, @NonNull PreferenceCallback preferenceCallback) {
        return setAndCreateAlertDialogForSwitch(switchPreference, preferenceCallback, new AlertDialog.Builder(this.mContext, R.style.AlertDialogBackground));
    }

    @VisibleForTesting
    RadioGroup.OnCheckedChangeListener getCheckedChangeListener(@NonNull final ResponsePreferenceCallback responsePreferenceCallback) {
        return new RadioGroup.OnCheckedChangeListener() { // from class: com.amazon.alexa.handsfree.settings.handsfreesettings.lockscreen.LockScreenSettingView.3
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (((RadioButton) radioGroup.findViewById(i)).isChecked()) {
                    if (i == R.id.response_while_locked_on) {
                        responsePreferenceCallback.onPreferenceChanged(LockScreenSettingView.this.mContext.getString(R.string.alexa_respond_while_locked_block_none));
                    } else if (i == R.id.response_while_locked_on_block_resp) {
                        responsePreferenceCallback.onPreferenceChanged(LockScreenSettingView.this.mContext.getString(R.string.alexa_respond_while_locked_block_personal));
                    } else if (i != R.id.response_while_locked_off) {
                    } else {
                        responsePreferenceCallback.onPreferenceChanged(LockScreenSettingView.this.mContext.getString(R.string.alexa_respond_while_locked_block_all));
                    }
                }
            }
        };
    }

    @VisibleForTesting
    Preference.OnPreferenceClickListener getPreferenceClickListener(@NonNull final PreferenceCallback preferenceCallback) {
        return new Preference.OnPreferenceClickListener() { // from class: com.amazon.alexa.handsfree.settings.handsfreesettings.lockscreen.LockScreenSettingView.1
            @Override // android.preference.Preference.OnPreferenceClickListener
            public boolean onPreferenceClick(Preference preference) {
                if (!(preference instanceof SwitchPreference)) {
                    Log.e(LockScreenSettingView.TAG, "onCreate: show on lock screen is not a SwitchPreference.");
                    return false;
                }
                SwitchPreference switchPreference = (SwitchPreference) preference;
                if (switchPreference.isChecked()) {
                    switchPreference.setChecked(false);
                    LockScreenSettingView.this.createAlertDialogForSwitch(switchPreference, preferenceCallback).show();
                    return true;
                }
                preferenceCallback.onPreferenceChanged(false);
                return true;
            }
        };
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.lockscreen.LockScreenSettingContract.View
    public boolean isBlockSensitiveRequest() {
        HandsFreeUserIdentity handsFreeUserIdentity = this.mHandsFreeUser;
        return handsFreeUserIdentity != null && handsFreeUserIdentity.hasComponent(HandsFreeComponent.HANDS_FREE_BLOCK_SENSITIVE_REQUEST) && this.mAMPDInformationProvider.isBSR();
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.lockscreen.LockScreenSettingContract.View
    public void onVoiceProfileSettingChange(boolean z) {
        if (isBlockSensitiveRequest()) {
            if (z) {
                this.mPreferenceScreen.addPreference(this.mRespondWhileLockedPreference);
            } else {
                this.mPreferenceScreen.removePreference(this.mRespondWhileLockedPreference);
            }
        } else if (z) {
            this.mPreferenceScreen.addPreference(this.mSwitchPreference);
        } else {
            this.mPreferenceScreen.removePreference(this.mSwitchPreference);
        }
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.lockscreen.LockScreenSettingContract.View
    public void setActive(String str) {
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.lockscreen.LockScreenSettingContract.View
    public void setActive(boolean z) {
        if (!isBlockSensitiveRequest()) {
            this.mSwitchPreference.setChecked(z);
        }
    }

    @VisibleForTesting
    AlertDialog setAndCreateAlertDialogForSwitch(@NonNull final SwitchPreference switchPreference, @NonNull final PreferenceCallback preferenceCallback, @NonNull AlertDialog.Builder builder) {
        String string;
        EnrollmentTypeResolver mo358get = this.mEnrollmentTypeResolverLazy.mo358get();
        boolean z = mo358get != null && mo358get.getSpeakerVerificationEnrollmentType() == EnrollmentType._1PSV;
        String hexString = Integer.toHexString(ThemeUtil.getColorFromAttribute(this.mContext, R.attr.mosaicNeutral10) & 16777215);
        if (z) {
            string = this.mContext.getString(R.string.alexa_handsfree_show_on_lock_screen_alert_text_edgesv);
        } else {
            string = this.mContext.getString(R.string.alexa_handsfree_show_on_lock_screen_alert_text);
        }
        builder.setMessage(Html.fromHtml(String.format("<p style=\"color:#%s\">%s</p>", hexString, string)));
        return builder.setTitle(R.string.alexa_handsfree_show_on_lock_screen_alert_title).setPositiveButton(R.string.alexa_handsfree_show_on_lock_screen_alert_positive_text, new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.handsfree.settings.handsfreesettings.lockscreen.LockScreenSettingView.5
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(@NonNull DialogInterface dialogInterface, int i) {
                switchPreference.setChecked(true);
                preferenceCallback.onPreferenceChanged(true);
            }
        }).setNegativeButton(R.string.alexa_handsfree_show_on_lock_screen_alert_negative_text, new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.handsfree.settings.handsfreesettings.lockscreen.LockScreenSettingView.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(@NonNull DialogInterface dialogInterface, int i) {
                switchPreference.setChecked(false);
            }
        }).create();
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.lockscreen.LockScreenSettingContract.View
    public void setEnabled(boolean z) {
        if (isBlockSensitiveRequest()) {
            this.mPreferenceScreen.removePreference(this.mSwitchPreference);
            this.mRespondWhileLockedPreference.setEnabled(z);
            this.mRespondWhileLockedPreference.setSelectable(z);
            if (!z) {
                this.mPreferenceScreen.removePreference(this.mRespondWhileLockedPreference);
                return;
            } else {
                this.mPreferenceScreen.addPreference(this.mRespondWhileLockedPreference);
                return;
            }
        }
        this.mPreferenceScreen.removePreference(this.mRespondWhileLockedPreference);
        this.mSwitchPreference.setEnabled(z);
        this.mSwitchPreference.setSelectable(z);
        if (!z) {
            this.mPreferenceScreen.removePreference(this.mSwitchPreference);
        } else {
            this.mPreferenceScreen.addPreference(this.mSwitchPreference);
        }
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.lockscreen.LockScreenSettingContract.View
    public void setRemotelyDisabled() {
        if (isBlockSensitiveRequest()) {
            VoxSettingsEnqueuer voxSettingsEnqueuer = this.mVoxSettingsEnqueuer;
            Context context = this.mContext;
            voxSettingsEnqueuer.updateShowOnLockscreenPref(context, context.getString(R.string.alexa_respond_while_locked_block_personal));
        } else {
            this.mSwitchPreference.setChecked(false);
        }
        setEnabled(false);
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.lockscreen.LockScreenSettingContract.View
    public void setupListener(@NonNull PreferenceCallback preferenceCallback) {
        this.mSwitchPreference.setOnPreferenceClickListener(getPreferenceClickListener(preferenceCallback));
    }

    @VisibleForTesting
    Preference.OnPreferenceClickListener getPreferenceClickListener() {
        return new Preference.OnPreferenceClickListener() { // from class: com.amazon.alexa.handsfree.settings.handsfreesettings.lockscreen.LockScreenSettingView.2
            @Override // android.preference.Preference.OnPreferenceClickListener
            public boolean onPreferenceClick(@NonNull Preference preference) {
                LockScreenSettingView.this.mContext.startActivity(new Intent(LockScreenSettingView.this.mContext, RespondWhileLockedActivity.class));
                return true;
            }
        };
    }

    @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.lockscreen.LockScreenSettingContract.View
    public void setupListener(@NonNull ResponsePreferenceCallback responsePreferenceCallback) {
        if (this.mRadioGroupChangeListener == null) {
            this.mRadioGroupChangeListener = getCheckedChangeListener(responsePreferenceCallback);
            ((RadioGroup) this.mRadioGroupView.findViewById(R.id.alexa_respond_while_locked_radiogroup)).setOnCheckedChangeListener(this.mRadioGroupChangeListener);
        }
    }

    public LockScreenSettingView(@NonNull PreferenceScreen preferenceScreen, @NonNull Preference preference, @NonNull SwitchPreference switchPreference, @NonNull Lazy<EnrollmentTypeResolver> lazy, @NonNull VoxSettingsEnqueuer voxSettingsEnqueuer, @NonNull Context context, @NonNull HandsFreeUserIdentity handsFreeUserIdentity, @NonNull AMPDInformationProvider aMPDInformationProvider) {
        this.mPreferenceScreen = preferenceScreen;
        this.mRespondWhileLockedPreference = preference;
        this.mSwitchPreference = switchPreference;
        this.mEnrollmentTypeResolverLazy = lazy;
        this.mContext = context;
        this.mHandsFreeUser = handsFreeUserIdentity;
        this.mAMPDInformationProvider = aMPDInformationProvider;
        this.mVoxSettingsEnqueuer = voxSettingsEnqueuer;
        this.mRadioGroupView = ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(R.layout.respond_while_locked_choice, (ViewGroup) null);
        this.mPreferenceClickListener = getPreferenceClickListener();
        this.mRespondWhileLockedPreference.setOnPreferenceClickListener(this.mPreferenceClickListener);
        if (isBlockSensitiveRequest()) {
            this.mPreferenceScreen.removePreference(this.mSwitchPreference);
        } else {
            this.mPreferenceScreen.removePreference(this.mRespondWhileLockedPreference);
        }
    }
}
