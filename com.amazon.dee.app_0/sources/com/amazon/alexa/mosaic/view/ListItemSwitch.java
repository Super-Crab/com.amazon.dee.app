package com.amazon.alexa.mosaic.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.appcompat.widget.SwitchCompat;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.mosaic.components.ThemeUtil;
/* loaded from: classes9.dex */
public class ListItemSwitch extends ListItem {
    private static final String TAG = ListItemSwitch.class.getSimpleName();
    boolean clickable;
    protected SwitchCompat inputSwitch;

    public ListItemSwitch(Context context) {
        this(context, null);
    }

    @Override // com.amazon.alexa.mosaic.view.ListItem
    void inflateLayout() {
        ViewGroup.inflate(getContext(), R.layout.mosaic_list_item_switch, this);
    }

    public boolean isChecked() {
        this.inputSwitch = (SwitchCompat) findViewById(R.id.inputSwitch);
        SwitchCompat switchCompat = this.inputSwitch;
        if (switchCompat == null) {
            LogUtils.d(TAG, "cannot return checked value of null inputSwitch");
            return false;
        }
        return switchCompat.isChecked();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.alexa.mosaic.view.ListItem, android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        setClickable(this.clickable);
    }

    @Override // android.view.View
    public void setClickable(boolean z) {
        super.setClickable(z);
        this.secondaryTextView = (TextView) findViewById(R.id.secondaryText);
        TextView textView = this.secondaryTextView;
        if (textView == null) {
            LogUtils.w(TAG, "cannot change color of secondaryTextView when it is null");
        } else if (z) {
            textView.setTextColor(ThemeUtil.getColorFromAttribute(getContext(), R.attr.mosaicAction10));
        } else {
            textView.setTextColor(ThemeUtil.getColorFromAttribute(getContext(), R.attr.mosaicNeutral10));
        }
    }

    public void setIsAlertSwitchEnabled(boolean z) {
        setClickable(z);
        this.inputSwitch = (SwitchCompat) findViewById(R.id.inputSwitch);
        this.inputSwitch.setEnabled(z);
    }

    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.inputSwitch = (SwitchCompat) findViewById(R.id.inputSwitch);
        SwitchCompat switchCompat = this.inputSwitch;
        if (switchCompat == null) {
            LogUtils.d(TAG, "cannot add OnCheckedChangeListener to null inputSwitch");
        } else {
            switchCompat.setOnCheckedChangeListener(onCheckedChangeListener);
        }
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        super.setOnClickListener(onClickListener);
        setClickable(true);
    }

    public void setSwitchIsChecked(boolean z) {
        this.inputSwitch = (SwitchCompat) findViewById(R.id.inputSwitch);
        SwitchCompat switchCompat = this.inputSwitch;
        if (switchCompat == null) {
            LogUtils.d(TAG, "cannot set checked value of null inputSwitch");
        } else {
            switchCompat.setChecked(z);
        }
    }

    public void setSwitchOnCheckedChange(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        setOnCheckedChangeListener(onCheckedChangeListener);
    }

    public ListItemSwitch(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ListItemSwitch(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
