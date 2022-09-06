package com.amazon.alexa.biloba.view.comms;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.NotificationManagerCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.amazon.alexa.biloba.dependency.BilobaDependencies;
import com.amazon.alexa.biloba.generated.models.EmergencyContact;
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
import dagger.Lazy;
import javax.inject.Inject;
/* loaded from: classes6.dex */
public class EmergencyContactViewModel implements BilobaViewModel {
    private static final String TAG = "EmergencyContactViewModel";
    @Inject
    Lazy<BilobaMetricsService> bilobaMetricsService;
    @Inject
    Lazy<CareActorsStore> careActorsStore;
    @Inject
    Lazy<CommsStore> commsStore;
    @Inject
    Lazy<CrashMetadata> crashMetadata;
    @Inject
    Lazy<CrashReporter> crashReporter;
    private final NotificationManagerCompat notificationManager;
    @Inject
    Lazy<BilobaUrlResolver> urlResolver;
    private final MediatorLiveData<Throwable> error = new MediatorLiveData<>();
    private final MediatorLiveData<Boolean> isCareGiverEmergencyContact = new MediatorLiveData<>();
    private final MediatorLiveData<Boolean> showChangeEmergencyContactLink = new MediatorLiveData<>();
    private final MediatorLiveData<Boolean> isNotEmergencyContactWithNotification = new MediatorLiveData<>();
    private final MediatorLiveData<Boolean> isNotEmergencyContactWithoutNotification = new MediatorLiveData<>();
    private final MediatorLiveData<Boolean> isCommsSetupByCareRecipient = new MediatorLiveData<>();
    private final MediatorLiveData<Boolean> isCommsSetupByCareGiver = new MediatorLiveData<>();
    private final MediatorLiveData<Boolean> isEmergencyContactNotSet = new MediatorLiveData<>();
    private final MediatorLiveData<Boolean> showSendSetupLinkButton = new MediatorLiveData<>();
    private final MediatorLiveData<Boolean> showAddEmergencyContactButton = new MediatorLiveData<>();
    private final MediatorLiveData<Boolean> showChangeEmergencyContactButton = new MediatorLiveData<>();
    private final MutableLiveData<Boolean> isNotificationEnabled = new MutableLiveData<>();
    private final MediatorLiveData<Boolean> isLoading = new MediatorLiveData<>();

    public EmergencyContactViewModel(NotificationManagerCompat notificationManagerCompat) {
        BilobaDependencies.inject(this);
        this.notificationManager = notificationManagerCompat;
        initLiveData();
    }

    private Boolean checkShowChangeEmergencyContactButton() {
        boolean z = true;
        if (getEmergencyContact().getValue() == null || getNoticeCount() > 1) {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    private Boolean checkShowChangeEmergencyContactLink() {
        boolean z = true;
        if (getEmergencyContact().getValue() == null || getNoticeCount() <= 1) {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    private Boolean checkShowSendSetupLink() {
        return Boolean.valueOf(getIsCommsSetupByCareGiver().getValue() == Boolean.TRUE && getIsCommsSetupByCareRecipient().getValue() == Boolean.FALSE);
    }

    private int getNoticeCount() {
        int i = getIsCommsSetupByCareGiver().getValue() == Boolean.FALSE ? 1 : 0;
        if (getIsCommsSetupByCareRecipient().getValue() == Boolean.FALSE) {
            i++;
        }
        if (getIsNotEmergencyContactWithoutNotification().getValue() == Boolean.TRUE) {
            i++;
        }
        return getIsNotEmergencyContactWithNotification().getValue() == Boolean.TRUE ? i + 1 : i;
    }

    private void initLiveData() {
        LiveDataUtils.mergeLiveDataNotNull(this.error, this.commsStore.mo358get().getError(), this.careActorsStore.mo358get().getError());
        LiveDataUtils.mergeLiveDataEqualTrue(this.isLoading, this.commsStore.mo358get().getIsLoading(), this.careActorsStore.mo358get().getIsLoading());
        this.isCareGiverEmergencyContact.addSource(getEmergencyContact(), new Observer() { // from class: com.amazon.alexa.biloba.view.comms.-$$Lambda$EmergencyContactViewModel$g-Ku5kEcPuhcZob-2MV_I5SvS4k
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                EmergencyContactViewModel.this.lambda$initLiveData$0$EmergencyContactViewModel((EmergencyContact) obj);
            }
        });
        this.isCareGiverEmergencyContact.addSource(getCareGiver(), new Observer() { // from class: com.amazon.alexa.biloba.view.comms.-$$Lambda$EmergencyContactViewModel$d9V96rb_jzqaJoU07Or2zin7nNU
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                EmergencyContactViewModel.this.lambda$initLiveData$1$EmergencyContactViewModel((CareActor) obj);
            }
        });
        this.isEmergencyContactNotSet.addSource(getEmergencyContact(), new Observer() { // from class: com.amazon.alexa.biloba.view.comms.-$$Lambda$EmergencyContactViewModel$f3kjIyE8CW1k_ZttERrmBoAzj90
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                EmergencyContactViewModel.this.lambda$initLiveData$2$EmergencyContactViewModel((EmergencyContact) obj);
            }
        });
        this.isEmergencyContactNotSet.addSource(getIsCommsSetupByCareRecipient(), new Observer() { // from class: com.amazon.alexa.biloba.view.comms.-$$Lambda$EmergencyContactViewModel$_G8Uvi5GGZrpWr3reCOd0vEfXzI
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                EmergencyContactViewModel.this.lambda$initLiveData$3$EmergencyContactViewModel((Boolean) obj);
            }
        });
        this.isCommsSetupByCareRecipient.addSource(getCareRecipient(), new Observer() { // from class: com.amazon.alexa.biloba.view.comms.-$$Lambda$EmergencyContactViewModel$2aSH8Fsu4mjk19MSsQ8p4QtbsQ4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                EmergencyContactViewModel.this.lambda$initLiveData$4$EmergencyContactViewModel((CareActor) obj);
            }
        });
        this.isCommsSetupByCareGiver.addSource(getCareGiver(), new Observer() { // from class: com.amazon.alexa.biloba.view.comms.-$$Lambda$EmergencyContactViewModel$TgG2uyU15_RZOB_Y9Y7XC2RuQ3c
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                EmergencyContactViewModel.this.lambda$initLiveData$5$EmergencyContactViewModel((CareActor) obj);
            }
        });
        this.isNotEmergencyContactWithNotification.addSource(getIsCareGiverEmergencyContact(), new Observer() { // from class: com.amazon.alexa.biloba.view.comms.-$$Lambda$EmergencyContactViewModel$Mui3aeeuEX2tibdh38_VCHP1xfM
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                EmergencyContactViewModel.this.lambda$initLiveData$6$EmergencyContactViewModel((Boolean) obj);
            }
        });
        this.isNotEmergencyContactWithNotification.addSource(this.isNotificationEnabled, new Observer() { // from class: com.amazon.alexa.biloba.view.comms.-$$Lambda$EmergencyContactViewModel$IncDsqoJ3epSzSp1y8_gjmyAVGA
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                EmergencyContactViewModel.this.lambda$initLiveData$7$EmergencyContactViewModel((Boolean) obj);
            }
        });
        this.isNotEmergencyContactWithoutNotification.addSource(getIsCareGiverEmergencyContact(), new Observer() { // from class: com.amazon.alexa.biloba.view.comms.-$$Lambda$EmergencyContactViewModel$jJZa_MDSHXhPcktH14E8Z92TqWg
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                EmergencyContactViewModel.this.lambda$initLiveData$8$EmergencyContactViewModel((Boolean) obj);
            }
        });
        this.isNotEmergencyContactWithoutNotification.addSource(this.isNotificationEnabled, new Observer() { // from class: com.amazon.alexa.biloba.view.comms.-$$Lambda$EmergencyContactViewModel$etjUm4clnbyMl44R5lUrqaxxoes
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                EmergencyContactViewModel.this.lambda$initLiveData$9$EmergencyContactViewModel((Boolean) obj);
            }
        });
        this.showSendSetupLinkButton.addSource(getIsCommsSetupByCareGiver(), new Observer() { // from class: com.amazon.alexa.biloba.view.comms.-$$Lambda$EmergencyContactViewModel$CiS8-MrxRMuGwYIszQ25MgtYfYs
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                EmergencyContactViewModel.this.lambda$initLiveData$10$EmergencyContactViewModel((Boolean) obj);
            }
        });
        this.showSendSetupLinkButton.addSource(getIsCommsSetupByCareRecipient(), new Observer() { // from class: com.amazon.alexa.biloba.view.comms.-$$Lambda$EmergencyContactViewModel$WNtUY0kHXs6SsS-cMmN8kM90rF4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                EmergencyContactViewModel.this.lambda$initLiveData$11$EmergencyContactViewModel((Boolean) obj);
            }
        });
        MediatorLiveData<Boolean> mediatorLiveData = this.showAddEmergencyContactButton;
        LiveData<Boolean> isEmergencyContactNotSet = getIsEmergencyContactNotSet();
        final MediatorLiveData<Boolean> mediatorLiveData2 = this.showAddEmergencyContactButton;
        mediatorLiveData2.getClass();
        mediatorLiveData.addSource(isEmergencyContactNotSet, new Observer() { // from class: com.amazon.alexa.biloba.view.comms.-$$Lambda$-5I7U1_FDQyj7H63n6SUvFsp87U
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                MediatorLiveData.this.setValue((Boolean) obj);
            }
        });
        this.showChangeEmergencyContactButton.addSource(getEmergencyContact(), new Observer() { // from class: com.amazon.alexa.biloba.view.comms.-$$Lambda$EmergencyContactViewModel$9MHqdJECOlfHCH-CIhCTsib0y0o
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                EmergencyContactViewModel.this.lambda$initLiveData$12$EmergencyContactViewModel((EmergencyContact) obj);
            }
        });
        this.showChangeEmergencyContactButton.addSource(getIsNotEmergencyContactWithoutNotification(), new Observer() { // from class: com.amazon.alexa.biloba.view.comms.-$$Lambda$EmergencyContactViewModel$M27rZ1gr2HoU_cT3NlnIrKly0g8
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                EmergencyContactViewModel.this.lambda$initLiveData$13$EmergencyContactViewModel((Boolean) obj);
            }
        });
        this.showChangeEmergencyContactButton.addSource(getIsNotEmergencyContactWithNotification(), new Observer() { // from class: com.amazon.alexa.biloba.view.comms.-$$Lambda$EmergencyContactViewModel$83NWbelubqehp0NG0dvzCfc2E3M
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                EmergencyContactViewModel.this.lambda$initLiveData$14$EmergencyContactViewModel((Boolean) obj);
            }
        });
        this.showChangeEmergencyContactLink.addSource(getEmergencyContact(), new Observer() { // from class: com.amazon.alexa.biloba.view.comms.-$$Lambda$EmergencyContactViewModel$rds4HhQzSg0__JFE2Zvd28oA1vU
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                EmergencyContactViewModel.this.lambda$initLiveData$15$EmergencyContactViewModel((EmergencyContact) obj);
            }
        });
        this.showChangeEmergencyContactLink.addSource(getIsCommsSetupByCareGiver(), new Observer() { // from class: com.amazon.alexa.biloba.view.comms.-$$Lambda$EmergencyContactViewModel$Dm9EW6gUWGBVdR5qhqDkMFcdSm0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                EmergencyContactViewModel.this.lambda$initLiveData$16$EmergencyContactViewModel((Boolean) obj);
            }
        });
        this.showChangeEmergencyContactLink.addSource(getIsCommsSetupByCareRecipient(), new Observer() { // from class: com.amazon.alexa.biloba.view.comms.-$$Lambda$EmergencyContactViewModel$rnbEbLJW6MBLCiBYR5jgtmS1Opo
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                EmergencyContactViewModel.this.lambda$initLiveData$17$EmergencyContactViewModel((Boolean) obj);
            }
        });
        this.showChangeEmergencyContactLink.addSource(getIsNotEmergencyContactWithoutNotification(), new Observer() { // from class: com.amazon.alexa.biloba.view.comms.-$$Lambda$EmergencyContactViewModel$9YKy1YhaR2Tqo8JVKXeVbm0bMVQ
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                EmergencyContactViewModel.this.lambda$initLiveData$18$EmergencyContactViewModel((Boolean) obj);
            }
        });
        this.showChangeEmergencyContactLink.addSource(getIsNotEmergencyContactWithNotification(), new Observer() { // from class: com.amazon.alexa.biloba.view.comms.-$$Lambda$EmergencyContactViewModel$rAkCr9JjeAyZzAe2bsv-0KRHnls
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                EmergencyContactViewModel.this.lambda$initLiveData$19$EmergencyContactViewModel((Boolean) obj);
            }
        });
    }

    private Boolean isCareGiverEmergencyContact() {
        return Boolean.valueOf((getEmergencyContact().getValue() == null || getCareGiver().getValue() == null || !TextUtils.equals(getEmergencyContact().getValue().getPersonIdV1(), getCareGiver().getValue().getPersonIdV1())) ? false : true);
    }

    private Boolean isCareGiverNotEmergencyContact() {
        return Boolean.valueOf((getEmergencyContact().getValue() == null || getCareGiver().getValue() == null || TextUtils.equals(getEmergencyContact().getValue().getPersonIdV1(), getCareGiver().getValue().getPersonIdV1())) ? false : true);
    }

    private Boolean isCareGiverNotEmergencyContactWithNotification() {
        return Boolean.valueOf(isCareGiverNotEmergencyContact().booleanValue() && this.isNotificationEnabled.getValue() == Boolean.TRUE);
    }

    private Boolean isCareGiverNotEmergencyContactWithoutNotification() {
        return Boolean.valueOf(isCareGiverNotEmergencyContact().booleanValue() && this.isNotificationEnabled.getValue() == Boolean.FALSE);
    }

    private Boolean shouldShowAddEmergencyContact() {
        return Boolean.valueOf(getEmergencyContact().getValue() == null && getIsCommsSetupByCareRecipient().getValue() == Boolean.TRUE);
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public void create(@Nullable Bundle bundle) {
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public void destroy() {
    }

    public LiveData<CareActor> getCareGiver() {
        return this.careActorsStore.mo358get().getCareGiver();
    }

    public LiveData<CareActor> getCareRecipient() {
        return this.careActorsStore.mo358get().getCareRecipient();
    }

    public String getDisplayName(LiveData<CareActor> liveData) {
        return CareActorUtil.getDisplayName(liveData.getValue());
    }

    public LiveData<EmergencyContact> getEmergencyContact() {
        return this.commsStore.mo358get().getEmergencyContact();
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public LiveData<Throwable> getError() {
        return this.error;
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public String getErrorViewMetricName() {
        return "EmergencyContactView.Error";
    }

    public LiveData<Boolean> getIsCareGiver() {
        return this.careActorsStore.mo358get().getIsCareGiver();
    }

    public LiveData<Boolean> getIsCareGiverEmergencyContact() {
        return this.isCareGiverEmergencyContact;
    }

    public LiveData<Boolean> getIsCommsSetupByCareGiver() {
        return this.isCommsSetupByCareGiver;
    }

    public LiveData<Boolean> getIsCommsSetupByCareRecipient() {
        return this.isCommsSetupByCareRecipient;
    }

    public LiveData<Boolean> getIsEmergencyContactNotSet() {
        return this.isEmergencyContactNotSet;
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

    public LiveData<Boolean> getShowAddEmergencyContactButton() {
        return this.showAddEmergencyContactButton;
    }

    public LiveData<Boolean> getShowChangeEmergencyContactButton() {
        return this.showChangeEmergencyContactButton;
    }

    public LiveData<Boolean> getShowChangeEmergencyContactLink() {
        return this.showChangeEmergencyContactLink;
    }

    public LiveData<Boolean> getShowSendSetupLinkButton() {
        return this.showSendSetupLinkButton;
    }

    public /* synthetic */ void lambda$initLiveData$0$EmergencyContactViewModel(EmergencyContact emergencyContact) {
        this.isCareGiverEmergencyContact.setValue(isCareGiverEmergencyContact());
    }

    public /* synthetic */ void lambda$initLiveData$1$EmergencyContactViewModel(CareActor careActor) {
        this.isCareGiverEmergencyContact.setValue(isCareGiverEmergencyContact());
    }

    public /* synthetic */ void lambda$initLiveData$10$EmergencyContactViewModel(Boolean bool) {
        this.showSendSetupLinkButton.setValue(checkShowSendSetupLink());
    }

    public /* synthetic */ void lambda$initLiveData$11$EmergencyContactViewModel(Boolean bool) {
        this.showSendSetupLinkButton.setValue(checkShowSendSetupLink());
    }

    public /* synthetic */ void lambda$initLiveData$12$EmergencyContactViewModel(EmergencyContact emergencyContact) {
        this.showChangeEmergencyContactButton.setValue(checkShowChangeEmergencyContactButton());
    }

    public /* synthetic */ void lambda$initLiveData$13$EmergencyContactViewModel(Boolean bool) {
        this.showChangeEmergencyContactButton.setValue(checkShowChangeEmergencyContactButton());
    }

    public /* synthetic */ void lambda$initLiveData$14$EmergencyContactViewModel(Boolean bool) {
        this.showChangeEmergencyContactButton.setValue(checkShowChangeEmergencyContactButton());
    }

    public /* synthetic */ void lambda$initLiveData$15$EmergencyContactViewModel(EmergencyContact emergencyContact) {
        this.showChangeEmergencyContactLink.setValue(checkShowChangeEmergencyContactLink());
    }

    public /* synthetic */ void lambda$initLiveData$16$EmergencyContactViewModel(Boolean bool) {
        this.showChangeEmergencyContactLink.setValue(checkShowChangeEmergencyContactLink());
    }

    public /* synthetic */ void lambda$initLiveData$17$EmergencyContactViewModel(Boolean bool) {
        this.showChangeEmergencyContactLink.setValue(checkShowChangeEmergencyContactLink());
    }

    public /* synthetic */ void lambda$initLiveData$18$EmergencyContactViewModel(Boolean bool) {
        this.showChangeEmergencyContactLink.setValue(checkShowChangeEmergencyContactLink());
    }

    public /* synthetic */ void lambda$initLiveData$19$EmergencyContactViewModel(Boolean bool) {
        this.showChangeEmergencyContactLink.setValue(checkShowChangeEmergencyContactLink());
    }

    public /* synthetic */ void lambda$initLiveData$2$EmergencyContactViewModel(EmergencyContact emergencyContact) {
        this.isEmergencyContactNotSet.setValue(shouldShowAddEmergencyContact());
    }

    public /* synthetic */ void lambda$initLiveData$3$EmergencyContactViewModel(Boolean bool) {
        this.isEmergencyContactNotSet.setValue(shouldShowAddEmergencyContact());
    }

    public /* synthetic */ void lambda$initLiveData$4$EmergencyContactViewModel(CareActor careActor) {
        this.isCommsSetupByCareRecipient.setValue(Boolean.valueOf((careActor == null || careActor.getCommsId() == null || !careActor.getIsCommsProvisioned().booleanValue()) ? false : true));
    }

    public /* synthetic */ void lambda$initLiveData$5$EmergencyContactViewModel(CareActor careActor) {
        this.isCommsSetupByCareGiver.setValue(Boolean.valueOf((careActor == null || careActor.getCommsId() == null || !careActor.getIsCommsProvisioned().booleanValue()) ? false : true));
    }

    public /* synthetic */ void lambda$initLiveData$6$EmergencyContactViewModel(Boolean bool) {
        this.isNotEmergencyContactWithNotification.setValue(isCareGiverNotEmergencyContactWithNotification());
    }

    public /* synthetic */ void lambda$initLiveData$7$EmergencyContactViewModel(Boolean bool) {
        this.isNotEmergencyContactWithoutNotification.setValue(isCareGiverNotEmergencyContactWithNotification());
    }

    public /* synthetic */ void lambda$initLiveData$8$EmergencyContactViewModel(Boolean bool) {
        this.isNotEmergencyContactWithoutNotification.setValue(isCareGiverNotEmergencyContactWithoutNotification());
    }

    public /* synthetic */ void lambda$initLiveData$9$EmergencyContactViewModel(Boolean bool) {
        this.isNotEmergencyContactWithoutNotification.setValue(isCareGiverNotEmergencyContactWithoutNotification());
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public void sendRequest() {
        this.isNotificationEnabled.setValue(Boolean.valueOf(this.notificationManager.areNotificationsEnabled()));
        this.careActorsStore.mo358get().requestCareActors();
        this.commsStore.mo358get().requestEmergencyContact(this.careActorsStore.mo358get().getCurrentGroupId());
    }

    @VisibleForTesting
    EmergencyContactViewModel(Lazy<CommsStore> lazy, Lazy<CareActorsStore> lazy2, NotificationManagerCompat notificationManagerCompat, Lazy<BilobaUrlResolver> lazy3, Lazy<CrashMetadata> lazy4, Lazy<CrashReporter> lazy5, Lazy<BilobaMetricsService> lazy6) {
        this.notificationManager = notificationManagerCompat;
        this.urlResolver = lazy3;
        this.commsStore = lazy;
        this.careActorsStore = lazy2;
        this.crashMetadata = lazy4;
        this.crashReporter = lazy5;
        this.bilobaMetricsService = lazy6;
        initLiveData();
    }
}
