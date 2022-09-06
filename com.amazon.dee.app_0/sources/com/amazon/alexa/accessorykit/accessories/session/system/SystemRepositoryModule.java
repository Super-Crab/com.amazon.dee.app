package com.amazon.alexa.accessorykit.accessories.session.system;

import android.annotation.SuppressLint;
import com.amazon.alexa.accessory.davs.DavsI18nConfig;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.System;
import com.amazon.alexa.accessoryclient.client.accessories.SessionSupplier;
import com.amazon.alexa.accessorykit.ContainerProvider;
import com.amazon.alexa.accessorykit.accessories.ArrayModelTransformerHelper;
import com.amazon.alexa.accessorykit.accessories.MapModelTransformer;
import com.amazon.alexa.accessorykit.internal.rxreactnative.RxRN;
import com.amazon.alexa.accessorykit.internal.rxreactnative.RxRNEventId;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableMap;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Consumer;
/* loaded from: classes6.dex */
public class SystemRepositoryModule {
    private final SessionSupplier clientSessionSupplier;
    private final MapModelTransformer<DavsI18nConfig> davsi18nConfigTransformer;
    private final MapModelTransformer<System.Locale> localeTransformer;
    private final MapModelTransformer<System.Locales> localesTransformer;
    private final RxRN rxRN;
    private final MapModelTransformer<System.Users> usersTransformer;

    public SystemRepositoryModule(ContainerProvider containerProvider, RxRN rxRN, SessionSupplier sessionSupplier) {
        Preconditions.notNull(containerProvider, "containerProvider");
        Preconditions.notNull(rxRN, "rxRN");
        Preconditions.notNull(sessionSupplier, "clientSessionSupplier");
        this.localeTransformer = new SystemModelLocaleTransformer(containerProvider);
        this.localesTransformer = new SystemModelLocalesTransformer(containerProvider, this.localeTransformer);
        this.usersTransformer = new SystemModelUsersTransformer(containerProvider, new ArrayModelTransformerHelper(containerProvider, new SystemModelUserTransformer(containerProvider)));
        this.davsi18nConfigTransformer = new SystemModelI18nConfigTransformer(containerProvider);
        this.clientSessionSupplier = sessionSupplier;
        this.rxRN = rxRN;
    }

    @SuppressLint({"CheckResult"})
    public void connectUser(String str, String str2, final Promise promise) {
        Single<Common.ErrorCode> connectUser = this.clientSessionSupplier.getSession(str).getSystemRepository().connectUser(str2);
        Consumer<? super Common.ErrorCode> consumer = new Consumer() { // from class: com.amazon.alexa.accessorykit.accessories.session.system.-$$Lambda$SystemRepositoryModule$q8b1y4blOyD8Frcy4lIAu1S19EQ
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Promise.this.resolve(Integer.valueOf(((Common.ErrorCode) obj).getNumber()));
            }
        };
        promise.getClass();
        connectUser.subscribe(consumer, new $$Lambda$uy64BVvzETLGSEBSP57B5ZCU4o(promise));
    }

    @SuppressLint({"CheckResult"})
    public void disconnectUser(String str, String str2, final Promise promise) {
        Single<Common.ErrorCode> disconnectUser = this.clientSessionSupplier.getSession(str).getSystemRepository().disconnectUser(str2);
        Consumer<? super Common.ErrorCode> consumer = new Consumer() { // from class: com.amazon.alexa.accessorykit.accessories.session.system.-$$Lambda$SystemRepositoryModule$m4n3zsWQff7fb02b3nIYlSIK7PM
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Promise.this.resolve(Integer.valueOf(((Common.ErrorCode) obj).getNumber()));
            }
        };
        promise.getClass();
        disconnectUser.subscribe(consumer, new $$Lambda$uy64BVvzETLGSEBSP57B5ZCU4o(promise));
    }

    @SuppressLint({"CheckResult"})
    public void queryI18NConfig(ReadableMap readableMap, String str) {
        this.rxRN.subscribe(RxRNEventId.from(readableMap), this.davsi18nConfigTransformer, this.clientSessionSupplier.getSession(str).getSystemRepository().queryDavsI18nConfig());
    }

    public void queryLocales(ReadableMap readableMap, String str) {
        this.rxRN.subscribe(RxRNEventId.from(readableMap), this.localesTransformer, this.clientSessionSupplier.getSession(str).getSystemRepository().queryLocales().toObservable());
    }

    @SuppressLint({"CheckResult"})
    public void queryUsers(ReadableMap readableMap, String str) {
        this.rxRN.subscribe(RxRNEventId.from(readableMap), this.usersTransformer, this.clientSessionSupplier.getSession(str).getSystemRepository().queryUsers());
    }

    @SuppressLint({"CheckResult"})
    public void setLocale(String str, String str2, final Promise promise) {
        Single<Common.ErrorCode> locale = this.clientSessionSupplier.getSession(str).getSystemRepository().setLocale(System.Locale.newBuilder().setName(str2).mo10084build());
        Consumer<? super Common.ErrorCode> consumer = new Consumer() { // from class: com.amazon.alexa.accessorykit.accessories.session.system.-$$Lambda$SystemRepositoryModule$ydqMbGX3TOBx-j5TTf9XKwAAvyc
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Promise.this.resolve(Integer.valueOf(((Common.ErrorCode) obj).getNumber()));
            }
        };
        promise.getClass();
        locale.subscribe(consumer, new $$Lambda$uy64BVvzETLGSEBSP57B5ZCU4o(promise));
    }

    @SuppressLint({"CheckResult"})
    public void unpairUser(String str, String str2, final Promise promise) {
        Single<Common.ErrorCode> unpairUser = this.clientSessionSupplier.getSession(str).getSystemRepository().unpairUser(str2);
        Consumer<? super Common.ErrorCode> consumer = new Consumer() { // from class: com.amazon.alexa.accessorykit.accessories.session.system.-$$Lambda$SystemRepositoryModule$g6yE3H-OUWJrxaVb1tsGgj_wQX8
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Promise.this.resolve(Integer.valueOf(((Common.ErrorCode) obj).getNumber()));
            }
        };
        promise.getClass();
        unpairUser.subscribe(consumer, new $$Lambda$uy64BVvzETLGSEBSP57B5ZCU4o(promise));
    }
}
