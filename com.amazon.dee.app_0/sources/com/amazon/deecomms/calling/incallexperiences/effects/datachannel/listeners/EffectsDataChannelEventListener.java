package com.amazon.deecomms.calling.incallexperiences.effects.datachannel.listeners;
/* loaded from: classes12.dex */
public interface EffectsDataChannelEventListener {
    void onApplied();

    void onApplyFailed(String str);

    void onRemoveFailed(String str);

    void onRemoved();

    void refreshMenu();
}
