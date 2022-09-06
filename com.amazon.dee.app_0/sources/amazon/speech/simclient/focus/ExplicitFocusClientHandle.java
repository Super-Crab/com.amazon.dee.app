package amazon.speech.simclient.focus;

import amazon.speech.simclient.common.SimClientServiceHandle;
import android.content.Context;
/* loaded from: classes.dex */
public class ExplicitFocusClientHandle extends SimClientServiceHandle<ExplicitFocusClient> {
    public ExplicitFocusClientHandle() {
        super(ExplicitFocusClient.class);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // amazon.speech.simclient.common.SimClientServiceHandle
    public boolean serviceExists(Context context) {
        return ExplicitFocusClient.isFocusServiceAvailable(context);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // amazon.speech.simclient.common.SimClientServiceHandle
    /* renamed from: createClient  reason: collision with other method in class */
    public ExplicitFocusClient mo50createClient(Context context) {
        return new ExplicitFocusClient(context);
    }
}
