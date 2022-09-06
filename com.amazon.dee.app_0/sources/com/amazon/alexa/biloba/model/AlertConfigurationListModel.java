package com.amazon.alexa.biloba.model;

import java.util.ArrayList;
import java.util.List;
/* loaded from: classes6.dex */
public class AlertConfigurationListModel {
    List<AlertConfigurationViewItemModel> list = new ArrayList();
    AlertConfigurationViewItemModel pauseAlertConfiguration;

    public void addItem(AlertConfigurationViewItemModel alertConfigurationViewItemModel) {
        if (alertConfigurationViewItemModel.getAlertConfigurationType() == 4) {
            this.pauseAlertConfiguration = alertConfigurationViewItemModel;
        } else {
            this.list.add(alertConfigurationViewItemModel);
        }
    }

    public List<AlertConfigurationViewItemModel> getList() {
        return this.list;
    }

    public AlertConfigurationViewItemModel getPauseAlertConfiguration() {
        return this.pauseAlertConfiguration;
    }
}
