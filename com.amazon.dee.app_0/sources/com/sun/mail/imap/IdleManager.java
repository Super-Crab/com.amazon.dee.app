package com.sun.mail.imap;

import com.sun.mail.util.MailLogger;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Session;
/* loaded from: classes3.dex */
public class IdleManager {
    private Executor es;
    private MailLogger logger;
    private volatile boolean die = false;
    private Queue<IMAPFolder> toWatch = new ConcurrentLinkedQueue();
    private Queue<IMAPFolder> toAbort = new ConcurrentLinkedQueue();
    private Selector selector = Selector.open();

    public IdleManager(Session session, Executor executor) throws IOException {
        this.es = executor;
        this.logger = new MailLogger(IdleManager.class, "DEBUG IMAP", session);
        executor.execute(new Runnable() { // from class: com.sun.mail.imap.IdleManager.1
            @Override // java.lang.Runnable
            public void run() {
                IdleManager.this.logger.fine("IdleManager select starting");
                try {
                    IdleManager.this.select();
                } finally {
                    IdleManager.this.logger.fine("IdleManager select terminating");
                }
            }
        });
    }

    private boolean processKeys() throws IOException {
        Iterator<SelectionKey> it2 = this.selector.selectedKeys().iterator();
        boolean z = false;
        while (it2.hasNext()) {
            SelectionKey next = it2.next();
            it2.remove();
            next.cancel();
            IMAPFolder iMAPFolder = (IMAPFolder) next.attachment();
            this.logger.log(Level.FINE, "IdleManager selected folder: {0}", iMAPFolder);
            next.channel().configureBlocking(true);
            try {
                if (iMAPFolder.handleIdle(false)) {
                    this.logger.log(Level.FINE, "IdleManager continue watching folder {0}", iMAPFolder);
                    this.toWatch.add(iMAPFolder);
                    z = true;
                } else {
                    this.logger.log(Level.FINE, "IdleManager done watching folder {0}", iMAPFolder);
                }
            } catch (MessagingException e) {
                this.logger.log(Level.FINE, "IdleManager got exception for folder: " + iMAPFolder, (Throwable) e);
            }
        }
        while (true) {
            final IMAPFolder poll = this.toAbort.poll();
            if (poll != null) {
                this.logger.log(Level.FINE, "IdleManager aborting IDLE for folder: {0}", poll);
                SocketChannel channel = poll.getChannel();
                if (channel != null) {
                    SelectionKey keyFor = channel.keyFor(this.selector);
                    if (keyFor != null) {
                        keyFor.cancel();
                    }
                    channel.configureBlocking(true);
                    Socket socket = channel.socket();
                    if (socket != null && socket.getSoTimeout() > 0) {
                        this.logger.fine("IdleManager requesting DONE with timeout");
                        this.toWatch.remove(poll);
                        this.es.execute(new Runnable() { // from class: com.sun.mail.imap.IdleManager.2
                            @Override // java.lang.Runnable
                            public void run() {
                                poll.idleAbortWait();
                            }
                        });
                    } else {
                        poll.idleAbort();
                        this.toWatch.add(poll);
                        z = true;
                    }
                }
            } else {
                return z;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void select() {
        this.die = false;
        while (!this.die) {
            try {
                try {
                    watchAll();
                    this.logger.finest("IdleManager waiting...");
                    int select = this.selector.select();
                    if (this.logger.isLoggable(Level.FINEST)) {
                        this.logger.log(Level.FINEST, "IdleManager selected {0} channels", Integer.valueOf(select));
                    }
                    if (this.die || Thread.currentThread().isInterrupted()) {
                        break;
                    }
                    while (processKeys() && this.selector.selectNow() > 0) {
                    }
                } catch (InterruptedIOException e) {
                    this.logger.log(Level.FINE, "IdleManager interrupted", (Throwable) e);
                    this.logger.fine("IdleManager unwatchAll");
                    try {
                        unwatchAll();
                        this.selector.close();
                    } catch (IOException e2) {
                        e = e2;
                        this.logger.log(Level.FINE, "IdleManager unwatch exception", (Throwable) e);
                        this.logger.fine("IdleManager exiting");
                    }
                } catch (IOException e3) {
                    this.logger.log(Level.FINE, "IdleManager got exception", (Throwable) e3);
                    this.logger.fine("IdleManager unwatchAll");
                    try {
                        unwatchAll();
                        this.selector.close();
                    } catch (IOException e4) {
                        e = e4;
                        this.logger.log(Level.FINE, "IdleManager unwatch exception", (Throwable) e);
                        this.logger.fine("IdleManager exiting");
                    }
                }
            } catch (Throwable th) {
                this.logger.fine("IdleManager unwatchAll");
                try {
                    unwatchAll();
                    this.selector.close();
                } catch (IOException e5) {
                    this.logger.log(Level.FINE, "IdleManager unwatch exception", (Throwable) e5);
                }
                this.logger.fine("IdleManager exiting");
                throw th;
            }
        }
        this.logger.fine("IdleManager unwatchAll");
        try {
            unwatchAll();
            this.selector.close();
        } catch (IOException e6) {
            e = e6;
            this.logger.log(Level.FINE, "IdleManager unwatch exception", (Throwable) e);
            this.logger.fine("IdleManager exiting");
        }
        this.logger.fine("IdleManager exiting");
    }

    private void unwatchAll() {
        for (SelectionKey selectionKey : this.selector.keys()) {
            selectionKey.cancel();
            IMAPFolder iMAPFolder = (IMAPFolder) selectionKey.attachment();
            this.logger.log(Level.FINE, "IdleManager no longer watching folder: {0}", iMAPFolder);
            try {
                selectionKey.channel().configureBlocking(true);
                iMAPFolder.idleAbortWait();
            } catch (IOException e) {
                MailLogger mailLogger = this.logger;
                Level level = Level.FINE;
                mailLogger.log(level, "IdleManager exception while aborting idle for folder: " + iMAPFolder, (Throwable) e);
            }
        }
        while (true) {
            IMAPFolder poll = this.toWatch.poll();
            if (poll != null) {
                this.logger.log(Level.FINE, "IdleManager aborting IDLE for unwatched folder: {0}", poll);
                SocketChannel channel = poll.getChannel();
                if (channel != null) {
                    try {
                        channel.configureBlocking(true);
                        poll.idleAbortWait();
                    } catch (IOException e2) {
                        MailLogger mailLogger2 = this.logger;
                        Level level2 = Level.FINE;
                        mailLogger2.log(level2, "IdleManager exception while aborting idle for folder: " + poll, (Throwable) e2);
                    }
                }
            } else {
                return;
            }
        }
    }

    private void watchAll() {
        while (true) {
            IMAPFolder poll = this.toWatch.poll();
            if (poll != null) {
                this.logger.log(Level.FINEST, "IdleManager adding {0} to selector", poll);
                try {
                    SocketChannel channel = poll.getChannel();
                    if (channel != null) {
                        channel.configureBlocking(false);
                        channel.register(this.selector, 1, poll);
                    }
                } catch (IOException e) {
                    this.logger.log(Level.FINEST, "IdleManager can't register folder", (Throwable) e);
                }
            } else {
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void requestAbort(IMAPFolder iMAPFolder) {
        this.toAbort.add(iMAPFolder);
        this.selector.wakeup();
    }

    public synchronized void stop() {
        this.die = true;
        this.logger.finest("IdleManager stopping");
        this.selector.wakeup();
    }

    public synchronized void watch(Folder folder) throws MessagingException {
        if (folder instanceof IMAPFolder) {
            IMAPFolder iMAPFolder = (IMAPFolder) folder;
            if (iMAPFolder.getChannel() != null) {
                this.logger.log(Level.FINEST, "IdleManager watching {0}", iMAPFolder);
                while (!iMAPFolder.startIdle(this)) {
                }
                this.toWatch.add(iMAPFolder);
                this.selector.wakeup();
            } else {
                throw new MessagingException("Folder is not using SocketChannels");
            }
        } else {
            throw new MessagingException("Can only watch IMAP folders");
        }
    }
}
