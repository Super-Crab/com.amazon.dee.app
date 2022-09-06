package org.apache.commons.net.util;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.EventListener;
import java.util.Vector;
/* loaded from: classes4.dex */
public class ListenerList implements Serializable {
    private Vector __listeners = new Vector();

    public synchronized void addListener(EventListener eventListener) {
        this.__listeners.addElement(eventListener);
    }

    public int getListenerCount() {
        return this.__listeners.size();
    }

    public synchronized Enumeration getListeners() {
        return ((Vector) this.__listeners.clone()).elements();
    }

    public synchronized void removeListener(EventListener eventListener) {
        this.__listeners.removeElement(eventListener);
    }
}
