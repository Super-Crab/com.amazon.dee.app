package com.amazon.alexa.biloba.view.account;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import com.amazon.alexa.biloba.dependency.BilobaDependencies;
import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.model.CareActor;
import com.amazon.alexa.biloba.storage.CareActorsStore;
import com.amazon.alexa.biloba.utils.CareActorUtil;
import com.amazon.alexa.biloba.view.BilobaViewModel;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import dagger.Lazy;
import javax.inject.Inject;
/* loaded from: classes6.dex */
public class MembershipViewModel implements BilobaViewModel {
    private static final String TAG = "MembershipViewModel";
    @Inject
    Lazy<BilobaMetricsService> bilobaMetricsService;
    @Inject
    Lazy<CareActorsStore> careActorsStore;
    @Inject
    Lazy<CrashMetadata> crashMetadata;
    @Inject
    Lazy<CrashReporter> crashReporter;

    public MembershipViewModel() {
        BilobaDependencies.inject(this);
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public void create(@Nullable Bundle bundle) {
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public void destroy() {
    }

    public LiveData<CareActor> getCareActor() {
        return this.careActorsStore.mo358get().getCurrentActor();
    }

    public LiveData<CareActor> getCareContact() {
        return this.careActorsStore.mo358get().getCareContact();
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

    public String getEnclosedNickName(LiveData<CareActor> liveData) {
        return CareActorUtil.getEnclosedNickName(liveData.getValue());
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public LiveData<Throwable> getError() {
        return this.careActorsStore.mo358get().getError();
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public String getErrorViewMetricName() {
        return null;
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public LiveData<Boolean> getIsLoading() {
        return this.careActorsStore.mo358get().getIsLoading();
    }

    public LiveData<Boolean> getLeaveGroupSuccess() {
        return this.careActorsStore.mo358get().getLeaveGroupSuccess();
    }

    public boolean isCareGiver() {
        return this.careActorsStore.mo358get().getIsCareGiver().getValue() == Boolean.TRUE;
    }

    public void leaveGroup() {
        this.careActorsStore.mo358get().leaveGroupAsync();
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public void sendRequest() {
        this.careActorsStore.mo358get().requestCareActors();
    }

    @VisibleForTesting
    MembershipViewModel(Lazy<CareActorsStore> lazy, Lazy<CrashMetadata> lazy2, Lazy<CrashReporter> lazy3, Lazy<BilobaMetricsService> lazy4) {
        this.careActorsStore = lazy;
        this.crashMetadata = lazy2;
        this.crashReporter = lazy3;
        this.bilobaMetricsService = lazy4;
    }
}
