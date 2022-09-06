package com.amazon.alexa.biloba.view.alerts;

import com.amazon.alexa.biloba.model.AlertConfigurationViewItemModel;
import com.amazon.alexa.biloba.view.common.ListItemClickListener;
/* loaded from: classes6.dex */
public interface AlertListItemClickListener extends ListItemClickListener<AlertConfigurationViewItemModel> {
    void onEditButtonClicked(AlertConfigurationViewItemModel alertConfigurationViewItemModel);

    void onEnableDisableSwitchClicked(AlertConfigurationViewItemModel alertConfigurationViewItemModel, boolean z);
}
