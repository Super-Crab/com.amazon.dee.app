package com.bugsnag.android;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
/* loaded from: classes.dex */
class StrictModeHandler {
    private static final int DETECT_CUSTOM = 8;
    private static final int DETECT_DISK_READ = 2;
    private static final int DETECT_DISK_WRITE = 1;
    private static final int DETECT_NETWORK = 4;
    private static final int DETECT_RESOURCE_MISMATCH = 16;
    private static final int DETECT_VM_ACTIVITY_LEAKS = 1024;
    private static final int DETECT_VM_CLEARTEXT_NETWORK = 16384;
    private static final int DETECT_VM_CLOSABLE_LEAKS = 512;
    private static final int DETECT_VM_CURSOR_LEAKS = 256;
    private static final int DETECT_VM_FILE_URI_EXPOSURE = 8192;
    private static final int DETECT_VM_INSTANCE_LEAKS = 2048;
    private static final int DETECT_VM_REGISTRATION_LEAKS = 4096;
    @SuppressLint({"UseSparseArrays"})
    private static final Map<Integer, String> POLICY_CODE_MAP = new HashMap();
    private static final String STRICT_MODE_CLZ_NAME = "android.os.strictmode";

    static {
        POLICY_CODE_MAP.put(1, "DiskWrite");
        POLICY_CODE_MAP.put(2, "DiskRead");
        POLICY_CODE_MAP.put(4, "NetworkOperation");
        POLICY_CODE_MAP.put(8, "CustomSlowCall");
        POLICY_CODE_MAP.put(16, "ResourceMismatch");
        POLICY_CODE_MAP.put(256, "CursorLeak");
        POLICY_CODE_MAP.put(512, "CloseableLeak");
        POLICY_CODE_MAP.put(1024, "ActivityLeak");
        POLICY_CODE_MAP.put(2048, "InstanceLeak");
        POLICY_CODE_MAP.put(4096, "RegistrationLeak");
        POLICY_CODE_MAP.put(8192, "FileUriLeak");
        POLICY_CODE_MAP.put(16384, "CleartextNetwork");
    }

    private Throwable getRootCause(Throwable th) {
        Throwable cause = th.getCause();
        return cause == null ? th : getRootCause(cause);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public String getViolationDescription(String str) {
        if (!TextUtils.isEmpty(str)) {
            int lastIndexOf = str.lastIndexOf("violation=");
            if (lastIndexOf == -1) {
                return null;
            }
            String replace = str.substring(lastIndexOf).replace("violation=", "");
            if (!TextUtils.isDigitsOnly(replace)) {
                return null;
            }
            return POLICY_CODE_MAP.get(Integer.valueOf(replace));
        }
        throw new IllegalArgumentException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isStrictModeThrowable(Throwable th) {
        return getRootCause(th).getClass().getName().toLowerCase(Locale.US).startsWith(STRICT_MODE_CLZ_NAME);
    }
}
