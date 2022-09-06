package com.amazon.comms.calling.service;
/* loaded from: classes11.dex */
public interface Participant {

    /* loaded from: classes11.dex */
    public interface Listener {
        void onUpdated(Participant participant);
    }

    String getEndpointDescription();

    String getName();

    String getProviderSpecifiedId();

    String getProviderSpecifiedName();

    String getUri();

    boolean hasDropInPermission();

    void registerListener(Listener listener);

    void unregisterListener(Listener listener);
}
