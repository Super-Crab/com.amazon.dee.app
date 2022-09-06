package com.amazon.alexa.accessory.internal.session;

import com.amazon.alexa.accessory.capabilities.Capability;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public class AccessorySessionCapabilityResolver {
    private static final Map<Capability, List<Capability>> CAPABILITY_DEPENDENCY_GRAPH = new HashMap();
    private static final Set<Capability> NON_DEFAULT_CAPABILITIES = new HashSet();

    static {
        CAPABILITY_DEPENDENCY_GRAPH.put(Capability.SPEECH_RECOGNITION, Arrays.asList(Capability.DEVICE, Capability.FIRMWARE, Capability.SYSTEM));
        CAPABILITY_DEPENDENCY_GRAPH.put(Capability.BULK_DATA, Arrays.asList(Capability.DEVICE));
        CAPABILITY_DEPENDENCY_GRAPH.put(Capability.TRANSPORT, Arrays.asList(Capability.DEVICE));
        CAPABILITY_DEPENDENCY_GRAPH.put(Capability.FIRMWARE, Arrays.asList(Capability.DEVICE));
        CAPABILITY_DEPENDENCY_GRAPH.put(Capability.DEVICE, Collections.emptyList());
        CAPABILITY_DEPENDENCY_GRAPH.put(Capability.KEY_EXCHANGE, Collections.emptyList());
        CAPABILITY_DEPENDENCY_GRAPH.put(Capability.STATE, Collections.emptyList());
        CAPABILITY_DEPENDENCY_GRAPH.put(Capability.DIAGNOSTIC, Collections.emptyList());
        CAPABILITY_DEPENDENCY_GRAPH.put(Capability.CALLING, Collections.emptyList());
        CAPABILITY_DEPENDENCY_GRAPH.put(Capability.CBL, Collections.emptyList());
        CAPABILITY_DEPENDENCY_GRAPH.put(Capability.SYSTEM, Collections.emptyList());
        CAPABILITY_DEPENDENCY_GRAPH.put(Capability.CENTRAL, Collections.emptyList());
        CAPABILITY_DEPENDENCY_GRAPH.put(Capability.METRICS, Collections.emptyList());
        CAPABILITY_DEPENDENCY_GRAPH.put(Capability.FITNESS, Collections.emptyList());
        CAPABILITY_DEPENDENCY_GRAPH.put(Capability.INSTRUMENTATION, Collections.emptyList());
        CAPABILITY_DEPENDENCY_GRAPH.put(Capability.INPUT_EVENTS, Collections.emptyList());
        CAPABILITY_DEPENDENCY_GRAPH.put(Capability.DISPLAY_CONTENT, Collections.emptyList());
        CAPABILITY_DEPENDENCY_GRAPH.put(Capability.MEDIA, Collections.emptyList());
        CAPABILITY_DEPENDENCY_GRAPH.put(Capability.SIDEWALK, Collections.emptyList());
        CAPABILITY_DEPENDENCY_GRAPH.put(Capability.HEARING_ENHANCEMENT, Collections.emptyList());
        CAPABILITY_DEPENDENCY_GRAPH.put(Capability.NON_HFP_CALLING, Collections.emptyList());
        CAPABILITY_DEPENDENCY_GRAPH.put(Capability.NOTIFICATION, Collections.emptyList());
        CAPABILITY_DEPENDENCY_GRAPH.put(Capability.CLOUD_PAIRING, Collections.emptyList());
        NON_DEFAULT_CAPABILITIES.add(Capability.SIDEWALK);
    }

    private Set<Capability> getDefaultCapabilities() {
        Set<Capability> keySet = CAPABILITY_DEPENDENCY_GRAPH.keySet();
        HashSet hashSet = new HashSet();
        for (Capability capability : keySet) {
            hashSet.add(capability);
        }
        hashSet.removeAll(NON_DEFAULT_CAPABILITIES);
        return hashSet;
    }

    public Set<Capability> getRequiredCapabilities(List<Capability> list) {
        if (list != null && list.size() != 0) {
            HashSet hashSet = new HashSet();
            for (Capability capability : list) {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(CAPABILITY_DEPENDENCY_GRAPH.get(capability));
                while (arrayList.size() != 0) {
                    for (int i = 0; i < arrayList.size(); i++) {
                        Capability capability2 = (Capability) arrayList.remove(i);
                        hashSet.add(capability2);
                        arrayList.addAll(CAPABILITY_DEPENDENCY_GRAPH.get(capability2));
                    }
                }
            }
            hashSet.addAll(list);
            return hashSet;
        }
        return getDefaultCapabilities();
    }
}
