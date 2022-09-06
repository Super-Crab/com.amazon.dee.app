package com.amazon.alexa.api;

import android.os.IBinder;
import android.os.RemoteException;
/* loaded from: classes6.dex */
public class DialogControllerProxyWrapper implements AlexaDialogControllerProxy, AlexaDialogControllerProxyV2 {
    public final AlexaDialogControllerProxy BIo;
    public final AlexaDialogControllerProxyV2 zQM;
    public final AlexaDialogExtras zZm;
    public final WrapperLifecycle zyO;

    /* loaded from: classes6.dex */
    interface WrapperLifecycle {
        void onFinished();

        void onStarted();
    }

    public DialogControllerProxyWrapper(AlexaDialogExtras alexaDialogExtras, AlexaDialogControllerProxy alexaDialogControllerProxy, WrapperLifecycle wrapperLifecycle) {
        this.zZm = alexaDialogExtras;
        this.BIo = alexaDialogControllerProxy;
        this.zQM = null;
        this.zyO = wrapperLifecycle;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        AlexaDialogControllerProxy alexaDialogControllerProxy = this.BIo;
        if (alexaDialogControllerProxy != null) {
            return alexaDialogControllerProxy.asBinder();
        }
        AlexaDialogControllerProxyV2 alexaDialogControllerProxyV2 = this.zQM;
        if (alexaDialogControllerProxyV2 != null) {
            return alexaDialogControllerProxyV2.asBinder();
        }
        throw new IllegalStateException("Both proxy and proxyV2 are null");
    }

    @Override // com.amazon.alexa.api.AlexaDialogControllerProxy
    public String getDialogIdentifier() throws RemoteException {
        AlexaDialogControllerProxy alexaDialogControllerProxy = this.BIo;
        if (alexaDialogControllerProxy != null) {
            return alexaDialogControllerProxy.getDialogIdentifier();
        }
        AlexaDialogControllerProxyV2 alexaDialogControllerProxyV2 = this.zQM;
        if (alexaDialogControllerProxyV2 != null) {
            return alexaDialogControllerProxyV2.getDialogIdentifier();
        }
        throw new IllegalStateException("Both proxy and proxyV2 are null");
    }

    @Override // com.amazon.alexa.api.AlexaDialogControllerProxy
    public String getDialogTurnIdentifier() throws RemoteException {
        AlexaDialogControllerProxy alexaDialogControllerProxy = this.BIo;
        if (alexaDialogControllerProxy != null) {
            return alexaDialogControllerProxy.getDialogTurnIdentifier();
        }
        AlexaDialogControllerProxyV2 alexaDialogControllerProxyV2 = this.zQM;
        if (alexaDialogControllerProxyV2 != null) {
            return alexaDialogControllerProxyV2.getDialogTurnIdentifier();
        }
        throw new IllegalStateException("Both proxy and proxyV2 are null");
    }

    @Override // com.amazon.alexa.api.AlexaDialogControllerProxy
    public void onDialogFinished() throws RemoteException {
        AlexaDialogControllerProxy alexaDialogControllerProxy = this.BIo;
        if (alexaDialogControllerProxy != null) {
            alexaDialogControllerProxy.onDialogFinished();
        } else {
            AlexaDialogControllerProxyV2 alexaDialogControllerProxyV2 = this.zQM;
            if (alexaDialogControllerProxyV2 != null) {
                alexaDialogControllerProxyV2.onDialogFinished();
            }
        }
        this.zyO.onFinished();
    }

    @Override // com.amazon.alexa.api.AlexaDialogControllerProxy
    public void onDialogStarted() throws RemoteException {
        AlexaDialogControllerProxy alexaDialogControllerProxy = this.BIo;
        if (alexaDialogControllerProxy != null) {
            alexaDialogControllerProxy.onDialogStarted();
        } else {
            AlexaDialogControllerProxyV2 alexaDialogControllerProxyV2 = this.zQM;
            if (alexaDialogControllerProxyV2 != null) {
                alexaDialogControllerProxyV2.onDialogStarted();
            }
        }
        this.zyO.onStarted();
    }

    @Override // com.amazon.alexa.api.AlexaDialogControllerProxy
    public void onDialogTurnFinished() throws RemoteException {
        AlexaDialogControllerProxy alexaDialogControllerProxy = this.BIo;
        if (alexaDialogControllerProxy != null) {
            alexaDialogControllerProxy.onDialogTurnFinished();
            return;
        }
        AlexaDialogControllerProxyV2 alexaDialogControllerProxyV2 = this.zQM;
        if (alexaDialogControllerProxyV2 == null) {
            return;
        }
        alexaDialogControllerProxyV2.onDialogTurnFinished();
    }

    @Override // com.amazon.alexa.api.AlexaDialogControllerProxyV2
    public void onDialogTurnStarted(String str) throws RemoteException {
        AlexaDialogControllerProxyV2 alexaDialogControllerProxyV2 = this.zQM;
        if (alexaDialogControllerProxyV2 != null) {
            alexaDialogControllerProxyV2.onDialogTurnStarted(str);
        }
    }

    @Override // com.amazon.alexa.api.AlexaDialogControllerProxy
    public void startRecordingNextDialogTurn(String str) throws RemoteException {
        AlexaDialogControllerProxy alexaDialogControllerProxy = this.BIo;
        if (alexaDialogControllerProxy != null) {
            alexaDialogControllerProxy.startRecordingNextDialogTurn(str);
            return;
        }
        AlexaDialogControllerProxyV2 alexaDialogControllerProxyV2 = this.zQM;
        if (alexaDialogControllerProxyV2 == null) {
            return;
        }
        alexaDialogControllerProxyV2.startRecordingNextDialogTurn(str);
    }

    @Override // com.amazon.alexa.api.AlexaDialogControllerProxy
    public void stopRecording() throws RemoteException {
        AlexaDialogControllerProxy alexaDialogControllerProxy = this.BIo;
        if (alexaDialogControllerProxy != null) {
            alexaDialogControllerProxy.stopRecording();
            return;
        }
        AlexaDialogControllerProxyV2 alexaDialogControllerProxyV2 = this.zQM;
        if (alexaDialogControllerProxyV2 == null) {
            return;
        }
        alexaDialogControllerProxyV2.stopRecording();
    }

    @Override // com.amazon.alexa.api.AlexaDialogControllerProxy
    public void onDialogTurnStarted() throws RemoteException {
        AlexaDialogControllerProxy alexaDialogControllerProxy = this.BIo;
        if (alexaDialogControllerProxy != null) {
            alexaDialogControllerProxy.onDialogTurnStarted();
        }
    }

    public DialogControllerProxyWrapper(AlexaDialogExtras alexaDialogExtras, AlexaDialogControllerProxyV2 alexaDialogControllerProxyV2, WrapperLifecycle wrapperLifecycle) {
        this.zZm = alexaDialogExtras;
        this.BIo = null;
        this.zQM = alexaDialogControllerProxyV2;
        this.zyO = wrapperLifecycle;
    }
}
