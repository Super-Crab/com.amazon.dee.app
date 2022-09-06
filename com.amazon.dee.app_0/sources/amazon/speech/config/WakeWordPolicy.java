package amazon.speech.config;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public abstract class WakeWordPolicy {
    private static final String TAG = "WakeWordPolicy";

    private boolean allConditionsPresent(Map<String, Boolean> map) {
        for (String str : getConditions()) {
            if (map.get(str) == null) {
                GeneratedOutlineSupport1.outline162("State Map incomplete, missing configuration: ", str, TAG);
                return false;
            }
        }
        return true;
    }

    public abstract List<String> getConditions();

    public abstract Map<String, Boolean> getDefaultStateMap();

    public boolean shouldEnableWakeWord(Map<String, Boolean> map) {
        if (allConditionsPresent(map)) {
            return shouldEnableWakeWordImpl(map);
        }
        return false;
    }

    abstract boolean shouldEnableWakeWordImpl(Map<String, Boolean> map);

    public boolean shouldHoldMicExclusive(Map<String, Boolean> map) {
        if (allConditionsPresent(map)) {
            return shouldHoldMicExclusiveImpl(map);
        }
        return false;
    }

    abstract boolean shouldHoldMicExclusiveImpl(Map<String, Boolean> map);
}
