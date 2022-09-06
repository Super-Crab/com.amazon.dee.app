package amazon.speech.csmshark;

import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public enum CSMSharkFlag {
    LOG_SHARKABLES("logSharkables", false),
    ENQUEUE_SHARKABLES("enqueueSharkables", true);
    
    private final boolean defaultValue;
    private final String name;

    CSMSharkFlag(String str, boolean z) {
        this.name = str;
        this.defaultValue = z;
    }

    public static List<String> getFlagNames(CSMSharkFlag... cSMSharkFlagArr) {
        ArrayList arrayList = new ArrayList();
        for (CSMSharkFlag cSMSharkFlag : cSMSharkFlagArr) {
            arrayList.add(cSMSharkFlag.getName());
        }
        return arrayList;
    }

    public boolean getDefault() {
        return this.defaultValue;
    }

    public String getName() {
        return this.name;
    }
}
