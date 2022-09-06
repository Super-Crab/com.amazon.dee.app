package com.amazon.alexa.featureservice.recordTrigger;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes7.dex */
class TriggerQueue {
    @VisibleForTesting
    final List<RequestTreatment> list;
    private ChangeListener listener;

    /* loaded from: classes7.dex */
    interface ChangeListener {
        default void onAdd(@NonNull Collection<RequestTreatment> collection) {
        }

        default void onInsert(int i, @NonNull Collection<RequestTreatment> collection) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TriggerQueue(@NonNull ChangeListener changeListener) {
        this(changeListener, new ArrayList());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized boolean add(@NonNull Collection<RequestTreatment> collection) {
        boolean addAll;
        addAll = this.list.addAll(collection);
        this.listener.onAdd(collection);
        return addAll;
    }

    synchronized boolean insert(int i, @NonNull Collection<RequestTreatment> collection) {
        boolean addAll;
        addAll = this.list.addAll(i, collection);
        this.listener.onInsert(i, collection);
        return addAll;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized boolean isEmpty() {
        return this.list.isEmpty();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized List<RequestTreatment> popAll() {
        ArrayList arrayList;
        arrayList = new ArrayList(this.list);
        this.list.clear();
        return arrayList;
    }

    public String toString() {
        return this.list.toString();
    }

    @VisibleForTesting
    TriggerQueue(@NonNull ChangeListener changeListener, @NonNull List<RequestTreatment> list) {
        this.list = new ArrayList(list);
        this.listener = changeListener;
    }
}
