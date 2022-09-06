package com.amazon.alexa.accessoryclient.client.accessories;

import com.amazon.alexa.accessory.davs.DavsI18nConfig;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.System;
import com.amazon.alexa.accessory.repositories.system.SystemRepository;
import com.amazon.alexa.accessoryclient.common.query.ApiIdentifier;
import com.amazon.alexa.accessoryclient.common.query.request.QuerySessionBooleanIntRequest;
import com.amazon.alexa.accessoryclient.common.query.request.QuerySessionLocaleRequest;
import com.amazon.alexa.accessoryclient.common.query.request.QuerySessionRequest;
import com.amazon.alexa.accessoryclient.common.query.request.QuerySessionStringRequest;
import com.amazon.alexa.accessoryclient.common.query.response.ErrorCodeResponse;
import com.amazon.alexa.accessoryclient.common.query.response.I18nConfigResponse;
import com.amazon.alexa.accessoryclient.common.query.response.LocalesResponse;
import com.amazon.alexa.accessoryclient.common.query.response.UsersResponse;
import com.amazon.alexa.accessoryclient.common.rxipc.RxIPCClient;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ClientSystemRepository.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u0005H\u0016J\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u0005H\u0016J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u000e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\bH\u0016J\u000e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0016J\u000e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013H\u0016J\u000e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u0010H\u0016J\u001e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0016\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u001d\u001a\u00020\u0019H\u0016J\u0016\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u001f\u001a\u00020 H\u0016J\u0016\u0010!\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u0005H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/amazon/alexa/accessoryclient/client/accessories/ClientSystemRepository;", "Lcom/amazon/alexa/accessory/repositories/system/SystemRepository;", "client", "Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;", "identifier", "", "(Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;Ljava/lang/String;)V", "connectUser", "Lio/reactivex/rxjava3/core/Single;", "Lcom/amazon/alexa/accessory/protocol/Common$ErrorCode;", "address", "disconnectUser", "notifyDeviceBeingRemoved", "queryCurrentUser", "Lcom/amazon/alexa/accessory/protocol/System$User;", "queryDavsI18nConfig", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/amazon/alexa/accessory/davs/DavsI18nConfig;", "queryLocales", "Lio/reactivex/rxjava3/core/Flowable;", "Lcom/amazon/alexa/accessory/protocol/System$Locales;", "queryUsers", "Lcom/amazon/alexa/accessory/protocol/System$Users;", "requestResetConnection", "timeout", "", "forceDisconnect", "", "requestSwitchUser", "newUser", "setLocale", "locale", "Lcom/amazon/alexa/accessory/protocol/System$Locale;", "unpairUser", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ClientSystemRepository implements SystemRepository {
    private final RxIPCClient client;
    private final String identifier;

    public ClientSystemRepository(@NotNull RxIPCClient client, @NotNull String identifier) {
        Intrinsics.checkParameterIsNotNull(client, "client");
        Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        this.client = client;
        this.identifier = identifier;
    }

    @Override // com.amazon.alexa.accessory.repositories.system.SystemRepository
    @NotNull
    public Single<Common.ErrorCode> connectUser(@NotNull String address) {
        Intrinsics.checkParameterIsNotNull(address, "address");
        Single<Common.ErrorCode> map = this.client.createSingle(ApiIdentifier.CONNECT_USER, new QuerySessionStringRequest(this.identifier, address), ErrorCodeResponse.Transformer.INSTANCE).map(ClientSystemRepository$connectUser$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.createSingle(ApiI…    .map { it.errorCode }");
        return map;
    }

    @Override // com.amazon.alexa.accessory.repositories.system.SystemRepository
    @NotNull
    public Single<Common.ErrorCode> disconnectUser(@NotNull String address) {
        Intrinsics.checkParameterIsNotNull(address, "address");
        Single<Common.ErrorCode> map = this.client.createSingle(ApiIdentifier.DISCONNECT_USER, new QuerySessionStringRequest(this.identifier, address), ErrorCodeResponse.Transformer.INSTANCE).map(ClientSystemRepository$disconnectUser$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.createSingle(ApiI…    .map { it.errorCode }");
        return map;
    }

    @Override // com.amazon.alexa.accessory.repositories.system.SystemRepository
    @NotNull
    public Single<Common.ErrorCode> notifyDeviceBeingRemoved() {
        throw new RuntimeException("Not supported");
    }

    @Override // com.amazon.alexa.accessory.repositories.system.SystemRepository
    @NotNull
    public Single<System.User> queryCurrentUser() {
        throw new RuntimeException("Not supported");
    }

    @Override // com.amazon.alexa.accessory.repositories.system.SystemRepository
    @NotNull
    public Observable<DavsI18nConfig> queryDavsI18nConfig() {
        Observable<DavsI18nConfig> map = this.client.create(ApiIdentifier.QUERY_I18N_CONFIG, new QuerySessionRequest(this.identifier), I18nConfigResponse.Transformer.INSTANCE).map(ClientSystemRepository$queryDavsI18nConfig$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.create(ApiIdentif…map { it.davsI18nConfig }");
        return map;
    }

    @Override // com.amazon.alexa.accessory.repositories.system.SystemRepository
    @NotNull
    public Flowable<System.Locales> queryLocales() {
        Flowable<System.Locales> map = this.client.create(ApiIdentifier.QUERY_LOCALES, new QuerySessionRequest(this.identifier), LocalesResponse.Transformer.INSTANCE).toFlowable(BackpressureStrategy.BUFFER).map(ClientSystemRepository$queryLocales$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.create(ApiIdentif…      .map { it.locales }");
        return map;
    }

    @Override // com.amazon.alexa.accessory.repositories.system.SystemRepository
    @NotNull
    public Observable<System.Users> queryUsers() {
        Observable<System.Users> map = this.client.create(ApiIdentifier.QUERY_USERS, new QuerySessionRequest(this.identifier), UsersResponse.Transformer.INSTANCE).map(ClientSystemRepository$queryUsers$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.create(ApiIdentif…        .map { it.users }");
        return map;
    }

    @Override // com.amazon.alexa.accessory.repositories.system.SystemRepository
    @NotNull
    public Single<Common.ErrorCode> requestResetConnection(int i, boolean z) {
        Single<Common.ErrorCode> map = this.client.createSingle(ApiIdentifier.REQUEST_RESET_CONNECTION, new QuerySessionBooleanIntRequest(this.identifier, z, i), ErrorCodeResponse.Transformer.INSTANCE).map(ClientSystemRepository$requestResetConnection$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.createSingle(ApiI…    .map { it.errorCode }");
        return map;
    }

    @Override // com.amazon.alexa.accessory.repositories.system.SystemRepository
    @NotNull
    public Single<Common.ErrorCode> requestSwitchUser(int i) {
        throw new RuntimeException("Not supported");
    }

    @Override // com.amazon.alexa.accessory.repositories.system.SystemRepository
    @NotNull
    public Single<Common.ErrorCode> setLocale(@NotNull System.Locale locale) {
        Intrinsics.checkParameterIsNotNull(locale, "locale");
        Single<Common.ErrorCode> map = this.client.createSingle(ApiIdentifier.SET_LOCALE, new QuerySessionLocaleRequest(this.identifier, locale), ErrorCodeResponse.Transformer.INSTANCE).map(ClientSystemRepository$setLocale$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.createSingle(ApiI…    .map { it.errorCode }");
        return map;
    }

    @Override // com.amazon.alexa.accessory.repositories.system.SystemRepository
    @NotNull
    public Single<Common.ErrorCode> unpairUser(@NotNull String address) {
        Intrinsics.checkParameterIsNotNull(address, "address");
        Single<Common.ErrorCode> map = this.client.createSingle(ApiIdentifier.UNPAIR_USER, new QuerySessionStringRequest(this.identifier, address), ErrorCodeResponse.Transformer.INSTANCE).map(ClientSystemRepository$unpairUser$1.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "client.createSingle(ApiI…    .map { it.errorCode }");
        return map;
    }
}
