package org.apache.logging.log4j.message;
/* loaded from: classes4.dex */
public interface FlowMessageFactory {
    EntryMessage newEntryMessage(Message message);

    ExitMessage newExitMessage(Object obj, EntryMessage entryMessage);

    ExitMessage newExitMessage(Object obj, Message message);

    ExitMessage newExitMessage(EntryMessage entryMessage);
}
