package com.amazon.alexa.accessory.repositories.cbl;

import com.amazon.alexa.accessory.protocol.Cbl;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes6.dex */
public interface CblRepository {
    Observable<Cbl.CblLoginState> queryCblLoginState();

    Single<Cbl.CblInformation> requestCblInformation();
}
