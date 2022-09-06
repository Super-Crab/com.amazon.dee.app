package com.amazon.alexa.biloba.service;

import com.amazon.alexa.biloba.model.AlertConfigurationViewItemModel;
/* loaded from: classes6.dex */
public class AlertConfigurationRequest {
    private String alertConfigurationId;
    private AlertConfigurationViewItemModel alertConfigurationViewItemModel;
    private String relationshipId;

    public AlertConfigurationRequest(String str, String str2, AlertConfigurationViewItemModel alertConfigurationViewItemModel) {
        this.relationshipId = str;
        this.alertConfigurationId = str2;
        this.alertConfigurationViewItemModel = alertConfigurationViewItemModel;
    }

    public String getAlertConfigurationId() {
        return this.alertConfigurationId;
    }

    public AlertConfigurationViewItemModel getAlertConfigurationViewItemModel() {
        return this.alertConfigurationViewItemModel;
    }

    public String getRelationshipId() {
        return this.relationshipId;
    }

    public void setAlertConfigurationId(String str) {
        this.alertConfigurationId = str;
    }

    public void setAlertConfigurationViewItemModel(AlertConfigurationViewItemModel alertConfigurationViewItemModel) {
        this.alertConfigurationViewItemModel = alertConfigurationViewItemModel;
    }

    public void setRelationshipId(String str) {
        this.relationshipId = str;
    }

    public AlertConfigurationRequest(String str, String str2) {
        this.relationshipId = str;
        this.alertConfigurationId = str2;
    }

    public AlertConfigurationRequest(String str, AlertConfigurationViewItemModel alertConfigurationViewItemModel) {
        this.relationshipId = str;
        this.alertConfigurationViewItemModel = alertConfigurationViewItemModel;
    }
}
