package com.amazon.bluefront.api.v2;
/* loaded from: classes11.dex */
public class DialogHint {
    private String mDialogAct;
    private String mDialogState;

    DialogHint() {
    }

    public String getAct() {
        return this.mDialogAct;
    }

    public String getState() {
        return this.mDialogState;
    }

    void setAct(String str) {
        this.mDialogAct = str;
    }

    void setState(String str) {
        this.mDialogState = str;
    }

    public DialogHint(String str, String str2) {
        this.mDialogState = str;
        this.mDialogAct = str2;
    }
}
