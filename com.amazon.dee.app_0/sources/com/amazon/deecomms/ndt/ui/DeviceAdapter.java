package com.amazon.deecomms.ndt.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.deecomms.R;
import com.amazon.deecomms.calling.enums.DeviceCommsAvailability;
import com.amazon.deecomms.calling.enums.DropInAvailability;
import com.amazon.deecomms.common.ui.CommsTextView;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.ndt.model.DeviceModel;
import com.amazon.deecomms.ndt.model.DeviceStatusModel;
import com.amazon.deecomms.ndt.model.DropInDisableReason;
import com.amazon.deecomms.ndt.state.DeviceState;
import com.amazon.deecomms.ndt.ui.DeviceAdapter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes12.dex */
public class DeviceAdapter extends RecyclerView.Adapter implements Filterable {
    private static final int EMPTY = 1;
    private static final int NORMAL = 0;
    private DeviceFilter deviceFilter;
    private final DeviceSelectionListener deviceSelectionListener;
    private List<DropInDisableReason> disabledDropInStates;
    private List<DeviceModel> filteredData;
    private boolean mIsThemedUIEnabled;
    private List<DeviceModel> originalData;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class DeviceFilter extends Filter {
        private DeviceFilter() {
        }

        private boolean shouldFilterDevice(DeviceModel deviceModel) {
            DeviceStatusModel deviceStatus = deviceModel.getDeviceStatus();
            String dropInUnsupportedReason = deviceModel.getDropInUnsupportedReason();
            return (dropInUnsupportedReason == null || !DeviceAdapter.this.filterDeviceBasedOnDropinDisableReason(dropInUnsupportedReason)) && deviceStatus.getDeviceDropInAvailability() != DropInAvailability.OFF && deviceStatus.getDeviceCommsAvailability() == DeviceCommsAvailability.ON;
        }

        @Override // android.widget.Filter
        protected Filter.FilterResults performFiltering(CharSequence charSequence) {
            Filter.FilterResults filterResults = new Filter.FilterResults();
            for (DeviceModel deviceModel : DeviceAdapter.this.originalData) {
                if (shouldFilterDevice(deviceModel)) {
                    DeviceAdapter.this.filteredData.add(deviceModel);
                }
            }
            filterResults.values = DeviceAdapter.this.filteredData;
            filterResults.count = DeviceAdapter.this.filteredData.size();
            return filterResults;
        }

        @Override // android.widget.Filter
        protected void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
            Collections.sort(DeviceAdapter.this.filteredData, $$Lambda$DeviceAdapter$DeviceFilter$JT1p3j4axSocdmjXf2FVmOUt14c.INSTANCE);
            DeviceAdapter.this.notifyDataSetChanged();
        }

        /* synthetic */ DeviceFilter(AnonymousClass1 anonymousClass1) {
        }
    }

    /* loaded from: classes12.dex */
    public class DeviceViewHolder extends RecyclerView.ViewHolder {
        public CommsTextView deviceName;
        private DeviceSelectionListener deviceSelectionListener;
        public CommsTextView deviceStatus;
        private View mainView;

        public DeviceViewHolder(View view, DeviceSelectionListener deviceSelectionListener) {
            super(view);
            this.mainView = view;
            this.deviceName = (CommsTextView) this.mainView.findViewById(R.id.drop_in_targeting_device_name);
            this.deviceStatus = (CommsTextView) this.mainView.findViewById(R.id.drop_in_targeting_device_status);
            this.deviceSelectionListener = deviceSelectionListener;
        }
    }

    public DeviceAdapter(DeviceSelectionListener deviceSelectionListener) {
        this(deviceSelectionListener, new ArrayList(), getDefaultDisabledDropInStates(), false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean filterDeviceBasedOnDropinDisableReason(String str) {
        DropInDisableReason lookup = DropInDisableReason.lookup(str);
        return lookup == DropInDisableReason.Unknown || this.disabledDropInStates.indexOf(lookup) != -1;
    }

    private static List<DropInDisableReason> getDefaultDisabledDropInStates() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(DropInDisableReason.DropInUnavailable);
        arrayList.add(DropInDisableReason.DropInSettingOff);
        arrayList.add(DropInDisableReason.CommsSettingOff);
        return arrayList;
    }

    @Override // android.widget.Filterable
    public Filter getFilter() {
        return this.deviceFilter;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<DeviceModel> list = this.filteredData;
        if (list == null || list.size() <= 0) {
            return 1;
        }
        return this.filteredData.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        List<DeviceModel> list = this.filteredData;
        return (list == null || list.size() == 0) ? 1 : 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (i >= this.filteredData.size()) {
            return;
        }
        final DeviceViewHolder deviceViewHolder = (DeviceViewHolder) viewHolder;
        if (viewHolder.getItemViewType() == 1) {
            deviceViewHolder.deviceName.setText(R.string.no_devices_available);
            deviceViewHolder.deviceName.setTextColor(Utils.getColorFromResource(R.color.device_targeting_inactive_device));
            return;
        }
        final DeviceModel deviceModel = this.filteredData.get(i);
        final DeviceState stateForCurrentDevice = CommsDaggerWrapper.getComponent().getDeviceStateManager().getStateForCurrentDevice(deviceModel);
        stateForCurrentDevice.setTextAndColor(deviceViewHolder.deviceName, deviceViewHolder.deviceStatus, deviceModel);
        deviceViewHolder.mainView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.ndt.ui.-$$Lambda$DeviceAdapter$0iYa1mkTodh0jHH0CuCNwuC6UKU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceAdapter.DeviceViewHolder.this.deviceSelectionListener.deviceSelected(deviceModel, stateForCurrentDevice);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: onCreateViewHolder */
    public RecyclerView.ViewHolder mo7499onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate;
        if (this.mIsThemedUIEnabled) {
            inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fiesta_drop_in_targeting_list_item, viewGroup, false);
        } else {
            inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.drop_in_targeting_list_item, viewGroup, false);
        }
        return new DeviceViewHolder(inflate, this.deviceSelectionListener);
    }

    public void setDeviceList(List<DeviceModel> list) {
        this.originalData = list;
        this.filteredData = new ArrayList(this.originalData.size());
        getFilter().filter("");
    }

    public DeviceAdapter(DeviceSelectionListener deviceSelectionListener, List<DeviceModel> list, boolean z) {
        this(deviceSelectionListener, list, getDefaultDisabledDropInStates(), z);
    }

    public DeviceAdapter(DeviceSelectionListener deviceSelectionListener, List<DeviceModel> list, List<DropInDisableReason> list2, boolean z) {
        this.disabledDropInStates = new ArrayList();
        this.deviceSelectionListener = deviceSelectionListener;
        this.disabledDropInStates = list2;
        this.mIsThemedUIEnabled = z;
        this.deviceFilter = new DeviceFilter(null);
        setDeviceList(list);
    }
}
