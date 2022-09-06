package com.amazon.leaderselection;

import android.content.ComponentName;
import android.content.Context;
import com.amazon.alexa.client.annotations.Nullable;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes12.dex */
class b {
    private final Map<ComponentName, a> a = new HashMap();

    private boolean b(Context context, ComponentName componentName) {
        return context != null && componentName != null && !componentName.equals(h.a) && !context.getPackageName().equals(componentName.getPackageName());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public synchronized a a(Context context, ComponentName componentName) {
        if (!b(context, componentName)) {
            return null;
        }
        a aVar = this.a.get(componentName);
        if (aVar == null) {
            aVar = new a(context, componentName);
            this.a.put(componentName, aVar);
        }
        return aVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void a() {
        for (a aVar : this.a.values()) {
            if (aVar.d()) {
                aVar.a();
            }
        }
        this.a.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void a(a aVar) {
        this.a.remove(aVar.c());
    }
}
