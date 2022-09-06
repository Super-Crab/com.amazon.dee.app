package com.amazon.identity.auth.device;

import com.amazon.identity.kcpsdk.auth.ParseErrorException;
import com.amazon.identity.kcpsdk.common.WebResponseParser;
import java.io.IOException;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public interface kp {
    Object a(md mdVar, jx jxVar) throws IOException, ParseErrorException;

    kn b(md mdVar, WebResponseParser webResponseParser, ko koVar);
}
