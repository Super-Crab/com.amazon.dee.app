package com.facebook.react.bridge;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes2.dex */
public class JavaOnlyMap implements ReadableMap, WritableMap {
    private final Map mBackingMap;

    /* renamed from: com.facebook.react.bridge.JavaOnlyMap$2  reason: invalid class name */
    /* loaded from: classes2.dex */
    static /* synthetic */ class AnonymousClass2 {
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

    private JavaOnlyMap(Object... objArr) {
        if (objArr.length % 2 == 0) {
            this.mBackingMap = new HashMap();
            for (int i = 0; i < objArr.length; i += 2) {
                Object obj = objArr[i + 1];
                if (obj instanceof Number) {
                    obj = Double.valueOf(((Number) obj).doubleValue());
                }
                this.mBackingMap.put(objArr[i], obj);
            }
            return;
        }
        throw new IllegalArgumentException("You must provide the same number of keys and values");
    }

    public static JavaOnlyMap deepClone(ReadableMap readableMap) {
        JavaOnlyMap javaOnlyMap = new JavaOnlyMap();
        ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            int ordinal = readableMap.getType(nextKey).ordinal();
            if (ordinal == 0) {
                javaOnlyMap.putNull(nextKey);
            } else if (ordinal == 1) {
                javaOnlyMap.putBoolean(nextKey, readableMap.getBoolean(nextKey));
            } else if (ordinal == 2) {
                javaOnlyMap.putDouble(nextKey, readableMap.getDouble(nextKey));
            } else if (ordinal == 3) {
                javaOnlyMap.putString(nextKey, readableMap.getString(nextKey));
            } else if (ordinal == 4) {
                javaOnlyMap.putMap(nextKey, deepClone(readableMap.mo6945getMap(nextKey)));
            } else if (ordinal == 5) {
                javaOnlyMap.putArray(nextKey, JavaOnlyArray.deepClone(readableMap.getArray(nextKey)));
            }
        }
        return javaOnlyMap;
    }

    public static JavaOnlyMap of(Object... objArr) {
        return new JavaOnlyMap(objArr);
    }

    @Override // com.facebook.react.bridge.WritableMap
    public WritableMap copy() {
        JavaOnlyMap javaOnlyMap = new JavaOnlyMap();
        javaOnlyMap.merge(this);
        return javaOnlyMap;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || JavaOnlyMap.class != obj.getClass()) {
            return false;
        }
        Map map = this.mBackingMap;
        Map map2 = ((JavaOnlyMap) obj).mBackingMap;
        return map == null ? map2 == null : map.equals(map2);
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public ReadableArray getArray(@NonNull String str) {
        return (ReadableArray) this.mBackingMap.get(str);
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public boolean getBoolean(@NonNull String str) {
        return ((Boolean) this.mBackingMap.get(str)).booleanValue();
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public double getDouble(@NonNull String str) {
        return ((Number) this.mBackingMap.get(str)).doubleValue();
    }

    @Override // com.facebook.react.bridge.ReadableMap
    @NonNull
    public Dynamic getDynamic(@NonNull String str) {
        return DynamicFromMap.create(this, str);
    }

    @Override // com.facebook.react.bridge.ReadableMap
    @NonNull
    public Iterator<Map.Entry<String, Object>> getEntryIterator() {
        return this.mBackingMap.entrySet().iterator();
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public int getInt(@NonNull String str) {
        return ((Number) this.mBackingMap.get(str)).intValue();
    }

    @Override // com.facebook.react.bridge.ReadableMap
    /* renamed from: getMap */
    public ReadableMap mo6945getMap(@NonNull String str) {
        return (ReadableMap) this.mBackingMap.get(str);
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public String getString(@NonNull String str) {
        return (String) this.mBackingMap.get(str);
    }

    @Override // com.facebook.react.bridge.ReadableMap
    @NonNull
    public ReadableType getType(@NonNull String str) {
        Object obj = this.mBackingMap.get(str);
        if (obj == null) {
            return ReadableType.Null;
        }
        if (obj instanceof Number) {
            return ReadableType.Number;
        }
        if (obj instanceof String) {
            return ReadableType.String;
        }
        if (obj instanceof Boolean) {
            return ReadableType.Boolean;
        }
        if (obj instanceof ReadableMap) {
            return ReadableType.Map;
        }
        if (obj instanceof ReadableArray) {
            return ReadableType.Array;
        }
        if (obj instanceof Dynamic) {
            return ((Dynamic) obj).getType();
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid value ");
        outline107.append(obj.toString());
        outline107.append(" for key ");
        outline107.append(str);
        outline107.append("contained in JavaOnlyMap");
        throw new IllegalArgumentException(outline107.toString());
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public boolean hasKey(@NonNull String str) {
        return this.mBackingMap.containsKey(str);
    }

    public int hashCode() {
        Map map = this.mBackingMap;
        if (map != null) {
            return map.hashCode();
        }
        return 0;
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public boolean isNull(@NonNull String str) {
        return this.mBackingMap.get(str) == null;
    }

    @Override // com.facebook.react.bridge.ReadableMap
    @NonNull
    public ReadableMapKeySetIterator keySetIterator() {
        return new ReadableMapKeySetIterator() { // from class: com.facebook.react.bridge.JavaOnlyMap.1
            Iterator<Map.Entry<String, Object>> mIterator;

            {
                this.mIterator = JavaOnlyMap.this.mBackingMap.entrySet().iterator();
            }

            @Override // com.facebook.react.bridge.ReadableMapKeySetIterator
            public boolean hasNextKey() {
                return this.mIterator.hasNext();
            }

            @Override // com.facebook.react.bridge.ReadableMapKeySetIterator
            public String nextKey() {
                return this.mIterator.next().getKey();
            }
        };
    }

    @Override // com.facebook.react.bridge.WritableMap
    public void merge(@NonNull ReadableMap readableMap) {
        this.mBackingMap.putAll(((JavaOnlyMap) readableMap).mBackingMap);
    }

    @Override // com.facebook.react.bridge.WritableMap
    public void putArray(@NonNull String str, @Nullable ReadableArray readableArray) {
        this.mBackingMap.put(str, readableArray);
    }

    @Override // com.facebook.react.bridge.WritableMap
    public void putBoolean(@NonNull String str, boolean z) {
        this.mBackingMap.put(str, Boolean.valueOf(z));
    }

    @Override // com.facebook.react.bridge.WritableMap
    public void putDouble(@NonNull String str, double d) {
        this.mBackingMap.put(str, Double.valueOf(d));
    }

    @Override // com.facebook.react.bridge.WritableMap
    public void putInt(@NonNull String str, int i) {
        this.mBackingMap.put(str, new Double(i));
    }

    @Override // com.facebook.react.bridge.WritableMap
    public void putMap(@NonNull String str, @Nullable ReadableMap readableMap) {
        this.mBackingMap.put(str, readableMap);
    }

    @Override // com.facebook.react.bridge.WritableMap
    public void putNull(@NonNull String str) {
        this.mBackingMap.put(str, null);
    }

    @Override // com.facebook.react.bridge.WritableMap
    public void putString(@NonNull String str, @Nullable String str2) {
        this.mBackingMap.put(str, str2);
    }

    @Override // com.facebook.react.bridge.ReadableMap
    @NonNull
    public HashMap<String, Object> toHashMap() {
        return new HashMap<>(this.mBackingMap);
    }

    public String toString() {
        return this.mBackingMap.toString();
    }

    public JavaOnlyMap() {
        this.mBackingMap = new HashMap();
    }
}
