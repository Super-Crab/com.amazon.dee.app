package com.amazon.deecomms.calling.incallexperiences.effects.datachannel.model;

import androidx.annotation.VisibleForTesting;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
/* loaded from: classes12.dex */
public class EventMetadataQueue extends ConcurrentLinkedQueue {
    @VisibleForTesting
    static final int DATA_CHANNEL_TIMEOUT_MS = 1500;
    private static EventMetadataQueue effectsMetadataQueue;
    private static EventMetadataQueue reactionsMetadataQueue;

    protected EventMetadataQueue() {
    }

    public static EventMetadataQueue getEffectsInstance() {
        if (effectsMetadataQueue == null) {
            effectsMetadataQueue = new EventMetadataQueue();
        }
        return effectsMetadataQueue;
    }

    public static EventMetadataQueue getReactionsInstance() {
        if (reactionsMetadataQueue == null) {
            reactionsMetadataQueue = new EventMetadataQueue();
        }
        return reactionsMetadataQueue;
    }

    public boolean add(EventMetadata eventMetadata) {
        return super.add((EventMetadataQueue) eventMetadata);
    }

    @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection
    public void clear() {
        super.clear();
    }

    public void removeStaleDataChannelEvent(int i) {
        Iterator it2 = iterator();
        while (it2.hasNext() && size() > i) {
            if (System.currentTimeMillis() - ((EventMetadata) it2.next()).getTimestamp() >= 1500) {
                it2.remove();
            }
        }
    }

    @Override // java.util.concurrent.ConcurrentLinkedQueue, java.util.AbstractCollection, java.util.Collection
    public int size() {
        return super.size();
    }

    @Override // java.util.concurrent.ConcurrentLinkedQueue, java.util.Queue
    /* renamed from: poll */
    public EventMetadata mo3625poll() {
        return (EventMetadata) super.poll();
    }
}
