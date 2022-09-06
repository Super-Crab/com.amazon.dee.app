package com.amazon.identity.auth.device.utils;

import com.amazon.identity.auth.device.api.authorization.Region;
import com.amazon.identity.auth.device.authorization.Stage;
import com.amazon.identity.auth.map.device.utils.MAPLog;
/* loaded from: classes12.dex */
public abstract class DefaultLibraryInfo {
    private static final String TAG = "com.amazon.identity.auth.device.utils.DefaultLibraryInfo";
    private static Stage stage = Stage.PROD;
    private static Region region = Region.AUTO;

    public static synchronized Region getLibraryRegion() {
        Region region2;
        synchronized (DefaultLibraryInfo.class) {
            region2 = region;
        }
        return region2;
    }

    public static synchronized Stage getOverrideLibraryStage() {
        Stage stage2;
        synchronized (DefaultLibraryInfo.class) {
            stage2 = stage;
        }
        return stage2;
    }

    public static synchronized boolean isDevo() {
        boolean z;
        synchronized (DefaultLibraryInfo.class) {
            z = !isProd();
        }
        return z;
    }

    public static synchronized boolean isProd() {
        boolean z;
        synchronized (DefaultLibraryInfo.class) {
            z = stage == Stage.PROD;
        }
        return z;
    }

    public static synchronized void setLibraryRegion(Region region2) {
        synchronized (DefaultLibraryInfo.class) {
            region = region2;
            String str = TAG;
            MAPLog.i(str, "App Region overwritten : " + region.toString());
        }
    }

    public static synchronized void setOverrideAppStage(Stage stage2) {
        synchronized (DefaultLibraryInfo.class) {
            stage = stage2;
            String str = TAG;
            MAPLog.i(str, "App Stage overwritten : " + stage.toString());
        }
    }
}
