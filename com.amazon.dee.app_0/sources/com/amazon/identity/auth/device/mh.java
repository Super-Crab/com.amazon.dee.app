package com.amazon.identity.auth.device;

import java.util.Map;
import org.w3c.dom.Element;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class mh extends mj {
    private static final String TAG = "com.amazon.identity.auth.device.mh";
    private final Map<String, mb> tv;

    public mh(Map<String, mb> map) {
        this.tv = map;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazon.identity.auth.device.mj
    public void c(Element element) {
        Map<String, mb> map = this.tv;
        if (map == null || map.size() == 0) {
            return;
        }
        mi miVar = new mi("deviceTypeSoftwareVersionMap", new mj[0]);
        for (Map.Entry<String, mb> entry : this.tv.entrySet()) {
            mb value = entry.getValue();
            if (value != null && value.iw() != null && value.ix() != null && entry.getKey() != null) {
                miVar.a(new mi("entry", new mf("deviceType", entry.getKey()), new mf("version", entry.getValue().iw().toString()), new mf("softwareComponentId", entry.getValue().ix())));
            } else {
                io.b(TAG, "Skipping version for entry %s - version not present", entry.getKey());
            }
        }
        miVar.c(element);
    }
}
