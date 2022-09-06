package com.amazon.alexa.redesign.view.viewtypes;

import android.content.Context;
import android.content.res.Resources;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.redesign.R;
import com.amazon.alexa.redesign.entity.viewtypes.AlarmsTimersModel;
import com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel;
import java.util.Locale;
import java.util.Map;
/* loaded from: classes10.dex */
public class AlarmsTimersView extends IconTitleSubtitleView {
    public static final String STATE_CANCELED = "canceled";
    @VisibleForTesting
    static final String STATE_DONE = "done";
    @VisibleForTesting
    static final String STATE_PAUSED = "paused";
    @VisibleForTesting
    static final String STATE_RUNNING = "running";
    @VisibleForTesting
    String state;
    private String title;
    private long triggerTime;
    @VisibleForTesting
    CountDownTimer updateTextTimer;

    public AlarmsTimersView(Context context) {
        super(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindTitle(String str) {
        this.titleTextView.setText(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateState(String str) {
        this.state = str;
        bindTitle(generateTitleString(this.triggerTime - System.currentTimeMillis(), this.title, this.state));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @NonNull
    @VisibleForTesting
    String addPrefixFromState(String str, String str2, Resources resources) {
        char c;
        switch (str.hashCode()) {
            case -995321554:
                if (str.equals("paused")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -123173735:
                if (str.equals(STATE_CANCELED)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 3089282:
                if (str.equals("done")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1550783935:
                if (str.equals(STATE_RUNNING)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        return c != 0 ? c != 1 ? c != 2 ? str2 : String.format(resources.getString(R.string.amahc_alarms_timers_paused), str2) : String.format(resources.getString(R.string.amahc_alarms_timers_done), str2) : String.format(resources.getString(R.string.amahc_alarms_timers_canceled), str2);
    }

    @Override // com.amazon.alexa.redesign.view.viewtypes.IconTitleSubtitleView, com.amazon.alexa.redesign.view.viewtypes.ViewType
    public void bind(@NonNull ViewTypeModel viewTypeModel, @NonNull Map<String, Object> map, @NonNull String str) {
        super.bind(viewTypeModel, map, str);
        initVariablesFromModel(viewTypeModel);
        long currentTimeMillis = this.triggerTime - System.currentTimeMillis();
        if (currentTimeMillis < 0) {
            currentTimeMillis = 0;
        }
        bindTitle(generateTitleString(currentTimeMillis, this.title, this.state));
        initUpdateTextTimer(currentTimeMillis);
    }

    @VisibleForTesting
    String generateTitleString(long j, String str, String str2) {
        return addPrefixFromState(str2, str.replace("%t", String.format(Locale.getDefault(), "%02d", Integer.valueOf((int) ((j / 3600000) % 24))) + ":" + String.format(Locale.getDefault(), "%02d", Integer.valueOf((int) ((j / 60000) % 60))) + ":" + String.format(Locale.getDefault(), "%02d", Integer.valueOf(((int) (j / 1000)) % 60))), getContext().getResources());
    }

    @VisibleForTesting
    void initUpdateTextTimer(long j) {
        this.updateTextTimer = new CountDownTimer(j, 1000L) { // from class: com.amazon.alexa.redesign.view.viewtypes.AlarmsTimersView.1
            @Override // android.os.CountDownTimer
            public void onFinish() {
                AlarmsTimersView.this.updateState("done");
            }

            @Override // android.os.CountDownTimer
            public void onTick(long j2) {
                AlarmsTimersView alarmsTimersView = AlarmsTimersView.this;
                AlarmsTimersView.this.bindTitle(alarmsTimersView.generateTitleString(j2, alarmsTimersView.title, AlarmsTimersView.this.state));
            }
        };
        this.updateTextTimer.start();
    }

    @VisibleForTesting
    void initVariablesFromModel(ViewTypeModel viewTypeModel) {
        if (viewTypeModel instanceof AlarmsTimersModel) {
            AlarmsTimersModel alarmsTimersModel = (AlarmsTimersModel) viewTypeModel;
            this.state = alarmsTimersModel.getState();
            this.title = alarmsTimersModel.getTitle();
            this.triggerTime = alarmsTimersModel.getTriggerTime();
        }
    }

    @Override // com.amazon.alexa.redesign.view.viewtypes.IconTitleSubtitleView
    protected void setTextOnTitleView(TextView textView, String str) {
    }

    public AlarmsTimersView(Context context, ViewGroup viewGroup, LayoutInflater layoutInflater) {
        super(context, viewGroup, layoutInflater);
    }
}
