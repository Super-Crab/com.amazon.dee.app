package com.facebook.react.devsupport;

import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.devsupport.interfaces.StackFrame;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class StackTraceHelper {
    public static final String COLUMN_KEY = "column";
    public static final String LINE_NUMBER_KEY = "lineNumber";
    private static final Pattern STACK_FRAME_PATTERN1 = Pattern.compile("^(?:(.*?)@)?(.*?)\\:([0-9]+)\\:([0-9]+)$");
    private static final Pattern STACK_FRAME_PATTERN2 = Pattern.compile("\\s*(?:at)\\s*(.+?)\\s*[@(](.*):([0-9]+):([0-9]+)[)]$");

    public static StackFrame[] convertJavaStackTrace(Throwable th) {
        StackTraceElement[] stackTrace = th.getStackTrace();
        StackFrame[] stackFrameArr = new StackFrame[stackTrace.length];
        for (int i = 0; i < stackTrace.length; i++) {
            stackFrameArr[i] = new StackFrameImpl(stackTrace[i].getClassName(), stackTrace[i].getFileName(), stackTrace[i].getMethodName(), stackTrace[i].getLineNumber(), -1);
        }
        return stackFrameArr;
    }

    public static StackFrame[] convertJsStackTrace(@Nullable ReadableArray readableArray) {
        int size = readableArray != null ? readableArray.size() : 0;
        StackFrame[] stackFrameArr = new StackFrame[size];
        for (int i = 0; i < size; i++) {
            ReadableType type = readableArray.getType(i);
            if (type == ReadableType.Map) {
                ReadableMap mo6944getMap = readableArray.mo6944getMap(i);
                stackFrameArr[i] = new StackFrameImpl(mo6944getMap.getString("file"), mo6944getMap.getString("methodName"), (!mo6944getMap.hasKey(LINE_NUMBER_KEY) || mo6944getMap.isNull(LINE_NUMBER_KEY)) ? -1 : mo6944getMap.getInt(LINE_NUMBER_KEY), (!mo6944getMap.hasKey(COLUMN_KEY) || mo6944getMap.isNull(COLUMN_KEY)) ? -1 : mo6944getMap.getInt(COLUMN_KEY), mo6944getMap.hasKey("collapse") && !mo6944getMap.isNull("collapse") && mo6944getMap.getBoolean("collapse"));
            } else if (type == ReadableType.String) {
                stackFrameArr[i] = new StackFrameImpl((String) null, readableArray.getString(i), -1, -1);
            }
        }
        return stackFrameArr;
    }

    public static String formatFrameSource(StackFrame stackFrame) {
        StringBuilder sb = new StringBuilder();
        sb.append(stackFrame.getFileName());
        int line = stackFrame.getLine();
        if (line > 0) {
            sb.append(":");
            sb.append(line);
            int column = stackFrame.getColumn();
            if (column > 0) {
                sb.append(":");
                sb.append(column);
            }
        }
        return sb.toString();
    }

    public static String formatStackTrace(String str, StackFrame[] stackFrameArr) {
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str, "\n");
        for (StackFrame stackFrame : stackFrameArr) {
            outline113.append(stackFrame.getMethod());
            outline113.append("\n");
            outline113.append("    ");
            outline113.append(formatFrameSource(stackFrame));
            outline113.append("\n");
        }
        return outline113.toString();
    }

    /* loaded from: classes2.dex */
    public static class StackFrameImpl implements StackFrame {
        private final int mColumn;
        private final String mFile;
        private final String mFileName;
        private final boolean mIsCollapsed;
        private final int mLine;
        private final String mMethod;

        @Override // com.facebook.react.devsupport.interfaces.StackFrame
        public int getColumn() {
            return this.mColumn;
        }

        @Override // com.facebook.react.devsupport.interfaces.StackFrame
        public String getFile() {
            return this.mFile;
        }

        @Override // com.facebook.react.devsupport.interfaces.StackFrame
        public String getFileName() {
            return this.mFileName;
        }

        @Override // com.facebook.react.devsupport.interfaces.StackFrame
        public int getLine() {
            return this.mLine;
        }

        @Override // com.facebook.react.devsupport.interfaces.StackFrame
        public String getMethod() {
            return this.mMethod;
        }

        @Override // com.facebook.react.devsupport.interfaces.StackFrame
        public boolean isCollapsed() {
            return this.mIsCollapsed;
        }

        @Override // com.facebook.react.devsupport.interfaces.StackFrame
        public JSONObject toJSON() {
            return new JSONObject(MapBuilder.of("file", getFile(), "methodName", getMethod(), StackTraceHelper.LINE_NUMBER_KEY, Integer.valueOf(getLine()), StackTraceHelper.COLUMN_KEY, Integer.valueOf(getColumn()), "collapse", Boolean.valueOf(isCollapsed())));
        }

        private StackFrameImpl(String str, String str2, int i, int i2, boolean z) {
            this.mFile = str;
            this.mMethod = str2;
            this.mLine = i;
            this.mColumn = i2;
            this.mFileName = str != null ? new File(str).getName() : "";
            this.mIsCollapsed = z;
        }

        private StackFrameImpl(String str, String str2, int i, int i2) {
            this(str, str2, i, i2, false);
        }

        private StackFrameImpl(String str, String str2, String str3, int i, int i2) {
            this.mFile = str;
            this.mFileName = str2;
            this.mMethod = str3;
            this.mLine = i;
            this.mColumn = i2;
            this.mIsCollapsed = false;
        }
    }

    public static StackFrame[] convertJsStackTrace(JSONArray jSONArray) {
        int length = jSONArray != null ? jSONArray.length() : 0;
        StackFrame[] stackFrameArr = new StackFrame[length];
        for (int i = 0; i < length; i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                stackFrameArr[i] = new StackFrameImpl(jSONObject.getString("file"), jSONObject.getString("methodName"), (!jSONObject.has(LINE_NUMBER_KEY) || jSONObject.isNull(LINE_NUMBER_KEY)) ? -1 : jSONObject.getInt(LINE_NUMBER_KEY), (!jSONObject.has(COLUMN_KEY) || jSONObject.isNull(COLUMN_KEY)) ? -1 : jSONObject.getInt(COLUMN_KEY), jSONObject.has("collapse") && !jSONObject.isNull("collapse") && jSONObject.getBoolean("collapse"));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        return stackFrameArr;
    }

    public static StackFrame[] convertJsStackTrace(String str) {
        String[] split = str.split("\n");
        StackFrame[] stackFrameArr = new StackFrame[split.length];
        for (int i = 0; i < split.length; i++) {
            Matcher matcher = STACK_FRAME_PATTERN1.matcher(split[i]);
            Matcher matcher2 = STACK_FRAME_PATTERN2.matcher(split[i]);
            if (matcher2.find()) {
                matcher = matcher2;
            } else if (!matcher.find()) {
                stackFrameArr[i] = new StackFrameImpl((String) null, split[i], -1, -1);
            }
            stackFrameArr[i] = new StackFrameImpl(matcher.group(2), matcher.group(1) == null ? "(unknown)" : matcher.group(1), Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)));
        }
        return stackFrameArr;
    }
}
