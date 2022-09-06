package com.amazon.whisperjoin.deviceprovisioningservice.metrics;

import com.amazon.whisperjoin.metrics.MetricsRecorder;
import com.amazon.whisperjoin.metrics.MetricsRecorderProvider;
import com.amazon.whisperjoin.metrics.WhisperJoinMetricName;
import com.amazon.whisperjoin.metrics.WhisperJoinMetricSourceName;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.core.SingleTransformer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
/* loaded from: classes13.dex */
public class MetricsRecorderSingleTransformer<Upstream, Downstream> implements SingleTransformer<Upstream, Downstream> {
    private final MetricsRecorder mMetricsRecorder;
    private final WhisperJoinMetricSourceName mSourceName;

    public MetricsRecorderSingleTransformer(MetricsRecorderProvider metricsRecorderProvider, WhisperJoinMetricSourceName whisperJoinMetricSourceName) {
        this.mMetricsRecorder = metricsRecorderProvider.getMetricsRecorder(whisperJoinMetricSourceName);
        this.mSourceName = whisperJoinMetricSourceName;
    }

    @Override // io.reactivex.rxjava3.core.SingleTransformer
    public SingleSource<Downstream> apply(@NonNull Single<Upstream> single) {
        return single.doOnSubscribe(new Consumer<Disposable>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.metrics.MetricsRecorderSingleTransformer.3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public void accept(@NonNull Disposable disposable) throws Exception {
                MetricsRecorderSingleTransformer.this.mMetricsRecorder.startProfiling(MetricsRecorderSingleTransformer.this.mSourceName.toString());
            }
        }).doOnSuccess(new Consumer() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.metrics.MetricsRecorderSingleTransformer.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public void accept(@NonNull Object obj) throws Exception {
                MetricsRecorderSingleTransformer.this.mMetricsRecorder.stopProfiling(WhisperJoinMetricName.SUCCESS, MetricsRecorderSingleTransformer.this.mSourceName.toString());
            }
        }).doOnError(new Consumer<Throwable>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.metrics.MetricsRecorderSingleTransformer.1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public void accept(@NonNull Throwable th) throws Exception {
                MetricsRecorderSingleTransformer.this.mMetricsRecorder.stopProfiling(WhisperJoinMetricName.FAILURE, MetricsRecorderSingleTransformer.this.mSourceName.toString());
            }
        });
    }
}
