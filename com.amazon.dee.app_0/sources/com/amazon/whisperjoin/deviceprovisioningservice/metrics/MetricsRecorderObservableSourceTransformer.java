package com.amazon.whisperjoin.deviceprovisioningservice.metrics;

import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.result.WJResult;
import com.amazon.whisperjoin.metrics.MetricsRecorder;
import com.amazon.whisperjoin.metrics.MetricsRecorderProvider;
import com.amazon.whisperjoin.metrics.WhisperJoinMetricName;
import com.amazon.whisperjoin.metrics.WhisperJoinMetricSourceName;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
/* loaded from: classes13.dex */
public class MetricsRecorderObservableSourceTransformer implements ObservableTransformer<WJResult, WJResult> {
    private final MetricsRecorder mMetricsRecorder;
    private final WhisperJoinMetricSourceName mSourceName;

    public MetricsRecorderObservableSourceTransformer(MetricsRecorderProvider metricsRecorderProvider, WhisperJoinMetricSourceName whisperJoinMetricSourceName) {
        this.mMetricsRecorder = metricsRecorderProvider.getMetricsRecorder(whisperJoinMetricSourceName);
        this.mSourceName = whisperJoinMetricSourceName;
    }

    @Override // io.reactivex.rxjava3.core.ObservableTransformer
    public ObservableSource<WJResult> apply(@NonNull Observable<WJResult> observable) {
        return observable.doOnSubscribe(new Consumer<Disposable>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.metrics.MetricsRecorderObservableSourceTransformer.3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public void accept(@NonNull Disposable disposable) throws Exception {
                MetricsRecorderObservableSourceTransformer.this.mMetricsRecorder.startProfiling(MetricsRecorderObservableSourceTransformer.this.mSourceName.toString());
            }
        }).doOnNext(new Consumer<WJResult>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.metrics.MetricsRecorderObservableSourceTransformer.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public void accept(@NonNull WJResult wJResult) throws Exception {
                MetricsRecorderObservableSourceTransformer.this.mMetricsRecorder.stopProfiling(WhisperJoinMetricName.SUCCESS, MetricsRecorderObservableSourceTransformer.this.mSourceName.toString());
            }
        }).doOnError(new Consumer<Throwable>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.metrics.MetricsRecorderObservableSourceTransformer.1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public void accept(@NonNull Throwable th) throws Exception {
                MetricsRecorderObservableSourceTransformer.this.mMetricsRecorder.stopProfiling(WhisperJoinMetricName.FAILURE, MetricsRecorderObservableSourceTransformer.this.mSourceName.toString());
            }
        });
    }
}
