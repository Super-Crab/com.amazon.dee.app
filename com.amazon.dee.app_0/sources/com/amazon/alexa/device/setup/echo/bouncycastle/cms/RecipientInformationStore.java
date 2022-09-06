package com.amazon.alexa.device.setup.echo.bouncycastle.cms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public class RecipientInformationStore {
    private final List all;
    private final Map table = new HashMap();

    public RecipientInformationStore(Collection collection) {
        Iterator it2 = collection.iterator();
        while (it2.hasNext()) {
            RecipientInformation recipientInformation = (RecipientInformation) it2.next();
            RecipientId rid = recipientInformation.getRID();
            ArrayList arrayList = (ArrayList) this.table.get(rid);
            if (arrayList == null) {
                arrayList = new ArrayList(1);
                this.table.put(rid, arrayList);
            }
            arrayList.add(recipientInformation);
        }
        this.all = new ArrayList(collection);
    }

    public RecipientInformation get(RecipientId recipientId) {
        Collection recipients = getRecipients(recipientId);
        if (recipients.size() == 0) {
            return null;
        }
        return (RecipientInformation) recipients.iterator().next();
    }

    public Collection getRecipients() {
        return new ArrayList(this.all);
    }

    public int size() {
        return this.all.size();
    }

    public Collection getRecipients(RecipientId recipientId) {
        if (recipientId instanceof KeyTransRecipientId) {
            KeyTransRecipientId keyTransRecipientId = (KeyTransRecipientId) recipientId;
            byte[] subjectKeyIdentifier = keyTransRecipientId.getSubjectKeyIdentifier();
            if (keyTransRecipientId.getIssuer() != null && subjectKeyIdentifier != null) {
                ArrayList arrayList = new ArrayList();
                Collection recipients = getRecipients(new KeyTransRecipientId(keyTransRecipientId.getIssuer(), keyTransRecipientId.getSerialNumber()));
                if (recipients != null) {
                    arrayList.addAll(recipients);
                }
                Collection recipients2 = getRecipients(new KeyTransRecipientId(subjectKeyIdentifier));
                if (recipients2 != null) {
                    arrayList.addAll(recipients2);
                }
                return arrayList;
            }
            ArrayList arrayList2 = (ArrayList) this.table.get(recipientId);
            return arrayList2 == null ? new ArrayList() : new ArrayList(arrayList2);
        }
        ArrayList arrayList3 = (ArrayList) this.table.get(recipientId);
        return arrayList3 == null ? new ArrayList() : new ArrayList(arrayList3);
    }
}
