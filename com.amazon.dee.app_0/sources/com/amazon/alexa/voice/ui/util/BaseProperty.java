package com.amazon.alexa.voice.ui.util;

import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public abstract class BaseProperty {
    private final List<OnChangedListener> changedListenerList = new ArrayList();

    /* loaded from: classes11.dex */
    public interface OnChangedListener {
        void onPropertyChanged();
    }

    public final void addOnChangedListener(OnChangedListener onChangedListener) {
        if (onChangedListener != null) {
            synchronized (this.changedListenerList) {
                this.changedListenerList.add(onChangedListener);
            }
            return;
        }
        throw new IllegalArgumentException("listener == null");
    }

    public final int getOnChangedListenerSize() {
        int size;
        synchronized (this.changedListenerList) {
            size = this.changedListenerList.size();
        }
        return size;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void notifyChanged() {
        synchronized (this.changedListenerList) {
            for (int size = this.changedListenerList.size() - 1; size >= 0; size--) {
                this.changedListenerList.get(size).onPropertyChanged();
            }
        }
    }

    public final void removeOnChangedListener(OnChangedListener onChangedListener) {
        if (onChangedListener != null) {
            synchronized (this.changedListenerList) {
                this.changedListenerList.remove(onChangedListener);
            }
            return;
        }
        throw new IllegalArgumentException("listener == null");
    }
}
