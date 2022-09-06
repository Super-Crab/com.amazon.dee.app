package com.amazon.alexa.biloba.view.alerts.edit;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.databinding.AlertFormBindingImpl;
import com.amazon.alexa.biloba.dependency.BilobaDependencies;
import com.amazon.alexa.biloba.metrics.MetricsConstants;
import com.amazon.alexa.biloba.model.AlertConfigurationViewItemModel;
import com.amazon.alexa.biloba.routing.Routes;
import com.amazon.alexa.biloba.service.AlertConfigurationException;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.biloba.view.BilobaViewController;
import com.amazon.alexa.biloba.view.BilobaViewModelFactory;
import com.amazon.alexa.biloba.view.common.recycler.BaseRecyclerItem;
import com.amazon.alexa.mosaic.view.ViewUtils;
import com.amazon.alexa.routing.api.RoutingService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
import java.lang.ref.WeakReference;
import java.text.DateFormat;
import java.util.List;
import javax.inject.Inject;
/* loaded from: classes6.dex */
public class AlertSettingsView extends BilobaViewController {
    private static final String TAG = "AlertSettingsView";
    private WeakReference<Context> context;
    private TimePicker endTimePicker;
    private String midnightTimeWrapper;
    private String noonTimeWrapper;
    private final RoutingService routingService;
    private View settingsView;
    private TimePicker startTimePicker;
    private DateFormat timeDisplayFormat;
    private AlertSettingsViewModel viewModel;
    @Inject
    Lazy<BilobaViewModelFactory> viewModelFactory;
    private Observer<Throwable> errorObserver = new Observer() { // from class: com.amazon.alexa.biloba.view.alerts.edit.-$$Lambda$AlertSettingsView$eFWvmA3VbCeTArEa5lfJwkgKMG8
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            AlertSettingsView.this.lambda$new$0$AlertSettingsView((Throwable) obj);
        }
    };
    private Observer<AlertConfigurationViewItemModel> alertConfigObserver = new Observer() { // from class: com.amazon.alexa.biloba.view.alerts.edit.-$$Lambda$AlertSettingsView$KY6PE_Enwkf-lshJyIVrCVvh08Y
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            AlertSettingsView.this.lambda$new$1$AlertSettingsView((AlertConfigurationViewItemModel) obj);
        }
    };
    private Observer<List<BaseRecyclerItem>> alertItemsObserver = new Observer() { // from class: com.amazon.alexa.biloba.view.alerts.edit.-$$Lambda$AlertSettingsView$wBLawDRA80rh62KvOqafT2ce9dI
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            AlertSettingsView.this.lambda$new$2$AlertSettingsView((List) obj);
        }
    };
    private Observer<Boolean> loadingObserver = $$Lambda$AlertSettingsView$Vu9CeK3gkhXdxLP3yJ8RVWWN0.INSTANCE;

    public AlertSettingsView(Context context, Bundle bundle) {
        BilobaDependencies.inject(this);
        this.context = new WeakReference<>(context);
        this.timeDisplayFormat = android.text.format.DateFormat.getTimeFormat(context);
        this.midnightTimeWrapper = context.getString(R.string.midnight_time_wrapper);
        this.noonTimeWrapper = context.getString(R.string.noon_time_wrapper);
        this.routingService = (RoutingService) GeneratedOutlineSupport1.outline20(RoutingService.class);
        this.viewModel = (AlertSettingsViewModel) this.viewModelFactory.mo358get().getViewModelFor(Routes.BILOBA_ALERT_FORM);
        this.viewModel.create(bundle);
    }

    private void displayErrorDialog(Context context, String str) {
        new AlertDialog.Builder(context).setMessage(context.getString(R.string.error_try_again)).setTitle(str).setPositiveButton(context.getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.biloba.view.alerts.edit.-$$Lambda$AlertSettingsView$15HSfn-9-x-X4YXLh6U0O4cM89I
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                AlertSettingsView.this.lambda$displayErrorDialog$6$AlertSettingsView(dialogInterface, i);
            }
        }).create().show();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$new$3(Boolean bool) {
        String str = TAG;
        LogUtils.d(str, "still loading=" + bool);
    }

    private void updateTimePickerValue(int i, int i2, TimePicker timePicker) {
        int i3 = Build.VERSION.SDK_INT;
        timePicker.setHour(i);
        timePicker.setMinute(i2);
    }

    public String getAlertConditionString(AlertConfigurationViewItemModel alertConfigurationViewItemModel) {
        if (alertConfigurationViewItemModel != null && alertConfigurationViewItemModel.getAlertCondition() != null) {
            Context context = this.context.get();
            if (context == null) {
                LogUtils.d(TAG, "cannot get alert condition string because context is no longer available.");
                return null;
            }
            String condition = alertConfigurationViewItemModel.getAlertCondition().getCondition();
            if ("FIRST_ACTIVITY_OF_THE_DAY".equals(condition)) {
                return context.getString(R.string.alert_condition_first_activity_of_the_day);
            }
            if ("NO_ACTIVITY_DETECTED".equals(condition)) {
                return context.getString(R.string.alert_condition_no_activity_detected);
            }
            String str = TAG;
            LogUtils.w(str, "Unknown condition type:" + condition);
            return null;
        }
        LogUtils.w(TAG, "Null condition");
        return null;
    }

    public String getDeviceString() {
        Context context = this.context.get();
        if (context == null) {
            LogUtils.d(TAG, "ignoring data update because context is no longer available.");
            return null;
        }
        return context.getString(R.string.alert_condition_all_devices);
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public String getTitle(@NonNull Context context) {
        return context.getResources().getString(R.string.alert_settings_heading);
    }

    public /* synthetic */ void lambda$displayErrorDialog$6$AlertSettingsView(DialogInterface dialogInterface, int i) {
        this.routingService.route(Routes.BILOBA_ALERT_FORM).with(AlertSettingsViewModel.ALERT_CONFIG_ID, this.viewModel.alertConfigId).navigate();
    }

    public /* synthetic */ void lambda$makeView$4$AlertSettingsView(TimePicker timePicker, int i, int i2) {
        this.viewModel.updateStartTime(i, i2);
    }

    public /* synthetic */ void lambda$makeView$5$AlertSettingsView(TimePicker timePicker, int i, int i2) {
        this.viewModel.updateEndTime(i, i2);
    }

    public /* synthetic */ void lambda$new$0$AlertSettingsView(Throwable th) {
        if (th == null) {
            return;
        }
        Context context = this.context.get();
        if (context == null) {
            LogUtils.d(TAG, "ignoring data update because context is no longer available.");
        } else if (th.getClass().equals(AlertConfigurationException.class)) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Got this error:\n");
            outline107.append(th.getLocalizedMessage());
            LogUtils.e(str, outline107.toString());
            if (((AlertConfigurationException) th).getErrorCode() != -3) {
                return;
            }
            displayErrorDialog(context, context.getString(R.string.error_alert_change_failure_body));
        } else {
            String str2 = TAG;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("unknown exception was forwarded to the view: ");
            outline1072.append(th.getMessage());
            LogUtils.e(str2, outline1072.toString());
            displayErrorDialog(context, context.getString(R.string.error_alert_change_failure_body));
        }
    }

    public /* synthetic */ void lambda$new$1$AlertSettingsView(AlertConfigurationViewItemModel alertConfigurationViewItemModel) {
        LogUtils.d(TAG, "view was notified about an updated alert configuration");
        if (alertConfigurationViewItemModel == null) {
            LogUtils.w(TAG, "alert configuration was null");
        } else if (this.context.get() == null) {
            LogUtils.d(TAG, "ignoring data update because context is no longer available.");
        } else {
            String displayStartTime = alertConfigurationViewItemModel.getDisplayStartTime(this.timeDisplayFormat, this.noonTimeWrapper, this.midnightTimeWrapper);
            if (displayStartTime != null) {
                LogUtils.v(TAG, String.format("setting start time to \"%s\"", displayStartTime));
                this.viewModel.updateDisplayStartTime(displayStartTime);
                if (!this.viewModel.isStartTimePickerUpdated()) {
                    updateTimePickerValue(alertConfigurationViewItemModel.getStartTimeHours(), alertConfigurationViewItemModel.getStartTimeMinutes(), this.startTimePicker);
                    this.viewModel.setStartTimePickerUpdated(true);
                }
            } else {
                LogUtils.v(TAG, "setting start time to \"not set\"");
                this.viewModel.updateDisplayStartTime(null);
            }
            String displayEndTime = alertConfigurationViewItemModel.getDisplayEndTime(this.timeDisplayFormat, this.noonTimeWrapper, this.midnightTimeWrapper);
            if (displayEndTime != null) {
                LogUtils.v(TAG, String.format("setting end time to \"%s\"", displayEndTime));
                this.viewModel.updateDisplayEndTime(displayEndTime);
                if (this.viewModel.isEndTimePickerUpdated()) {
                    return;
                }
                updateTimePickerValue(alertConfigurationViewItemModel.getEndTimeHours(), alertConfigurationViewItemModel.getEndTimeMinutes(), this.endTimePicker);
                this.viewModel.setEndTimePickerUpdated(true);
                return;
            }
            LogUtils.v(TAG, "setting end time to \"not set\"");
            this.viewModel.updateDisplayEndTime(null);
        }
    }

    public /* synthetic */ void lambda$new$2$AlertSettingsView(List list) {
        this.viewModel.notifyAlertListUpdated(list);
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public View makeView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        AlertFormBindingImpl alertFormBindingImpl = (AlertFormBindingImpl) DataBindingUtil.inflate(LayoutInflater.from(ViewUtils.getThemeWrapper(this.context.get())), R.layout.alert_form, viewGroup, false);
        alertFormBindingImpl.setLifecycleOwner((LifecycleOwner) viewGroup.getContext());
        alertFormBindingImpl.setViewmodel(this.viewModel);
        alertFormBindingImpl.setHandler(this);
        this.settingsView = alertFormBindingImpl.getRoot();
        this.startTimePicker = (TimePicker) this.settingsView.findViewById(R.id.start_time_input_picker);
        this.startTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() { // from class: com.amazon.alexa.biloba.view.alerts.edit.-$$Lambda$AlertSettingsView$kjVOL-fJIaRVQJAEeeJKovRB4uo
            @Override // android.widget.TimePicker.OnTimeChangedListener
            public final void onTimeChanged(TimePicker timePicker, int i, int i2) {
                AlertSettingsView.this.lambda$makeView$4$AlertSettingsView(timePicker, i, i2);
            }
        });
        this.endTimePicker = (TimePicker) this.settingsView.findViewById(R.id.end_time_input_picker);
        this.endTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() { // from class: com.amazon.alexa.biloba.view.alerts.edit.-$$Lambda$AlertSettingsView$Tpo3S-8V4jHKBKzUsFWlE62qOq4
            @Override // android.widget.TimePicker.OnTimeChangedListener
            public final void onTimeChanged(TimePicker timePicker, int i, int i2) {
                AlertSettingsView.this.lambda$makeView$5$AlertSettingsView(timePicker, i, i2);
            }
        });
        registerViewAttributes((LinearLayout) this.settingsView.findViewById(R.id.no_connection_banner), this.viewModel);
        return this.settingsView;
    }

    public void onAlertConditionClicked(View view) {
        LogUtils.v(TAG, "The alert condition button was clicked. This feature is not yet implemented.");
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onAttach(@NonNull View view) {
        recordViewMetric(MetricsConstants.UserInteractionMetrics.ALERT_EDIT_VIEW, "");
        subscribeToNetworkChange();
        this.viewModel.getError().observe((LifecycleOwner) this.context.get(), this.errorObserver);
        this.viewModel.getAlertConfig().observe((LifecycleOwner) this.context.get(), this.alertConfigObserver);
        this.viewModel.getAlertItems().observe((LifecycleOwner) this.context.get(), this.alertItemsObserver);
        this.viewModel.getIsLoading().observe((LifecycleOwner) this.context.get(), this.loadingObserver);
    }

    public void onDeleteButtonClicked(View view) {
        LogUtils.d(TAG, "delete button was clicked");
        this.viewModel.deleteAlertConfiguration();
        this.routingService.navigateBackward();
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onDetach(@NonNull View view) {
        unSubscribeToNetworkChange();
        this.context.clear();
        this.viewModel.getError().removeObserver(this.errorObserver);
        this.viewModel.getAlertConfig().removeObserver(this.alertConfigObserver);
        this.viewModel.getAlertItems().removeObserver(this.alertItemsObserver);
        this.viewModel.getIsLoading().removeObserver(this.loadingObserver);
    }

    public void onDeviceTypeClicked(View view) {
        LogUtils.v(TAG, "The device type button was clicked. This feature is not yet implemented.");
    }

    public void onEndTimeViewClicked(View view) {
        LogUtils.v(TAG, "toggling end time picker visibility");
        TimePicker timePicker = this.endTimePicker;
        int i = 8;
        if (timePicker.getVisibility() == 8) {
            i = 0;
        }
        timePicker.setVisibility(i);
    }

    public void onSaveButtonClicked(View view) {
        LogUtils.d(TAG, "save button was clicked");
        this.viewModel.saveAlertConfiguration();
        this.routingService.navigateBackward();
        if (this.viewModel.isStartTimePickerUpdated() || this.viewModel.isEndTimePickerUpdated()) {
            recordClickMetric(MetricsConstants.UserInteractionMetrics.CHANGE_ALERT_TIME_RANGE, "");
        }
    }

    public void onStartTimeViewClicked(View view) {
        LogUtils.v(TAG, "toggling start time picker visibility");
        TimePicker timePicker = this.startTimePicker;
        int i = 8;
        if (timePicker.getVisibility() == 8) {
            i = 0;
        }
        timePicker.setVisibility(i);
    }
}
