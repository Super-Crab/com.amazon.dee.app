package com.amazon.alexa.api;

import android.os.RemoteException;
import com.amazon.alexa.api.AlexaDialogControllerProxy;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.utils.ApiThreadHelper;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.UUID;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class ak extends AlexaDialogControllerProxy.Stub {
    private static final String a = ak.class.getSimpleName();
    private final String b;
    private final AlexaDialogController c;
    @Nullable
    private final AlexaConnectionWithoutLeaderSelection d;
    @Nullable
    private final AlexaConnection e;
    private String f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ak(AlexaDialogController alexaDialogController, AlexaConnection alexaConnection) {
        this.b = UUID.randomUUID().toString();
        this.c = alexaDialogController;
        this.e = alexaConnection;
        this.d = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ak(AlexaDialogController alexaDialogController, AlexaConnectionWithoutLeaderSelection alexaConnectionWithoutLeaderSelection) {
        this.b = UUID.randomUUID().toString();
        this.c = alexaDialogController;
        this.d = alexaConnectionWithoutLeaderSelection;
        this.e = null;
    }

    private void b() {
        AlexaConnection alexaConnection = this.e;
        if (alexaConnection != null) {
            alexaConnection.removeProxy(this.c);
        }
        AlexaConnectionWithoutLeaderSelection alexaConnectionWithoutLeaderSelection = this.d;
        if (alexaConnectionWithoutLeaderSelection != null) {
            alexaConnectionWithoutLeaderSelection.removeProxy(this.c);
        }
    }

    @Override // com.amazon.alexa.api.AlexaDialogControllerProxy
    public String getDialogIdentifier() throws RemoteException {
        return this.b;
    }

    @Override // com.amazon.alexa.api.AlexaDialogControllerProxy
    public String getDialogTurnIdentifier() throws RemoteException {
        return this.f;
    }

    @Override // com.amazon.alexa.api.AlexaDialogControllerProxy
    public void onDialogFinished() throws RemoteException {
        ApiThreadHelper.runOnUiThread(new Runnable() { // from class: com.amazon.alexa.api.ak.6
            @Override // java.lang.Runnable
            public void run() {
                String unused = ak.a;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onDialogFinished ");
                outline107.append(ak.this.b);
                outline107.toString();
                ak.this.c.onDialogFinished();
            }
        });
        b();
    }

    @Override // com.amazon.alexa.api.AlexaDialogControllerProxy
    public void onDialogStarted() throws RemoteException {
        ApiThreadHelper.runOnUiThread(new Runnable() { // from class: com.amazon.alexa.api.ak.5
            @Override // java.lang.Runnable
            public void run() {
                String unused = ak.a;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onDialogStarted ");
                outline107.append(ak.this.b);
                outline107.toString();
                ak.this.c.onDialogStarted();
            }
        });
    }

    @Override // com.amazon.alexa.api.AlexaDialogControllerProxy
    public void onDialogTurnFinished() throws RemoteException {
        ApiThreadHelper.runOnUiThread(new Runnable() { // from class: com.amazon.alexa.api.ak.4
            @Override // java.lang.Runnable
            public void run() {
                String unused = ak.a;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onDialogTurnFinished ");
                outline107.append(ak.this.b);
                outline107.toString();
                ak.this.c.onDialogTurnFinished();
            }
        });
    }

    @Override // com.amazon.alexa.api.AlexaDialogControllerProxy
    public void onDialogTurnStarted() throws RemoteException {
        ApiThreadHelper.runOnUiThread(new Runnable() { // from class: com.amazon.alexa.api.ak.3
            @Override // java.lang.Runnable
            public void run() {
                String unused = ak.a;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onDialogTurnStarted ");
                outline107.append(ak.this.b);
                outline107.toString();
                ak.this.c.onDialogTurnStarted();
            }
        });
    }

    @Override // com.amazon.alexa.api.AlexaDialogControllerProxy
    public void startRecordingNextDialogTurn(final String str) throws RemoteException {
        this.f = str;
        ApiThreadHelper.runOnUiThread(new Runnable() { // from class: com.amazon.alexa.api.ak.1
            @Override // java.lang.Runnable
            public void run() {
                String unused = ak.a;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("startRecording ");
                outline107.append(ak.this.b);
                outline107.append(" ");
                outline107.append(str);
                outline107.toString();
                ak.this.c.startRecordingNextDialogTurn();
            }
        });
    }

    @Override // com.amazon.alexa.api.AlexaDialogControllerProxy
    public void stopRecording() throws RemoteException {
        ApiThreadHelper.runOnUiThread(new Runnable() { // from class: com.amazon.alexa.api.ak.2
            @Override // java.lang.Runnable
            public void run() {
                String unused = ak.a;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("stopRecording ");
                outline107.append(ak.this.b);
                outline107.toString();
                ak.this.c.stopRecording();
            }
        });
    }
}
