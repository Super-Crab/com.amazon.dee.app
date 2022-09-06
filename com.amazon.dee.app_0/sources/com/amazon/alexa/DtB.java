package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import java.io.IOException;
import java.util.regex.Pattern;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: AlexaSpeechmarkParser.java */
@Singleton
/* loaded from: classes.dex */
public class DtB implements Vpd {
    public final ILi BIo;
    public final AlexaClientEventBus zQM;
    public final fUD zZm;
    public final Pattern zyO = Pattern.compile(zZm.zQM, 2);

    /* compiled from: AlexaSpeechmarkParser.java */
    /* loaded from: classes.dex */
    static class zZm {
        public static final byte[] zZm = "ID3".getBytes();
        public static final byte[] BIo = {-1, -13};
        public static final String zQM = String.format("\\b%s*%s%s*\\b", "[^a-zA-Z\\d\\s:\\u00C0-\\u00FF]", "alexa", "[^a-zA-Z\\d\\s:\\u00C0-\\u00FF]");
    }

    @Inject
    public DtB(fUD fud, ILi iLi, AlexaClientEventBus alexaClientEventBus) {
        this.zZm = fud;
        this.BIo = iLi;
        this.zQM = alexaClientEventBus;
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x00e6, code lost:
        r0.close();
        r0 = r2.iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00f1, code lost:
        if (r0.hasNext() == false) goto L95;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00f3, code lost:
        r1 = (com.amazon.alexa.client.alexaservice.audio.Speechmark) r0.next();
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0102, code lost:
        if (r1.type.equals("word") == false) goto L94;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0110, code lost:
        if (r13.zyO.matcher(r1.value).find() == false) goto L93;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0112, code lost:
        r13.BIo.zZm(r15, r1.time);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void zZm(com.amazon.alexa.xDc r14, com.amazon.alexa.cIy r15) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 365
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.DtB.zZm(com.amazon.alexa.xDc, com.amazon.alexa.cIy):void");
    }

    public final boolean zZm(byte[] bArr, byte[] bArr2) throws IOException {
        if (bArr.length >= bArr2.length) {
            for (int i = 0; i < bArr2.length; i++) {
                if (bArr2[i] != bArr[i]) {
                    return false;
                }
            }
            return true;
        }
        throw new IOException("Phrase length is invalid");
    }
}
