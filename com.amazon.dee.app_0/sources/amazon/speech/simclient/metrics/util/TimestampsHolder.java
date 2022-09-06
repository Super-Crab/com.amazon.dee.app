package amazon.speech.simclient.metrics.util;

import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class TimestampsHolder {
    private Map<Object, Long> mMap = new HashMap();

    public Long get(Object obj) {
        if (!hasKey(obj)) {
            return -1L;
        }
        return this.mMap.get(obj);
    }

    public boolean hasKey(Object obj) {
        return this.mMap.containsKey(obj);
    }

    public boolean setTime(Object obj) {
        return setTime(obj, System.currentTimeMillis());
    }

    public boolean setTime(Object obj, long j) {
        if (hasKey(obj)) {
            return false;
        }
        this.mMap.put(obj, Long.valueOf(j));
        return true;
    }
}
