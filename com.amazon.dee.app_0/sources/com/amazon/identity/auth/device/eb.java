package com.amazon.identity.auth.device;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public abstract class eb {
    public static final a lg = new a() { // from class: com.amazon.identity.auth.device.eb.1
        @Override // com.amazon.identity.auth.device.eb.a
        public boolean a(ek ekVar, ComponentName componentName) {
            return ekVar.a(componentName) != null;
        }
    };
    protected final ComponentName mComponentName;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public interface a {
        boolean a(ek ekVar, ComponentName componentName);
    }

    public eb(ComponentName componentName) {
        this.mComponentName = componentName;
    }

    public static ComponentName a(Context context, String str, a aVar) {
        ek ekVar = new ek(context);
        ComponentName[] componentNameArr = {new ComponentName("com.amazon.imp", str), new ComponentName("com.amazon.sso", str), new ComponentName("com.amazon.dcp", str), new ComponentName("com.amazon.fv", str), new ComponentName("com.amazon.canary", str), new ComponentName(context, str)};
        for (int i = 0; i < 6; i++) {
            ComponentName componentName = componentNameArr[i];
            if (aVar.a(ekVar, componentName)) {
                return componentName;
            }
        }
        for (String str2 : ekVar.ee()) {
            ComponentName componentName2 = new ComponentName(str2, str);
            if (aVar.a(ekVar, componentName2)) {
                return componentName2;
            }
        }
        return null;
    }

    public Intent bw(String str) {
        Intent intent = new Intent();
        intent.setAction(str);
        ComponentName componentName = this.mComponentName;
        if (componentName != null) {
            intent.setComponent(componentName);
        }
        return intent;
    }
}
