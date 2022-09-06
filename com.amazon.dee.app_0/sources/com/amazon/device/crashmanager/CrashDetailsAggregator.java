package com.amazon.device.crashmanager;

import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes12.dex */
public class CrashDetailsAggregator implements CrashDetailsCollectable {
    private static final String TAG = "com.amazon.device.crashmanager.CrashDetailsAggregator";
    private List<CrashDetailsCollectable> collectors = new ArrayList();

    public void appendCollector(CrashDetailsCollectable crashDetailsCollectable) {
        this.collectors.add(crashDetailsCollectable);
    }

    @Override // com.amazon.device.crashmanager.CrashDetailsCollectable
    public Map<String, String> collect(Throwable th) {
        HashMap hashMap = new HashMap();
        for (CrashDetailsCollectable crashDetailsCollectable : this.collectors) {
            try {
                hashMap.putAll(crashDetailsCollectable.collect(th));
            } catch (Exception e) {
                String str = TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("crash data collector: '");
                outline107.append(crashDetailsCollectable.toString());
                outline107.append("' threw exception: ");
                Log.e(str, outline107.toString(), e);
            }
        }
        return hashMap;
    }
}
