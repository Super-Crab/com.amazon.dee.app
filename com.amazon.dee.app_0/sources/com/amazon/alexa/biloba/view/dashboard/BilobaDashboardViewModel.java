package com.amazon.alexa.biloba.view.dashboard;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.amazon.alexa.biloba.dependency.BilobaDependencies;
import com.amazon.alexa.biloba.generated.models.EmergencyContact;
import com.amazon.alexa.biloba.membership.PermissionConstants;
import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.metrics.MetricsConstants;
import com.amazon.alexa.biloba.model.CareActor;
import com.amazon.alexa.biloba.storage.CardsStore;
import com.amazon.alexa.biloba.storage.CareActorsStore;
import com.amazon.alexa.biloba.storage.CommsStore;
import com.amazon.alexa.biloba.storage.SettingsStore;
import com.amazon.alexa.biloba.storage.TodaysActivitiesStore;
import com.amazon.alexa.biloba.utils.AndroidUtils;
import com.amazon.alexa.biloba.utils.CareActorUtil;
import com.amazon.alexa.biloba.utils.LiveDataUtils;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.biloba.view.BilobaViewModel;
import com.amazon.alexa.biloba.view.common.recycler.BaseRecyclerItem;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import dagger.Lazy;
import java.util.List;
import javax.inject.Inject;
/* loaded from: classes6.dex */
public class BilobaDashboardViewModel implements BilobaViewModel {
    private static final String TAG = "BilobaDashboardViewModel";
    @Inject
    Lazy<BilobaMetricsService> bilobaMetricsService;
    @Inject
    Lazy<CardsStore> cardsStore;
    private final LiveData<CareActor> careActor;
    @Inject
    Lazy<CareActorsStore> careActorsStore;
    private final LiveData<CareActor> careContact;
    private final LiveData<CareActor> careRecipient;
    @Inject
    Lazy<CommsStore> commsStore;
    @Inject
    Lazy<CrashMetadata> crashMetadata;
    @Inject
    Lazy<CrashReporter> crashReporter;
    private final LiveData<EmergencyContact> emergencyContact;
    @Inject
    Lazy<FeatureServiceV2> featureServiceV2;
    @Inject
    Lazy<SettingsStore> settingsStore;
    private final SharedPreferences sharedPreferences;
    @Inject
    Lazy<TodaysActivitiesStore> todaysActivitiesStore;
    private final MediatorLiveData<Throwable> error = new MediatorLiveData<>();
    private final MediatorLiveData<Boolean> isCareActorAvailable = new MediatorLiveData<>();
    private final MediatorLiveData<Boolean> isCareContactAvailable = new MediatorLiveData<>();
    private final MediatorLiveData<Boolean> areCareActorsAvailable = new MediatorLiveData<>();
    private final MediatorLiveData<Boolean> isEmergencyContactAvailable = new MediatorLiveData<>();
    private final MediatorLiveData<Boolean> isDashboardLoading = new MediatorLiveData<>();
    private final MediatorLiveData<Boolean> shouldShowFinishSetupBanner = new MediatorLiveData<>();
    private final MediatorLiveData<Boolean> isRemoteManagementEnabled = new MediatorLiveData<>();
    private final MediatorLiveData<Boolean> isDropInEnabled = new MediatorLiveData<>();

    public BilobaDashboardViewModel(SharedPreferences sharedPreferences) {
        BilobaDependencies.inject(this);
        this.careActor = this.careActorsStore.mo358get().getCurrentActor();
        this.careContact = this.careActorsStore.mo358get().getCareContact();
        this.careRecipient = this.careActorsStore.mo358get().getCareRecipient();
        this.emergencyContact = this.commsStore.mo358get().getEmergencyContact();
        this.sharedPreferences = sharedPreferences;
        initLiveData();
    }

    private boolean checkForCareActors() {
        return this.isCareActorAvailable.getValue() == Boolean.TRUE && this.isCareContactAvailable.getValue() == Boolean.TRUE;
    }

    private void initLiveData() {
        LiveDataUtils.mergeLiveDataNotNull(this.error, this.careActorsStore.mo358get().getError(), this.cardsStore.mo358get().getError(), this.todaysActivitiesStore.mo358get().getError(), this.settingsStore.mo358get().getError(), this.commsStore.mo358get().getError());
        LiveDataUtils.mergeLiveDataEqualTrue(this.isDashboardLoading, this.careActorsStore.mo358get().getIsLoading(), this.cardsStore.mo358get().getIsLoading(), this.todaysActivitiesStore.mo358get().getIsLoading(), this.settingsStore.mo358get().getIsLoading(), this.commsStore.mo358get().getIsLoading());
        this.isCareActorAvailable.addSource(getCareActor(), new Observer() { // from class: com.amazon.alexa.biloba.view.dashboard.-$$Lambda$BilobaDashboardViewModel$tQz2MBlUDoJRlne_vZMzOP_Lfng
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                BilobaDashboardViewModel.this.lambda$initLiveData$0$BilobaDashboardViewModel((CareActor) obj);
            }
        });
        this.isCareContactAvailable.addSource(getCareContact(), new Observer() { // from class: com.amazon.alexa.biloba.view.dashboard.-$$Lambda$BilobaDashboardViewModel$1Dv6bb6iuqIbzHuu7WbLb9fi6tw
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                BilobaDashboardViewModel.this.lambda$initLiveData$1$BilobaDashboardViewModel((CareActor) obj);
            }
        });
        this.areCareActorsAvailable.addSource(getIsCareActorAvailable(), new Observer() { // from class: com.amazon.alexa.biloba.view.dashboard.-$$Lambda$BilobaDashboardViewModel$P5WojKzIcrkmXjEsQ9yMU7N0FGc
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                BilobaDashboardViewModel.this.lambda$initLiveData$2$BilobaDashboardViewModel((Boolean) obj);
            }
        });
        this.areCareActorsAvailable.addSource(getIsCareContactAvailable(), new Observer() { // from class: com.amazon.alexa.biloba.view.dashboard.-$$Lambda$BilobaDashboardViewModel$awu-_QQHawStLve9vsPtN0mI_d8
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                BilobaDashboardViewModel.this.lambda$initLiveData$3$BilobaDashboardViewModel((Boolean) obj);
            }
        });
        this.isEmergencyContactAvailable.addSource(this.emergencyContact, new Observer() { // from class: com.amazon.alexa.biloba.view.dashboard.-$$Lambda$BilobaDashboardViewModel$8b0VVzV6Qt9lXX2OSyYNObFyeGc
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                BilobaDashboardViewModel.this.lambda$initLiveData$4$BilobaDashboardViewModel((EmergencyContact) obj);
            }
        });
        this.shouldShowFinishSetupBanner.addSource(getIsEmergencyContactAvailable(), new Observer() { // from class: com.amazon.alexa.biloba.view.dashboard.-$$Lambda$BilobaDashboardViewModel$CUqI1cwEHCBuBF8Ak6cEp8YcfFU
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                BilobaDashboardViewModel.this.lambda$initLiveData$5$BilobaDashboardViewModel((Boolean) obj);
            }
        });
        this.shouldShowFinishSetupBanner.addSource(getCareActor(), new Observer() { // from class: com.amazon.alexa.biloba.view.dashboard.-$$Lambda$BilobaDashboardViewModel$gbsZBZZXNlZYtWOxCKLHV8_60qc
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                BilobaDashboardViewModel.this.lambda$initLiveData$6$BilobaDashboardViewModel((CareActor) obj);
            }
        });
        this.shouldShowFinishSetupBanner.addSource(getCareContact(), new Observer() { // from class: com.amazon.alexa.biloba.view.dashboard.-$$Lambda$BilobaDashboardViewModel$ZTota0IzjapyOpbsGl0eWmbsPeg
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                BilobaDashboardViewModel.this.lambda$initLiveData$7$BilobaDashboardViewModel((CareActor) obj);
            }
        });
        this.isRemoteManagementEnabled.addSource(this.careActor, new Observer() { // from class: com.amazon.alexa.biloba.view.dashboard.-$$Lambda$BilobaDashboardViewModel$jciM2u4oyI9Um4g3kBjL4vyMhpE
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                BilobaDashboardViewModel.this.lambda$initLiveData$8$BilobaDashboardViewModel((CareActor) obj);
            }
        });
        this.isDropInEnabled.addSource(getCareContact(), new Observer() { // from class: com.amazon.alexa.biloba.view.dashboard.-$$Lambda$BilobaDashboardViewModel$jcUJxClKLH8d26bYVrlXi06S_vI
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                BilobaDashboardViewModel.this.lambda$initLiveData$9$BilobaDashboardViewModel((CareActor) obj);
            }
        });
    }

    private boolean showFinishSetupBanner() {
        if (!isCareGiver() ? isCommsEnabledForCurrentActor() : isCommsEnabledForActors()) {
            if (this.isEmergencyContactAvailable.getValue() != Boolean.FALSE) {
                return false;
            }
        }
        return true;
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public void create(@Nullable Bundle bundle) {
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public void destroy() {
    }

    public void finishTimer(MobilyticsMetricsTimer mobilyticsMetricsTimer) {
        this.bilobaMetricsService.mo358get().finishTimer(mobilyticsMetricsTimer);
    }

    @VisibleForTesting
    LiveData<Boolean> getAreCareActorsAvailable() {
        return this.areCareActorsAvailable;
    }

    public String getCGAdminName() {
        CareActor value = this.careActorsStore.mo358get().getCareGiverAdmin().getValue();
        if (value == null) {
            return null;
        }
        return CareActorUtil.getFullName(value);
    }

    public LiveData<CareActor> getCareActor() {
        return this.careActor;
    }

    public LiveData<CareActor> getCareContact() {
        return this.careContact;
    }

    @VisibleForTesting
    String getCareContactCommsId() {
        return this.careActorsStore.mo358get().getCareContactCommsId();
    }

    @VisibleForTesting
    String getCareContactContactIdForCall() {
        return this.careActorsStore.mo358get().getSlicedCareContactContactId();
    }

    public LiveData<CareActor> getCareRecipient() {
        return this.careRecipient;
    }

    public String getDisplayName(LiveData<CareActor> liveData) {
        return CareActorUtil.getDisplayName(liveData.getValue());
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public LiveData<Throwable> getError() {
        return this.error;
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public String getErrorViewMetricName() {
        return "DashboardView.Error";
    }

    public String getGroupId() {
        return this.careActorsStore.mo358get().getCurrentGroupId();
    }

    @VisibleForTesting
    LiveData<Boolean> getIsCareActorAvailable() {
        return this.isCareActorAvailable;
    }

    @VisibleForTesting
    LiveData<Boolean> getIsCareContactAvailable() {
        return this.isCareContactAvailable;
    }

    public LiveData<Boolean> getIsDropInEnabled() {
        return this.isDropInEnabled;
    }

    @VisibleForTesting
    LiveData<Boolean> getIsEmergencyContactAvailable() {
        return this.isEmergencyContactAvailable;
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public LiveData<Boolean> getIsLoading() {
        return this.isDashboardLoading;
    }

    public LiveData<Boolean> getIsRemoteManagementEnabled() {
        return this.isRemoteManagementEnabled;
    }

    public LiveData<List<BaseRecyclerItem>> getLiveDashboardCards() {
        return this.cardsStore.mo358get().getLiveDashboardCards();
    }

    @VisibleForTesting
    LiveData<Boolean> getShouldShowFinishSetupBanner() {
        return this.shouldShowFinishSetupBanner;
    }

    public MutableLiveData<String> getTimeZone() {
        return this.settingsStore.mo358get().getTimeZone();
    }

    public String getTimeZoneDisplayName(MutableLiveData<String> mutableLiveData) {
        return this.settingsStore.mo358get().getTimeZoneDisplayName(mutableLiveData);
    }

    public LiveData<List<BaseRecyclerItem>> getTodaysActivities() {
        return this.todaysActivitiesStore.mo358get().getTodaysActivities();
    }

    public LiveData<Boolean> hasSubscription() {
        return this.careActorsStore.mo358get().getHasSubscription();
    }

    public boolean isCareGiver() {
        return this.careActorsStore.mo358get().getIsCareGiver().getValue() == Boolean.TRUE;
    }

    public boolean isCareGiverMultiCG() {
        return isMultiCGEnabled() && isCareGiver();
    }

    public boolean isCareRecipientMultiCG() {
        return isMultiCGEnabled() && !isCareGiver();
    }

    @VisibleForTesting
    boolean isCommsEnabledForActors() {
        return this.careActorsStore.mo358get().isCommsEnabledForActors();
    }

    @VisibleForTesting
    boolean isCommsEnabledForCurrentActor() {
        return this.careActorsStore.mo358get().isCommsEnabledForCurrentActor();
    }

    @VisibleForTesting
    boolean isCommsProvisionedWithCommsId() {
        return this.careActorsStore.mo358get().isCommsProvisionedWithCommsId();
    }

    @VisibleForTesting
    boolean isCommsProvisionedWithContactId() {
        return this.careActorsStore.mo358get().isCommsProvisionedWithContactId();
    }

    @VisibleForTesting
    boolean isDropInEnabledForCareContact() {
        return this.careActorsStore.mo358get().isDropInEnabledForCareContact();
    }

    public boolean isMultiCGEnabled() {
        Lazy<FeatureServiceV2> lazy = this.featureServiceV2;
        return lazy != null && lazy.mo358get().hasAccess("ALEXA_AGING_MULTI_CG_MOBILE", false);
    }

    @VisibleForTesting
    boolean isRemoteManagementEnabled() {
        return isCareGiver() && this.careActorsStore.mo358get().hasActiveSubscription() && this.careActorsStore.mo358get().isRemoteManagementPermissionEnabled();
    }

    public /* synthetic */ void lambda$initLiveData$0$BilobaDashboardViewModel(CareActor careActor) {
        this.isCareActorAvailable.setValue(Boolean.valueOf(careActor != null));
    }

    public /* synthetic */ void lambda$initLiveData$1$BilobaDashboardViewModel(CareActor careActor) {
        this.isCareContactAvailable.setValue(Boolean.valueOf(careActor != null));
    }

    public /* synthetic */ void lambda$initLiveData$2$BilobaDashboardViewModel(Boolean bool) {
        this.areCareActorsAvailable.setValue(Boolean.valueOf(checkForCareActors()));
    }

    public /* synthetic */ void lambda$initLiveData$3$BilobaDashboardViewModel(Boolean bool) {
        this.areCareActorsAvailable.setValue(Boolean.valueOf(checkForCareActors()));
    }

    public /* synthetic */ void lambda$initLiveData$4$BilobaDashboardViewModel(EmergencyContact emergencyContact) {
        this.isEmergencyContactAvailable.setValue(Boolean.valueOf(emergencyContact != null));
    }

    public /* synthetic */ void lambda$initLiveData$5$BilobaDashboardViewModel(Boolean bool) {
        this.shouldShowFinishSetupBanner.setValue(Boolean.valueOf(showFinishSetupBanner()));
    }

    public /* synthetic */ void lambda$initLiveData$6$BilobaDashboardViewModel(CareActor careActor) {
        this.shouldShowFinishSetupBanner.setValue(Boolean.valueOf(showFinishSetupBanner()));
    }

    public /* synthetic */ void lambda$initLiveData$7$BilobaDashboardViewModel(CareActor careActor) {
        this.shouldShowFinishSetupBanner.setValue(Boolean.valueOf(showFinishSetupBanner()));
    }

    public /* synthetic */ void lambda$initLiveData$8$BilobaDashboardViewModel(CareActor careActor) {
        this.isRemoteManagementEnabled.setValue(Boolean.valueOf(isRemoteManagementEnabled()));
    }

    public /* synthetic */ void lambda$initLiveData$9$BilobaDashboardViewModel(CareActor careActor) {
        this.isDropInEnabled.setValue(Boolean.valueOf(isDropInEnabledForCareContact()));
    }

    public void resetTTCFTimers() {
        this.bilobaMetricsService.mo358get().resetTTCFTimers();
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public void sendRequest() {
        LogUtils.i(TAG, "sendRequest for today's activity");
        this.careActorsStore.mo358get().requestCareActors();
        this.commsStore.mo358get().requestEmergencyContact(this.careActorsStore.mo358get().getCurrentGroupId());
        this.todaysActivitiesStore.mo358get().requestTodaysActivities(this.careActorsStore.mo358get().getCurrentGroupId());
        this.cardsStore.mo358get().fetchDashboardCards(this.careActorsStore.mo358get().getCurrentGroupId(), this.sharedPreferences);
        this.settingsStore.mo358get().getTimeZoneSettings(this.careActorsStore.mo358get().getCurrentGroupId());
    }

    public void setDashboardCards(List<BaseRecyclerItem> list) {
        this.cardsStore.mo358get().setLiveDashboardCards(list);
    }

    public boolean shouldShowAdminListItem() {
        return isCareRecipientMultiCG() && !AndroidUtils.isEmpty(getCGAdminName());
    }

    public boolean shouldShowAlertButton() {
        return !isMultiCGEnabled() || (isMultiCGEnabled() && this.careActorsStore.mo358get().isPermissionEnabled(PermissionConstants.LIST_ALERT_CONFIGURATIONS));
    }

    public boolean shouldShowCarePlusEmergencyView() {
        return hasSubscription().getValue() == Boolean.TRUE;
    }

    public boolean shouldShowEmergencySettingsAlert() {
        return !isMultiCGEnabled() && getShouldShowFinishSetupBanner().getValue() == Boolean.TRUE;
    }

    public boolean shouldShowOldCRDashboardHeader() {
        return !isMultiCGEnabled() && !isCareGiver();
    }

    public boolean shouldShowTimezonePicker() {
        return !isMultiCGEnabled() || (isMultiCGEnabled() && this.careActorsStore.mo358get().isPermissionEnabled(PermissionConstants.UPDATE_SETTINGS));
    }

    public MobilyticsMetricsTimer startTimer(@NonNull String str, long j) {
        MobilyticsMetricsTimer startTimer = this.bilobaMetricsService.mo358get().startTimer(str);
        startTimer.setEventTimestamp(j);
        return startTimer;
    }

    public void stopRecordingTTCF(@MetricsConstants.TTCFMetrics.TTCFMetric String str) {
        this.bilobaMetricsService.mo358get().stopRecordingTTCF(str);
    }

    @VisibleForTesting
    BilobaDashboardViewModel(Lazy<CareActorsStore> lazy, Lazy<TodaysActivitiesStore> lazy2, Lazy<CardsStore> lazy3, Lazy<SettingsStore> lazy4, Lazy<CommsStore> lazy5, Lazy<CrashMetadata> lazy6, Lazy<CrashReporter> lazy7, Lazy<BilobaMetricsService> lazy8, SharedPreferences sharedPreferences, Lazy<FeatureServiceV2> lazy9) {
        this.crashMetadata = lazy6;
        this.crashReporter = lazy7;
        this.bilobaMetricsService = lazy8;
        this.careActorsStore = lazy;
        this.careActor = lazy.mo358get().getCurrentActor();
        this.careContact = lazy.mo358get().getCareContact();
        this.careRecipient = lazy.mo358get().getCareRecipient();
        this.commsStore = lazy5;
        this.emergencyContact = lazy5.mo358get().getEmergencyContact();
        this.todaysActivitiesStore = lazy2;
        this.cardsStore = lazy3;
        this.sharedPreferences = sharedPreferences;
        this.settingsStore = lazy4;
        this.featureServiceV2 = lazy9;
        initLiveData();
    }
}
