package com.amazon.alexa.fitness.dagger;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.fitness.logs.AndroidLog;
import com.amazon.alexa.fitness.logs.ILog;
/* loaded from: classes8.dex */
public class StaticReleasePackageComponentHolder {
    private static final String COMPONENT = "StaticReleasePackageComponentHolder";
    private static final ILog LOG = new AndroidLog.Release();
    private static StaticReleasePackageComponent sStaticReleasePackageComponent;

    private StaticReleasePackageComponentHolder() {
        throw new UnsupportedOperationException("Cannot instantiate a static utility class");
    }

    @NonNull
    public static synchronized StaticReleasePackageComponent getPackageComponent() {
        StaticReleasePackageComponent staticReleasePackageComponent;
        synchronized (StaticReleasePackageComponentHolder.class) {
            if (sStaticReleasePackageComponent != null) {
                staticReleasePackageComponent = sStaticReleasePackageComponent;
            } else {
                throw new IllegalStateException("packageComponent property accessed before initialization.");
            }
        }
        return staticReleasePackageComponent;
    }

    public static synchronized void initializePackageComponent(@NonNull Context context) {
        StaticReleasePackageComponent build;
        synchronized (StaticReleasePackageComponentHolder.class) {
            if (sStaticReleasePackageComponent == null) {
                if (isContextDebuggable(context)) {
                    build = DaggerRuntimeDebugStaticReleasePackageComponent.builder().runtimeDebugStaticReleasePackageModule(new RuntimeDebugStaticReleasePackageModule(context)).build();
                } else {
                    build = DaggerRuntimeReleaseStaticReleasePackageComponent.builder().runtimeReleaseStaticReleasePackageModule(new RuntimeReleaseStaticReleasePackageModule(context)).build();
                }
                setPackageComponent(build);
            } else {
                LOG.debug(COMPONENT, "initializePackageComponent(...) called when the StaticReleasePackageComponent has already been initialized", null);
            }
        }
    }

    private static boolean isContextDebuggable(@NonNull Context context) {
        return (context.getApplicationInfo().flags & 2) != 0;
    }

    @VisibleForTesting
    public static synchronized void setPackageComponent(@NonNull StaticReleasePackageComponent staticReleasePackageComponent) {
        synchronized (StaticReleasePackageComponentHolder.class) {
            sStaticReleasePackageComponent = staticReleasePackageComponent;
        }
    }
}
