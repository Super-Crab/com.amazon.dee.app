package amazon.speech.simclient.focus;

import amazon.speech.simclient.common.SimClientServiceHandle;
import android.content.Context;
/* loaded from: classes.dex */
public class ImplicitFocusClientHandle extends SimClientServiceHandle<ImplicitFocusClient> {
    public ImplicitFocusClientHandle() {
        super(ImplicitFocusClient.class);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // amazon.speech.simclient.common.SimClientServiceHandle
    public boolean serviceExists(Context context) {
        return ImplicitFocusClient.isFocusServiceAvailable(context);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // amazon.speech.simclient.common.SimClientServiceHandle
    /* renamed from: createClient  reason: collision with other method in class */
    public ImplicitFocusClient mo50createClient(Context context) {
        return new ImplicitFocusClient(context);
    }
}
