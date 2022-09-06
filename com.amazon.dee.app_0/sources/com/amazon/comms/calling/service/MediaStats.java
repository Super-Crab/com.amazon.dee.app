package com.amazon.comms.calling.service;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public class MediaStats {
    private HashMap<String, String> legacyMetrics;
    private final List<Map<String, String>> mediaStats;
    private final Map<String, String> newMediaStats;

    public MediaStats(List<HashMap<String, String>> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (HashMap<String, String> hashMap : list) {
            arrayList.add(ImmutableMap.copyOf((Map) hashMap));
        }
        this.mediaStats = ImmutableList.copyOf((Collection) arrayList);
        this.newMediaStats = new HashMap();
    }

    public HashMap<String, String> getLegacyMetrics() {
        return this.legacyMetrics;
    }

    public List<Map<String, String>> getMediaStats() {
        return this.mediaStats;
    }

    public Map<String, String> getNewMediaStats() {
        return this.newMediaStats;
    }

    public void setLegacyMetrics(HashMap<String, String> hashMap) {
        this.legacyMetrics = hashMap;
    }

    public MediaStats(List<HashMap<String, String>> list, HashMap<String, String> hashMap) {
        ArrayList arrayList = new ArrayList(list.size());
        for (HashMap<String, String> hashMap2 : list) {
            arrayList.add(ImmutableMap.copyOf((Map) hashMap2));
        }
        this.mediaStats = ImmutableList.copyOf((Collection) arrayList);
        HashMap hashMap3 = new HashMap();
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            hashMap3.put(entry.getKey(), entry.getValue());
        }
        this.newMediaStats = ImmutableMap.copyOf((Map) hashMap3);
    }
}
