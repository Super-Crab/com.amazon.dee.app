package amazon.speech.simclient;

import amazon.speech.model.DirectiveEnvelope;
import amazon.speech.model.Event;
import java.util.List;
/* loaded from: classes.dex */
public interface ISIMEventCallback {
    void onError(Event event, int i);

    void onResponse(Event event, List<DirectiveEnvelope> list);
}
