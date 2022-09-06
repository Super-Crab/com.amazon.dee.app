package com.amazon.identity.auth.device;

import android.content.Context;
import com.amazon.identity.kcpsdk.auth.ParseErrorException;
import com.amazon.identity.kcpsdk.common.WebResponseParser;
import java.io.IOException;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class jz implements kp {
    private final ej bR;
    private lk iQ;
    private final Context mContext;

    public jz(Context context, lk lkVar, ej ejVar) {
        this.iQ = null;
        this.iQ = lkVar;
        this.mContext = context;
        this.bR = ejVar;
    }

    @Override // com.amazon.identity.auth.device.kp
    public Object a(md mdVar, jx jxVar) throws IOException, ParseErrorException {
        return jw.a(this.mContext, mdVar, jxVar, this.iQ, this.bR);
    }

    @Override // com.amazon.identity.auth.device.kp
    public kn b(md mdVar, WebResponseParser webResponseParser, ko koVar) {
        return b(mdVar, new lo(webResponseParser), koVar);
    }

    public kn b(md mdVar, lw lwVar, ko koVar) {
        return new jw(this.mContext, mdVar, new ll(lwVar, koVar, mdVar.iE()), this.iQ, this.bR);
    }
}
