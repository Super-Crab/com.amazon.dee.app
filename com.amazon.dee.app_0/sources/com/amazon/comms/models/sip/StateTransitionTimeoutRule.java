package com.amazon.comms.models.sip;

import com.amazon.alexa.presence.bleconn.service.PresenceBleService;
import com.amazon.deecomms.common.Constants;
import com.amazon.devicesetup.common.v1.WifiConnectionState;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
/* loaded from: classes11.dex */
public class StateTransitionTimeoutRule {
    private static final Map<String, List<String>> CONVERSION_MAP = new HashMap();
    @JsonIgnore
    private boolean isHalloV2;
    private List<String> timeoutEndStates;
    private int timeoutInMilliseconds;
    private String timeoutStartState;

    /* loaded from: classes11.dex */
    public static class StateTransitionTimeoutRuleBuilder {
        private boolean isHalloV2;
        private List<String> timeoutEndStates;
        private int timeoutInMilliseconds;
        private String timeoutStartState;

        StateTransitionTimeoutRuleBuilder() {
        }

        public StateTransitionTimeoutRule build() {
            StateTransitionTimeoutRule.CONVERSION_MAP.put("INACTIVE", new ArrayList(Arrays.asList("UNREGISTERED", WifiConnectionState.IDLE, PresenceBleService.ServiceState.STOPPING)));
            StateTransitionTimeoutRule.CONVERSION_MAP.put(Constants.NOTIFICATION_TARGET_CALLING, new ArrayList(Arrays.asList("TRYING", "INVITED")));
            StateTransitionTimeoutRule.CONVERSION_MAP.put("OUTBOUND_RINGING", new ArrayList(Arrays.asList("OUTBOUND_LOCAL_RINGING", "OUTBOUND_PROVIDER_RINGING")));
            StateTransitionTimeoutRule.CONVERSION_MAP.put("INBOUND_RINGING", new ArrayList(Arrays.asList("INBOUND_RINGING")));
            StateTransitionTimeoutRule.CONVERSION_MAP.put("ACTIVE", new ArrayList(Arrays.asList("ACTIVE")));
            return new StateTransitionTimeoutRule(this.timeoutStartState, this.timeoutEndStates, this.timeoutInMilliseconds, this.isHalloV2);
        }

        public StateTransitionTimeoutRuleBuilder isHalloV2(boolean z) {
            this.isHalloV2 = z;
            return this;
        }

        public StateTransitionTimeoutRuleBuilder timeoutEndStates(List<String> list) {
            this.timeoutEndStates = list;
            return this;
        }

        public StateTransitionTimeoutRuleBuilder timeoutInMilliseconds(int i) {
            this.timeoutInMilliseconds = i;
            return this;
        }

        public StateTransitionTimeoutRuleBuilder timeoutStartState(String str) {
            this.timeoutStartState = str;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("StateTransitionTimeoutRule.StateTransitionTimeoutRuleBuilder(timeoutStartState=");
            outline107.append(this.timeoutStartState);
            outline107.append(", timeoutEndStates=");
            outline107.append(this.timeoutEndStates);
            outline107.append(", timeoutInMilliseconds=");
            outline107.append(this.timeoutInMilliseconds);
            outline107.append(", isHalloV2=");
            return GeneratedOutlineSupport1.outline97(outline107, this.isHalloV2, ")");
        }
    }

    public StateTransitionTimeoutRule() {
        this.isHalloV2 = false;
    }

    public static StateTransitionTimeoutRuleBuilder builder() {
        return new StateTransitionTimeoutRuleBuilder();
    }

    @JsonIgnore
    private List<String> getV2States(String str) {
        return CONVERSION_MAP.get(str);
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof StateTransitionTimeoutRule;
    }

    @JsonIgnore
    public Optional<List<StateTransitionTimeoutRule>> convertToHalloV2() {
        if (this.isHalloV2) {
            return Optional.empty();
        }
        ArrayList arrayList = new ArrayList();
        for (String str : getV2States(this.timeoutStartState)) {
            ArrayList arrayList2 = new ArrayList();
            for (String str2 : this.timeoutEndStates) {
                arrayList2.addAll(getV2States(str2));
            }
            arrayList.add(builder().timeoutStartState(str).timeoutEndStates(arrayList2).timeoutInMilliseconds(this.timeoutInMilliseconds).isHalloV2(true).build());
        }
        return Optional.of(arrayList);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StateTransitionTimeoutRule)) {
            return false;
        }
        StateTransitionTimeoutRule stateTransitionTimeoutRule = (StateTransitionTimeoutRule) obj;
        if (!stateTransitionTimeoutRule.canEqual(this)) {
            return false;
        }
        String timeoutStartState = getTimeoutStartState();
        String timeoutStartState2 = stateTransitionTimeoutRule.getTimeoutStartState();
        if (timeoutStartState != null ? !timeoutStartState.equals(timeoutStartState2) : timeoutStartState2 != null) {
            return false;
        }
        List<String> timeoutEndStates = getTimeoutEndStates();
        List<String> timeoutEndStates2 = stateTransitionTimeoutRule.getTimeoutEndStates();
        if (timeoutEndStates != null ? !timeoutEndStates.equals(timeoutEndStates2) : timeoutEndStates2 != null) {
            return false;
        }
        return getTimeoutInMilliseconds() == stateTransitionTimeoutRule.getTimeoutInMilliseconds() && getIsHalloV2() == stateTransitionTimeoutRule.getIsHalloV2();
    }

    public boolean getIsHalloV2() {
        return this.isHalloV2;
    }

    public List<String> getTimeoutEndStates() {
        return this.timeoutEndStates;
    }

    public int getTimeoutInMilliseconds() {
        return this.timeoutInMilliseconds;
    }

    public String getTimeoutStartState() {
        return this.timeoutStartState;
    }

    public int hashCode() {
        String timeoutStartState = getTimeoutStartState();
        int i = 43;
        int hashCode = timeoutStartState == null ? 43 : timeoutStartState.hashCode();
        List<String> timeoutEndStates = getTimeoutEndStates();
        int i2 = (hashCode + 59) * 59;
        if (timeoutEndStates != null) {
            i = timeoutEndStates.hashCode();
        }
        return ((getTimeoutInMilliseconds() + ((i2 + i) * 59)) * 59) + (getIsHalloV2() ? 79 : 97);
    }

    public void setIsHalloV2(boolean z) {
        this.isHalloV2 = z;
    }

    public void setTimeoutEndStates(List<String> list) {
        this.timeoutEndStates = list;
    }

    public void setTimeoutInMilliseconds(int i) {
        this.timeoutInMilliseconds = i;
    }

    public void setTimeoutStartState(String str) {
        this.timeoutStartState = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("StateTransitionTimeoutRule(timeoutStartState=");
        outline107.append(getTimeoutStartState());
        outline107.append(", timeoutEndStates=");
        outline107.append(getTimeoutEndStates());
        outline107.append(", timeoutInMilliseconds=");
        outline107.append(getTimeoutInMilliseconds());
        outline107.append(", isHalloV2=");
        outline107.append(getIsHalloV2());
        outline107.append(")");
        return outline107.toString();
    }

    private StateTransitionTimeoutRule(String str, List<String> list, int i, boolean z) {
        this.isHalloV2 = false;
        this.timeoutStartState = str;
        this.timeoutEndStates = list;
        this.timeoutInMilliseconds = i;
        this.isHalloV2 = z;
    }
}
