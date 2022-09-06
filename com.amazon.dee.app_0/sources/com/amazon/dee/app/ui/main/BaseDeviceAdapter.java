package com.amazon.dee.app.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.databinding.DataBindingUtil;
import com.amazon.dee.app.R;
import com.amazon.dee.app.databinding.MainDeviceItemBinding;
import com.amazon.dee.app.ui.web.DeviceInfo;
import java.util.ArrayList;
import java.util.Collection;
/* loaded from: classes12.dex */
public abstract class BaseDeviceAdapter extends BaseAdapter {
    ArrayList<DeviceInfo> items = new ArrayList<>();

    public void clear() {
        this.items.clear();
        notifyDataSetChanged();
    }

    public int findPosition(DeviceInfo deviceInfo) {
        return this.items.indexOf(deviceInfo);
    }

    public DeviceInfo get(int i) {
        return this.items.get(i);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.items.size();
    }

    @Override // android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        MainDeviceItemBinding mainDeviceItemBinding = (MainDeviceItemBinding) DataBindingUtil.getBinding(view);
        if (mainDeviceItemBinding == null) {
            mainDeviceItemBinding = (MainDeviceItemBinding) DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.main_device_item, viewGroup, false);
        }
        mainDeviceItemBinding.setModel(this.items.get(i));
        mainDeviceItemBinding.executePendingBindings();
        return mainDeviceItemBinding.getRoot();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.items.get(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public void set(Collection<? extends DeviceInfo> collection) {
        this.items = new ArrayList<>(collection);
        notifyDataSetChanged();
    }
}
