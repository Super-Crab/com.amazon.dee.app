package com.google.android.gms.common.server.response;

import com.google.android.gms.common.server.response.FastParser;
import java.io.BufferedReader;
import java.io.IOException;
/* loaded from: classes2.dex */
final class zaa implements FastParser.zaa<Integer> {
    @Override // com.google.android.gms.common.server.response.FastParser.zaa
    public final /* synthetic */ Integer zah(FastParser fastParser, BufferedReader bufferedReader) throws FastParser.ParseException, IOException {
        int zad;
        zad = fastParser.zad(bufferedReader);
        return Integer.valueOf(zad);
    }
}
