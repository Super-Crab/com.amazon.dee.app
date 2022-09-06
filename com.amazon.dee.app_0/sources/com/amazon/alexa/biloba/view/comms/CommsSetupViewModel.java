package com.amazon.alexa.biloba.view.comms;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import com.amazon.alexa.biloba.dependency.BilobaDependencies;
import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.model.CareActor;
import com.amazon.alexa.biloba.service.BilobaUrlResolver;
import com.amazon.alexa.biloba.storage.CareActorsStore;
import com.amazon.alexa.biloba.utils.CareActorUtil;
import com.amazon.alexa.biloba.view.BilobaViewModel;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import dagger.Lazy;
import javax.inject.Inject;
/* loaded from: classes6.dex */
public class CommsSetupViewModel implements BilobaViewModel {
    private static final String TAG = "CommsSetupViewModel";
    @Inject
    Lazy<BilobaMetricsService> bilobaMetricsService;
    @Inject
    Lazy<CareActorsStore> careActorsStore;
    @Inject
    Lazy<CrashMetadata> crashMetadata;
    @Inject
    Lazy<CrashReporter> crashReporter;
    @Inject
    Lazy<BilobaUrlResolver> urlResolver;

    public CommsSetupViewModel() {
        BilobaDependencies.inject(this);
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

    public String getDisplayName(LiveData<CareActor> liveData) {
        return CareActorUtil.getDisplayName(liveData.getValue());
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public LiveData<Throwable> getError() {
        return null;
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public String getErrorViewMetricName() {
        return null;
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public LiveData<Boolean> getIsLoading() {
        return null;
    }

    public String getShareLinkUrl() {
        return this.urlResolver.mo358get().getCommsSetupUrl();
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public void sendRequest() {
    }

    @VisibleForTesting
    CommsSetupViewModel(Lazy<CareActorsStore> lazy, Lazy<BilobaUrlResolver> lazy2, Lazy<CrashMetadata> lazy3, Lazy<CrashReporter> lazy4, Lazy<BilobaMetricsService> lazy5) {
        this.urlResolver = lazy2;
        this.careActorsStore = lazy;
        this.crashMetadata = lazy3;
        this.crashReporter = lazy4;
        this.bilobaMetricsService = lazy5;
    }
}
