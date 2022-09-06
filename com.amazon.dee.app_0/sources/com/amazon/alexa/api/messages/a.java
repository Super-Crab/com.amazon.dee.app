package com.amazon.alexa.api.messages;

import android.os.HandlerThread;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes6.dex */
class a {
    private static final String a = "a";
    private final Map<Class, HandlerThread> b = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized HandlerThread a(Class<? extends MessageProcessor> cls) {
        if (!this.b.containsKey(cls)) {
            HandlerThread b = b(cls);
            b.start();
            this.b.put(cls, b);
        }
        String str = "getting the handler for class " + cls;
        return this.b.get(cls);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void a() {
        for (HandlerThread handlerThread : this.b.values()) {
            handlerThread.quitSafely();
        }
        this.b.clear();
    }

    protected HandlerThread b(Class<? extends MessageProcessor> cls) {
        String str = "creating handler thread for " + cls;
        return new HandlerThread(cls.getSimpleName() + "-handlerThread");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void c(Class<? extends MessageProcessor> cls) {
        if (this.b.containsKey(cls)) {
            String str = "releasing handler thread for " + cls;
            this.b.remove(cls).quitSafely();
        } else {
            Log.w(a, "trying to release a handler without having any for " + cls);
        }
    }
}
