package com.amazon.alexa.biloba.view.account.timeZonePicker;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.biloba.view.common.ListItemClickListener;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes6.dex */
public class TimeZonePickerAdapter extends RecyclerView.Adapter<TimeZonePickerViewHolder> {
    private static final String TAG = "TimeZonePickerAdapter";
    private final LayoutInflater inflater;
    private final ListItemClickListener onTimeZoneSelectedListner;
    private final TimeZonePickerViewModel timeZonePickerViewModel;

    public TimeZonePickerAdapter(@NonNull LayoutInflater layoutInflater, @NonNull TimeZonePickerViewModel timeZonePickerViewModel, ListItemClickListener listItemClickListener) {
        this.inflater = layoutInflater;
        this.timeZonePickerViewModel = timeZonePickerViewModel;
        this.onTimeZoneSelectedListner = listItemClickListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Item Count  ");
        outline107.append(this.timeZonePickerViewModel.getTimeZonesForCurrentRegion().size());
        LogUtils.d(str, outline107.toString());
        return this.timeZonePickerViewModel.getTimeZonesForCurrentRegion().size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull TimeZonePickerViewHolder timeZonePickerViewHolder, int i) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("binding the timezone data with ");
        outline107.append(this.timeZonePickerViewModel.getTimeZonesForCurrentRegion().get(i));
        LogUtils.d(str, outline107.toString());
        timeZonePickerViewHolder.bindData(this.timeZonePickerViewModel.getTimeZonesForCurrentRegion().get(i), this.timeZonePickerViewModel.getCurrentTimeZone().getValue(), this.onTimeZoneSelectedListner);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    /* renamed from: onCreateViewHolder  reason: avoid collision after fix types in other method */
    public TimeZonePickerViewHolder mo7499onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new TimeZonePickerViewHolder(this.inflater.inflate(R.layout.timezone_list_item, viewGroup, false));
    }
}
