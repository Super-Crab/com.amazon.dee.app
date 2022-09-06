package com.amazon.ptz.gestures.handlers;

import com.amazon.ptz.gestures.Gesture;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Qualifier;
/* loaded from: classes13.dex */
public interface GestureHandler {

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: classes13.dex */
    public @interface Router {
    }

    boolean canHandle(Gesture gesture);

    void handle(Gesture gesture);
}
