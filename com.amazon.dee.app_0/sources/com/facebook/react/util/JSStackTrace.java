package com.facebook.react.util;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes2.dex */
public class JSStackTrace {
    private static final String COLUMN_KEY = "column";
    private static final Pattern FILE_ID_PATTERN = Pattern.compile("\\b((?:seg-\\d+(?:_\\d+)?|\\d+)\\.js)");
    private static final String FILE_KEY = "file";
    private static final String LINE_NUMBER_KEY = "lineNumber";
    private static final String METHOD_NAME_KEY = "methodName";

    public static String format(String str, ReadableArray readableArray) {
        StringBuilder outline112 = GeneratedOutlineSupport1.outline112(str, ", stack:\n");
        for (int i = 0; i < readableArray.size(); i++) {
            ReadableMap mo6944getMap = readableArray.mo6944getMap(i);
            outline112.append(mo6944getMap.getString(METHOD_NAME_KEY));
            outline112.append("@");
            outline112.append(parseFileId(mo6944getMap));
            if (mo6944getMap.hasKey("lineNumber") && !mo6944getMap.isNull("lineNumber") && mo6944getMap.getType("lineNumber") == ReadableType.Number) {
                outline112.append(mo6944getMap.getInt("lineNumber"));
            } else {
                outline112.append(-1);
            }
            if (mo6944getMap.hasKey("column") && !mo6944getMap.isNull("column") && mo6944getMap.getType("column") == ReadableType.Number) {
                outline112.append(":");
                outline112.append(mo6944getMap.getInt("column"));
            }
            outline112.append("\n");
        }
        return outline112.toString();
    }

    private static String parseFileId(ReadableMap readableMap) {
        String string;
        if (!readableMap.hasKey("file") || readableMap.isNull("file") || readableMap.getType("file") != ReadableType.String || (string = readableMap.getString("file")) == null) {
            return "";
        }
        Matcher matcher = FILE_ID_PATTERN.matcher(string);
        if (!matcher.find()) {
            return "";
        }
        return matcher.group(1) + ":";
    }
}
