package com.amazon.identity.auth.device;

import android.content.Context;
import com.amazon.identity.kcpsdk.auth.ParseErrorException;
import com.amazon.identity.kcpsdk.common.WebResponseParser;
import java.io.IOException;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class dc implements kp {
    private final ej bR;
    private lk iQ;
    private br iR;
    private Context mContext;

    public dc(Context context, String str, ej ejVar) {
        this(context, bs.d(context, str), ejVar);
    }

    @Override // com.amazon.identity.auth.device.kp
    /* renamed from: a */
    public db b(md mdVar, WebResponseParser webResponseParser, ko koVar) {
        return a(mdVar, new lo(webResponseParser), koVar);
    }

    public dc(Context context, String str, String str2, ej ejVar) {
        this(context, bs.e(context, str, str2), ejVar);
    }

    @Override // com.amazon.identity.auth.device.kp
    public Object a(md mdVar, jx jxVar) throws IOException, ParseErrorException {
        throw new RuntimeException("This should not be called!");
    }

    public dc(Context context, br brVar, ej ejVar) {
        this.mContext = null;
        this.iQ = null;
        this.iR = null;
        this.mContext = context;
        this.iR = brVar;
        this.iQ = new lk(this.iR);
        this.bR = ejVar;
    }

    public db a(md mdVar, lw lwVar, ko koVar) {
        br brVar = this.iR;
        if (brVar != null && brVar.z()) {
            this.iR = bs.r(this.mContext);
            boolean ig = this.iQ.ig();
            this.iQ = new lk(this.iR);
            this.iQ.l(ig);
        }
        return new db(this.mContext, mdVar, new ll(lwVar, koVar, mdVar.iE()), this.iQ, this.bR);
    }
}
