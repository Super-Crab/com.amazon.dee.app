package org.apache.thrift.orig.async;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.nio.channels.ClosedSelectorException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.spi.SelectorProvider;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeoutException;
import org.apache.thrift.orig.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* loaded from: classes4.dex */
public class TAsyncClientManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(TAsyncClientManager.class.getName());
    private final ConcurrentLinkedQueue<TAsyncMethodCall> pendingCalls = new ConcurrentLinkedQueue<>();
    private final SelectThread selectThread = new SelectThread();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public class SelectThread extends Thread {
        private final TreeSet<TAsyncMethodCall> timeoutWatchSet = new TreeSet<>(new TAsyncMethodCallTimeoutComparator());
        private final Selector selector = SelectorProvider.provider().openSelector();
        private volatile boolean running = true;

        public SelectThread() throws IOException {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TAsyncClientManager#SelectorThread ");
            outline107.append(getId());
            setName(outline107.toString());
            setDaemon(true);
        }

        private void startPendingMethods() {
            while (true) {
                TAsyncMethodCall tAsyncMethodCall = (TAsyncMethodCall) TAsyncClientManager.this.pendingCalls.poll();
                if (tAsyncMethodCall != null) {
                    try {
                        tAsyncMethodCall.start(this.selector);
                        TAsyncClient client = tAsyncMethodCall.getClient();
                        if (client.hasTimeout() && !client.hasError()) {
                            this.timeoutWatchSet.add(tAsyncMethodCall);
                        }
                    } catch (Exception e) {
                        TAsyncClientManager.LOGGER.warn("Caught exception in TAsyncClientManager!", (Throwable) e);
                        tAsyncMethodCall.onError(e);
                    }
                } else {
                    return;
                }
            }
        }

        private void timeoutMethods() {
            Iterator<TAsyncMethodCall> it2 = this.timeoutWatchSet.iterator();
            long currentTimeMillis = System.currentTimeMillis();
            while (it2.hasNext()) {
                TAsyncMethodCall next = it2.next();
                if (currentTimeMillis < next.getTimeoutTimestamp()) {
                    return;
                }
                it2.remove();
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Operation ");
                outline107.append(next.getClass());
                outline107.append(" timed out after ");
                outline107.append(currentTimeMillis - next.getStartTime());
                outline107.append(" ms.");
                next.onError(new TimeoutException(outline107.toString()));
            }
        }

        private void transitionMethods() {
            try {
                Iterator<SelectionKey> it2 = this.selector.selectedKeys().iterator();
                while (it2.hasNext()) {
                    SelectionKey next = it2.next();
                    it2.remove();
                    if (next.isValid()) {
                        TAsyncMethodCall tAsyncMethodCall = (TAsyncMethodCall) next.attachment();
                        tAsyncMethodCall.transition(next);
                        if (tAsyncMethodCall.isFinished() || tAsyncMethodCall.getClient().hasError()) {
                            this.timeoutWatchSet.remove(tAsyncMethodCall);
                        }
                    }
                }
            } catch (ClosedSelectorException e) {
                TAsyncClientManager.LOGGER.error("Caught ClosedSelectorException in TAsyncClientManager!", (Throwable) e);
            }
        }

        public void finish() {
            this.running = false;
            this.selector.wakeup();
        }

        public Selector getSelector() {
            return this.selector;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (this.running) {
                try {
                    try {
                        if (this.timeoutWatchSet.size() == 0) {
                            this.selector.select();
                        } else {
                            long timeoutTimestamp = this.timeoutWatchSet.first().getTimeoutTimestamp() - System.currentTimeMillis();
                            if (timeoutTimestamp > 0) {
                                this.selector.select(timeoutTimestamp);
                            } else {
                                this.selector.selectNow();
                            }
                        }
                    } catch (IOException e) {
                        TAsyncClientManager.LOGGER.error("Caught IOException in TAsyncClientManager!", (Throwable) e);
                    }
                    transitionMethods();
                    timeoutMethods();
                    startPendingMethods();
                } catch (Exception e2) {
                    TAsyncClientManager.LOGGER.error("Ignoring uncaught exception in SelectThread", (Throwable) e2);
                }
            }
        }
    }

    /* loaded from: classes4.dex */
    private static class TAsyncMethodCallTimeoutComparator implements Comparator<TAsyncMethodCall> {
        private TAsyncMethodCallTimeoutComparator() {
        }

        @Override // java.util.Comparator
        public int compare(TAsyncMethodCall tAsyncMethodCall, TAsyncMethodCall tAsyncMethodCall2) {
            long timeoutTimestamp;
            long timeoutTimestamp2;
            if (tAsyncMethodCall.getTimeoutTimestamp() == tAsyncMethodCall2.getTimeoutTimestamp()) {
                timeoutTimestamp = tAsyncMethodCall.getSequenceId();
                timeoutTimestamp2 = tAsyncMethodCall2.getSequenceId();
            } else {
                timeoutTimestamp = tAsyncMethodCall.getTimeoutTimestamp();
                timeoutTimestamp2 = tAsyncMethodCall2.getTimeoutTimestamp();
            }
            return (int) (timeoutTimestamp - timeoutTimestamp2);
        }
    }

    public TAsyncClientManager() throws IOException {
        this.selectThread.start();
    }

    public void call(TAsyncMethodCall tAsyncMethodCall) throws TException {
        if (isRunning()) {
            tAsyncMethodCall.prepareMethodCall();
            this.pendingCalls.add(tAsyncMethodCall);
            this.selectThread.getSelector().wakeup();
            return;
        }
        throw new TException("SelectThread is not running");
    }

    public boolean isRunning() {
        return this.selectThread.isAlive();
    }

    public void stop() {
        this.selectThread.finish();
    }
}
