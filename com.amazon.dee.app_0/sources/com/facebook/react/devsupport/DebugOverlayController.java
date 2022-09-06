package com.facebook.react.devsupport;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.view.WindowManager;
import android.widget.FrameLayout;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.ReactConstants;
/* loaded from: classes2.dex */
class DebugOverlayController {
    @Nullable
    private FrameLayout mFPSDebugViewContainer;
    private final ReactContext mReactContext;
    private final WindowManager mWindowManager;

    public DebugOverlayController(ReactContext reactContext) {
        this.mReactContext = reactContext;
        this.mWindowManager = (WindowManager) reactContext.getSystemService("window");
    }

    private static boolean canHandleIntent(Context context, Intent intent) {
        return intent.resolveActivity(context.getPackageManager()) != null;
    }

    private static boolean hasPermission(Context context, String str) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096);
            if (packageInfo.requestedPermissions != null) {
                for (String str2 : packageInfo.requestedPermissions) {
                    if (str2.equals(str)) {
                        return true;
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            FLog.e(ReactConstants.TAG, "Error while retrieving package info", e);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean permissionCheck(Context context) {
        int i = Build.VERSION.SDK_INT;
        return Settings.canDrawOverlays(context);
    }

    public static void requestPermission(Context context) {
        int i = Build.VERSION.SDK_INT;
        if (!Settings.canDrawOverlays(context)) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("package:");
            outline107.append(context.getPackageName());
            Intent intent = new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse(outline107.toString()));
            intent.setFlags(268435456);
            FLog.w(ReactConstants.TAG, "Overlay permissions needs to be granted in order for react native apps to run in dev mode");
            if (!canHandleIntent(context, intent)) {
                return;
            }
            context.startActivity(intent);
        }
    }

    public void setFpsDebugViewVisible(final boolean z) {
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.devsupport.DebugOverlayController.1
            @Override // java.lang.Runnable
            public void run() {
                if (z && DebugOverlayController.this.mFPSDebugViewContainer == null) {
                    if (!DebugOverlayController.permissionCheck(DebugOverlayController.this.mReactContext)) {
                        FLog.d(ReactConstants.TAG, "Wait for overlay permission to be set");
                        return;
                    }
                    DebugOverlayController debugOverlayController = DebugOverlayController.this;
                    debugOverlayController.mFPSDebugViewContainer = new FpsView(debugOverlayController.mReactContext);
                    DebugOverlayController.this.mWindowManager.addView(DebugOverlayController.this.mFPSDebugViewContainer, new WindowManager.LayoutParams(-1, -1, WindowOverlayCompat.TYPE_SYSTEM_OVERLAY, 24, -3));
                } else if (z || DebugOverlayController.this.mFPSDebugViewContainer == null) {
                } else {
                    DebugOverlayController.this.mFPSDebugViewContainer.removeAllViews();
                    DebugOverlayController.this.mWindowManager.removeView(DebugOverlayController.this.mFPSDebugViewContainer);
                    DebugOverlayController.this.mFPSDebugViewContainer = null;
                }
            }
        });
    }
}
