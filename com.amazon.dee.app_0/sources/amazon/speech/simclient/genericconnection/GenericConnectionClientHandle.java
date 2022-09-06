package amazon.speech.simclient.genericconnection;

import amazon.speech.simclient.common.SimClientServiceHandle;
import android.content.Context;
/* loaded from: classes.dex */
public class GenericConnectionClientHandle extends SimClientServiceHandle<GenericConnectionClient> {
    public GenericConnectionClientHandle() {
        super(GenericConnectionClient.class);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // amazon.speech.simclient.common.SimClientServiceHandle
    public boolean serviceExists(Context context) {
        return GenericConnectionClient.isGenericConnectionServiceAvailable(context);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // amazon.speech.simclient.common.SimClientServiceHandle
    /* renamed from: createClient  reason: collision with other method in class */
    public GenericConnectionClient mo50createClient(Context context) {
        return new GenericConnectionClient(context);
    }
}
