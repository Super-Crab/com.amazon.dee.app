package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public enum Suppress {
    Deduplication("deduplication"),
    Process("process");
    
    public final String parameter;

    Suppress(String str) {
        this.parameter = str;
    }
}
