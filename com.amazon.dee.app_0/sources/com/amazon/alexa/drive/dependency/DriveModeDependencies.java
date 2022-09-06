package com.amazon.alexa.drive.dependency;

import android.content.Context;
import android.os.Looper;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.regulator.Router;
import com.google.common.base.Preconditions;
/* loaded from: classes7.dex */
public final class DriveModeDependencies {
    private static AppComponent daggerAppComponent = null;
    private static DriveModeDaggerComponent driveModeDaggerComponent = null;
    private static boolean ignoreInitializingDriveModeRootComponent = false;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public static class DriveModeDaggerComponent {
        private static int nextGenerationId;
        private final DriveModeRootComponent daggerDriveModeRootComponent;
        private final int daggerDriveModeRootComponentGenerationId;

        DriveModeDaggerComponent(DriveModeRootComponent driveModeRootComponent) {
            int i = nextGenerationId + 1;
            nextGenerationId = i;
            this.daggerDriveModeRootComponentGenerationId = i;
            this.daggerDriveModeRootComponent = driveModeRootComponent;
        }
    }

    private DriveModeDependencies() {
    }

    public static void appInitialize() {
        daggerAppComponent = DaggerAppComponent.builder().build();
    }

    public static boolean doesDriveModeDaggerBelongsToTheGeneration(int i) {
        Preconditions.checkState(Looper.getMainLooper().getThread() == Thread.currentThread());
        DriveModeDaggerComponent driveModeDaggerComponent2 = driveModeDaggerComponent;
        return driveModeDaggerComponent2 != null && driveModeDaggerComponent2.daggerDriveModeRootComponentGenerationId == i;
    }

    public static int driveModeInitialize(Context context, Router router) {
        Preconditions.checkState(Looper.getMainLooper().getThread() == Thread.currentThread());
        if (ignoreInitializingDriveModeRootComponent) {
            DriveModeDaggerComponent driveModeDaggerComponent2 = driveModeDaggerComponent;
            if (driveModeDaggerComponent2 != null) {
                return driveModeDaggerComponent2.daggerDriveModeRootComponentGenerationId;
            }
            return 0;
        }
        driveModeDaggerComponent = new DriveModeDaggerComponent(getAppComponent().getDriveModeRootComponent(new AndroidModule(context), new RoutingModule(router)));
        return driveModeDaggerComponent.daggerDriveModeRootComponentGenerationId;
    }

    public static void driveModeUninitialize(int i) {
        Preconditions.checkState(Looper.getMainLooper().getThread() == Thread.currentThread());
        if (doesDriveModeDaggerBelongsToTheGeneration(i)) {
            driveModeDaggerComponent = null;
        }
    }

    public static AppComponent getAppComponent() {
        return daggerAppComponent;
    }

    @Nullable
    public static DriveModeRootComponent getDriveModeRootComponent() {
        DriveModeDaggerComponent driveModeDaggerComponent2 = driveModeDaggerComponent;
        if (driveModeDaggerComponent2 != null) {
            return driveModeDaggerComponent2.daggerDriveModeRootComponent;
        }
        return null;
    }

    @VisibleForTesting
    public static int setDaggerDriveModeRootComponent(DriveModeRootComponent driveModeRootComponent) {
        driveModeDaggerComponent = new DriveModeDaggerComponent(driveModeRootComponent);
        return driveModeDaggerComponent.daggerDriveModeRootComponentGenerationId;
    }

    @VisibleForTesting
    public static void setIgnoreInitializingDriveModeRootComponent(boolean z) {
        ignoreInitializingDriveModeRootComponent = z;
    }
}
