package com.amazon.communication;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import com.amazon.dp.logger.DPLogger;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.util.List;
/* loaded from: classes12.dex */
public class StackDumper {
    private static final DPLogger log = new DPLogger("TComm.StackDumper");
    private final Context mContext;

    public StackDumper(Context context) {
        this.mContext = context;
    }

    public void dumpStack(String str) {
        if (str != null) {
            try {
                int pid = getPid(str);
                log.info("dumpStack", "process found", "processName", str, "pid", Integer.valueOf(pid));
                Process.sendSignal(pid, 3);
                log.info("dumpStack", "signal sent", "processName", str, "pid", Integer.valueOf(pid));
                return;
            } catch (IOException e) {
                log.warn("dumpStack", "stack dump failed", "processName", str, "error", e.getMessage());
                return;
            }
        }
        throw new IllegalArgumentException("processName must not be null");
    }

    protected int getPid(String str) throws IOException {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) this.mContext.getSystemService(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME)).getRunningAppProcesses();
        if (runningAppProcesses != null) {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (str.equals(runningAppProcessInfo.processName)) {
                    return runningAppProcessInfo.pid;
                }
            }
            throw new IOException(GeneratedOutlineSupport1.outline72("No process found with name ", str));
        }
        throw new IOException("Failed to get list of running app processes");
    }
}
