package com.amazon.identity.auth.device;

import android.content.pm.PackageManager;
import com.amazon.identity.auth.device.fd;
import com.amazon.identity.auth.device.ff;
import com.amazon.identity.auth.device.framework.MAPSmsReceiver;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class fe {
    private MAPSmsReceiver mk;
    private a<fd> ml;
    private boolean mm;
    private ed o;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public interface a<T> {
        void f(T t);
    }

    @Deprecated
    public fe() {
    }

    private void a(fd fdVar) {
        a<fd> aVar = this.ml;
        if (aVar == null) {
            io.w("SmsRetrieverManager", "Got null consumer callback, there may be errors when consuming sms");
            return;
        }
        aVar.f(fdVar);
        this.ml = null;
        MAPSmsReceiver mAPSmsReceiver = this.mk;
        if (mAPSmsReceiver == null || !this.mm) {
            return;
        }
        mAPSmsReceiver.L(this.o);
    }

    private String eu() {
        try {
            String a2 = fl.a(ek.a(this.o.getPackageName(), 64, this.o.getPackageManager()));
            io.i("SmsRetrieverManager", "appSmsHash =  ".concat(String.valueOf(a2)));
            return a2;
        } catch (PackageManager.NameNotFoundException unused) {
            io.e("SmsRetrieverManager", "NameNotFoundException when getting packageInfo for appSmsHash");
            return "";
        }
    }

    public void b(a<fd> aVar) {
        this.ml = aVar;
        MAPSmsReceiver mAPSmsReceiver = this.mk;
        if (mAPSmsReceiver != null && this.mm) {
            mAPSmsReceiver.a(this.o, this);
        } else {
            a(a(false, ""));
        }
    }

    public void bL(String str) {
        a(a(true, str));
    }

    public fe(ed edVar, MAPSmsReceiver mAPSmsReceiver) {
        this.o = edVar;
        this.mk = mAPSmsReceiver;
        this.ml = null;
        MAPSmsReceiver mAPSmsReceiver2 = this.mk;
        if (mAPSmsReceiver2 != null) {
            this.mm = mAPSmsReceiver2.H(this.o);
        }
        io.i("SmsRetrieverManager", "SmsRetriever supporting: " + this.mm);
    }

    public void a(a<ff> aVar) {
        ff.a aVar2 = new ff.a();
        aVar2.g(this.mm);
        aVar2.bM(this.mm ? eu() : "");
        aVar.f(aVar2.ev());
    }

    private fd a(boolean z, String str) {
        fd.a aVar = new fd.a();
        aVar.f(z);
        if (!z) {
            str = "";
        }
        aVar.bK(str);
        return aVar.et();
    }
}
