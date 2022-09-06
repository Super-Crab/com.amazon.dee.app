package com.amazonaws.cognito.clientcontext.datacollection;

import android.content.Context;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes13.dex */
public class ContextDataAggregator {
    private final List<DataCollector> dataCollectors;

    /* loaded from: classes13.dex */
    private static class InstanceHolder {
        private static final ContextDataAggregator INSTANCE = new ContextDataAggregator();

        private InstanceHolder() {
        }
    }

    private List<DataCollector> getDataCollectors() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ApplicationDataCollector());
        arrayList.add(new BuildDataCollector());
        arrayList.add(new DeviceDataCollector());
        arrayList.add(new TelephonyDataCollector());
        return arrayList;
    }

    public static ContextDataAggregator getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private void removerNullEntries(Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getValue() == null) {
                map.remove(entry.getKey());
            }
        }
    }

    public Map<String, String> getAggregatedData(Context context) {
        HashMap hashMap = new HashMap();
        for (DataCollector dataCollector : this.dataCollectors) {
            hashMap.putAll(dataCollector.collect(context));
        }
        removerNullEntries(hashMap);
        return hashMap;
    }

    private ContextDataAggregator() {
        this.dataCollectors = getDataCollectors();
    }

    protected ContextDataAggregator(List<DataCollector> list) {
        this.dataCollectors = list;
    }
}
