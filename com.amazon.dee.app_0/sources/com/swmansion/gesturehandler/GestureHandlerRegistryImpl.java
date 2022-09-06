package com.swmansion.gesturehandler;

import android.view.View;
import java.util.ArrayList;
import java.util.WeakHashMap;
/* loaded from: classes3.dex */
public class GestureHandlerRegistryImpl implements GestureHandlerRegistry {
    private WeakHashMap<View, ArrayList<GestureHandler>> mHandlers = new WeakHashMap<>();

    @Override // com.swmansion.gesturehandler.GestureHandlerRegistry
    public ArrayList<GestureHandler> getHandlersForView(View view) {
        return this.mHandlers.get(view);
    }

    public <T extends GestureHandler> T registerHandlerForView(View view, T t) {
        ArrayList<GestureHandler> arrayList = this.mHandlers.get(view);
        if (arrayList == null) {
            ArrayList<GestureHandler> arrayList2 = new ArrayList<>(1);
            arrayList2.add(t);
            this.mHandlers.put(view, arrayList2);
        } else {
            arrayList.add(t);
        }
        return t;
    }
}
