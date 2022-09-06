package com.amazonaws.auth.policy.actions;

import com.amazonaws.auth.policy.Action;
/* loaded from: classes13.dex */
public enum SNSActions implements Action {
    AllSNSActions("sns:*"),
    AddPermission("sns:AddPermission"),
    CheckIfPhoneNumberIsOptedOut("sns:CheckIfPhoneNumberIsOptedOut"),
    ConfirmSubscription("sns:ConfirmSubscription"),
    CreatePlatformApplication("sns:CreatePlatformApplication"),
    CreatePlatformEndpoint("sns:CreatePlatformEndpoint"),
    CreateTopic("sns:CreateTopic"),
    DeleteEndpoint("sns:DeleteEndpoint"),
    DeletePlatformApplication("sns:DeletePlatformApplication"),
    DeleteTopic("sns:DeleteTopic"),
    GetEndpointAttributes("sns:GetEndpointAttributes"),
    GetPlatformApplicationAttributes("sns:GetPlatformApplicationAttributes"),
    GetSMSAttributes("sns:GetSMSAttributes"),
    GetSubscriptionAttributes("sns:GetSubscriptionAttributes"),
    GetTopicAttributes("sns:GetTopicAttributes"),
    ListEndpointsByPlatformApplication("sns:ListEndpointsByPlatformApplication"),
    ListPhoneNumbersOptedOut("sns:ListPhoneNumbersOptedOut"),
    ListPlatformApplications("sns:ListPlatformApplications"),
    ListSubscriptions("sns:ListSubscriptions"),
    ListSubscriptionsByTopic("sns:ListSubscriptionsByTopic"),
    ListTopics("sns:ListTopics"),
    OptInPhoneNumber("sns:OptInPhoneNumber"),
    Publish("sns:Publish"),
    RemovePermission("sns:RemovePermission"),
    SetEndpointAttributes("sns:SetEndpointAttributes"),
    SetPlatformApplicationAttributes("sns:SetPlatformApplicationAttributes"),
    SetSMSAttributes("sns:SetSMSAttributes"),
    SetSubscriptionAttributes("sns:SetSubscriptionAttributes"),
    SetTopicAttributes("sns:SetTopicAttributes"),
    Subscribe("sns:Subscribe"),
    Unsubscribe("sns:Unsubscribe");
    
    private final String action;

    SNSActions(String str) {
        this.action = str;
    }

    @Override // com.amazonaws.auth.policy.Action
    public String getActionName() {
        return this.action;
    }
}
