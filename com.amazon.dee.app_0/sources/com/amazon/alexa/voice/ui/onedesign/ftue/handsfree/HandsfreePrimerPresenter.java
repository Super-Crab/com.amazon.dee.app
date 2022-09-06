package com.amazon.alexa.voice.ui.onedesign.ftue.handsfree;

import androidx.annotation.NonNull;
import com.amazon.alexa.voice.metrics.MetricsBridge;
import com.amazon.alexa.voice.ui.onedesign.ftue.R;
import com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.HandsfreePrimerContract;
import com.amazon.alexa.voice.ui.onedesign.ftue.metrics.VoxUiMetricEventName;
import com.amazon.alexa.voice.ui.onedesign.util.Resources;
/* loaded from: classes11.dex */
public abstract class HandsfreePrimerPresenter implements HandsfreePrimerContract.Presenter {
    private final HandsfreePrimerContract.Interactor interactor;
    private final MetricsBridge metricsBridge;
    private final Resources resources;

    public HandsfreePrimerPresenter(@NonNull HandsfreePrimerContract.Interactor interactor, @NonNull Resources resources, @NonNull MetricsBridge metricsBridge) {
        this.interactor = interactor;
        this.resources = resources;
        this.metricsBridge = metricsBridge;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.HandsfreePrimerContract.Presenter
    public void allowClicked() {
        this.metricsBridge.reportEvent(VoxUiMetricEventName.PRIMER_ALLOWED);
        this.interactor.requestPermissions();
    }

    public Resources getResources() {
        return this.resources;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.HandsfreePrimerContract.Presenter
    public void laterClicked() {
        this.metricsBridge.reportEvent(VoxUiMetricEventName.PRIMER_DENIED);
        this.interactor.deferPrimer();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.HandsfreePrimerContract.Presenter
    public void learnMoreClicked() {
        this.interactor.learnMoreClicked(this.resources.getString(R.string.voice_ui_od_handsfree_ftue_learn_more_url));
        this.metricsBridge.reportEvent(VoxUiMetricEventName.LEARNMORE_VISITED);
    }
}
