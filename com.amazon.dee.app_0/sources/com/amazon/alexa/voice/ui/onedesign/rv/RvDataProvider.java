package com.amazon.alexa.voice.ui.onedesign.rv;

import java.util.HashMap;
import java.util.Map;
/* loaded from: classes11.dex */
public class RvDataProvider {
    private final Map<String, Object> dataByKey = new HashMap();

    public Object get(String str) {
        return this.dataByKey.get(str);
    }

    public void put(String str, Object obj) {
        this.dataByKey.put(str, obj);
    }

    public void remove(String str) {
        this.dataByKey.remove(str);
    }
}
