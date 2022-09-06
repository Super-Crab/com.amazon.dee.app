package amazon.speech.simclient.capability;

import amazon.speech.simclient.common.SimClientServiceHandle;
import android.content.Context;
/* loaded from: classes.dex */
public class CapabilityClientHandle extends SimClientServiceHandle<CapabilityClient> {
    public CapabilityClientHandle() {
        super(CapabilityClient.class);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // amazon.speech.simclient.common.SimClientServiceHandle
    public boolean serviceExists(Context context) {
        return CapabilityClient.isCapabilityServiceAvailable(context);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // amazon.speech.simclient.common.SimClientServiceHandle
    /* renamed from: createClient */
    public CapabilityClient mo50createClient(Context context) {
        return new CapabilityClient(context);
    }
}
