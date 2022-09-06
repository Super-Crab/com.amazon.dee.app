package com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery;

import java.util.Iterator;
@Deprecated
/* loaded from: classes13.dex */
public interface EventStore {

    /* loaded from: classes13.dex */
    public interface EventIterator extends Iterator<String> {
        String peek();

        void removeReadEvents();
    }

    EventIterator iterator();

    boolean put(String str) throws EventStoreException;
}
