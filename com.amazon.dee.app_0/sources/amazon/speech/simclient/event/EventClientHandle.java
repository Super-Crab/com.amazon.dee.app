package amazon.speech.simclient.event;

import amazon.speech.simclient.common.SimClientServiceHandle;
import android.content.Context;
/* loaded from: classes.dex */
public class EventClientHandle extends SimClientServiceHandle<EventClient> {
    public EventClientHandle() {
        super(EventClient.class);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // amazon.speech.simclient.common.SimClientServiceHandle
    public boolean serviceExists(Context context) {
        return EventClient.isEventServiceAvailable(context);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // amazon.speech.simclient.common.SimClientServiceHandle
    /* renamed from: createClient  reason: collision with other method in class */
    public EventClient mo50createClient(Context context) {
        return new EventClient(context);
    }
}
