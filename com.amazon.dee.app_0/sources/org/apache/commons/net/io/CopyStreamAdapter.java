package org.apache.commons.net.io;

import java.util.Enumeration;
import org.apache.commons.net.util.ListenerList;
/* loaded from: classes4.dex */
public class CopyStreamAdapter implements CopyStreamListener {
    private ListenerList internalListeners = new ListenerList();

    public void addCopyStreamListener(CopyStreamListener copyStreamListener) {
        this.internalListeners.addListener(copyStreamListener);
    }

    @Override // org.apache.commons.net.io.CopyStreamListener
    public void bytesTransferred(CopyStreamEvent copyStreamEvent) {
        bytesTransferred(copyStreamEvent.getTotalBytesTransferred(), copyStreamEvent.getBytesTransferred(), copyStreamEvent.getStreamSize());
    }

    public void removeCopyStreamListener(CopyStreamListener copyStreamListener) {
        this.internalListeners.removeListener(copyStreamListener);
    }

    @Override // org.apache.commons.net.io.CopyStreamListener
    public void bytesTransferred(long j, int i, long j2) {
        Enumeration listeners = this.internalListeners.getListeners();
        CopyStreamEvent copyStreamEvent = new CopyStreamEvent(this, j, i, j2);
        while (listeners.hasMoreElements()) {
            ((CopyStreamListener) listeners.nextElement()).bytesTransferred(copyStreamEvent);
        }
    }
}
