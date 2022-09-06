package com.amazon.dee.app.strictmode;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.StrictMode;
import android.os.strictmode.Violation;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.tasks.api.TaskManager;
import com.amazon.dee.app.services.logging.Log;
import dagger.Lazy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes12.dex */
public class AlexaStrictMode {
    private static final String TAG = Log.tag(AlexaStrictMode.class);
    private AlexaStrictModeData alexaStrictModeData;
    @SuppressLint({"UseSparseArrays"})
    private Map<Integer, List<StackTraceElement[]>> existingViolationsHashMap;
    private Integer nonAmazonViolationHashValue = -1;

    public AlexaStrictMode(Lazy<TaskManager> lazy) {
        this.existingViolationsHashMap = new HashMap();
        this.alexaStrictModeData = null;
        initializeStrictMode(lazy);
        this.alexaStrictModeData = new AlexaStrictModeData();
        this.existingViolationsHashMap = this.alexaStrictModeData.getExistingViolations();
    }

    public void initializeStrictMode(Lazy<TaskManager> lazy) {
        StrictMode.ThreadPolicy.Builder detectAll = new StrictMode.ThreadPolicy.Builder().detectAll();
        StrictMode.VmPolicy.Builder detectAll2 = new StrictMode.VmPolicy.Builder().detectAll();
        if (Build.VERSION.SDK_INT > 28) {
            StrictMode.OnThreadViolationListener onThreadViolationListener = new StrictMode.OnThreadViolationListener() { // from class: com.amazon.dee.app.strictmode.-$$Lambda$AlexaStrictMode$xIT_UvpKdmzOWg5-3uG-gVErQKE
                @Override // android.os.StrictMode.OnThreadViolationListener
                public final void onThreadViolation(Violation violation) {
                    AlexaStrictMode.this.lambda$initializeStrictMode$0$AlexaStrictMode(violation);
                }
            };
            StrictMode.OnVmViolationListener onVmViolationListener = new StrictMode.OnVmViolationListener() { // from class: com.amazon.dee.app.strictmode.-$$Lambda$AlexaStrictMode$cnfPR-W_RSZMVwxYKt9yVYUIz3k
                @Override // android.os.StrictMode.OnVmViolationListener
                public final void onVmViolation(Violation violation) {
                    AlexaStrictMode.this.lambda$initializeStrictMode$1$AlexaStrictMode(violation);
                }
            };
            detectAll.penaltyListener(lazy.mo358get().getExecutor(1), onThreadViolationListener);
            detectAll2.penaltyListener(lazy.mo358get().getExecutor(1), onVmViolationListener);
        } else {
            detectAll.penaltyLog();
            detectAll2.penaltyLog();
        }
        StrictMode.setThreadPolicy(detectAll.build());
        StrictMode.setVmPolicy(detectAll2.build());
    }

    @VisibleForTesting
    boolean isNewViolation(Integer num) {
        return !num.equals(this.nonAmazonViolationHashValue) && !this.existingViolationsHashMap.containsKey(num);
    }

    /* renamed from: violationProcessor */
    public void lambda$initializeStrictMode$1$AlexaStrictMode(Violation violation) {
        StackTrace stackTrace = new StackTrace(violation.getStackTrace());
        Integer valueOf = Integer.valueOf(stackTrace.hashCode());
        if (valueOf.equals(this.nonAmazonViolationHashValue) || !isNewViolation(valueOf)) {
            return;
        }
        StringBuffer stringBuffer = new StringBuffer(violationStackTraceBuilder(violation.getStackTrace()));
        String str = TAG;
        Log.e(str, "violationProcessor: " + ((Object) stringBuffer) + "\n\t" + valueOf);
        stackTrace.toString();
    }

    @VisibleForTesting
    StringBuffer violationStackTraceBuilder(StackTraceElement[] stackTraceElementArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (StackTraceElement stackTraceElement : stackTraceElementArr) {
            stringBuffer.append(stackTraceElement + "\n\t");
        }
        return stringBuffer;
    }
}
