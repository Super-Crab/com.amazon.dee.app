package com.sun.mail.imap;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.sun.mail.util.MailLogger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import javax.mail.Message;
/* loaded from: classes3.dex */
public class MessageCache {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int SLOP = 64;
    private IMAPFolder folder;
    private MailLogger logger;
    private IMAPMessage[] messages;
    private int[] seqnums;
    private int size;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageCache(IMAPFolder iMAPFolder, IMAPStore iMAPStore, int i) {
        this.folder = iMAPFolder;
        this.logger = iMAPFolder.logger.getSubLogger("messagecache", "DEBUG IMAP MC", iMAPStore.getMessageCacheDebug());
        if (this.logger.isLoggable(Level.CONFIG)) {
            MailLogger mailLogger = this.logger;
            mailLogger.config("create cache of size " + i);
        }
        ensureCapacity(i, 1);
    }

    private void ensureCapacity(int i, int i2) {
        IMAPMessage[] iMAPMessageArr = this.messages;
        if (iMAPMessageArr == null) {
            this.messages = new IMAPMessage[i + 64];
        } else if (iMAPMessageArr.length < i) {
            if (this.logger.isLoggable(Level.FINE)) {
                this.logger.fine("expand capacity to " + i);
            }
            int i3 = i + 64;
            IMAPMessage[] iMAPMessageArr2 = new IMAPMessage[i3];
            IMAPMessage[] iMAPMessageArr3 = this.messages;
            System.arraycopy(iMAPMessageArr3, 0, iMAPMessageArr2, 0, iMAPMessageArr3.length);
            this.messages = iMAPMessageArr2;
            int[] iArr = this.seqnums;
            if (iArr != null) {
                int[] iArr2 = new int[i3];
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                int i4 = this.size;
                while (i4 < iArr2.length) {
                    iArr2[i4] = i2;
                    i4++;
                    i2++;
                }
                this.seqnums = iArr2;
                if (this.logger.isLoggable(Level.FINE)) {
                    MailLogger mailLogger = this.logger;
                    StringBuilder outline109 = GeneratedOutlineSupport1.outline109("message ", i, " has sequence number ");
                    outline109.append(this.seqnums[i - 1]);
                    mailLogger.fine(outline109.toString());
                }
            }
        } else if (i < this.size) {
            if (this.logger.isLoggable(Level.FINE)) {
                this.logger.fine("shrink capacity to " + i);
            }
            for (int i5 = i + 1; i5 <= this.size; i5++) {
                int i6 = i5 - 1;
                this.messages[i6] = null;
                int[] iArr3 = this.seqnums;
                if (iArr3 != null) {
                    iArr3[i6] = -1;
                }
            }
        }
        this.size = i;
    }

    private int msgnumOf(int i) {
        if (this.seqnums == null) {
            return i;
        }
        if (i < 1) {
            if (this.logger.isLoggable(Level.FINE)) {
                this.logger.fine("bad seqnum " + i);
            }
            return -1;
        }
        for (int i2 = i; i2 <= this.size; i2++) {
            int[] iArr = this.seqnums;
            int i3 = i2 - 1;
            if (iArr[i3] == i) {
                return i2;
            }
            if (iArr[i3] > i) {
                break;
            }
        }
        return -1;
    }

    private void shrink(int i, int i2) {
        this.size = i - 1;
        if (this.logger.isLoggable(Level.FINE)) {
            MailLogger mailLogger = this.logger;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("size now ");
            outline107.append(this.size);
            mailLogger.fine(outline107.toString());
        }
        int i3 = this.size;
        if (i3 == 0) {
            this.messages = null;
            this.seqnums = null;
        } else if (i3 > 64 && i3 < this.messages.length / 2) {
            this.logger.fine("reallocate array");
            int i4 = this.size;
            IMAPMessage[] iMAPMessageArr = new IMAPMessage[i4 + 64];
            System.arraycopy(this.messages, 0, iMAPMessageArr, 0, i4);
            this.messages = iMAPMessageArr;
            int[] iArr = this.seqnums;
            if (iArr == null) {
                return;
            }
            int i5 = this.size;
            int[] iArr2 = new int[i5 + 64];
            System.arraycopy(iArr, 0, iArr2, 0, i5);
            this.seqnums = iArr2;
        } else {
            if (this.logger.isLoggable(Level.FINE)) {
                this.logger.fine("clean " + i + " to " + i2);
            }
            while (i < i2) {
                int i6 = i - 1;
                this.messages[i6] = null;
                int[] iArr3 = this.seqnums;
                if (iArr3 != null) {
                    iArr3[i6] = 0;
                }
                i++;
            }
        }
    }

    public void addMessages(int i, int i2) {
        if (this.logger.isLoggable(Level.FINE)) {
            MailLogger mailLogger = this.logger;
            mailLogger.fine("add " + i + " messages");
        }
        ensureCapacity(this.size + i, i2);
    }

    public void expungeMessage(int i) {
        int msgnumOf = msgnumOf(i);
        if (msgnumOf < 0) {
            if (!this.logger.isLoggable(Level.FINE)) {
                return;
            }
            this.logger.fine("expunge no seqnum " + i);
            return;
        }
        int i2 = msgnumOf - 1;
        IMAPMessage iMAPMessage = this.messages[i2];
        if (iMAPMessage != null) {
            if (this.logger.isLoggable(Level.FINE)) {
                this.logger.fine("expunge existing " + msgnumOf);
            }
            iMAPMessage.setExpunged(true);
        }
        int[] iArr = this.seqnums;
        if (iArr == null) {
            this.logger.fine("create seqnums array");
            this.seqnums = new int[this.messages.length];
            for (int i3 = 1; i3 < msgnumOf; i3++) {
                this.seqnums[i3 - 1] = i3;
            }
            this.seqnums[i2] = 0;
            int i4 = msgnumOf + 1;
            while (true) {
                int[] iArr2 = this.seqnums;
                if (i4 > iArr2.length) {
                    return;
                }
                int i5 = i4 - 1;
                iArr2[i5] = i5;
                i4++;
            }
        } else {
            iArr[i2] = 0;
            int i6 = msgnumOf + 1;
            while (true) {
                int[] iArr3 = this.seqnums;
                if (i6 > iArr3.length) {
                    return;
                }
                int i7 = i6 - 1;
                if (iArr3[i7] > 0) {
                    iArr3[i7] = iArr3[i7] - 1;
                }
                i6++;
            }
        }
    }

    public IMAPMessage getMessage(int i) {
        if (i >= 1 && i <= this.size) {
            int i2 = i - 1;
            IMAPMessage iMAPMessage = this.messages[i2];
            if (iMAPMessage == null) {
                if (this.logger.isLoggable(Level.FINE)) {
                    this.logger.fine("create message number " + i);
                }
                iMAPMessage = this.folder.newIMAPMessage(i);
                this.messages[i2] = iMAPMessage;
                if (seqnumOf(i) <= 0) {
                    this.logger.fine("it's expunged!");
                    iMAPMessage.setExpunged(true);
                }
            }
            return iMAPMessage;
        }
        throw new ArrayIndexOutOfBoundsException(GeneratedOutlineSupport1.outline86(GeneratedOutlineSupport1.outline109("message number (", i, ") out of bounds ("), this.size, ")"));
    }

    public IMAPMessage getMessageBySeqnum(int i) {
        int msgnumOf = msgnumOf(i);
        if (msgnumOf < 0) {
            if (!this.logger.isLoggable(Level.FINE)) {
                return null;
            }
            MailLogger mailLogger = this.logger;
            mailLogger.fine("no message seqnum " + i);
            return null;
        }
        return getMessage(msgnumOf);
    }

    public IMAPMessage[] removeExpungedMessages() {
        this.logger.fine("remove expunged messages");
        ArrayList arrayList = new ArrayList();
        int i = 1;
        int i2 = 1;
        while (i <= this.size) {
            if (seqnumOf(i) <= 0) {
                arrayList.add(getMessage(i));
            } else {
                if (i2 != i) {
                    IMAPMessage[] iMAPMessageArr = this.messages;
                    int i3 = i2 - 1;
                    iMAPMessageArr[i3] = iMAPMessageArr[i - 1];
                    if (iMAPMessageArr[i3] != null) {
                        iMAPMessageArr[i3].setMessageNumber(i2);
                    }
                }
                i2++;
            }
            i++;
        }
        this.seqnums = null;
        shrink(i2, i);
        IMAPMessage[] iMAPMessageArr2 = new IMAPMessage[arrayList.size()];
        if (this.logger.isLoggable(Level.FINE)) {
            MailLogger mailLogger = this.logger;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("return ");
            outline107.append(iMAPMessageArr2.length);
            mailLogger.fine(outline107.toString());
        }
        arrayList.toArray(iMAPMessageArr2);
        return iMAPMessageArr2;
    }

    public int seqnumOf(int i) {
        if (this.seqnums == null) {
            return i;
        }
        if (this.logger.isLoggable(Level.FINE)) {
            MailLogger mailLogger = this.logger;
            StringBuilder outline109 = GeneratedOutlineSupport1.outline109("msgnum ", i, " is seqnum ");
            outline109.append(this.seqnums[i - 1]);
            mailLogger.fine(outline109.toString());
        }
        return this.seqnums[i - 1];
    }

    public int size() {
        return this.size;
    }

    MessageCache(int i, boolean z) {
        this.folder = null;
        this.logger = new MailLogger(MessageCache.class, "messagecache", "DEBUG IMAP MC", z, System.out);
        if (this.logger.isLoggable(Level.CONFIG)) {
            MailLogger mailLogger = this.logger;
            mailLogger.config("create DEBUG cache of size " + i);
        }
        ensureCapacity(i, 1);
    }

    public IMAPMessage[] removeExpungedMessages(Message[] messageArr) {
        this.logger.fine("remove expunged messages");
        ArrayList arrayList = new ArrayList();
        int[] iArr = new int[messageArr.length];
        for (int i = 0; i < messageArr.length; i++) {
            iArr[i] = messageArr[i].getMessageNumber();
        }
        Arrays.sort(iArr);
        int i2 = 1;
        boolean z = false;
        int i3 = 0;
        int i4 = 1;
        while (i4 <= this.size) {
            if (i3 < iArr.length && i4 == iArr[i3] && seqnumOf(i4) <= 0) {
                arrayList.add(getMessage(i4));
                while (i3 < iArr.length && iArr[i3] <= i4) {
                    i3++;
                }
            } else {
                if (i2 != i4) {
                    IMAPMessage[] iMAPMessageArr = this.messages;
                    int i5 = i2 - 1;
                    int i6 = i4 - 1;
                    iMAPMessageArr[i5] = iMAPMessageArr[i6];
                    if (iMAPMessageArr[i5] != null) {
                        iMAPMessageArr[i5].setMessageNumber(i2);
                    }
                    int[] iArr2 = this.seqnums;
                    if (iArr2 != null) {
                        iArr2[i5] = iArr2[i6];
                    }
                }
                int[] iArr3 = this.seqnums;
                if (iArr3 != null && iArr3[i2 - 1] != i2) {
                    z = true;
                }
                i2++;
            }
            i4++;
        }
        if (!z) {
            this.seqnums = null;
        }
        shrink(i2, i4);
        IMAPMessage[] iMAPMessageArr2 = new IMAPMessage[arrayList.size()];
        if (this.logger.isLoggable(Level.FINE)) {
            MailLogger mailLogger = this.logger;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("return ");
            outline107.append(iMAPMessageArr2.length);
            mailLogger.fine(outline107.toString());
        }
        arrayList.toArray(iMAPMessageArr2);
        return iMAPMessageArr2;
    }
}
