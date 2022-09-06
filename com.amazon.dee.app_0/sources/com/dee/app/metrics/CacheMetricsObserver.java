package com.dee.app.metrics;

import android.util.Log;
import androidx.annotation.NonNull;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.cachemanager.CacheEvent;
import com.dee.app.metrics.MetricComponentName;
import com.dee.app.metrics.MetricDescriptor;
import com.dee.app.metrics.MetricName;
import com.dee.app.metrics.MetricsConstants;
import com.google.common.base.Preconditions;
import rx.Observer;
/* loaded from: classes2.dex */
public class CacheMetricsObserver implements Observer<CacheEvent> {
    private static final String TAG = "CacheMetricsObserver";
    @NonNull
    private final MetricsServiceV2 metricsService;
    private static final MetricComponentName CACHE_SERVICE_GET_METRIC_COMPONENT_NAME = new MetricComponentName.Builder().withCategoryId(MetricsConstants.CategoryId.CACHE_SERVICE).withMethod(MetricsConstants.Method.CACHE_GET).build();
    private static final MetricComponentName CACHE_SERVICE_PUT_METRIC_COMPONENT_NAME = new MetricComponentName.Builder().withCategoryId(MetricsConstants.CategoryId.CACHE_SERVICE).withMethod(MetricsConstants.Method.CACHE_PUT).build();
    private static final MetricComponentName CACHE_SERVICE_CLEAR_METRIC_COMPONENT_NAME = new MetricComponentName.Builder().withCategoryId(MetricsConstants.CategoryId.CACHE_SERVICE).withMethod(MetricsConstants.Method.CACHE_CLEAR).build();
    private static final MetricName CORE_GET_ERROR_METRIC_NAME = new MetricName.Builder(MetricsConstants.Id.CACHE_GET_ERROR).withModule(MetricsConstants.Module.CORE).withSource(MetricsConstants.Source.NATIVE_ELEMENTS).build();
    private static final MetricName CORE_PUT_ERROR_METRIC_NAME = new MetricName.Builder(MetricsConstants.Id.CACHE_PUT_ERROR).withModule(MetricsConstants.Module.CORE).withSource(MetricsConstants.Source.NATIVE_ELEMENTS).build();
    private static final MetricName CORE_CLEAR_ERROR_METRIC_NAME = new MetricName.Builder(MetricsConstants.Id.CACHE_CLEAR_ERROR).withModule(MetricsConstants.Module.CORE).withSource(MetricsConstants.Source.NATIVE_ELEMENTS).build();

    public CacheMetricsObserver(@NonNull MetricsServiceV2 metricsServiceV2) {
        Preconditions.checkNotNull(metricsServiceV2);
        this.metricsService = metricsServiceV2;
    }

    private void processClearError(CacheEvent cacheEvent) {
        Preconditions.checkArgument(cacheEvent.type == 6);
        MetricDescriptor build = new MetricDescriptor.Builder(new MetricName.Builder(MetricsConstants.Id.CACHE_CLEAR_ERROR).withModule(cacheEvent.cacheMetadata.module).withSource(MetricsConstants.Source.NATIVE_ELEMENTS).build(), CACHE_SERVICE_CLEAR_METRIC_COMPONENT_NAME).build();
        MetricDescriptor build2 = new MetricDescriptor.Builder(CORE_CLEAR_ERROR_METRIC_NAME, CACHE_SERVICE_CLEAR_METRIC_COMPONENT_NAME).build();
        this.metricsService.recordCount(build, 1.0d);
        this.metricsService.recordCount(build2, 1.0d);
    }

    private void processGetError(CacheEvent cacheEvent) {
        Preconditions.checkArgument(cacheEvent.type == 3);
        MetricDescriptor build = new MetricDescriptor.Builder(new MetricName.Builder(MetricsConstants.Id.CACHE_GET_ERROR).withModule(cacheEvent.cacheMetadata.module).withSource(MetricsConstants.Source.NATIVE_ELEMENTS).build(), CACHE_SERVICE_GET_METRIC_COMPONENT_NAME).build();
        MetricDescriptor build2 = new MetricDescriptor.Builder(CORE_GET_ERROR_METRIC_NAME, CACHE_SERVICE_GET_METRIC_COMPONENT_NAME).build();
        this.metricsService.recordCount(build, 1.0d);
        this.metricsService.recordCount(build2, 1.0d);
    }

    private void processHitOrMissEvent(CacheEvent cacheEvent) {
        int i = cacheEvent.type;
        boolean z = false;
        Preconditions.checkArgument(i == 0 || i == 1);
        MetricDescriptor build = new MetricDescriptor.Builder(new MetricName.Builder(MetricsConstants.Id.CACHE_MISS).withModule(cacheEvent.cacheMetadata.module).withSource(MetricsConstants.Source.NATIVE_ELEMENTS).build(), CACHE_SERVICE_GET_METRIC_COMPONENT_NAME).withEmissionFactor(10).build();
        if (cacheEvent.type == 1) {
            z = true;
        }
        this.metricsService.recordLimit(build, z);
    }

    private void processPutError(CacheEvent cacheEvent) {
        Preconditions.checkArgument(cacheEvent.type == 4);
        MetricDescriptor build = new MetricDescriptor.Builder(new MetricName.Builder(MetricsConstants.Id.CACHE_PUT_ERROR).withModule(cacheEvent.cacheMetadata.module).withSource(MetricsConstants.Source.NATIVE_ELEMENTS).build(), CACHE_SERVICE_PUT_METRIC_COMPONENT_NAME).build();
        MetricDescriptor build2 = new MetricDescriptor.Builder(CORE_PUT_ERROR_METRIC_NAME, CACHE_SERVICE_PUT_METRIC_COMPONENT_NAME).build();
        this.metricsService.recordCount(build, 1.0d);
        this.metricsService.recordCount(build2, 1.0d);
    }

    @Override // rx.Observer
    public void onCompleted() {
        Log.i(TAG, "Cache metrics observer completed");
    }

    @Override // rx.Observer
    public void onError(Throwable th) {
        Log.e(TAG, "Cache metrics observer errored out.", th);
    }

    @Override // rx.Observer
    public void onNext(@NonNull CacheEvent cacheEvent) {
        switch (cacheEvent.type) {
            case 0:
            case 1:
                processHitOrMissEvent(cacheEvent);
                return;
            case 2:
            case 5:
            case 6:
            case 7:
                return;
            case 3:
                processGetError(cacheEvent);
                return;
            case 4:
                processPutError(cacheEvent);
                return;
            default:
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unknown cache event: ");
                outline107.append(cacheEvent.type);
                outline107.toString();
                return;
        }
    }
}
