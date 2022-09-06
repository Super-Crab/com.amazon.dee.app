package com.amazon.alexa.eventbus.core;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.Subscriber;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;
/* loaded from: classes7.dex */
class Registry {
    @VisibleForTesting
    final Set<Subscriber> subscribers = new HashSet();

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addSubscriber(@NonNull Subscriber subscriber) {
        synchronized (this.subscribers) {
            this.subscribers.add(subscriber);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clear() {
        synchronized (this.subscribers) {
            this.subscribers.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public Set<Subscriber> getSubscribersForMessage(@NonNull Message message) {
        HashSet hashSet = new HashSet();
        synchronized (this.subscribers) {
            for (Subscriber subscriber : this.subscribers) {
                if (subscriber.supportsMessage(message)) {
                    hashSet.add(subscriber);
                }
            }
        }
        return hashSet;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeSubscriber(@NonNull Subscriber subscriber) {
        synchronized (this.subscribers) {
            this.subscribers.remove(subscriber);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeSubscriber(@NonNull UUID uuid) {
        synchronized (this.subscribers) {
            Subscriber subscriber = null;
            Iterator<Subscriber> it2 = this.subscribers.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                Subscriber next = it2.next();
                if (next.getUUID().equals(uuid)) {
                    subscriber = next;
                    break;
                }
            }
            if (subscriber != null) {
                this.subscribers.remove(subscriber);
            }
        }
    }
}
