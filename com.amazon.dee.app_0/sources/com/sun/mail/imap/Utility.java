package com.sun.mail.imap;

import com.sun.mail.imap.protocol.MessageSet;
import com.sun.mail.imap.protocol.UIDSet;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Vector;
import javax.mail.Message;
/* loaded from: classes3.dex */
public final class Utility {

    /* loaded from: classes3.dex */
    public interface Condition {
        boolean test(IMAPMessage iMAPMessage);
    }

    private Utility() {
    }

    public static UIDSet[] getResyncUIDSet(ResyncData resyncData) {
        return resyncData.getUIDSet();
    }

    public static MessageSet[] toMessageSet(Message[] messageArr, Condition condition) {
        Vector vector = new Vector(1);
        int i = 0;
        while (i < messageArr.length) {
            IMAPMessage iMAPMessage = (IMAPMessage) messageArr[i];
            if (!iMAPMessage.isExpunged()) {
                int sequenceNumber = iMAPMessage.getSequenceNumber();
                if (condition == null || condition.test(iMAPMessage)) {
                    MessageSet messageSet = new MessageSet();
                    messageSet.start = sequenceNumber;
                    while (true) {
                        i++;
                        if (i >= messageArr.length) {
                            break;
                        }
                        IMAPMessage iMAPMessage2 = (IMAPMessage) messageArr[i];
                        if (!iMAPMessage2.isExpunged()) {
                            int sequenceNumber2 = iMAPMessage2.getSequenceNumber();
                            if (condition == null || condition.test(iMAPMessage2)) {
                                if (sequenceNumber2 != sequenceNumber + 1) {
                                    i--;
                                    break;
                                }
                                sequenceNumber = sequenceNumber2;
                            }
                        }
                    }
                    messageSet.end = sequenceNumber;
                    vector.addElement(messageSet);
                }
            }
            i++;
        }
        if (vector.isEmpty()) {
            return null;
        }
        MessageSet[] messageSetArr = new MessageSet[vector.size()];
        vector.copyInto(messageSetArr);
        return messageSetArr;
    }

    public static MessageSet[] toMessageSetSorted(Message[] messageArr, Condition condition) {
        Message[] messageArr2 = (Message[]) messageArr.clone();
        Arrays.sort(messageArr2, new Comparator<Message>() { // from class: com.sun.mail.imap.Utility.1
            @Override // java.util.Comparator
            public int compare(Message message, Message message2) {
                return message.getMessageNumber() - message2.getMessageNumber();
            }
        });
        return toMessageSet(messageArr2, condition);
    }

    public static UIDSet[] toUIDSet(Message[] messageArr) {
        Vector vector = new Vector(1);
        int i = 0;
        while (i < messageArr.length) {
            IMAPMessage iMAPMessage = (IMAPMessage) messageArr[i];
            if (!iMAPMessage.isExpunged()) {
                long uid = iMAPMessage.getUID();
                UIDSet uIDSet = new UIDSet();
                uIDSet.start = uid;
                while (true) {
                    i++;
                    if (i >= messageArr.length) {
                        break;
                    }
                    IMAPMessage iMAPMessage2 = (IMAPMessage) messageArr[i];
                    if (!iMAPMessage2.isExpunged()) {
                        long uid2 = iMAPMessage2.getUID();
                        if (uid2 != 1 + uid) {
                            i--;
                            break;
                        }
                        uid = uid2;
                    }
                }
                uIDSet.end = uid;
                vector.addElement(uIDSet);
            }
            i++;
        }
        if (vector.isEmpty()) {
            return null;
        }
        UIDSet[] uIDSetArr = new UIDSet[vector.size()];
        vector.copyInto(uIDSetArr);
        return uIDSetArr;
    }
}
