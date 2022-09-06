package com.amazon.alexa;

import com.amazon.alexa.client.core.messages.MessageIdentifier;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Set;
/* compiled from: InteractionsContainer.java */
/* loaded from: classes.dex */
public class BcO {
    public final LinkedHashMap<Bha, Set<MessageIdentifier>> zZm = new LinkedHashMap<>();
    public final LinkedList<Bha> BIo = new LinkedList<>();

    public boolean BIo() {
        return this.BIo.isEmpty();
    }

    public Set<MessageIdentifier> zZm() {
        return this.zZm.get(this.BIo.getFirst());
    }

    public void zZm(Bha bha) {
        this.BIo.remove(bha);
        this.zZm.remove(bha);
    }

    public void zZm(Bha bha, MessageIdentifier messageIdentifier) {
        if (this.zZm.containsKey(bha)) {
            this.zZm.get(bha).add(messageIdentifier);
        }
    }
}
