package com.google.android.gms.common.server.response;

import com.google.android.gms.common.server.response.FastParser;
import java.io.BufferedReader;
import java.io.IOException;
/* loaded from: classes2.dex */
final class zae implements FastParser.zaa<Boolean> {
    @Override // com.google.android.gms.common.server.response.FastParser.zaa
    public final /* synthetic */ Boolean zah(FastParser fastParser, BufferedReader bufferedReader) throws FastParser.ParseException, IOException {
        boolean zaa;
        zaa = fastParser.zaa(bufferedReader, false);
        return Boolean.valueOf(zaa);
    }
}
