package amazon.speech.simclient.context;

import amazon.speech.simclient.common.SimClientServiceHandle;
import android.content.Context;
/* loaded from: classes.dex */
public class ContextClientHandle extends SimClientServiceHandle<ContextClient> {
    public ContextClientHandle() {
        super(ContextClient.class);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // amazon.speech.simclient.common.SimClientServiceHandle
    public boolean serviceExists(Context context) {
        return ContextClient.isContextServiceAvailable(context);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // amazon.speech.simclient.common.SimClientServiceHandle
    /* renamed from: createClient  reason: collision with other method in class */
    public ContextClient mo50createClient(Context context) {
        return new ContextClient(context);
    }
}
