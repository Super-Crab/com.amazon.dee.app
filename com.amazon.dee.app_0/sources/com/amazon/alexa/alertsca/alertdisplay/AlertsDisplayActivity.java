package com.amazon.alexa.alertsca.alertdisplay;

import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.ObjectsCompat;
import com.amazon.alexa.alertsca.AlertsAuthority;
import com.amazon.alexa.alertsca.AlertsIntentFactory;
import com.amazon.alexa.alertsca.R;
import com.amazon.alexa.alertsca.dependencies.MetricsModule;
import com.amazon.alexa.alertsca.metrics.MetricsConstants;
import com.amazon.alexa.alertsca.metrics.service.MetricsService;
import com.amazon.alexa.alertsca.utils.datetime.AlertsDateTimeFormatter;
import com.amazon.alexa.api.compat.alerts.AlertType;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Component;
import java.util.Date;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes6.dex */
public class AlertsDisplayActivity extends AppCompatActivity implements View.OnClickListener, AlertDisplayListener {
    private static final String EMPTY_STRING = "";
    private static final String TAG = AlertsDisplayActivity.class.getSimpleName();
    private AlertDisplayData alertDisplayData;
    private TextView alertLabel;
    private AlertsDateTimeFormatter alertsDateTimeFormatter;
    private Chronometer chronometer;
    private TextView dateLabel;
    private Button dismissButton;
    private ImageView iconHolder;
    @Inject
    MetricsService metricsService;
    private AlertDisplayReceiver receiver;
    private TextView reminderLabel;
    private TextView timeLabel;
    private PowerManager.WakeLock wakeLock;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.alexa.alertsca.alertdisplay.AlertsDisplayActivity$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$api$compat$alerts$AlertType = new int[AlertType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$api$compat$alerts$AlertType[AlertType.ALARM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$compat$alerts$AlertType[AlertType.TIMER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static class AlertDisplayData {
        private int alertId;
        private String alertText;
        private long alertTimeInMillis;
        private String alertToken;
        private AlertType alertType;

        private AlertDisplayData() {
        }

        public long getCountdownBase() {
            long currentTimeMillis = System.currentTimeMillis();
            return (this.alertTimeInMillis - currentTimeMillis) + SystemClock.elapsedRealtime();
        }

        public boolean isValid() {
            return this.alertToken != null && AlertsAuthority.ALERT_TYPES_SHOWING_FULL_SCREEN.contains(this.alertType);
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AlertsDisplayData{alertText='");
            GeneratedOutlineSupport1.outline176(outline107, this.alertText, Chars.QUOTE, ", alertToken='");
            GeneratedOutlineSupport1.outline176(outline107, this.alertToken, Chars.QUOTE, ", alertType=");
            outline107.append(this.alertType);
            outline107.append(", alertTimeInMillis=");
            outline107.append(this.alertTimeInMillis);
            outline107.append(", alertId=");
            return GeneratedOutlineSupport1.outline85(outline107, this.alertId, JsonReaderKt.END_OBJ);
        }

        /* synthetic */ AlertDisplayData(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Component(modules = {MetricsModule.class})
    @Singleton
    /* loaded from: classes6.dex */
    public interface Injector {
        void inject(AlertsDisplayActivity alertsDisplayActivity);
    }

    private AlertDisplayData extractData(Intent intent) {
        AlertType alertType = null;
        if (intent == null || intent.getExtras() == null) {
            return null;
        }
        AlertDisplayData alertDisplayData = new AlertDisplayData(null);
        alertDisplayData.alertText = intent.getStringExtra(AlertsIntentFactory.ExtraKeys.ALERT_LABEL);
        alertDisplayData.alertToken = intent.getStringExtra(AlertsIntentFactory.ExtraKeys.ALERT_TOKEN);
        String stringExtra = intent.getStringExtra(AlertsIntentFactory.ExtraKeys.ALERT_RECORD_TYPE);
        if (stringExtra != null) {
            alertType = AlertType.valueOf(stringExtra);
        }
        alertDisplayData.alertType = alertType;
        alertDisplayData.alertTimeInMillis = intent.getLongExtra(AlertsIntentFactory.ExtraKeys.ALERT_TIME, System.currentTimeMillis());
        alertDisplayData.alertId = intent.getIntExtra(AlertsIntentFactory.ExtraKeys.ALERT_RECORD_ID, 0);
        return alertDisplayData;
    }

    private void hideSystemUI() {
        getWindow().getDecorView().setSystemUiVisibility(5894);
    }

    private void initView() {
        this.alertLabel = (TextView) findViewById(R.id.txt_label);
        this.reminderLabel = (TextView) findViewById(R.id.txt_reminder_name);
        this.iconHolder = (ImageView) findViewById(R.id.iv_alarm);
        this.timeLabel = (TextView) findViewById(R.id.txt_time);
        this.chronometer = (Chronometer) findViewById(R.id.chronometer);
        this.dateLabel = (TextView) findViewById(R.id.txt_date);
        this.dismissButton = (Button) findViewById(R.id.btn_dismiss);
        this.dismissButton.setOnClickListener(this);
        getWindow().addFlags(4718720);
    }

    private void registerReceiver() {
        this.receiver.register();
    }

    private void unregisterReceiver() {
        this.receiver.unregister();
    }

    private void updateUI() {
        int ordinal = this.alertDisplayData.alertType.ordinal();
        if (ordinal == 0) {
            Date date = new Date(this.alertDisplayData.alertTimeInMillis);
            this.chronometer.setVisibility(8);
            this.dateLabel.setVisibility(0);
            this.timeLabel.setText(this.alertsDateTimeFormatter.formatTime(date));
            this.dateLabel.setText(this.alertsDateTimeFormatter.formatDate(date));
            this.alertLabel.setText(getResources().getString(R.string.amazon_avs_alarm_label));
            this.iconHolder.setImageResource(R.drawable.amazon_avs_activity_timer_icon);
        } else if (ordinal == 1) {
            this.dateLabel.setText("");
            this.timeLabel.setText("");
            this.dateLabel.setVisibility(4);
            this.chronometer.setVisibility(0);
            this.iconHolder.setImageResource(R.drawable.amazon_avs_activity_alarm_icon);
            this.chronometer.setBase(this.alertDisplayData.getCountdownBase());
            this.chronometer.start();
            this.alertLabel.setText(getResources().getString(R.string.amazon_avs_timer_label));
        }
        if (!TextUtils.isEmpty(this.alertDisplayData.alertText)) {
            this.reminderLabel.setVisibility(0);
            this.reminderLabel.setText(this.alertDisplayData.alertText);
            return;
        }
        this.reminderLabel.setVisibility(8);
        this.reminderLabel.setText("");
    }

    @VisibleForTesting
    void injectDependencies() {
        DaggerAlertsDisplayActivity_Injector.builder().build().inject(this);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        this.metricsService.recordEvent(MetricsConstants.ALERTS.DISPLAY_ACTIVITY.BACK_PRESSED.element(this.alertDisplayData.alertType.name()));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.btn_dismiss) {
            getApplicationContext().startService(AlertsIntentFactory.createStopIntent(getBaseContext(), this.alertDisplayData.alertToken, this.alertDisplayData.alertId));
            this.metricsService.recordEvent(MetricsConstants.ALERTS.DISPLAY_ACTIVITY.CLICKED_DISMISS.element(this.alertDisplayData.alertType.name()));
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ThemeUtil.setTheme(this);
        setContentView(R.layout.amazon_avs_alarm_display);
        injectDependencies();
        initView();
        this.alertDisplayData = extractData(getIntent());
        AlertDisplayData alertDisplayData = this.alertDisplayData;
        if (alertDisplayData != null && alertDisplayData.isValid()) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onCreate: alertDisplayData = ");
            outline107.append(this.alertDisplayData);
            outline107.toString();
            this.alertsDateTimeFormatter = new AlertsDateTimeFormatter(this);
            updateUI();
            this.wakeLock = ((PowerManager) getSystemService("power")).newWakeLock(268435466, TAG);
            this.wakeLock.acquire();
            this.receiver = new AlertDisplayReceiver(getBaseContext(), this);
            registerReceiver();
            this.metricsService.recordEvent(MetricsConstants.ALERTS.DISPLAY_ACTIVITY.SHOW.element(this.alertDisplayData.alertType.name()).success());
            return;
        }
        Log.w(TAG, "onCreate: alertDisplayData is null or invalid.");
        this.metricsService.recordEvent(MetricsConstants.ALERTS.DISPLAY_ACTIVITY.SHOW.failure());
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver();
        PowerManager.WakeLock wakeLock = this.wakeLock;
        if (wakeLock != null) {
            wakeLock.release();
            this.wakeLock = null;
        }
    }

    @Override // com.amazon.alexa.alertsca.alertdisplay.AlertDisplayListener
    public void onDismiss(String str) {
        if (ObjectsCompat.equals(str, this.alertDisplayData.alertToken)) {
            this.metricsService.recordEvent(MetricsConstants.ALERTS.DISPLAY_ACTIVITY.ON_DISMISS.element(this.alertDisplayData.alertType.name()));
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        AlertDisplayData extractData = extractData(intent);
        if (extractData != null && extractData.isValid()) {
            this.alertDisplayData = extractData;
            String str = "onNewIntent: alertDisplayData = " + extractData;
            setIntent(intent);
            updateUI();
            this.metricsService.recordEvent(MetricsConstants.ALERTS.DISPLAY_ACTIVITY.NEW_INTENT.element(extractData.alertType.name()).success());
            return;
        }
        Log.w(TAG, "onNewIntent: alertDisplayData is null or invalid.");
        this.metricsService.recordEvent(MetricsConstants.ALERTS.DISPLAY_ACTIVITY.NEW_INTENT.failure());
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            hideSystemUI();
        }
    }
}
