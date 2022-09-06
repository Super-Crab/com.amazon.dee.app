package com.facebook.react.views.picker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Assertions;
import java.util.List;
/* loaded from: classes2.dex */
class ReactPickerAdapter extends ArrayAdapter<ReactPickerItem> {
    @Nullable
    private Integer mBackgroundColor;
    private final LayoutInflater mInflater;
    @Nullable
    private Integer mPrimaryTextColor;

    public ReactPickerAdapter(Context context, List<ReactPickerItem> list) {
        super(context, 0, list);
        this.mInflater = (LayoutInflater) Assertions.assertNotNull(context.getSystemService("layout_inflater"));
    }

    @Nullable
    public Integer getBackgroundColor() {
        return this.mBackgroundColor;
    }

    @Override // android.widget.ArrayAdapter, android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        return getView(i, view, viewGroup, true);
    }

    @Nullable
    public Integer getPrimaryTextColor() {
        return this.mPrimaryTextColor;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        return getView(i, view, viewGroup, false);
    }

    public void setBackgroundColor(@Nullable Integer num) {
        this.mBackgroundColor = num;
        notifyDataSetChanged();
    }

    public void setPrimaryTextColor(@Nullable Integer num) {
        this.mPrimaryTextColor = num;
        notifyDataSetChanged();
    }

    private View getView(int i, View view, ViewGroup viewGroup, boolean z) {
        Integer num;
        ReactPickerItem item = getItem(i);
        boolean z2 = false;
        if (view == null) {
            view = this.mInflater.inflate(z ? 17367049 : 17367048, viewGroup, false);
            view.setTag(((TextView) view).getTextColors());
            z2 = true;
        }
        TextView textView = (TextView) view;
        textView.setText(item.label);
        if (!z && (num = this.mPrimaryTextColor) != null) {
            textView.setTextColor(num.intValue());
        } else {
            Integer num2 = item.color;
            if (num2 != null) {
                textView.setTextColor(num2.intValue());
            } else if (textView.getTag() != null && !z2) {
                textView.setTextColor((ColorStateList) textView.getTag());
            }
        }
        Integer num3 = this.mBackgroundColor;
        if (num3 != null) {
            textView.setBackgroundColor(num3.intValue());
        }
        return textView;
    }
}
