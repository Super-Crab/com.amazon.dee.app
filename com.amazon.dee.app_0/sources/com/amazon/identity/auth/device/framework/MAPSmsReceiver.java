package com.amazon.identity.auth.device.framework;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebView;
import com.amazon.identity.auth.device.ej;
import com.amazon.identity.auth.device.fe;
import com.amazon.identity.auth.device.io;
import com.amazon.identity.auth.device.iv;
import com.amazon.identity.auth.device.ji;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import java.net.MalformedURLException;
import java.net.URL;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class MAPSmsReceiver extends BroadcastReceiver {
    private ej bR;
    private WebView el;
    private fe es;
    private Boolean ku = null;
    private volatile boolean kv = false;
    private volatile a kw = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class a {
        final MAPSmsReceiver ji;
        volatile SmsRetrieverClient ky;

        a(MAPSmsReceiver mAPSmsReceiver, final Context context) {
            this.ky = null;
            this.ji = mAPSmsReceiver;
            context.registerReceiver(this.ji, new IntentFilter("com.google.android.gms.auth.api.phone.SMS_RETRIEVED"));
            this.ky = SmsRetriever.getClient(context);
            io.dm("MAPSmsReceiver");
            Task startSmsRetriever = this.ky.startSmsRetriever();
            io.i("MAPSmsReceiver", "mSmsRetrieverClient started");
            startSmsRetriever.addOnSuccessListener(new OnSuccessListener<Void>() { // from class: com.amazon.identity.auth.device.framework.MAPSmsReceiver.a.2
                public void dg() {
                    a.this.ji.de();
                }

                @Override // com.google.android.gms.tasks.OnSuccessListener
                public /* synthetic */ void onSuccess(Void r1) {
                    dg();
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: com.amazon.identity.auth.device.framework.MAPSmsReceiver.a.1
                @Override // com.google.android.gms.tasks.OnFailureListener
                public void onFailure(Exception exc) {
                    a.this.ji.a(context, exc);
                }
            });
        }
    }

    public MAPSmsReceiver(ej ejVar, WebView webView) {
        this.bR = ejVar;
        this.el = webView;
        io.i("MAPSmsReceiver", "instance created");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void de() {
        io.i("MAPSmsReceiver", "sms retriever registered");
        this.bR.bA("MOA:RegisterReadSmsReceiver");
    }

    public boolean H(Context context) {
        if (this.ku == null) {
            this.ku = Boolean.valueOf(MAPRuntimePermissionHandler.H(context));
        }
        io.i("MAPSmsReceiver", "sms retriever is supported: " + this.ku);
        return this.ku.booleanValue();
    }

    public synchronized void L(Context context) {
        io.i("MAPSmsReceiver", "unregistering sms retriever: " + this.kw);
        if (context != null && this.kw != null) {
            if (!this.kv) {
                this.bR.bA("MOA:AutoPVCancel");
            }
            a aVar = this.kw;
            context.unregisterReceiver(aVar.ji);
            aVar.ky = null;
            this.kw = null;
            this.es = null;
        }
        io.i("MAPSmsReceiver", "Unregistered MAP sms receiver");
    }

    public synchronized void df() {
        if (this.kv) {
            this.bR.bA("MOA:AutoPVSuccess");
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            return;
        }
        try {
            if (!"com.google.android.gms.auth.api.phone.SMS_RETRIEVED".equals(intent.getAction())) {
                return;
            }
            Bundle extras = intent.getExtras();
            Status status = (Status) extras.get("com.google.android.gms.auth.api.phone.EXTRA_STATUS");
            int statusCode = status.getStatusCode();
            if (statusCode != 0) {
                if (statusCode != 15) {
                    io.w("MAPSmsReceiver", "Receiving message get unknown status:" + status.getStatusCode());
                    return;
                }
                io.w("MAPSmsReceiver", "Receiving message timeout");
                return;
            }
            String str = (String) extras.get("com.google.android.gms.auth.api.phone.EXTRA_SMS_MESSAGE");
            io.i("MAPSmsReceiver", "Receiving message");
            synchronized (this) {
                if (this.es != null) {
                    io.i("MAPSmsReceiver", "Consuming SMS message via SmsRetrieverManager");
                    this.es.bL(str);
                    return;
                }
                final String dv = iv.dv(str);
                io.i("MAPSmsReceiver", "submit code");
                if (dv != null) {
                    this.bR.bA("MOA:GetValidCodeFromSMS");
                }
                try {
                    Integer.parseInt(dv);
                    if (this.el != null) {
                        ji.c(new Runnable() { // from class: com.amazon.identity.auth.device.framework.MAPSmsReceiver.1
                            @Override // java.lang.Runnable
                            public void run() {
                                synchronized (MAPSmsReceiver.this) {
                                    io.i("MAPSmsReceiver", "check if we can submit code: " + MAPSmsReceiver.this.kw);
                                    if (MAPSmsReceiver.this.kw != null) {
                                        io.i("MAPSmsReceiver", "Start submit code");
                                        MAPSmsReceiver.this.kv = true;
                                        WebView webView = MAPSmsReceiver.this.el;
                                        webView.loadUrl("javascript:submitVerificationCode('" + dv + "')");
                                    }
                                }
                            }
                        });
                    }
                } catch (NumberFormatException unused) {
                    io.e("MAPSmsReceiver", "get an non-numeric code");
                }
            }
        } catch (Exception e) {
            ej ejVar = this.bR;
            io.a("MAPSmsReceiver", ejVar, "Unknown exception happened when reading the message.", "UnknownExceptionReadingSMS:" + e.getClass().getName());
        }
    }

    public boolean a(URL url, Context context) {
        String query;
        if (context == null) {
            return false;
        }
        if ((!"/ap/pv".equals(url.getPath()) && !"/ap/cvf/request".equals(url.getPath())) || (query = url.getQuery()) == null || !query.contains("spin=true") || !query.contains("smsretriever=true")) {
            return false;
        }
        return H(context);
    }

    public boolean a(String str, Context context) {
        if (TextUtils.isEmpty(str)) {
            io.e("MAPSmsReceiver", "url is null or empty");
            return false;
        }
        try {
            return a(new URL(str), context);
        } catch (MalformedURLException unused) {
            new StringBuilder("MalformedURLException url=").append((Object) null);
            io.dm("MAPSmsReceiver");
            return false;
        }
    }

    public synchronized void a(Context context, fe feVar) {
        io.i("MAPSmsReceiver", "registering sms retriever: " + this.kw);
        if (context != null && this.kw == null) {
            this.kw = new a(this, context);
            this.es = feVar;
        }
        io.i("MAPSmsReceiver", "registered sms retriever: " + this.kw);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(Context context, Exception exc) {
        io.w("MAPSmsReceiver", "Not able to start sms retriever", exc);
        this.bR.bA("MOA:RegisterReadSmsReceiverFailed");
        L(context);
    }
}
