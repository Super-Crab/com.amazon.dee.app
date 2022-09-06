package com.amazon.alexa.api;

import android.os.Bundle;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class v<K> extends az<K, Bundle> {
    private static final String a = "v";
    private final x<K> b;
    private final ar<K> c;
    private final Map<K, w> d = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public v(x<K> xVar, ar<K> arVar) {
        this.b = xVar;
        this.c = arVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.alexa.api.az
    /* renamed from: a */
    public Bundle mo846createValueFor(K k) {
        w createBundlerFor = this.b.createBundlerFor(k);
        this.d.put(k, createBundlerFor);
        return createBundlerFor.getBundle();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.alexa.api.az
    /* renamed from: a */
    public void clearValue(K k, Bundle bundle) {
        w remove = this.d.remove(k);
        if (remove != null) {
            remove.clear();
            this.c.onRemove(k);
            return;
        }
        String str = a;
        Log.w(str, "Could not find Bundler to clear for key: " + k);
    }
}
