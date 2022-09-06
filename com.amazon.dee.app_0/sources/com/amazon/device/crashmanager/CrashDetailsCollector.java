package com.amazon.device.crashmanager;

import android.content.Context;
import android.util.Log;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes12.dex */
class CrashDetailsCollector implements CrashDetailsCollectable {
    private static final String TAG = "com.amazon.device.crashmanager.CrashDetailsCollector";
    private final Date appStartTime = new Date();
    private final Context mContext;

    public CrashDetailsCollector(Context context) {
        if (context != null) {
            this.mContext = context;
            return;
        }
        throw new IllegalArgumentException("Context must not be null.");
    }

    private void collectAppStartTime(Map<String, String> map) {
        try {
            map.put("appLaunchTime", this.appStartTime.toString());
            map.put("appLaunchTimeUTC", Long.valueOf(this.appStartTime.getTime()).toString());
        } catch (Exception e) {
            Log.e(TAG, "Error while capturing app start time details", e);
        }
    }

    private void collectCrashTime(Map<String, String> map) {
        try {
            Date date = new Date();
            map.put("crashTime", date.toString());
            map.put("CrashTimeUTC", Long.valueOf(date.getTime()).toString());
            map.put("msToCrash", Long.valueOf(date.getTime() - this.appStartTime.getTime()).toString());
        } catch (Exception e) {
            Log.e(TAG, "Error while capturing time details", e);
        }
    }

    private void collectDeviceDetails(Map<String, String> map) {
        try {
            DeviceDetailsCollector.collect(this.mContext, map);
        } catch (Exception e) {
            Log.e(TAG, "Error while capturing device details", e);
        }
    }

    private void collectPackageDetails(Map<String, String> map) {
        try {
            AppDetailsCollector.collect(this.mContext, map);
        } catch (Exception e) {
            Log.e(TAG, "Error while capturing package details", e);
        }
    }

    private void collectStackTrace(Throwable th, Map<String, String> map) {
        try {
            StringBuilder sb = new StringBuilder();
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            th.printStackTrace(printWriter);
            sb.append(stringWriter.toString());
            map.put("stackTrace", sb.toString());
            Throwable cause = th.getCause();
            while (cause != null) {
                printWriter.close();
                stringWriter.close();
                stringWriter = new StringWriter();
                printWriter = new PrintWriter(stringWriter);
                sb.append("\nFull stack trace of next cause...\n");
                cause.printStackTrace(printWriter);
                cause = cause.getCause();
                sb.append(stringWriter.toString());
            }
            printWriter.close();
            stringWriter.close();
            map.put("stackTraceExtended", sb.toString());
        } catch (Exception e) {
            Log.e(TAG, "Error while capturing stack trace", e);
        }
    }

    private void collectThreadDump(Map<String, String> map) {
        try {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<Thread, StackTraceElement[]> entry : Thread.getAllStackTraces().entrySet()) {
                Thread key = entry.getKey();
                StackTraceElement[] value = entry.getValue();
                sb.append("\n\tThread : ");
                sb.append(key.getId());
                if (key.getName() != null && !key.getName().isEmpty()) {
                    sb.append("/");
                    sb.append(key.getName());
                }
                sb.append("\n");
                sb.append("\tisAlive : ");
                sb.append(key.isAlive());
                sb.append("\n");
                sb.append("\tisInterrupted : ");
                sb.append(key.isInterrupted());
                sb.append("\n");
                sb.append("\tisDaemon : ");
                sb.append(key.isDaemon());
                sb.append("\n");
                for (StackTraceElement stackTraceElement : value) {
                    sb.append("\t\tat ");
                    sb.append(stackTraceElement);
                    sb.append("\n");
                }
            }
            map.put("threadDump", sb.toString());
        } catch (Exception e) {
            Log.e(TAG, "Error while capturing thread dump", e);
        }
    }

    @Override // com.amazon.device.crashmanager.CrashDetailsCollectable
    public Map<String, String> collect(Throwable th) {
        HashMap hashMap = new HashMap();
        collectAppStartTime(hashMap);
        collectCrashTime(hashMap);
        collectPackageDetails(hashMap);
        collectDeviceDetails(hashMap);
        collectStackTrace(th, hashMap);
        collectThreadDump(hashMap);
        return hashMap;
    }
}
