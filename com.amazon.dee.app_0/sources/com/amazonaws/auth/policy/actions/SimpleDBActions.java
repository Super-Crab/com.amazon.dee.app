package com.amazonaws.auth.policy.actions;

import com.amazonaws.auth.policy.Action;
/* loaded from: classes13.dex */
public enum SimpleDBActions implements Action {
    AllSimpleDBActions("sdb:*"),
    BatchDeleteAttributes("sdb:BatchDeleteAttributes"),
    BatchPutAttributes("sdb:BatchPutAttributes"),
    CreateDomain("sdb:CreateDomain"),
    DeleteAttributes("sdb:DeleteAttributes"),
    DeleteDomain("sdb:DeleteDomain"),
    DomainMetadata("sdb:DomainMetadata"),
    GetAttributes("sdb:GetAttributes"),
    ListDomains("sdb:ListDomains"),
    PutAttributes("sdb:PutAttributes"),
    Select("sdb:Select");
    
    private final String action;

    SimpleDBActions(String str) {
        this.action = str;
    }

    @Override // com.amazonaws.auth.policy.Action
    public String getActionName() {
        return this.action;
    }
}
