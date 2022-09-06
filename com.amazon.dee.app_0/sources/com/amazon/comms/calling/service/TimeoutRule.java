package com.amazon.comms.calling.service;

import com.amazon.comms.calling.service.Call;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes11.dex */
public class TimeoutRule {
    private List<Call.State> timeoutEndStates;
    private int timeoutInMilliseconds;
    private Call.State timeoutStartState;

    public TimeoutRule(Call.State state, List<Call.State> list, int i) {
        ArrayList arrayList = new ArrayList(list.size());
        for (Call.State state2 : list) {
            arrayList.add(state2);
        }
        this.timeoutStartState = state;
        this.timeoutEndStates = ImmutableList.copyOf((Collection) arrayList);
        this.timeoutInMilliseconds = i;
    }

    public List<Call.State> getTimeoutEndStates() {
        return this.timeoutEndStates;
    }

    public int getTimeoutInMilliseconds() {
        return this.timeoutInMilliseconds;
    }

    public Call.State getTimeoutStartState() {
        return this.timeoutStartState;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("timeoutStartState=");
        outline107.append(this.timeoutStartState.toString());
        String sb = outline107.toString();
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("timeoutEndStates=");
        outline1072.append(this.timeoutEndStates.toString());
        String sb2 = outline1072.toString();
        StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("timeoutInMilliseconds=");
        outline1073.append(Integer.toString(this.timeoutInMilliseconds));
        return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline116("TimeoutRule(", sb, ", ", sb2, ", "), outline1073.toString(), ")");
    }
}
