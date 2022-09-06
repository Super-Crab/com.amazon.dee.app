package com.amazon.whisperjoin.deviceprovisioningservice.metrics;

import com.amazon.whisperjoin.metrics.MetricsRecorder;
import com.amazon.whisperjoin.metrics.MetricsRecorderProvider;
import com.amazon.whisperjoin.metrics.WhisperJoinMetricName;
import com.amazon.whisperjoin.metrics.WhisperJoinMetricSourceName;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.CompletableTransformer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
/* loaded from: classes13.dex */
public class MetricsRecorderCompletableTransformer implements CompletableTransformer {
    private final MetricsRecorder mMetricsRecorder;
    private final WhisperJoinMetricSourceName mSourceName;

    public MetricsRecorderCompletableTransformer(MetricsRecorderProvider metricsRecorderProvider, WhisperJoinMetricSourceName whisperJoinMetricSourceName) {
        this.mMetricsRecorder = metricsRecorderProvider.getMetricsRecorder(whisperJoinMetricSourceName);
        this.mSourceName = whisperJoinMetricSourceName;
    }

    @Override // io.reactivex.rxjava3.core.CompletableTransformer
    public CompletableSource apply(@NonNull Completable completable) {
        return completable.doOnSubscribe(new Consumer<Disposable>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.metrics.MetricsRecorderCompletableTransformer.3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public void accept(@NonNull Disposable disposable) throws Exception {
                MetricsRecorderCompletableTransformer.this.mMetricsRecorder.startProfiling(MetricsRecorderCompletableTransformer.this.mSourceName.toString());
            }
        }).doOnComplete(new Action() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.metrics.MetricsRecorderCompletableTransformer.2
            @Override // io.reactivex.rxjava3.functions.Action
            public void run() throws Exception {
                MetricsRecorderCompletableTransformer.this.mMetricsRecorder.stopProfiling(WhisperJoinMetricName.SUCCESS, MetricsRecorderCompletableTransformer.this.mSourceName.toString());
            }
        }).doOnError(new Consumer<Throwable>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.metrics.MetricsRecorderCompletableTransformer.1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public void accept(@NonNull Throwable th) throws Exception {
                MetricsRecorderCompletableTransformer.this.mMetricsRecorder.stopProfiling(WhisperJoinMetricName.FAILURE, MetricsRecorderCompletableTransformer.this.mSourceName.toString());
            }
        });
    }
}
