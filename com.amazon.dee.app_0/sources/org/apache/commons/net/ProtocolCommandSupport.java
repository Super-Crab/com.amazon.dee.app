package org.apache.commons.net;

import java.io.Serializable;
import java.util.Enumeration;
import org.apache.commons.net.util.ListenerList;
/* loaded from: classes4.dex */
public class ProtocolCommandSupport implements Serializable {
    private ListenerList __listeners = new ListenerList();
    private Object __source;

    public ProtocolCommandSupport(Object obj) {
        this.__source = obj;
    }

    public void addProtocolCommandListener(ProtocolCommandListener protocolCommandListener) {
        this.__listeners.addListener(protocolCommandListener);
    }

    public void fireCommandSent(String str, String str2) {
        Enumeration listeners = this.__listeners.getListeners();
        ProtocolCommandEvent protocolCommandEvent = new ProtocolCommandEvent(this.__source, str, str2);
        while (listeners.hasMoreElements()) {
            ((ProtocolCommandListener) listeners.nextElement()).protocolCommandSent(protocolCommandEvent);
        }
    }

    public void fireReplyReceived(int i, String str) {
        Enumeration listeners = this.__listeners.getListeners();
        ProtocolCommandEvent protocolCommandEvent = new ProtocolCommandEvent(this.__source, i, str);
        while (listeners.hasMoreElements()) {
            ((ProtocolCommandListener) listeners.nextElement()).protocolReplyReceived(protocolCommandEvent);
        }
    }

    public int getListenerCount() {
        return this.__listeners.getListenerCount();
    }

    public void removeProtocolCommandListener(ProtocolCommandListener protocolCommandListener) {
        this.__listeners.removeListener(protocolCommandListener);
    }
}
