package com.typesafe.config.impl;

import java.util.HashMap;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class ResolveMemos {
    private final Map<MemoKey, AbstractConfigValue> memos;

    private ResolveMemos(Map<MemoKey, AbstractConfigValue> map) {
        this.memos = map;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractConfigValue get(MemoKey memoKey) {
        return this.memos.get(memoKey);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ResolveMemos put(MemoKey memoKey, AbstractConfigValue abstractConfigValue) {
        HashMap hashMap = new HashMap(this.memos);
        hashMap.put(memoKey, abstractConfigValue);
        return new ResolveMemos(hashMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ResolveMemos() {
        this(new HashMap());
    }
}
