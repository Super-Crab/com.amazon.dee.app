package com.amazon.alexa.biloba.view.startup;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.amazon.alexa.biloba.dependency.BilobaDependencies;
import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.model.CareActor;
import com.amazon.alexa.biloba.model.PersonIdentitySetupResponse;
import com.amazon.alexa.biloba.model.PersonIdentityStep;
import com.amazon.alexa.biloba.routing.DeferredRoutingHelper;
import com.amazon.alexa.biloba.service.PasscodeApi;
import com.amazon.alexa.biloba.storage.CareActorsStore;
import com.amazon.alexa.biloba.storage.IdentityLocalDataStore;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.biloba.view.BilobaViewModel;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.alexa.routing.api.RoutingService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
import javax.inject.Inject;
import rx.functions.Action1;
/* loaded from: classes6.dex */
public class StartupViewModel implements BilobaViewModel {
    private static final String TAG = "StartupViewModel";
    @Inject
    Lazy<BilobaMetricsService> bilobaMetricsService;
    @Inject
    Lazy<CareActorsStore> careActorsStore;
    @Inject
    Lazy<CrashMetadata> crashMetadata;
    @Inject
    Lazy<CrashReporter> crashReporter;
    @Inject
    Lazy<DeferredRoutingHelper> deferredRoutingHelper;
    @Inject
    Lazy<FeatureServiceV2> featureServiceV2;
    @Inject
    Lazy<IdentityLocalDataStore> identityLocalDataStore;
    @Inject
    Lazy<PasscodeApi> passcodeApi;
    @Inject
    Lazy<RoutingService> routingService;
    private final MutableLiveData<Integer> passcodeState = new MutableLiveData<>();
    private boolean isPasscodeAuthorizedByRouteArg = false;
    private boolean receivedCareContact = false;
    private boolean receivedCGCRStatus = false;

    public StartupViewModel() {
        BilobaDependencies.inject(this);
        this.passcodeState.setValue(-1);
    }

    private boolean isPasscodeAuthorized(String str) {
        if (isPasscodeAuthorizedByStorage()) {
            return true;
        }
        String persistedSessionId = this.identityLocalDataStore.mo358get().getPersistedSessionId();
        if (persistedSessionId != null) {
            String str2 = TAG;
            LogUtils.d(str2, "found a session ID in storage with value=" + persistedSessionId);
            if (this.isPasscodeAuthorizedByRouteArg && str.equals(persistedSessionId)) {
                LogUtils.d(TAG, "granting access to Care Hub based on:\n - the session IDs match\n - previously receiving the isPersonalPasscodeVerified=true route argument");
                if (!this.identityLocalDataStore.mo358get().persistAuthorizationStatus(true)) {
                    this.passcodeState.postValue(1);
                }
                return true;
            }
            if (!this.identityLocalDataStore.mo358get().persistSessionId(str)) {
                this.passcodeState.postValue(1);
            }
            return false;
        }
        LogUtils.d(TAG, "key \"last_session_id\" not found in local storage");
        return false;
    }

    private boolean isPasscodeAuthorizedByStorage() {
        if (this.identityLocalDataStore.mo358get().isPasscodeAuthorized()) {
            LogUtils.d(TAG, "granting access to Care Hub based on:\n - reading an authorized session ID in \"passcode_authorized\" local storage");
            return true;
        }
        return false;
    }

    public void clear() {
        this.careActorsStore.mo358get().clear();
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

    public LiveData<CareActor> getCareContact() {
        return this.careActorsStore.mo358get().getCareContact();
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public LiveData<Throwable> getError() {
        return this.careActorsStore.mo358get().getError();
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public String getErrorViewMetricName() {
        return "StartupView.Error";
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public LiveData<Boolean> getIsLoading() {
        return this.careActorsStore.mo358get().getIsLoading();
    }

    public LiveData<Integer> getPasscodeStatus() {
        return this.passcodeState;
    }

    public LiveData<Boolean> isCareGiver() {
        return this.careActorsStore.mo358get().getIsCareGiver();
    }

    public boolean isMultiCGEnabled() {
        return this.careActorsStore.mo358get().isMultiCGEnabled();
    }

    public boolean isReadyToRouteToBiloba() {
        boolean z = false;
        if (!this.receivedCGCRStatus) {
            LogUtils.d(TAG, "Not ready to route: still waiting for care giver or care recipient status");
            return false;
        } else if (!this.receivedCareContact) {
            LogUtils.d(TAG, "Not ready to route: still waiting for the care contact");
            return false;
        } else {
            if (this.careActorsStore.mo358get().getIsCareGiver().getValue() == Boolean.TRUE) {
                LogUtils.d(TAG, "This is a care giver's account.");
                if ((this.passcodeState.getValue() != null ? this.passcodeState.getValue().intValue() : 1) == 4) {
                    z = true;
                }
                LogUtils.d(TAG, "passcode required and authorized? " + z);
                return z;
            }
            LogUtils.d(TAG, "Ready to route and no passcode is required.");
            return true;
        }
    }

    public /* synthetic */ void lambda$null$0$StartupViewModel(String str, PersonIdentitySetupResponse personIdentitySetupResponse) {
        String str2 = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Got response from passcode api: ");
        outline107.append(personIdentitySetupResponse.toString());
        LogUtils.d(str2, outline107.toString());
        for (PersonIdentityStep personIdentityStep : personIdentitySetupResponse.getWorkflow().getSteps()) {
            String str3 = TAG;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("step: ");
            outline1072.append(personIdentityStep.toString());
            LogUtils.d(str3, outline1072.toString());
            if (personIdentityStep.getType().equals(PersonIdentityStep.PersonIdentityStepTypeEnum.VOICE_CODE)) {
                if (personIdentityStep.getConfig().getSetup() == Boolean.TRUE) {
                    if (isPasscodeAuthorized(str)) {
                        LogUtils.d(TAG, "passcode is authorized");
                        this.passcodeState.postValue(4);
                    } else {
                        LogUtils.d(TAG, "passcode setup but not authorized");
                        this.passcodeState.postValue(3);
                    }
                } else {
                    LogUtils.d(TAG, "passcode is not setup");
                    this.passcodeState.postValue(2);
                }
            }
        }
    }

    public /* synthetic */ void lambda$null$1$StartupViewModel(Throwable th) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed with error: ");
        outline107.append(th.getLocalizedMessage());
        LogUtils.e(str, outline107.toString());
        this.passcodeState.postValue(1);
    }

    public /* synthetic */ void lambda$requestPasscodeStatus$2$StartupViewModel(CareActor careActor, final String str) {
        String str2 = TAG;
        LogUtils.d(str2, "got current session id=" + str);
        if (isPasscodeAuthorized(str)) {
            this.passcodeState.postValue(4);
            return;
        }
        this.identityLocalDataStore.mo358get().persistSessionId(str);
        this.passcodeState.postValue(0);
        this.passcodeApi.mo358get().getPasscodeAuthObservable(careActor.getPersonIdV1()).subscribe(new Action1() { // from class: com.amazon.alexa.biloba.view.startup.-$$Lambda$StartupViewModel$eRBozWcockGYCJjlcBTx65SRoEU
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                StartupViewModel.this.lambda$null$0$StartupViewModel(str, (PersonIdentitySetupResponse) obj);
            }
        }, new Action1() { // from class: com.amazon.alexa.biloba.view.startup.-$$Lambda$StartupViewModel$4APUe7NqvgeBHyUJuIZWO3QstRU
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                StartupViewModel.this.lambda$null$1$StartupViewModel((Throwable) obj);
            }
        });
    }

    public void navigateToDeferredRoute() {
        if (this.deferredRoutingHelper.mo358get().getDeferredRouteContext() != null) {
            this.deferredRoutingHelper.mo358get().processDeferredRouteContext();
            resetTTCFTimers();
        }
    }

    public void notifyReceivedCRCGStatus() {
        this.receivedCGCRStatus = true;
    }

    public void notifyReceivedCareContact() {
        this.receivedCareContact = true;
    }

    public void requestPasscodeStatus() {
        final CareActor value = this.careActorsStore.mo358get().getCurrentActor().getValue();
        if (value == null) {
            LogUtils.e(TAG, "current actor is null!?");
            this.passcodeState.setValue(1);
        } else if (isPasscodeAuthorizedByStorage()) {
            this.passcodeState.postValue(4);
        } else {
            this.identityLocalDataStore.mo358get().getCurrentSessionId(new IdentityLocalDataStore.SessionIdHandler() { // from class: com.amazon.alexa.biloba.view.startup.-$$Lambda$StartupViewModel$l4WMNgC1mk3Kgh2js0T-HHYZVic
                @Override // com.amazon.alexa.biloba.storage.IdentityLocalDataStore.SessionIdHandler
                public final void handleSessionId(String str) {
                    StartupViewModel.this.lambda$requestPasscodeStatus$2$StartupViewModel(value, str);
                }
            });
        }
    }

    public void resetTTCFTimers() {
        this.bilobaMetricsService.mo358get().resetTTCFTimers();
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public void sendRequest() {
        this.careActorsStore.mo358get().requestCareActors();
    }

    public void setPasscodeAuthorizedByRouteArg(boolean z) {
        this.isPasscodeAuthorizedByRouteArg = z;
    }

    public boolean shouldShowLoneCr() {
        return this.careActorsStore.mo358get().getHasSubscription().getValue() == Boolean.TRUE;
    }

    public MobilyticsMetricsTimer startTimer(@NonNull String str, long j) {
        MobilyticsMetricsTimer startTimer = this.bilobaMetricsService.mo358get().startTimer(str);
        startTimer.setEventTimestamp(j);
        return startTimer;
    }

    @VisibleForTesting
    StartupViewModel(Lazy<CareActorsStore> lazy, Lazy<CrashMetadata> lazy2, Lazy<CrashReporter> lazy3, Lazy<BilobaMetricsService> lazy4, Lazy<IdentityLocalDataStore> lazy5, Lazy<PasscodeApi> lazy6, Lazy<FeatureServiceV2> lazy7, Lazy<DeferredRoutingHelper> lazy8, Lazy<RoutingService> lazy9) {
        this.careActorsStore = lazy;
        this.crashMetadata = lazy2;
        this.crashReporter = lazy3;
        this.bilobaMetricsService = lazy4;
        this.identityLocalDataStore = lazy5;
        this.passcodeApi = lazy6;
        this.passcodeState.setValue(-1);
        this.featureServiceV2 = lazy7;
        this.deferredRoutingHelper = lazy8;
        this.routingService = lazy9;
    }
}
