package com.amazon.comms.calling.service;

import com.amazon.comms.calling.service.Call;
/* loaded from: classes11.dex */
public interface DropInController {

    /* loaded from: classes11.dex */
    public enum UserPreference {
        NeverFrosted,
        AlwaysFrosted,
        Transition,
        AudioOnly
    }

    double getFrostedTransitionTime();

    double getTotalFrostTime();

    UserPreference getUserPreference();

    void initializeWithAcceptParams(Call.AcceptParams acceptParams);

    boolean shouldShowRemoteView(Call call);

    boolean shouldShowSelfView(Call call);

    void startDefrostTimer(Call call);
}
