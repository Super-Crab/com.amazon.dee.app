package com.amazon.alexa;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import com.amazon.alexa.NEv;
import com.amazon.alexa.TTH;
import com.amazon.alexa.ZAZ;
import com.amazon.alexa.api.ApiCallFailure;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.client.core.messages.Message;
import com.amazon.alexa.client.crashreporting.CrashReporter;
import com.amazon.alexa.kbp;
import com.amazon.alexa.utils.validation.Preconditions;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import okhttp3.Headers;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.commons.fileupload.MultipartStream;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
/* compiled from: ResponseHandler.java */
/* loaded from: classes.dex */
public class nmS {
    public static final Pattern BIo = Pattern.compile("boundary=([^;]+)", 2);
    public static final Pattern zQM = Pattern.compile("Content-ID: <([^>]+)>", 2);
    public static final String zZm = "nmS";
    public final shl JTe;
    public final DYu LPk;
    public final Set<DialogRequestIdentifier> Mlj;
    public final SmC Qle;
    public final SbM dMe;
    public final Gson jiA;
    public final ZPU lOf;
    public final qxC yPL;
    public final AlexaClientEventBus zyO;
    public final CrashReporter zzR;

    public nmS(Gson gson, SmC smC, shl shlVar, DYu dYu, qxC qxc, AlexaClientEventBus alexaClientEventBus, CrashReporter crashReporter, ZPU zpu, SbM sbM) {
        HashSet hashSet = new HashSet();
        this.jiA = gson;
        this.Qle = smC;
        this.JTe = shlVar;
        this.zyO = alexaClientEventBus;
        this.LPk = dYu;
        this.yPL = qxc;
        this.lOf = zpu;
        this.zzR = crashReporter;
        this.Mlj = hashSet;
        alexaClientEventBus.zZm(this);
        this.dMe = sbM;
    }

    public int BIo(ZAZ.zZm zzm, Response response) {
        return response.code();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [boolean] */
    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onResponse(ZAZ zaz) {
        UuN uuN = (UuN) zaz;
        Response response = uuN.zQM;
        ZAZ.zZm zzm = uuN.BIo;
        eOP eop = uuN.zyO;
        Headers headers = response.headers();
        for (String str : headers.names()) {
            StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str, RealTimeTextConstants.COLON_SPACE);
            outline113.append(headers.get(str));
            outline113.toString();
            if ("x-amzn-requestid".equalsIgnoreCase(str)) {
                RrI zZm2 = zZm(response);
                String str2 = headers.get(str);
                String BIo2 = this.LPk.BIo(zZm2);
                if (BIo2 != null && str2 != null) {
                    Log.i(zZm, String.format("Event %s x-amzn-requestid: %s", BIo2, str2));
                } else {
                    GeneratedOutlineSupport1.outline163("Event name does not exist for x-amzn-requestid: ", str2, zZm);
                }
            }
        }
        int BIo3 = BIo(zzm, response);
        ?? zQM2 = zQM(zzm, response);
        Exception exc = null;
        try {
            try {
                try {
                    ResponseBody body = response.body();
                    try {
                        if (204 == BIo3) {
                            zZm(zzm, response);
                            if (body != null) {
                                body.close();
                            }
                            zZm(response, BIo3, true, zzm, null);
                        } else if (zQM2 == 0) {
                            zZm(response, zzm);
                            if (body != null) {
                                body.close();
                            }
                            zZm(response, BIo3, false, zzm, null);
                        } else if (body == null) {
                            Log.w(zZm, "Response body was null, which should never happen");
                            if (body != null) {
                                body.close();
                            }
                            zZm(response, BIo3, true, zzm, null);
                        } else {
                            zZm(zzm, response);
                            boolean zZm3 = zZm(zzm, response, body);
                            body.close();
                            zZm(response, BIo3, zZm3, zzm, null);
                        }
                    } catch (Throwable th) {
                        try {
                            throw th;
                        } catch (Throwable th2) {
                            if (body != null) {
                                try {
                                    body.close();
                                } catch (Throwable th3) {
                                    th.addSuppressed(th3);
                                }
                            }
                            throw th2;
                        }
                    }
                } catch (MultipartStream.MalformedStreamException e) {
                    e = e;
                    if (!((knl) zzm).zZm) {
                        Log.e(zZm, e.getMessage(), e);
                    }
                    bij bijVar = bij.RESPONSE_PARSING_ERROR_MULTIPART;
                    zZm(eop, response, e);
                    zZm(response, bijVar);
                    zQM(response);
                    this.zyO.zyO(TTH.zZm("Received stream is malformed"));
                    BIo(response);
                    if (zZm(response).BIo()) {
                        this.zzR.notifyHandledException(e, 0.05d);
                    }
                    zZm(response, BIo3, false, zzm, e);
                }
            } catch (IOException e2) {
                e = e2;
                zZm(response, e, eop);
                if (zZm(response).BIo()) {
                    this.zzR.notifyHandledException(e, 0.05d);
                }
                zZm(response, BIo3, false, zzm, e);
            } catch (Throwable th4) {
                th = th4;
                zZm(response, BIo3, false, zzm, exc);
                throw th;
            }
        } catch (Throwable th5) {
            th = th5;
            exc = zQM2;
            zZm(response, BIo3, false, zzm, exc);
            throw th;
        }
    }

    public boolean zQM(ZAZ.zZm zzm, Response response) {
        return response.isSuccessful();
    }

    public void zZm(ZAZ.zZm zzm, Response response) throws IOException {
    }

    public final void zZm(Response response, IOException iOException, eOP eop) {
        bij bijVar = bij.RESPONSE_PARSING_ERROR;
        zZm(eop, response, iOException);
        Log.e(zZm, iOException.getMessage(), iOException);
        if (zZm(response).BIo()) {
            DialogRequestIdentifier dialogRequestIdentifier = ((Fkl) zZm(response)).BIo;
            this.yPL.zZm(true, dialogRequestIdentifier, null);
            boolean zQM2 = this.LPk.zQM(zZm(response));
            if (qxC.zZm(iOException)) {
                this.zyO.zyO(kbp.zQM.zZm(dialogRequestIdentifier, bij.NETWORK_UNAVAILABLE, zQM2));
            } else {
                AlexaClientEventBus alexaClientEventBus = this.zyO;
                bijVar.zZm(zQM2);
                alexaClientEventBus.zyO(new gMf(dialogRequestIdentifier, bijVar, null, zQM2));
            }
        }
        zQM(response);
        this.zyO.zyO(TTH.zZm("IOException thrown while parsing response"));
        BIo(response);
    }

    public final void BIo(Response response) {
        if (!zZm(response).BIo()) {
            return;
        }
        this.zyO.zyO(cXw.zZm(this.LPk.zQM(zZm(response))));
    }

    public final void zQM(Response response) {
        if (zZm(response).BIo()) {
            this.zyO.zyO(kOA.BIo());
        }
    }

    public final void BIo(@Nullable Message message, ZAZ.zZm zzm) {
        if (message != null) {
            this.zyO.zyO(new mob(message, zzm));
        }
    }

    public final void zZm(eOP eop, Response response, @Nullable Exception exc) {
        if (eOP.zZm != eop) {
            String message = exc == null ? null : exc.getMessage();
            if (response.isSuccessful()) {
                this.zyO.zyO(NEv.zZm.zZm(eop, ApiCallFailure.NetworkFailure.create(message, exc)));
            } else {
                this.zyO.zyO(NEv.zZm.zZm(eop, ApiCallFailure.ServerErrorFailure.create(message, exc, Integer.valueOf(response.code()))));
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0037  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean zZm(com.amazon.alexa.ZAZ.zZm r6, okhttp3.Response r7, okhttp3.ResponseBody r8) throws java.io.IOException {
        /*
            r5 = this;
            okhttp3.Headers r0 = r7.headers()
            java.lang.String r1 = "content-type"
            java.lang.String r0 = r0.get(r1)
            java.lang.String r1 = ""
            r2 = 1
            if (r0 == 0) goto L20
            java.util.regex.Pattern r3 = com.amazon.alexa.nmS.BIo
            java.util.regex.Matcher r0 = r3.matcher(r0)
            boolean r3 = r0.find()
            if (r3 == 0) goto L20
            java.lang.String r0 = r0.group(r2)
            goto L21
        L20:
            r0 = r1
        L21:
            boolean r3 = r0.isEmpty()
            if (r3 == 0) goto L37
            java.lang.String r8 = r8.string()
            com.amazon.alexa.client.core.messages.Message r0 = r5.zZm(r6, r8)
            r5.zZm(r0, r7, r8)
            r5.BIo(r0, r6)
            r6 = 0
            return r6
        L37:
            com.amazon.alexa.SmC r3 = r5.Qle
            java.io.InputStream r8 = r8.byteStream()
            com.amazon.alexa.wpU r8 = r3.zZm(r8, r0)
            org.apache.commons.fileupload.MultipartStream r0 = r8.zZm
            boolean r0 = r0.skipPreamble()
        L47:
            if (r0 == 0) goto Lb4
            com.amazon.alexa.wpU$zZm r0 = r8.zZm()
            org.apache.commons.fileupload.MultipartStream r3 = r0.zZm
            java.lang.String r3 = r3.readHeaders()
            java.util.regex.Pattern r4 = com.amazon.alexa.nmS.zQM
            java.util.regex.Matcher r3 = r4.matcher(r3)
            boolean r4 = r3.find()
            if (r4 == 0) goto L64
            java.lang.String r3 = r3.group(r2)
            goto L65
        L64:
            r3 = r1
        L65:
            boolean r4 = r3.isEmpty()
            if (r4 == 0) goto L7d
            java.lang.String r0 = r0.zZm()
            com.amazon.alexa.client.core.messages.Message r3 = r5.zZm(r6, r0)
            r5.zZm(r3, r7, r0)
            r5.zZm(r3, r6)
            r5.BIo(r3, r6)
            goto L9f
        L7d:
            com.amazon.alexa.shl r4 = r5.JTe
            com.amazon.alexa.cIy r3 = com.amazon.alexa.cIy.zZm(r3)
            com.amazon.alexa.Aml r3 = r4.zZm(r3)
            java.io.OutputStream r4 = r3.getOutputStream()
            org.apache.commons.fileupload.MultipartStream r0 = r0.zZm     // Catch: java.lang.Throwable -> L91 java.io.IOException -> L93
            r0.readBodyData(r4)     // Catch: java.lang.Throwable -> L91 java.io.IOException -> L93
            goto L9a
        L91:
            r6 = move-exception
            goto La7
        L93:
            r0 = move-exception
            boolean r3 = r3.isClosed()     // Catch: java.lang.Throwable -> L91
            if (r3 == 0) goto La6
        L9a:
            if (r4 == 0) goto L9f
            r4.close()
        L9f:
            org.apache.commons.fileupload.MultipartStream r0 = r8.zZm
            boolean r0 = r0.readBoundary()
            goto L47
        La6:
            throw r0     // Catch: java.lang.Throwable -> L91
        La7:
            throw r6     // Catch: java.lang.Throwable -> La8
        La8:
            r7 = move-exception
            if (r4 == 0) goto Lb3
            r4.close()     // Catch: java.lang.Throwable -> Laf
            goto Lb3
        Laf:
            r8 = move-exception
            r6.addSuppressed(r8)
        Lb3:
            throw r7
        Lb4:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.nmS.zZm(com.amazon.alexa.ZAZ$zZm, okhttp3.Response, okhttp3.ResponseBody):boolean");
    }

    public final void zZm(@Nullable Message message, ZAZ.zZm zzm) {
        if (message == null || !((knl) zzm).zZm) {
            return;
        }
        if (AvsApiConstants.zZm(AvsApiConstants.InteractionModel.zZm, AvsApiConstants.InteractionModel.Directives.RequestProcessingStarted.zZm, message)) {
            synchronized (this.Mlj) {
                this.Mlj.add(message.getDialogRequestIdentifier());
            }
        } else if (!AvsApiConstants.zZm(AvsApiConstants.InteractionModel.zZm, AvsApiConstants.InteractionModel.Directives.RequestProcessingCompleted.zZm, message)) {
        } else {
            DialogRequestIdentifier dialogRequestIdentifier = message.getDialogRequestIdentifier();
            RrI zZm2 = RrI.zZm(dialogRequestIdentifier);
            synchronized (this.Mlj) {
                this.Mlj.remove(dialogRequestIdentifier);
            }
            this.LPk.zZm(zZm2, true, (Integer) null, (Exception) null);
        }
    }

    public final void zZm(@Nullable Message message, Response response, @Nullable String str) {
        RrI zZm2;
        if (message != null) {
            if (message.hasDialogRequestIdentifier()) {
                zZm2 = RrI.zZm(message.getDialogRequestIdentifier());
            } else {
                zZm2 = zZm(response);
            }
            this.LPk.zZm(zZm2, message, str);
        }
    }

    public RrI zZm(Response response) {
        Object tag = response.request().tag();
        if (tag instanceof RrI) {
            return (RrI) tag;
        }
        Log.e(zZm, "Unable to determine request identifier");
        return RrI.zZm();
    }

    public final void zZm(Response response, bij bijVar) {
        if (zZm(response).BIo()) {
            DialogRequestIdentifier dialogRequestIdentifier = ((Fkl) zZm(response)).BIo;
            boolean zQM2 = this.LPk.zQM(zZm(response));
            AlexaClientEventBus alexaClientEventBus = this.zyO;
            bijVar.zZm(zQM2);
            alexaClientEventBus.zyO(new gMf(dialogRequestIdentifier, bijVar, null, zQM2));
        }
    }

    public final void zZm(Response response, int i, boolean z, ZAZ.zZm zzm, @Nullable Exception exc) {
        knl knlVar = (knl) zzm;
        boolean z2 = knlVar.BIo;
        boolean z3 = knlVar.zZm;
        RrI zZm2 = zZm(response);
        boolean zQM2 = this.LPk.zQM(zZm(response));
        if (zZm2.BIo()) {
            if (!this.LPk.zyO(zZm2)) {
                this.zyO.zyO(ISQ.BIo());
                synchronized (this.Mlj) {
                    if (!this.Mlj.contains(((Fkl) zZm2).BIo)) {
                        this.LPk.zZm(zZm2, z, Integer.valueOf(i), exc);
                    }
                }
            } else {
                this.LPk.zZm(zZm2, z, Integer.valueOf(i), exc);
            }
            DialogRequestIdentifier dialogRequestIdentifier = ((Fkl) zZm2).BIo;
            if (dialogRequestIdentifier != null) {
                AlexaClientEventBus alexaClientEventBus = this.zyO;
                YOj yOj = YOj.PROCESSED;
                Preconditions.notNull(dialogRequestIdentifier, "dialogRequestIdentifier is null");
                alexaClientEventBus.zyO(new jcN(null, dialogRequestIdentifier, yOj));
                if (z) {
                    this.zyO.zyO(new C0200dqd(dialogRequestIdentifier, zQM2));
                }
            }
        } else {
            if (z3) {
                zZm();
            }
            this.LPk.zZm(zZm2, z, Integer.valueOf(i), exc);
        }
        if (z2) {
            ((VBC) this.lOf).zZm(zZm2);
        }
    }

    public final void zZm() {
        synchronized (this.Mlj) {
            for (DialogRequestIdentifier dialogRequestIdentifier : this.Mlj) {
                this.LPk.zZm(RrI.zZm(dialogRequestIdentifier), false, (Integer) null, (Exception) null);
            }
        }
    }

    public final void zZm(Response response, ZAZ.zZm zzm) throws IOException {
        StringBuilder zZm2 = C0179Pya.zZm("HTTP response code: ");
        zZm2.append(response.code());
        zZm2.toString();
        this.zyO.zyO(new ies(response.code()));
        ResponseBody body = response.body();
        Message zZm3 = body != null ? zZm(zzm, body.string()) : null;
        boolean BIo2 = zZm(response).BIo();
        int code = response.code();
        if (code == 400) {
            zZm(response, bij.REQUEST_PARSING_ERROR);
            BIo(zZm3, zzm);
        } else if (code == 403) {
            this.zyO.zyO(new Kal());
            zZm(response, bij.AUTHORIZATION_ERROR);
            BIo(zZm3, zzm);
        } else if (code == 429) {
            zZm(response, bij.AVS_ERROR);
            BIo(zZm3, zzm);
        } else if (code != 500 && code != 503) {
            zZm(response, bij.AVS_ERROR);
            zQM(response);
        } else {
            if (BIo2) {
                this.zyO.zyO(cXw.zZm(this.LPk.zQM(zZm(response))));
            }
            BIo2 = false;
            zZm(response, bij.AVS_ERROR);
            BIo(zZm3, zzm);
        }
        if (BIo2) {
            this.zyO.zyO(new OnO());
        }
    }

    @Nullable
    public final Message zZm(ZAZ.zZm zzm, @Nullable String str) {
        StringBuilder zZm2 = C0179Pya.zZm("Raw response");
        zZm2.append(((knl) zzm).zZm ? " (downchannel): " : RealTimeTextConstants.COLON_SPACE);
        zZm2.append(str);
        zZm2.toString();
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            Message zZm3 = this.dMe.zZm((Message) this.jiA.fromJson(str, (Class<Object>) Message.class));
            if (zZm3 == null) {
                String str2 = zZm;
                StringBuilder sb = new StringBuilder();
                sb.append("Unable to parse json into message: ");
                sb.append(str);
                Log.e(str2, sb.toString());
            } else {
                String str3 = zZm;
                Object[] objArr = new Object[3];
                objArr[0] = ((knl) zzm).zZm ? " (downchannel)" : "";
                objArr[1] = zZm3.getHeader().getName().getValue();
                objArr[2] = zZm3.getHeader().getNamespace().getValue();
                Log.i(str3, String.format("Message received%s: %s in %s namespace", objArr));
            }
            return zZm3;
        } catch (JsonParseException e) {
            Log.e(zZm, e.getMessage(), e);
            this.zyO.zyO(TTH.zZm(TTH.zZm.UNSUPPORTED_OPERATION, e.getMessage(), str, false, DialogRequestIdentifier.NONE));
            return null;
        }
    }
}
