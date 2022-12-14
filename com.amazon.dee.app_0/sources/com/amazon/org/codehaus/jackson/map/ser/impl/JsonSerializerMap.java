package com.amazon.org.codehaus.jackson.map.ser.impl;

import com.amazon.org.codehaus.jackson.map.JsonSerializer;
import com.amazon.org.codehaus.jackson.map.ser.impl.SerializerCache;
import java.util.Map;
/* loaded from: classes13.dex */
public class JsonSerializerMap {
    private final Bucket[] _buckets;
    private final int _size;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static final class Bucket {
        public final SerializerCache.TypeKey key;
        public final Bucket next;
        public final JsonSerializer<Object> value;

        public Bucket(Bucket bucket, SerializerCache.TypeKey typeKey, JsonSerializer<Object> jsonSerializer) {
            this.next = bucket;
            this.key = typeKey;
            this.value = jsonSerializer;
        }
    }

    public JsonSerializerMap(Map<SerializerCache.TypeKey, JsonSerializer<Object>> map) {
        int findSize = findSize(map.size());
        this._size = findSize;
        int i = findSize - 1;
        Bucket[] bucketArr = new Bucket[findSize];
        for (Map.Entry<SerializerCache.TypeKey, JsonSerializer<Object>> entry : map.entrySet()) {
            SerializerCache.TypeKey key = entry.getKey();
            int hashCode = key.hashCode() & i;
            bucketArr[hashCode] = new Bucket(bucketArr[hashCode], key, entry.getValue());
        }
        this._buckets = bucketArr;
    }

    private static final int findSize(int i) {
        int i2 = 8;
        while (i2 < (i <= 64 ? i + i : i + (i >> 2))) {
            i2 += i2;
        }
        return i2;
    }

    public JsonSerializer<Object> find(SerializerCache.TypeKey typeKey) {
        int hashCode = typeKey.hashCode();
        Bucket[] bucketArr = this._buckets;
        Bucket bucket = bucketArr[hashCode & (bucketArr.length - 1)];
        if (bucket == null) {
            return null;
        }
        if (typeKey.equals(bucket.key)) {
            return bucket.value;
        }
        do {
            bucket = bucket.next;
            if (bucket == null) {
                return null;
            }
        } while (!typeKey.equals(bucket.key));
        return bucket.value;
    }

    public int size() {
        return this._size;
    }
}
