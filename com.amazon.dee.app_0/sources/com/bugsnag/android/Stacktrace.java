package com.bugsnag.android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bugsnag.android.JsonStream;
import com.dee.app.metrics.MetricsConstants;
import com.facebook.react.devsupport.StackTraceHelper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
class Stacktrace implements JsonStream.Streamable {
    private static final int STACKTRACE_TRIM_LENGTH = 200;
    private final List<Map<String, Object>> trace;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Stacktrace(StackTraceElement[] stackTraceElementArr, String[] strArr) {
        this.trace = serializeStacktrace(stackTraceElementArr, sanitiseProjectPackages(strArr));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean inProject(String str, String[] strArr) {
        return inProject(str, sanitiseProjectPackages(strArr));
    }

    private static List<String> sanitiseProjectPackages(String[] strArr) {
        if (strArr != null) {
            return Arrays.asList(strArr);
        }
        return Collections.emptyList();
    }

    @Nullable
    private Map<String, Object> serializeStackframe(StackTraceElement stackTraceElement, List<String> list) {
        String methodName;
        HashMap hashMap = new HashMap();
        try {
            if (stackTraceElement.getClassName().length() > 0) {
                methodName = stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName();
            } else {
                methodName = stackTraceElement.getMethodName();
            }
            hashMap.put(MetricsConstants.NativeFetch.METHOD, methodName);
            hashMap.put("file", stackTraceElement.getFileName() == null ? "Unknown" : stackTraceElement.getFileName());
            hashMap.put(StackTraceHelper.LINE_NUMBER_KEY, Integer.valueOf(stackTraceElement.getLineNumber()));
            if (inProject(stackTraceElement.getClassName(), list)) {
                hashMap.put("inProject", true);
            }
            return hashMap;
        } catch (Exception e) {
            Logger.warn("Failed to serialize stacktrace", e);
            return null;
        }
    }

    private List<Map<String, Object>> serializeStacktrace(StackTraceElement[] stackTraceElementArr, List<String> list) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < stackTraceElementArr.length && i < 200; i++) {
            Map<String, Object> serializeStackframe = serializeStackframe(stackTraceElementArr[i], list);
            if (serializeStackframe != null) {
                arrayList.add(serializeStackframe);
            }
        }
        return arrayList;
    }

    @Override // com.bugsnag.android.JsonStream.Streamable
    public void toStream(@NonNull JsonStream jsonStream) throws IOException {
        jsonStream.beginArray();
        for (Map<String, Object> map : this.trace) {
            jsonStream.value(map);
        }
        jsonStream.endArray();
    }

    private static boolean inProject(String str, List<String> list) {
        for (String str2 : list) {
            if (str2 != null && str.startsWith(str2)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Stacktrace(List<Map<String, Object>> list) {
        if (list.size() >= 200) {
            this.trace = list.subList(0, 200);
        } else {
            this.trace = list;
        }
    }
}
