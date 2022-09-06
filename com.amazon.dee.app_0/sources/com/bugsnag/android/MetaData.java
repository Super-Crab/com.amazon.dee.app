package com.bugsnag.android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bugsnag.android.JsonStream;
import com.bugsnag.android.NativeInterface;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes.dex */
public class MetaData extends Observable implements JsonStream.Streamable {
    final ObjectJsonStreamer jsonStreamer;
    @NonNull
    final Map<String, Object> store;

    public MetaData() {
        this(new ConcurrentHashMap());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public static MetaData merge(@NonNull MetaData... metaDataArr) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (MetaData metaData : metaDataArr) {
            if (metaData != null) {
                arrayList.add(metaData.store);
                String[] strArr = metaData.jsonStreamer.filters;
                if (strArr != null) {
                    arrayList2.addAll(Arrays.asList(strArr));
                }
            }
        }
        MetaData metaData2 = new MetaData(mergeMaps((Map[]) arrayList.toArray(new Map[0])));
        metaData2.setFilters((String[]) arrayList2.toArray(new String[0]));
        return metaData2;
    }

    @NonNull
    @SafeVarargs
    private static Map<String, Object> mergeMaps(@NonNull Map<String, Object>... mapArr) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        for (Map<String, Object> map : mapArr) {
            if (map != null) {
                HashSet<String> hashSet = new HashSet(concurrentHashMap.keySet());
                hashSet.addAll(map.keySet());
                for (String str : hashSet) {
                    Object obj = concurrentHashMap.get(str);
                    Object obj2 = map.get(str);
                    if (obj2 != null) {
                        if ((obj instanceof Map) && (obj2 instanceof Map)) {
                            concurrentHashMap.put(str, mergeMaps((Map) obj, (Map) obj2));
                        } else {
                            concurrentHashMap.put(str, obj2);
                        }
                    } else if (obj != null) {
                        concurrentHashMap.put(str, obj);
                    }
                }
            }
        }
        return concurrentHashMap;
    }

    public void addToTab(@NonNull String str, @NonNull String str2, @Nullable Object obj) {
        Map<String, Object> tab = getTab(str);
        setChanged();
        if (obj != null) {
            tab.put(str2, obj);
            notifyObservers(new NativeInterface.Message(NativeInterface.MessageType.ADD_METADATA, Arrays.asList(str, str2, obj)));
            return;
        }
        tab.remove(str2);
        notifyObservers(new NativeInterface.Message(NativeInterface.MessageType.REMOVE_METADATA, Arrays.asList(str, str2)));
    }

    public void clearTab(@NonNull String str) {
        this.store.remove(str);
        setChanged();
        notifyObservers(new NativeInterface.Message(NativeInterface.MessageType.CLEAR_METADATA_TAB, str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String[] getFilters() {
        return this.jsonStreamer.filters;
    }

    @NonNull
    Map<String, Object> getTab(String str) {
        Map<String, Object> map = (Map) this.store.get(str);
        if (map == null) {
            ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
            this.store.put(str, concurrentHashMap);
            return concurrentHashMap;
        }
        return map;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setFilters(String... strArr) {
        this.jsonStreamer.filters = strArr;
    }

    @Override // com.bugsnag.android.JsonStream.Streamable
    public void toStream(@NonNull JsonStream jsonStream) throws IOException {
        this.jsonStreamer.objectToStream(this.store, jsonStream);
    }

    public MetaData(@NonNull Map<String, Object> map) {
        this.store = new ConcurrentHashMap(map);
        this.jsonStreamer = new ObjectJsonStreamer();
    }
}
