package amazon.speech.nexusclient;

import amazon.speech.nexusclient.event.INexusEvent;
/* loaded from: classes.dex */
public interface INexusRecorder {
    void flush();

    void record(INexusEvent iNexusEvent);

    void release();
}
