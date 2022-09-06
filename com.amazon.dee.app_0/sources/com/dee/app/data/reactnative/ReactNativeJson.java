package com.dee.app.data.reactnative;

import android.util.Log;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public final class ReactNativeJson {
    private static final String ARRAY = "array";
    private static final String MAP = "map";
    private static final String RESPONSE = "response";
    private static final String TYPE = "type";

    /* renamed from: com.dee.app.data.reactnative.ReactNativeJson$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$bridge$ReadableType = new int[ReadableType.values().length];

        static {
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Null.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Boolean.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Number.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.String.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Map.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Array.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    private ReactNativeJson() {
    }

    public static JSONArray convertArrayToJson(ReadableArray readableArray) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < readableArray.size(); i++) {
            int ordinal = readableArray.getType(i).ordinal();
            if (ordinal == 1) {
                jSONArray.put(readableArray.getBoolean(i));
            } else if (ordinal == 2) {
                jSONArray.put(readableArray.getDouble(i));
            } else if (ordinal == 3) {
                jSONArray.put(readableArray.getString(i));
            } else if (ordinal == 4) {
                jSONArray.put(convertMapToJson(readableArray.mo6944getMap(i)));
            } else if (ordinal == 5) {
                jSONArray.put(convertArrayToJson(readableArray.mo6943getArray(i)));
            }
        }
        return jSONArray;
    }

    public static WritableArray convertJsonToArray(Map<String, String> map) {
        try {
            return convertJsonToArray(new JSONArray(map.get("response")));
        } catch (JSONException unused) {
            Log.e("ElementsReact", "Error converting to JSONArray");
            return null;
        }
    }

    public static WritableMap convertJsonToMap(Map<String, String> map) {
        try {
            return convertJsonToMap(new JSONObject(map.get("response")));
        } catch (JSONException unused) {
            Log.e("ElementsReact", "Error converting to JSONObject");
            return null;
        }
    }

    public static JSONObject convertMapToJson(ReadableMap readableMap) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            int ordinal = readableMap.getType(nextKey).ordinal();
            if (ordinal == 0) {
                jSONObject.put(nextKey, JSONObject.NULL);
            } else if (ordinal == 1) {
                jSONObject.put(nextKey, readableMap.getBoolean(nextKey));
            } else if (ordinal == 2) {
                jSONObject.put(nextKey, readableMap.getDouble(nextKey));
            } else if (ordinal == 3) {
                jSONObject.put(nextKey, readableMap.getString(nextKey));
            } else if (ordinal == 4) {
                jSONObject.put(nextKey, convertMapToJson(readableMap.mo6945getMap(nextKey)));
            } else if (ordinal == 5) {
                jSONObject.put(nextKey, convertArrayToJson(readableMap.getArray(nextKey)));
            }
        }
        return jSONObject;
    }

    public static Map<String, String> getJsonData(String str) {
        HashMap hashMap = new HashMap();
        if (str.startsWith("[")) {
            hashMap.put("type", ARRAY);
        } else {
            hashMap.put("type", "map");
        }
        hashMap.put("response", str);
        return hashMap;
    }

    public static boolean isArray(Map<String, String> map) {
        return map.get("type").equals(ARRAY);
    }

    public static WritableArray convertJsonToArray(JSONArray jSONArray) throws JSONException {
        WritableNativeArray writableNativeArray = new WritableNativeArray();
        for (int i = 0; i < jSONArray.length(); i++) {
            Object obj = jSONArray.get(i);
            if (obj instanceof JSONObject) {
                writableNativeArray.pushMap(convertJsonToMap((JSONObject) obj));
            } else if (obj instanceof JSONArray) {
                writableNativeArray.pushArray(convertJsonToArray((JSONArray) obj));
            } else if (obj instanceof Boolean) {
                writableNativeArray.pushBoolean(((Boolean) obj).booleanValue());
            } else if (obj instanceof Integer) {
                writableNativeArray.pushInt(((Integer) obj).intValue());
            } else if (obj instanceof Double) {
                writableNativeArray.pushDouble(((Double) obj).doubleValue());
            } else if (obj instanceof String) {
                writableNativeArray.pushString((String) obj);
            } else {
                writableNativeArray.pushString(obj.toString());
            }
        }
        return writableNativeArray;
    }

    public static WritableMap convertJsonToMap(JSONObject jSONObject) throws JSONException {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            Object obj = jSONObject.get(str);
            if (obj instanceof JSONObject) {
                writableNativeMap.putMap(str, convertJsonToMap((JSONObject) obj));
            } else if (obj instanceof JSONArray) {
                writableNativeMap.putArray(str, convertJsonToArray((JSONArray) obj));
            } else if (obj instanceof Boolean) {
                writableNativeMap.putBoolean(str, ((Boolean) obj).booleanValue());
            } else if (obj instanceof Integer) {
                writableNativeMap.putInt(str, ((Integer) obj).intValue());
            } else if (obj instanceof Double) {
                writableNativeMap.putDouble(str, ((Double) obj).doubleValue());
            } else if (obj instanceof String) {
                writableNativeMap.putString(str, (String) obj);
            } else {
                writableNativeMap.putString(str, obj.toString());
            }
        }
        return writableNativeMap;
    }
}
