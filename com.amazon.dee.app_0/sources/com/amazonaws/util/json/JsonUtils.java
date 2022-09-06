package com.amazonaws.util.json;

import com.amazonaws.AmazonClientException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class JsonUtils {
    private static volatile AwsJsonFactory factory = new GsonFactory();

    /* renamed from: com.amazonaws.util.json.JsonUtils$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazonaws$util$json$JsonUtils$JsonEngine = new int[JsonEngine.values().length];

        static {
            try {
                $SwitchMap$com$amazonaws$util$json$JsonUtils$JsonEngine[JsonEngine.Gson.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazonaws$util$json$JsonUtils$JsonEngine[JsonEngine.Jackson.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* loaded from: classes.dex */
    public enum JsonEngine {
        Gson,
        Jackson
    }

    public static AwsJsonReader getJsonReader(Reader reader) {
        if (factory != null) {
            return factory.getJsonReader(reader);
        }
        throw new IllegalStateException("Json engine is unavailable.");
    }

    public static AwsJsonWriter getJsonWriter(Writer writer) {
        if (factory != null) {
            return factory.getJsonWriter(writer);
        }
        throw new IllegalStateException("Json engine is unavailable.");
    }

    private static boolean isClassAvailable(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static Map<String, String> jsonToMap(Reader reader) {
        AwsJsonReader jsonReader = getJsonReader(reader);
        try {
            if (jsonReader.peek() == null) {
                return Collections.EMPTY_MAP;
            }
            HashMap hashMap = new HashMap();
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.isContainer()) {
                    jsonReader.skipValue();
                } else {
                    hashMap.put(nextName, jsonReader.nextString());
                }
            }
            jsonReader.endObject();
            jsonReader.close();
            return Collections.unmodifiableMap(hashMap);
        } catch (IOException e) {
            throw new AmazonClientException("Unable to parse JSON String.", e);
        }
    }

    public static String mapToString(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return "{}";
        }
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                jsonWriter.name(entry.getKey()).value(entry.getValue());
            }
            jsonWriter.endObject();
            jsonWriter.close();
            return stringWriter.toString();
        } catch (IOException e) {
            throw new AmazonClientException("Unable to serialize to JSON String.", e);
        }
    }

    public static void setJsonEngine(JsonEngine jsonEngine) {
        int ordinal = jsonEngine.ordinal();
        if (ordinal == 0) {
            factory = new GsonFactory();
        } else if (ordinal == 1) {
            factory = new JacksonFactory();
        } else {
            throw new RuntimeException("Unsupported json engine");
        }
    }

    static void setJsonEngine(AwsJsonFactory awsJsonFactory) {
        if (awsJsonFactory != null) {
            factory = awsJsonFactory;
            return;
        }
        throw new IllegalArgumentException("factory can't be null");
    }

    public static Map<String, String> jsonToMap(String str) {
        if (str != null && !str.isEmpty()) {
            return jsonToMap(new StringReader(str));
        }
        return Collections.EMPTY_MAP;
    }
}
