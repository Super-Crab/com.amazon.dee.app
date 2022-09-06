package com.amazon.alexa.handsfree.settings.handsfreesettings;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeComponent;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeUserIdentityProvider;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.UVRModule;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRConnector;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentType;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentTypeResolver;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.settings.R;
import com.amazon.alexa.handsfree.settings.SettingsModule;
import com.amazon.alexa.handsfree.settings.contract.SettingsContract;
import com.amazon.alexa.handsfree.settings.contract.handsfreesettings.CustomPreferenceSetting;
import com.amazon.alexa.handsfree.settings.contract.handsfreesettings.handsfree.HandsFreeSettingContract;
import com.amazon.alexa.handsfree.settings.contract.handsfreesettings.vendor.VendorSettingContract;
import com.amazon.alexa.handsfree.settings.dependencies.FalcoSettingsComponent;
import com.amazon.alexa.handsfree.settings.handsfreesettings.PreferencesPresenter;
import com.amazon.alexa.handsfree.settings.handsfreesettings.handsfree.HandsFreeSettingPresenter;
import com.amazon.alexa.handsfree.settings.handsfreesettings.handsfree.HandsFreeSettingView;
import com.amazon.alexa.handsfree.settings.handsfreesettings.lockscreen.LockScreenSettingPresenter;
import com.amazon.alexa.handsfree.settings.handsfreesettings.lockscreen.LockScreenSettingView;
import com.amazon.alexa.handsfree.settings.handsfreesettings.voiceprofile.VoiceProfileSettingPresenter;
import com.amazon.alexa.handsfree.settings.handsfreesettings.voiceprofile.VoiceProfileSettingView;
import com.amazon.alexa.handsfree.settings.metrics.MetricType;
import com.amazon.alexa.handsfree.settings.voxsettings.VoxSettingsEnqueuer;
import dagger.Lazy;
import javax.inject.Inject;
@RequiresApi(api = 23)
/* loaded from: classes8.dex */
public class AlexaSettingsPreferenceFragment extends PreferenceFragment {
    private static final String TAG = "AlexaSettingsPrefFrag";
    private AlexaSettingsRefreshReceiver mAlexaSettingsRefreshReceiver;
    private CustomPreferenceSetting mCustomPreferenceSetting;
    @Inject
    Lazy<EnrollmentTypeResolver> mEnrollmentTypeResolverLazy;
    @Inject
    HandsFreeUserIdentityProvider mHandsFreeUserIdentityProvider;
    private boolean mIsUVRAvailable;
    private MetricsBuilderProvider mMetricsBuilderProvider;
    private PreferencesPresenter mPreferencesPresenter;
    private UVRConnector mUVRConnector;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes8.dex */
    public class AlexaSettingsRefreshReceiver extends BroadcastReceiver {
        private AlexaSettingsRefreshReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(@NonNull Context context, @NonNull Intent intent) {
            AlexaSettingsPreferenceFragment.this.refreshDisplay();
        }
    }

    public AlexaSettingsPreferenceFragment() {
    }

    private LockScreenSettingPresenter getLockScreenSettingPresenter() {
        SwitchPreference switchPreference = (SwitchPreference) findPreference(getString(R.string.alexa_handsfree_show_on_lock_screen_key_deprecated));
        Preference findPreference = findPreference(getString(R.string.alexa_handsfree_show_on_lock_screen_key));
        findPreference.setLayoutResource(R.layout.settings_pref_rwl);
        return new LockScreenSettingPresenter(new LockScreenSettingView(getPreferenceScreen(), findPreference, switchPreference, new VoxSettingsEnqueuer(), getContext(), this.mHandsFreeUserIdentityProvider.getHandsFreeUserIdentity(), AMPDInformationProvider.getInstance(getContext())), getContext());
    }

    private VoiceProfileSettingPresenter getVoiceProfileSettingPresenter(@NonNull HandsFreeSettingContract.WakeWordManager wakeWordManager) {
        VoiceProfileSettingView voiceProfileSettingView;
        Preference findPreference = findPreference(getString(R.string.enable_uvr_key));
        Preference findPreference2 = findPreference(getString(R.string.disable_uvr_key));
        EnrollmentTypeResolver mo358get = this.mEnrollmentTypeResolverLazy.mo358get();
        if (mo358get != null && mo358get.getSpeakerVerificationEnrollmentType() == EnrollmentType._1PSV) {
            if (mo358get.getEnrollmentType() != EnrollmentType._1PSV_DECOUPLED) {
                findPreference2.setLayoutResource(R.layout.settings_preference_voice_profile);
                findPreference2.setTitle(R.string.alexa_voice_profile);
                findPreference2.setSummary(R.string.alexa_voice_profile_summary);
            } else {
                findPreference2.setSummary(getString(R.string.disable_uvr_summary_decoupled));
            }
        } else {
            findPreference2.setSummary(getString(R.string.disable_uvr_summary, UVRModule.INSTANCE.getVoiceApplicationName(getContext())));
        }
        Preference findPreference3 = findPreference(getString(R.string.alexa_handsfree_show_on_lock_screen_key));
        if (isBlockSensitiveRequest()) {
            findPreference3.setLayoutResource(R.layout.settings_pref_rwl);
        }
        SwitchPreference switchPreference = (SwitchPreference) findPreference(getString(R.string.alexa_handsfree_show_on_lock_screen_key_deprecated));
        if (isBlockSensitiveRequest()) {
            voiceProfileSettingView = new VoiceProfileSettingView(getPreferenceScreen(), findPreference, findPreference2, findPreference3, getContext());
        } else {
            voiceProfileSettingView = new VoiceProfileSettingView(getPreferenceScreen(), findPreference, findPreference2, switchPreference, getContext());
        }
        return new VoiceProfileSettingPresenter(voiceProfileSettingView, getContext(), wakeWordManager);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshDisplay() {
        PreferencesPresenter preferencesPresenter = this.mPreferencesPresenter;
        if (preferencesPresenter != null) {
            preferencesPresenter.refreshDisplay();
        }
    }

    private void registerReceiver() {
        if (this.mAlexaSettingsRefreshReceiver == null) {
            this.mAlexaSettingsRefreshReceiver = new AlexaSettingsRefreshReceiver();
            getActivity().registerReceiver(this.mAlexaSettingsRefreshReceiver, new IntentFilter(AlexaHandsFreeSettingsManager.ALEXA_SETTINGS_REFRESH_ACTION));
        }
    }

    private void setupAlexaSettingsTitleAndSummary() {
        String mainSectionTitle = this.mCustomPreferenceSetting.getMainSectionTitle();
        PreferenceCategory preferenceCategory = (PreferenceCategory) findPreference(getString(R.string.main_category_key));
        Preference findPreference = findPreference(getString(R.string.alexa_settings_activity_summary_key));
        if (mainSectionTitle != null) {
            preferenceCategory.setTitle(mainSectionTitle);
            getPreferenceScreen().removePreference(findPreference);
            return;
        }
        findPreference.setTitle(R.string.alexa_settings_activity_summary_content);
        getPreferenceScreen().removePreference(preferenceCategory);
    }

    private void setupUVRPreferences() {
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        Preference findPreference = findPreference(getString(R.string.enable_uvr_key));
        Preference findPreference2 = findPreference(getString(R.string.disable_uvr_key));
        Preference findPreference3 = findPreference(getString(R.string.alexa_handsfree_show_on_lock_screen_key));
        if (findPreference3 != null) {
            findPreference3.setLayoutResource(R.layout.settings_pref_rwl);
        }
        SwitchPreference switchPreference = (SwitchPreference) findPreference(getString(R.string.alexa_handsfree_show_on_lock_screen_key_deprecated));
        if (!this.mIsUVRAvailable) {
            preferenceScreen.removePreference(findPreference);
            preferenceScreen.removePreference(findPreference2);
        }
        if (this.mIsUVRAvailable) {
            this.mUVRConnector = UVRModule.INSTANCE.getUVRContract().getUVRConnector();
            this.mUVRConnector.startConnection(getContext(), true);
            setupVoiceProfilePreferences();
        }
        if (this.mCustomPreferenceSetting.isShowOnLockScreenAvailable()) {
            setupLockscreenPreferences();
            return;
        }
        preferenceScreen.removePreference(switchPreference);
        preferenceScreen.removePreference(findPreference3);
    }

    @VisibleForTesting
    PreferencesPresenter buildPreferencesPresenter(PreferencesPresenter.Builder builder, HandsFreeSettingPresenter handsFreeSettingPresenter) {
        builder.withHandsFreeSettingPresenter(handsFreeSettingPresenter);
        builder.withUvrAvailable(this.mIsUVRAvailable);
        builder.withShowOnLockScreenAvailable(this.mCustomPreferenceSetting.isShowOnLockScreenAvailable());
        if (this.mIsUVRAvailable) {
            builder.withVoiceProfileSettingPresenter(getVoiceProfileSettingPresenter(handsFreeSettingPresenter));
        }
        if (this.mCustomPreferenceSetting.isShowOnLockScreenAvailable()) {
            builder.withLockScreenSettingPresenter(getLockScreenSettingPresenter());
        }
        return builder.build();
    }

    @VisibleForTesting
    HandsFreeSettingPresenter getHandsFreeSettingPresenter() {
        return new HandsFreeSettingPresenter(new HandsFreeSettingView((SwitchPreference) findPreference(getString(R.string.alexa_handsfree_enable_key)), getContext()), getContext());
    }

    @VisibleForTesting
    PreferencesPresenter getPreferencesPresenter() {
        return buildPreferencesPresenter(new PreferencesPresenter.Builder(getContext()), getHandsFreeSettingPresenter());
    }

    public boolean isBlockSensitiveRequest() {
        return this.mHandsFreeUserIdentityProvider.getHandsFreeUserIdentity() != null && this.mHandsFreeUserIdentityProvider.getHandsFreeUserIdentity().hasComponent(HandsFreeComponent.HANDS_FREE_BLOCK_SENSITIVE_REQUEST) && AMPDInformationProvider.getInstance(getContext()).isBSR();
    }

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        View view = getView();
        ((ListView) view.findViewById(16908298)).setDivider(null);
        view.getViewTreeObserver().addOnWindowFocusChangeListener(new ViewTreeObserver.OnWindowFocusChangeListener() { // from class: com.amazon.alexa.handsfree.settings.handsfreesettings.AlexaSettingsPreferenceFragment.1
            @Override // android.view.ViewTreeObserver.OnWindowFocusChangeListener
            public void onWindowFocusChanged(boolean z) {
                if (z) {
                    AlexaSettingsPreferenceFragment.this.refreshDisplay();
                }
            }
        });
    }

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        ((FalcoSettingsComponent) AhfComponentsProvider.getComponent(getActivity().getApplicationContext(), FalcoSettingsComponent.class)).inject(this);
        super.onCreate(bundle);
        this.mMetricsBuilderProvider = MetricsBuilderProvider.getInstance(getActivity());
        SettingsContract contract = SettingsModule.INSTANCE.getContract();
        if (contract == null) {
            Log.e(TAG, "onCreate: SettingsModule has not been initialized!");
            getActivity().finish();
            return;
        }
        addPreferencesFromResource(R.xml.alexa_settings_preferences_uvr_available);
        this.mCustomPreferenceSetting = contract.getCustomPreferenceSetting();
        this.mIsUVRAvailable = this.mCustomPreferenceSetting.isUVRAvailable();
        this.mPreferencesPresenter = getPreferencesPresenter();
        setupAlexaSettingsTitleAndSummary();
        setupHandsFreePreferences();
        setupUVRPreferences();
        setupOtherCategory();
        registerReceiver();
    }

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        onDestroyFragment();
    }

    @VisibleForTesting
    void onDestroyFragment() {
        UVRConnector uVRConnector = this.mUVRConnector;
        if (uVRConnector != null) {
            uVRConnector.endConnection(getContext());
        }
        PreferencesPresenter preferencesPresenter = this.mPreferencesPresenter;
        if (preferencesPresenter != null) {
            preferencesPresenter.cleanup();
        } else {
            Log.w(TAG, "PreferencesPresenter is null. Can't execute cleanup.");
            this.mMetricsBuilderProvider.newBuilder().withPerformanceMetric(TAG, MetricType.PREFERENCES_PRESENTER_NOT_AVAILABLE_FOR_CLEANUP.getValue()).emit(getContext());
        }
        unregisterReceiver();
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        refreshDisplay();
    }

    @VisibleForTesting
    void setupHandsFreePreferences() {
        this.mPreferencesPresenter.setupHandsFreePreference();
        this.mPreferencesPresenter.setupHandsFree();
    }

    @VisibleForTesting
    void setupLockscreenPreferences() {
        this.mPreferencesPresenter.setupShowOnLockscreen();
        this.mPreferencesPresenter.getShowOnLockScreenValue();
        this.mPreferencesPresenter.setHandsFreeStateChangeForLockScreen();
    }

    @VisibleForTesting
    void setupOtherCategory() {
        PreferenceCategory preferenceCategory = (PreferenceCategory) findPreference(getString(R.string.other_category_key));
        Preference findPreference = findPreference(getString(R.string.legal_notice_key));
        if (!this.mCustomPreferenceSetting.isLegalNoticeNeeded()) {
            preferenceCategory.removePreference(findPreference);
        }
        setupVendorSettingsPreferences();
        if (preferenceCategory.getPreferenceCount() == 0) {
            getPreferenceScreen().removePreference(preferenceCategory);
        }
    }

    @VisibleForTesting
    void setupVendorSettingsPreferences() {
        PreferenceCategory preferenceCategory = (PreferenceCategory) findPreference(getString(R.string.other_category_key));
        Preference findPreference = findPreference(getString(R.string.alexa_settings_vendor_settings_key));
        VendorSettingContract vendorSettings = this.mCustomPreferenceSetting.getVendorSettings();
        if (vendorSettings == null) {
            Log.e(TAG, "setupVendorSettingPreference: No VendorSettingContract");
            preferenceCategory.removePreference(findPreference);
            return;
        }
        final Intent customSettingsIntent = vendorSettings.getCustomSettingsIntent();
        if (customSettingsIntent == null) {
            Log.e(TAG, "setupVendorSettingPreference: No CustomSettingsIntent");
            preferenceCategory.removePreference(findPreference);
            return;
        }
        findPreference.setTitle(vendorSettings.getVendorSettingsTitle());
        findPreference.setSummary(vendorSettings.getVendorSettingsSummary());
        findPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.amazon.alexa.handsfree.settings.handsfreesettings.AlexaSettingsPreferenceFragment.2
            @Override // android.preference.Preference.OnPreferenceClickListener
            public boolean onPreferenceClick(Preference preference) {
                try {
                    AlexaSettingsPreferenceFragment.this.startActivity(customSettingsIntent);
                    return true;
                } catch (ActivityNotFoundException e) {
                    Log.e(AlexaSettingsPreferenceFragment.TAG, "onCreate: No customized vendor settings found!", e, new Object[0]);
                    return false;
                }
            }
        });
    }

    @VisibleForTesting
    void setupVoiceProfilePreferences() {
        this.mPreferencesPresenter.setupVoiceProfilePreference();
    }

    @VisibleForTesting
    void unregisterReceiver() {
        if (this.mAlexaSettingsRefreshReceiver != null) {
            getActivity().unregisterReceiver(this.mAlexaSettingsRefreshReceiver);
            this.mAlexaSettingsRefreshReceiver = null;
        }
    }

    @SuppressLint({"ValidFragment"})
    @VisibleForTesting
    AlexaSettingsPreferenceFragment(@NonNull UVRConnector uVRConnector, @NonNull PreferencesPresenter preferencesPresenter, @NonNull CustomPreferenceSetting customPreferenceSetting, @NonNull boolean z, @NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull Lazy<EnrollmentTypeResolver> lazy, @NonNull HandsFreeUserIdentityProvider handsFreeUserIdentityProvider) {
        this.mUVRConnector = uVRConnector;
        this.mPreferencesPresenter = preferencesPresenter;
        this.mCustomPreferenceSetting = customPreferenceSetting;
        this.mIsUVRAvailable = z;
        this.mMetricsBuilderProvider = metricsBuilderProvider;
        this.mEnrollmentTypeResolverLazy = lazy;
        this.mHandsFreeUserIdentityProvider = handsFreeUserIdentityProvider;
    }
}
