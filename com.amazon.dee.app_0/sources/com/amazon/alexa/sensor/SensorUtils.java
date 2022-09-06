package com.amazon.alexa.sensor;

import android.os.HandlerThread;
import android.os.Looper;
import androidx.annotation.NonNull;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
/* loaded from: classes10.dex */
public final class SensorUtils {
    private SensorUtils() {
    }

    public static Looper getLooper(@NonNull String str) {
        Looper myLooper = Looper.myLooper();
        if (myLooper == null) {
            HandlerThread handlerThread = new HandlerThread(str);
            handlerThread.start();
            return handlerThread.getLooper();
        }
        return myLooper;
    }

    public static boolean isProdRelease() {
        EnvironmentService environmentService = (EnvironmentService) ComponentRegistry.getInstance().getLazy(EnvironmentService.class).mo10268get();
        String buildFlavor = environmentService.getBuildFlavor();
        return (buildFlavor.equals("prod") || buildFlavor.equals("prodPhoenix")) && !environmentService.isDebugBuild();
    }
}
