package com.amazonaws.auth.policy.actions;

import com.amazonaws.auth.policy.Action;
/* loaded from: classes13.dex */
public enum SQSActions implements Action {
    AllSQSActions("sqs:*"),
    AddPermission("sqs:AddPermission"),
    ChangeMessageVisibility("sqs:ChangeMessageVisibility"),
    ChangeMessageVisibilityBatch("sqs:ChangeMessageVisibilityBatch"),
    CreateQueue("sqs:CreateQueue"),
    DeleteMessage("sqs:DeleteMessage"),
    DeleteMessageBatch("sqs:DeleteMessageBatch"),
    DeleteQueue("sqs:DeleteQueue"),
    GetQueueAttributes("sqs:GetQueueAttributes"),
    GetQueueUrl("sqs:GetQueueUrl"),
    ListDeadLetterSourceQueues("sqs:ListDeadLetterSourceQueues"),
    ListQueues("sqs:ListQueues"),
    PurgeQueue("sqs:PurgeQueue"),
    ReceiveMessage("sqs:ReceiveMessage"),
    RemovePermission("sqs:RemovePermission"),
    SendMessage("sqs:SendMessage"),
    SendMessageBatch("sqs:SendMessageBatch"),
    SetQueueAttributes("sqs:SetQueueAttributes");
    
    private final String action;

    SQSActions(String str) {
        this.action = str;
    }

    @Override // com.amazonaws.auth.policy.Action
    public String getActionName() {
        return this.action;
    }
}
