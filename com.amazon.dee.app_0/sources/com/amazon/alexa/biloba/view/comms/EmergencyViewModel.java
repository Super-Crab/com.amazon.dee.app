package com.amazon.alexa.biloba.view.comms;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.NotificationManagerCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.dependency.BilobaDependencies;
import com.amazon.alexa.biloba.generated.models.EmergencyAddress;
import com.amazon.alexa.biloba.generated.models.EmergencyContact;
import com.amazon.alexa.biloba.membership.PermissionConstants;
import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.model.CareActor;
import com.amazon.alexa.biloba.service.BilobaUrlResolver;
import com.amazon.alexa.biloba.storage.CareActorsStore;
import com.amazon.alexa.biloba.storage.CommsStore;
import com.amazon.alexa.biloba.utils.CareActorUtil;
import com.amazon.alexa.biloba.utils.LiveDataUtils;
import com.amazon.alexa.biloba.view.BilobaViewModel;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import dagger.Lazy;
import javax.inject.Inject;
/* loaded from: classes6.dex */
public class EmergencyViewModel implements BilobaViewModel {
    private static final String TAG = "EmergencyViewModel";
    @Inject
    Lazy<BilobaMetricsService> bilobaMetricsService;
    @Inject
    Lazy<CareActorsStore> careActorsStore;
    @Inject
    Lazy<CommsStore> commsStore;
    private final Context context;
    @Inject
    Lazy<CrashMetadata> crashMetadata;
    @Inject
    Lazy<CrashReporter> crashReporter;
    @Inject
    Lazy<EnvironmentService> environmentService;
    @Inject
    Lazy<FeatureServiceV2> featureServiceV2;
    private final NotificationManagerCompat notificationManager;
    @Inject
    Lazy<BilobaUrlResolver> urlResolver;
    private final MediatorLiveData<Throwable> error = new MediatorLiveData<>();
    private final MediatorLiveData<Boolean> isCurrentActorEmergencyContact = new MediatorLiveData<>();
    private final MediatorLiveData<Boolean> isNotEmergencyContactWithNotification = new MediatorLiveData<>();
    private final MediatorLiveData<Boolean> isNotEmergencyContactWithoutNotification = new MediatorLiveData<>();
    private final MediatorLiveData<Boolean> isCommsSetupByCareRecipient = new MediatorLiveData<>();
    private final MediatorLiveData<Boolean> isCommsSetupByCurrentActor = new MediatorLiveData<>();
    private final MediatorLiveData<Boolean> isEmergencyContactNotSet = new MediatorLiveData<>();
    private final MutableLiveData<Boolean> isNotificationEnabled = new MutableLiveData<>();
    private final MediatorLiveData<Boolean> isLoading = new MediatorLiveData<>();
    private final MediatorLiveData<Boolean> isEmergencyHelplineEnabled = new MediatorLiveData<>();

    public EmergencyViewModel(NotificationManagerCompat notificationManagerCompat, Context context) {
        BilobaDependencies.inject(this);
        this.context = context;
        this.notificationManager = notificationManagerCompat;
        initLiveData();
    }

    private void initLiveData() {
        LiveDataUtils.mergeLiveDataNotNull(this.error, this.commsStore.mo358get().getError(), this.careActorsStore.mo358get().getError());
        LiveDataUtils.mergeLiveDataEqualTrue(this.isLoading, this.commsStore.mo358get().getIsLoading(), this.careActorsStore.mo358get().getIsLoading());
        this.isCurrentActorEmergencyContact.addSource(getEmergencyContact(), new Observer() { // from class: com.amazon.alexa.biloba.view.comms.-$$Lambda$EmergencyViewModel$eAHV8B9hDBUcFCSsMV8NR5QpRmw
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                EmergencyViewModel.this.lambda$initLiveData$0$EmergencyViewModel((EmergencyContact) obj);
            }
        });
        this.isCurrentActorEmergencyContact.addSource(getCurrentActor(), new Observer() { // from class: com.amazon.alexa.biloba.view.comms.-$$Lambda$EmergencyViewModel$imqIthdgBlW1BcNJegVbLT7Arzg
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                EmergencyViewModel.this.lambda$initLiveData$1$EmergencyViewModel((CareActor) obj);
            }
        });
        this.isEmergencyContactNotSet.addSource(getEmergencyContact(), new Observer() { // from class: com.amazon.alexa.biloba.view.comms.-$$Lambda$EmergencyViewModel$sdIRMucKonBbc9JYldQU9jTWqUI
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                EmergencyViewModel.this.lambda$initLiveData$2$EmergencyViewModel((EmergencyContact) obj);
            }
        });
        this.isEmergencyContactNotSet.addSource(getIsCommsSetupByCareRecipient(), new Observer() { // from class: com.amazon.alexa.biloba.view.comms.-$$Lambda$EmergencyViewModel$udh5PG-oM7IT5Z9YKD7hIFZKg1E
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                EmergencyViewModel.this.lambda$initLiveData$3$EmergencyViewModel((Boolean) obj);
            }
        });
        this.isCommsSetupByCareRecipient.addSource(getCareRecipient(), new Observer() { // from class: com.amazon.alexa.biloba.view.comms.-$$Lambda$EmergencyViewModel$lZyTxydDocqdOGkfClCjetvKqHs
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                EmergencyViewModel.this.lambda$initLiveData$4$EmergencyViewModel((CareActor) obj);
            }
        });
        this.isCommsSetupByCurrentActor.addSource(getCurrentActor(), new Observer() { // from class: com.amazon.alexa.biloba.view.comms.-$$Lambda$EmergencyViewModel$YBD_earIuk6545v0iUSsZWlQ0Ro
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                EmergencyViewModel.this.lambda$initLiveData$5$EmergencyViewModel((CareActor) obj);
            }
        });
        this.isNotEmergencyContactWithNotification.addSource(getIsCurrentActorEmergencyContact(), new Observer() { // from class: com.amazon.alexa.biloba.view.comms.-$$Lambda$EmergencyViewModel$--G2ITKd4Ng2wEvyoIX5lkaj-qk
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                EmergencyViewModel.this.lambda$initLiveData$6$EmergencyViewModel((Boolean) obj);
            }
        });
        this.isNotEmergencyContactWithNotification.addSource(this.isNotificationEnabled, new Observer() { // from class: com.amazon.alexa.biloba.view.comms.-$$Lambda$EmergencyViewModel$-JXBRrB19lP0Jv1n2H2NTmqD5wg
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                EmergencyViewModel.this.lambda$initLiveData$7$EmergencyViewModel((Boolean) obj);
            }
        });
        this.isNotEmergencyContactWithoutNotification.addSource(getIsCurrentActorEmergencyContact(), new Observer() { // from class: com.amazon.alexa.biloba.view.comms.-$$Lambda$EmergencyViewModel$Xe5aqcdhOzQbAsY9zzzrMExzyW4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                EmergencyViewModel.this.lambda$initLiveData$8$EmergencyViewModel((Boolean) obj);
            }
        });
        this.isNotEmergencyContactWithoutNotification.addSource(this.isNotificationEnabled, new Observer() { // from class: com.amazon.alexa.biloba.view.comms.-$$Lambda$EmergencyViewModel$kCN1sAFeoV7TuOdLXWam-BVA5mg
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                EmergencyViewModel.this.lambda$initLiveData$9$EmergencyViewModel((Boolean) obj);
            }
        });
        this.isEmergencyHelplineEnabled.addSource(getEmergencyHelplineStatus(), new Observer() { // from class: com.amazon.alexa.biloba.view.comms.-$$Lambda$EmergencyViewModel$umwP3_fSmAdLnYu4kxQomzJjTR4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                EmergencyViewModel.this.lambda$initLiveData$10$EmergencyViewModel((String) obj);
            }
        });
    }

    private boolean isCurrentActorEmergencyContact() {
        return (getEmergencyContact().getValue() == null || getCurrentActor().getValue() == null || !TextUtils.equals(getEmergencyContact().getValue().getPersonIdV1(), getCurrentActor().getValue().getPersonIdV1())) ? false : true;
    }

    private boolean isCurrentActorNotEmergencyContact() {
        return (getEmergencyContact().getValue() == null || getCurrentActor().getValue() == null || TextUtils.equals(getEmergencyContact().getValue().getPersonIdV1(), getCurrentActor().getValue().getPersonIdV1())) ? false : true;
    }

    private boolean isCurrentActorNotEmergencyContactWithNotification() {
        return isCareGiver() && isCurrentActorNotEmergencyContact() && this.isNotificationEnabled.getValue() == Boolean.TRUE;
    }

    private boolean isCurrentActorNotEmergencyContactWithoutNotification() {
        return isCareGiver() && isCurrentActorNotEmergencyContact() && this.isNotificationEnabled.getValue() == Boolean.FALSE;
    }

    private boolean isEmergencyHelplineEnabled(String str) {
        return str != null && str.equals(CommsStore.EmergencyHelplineStatus.VERIFIED);
    }

    private boolean shouldShowAddEmergencyContact() {
        return getEmergencyContact().getValue() == null && getIsCommsSetupByCareRecipient().getValue() == Boolean.TRUE;
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public void create(@Nullable Bundle bundle) {
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public void destroy() {
    }

    public LiveData<CareActor> getCareRecipient() {
        return this.careActorsStore.mo358get().getCareRecipient();
    }

    public LiveData<CareActor> getCurrentActor() {
        return this.careActorsStore.mo358get().getCurrentActor();
    }

    public String getDisplayName(LiveData<CareActor> liveData) {
        return CareActorUtil.getDisplayName(liveData.getValue());
    }

    public LiveData<EmergencyAddress> getEmergencyAddress() {
        return this.commsStore.mo358get().getEmergencyAddress();
    }

    public LiveData<EmergencyContact> getEmergencyContact() {
        return this.commsStore.mo358get().getEmergencyContact();
    }

    public String getEmergencyContactSecondaryText() {
        EmergencyContact value = getEmergencyContact().getValue();
        if (value == null) {
            return this.context.getString(R.string.emergency_contact_heading);
        }
        return value.getPhoneNumber().getLocalPhoneNumber();
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
        return "EmergencyContactView.Error";
    }

    public LiveData<Boolean> getHasSubscription() {
        return this.careActorsStore.mo358get().getHasSubscription();
    }

    public LiveData<Boolean> getIsCareGiver() {
        return this.careActorsStore.mo358get().getIsCareGiver();
    }

    public LiveData<Boolean> getIsCommsSetupByCareRecipient() {
        return this.isCommsSetupByCareRecipient;
    }

    public LiveData<Boolean> getIsCommsSetupByCurrentActor() {
        return this.isCommsSetupByCurrentActor;
    }

    public LiveData<Boolean> getIsCurrentActorEmergencyContact() {
        return this.isCurrentActorEmergencyContact;
    }

    public LiveData<Boolean> getIsEmergencyContactNotSet() {
        return this.isEmergencyContactNotSet;
    }

    public LiveData<Boolean> getIsEmergencyHelplineEnabled() {
        return this.isEmergencyHelplineEnabled;
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public LiveData<Boolean> getIsLoading() {
        return this.isLoading;
    }

    public LiveData<Boolean> getIsNotEmergencyContactWithNotification() {
        return this.isNotEmergencyContactWithNotification;
    }

    public LiveData<Boolean> getIsNotEmergencyContactWithoutNotification() {
        return this.isNotEmergencyContactWithoutNotification;
    }

    public String getShareLinkUrl() {
        return this.urlResolver.mo358get().getCommsSetupUrl();
    }

    public boolean isCareGiver() {
        return getIsCareGiver().getValue() == Boolean.TRUE;
    }

    public boolean isEmergencyAddressSet() {
        return this.commsStore.mo358get().getEmergencyAddress() != null;
    }

    public boolean isMCGEnabled() {
        Lazy<FeatureServiceV2> lazy = this.featureServiceV2;
        return lazy != null && lazy.mo358get().hasAccess("ALEXA_AGING_MULTI_CG_MOBILE", false);
    }

    public /* synthetic */ void lambda$initLiveData$0$EmergencyViewModel(EmergencyContact emergencyContact) {
        this.isCurrentActorEmergencyContact.setValue(Boolean.valueOf(isCurrentActorEmergencyContact()));
    }

    public /* synthetic */ void lambda$initLiveData$1$EmergencyViewModel(CareActor careActor) {
        this.isCurrentActorEmergencyContact.setValue(Boolean.valueOf(isCurrentActorEmergencyContact()));
    }

    public /* synthetic */ void lambda$initLiveData$10$EmergencyViewModel(String str) {
        this.isEmergencyHelplineEnabled.setValue(Boolean.valueOf(isEmergencyHelplineEnabled(str)));
    }

    public /* synthetic */ void lambda$initLiveData$2$EmergencyViewModel(EmergencyContact emergencyContact) {
        this.isEmergencyContactNotSet.setValue(Boolean.valueOf(shouldShowAddEmergencyContact()));
    }

    public /* synthetic */ void lambda$initLiveData$3$EmergencyViewModel(Boolean bool) {
        this.isEmergencyContactNotSet.setValue(Boolean.valueOf(shouldShowAddEmergencyContact()));
    }

    public /* synthetic */ void lambda$initLiveData$4$EmergencyViewModel(CareActor careActor) {
        this.isCommsSetupByCareRecipient.setValue(Boolean.valueOf((careActor == null || careActor.getCommsId() == null || !CareActorUtil.isCommsEnabled(careActor)) ? false : true));
    }

    public /* synthetic */ void lambda$initLiveData$5$EmergencyViewModel(CareActor careActor) {
        this.isCommsSetupByCurrentActor.setValue(Boolean.valueOf(careActor.getCommsId() != null && CareActorUtil.isCommsEnabled(careActor)));
    }

    public /* synthetic */ void lambda$initLiveData$6$EmergencyViewModel(Boolean bool) {
        this.isNotEmergencyContactWithNotification.setValue(Boolean.valueOf(isCurrentActorNotEmergencyContactWithNotification()));
    }

    public /* synthetic */ void lambda$initLiveData$7$EmergencyViewModel(Boolean bool) {
        this.isNotEmergencyContactWithoutNotification.setValue(Boolean.valueOf(isCurrentActorNotEmergencyContactWithNotification()));
    }

    public /* synthetic */ void lambda$initLiveData$8$EmergencyViewModel(Boolean bool) {
        this.isNotEmergencyContactWithoutNotification.setValue(Boolean.valueOf(isCurrentActorNotEmergencyContactWithoutNotification()));
    }

    public /* synthetic */ void lambda$initLiveData$9$EmergencyViewModel(Boolean bool) {
        this.isNotEmergencyContactWithoutNotification.setValue(Boolean.valueOf(isCurrentActorNotEmergencyContactWithoutNotification()));
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public void sendRequest() {
        this.isNotificationEnabled.setValue(Boolean.valueOf(this.notificationManager.areNotificationsEnabled()));
        this.careActorsStore.mo358get().requestCareActors();
        this.commsStore.mo358get().requestEmergencyContact(this.careActorsStore.mo358get().getCurrentGroupId());
        this.commsStore.mo358get().requestEmergencySettings(this.careActorsStore.mo358get().getCurrentGroupId());
    }

    public boolean shouldShowChangeEmergencyAddressButton() {
        return !isMCGEnabled() || (isMCGEnabled() && this.careActorsStore.mo358get().isPermissionEnabled(PermissionConstants.UPDATE_EMERGENCY_ADDRESS));
    }

    public boolean shouldShowEditOrAddEmergencyContactButton() {
        return !isMCGEnabled() || (isMCGEnabled() && this.careActorsStore.mo358get().isPermissionEnabled(PermissionConstants.UPDATE_COMMS_EMERGENCY_CONTACT));
    }

    public boolean shouldShowUrgentResponseSection() {
        return !isMCGEnabled() || (isMCGEnabled() && this.careActorsStore.mo358get().isPermissionEnabled(PermissionConstants.GET_EMERGENCY_ADDRESS));
    }

    @VisibleForTesting
    EmergencyViewModel(Lazy<EnvironmentService> lazy, Lazy<CommsStore> lazy2, Lazy<CareActorsStore> lazy3, NotificationManagerCompat notificationManagerCompat, Lazy<BilobaUrlResolver> lazy4, Lazy<CrashMetadata> lazy5, Lazy<CrashReporter> lazy6, Lazy<BilobaMetricsService> lazy7, Context context, Lazy<FeatureServiceV2> lazy8) {
        this.environmentService = lazy;
        this.notificationManager = notificationManagerCompat;
        this.urlResolver = lazy4;
        this.commsStore = lazy2;
        this.careActorsStore = lazy3;
        this.crashMetadata = lazy5;
        this.crashReporter = lazy6;
        this.bilobaMetricsService = lazy7;
        this.context = context;
        this.featureServiceV2 = lazy8;
        initLiveData();
    }
}
