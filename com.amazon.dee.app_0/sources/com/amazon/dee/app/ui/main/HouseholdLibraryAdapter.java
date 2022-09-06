package com.amazon.dee.app.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.databinding.DataBindingUtil;
import com.amazon.dee.app.R;
import com.amazon.dee.app.databinding.MainHouseholdItemBinding;
import com.amazon.dee.app.databinding.MainHouseholdMenuItemBinding;
import com.amazon.dee.app.ui.web.HouseholdLibraryInfo;
import java.util.ArrayList;
import java.util.Collection;
/* loaded from: classes12.dex */
public class HouseholdLibraryAdapter extends BaseAdapter {
    ArrayList<HouseholdLibraryInfo> items = new ArrayList<>();

    public void clear() {
        this.items.clear();
        notifyDataSetChanged();
    }

    public int findPosition(HouseholdLibraryInfo householdLibraryInfo) {
        return this.items.indexOf(householdLibraryInfo);
    }

    public HouseholdLibraryInfo get(int i) {
        return this.items.get(i);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.items.size();
    }

    @Override // android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        MainHouseholdItemBinding mainHouseholdItemBinding = (MainHouseholdItemBinding) DataBindingUtil.getBinding(view);
        if (mainHouseholdItemBinding == null) {
            mainHouseholdItemBinding = (MainHouseholdItemBinding) DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.main_household_item, viewGroup, false);
        }
        mainHouseholdItemBinding.setLibrary(this.items.get(i));
        mainHouseholdItemBinding.executePendingBindings();
        return mainHouseholdItemBinding.getRoot();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.items.get(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        MainHouseholdMenuItemBinding mainHouseholdMenuItemBinding = (MainHouseholdMenuItemBinding) DataBindingUtil.getBinding(view);
        if (mainHouseholdMenuItemBinding == null) {
            mainHouseholdMenuItemBinding = (MainHouseholdMenuItemBinding) DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.main_household_menu_item, viewGroup, false);
        }
        mainHouseholdMenuItemBinding.executePendingBindings();
        return mainHouseholdMenuItemBinding.getRoot();
    }

    public void set(Collection<? extends HouseholdLibraryInfo> collection) {
        this.items = new ArrayList<>(collection);
        notifyDataSetChanged();
    }
}
