package com.amazon.alexa;

import com.amazon.alexa.client.core.messages.MessageIdentifier;
import com.amazon.alexa.client.core.messages.MessageMetadata;
import com.amazon.alexa.client.core.messagesequencer.MessageProcessingCallbacks;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* compiled from: TextResponseSpeechTask.java */
/* loaded from: classes.dex */
public class Lzb {
    public final MessageMetadata BIo;
    public final MessageProcessingCallbacks jiA;
    public final bqj zQM;
    public final C0176Pce zZm;
    public final MessageIdentifier zyO;

    public Lzb(C0176Pce c0176Pce, MessageMetadata messageMetadata, bqj bqjVar, MessageIdentifier messageIdentifier, MessageProcessingCallbacks messageProcessingCallbacks) {
        this.zZm = c0176Pce;
        this.BIo = messageMetadata;
        this.zQM = bqjVar;
        this.zyO = messageIdentifier;
        this.jiA = messageProcessingCallbacks;
    }

    public bqj BIo() {
        return this.zQM;
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("TextResponseSpeechTask{speechToken=");
        zZm.append(this.zZm);
        zZm.append(", messageMetadata=");
        zZm.append(this.BIo);
        zZm.append(", speechItem=");
        zZm.append(this.zQM);
        zZm.append(", messageIdentifier=");
        zZm.append(this.zyO);
        zZm.append(", messageProcessingCallbacks=");
        zZm.append(this.jiA);
        zZm.append(JsonReaderKt.END_OBJ);
        return zZm.toString();
    }

    public MessageIdentifier zZm() {
        return this.zyO;
    }
}
