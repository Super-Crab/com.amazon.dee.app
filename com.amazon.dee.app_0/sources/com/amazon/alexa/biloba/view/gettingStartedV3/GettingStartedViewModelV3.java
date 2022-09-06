package com.amazon.alexa.biloba.view.gettingStartedV3;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import com.amazon.alexa.biloba.dependency.BilobaDependencies;
import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.metrics.MetricsConstants;
import com.amazon.alexa.biloba.view.BilobaViewModel;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import dagger.Lazy;
import javax.inject.Inject;
/* loaded from: classes6.dex */
public class GettingStartedViewModelV3 implements BilobaViewModel {
    private static final String TAG = "GettingStartedViewModelV3";
    @Inject
    Lazy<BilobaMetricsService> bilobaMetricsService;
    @Inject
    Lazy<EnvironmentService> environmentService;

    public GettingStartedViewModelV3() {
        BilobaDependencies.inject(this);
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public void create(@Nullable Bundle bundle) {
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public void destroy() {
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public LiveData<Throwable> getError() {
        return null;
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public String getErrorViewMetricName() {
        return null;
    }

    public String getExternalWebViewUrl() {
        String str;
        String buildStage = this.environmentService.mo358get().getBuildStage();
        int hashCode = buildStage.hashCode();
        if (hashCode == 3020272) {
            str = "beta";
        } else if (hashCode == 3449687) {
            str = "prod";
        } else if (hashCode != 98120615) {
            return "https://www.amazon.com/b/ref=ods_surl_ac?&node=21390531011";
        } else {
            str = "gamma";
        }
        buildStage.equals(str);
        return "https://www.amazon.com/b/ref=ods_surl_ac?&node=21390531011";
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public LiveData<Boolean> getIsLoading() {
        return null;
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public void sendRequest() {
    }

    public void stopRecordingTTCF() {
        this.bilobaMetricsService.mo358get().stopRecordingTTCF(MetricsConstants.TTCFMetrics.BILOBA_GETTING_STARTED);
    }

    @VisibleForTesting
    GettingStartedViewModelV3(Lazy<EnvironmentService> lazy, Lazy<BilobaMetricsService> lazy2) {
        this.environmentService = lazy;
        this.bilobaMetricsService = lazy2;
    }
}
