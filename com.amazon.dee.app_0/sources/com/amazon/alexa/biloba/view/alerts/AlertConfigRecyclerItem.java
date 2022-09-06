package com.amazon.alexa.biloba.view.alerts;

import android.view.View;
import android.widget.CompoundButton;
import androidx.annotation.NonNull;
import com.amazon.alexa.biloba.BR;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.model.AlertConfigurationViewItemModel;
import com.amazon.alexa.biloba.view.common.recycler.BaseRecyclerItem;
/* loaded from: classes6.dex */
public class AlertConfigRecyclerItem extends BaseRecyclerItem<AlertConfigurationViewItemModel> {
    private boolean isAlertSwitchEnabled;
    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
    private View.OnClickListener onClickListener;

    public AlertConfigRecyclerItem(@NonNull AlertConfigurationViewItemModel alertConfigurationViewItemModel) {
        super(alertConfigurationViewItemModel, R.layout.alert_item, BR.alert);
        this.isAlertSwitchEnabled = true;
    }

    public View.OnClickListener getClickListener() {
        return this.onClickListener;
    }

    public String getDescription() {
        return getData().getDescription();
    }

    public CompoundButton.OnCheckedChangeListener getEnableDisableSwitchListener() {
        return this.onCheckedChangeListener;
    }

    public String getTitle() {
        return getData().getTitle();
    }

    public boolean isAlertSwitchEnabled() {
        return this.isAlertSwitchEnabled;
    }

    public boolean isChecked() {
        return getData().isEnabled();
    }

    public void setAlertSwitchEnabled(boolean z) {
        this.isAlertSwitchEnabled = z;
    }

    public void setClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setEnableDisableSwitchListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.onCheckedChangeListener = onCheckedChangeListener;
    }
}
