package com.amazon.alexa.accessory.repositories.system;

import com.amazon.alexa.accessory.davs.DavsI18nConfig;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.System;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes6.dex */
public interface SystemRepository {
    Single<Common.ErrorCode> connectUser(String str);

    Single<Common.ErrorCode> disconnectUser(String str);

    Single<Common.ErrorCode> notifyDeviceBeingRemoved();

    Single<System.User> queryCurrentUser();

    Observable<DavsI18nConfig> queryDavsI18nConfig();

    Flowable<System.Locales> queryLocales();

    Observable<System.Users> queryUsers();

    Single<Common.ErrorCode> requestResetConnection(int i, boolean z);

    Single<Common.ErrorCode> requestSwitchUser(int i);

    Single<Common.ErrorCode> setLocale(System.Locale locale);

    Single<Common.ErrorCode> unpairUser(String str);
}
