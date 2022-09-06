package com.amazon.alexa.redesign.utils;
/* loaded from: classes10.dex */
class ViewMalformedException extends Exception {
    private String viewType;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewMalformedException(String str, String str2) {
        super(str);
        this.viewType = str2;
    }

    public String getViewType() {
        return this.viewType;
    }
}
