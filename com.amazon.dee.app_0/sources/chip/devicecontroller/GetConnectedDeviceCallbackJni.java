package chip.devicecontroller;
/* loaded from: classes.dex */
public class GetConnectedDeviceCallbackJni {
    private long callbackHandle;
    private GetConnectedDeviceCallback wrappedCallback;

    /* loaded from: classes.dex */
    public interface GetConnectedDeviceCallback {
        void onConnectionFailure(long j, Exception exc);

        void onDeviceConnected(long j);
    }

    public GetConnectedDeviceCallbackJni(GetConnectedDeviceCallback getConnectedDeviceCallback) {
        this.wrappedCallback = getConnectedDeviceCallback;
        this.callbackHandle = newCallback(getConnectedDeviceCallback);
    }

    private native void deleteCallback(long j);

    private native long newCallback(GetConnectedDeviceCallback getConnectedDeviceCallback);

    protected void finalize() throws Throwable {
        super.finalize();
        long j = this.callbackHandle;
        if (j != 0) {
            deleteCallback(j);
            this.callbackHandle = 0L;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getCallbackHandle() {
        return this.callbackHandle;
    }
}
