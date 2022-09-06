package com.amazonaws.auth.policy.actions;

import com.amazonaws.auth.policy.Action;
/* loaded from: classes13.dex */
public enum SimpleEmailServiceActions implements Action {
    AllSimpleEmailServiceActions("ses:*"),
    CloneReceiptRuleSet("ses:CloneReceiptRuleSet"),
    CreateConfigurationSet("ses:CreateConfigurationSet"),
    CreateConfigurationSetEventDestination("ses:CreateConfigurationSetEventDestination"),
    CreateConfigurationSetTrackingOptions("ses:CreateConfigurationSetTrackingOptions"),
    CreateCustomVerificationEmailTemplate("ses:CreateCustomVerificationEmailTemplate"),
    CreateReceiptFilter("ses:CreateReceiptFilter"),
    CreateReceiptRule("ses:CreateReceiptRule"),
    CreateReceiptRuleSet("ses:CreateReceiptRuleSet"),
    CreateTemplate("ses:CreateTemplate"),
    DeleteConfigurationSet("ses:DeleteConfigurationSet"),
    DeleteConfigurationSetEventDestination("ses:DeleteConfigurationSetEventDestination"),
    DeleteConfigurationSetTrackingOptions("ses:DeleteConfigurationSetTrackingOptions"),
    DeleteCustomVerificationEmailTemplate("ses:DeleteCustomVerificationEmailTemplate"),
    DeleteIdentity("ses:DeleteIdentity"),
    DeleteIdentityPolicy("ses:DeleteIdentityPolicy"),
    DeleteReceiptFilter("ses:DeleteReceiptFilter"),
    DeleteReceiptRule("ses:DeleteReceiptRule"),
    DeleteReceiptRuleSet("ses:DeleteReceiptRuleSet"),
    DeleteTemplate("ses:DeleteTemplate"),
    DeleteVerifiedEmailAddress("ses:DeleteVerifiedEmailAddress"),
    DescribeActiveReceiptRuleSet("ses:DescribeActiveReceiptRuleSet"),
    DescribeConfigurationSet("ses:DescribeConfigurationSet"),
    DescribeReceiptRule("ses:DescribeReceiptRule"),
    DescribeReceiptRuleSet("ses:DescribeReceiptRuleSet"),
    GetAccountSendingEnabled("ses:GetAccountSendingEnabled"),
    GetCustomVerificationEmailTemplate("ses:GetCustomVerificationEmailTemplate"),
    GetIdentityDkimAttributes("ses:GetIdentityDkimAttributes"),
    GetIdentityMailFromDomainAttributes("ses:GetIdentityMailFromDomainAttributes"),
    GetIdentityNotificationAttributes("ses:GetIdentityNotificationAttributes"),
    GetIdentityPolicies("ses:GetIdentityPolicies"),
    GetIdentityVerificationAttributes("ses:GetIdentityVerificationAttributes"),
    GetSendQuota("ses:GetSendQuota"),
    GetSendStatistics("ses:GetSendStatistics"),
    GetTemplate("ses:GetTemplate"),
    ListConfigurationSets("ses:ListConfigurationSets"),
    ListCustomVerificationEmailTemplates("ses:ListCustomVerificationEmailTemplates"),
    ListIdentities("ses:ListIdentities"),
    ListIdentityPolicies("ses:ListIdentityPolicies"),
    ListReceiptFilters("ses:ListReceiptFilters"),
    ListReceiptRuleSets("ses:ListReceiptRuleSets"),
    ListTemplates("ses:ListTemplates"),
    ListVerifiedEmailAddresses("ses:ListVerifiedEmailAddresses"),
    PutIdentityPolicy("ses:PutIdentityPolicy"),
    ReorderReceiptRuleSet("ses:ReorderReceiptRuleSet"),
    SendBounce("ses:SendBounce"),
    SendBulkTemplatedEmail("ses:SendBulkTemplatedEmail"),
    SendCustomVerificationEmail("ses:SendCustomVerificationEmail"),
    SendEmail("ses:SendEmail"),
    SendRawEmail("ses:SendRawEmail"),
    SendTemplatedEmail("ses:SendTemplatedEmail"),
    SetActiveReceiptRuleSet("ses:SetActiveReceiptRuleSet"),
    SetIdentityDkimEnabled("ses:SetIdentityDkimEnabled"),
    SetIdentityFeedbackForwardingEnabled("ses:SetIdentityFeedbackForwardingEnabled"),
    SetIdentityHeadersInNotificationsEnabled("ses:SetIdentityHeadersInNotificationsEnabled"),
    SetIdentityMailFromDomain("ses:SetIdentityMailFromDomain"),
    SetIdentityNotificationTopic("ses:SetIdentityNotificationTopic"),
    SetReceiptRulePosition("ses:SetReceiptRulePosition"),
    TestRenderTemplate("ses:TestRenderTemplate"),
    UpdateAccountSendingEnabled("ses:UpdateAccountSendingEnabled"),
    UpdateConfigurationSetEventDestination("ses:UpdateConfigurationSetEventDestination"),
    UpdateConfigurationSetReputationMetricsEnabled("ses:UpdateConfigurationSetReputationMetricsEnabled"),
    UpdateConfigurationSetSendingEnabled("ses:UpdateConfigurationSetSendingEnabled"),
    UpdateConfigurationSetTrackingOptions("ses:UpdateConfigurationSetTrackingOptions"),
    UpdateCustomVerificationEmailTemplate("ses:UpdateCustomVerificationEmailTemplate"),
    UpdateReceiptRule("ses:UpdateReceiptRule"),
    UpdateTemplate("ses:UpdateTemplate"),
    VerifyDomainDkim("ses:VerifyDomainDkim"),
    VerifyDomainIdentity("ses:VerifyDomainIdentity"),
    VerifyEmailAddress("ses:VerifyEmailAddress"),
    VerifyEmailIdentity("ses:VerifyEmailIdentity");
    
    private final String action;

    SimpleEmailServiceActions(String str) {
        this.action = str;
    }

    @Override // com.amazonaws.auth.policy.Action
    public String getActionName() {
        return this.action;
    }
}
