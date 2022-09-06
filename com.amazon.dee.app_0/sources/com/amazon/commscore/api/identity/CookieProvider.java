package com.amazon.commscore.api.identity;

import rx.Observable;
/* loaded from: classes12.dex */
public interface CookieProvider {
    Observable<String[]> getCookies(String str, String str2);
}
