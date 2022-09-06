package com.amazon.comms.ringservice;

import java.util.Iterator;
import org.pjsip.pjsua2.CallMediaInfo;
import org.pjsip.pjsua2.CallMediaInfoVector;
/* loaded from: classes12.dex */
public class CallMediaInfoIterable implements Iterable<CallMediaInfo> {
    private final CallMediaInfoVector mediaInfoVector;

    /* loaded from: classes12.dex */
    private static class CallMediaInfoIterator implements Iterator<CallMediaInfo> {
        private int curIndex = 0;
        private final CallMediaInfoVector mediaInfoVector;

        public CallMediaInfoIterator(CallMediaInfoVector callMediaInfoVector) {
            this.mediaInfoVector = callMediaInfoVector;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return ((long) this.curIndex) < this.mediaInfoVector.size();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Remove not supported for CallMediaInfoIterator");
        }

        @Override // java.util.Iterator
        public CallMediaInfo next() {
            CallMediaInfo callMediaInfo = this.mediaInfoVector.get(this.curIndex);
            this.curIndex++;
            return callMediaInfo;
        }
    }

    public CallMediaInfoIterable(CallMediaInfoVector callMediaInfoVector) {
        this.mediaInfoVector = callMediaInfoVector;
    }

    @Override // java.lang.Iterable
    public Iterator<CallMediaInfo> iterator() {
        return new CallMediaInfoIterator(this.mediaInfoVector);
    }
}
