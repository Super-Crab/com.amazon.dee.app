package amazon.speech.simclient.context;

import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public enum Lifecycle {
    TRANSIENT(0),
    SURVIVE_CLIENT_DEATH(1),
    PERSISTENT(2);
    
    private static final Map<Integer, Lifecycle> cLifecycleMap = new HashMap();
    private final int mValue;

    static {
        Lifecycle[] values;
        for (Lifecycle lifecycle : values()) {
            cLifecycleMap.put(Integer.valueOf(lifecycle.getValue()), lifecycle);
        }
    }

    Lifecycle(int i) {
        this.mValue = i;
    }

    public static Lifecycle fromInt(int i) {
        Lifecycle lifecycle = cLifecycleMap.get(Integer.valueOf(i));
        if (lifecycle != null) {
            return lifecycle;
        }
        throw new IllegalArgumentException();
    }

    public int getValue() {
        return this.mValue;
    }
}
