package com.amazon.communication;

import java.util.Set;
/* loaded from: classes12.dex */
public interface InstanceTracker<E> {
    Set<E> getTrackedInstances();

    boolean isTimedOut(E e);

    boolean stopTrackingInstance(E e);
}
