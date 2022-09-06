package com.amazon.alexa.api;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
/* loaded from: classes6.dex */
public class bl {
    private final Map<Object, a> a = Collections.synchronizedMap(new LinkedHashMap());

    /* loaded from: classes6.dex */
    public static class a {
        private final Runnable a;
        private final Runnable b;

        public a(Runnable runnable, Runnable runnable2) {
            this.a = runnable;
            this.b = runnable2;
        }

        public void a() {
            this.a.run();
        }

        public void b() {
            this.b.run();
        }
    }

    public a a(Object obj) {
        return this.a.remove(obj);
    }

    public void a() {
        synchronized (this.a) {
            for (a aVar : new LinkedHashSet(this.a.values())) {
                aVar.a();
            }
        }
    }

    public void a(Object obj, Runnable runnable, Runnable runnable2) {
        if (!this.a.containsKey(obj)) {
            this.a.put(obj, new a(runnable, runnable2));
        }
    }

    public void b() {
        synchronized (this.a) {
            for (a aVar : new LinkedHashSet(this.a.values())) {
                aVar.b();
            }
        }
    }

    public void c() {
        this.a.clear();
    }
}
