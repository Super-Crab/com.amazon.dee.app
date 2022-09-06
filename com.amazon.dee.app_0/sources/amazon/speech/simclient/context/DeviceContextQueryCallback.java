package amazon.speech.simclient.context;

import amazon.speech.simclient.context.IDeviceContextQueryCallback;
/* loaded from: classes.dex */
public abstract class DeviceContextQueryCallback extends IDeviceContextQueryCallback.Stub {
    @Override // amazon.speech.simclient.context.IDeviceContextQueryCallback
    public abstract void onQueryFinished(String str);
}
