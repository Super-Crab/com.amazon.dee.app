package com.amazon.alexa.voice.ui.onedesign.ftue.handsfree;

import androidx.annotation.NonNull;
import com.amazon.alexa.voice.metrics.MetricsBridge;
import com.amazon.alexa.voice.ui.onedesign.ftue.R;
import com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.HandsfreePrimerContract;
import com.amazon.alexa.voice.ui.onedesign.ftue.metrics.VoxUiMetricEventName;
import com.amazon.alexa.voice.ui.onedesign.util.Resources;
/* loaded from: classes11.dex */
public final class ReturningPrimerPresenter extends HandsfreePrimerPresenter {
    private final MetricsBridge metricsBridge;
    private final HandsfreePrimerContract.View view;

    public ReturningPrimerPresenter(@NonNull HandsfreePrimerContract.View view, @NonNull HandsfreePrimerContract.Interactor interactor, @NonNull Resources resources, @NonNull MetricsBridge metricsBridge) {
        super(interactor, resources, metricsBridge);
        this.view = view;
        this.metricsBridge = metricsBridge;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.HandsfreePrimerContract.Presenter
    public void start() {
        Resources resources = getResources();
        this.view.floodBackgroundToStatusBar();
        this.view.setTitle(resources.getString(R.string.voice_ui_od_handsfree_ftue_returning_user_title));
        String string = resources.getString(R.string.voice_ui_od_handsfree_ftue_learn_more);
        this.view.setUsage(resources.getString(R.string.voice_ui_od_handsfree_ftue_returning_user_usage, string), string);
        this.view.setHandsfreeSettingsInfo(resources.getString(R.string.voice_ui_od_handsfree_ftue_returning_user_handsfree_settings_info));
        this.view.setAllowButtonText(resources.getString(R.string.voice_ui_od_handsfree_ftue_returning_user_primer_button_allow));
        this.view.setLaterButtonText(resources.getString(R.string.voice_ui_od_handsfree_ftue_returning_user_primer_button_skip));
        this.metricsBridge.reportEvent(VoxUiMetricEventName.RETURNING_USER_PRIMER_SHOWN);
    }
}
