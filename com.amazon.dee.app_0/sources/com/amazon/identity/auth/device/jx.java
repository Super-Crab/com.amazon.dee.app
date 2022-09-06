package com.amazon.identity.auth.device;

import com.amazon.identity.kcpsdk.auth.ParseErrorException;
import java.io.IOException;
import java.net.HttpURLConnection;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public interface jx {
    Object a(me meVar, byte[] bArr) throws ParseErrorException, IOException;

    String g(HttpURLConnection httpURLConnection);
}
