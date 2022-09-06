package com.bugsnag.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class EventReceiver extends BroadcastReceiver {
    private static final String INTENT_ACTION_KEY = "Intent Action";
    @NonNull
    private static final Map<String, BreadcrumbType> actions = buildActions();
    private final Client client;

    public EventReceiver(@NonNull Client client) {
        this.client = client;
    }

    @NonNull
    private static Map<String, BreadcrumbType> buildActions() {
        HashMap hashMap = new HashMap();
        hashMap.put("android.appwidget.action.APPWIDGET_DELETED", BreadcrumbType.USER);
        hashMap.put("android.appwidget.action.APPWIDGET_DISABLED", BreadcrumbType.USER);
        hashMap.put("android.appwidget.action.APPWIDGET_ENABLED", BreadcrumbType.USER);
        hashMap.put("android.appwidget.action.APPWIDGET_HOST_RESTORED", BreadcrumbType.STATE);
        hashMap.put("android.appwidget.action.APPWIDGET_RESTORED", BreadcrumbType.STATE);
        hashMap.put("android.appwidget.action.APPWIDGET_UPDATE", BreadcrumbType.STATE);
        hashMap.put("android.appwidget.action.APPWIDGET_UPDATE_OPTIONS", BreadcrumbType.STATE);
        hashMap.put("android.intent.action.ACTION_POWER_CONNECTED", BreadcrumbType.STATE);
        hashMap.put("android.intent.action.ACTION_POWER_DISCONNECTED", BreadcrumbType.STATE);
        hashMap.put("android.intent.action.ACTION_SHUTDOWN", BreadcrumbType.STATE);
        hashMap.put("android.intent.action.AIRPLANE_MODE", BreadcrumbType.STATE);
        hashMap.put("android.intent.action.BATTERY_LOW", BreadcrumbType.STATE);
        hashMap.put("android.intent.action.BATTERY_OKAY", BreadcrumbType.STATE);
        hashMap.put("android.intent.action.BOOT_COMPLETED", BreadcrumbType.STATE);
        hashMap.put("android.intent.action.CAMERA_BUTTON", BreadcrumbType.USER);
        hashMap.put("android.intent.action.CLOSE_SYSTEM_DIALOGS", BreadcrumbType.USER);
        hashMap.put("android.intent.action.CONFIGURATION_CHANGED", BreadcrumbType.STATE);
        hashMap.put("android.intent.action.CONTENT_CHANGED", BreadcrumbType.STATE);
        hashMap.put("android.intent.action.DATE_CHANGED", BreadcrumbType.STATE);
        hashMap.put("android.intent.action.DEVICE_STORAGE_LOW", BreadcrumbType.STATE);
        hashMap.put("android.intent.action.DEVICE_STORAGE_OK", BreadcrumbType.STATE);
        hashMap.put("android.intent.action.DOCK_EVENT", BreadcrumbType.USER);
        hashMap.put("android.intent.action.DREAMING_STARTED", BreadcrumbType.NAVIGATION);
        hashMap.put("android.intent.action.DREAMING_STOPPED", BreadcrumbType.NAVIGATION);
        hashMap.put("android.intent.action.INPUT_METHOD_CHANGED", BreadcrumbType.STATE);
        hashMap.put("android.intent.action.LOCALE_CHANGED", BreadcrumbType.STATE);
        hashMap.put("android.intent.action.REBOOT", BreadcrumbType.STATE);
        hashMap.put("android.intent.action.SCREEN_OFF", BreadcrumbType.STATE);
        hashMap.put("android.intent.action.SCREEN_ON", BreadcrumbType.STATE);
        hashMap.put("android.intent.action.TIMEZONE_CHANGED", BreadcrumbType.STATE);
        hashMap.put("android.intent.action.TIME_SET", BreadcrumbType.STATE);
        hashMap.put("android.os.action.DEVICE_IDLE_MODE_CHANGED", BreadcrumbType.STATE);
        hashMap.put("android.os.action.POWER_SAVE_MODE_CHANGED", BreadcrumbType.STATE);
        return hashMap;
    }

    @NonNull
    public static IntentFilter getIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        for (String str : actions.keySet()) {
            intentFilter.addAction(str);
        }
        return intentFilter;
    }

    static boolean isAndroidKey(@NonNull String str) {
        return str.startsWith("android.");
    }

    @NonNull
    static String shortenActionNameIfNeeded(@NonNull String str) {
        return isAndroidKey(str) ? str.substring(str.lastIndexOf(".") + 1) : str;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(@NonNull Context context, @NonNull Intent intent) {
        try {
            HashMap hashMap = new HashMap();
            String action = intent.getAction();
            String shortenActionNameIfNeeded = shortenActionNameIfNeeded(intent.getAction());
            hashMap.put(INTENT_ACTION_KEY, action);
            Bundle extras = intent.getExtras();
            if (extras != null) {
                for (String str : extras.keySet()) {
                    String obj = extras.get(str).toString();
                    if (isAndroidKey(str)) {
                        hashMap.put("Extra", String.format("%s: %s", shortenActionNameIfNeeded, obj));
                    } else {
                        hashMap.put(str, obj);
                    }
                }
            }
            BreadcrumbType breadcrumbType = actions.containsKey(action) ? actions.get(action) : BreadcrumbType.LOG;
            if (!this.client.getConfig().isAutomaticallyCollectingBreadcrumbs()) {
                return;
            }
            this.client.leaveBreadcrumb(shortenActionNameIfNeeded, breadcrumbType, hashMap);
        } catch (Exception e) {
            Logger.warn(GeneratedOutlineSupport1.outline41(e, GeneratedOutlineSupport1.outline107("Failed to leave breadcrumb in EventReceiver: ")));
        }
    }
}
