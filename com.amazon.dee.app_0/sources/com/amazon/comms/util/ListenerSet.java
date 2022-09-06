package com.amazon.comms.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import java.util.Set;
import javax.annotation.Nonnull;
/* loaded from: classes12.dex */
public class ListenerSet<T> {
    private final Set<T> listeners = Sets.newConcurrentHashSet();

    /* loaded from: classes12.dex */
    public interface Notifier<T> {
        void notify(T t);
    }

    public boolean add(@Nonnull T t) {
        Preconditions.checkArgument(t != null, "listener must be non-null");
        return this.listeners.add(t);
    }

    public void clear() {
        this.listeners.clear();
    }

    public void notify(@Nonnull Notifier notifier) {
        Preconditions.checkArgument(notifier != null, "notifier must be non-null");
        for (T t : this.listeners) {
            notifier.notify(t);
        }
    }

    public boolean remove(@Nonnull T t) {
        Preconditions.checkArgument(t != null, "listener must be non-null");
        return this.listeners.remove(t);
    }

    public int size() {
        return this.listeners.size();
    }
}
