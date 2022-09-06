package com.amazon.alexa.api;

import android.os.RemoteException;
import com.amazon.alexa.api.AlexaDialogControllerProxyV2;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.utils.ApiThreadHelper;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.UUID;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class al extends AlexaDialogControllerProxyV2.Stub {
    private static final String a = al.class.getSimpleName();
    private final String b;
    private final AlexaDialogControllerV2 c;
    @Nullable
    private final AlexaConnectionWithoutLeaderSelection d;
    @Nullable
    private final AlexaConnection e;
    private String f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public al(AlexaDialogControllerV2 alexaDialogControllerV2, AlexaConnection alexaConnection) {
        this.b = UUID.randomUUID().toString();
        this.c = alexaDialogControllerV2;
        this.e = alexaConnection;
        this.d = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public al(AlexaDialogControllerV2 alexaDialogControllerV2, AlexaConnectionWithoutLeaderSelection alexaConnectionWithoutLeaderSelection) {
        this.b = UUID.randomUUID().toString();
        this.c = alexaDialogControllerV2;
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

    @Override // com.amazon.alexa.api.AlexaDialogControllerProxyV2
    public String getDialogIdentifier() throws RemoteException {
        return this.b;
    }

    @Override // com.amazon.alexa.api.AlexaDialogControllerProxyV2
    public String getDialogTurnIdentifier() throws RemoteException {
        return this.f;
    }

    @Override // com.amazon.alexa.api.AlexaDialogControllerProxyV2
    public void onDialogFinished() throws RemoteException {
        ApiThreadHelper.runOnUiThread(new Runnable() { // from class: com.amazon.alexa.api.al.6
            @Override // java.lang.Runnable
            public void run() {
                String unused = al.a;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onDialogFinished ");
                outline107.append(al.this.b);
                outline107.toString();
                al.this.c.onDialogFinished();
            }
        });
        b();
    }

    @Override // com.amazon.alexa.api.AlexaDialogControllerProxyV2
    public void onDialogStarted() throws RemoteException {
        ApiThreadHelper.runOnUiThread(new Runnable() { // from class: com.amazon.alexa.api.al.5
            @Override // java.lang.Runnable
            public void run() {
                String unused = al.a;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onDialogStarted ");
                outline107.append(al.this.b);
                outline107.toString();
                al.this.c.onDialogStarted();
            }
        });
    }

    @Override // com.amazon.alexa.api.AlexaDialogControllerProxyV2
    public void onDialogTurnFinished() throws RemoteException {
        ApiThreadHelper.runOnUiThread(new Runnable() { // from class: com.amazon.alexa.api.al.4
            @Override // java.lang.Runnable
            public void run() {
                String unused = al.a;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onDialogTurnFinished ");
                outline107.append(al.this.b);
                outline107.toString();
                al.this.c.onDialogTurnFinished();
            }
        });
    }

    @Override // com.amazon.alexa.api.AlexaDialogControllerProxyV2
    public void onDialogTurnStarted(final String str) throws RemoteException {
        ApiThreadHelper.runOnUiThread(new Runnable() { // from class: com.amazon.alexa.api.al.3
            @Override // java.lang.Runnable
            public void run() {
                String unused = al.a;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onDialogTurnStarted ");
                outline107.append(al.this.b);
                outline107.append(" for request: ");
                outline107.append(str);
                outline107.toString();
                al.this.c.onDialogTurnStarted(str);
            }
        });
    }

    @Override // com.amazon.alexa.api.AlexaDialogControllerProxyV2
    public void startRecordingNextDialogTurn(final String str) throws RemoteException {
        this.f = str;
        ApiThreadHelper.runOnUiThread(new Runnable() { // from class: com.amazon.alexa.api.al.1
            @Override // java.lang.Runnable
            public void run() {
                String unused = al.a;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("startRecording ");
                outline107.append(al.this.b);
                outline107.append(" ");
                outline107.append(str);
                outline107.toString();
                al.this.c.startRecordingNextDialogTurn();
            }
        });
    }

    @Override // com.amazon.alexa.api.AlexaDialogControllerProxyV2
    public void stopRecording() throws RemoteException {
        ApiThreadHelper.runOnUiThread(new Runnable() { // from class: com.amazon.alexa.api.al.2
            @Override // java.lang.Runnable
            public void run() {
                String unused = al.a;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("stopRecording ");
                outline107.append(al.this.b);
                outline107.toString();
                al.this.c.stopRecording();
            }
        });
    }
}
