package com.amazon.alexa.lifecycle.api;

import java.util.Map;
/* loaded from: classes9.dex */
public interface LifecycleManager {
    Map<String, Boolean> getCurrentState();

    void notify(LifecycleEvent lifecycleEvent);

    void start();
}
