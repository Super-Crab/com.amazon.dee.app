package com.amazon.alexa.biloba.view.account.timeZonePicker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.databinding.TimezoneListBinding;
import com.amazon.alexa.biloba.metrics.MetricsConstants;
import com.amazon.alexa.biloba.model.TimeZoneItem;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.biloba.view.BilobaViewController;
import com.amazon.alexa.biloba.view.common.ListItemClickListener;
import com.amazon.alexa.mosaic.view.ViewUtils;
/* loaded from: classes6.dex */
public class TimeZonePickerView extends BilobaViewController implements ListItemClickListener<TimeZoneItem> {
    public static final int REGION_INVALID = -1;
    private static final String TAG = "TimeZonePickerView";
    private AlertDialog.Builder builder;
    private Context context;
    private AlertDialog dialog;
    private TimeZonePickerAdapter timeZonePickerAdapter;
    private View timeZonePickerView;
    private RecyclerView timeZoneRecyclerView;
    private int trackSelectedRegion = -1;
    Observer<Throwable> errorObserver = new Observer() { // from class: com.amazon.alexa.biloba.view.account.timeZonePicker.-$$Lambda$TimeZonePickerView$z9IMHZ-2kI1oNuONOCGHibakMs8
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            TimeZonePickerView.this.lambda$new$2$TimeZonePickerView((Throwable) obj);
        }
    };
    private final TimeZonePickerViewModel timeZonePickerViewModel = new TimeZonePickerViewModel();

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public String getTitle(@NonNull Context context) {
        return context.getResources().getString(R.string.time_zone_header);
    }

    public /* synthetic */ void lambda$makeView$0$TimeZonePickerView(String str) {
        String str2 = TAG;
        LogUtils.i(str2, "Calling notifyDataSetChanged on region change to " + str);
        this.timeZonePickerAdapter.notifyDataSetChanged();
    }

    public /* synthetic */ void lambda$makeView$1$TimeZonePickerView(String str) {
        String str2 = TAG;
        LogUtils.i(str2, "Calling notifyDataSetChanged on time zone change " + str);
        this.timeZonePickerAdapter.notifyDataSetChanged();
    }

    public /* synthetic */ void lambda$new$2$TimeZonePickerView(Throwable th) {
        if (th != null) {
            new AlertDialog.Builder(this.context).setMessage(this.context.getString(R.string.error_care_timezone_body)).setTitle(this.context.getString(R.string.error_care_timezone_headline)).setPositiveButton(this.context.getString(R.string.ok), (DialogInterface.OnClickListener) null).create().show();
        }
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public View makeView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        this.context = viewGroup.getContext();
        TimezoneListBinding timezoneListBinding = (TimezoneListBinding) DataBindingUtil.inflate(LayoutInflater.from(ViewUtils.getThemeWrapper(this.context)), R.layout.timezone_list, viewGroup, false);
        timezoneListBinding.setViewmodel(this.timeZonePickerViewModel);
        timezoneListBinding.setTimezoneView(this);
        timezoneListBinding.setLifecycleOwner((LifecycleOwner) this.context);
        this.timeZonePickerView = timezoneListBinding.getRoot();
        this.timeZoneRecyclerView = (RecyclerView) this.timeZonePickerView.findViewById(R.id.timezone_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.context);
        LogUtils.d(TAG, "setting layout manage to  timeZoneRecyclerView");
        this.timeZoneRecyclerView.setLayoutManager(linearLayoutManager);
        this.timeZonePickerAdapter = new TimeZonePickerAdapter(layoutInflater, this.timeZonePickerViewModel, this);
        this.timeZoneRecyclerView.setAdapter(this.timeZonePickerAdapter);
        this.timeZonePickerViewModel.getCurrentRegion().observe((LifecycleOwner) this.context, new Observer() { // from class: com.amazon.alexa.biloba.view.account.timeZonePicker.-$$Lambda$TimeZonePickerView$iXNJTnXvsvA3t8aMnL8GPj6gmVw
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                TimeZonePickerView.this.lambda$makeView$0$TimeZonePickerView((String) obj);
            }
        });
        this.timeZonePickerViewModel.getCurrentTimeZone().observe((LifecycleOwner) this.context, new Observer() { // from class: com.amazon.alexa.biloba.view.account.timeZonePicker.-$$Lambda$TimeZonePickerView$Lhafz8RWVVzCYge8GuqCjg9hfnw
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                TimeZonePickerView.this.lambda$makeView$1$TimeZonePickerView((String) obj);
            }
        });
        registerViewAttributes((LinearLayout) this.timeZonePickerView.findViewById(R.id.no_connection_banner), this.timeZonePickerViewModel);
        return this.timeZonePickerView;
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onAttach(@NonNull View view) {
        subscribeToNetworkChange();
        this.timeZonePickerViewModel.getTimeZonePageError().observe((LifecycleOwner) this.context, this.errorObserver);
        this.timeZonePickerViewModel.sendRequest();
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onDetach(@NonNull View view) {
        unSubscribeToNetworkChange();
        this.timeZonePickerViewModel.getTimeZonePageError().removeObserver(this.errorObserver);
    }

    public void showTimeZoneRegions(View view) {
        if (this.builder == null) {
            this.builder = new AlertDialog.Builder(this.context);
            this.builder.setTitle(R.string.time_zone_region_picker_header);
            this.builder.setSingleChoiceItems(this.timeZonePickerViewModel.getTimezoneRegionsAsCharSequence(), this.timeZonePickerViewModel.getCurrentRegionIndex(), new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.biloba.view.account.timeZonePicker.TimeZonePickerView.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    TimeZonePickerView.this.trackSelectedRegion = i;
                }
            });
            this.builder.setPositiveButton(R.string.time_zone_region_picker_positive_button, new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.biloba.view.account.timeZonePicker.TimeZonePickerView.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (TimeZonePickerView.this.trackSelectedRegion != -1) {
                        TimeZonePickerView.this.timeZonePickerViewModel.updateCurrentTimeZoneRegion(TimeZonePickerView.this.trackSelectedRegion);
                    } else {
                        LogUtils.e(TimeZonePickerView.TAG, "Trying to set the time zone region with the value -1");
                    }
                }
            });
            this.builder.setNegativeButton(R.string.time_zone_region_picker_negative_button, (DialogInterface.OnClickListener) null);
            this.dialog = this.builder.create();
        }
        this.dialog.show();
    }

    @Override // com.amazon.alexa.biloba.view.common.ListItemClickListener
    public void onListItemClicked(TimeZoneItem timeZoneItem) {
        this.timeZonePickerViewModel.setTimeZoneSettings(timeZoneItem);
        recordClickMetric(MetricsConstants.UserInteractionMetrics.CHANGE_TIME_ZONE, "");
    }
}
