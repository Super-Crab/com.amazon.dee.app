package com.amazon.alexa.photos.uploadV2;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.photos.uploadV2.EndpointsHelper;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
import com.amazon.clouddrive.android.core.interfaces.MetricRecordingType;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.cdasdk.CDClient;
import com.amazon.clouddrive.cdasdk.EndpointUtil;
import com.amazon.clouddrive.cdasdk.SpecificEndpointConfiguration;
import com.amazon.clouddrive.cdasdk.cds.account.GetEndpointRequest;
import com.amazon.clouddrive.cdasdk.cds.account.GetEndpointResponse;
import com.google.common.base.Strings;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.io.InterruptedIOException;
/* loaded from: classes9.dex */
public class EndpointsHelper {
    private static final String TAG = "EndpointsHelper";
    @NonNull
    private final CDClient cdClient;
    @NonNull
    private final CdaSdkPreferences cdasdkPreferences;
    @NonNull
    private final LazyComponent<IdentityService> identityService;
    @NonNull
    private final Metrics metrics;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public enum MetricsEvent {
        FetchEndpointMetException,
        FetchEndpointMetInterruption
    }

    public EndpointsHelper(@NonNull CDClient cDClient, @NonNull LazyComponent<IdentityService> lazyComponent, @NonNull Metrics metrics, @NonNull CdaSdkPreferences cdaSdkPreferences) {
        this.cdClient = cDClient;
        this.identityService = lazyComponent;
        this.metrics = metrics;
        this.cdasdkPreferences = cdaSdkPreferences;
    }

    @NonNull
    private Completable getEndpointCompletable() {
        return Completable.fromAction(new Action() { // from class: com.amazon.alexa.photos.uploadV2.-$$Lambda$EndpointsHelper$JqA97GBJ6gvWWjPM9VeGbCV3wZA
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                EndpointsHelper.this.lambda$getEndpointCompletable$0$EndpointsHelper();
            }
        });
    }

    private void recordMetrics(@NonNull final MetricsEvent metricsEvent) {
        Metrics metrics = this.metrics;
        String str = TAG;
        metricsEvent.getClass();
        metrics.recordSimpleEvent(str, new MetricName() { // from class: com.amazon.alexa.photos.uploadV2.-$$Lambda$lGmflLQGGACZXk5l6MVPcue6A6s
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            public final String getEventName() {
                return EndpointsHelper.MetricsEvent.this.name();
            }
        }, new MetricRecordingType[0]);
    }

    @VisibleForTesting
    @WorkerThread
    GetEndpointResponse fetchGetEndpointResponse() {
        return this.cdClient.getCDSCalls().getAccountCalls().getEndpoint(new GetEndpointRequest()).blockingGet();
    }

    @Nullable
    @VisibleForTesting
    @WorkerThread
    SpecificEndpointConfiguration getEndpointConfiguration() {
        SpecificEndpointConfiguration endpointConfiguration = this.cdasdkPreferences.getEndpointConfiguration();
        if (endpointConfiguration != null) {
            return endpointConfiguration;
        }
        try {
            return parseEndpointConfigurationFromResponse(fetchGetEndpointResponse());
        } catch (Exception e) {
            if (e.getCause() instanceof InterruptedIOException) {
                Log.e(TAG, "InterruptedIOException while attempting getEndpoints fetch. ", e);
                Thread.currentThread().interrupt();
                recordMetrics(MetricsEvent.FetchEndpointMetInterruption);
                return null;
            }
            Log.e(TAG, "Error occurred while requesting endpoints.", e);
            recordMetrics(MetricsEvent.FetchEndpointMetException);
            return null;
        }
    }

    public /* synthetic */ void lambda$getEndpointCompletable$0$EndpointsHelper() throws Throwable {
        SpecificEndpointConfiguration endpointConfiguration = this.cdasdkPreferences.getEndpointConfiguration();
        if (endpointConfiguration != null) {
            this.cdClient.updateEndpointConfiguration(endpointConfiguration);
            return;
        }
        UserIdentity user = this.identityService.mo10268get().getUser(TAG);
        if (Strings.isNullOrEmpty(user != null ? user.getDirectedId() : null)) {
            removeEndpointConfiguration();
            return;
        }
        SpecificEndpointConfiguration endpointConfiguration2 = getEndpointConfiguration();
        if (endpointConfiguration2 == null) {
            return;
        }
        this.cdClient.updateEndpointConfiguration(endpointConfiguration2);
        this.cdasdkPreferences.setEndpointConfiguration(endpointConfiguration2);
    }

    @VisibleForTesting
    SpecificEndpointConfiguration parseEndpointConfigurationFromResponse(@NonNull GetEndpointResponse getEndpointResponse) {
        return EndpointUtil.convertResponseToEndpoints(getEndpointResponse);
    }

    public void prepareEndpointConfigAsync() {
        getEndpointCompletable().onErrorComplete().subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeEndpointConfiguration() {
        this.cdasdkPreferences.removeEndpointConfiguration();
    }
}
