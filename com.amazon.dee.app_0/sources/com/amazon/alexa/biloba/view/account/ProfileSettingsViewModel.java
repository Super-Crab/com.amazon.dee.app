package com.amazon.alexa.biloba.view.account;

import android.content.res.Configuration;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.amazon.alexa.biloba.dependency.BilobaDependencies;
import com.amazon.alexa.biloba.membership.PermissionConstants;
import com.amazon.alexa.biloba.model.CareActor;
import com.amazon.alexa.biloba.storage.CareActorsStore;
import com.amazon.alexa.biloba.storage.CommsStore;
import com.amazon.alexa.biloba.storage.SettingsStore;
import com.amazon.alexa.biloba.utils.AndroidUtils;
import com.amazon.alexa.biloba.utils.CareActorUtil;
import com.amazon.alexa.biloba.utils.LiveDataUtils;
import com.amazon.alexa.biloba.utils.UrlHelper;
import com.amazon.alexa.biloba.view.BilobaViewModel;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.routing.api.RoutingService;
import dagger.Lazy;
import javax.inject.Inject;
/* loaded from: classes6.dex */
public class ProfileSettingsViewModel implements BilobaViewModel {
    private static final String TAG = "ProfileSettingsViewModel";
    @Inject
    Lazy<CareActorsStore> careActorsStore;
    @Inject
    Lazy<CommsStore> commsStore;
    private Configuration configuration;
    @Inject
    Lazy<EnvironmentService> environmentService;
    @Inject
    Lazy<FeatureServiceV2> featureServiceV2;
    @Inject
    Lazy<RoutingService> routingService;
    @Inject
    Lazy<SettingsStore> settingsStore;
    @Inject
    Lazy<UrlHelper> urlHelper;
    private final MediatorLiveData<Throwable> error = new MediatorLiveData<>();
    private final MediatorLiveData<Boolean> isLoading = new MediatorLiveData<>();
    private final MediatorLiveData<Boolean> isEmergencyHelplineEnabled = new MediatorLiveData<>();

    public ProfileSettingsViewModel(Configuration configuration) {
        BilobaDependencies.inject(this);
        this.configuration = configuration;
        initLiveData();
    }

    private void initLiveData() {
        LiveDataUtils.mergeLiveDataNotNull(this.error, this.commsStore.mo358get().getError(), this.settingsStore.mo358get().getError(), this.careActorsStore.mo358get().getError());
        LiveDataUtils.mergeLiveDataEqualTrue(this.isLoading, this.commsStore.mo358get().getIsLoading(), this.settingsStore.mo358get().getIsLoading(), this.careActorsStore.mo358get().getIsLoading());
        this.isEmergencyHelplineEnabled.addSource(getEmergencyHelplineStatus(), new Observer() { // from class: com.amazon.alexa.biloba.view.account.-$$Lambda$ProfileSettingsViewModel$hwWS3x_BzuabMfuilg7fZ8Z5EAk
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ProfileSettingsViewModel.this.lambda$initLiveData$0$ProfileSettingsViewModel((String) obj);
            }
        });
    }

    private Boolean isEmergencyHelplineEnabled(String str) {
        return Boolean.valueOf(str != null && str.equals(CommsStore.EmergencyHelplineStatus.VERIFIED));
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public void create(@Nullable Bundle bundle) {
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public void destroy() {
    }

    public String getAmazonRetailEndpoint() {
        return this.environmentService.mo358get().getRetailEndpoint();
    }

    public String getAmazonRetailHost() {
        String retailHost = this.environmentService.mo358get().getRetailHost();
        return retailHost.substring(0, 1).toUpperCase() + retailHost.substring(1);
    }

    public LiveData<CareActor> getCareActor() {
        return this.careActorsStore.mo358get().getCurrentActor();
    }

    public LiveData<CareActor> getCareContact() {
        return this.careActorsStore.mo358get().getCareContact();
    }

    public String getDisplayName(LiveData<CareActor> liveData) {
        return CareActorUtil.getDisplayName(liveData.getValue());
    }

    public LiveData<String> getEmergencyHelplineStatus() {
        return this.commsStore.mo358get().getEmergencyHelplineStatus();
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public LiveData<Throwable> getError() {
        return this.error;
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public String getErrorViewMetricName() {
        return "SettingsView.Error";
    }

    public String getExternalWebViewUrl() {
        return this.urlHelper.mo358get().getUrl("/groups/care/dashboard", this.configuration);
    }

    public LiveData<Boolean> getIsEmergencyHelplineEnabled() {
        return this.isEmergencyHelplineEnabled;
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public LiveData<Boolean> getIsLoading() {
        return this.isLoading;
    }

    public String getRole() {
        return this.careActorsStore.mo358get().getRole().getValue();
    }

    public String getSlicedCareContactContactId() {
        return this.careActorsStore.mo358get().getSlicedCareContactContactId();
    }

    public String getSlicedCurrentActorContactId() {
        return this.careActorsStore.mo358get().getSlicedCurrentActorContactId();
    }

    public MutableLiveData<String> getTimeZone() {
        return this.settingsStore.mo358get().getTimeZone();
    }

    public String getTimeZoneDisplayName(MutableLiveData<String> mutableLiveData) {
        return this.settingsStore.mo358get().getTimeZoneDisplayName(mutableLiveData);
    }

    public boolean hasManageCareCirclePermissions() {
        return isMCGEnabled() && (this.careActorsStore.mo358get().isPermissionEnabled(PermissionConstants.UPDATE_MEMBERS) || this.careActorsStore.mo358get().isPermissionEnabled(PermissionConstants.INVITE_MEMBERS));
    }

    public boolean hasUpdateEmergencyPermissions() {
        return isMCGEnabled() && (this.careActorsStore.mo358get().isPermissionEnabled(PermissionConstants.UPDATE_COMMS_EMERGENCY_CONTACT) || this.careActorsStore.mo358get().isPermissionEnabled(PermissionConstants.UPDATE_EMERGENCY_ADDRESS));
    }

    public boolean isCareGiver() {
        return this.careActorsStore.mo358get().getIsCareGiver().getValue() == Boolean.TRUE;
    }

    public boolean isCommsEnabledForCurrentActor() {
        return this.careActorsStore.mo358get().isCommsEnabledForCurrentActor();
    }

    public boolean isDropInEnabledForCareContact() {
        return this.careActorsStore.mo358get().isDropInEnabledForCareContact();
    }

    public boolean isMCGEnabled() {
        Lazy<FeatureServiceV2> lazy = this.featureServiceV2;
        return (lazy == null || lazy.mo358get() == null || !this.featureServiceV2.mo358get().hasAccess("ALEXA_AGING_MULTI_CG_MOBILE", false)) ? false : true;
    }

    public /* synthetic */ void lambda$initLiveData$0$ProfileSettingsViewModel(String str) {
        this.isEmergencyHelplineEnabled.setValue(isEmergencyHelplineEnabled(str));
    }

    public void navigateToStartup() {
        AndroidUtils.popRouteBackstackForBiloba(this.routingService.mo358get());
        this.routingService.mo358get().route("biloba").navigateReplaceTop();
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public void sendRequest() {
        this.careActorsStore.mo358get().requestCareActors();
        this.settingsStore.mo358get().getTimeZoneSettings(this.careActorsStore.mo358get().getCurrentGroupId());
        if (isMCGEnabled()) {
            this.commsStore.mo358get().requestEmergencySettings(this.careActorsStore.mo358get().getCurrentGroupId());
        }
    }

    public boolean shouldShowCarePlusEmergencyView() {
        return this.careActorsStore.mo358get().getHasSubscription().getValue() == Boolean.TRUE;
    }

    public boolean shouldShowDropIn() {
        if (isMCGEnabled()) {
            return false;
        }
        return isCommsEnabledForCurrentActor();
    }

    public boolean shouldShowErrorScreen() {
        return ((this.careActorsStore.mo358get().getError().getValue() == null && getCareContact().getValue() == null) || getError().getValue() == null) ? false : true;
    }

    public boolean shouldShowRelationshipManagementWebview() {
        return this.careActorsStore.mo358get().getHasSubscription().getValue() == Boolean.TRUE;
    }

    public boolean shouldShowTimezonePickerButton() {
        return !isMCGEnabled() || (isMCGEnabled() && this.careActorsStore.mo358get().isPermissionEnabled(PermissionConstants.UPDATE_SETTINGS));
    }

    public boolean shouldShowToManageHyperLinkedMessage() {
        return isMCGEnabled() && this.careActorsStore.mo358get().getIsPayer().getValue() == Boolean.TRUE;
    }

    @VisibleForTesting
    ProfileSettingsViewModel(Lazy<CareActorsStore> lazy, Lazy<SettingsStore> lazy2, Lazy<CommsStore> lazy3, Lazy<UrlHelper> lazy4, Lazy<FeatureServiceV2> lazy5, Configuration configuration, Lazy<RoutingService> lazy6, Lazy<EnvironmentService> lazy7) {
        this.careActorsStore = lazy;
        this.settingsStore = lazy2;
        this.commsStore = lazy3;
        this.urlHelper = lazy4;
        this.featureServiceV2 = lazy5;
        this.configuration = configuration;
        this.routingService = lazy6;
        this.environmentService = lazy7;
        initLiveData();
    }
}
