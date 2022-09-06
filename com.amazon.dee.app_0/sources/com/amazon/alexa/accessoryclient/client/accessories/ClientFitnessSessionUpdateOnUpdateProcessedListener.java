package com.amazon.alexa.accessoryclient.client.accessories;

import android.annotation.SuppressLint;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.repositories.fitness.FitnessSessionUpdate;
import com.amazon.alexa.accessoryclient.common.query.ApiIdentifier;
import com.amazon.alexa.accessoryclient.common.query.request.QuerySessionBooleanStringRequest;
import com.amazon.alexa.accessoryclient.common.rxipc.RxIPCClient;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ClientFitnessRepository.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\b\u0086\b\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\b\u001a\u00020\u0003HÂ\u0003J\t\u0010\t\u001a\u00020\u0003HÂ\u0003J\t\u0010\n\u001a\u00020\u0006HÂ\u0003J'\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0017J\b\u0010\u0016\u001a\u00020\u0013H\u0017J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/amazon/alexa/accessoryclient/client/accessories/ClientFitnessSessionUpdateOnUpdateProcessedListener;", "Lcom/amazon/alexa/accessory/repositories/fitness/FitnessSessionUpdate$OnUpdateProcessedListener;", "identifier", "", "uuid", "client", "Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;", "(Ljava/lang/String;Ljava/lang/String;Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;)V", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "onFailure", "", "error", "", "onSuccess", "toString", "Companion", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ClientFitnessSessionUpdateOnUpdateProcessedListener implements FitnessSessionUpdate.OnUpdateProcessedListener {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "ClientOnUpdateProcessedListener:";
    private final RxIPCClient client;
    private final String identifier;
    private final String uuid;

    /* compiled from: ClientFitnessRepository.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/alexa/accessoryclient/client/accessories/ClientFitnessSessionUpdateOnUpdateProcessedListener$Companion;", "", "()V", "TAG", "", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public ClientFitnessSessionUpdateOnUpdateProcessedListener(@NotNull String identifier, @NotNull String uuid, @NotNull RxIPCClient client) {
        Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        Intrinsics.checkParameterIsNotNull(uuid, "uuid");
        Intrinsics.checkParameterIsNotNull(client, "client");
        this.identifier = identifier;
        this.uuid = uuid;
        this.client = client;
    }

    private final String component1() {
        return this.identifier;
    }

    private final String component2() {
        return this.uuid;
    }

    private final RxIPCClient component3() {
        return this.client;
    }

    public static /* synthetic */ ClientFitnessSessionUpdateOnUpdateProcessedListener copy$default(ClientFitnessSessionUpdateOnUpdateProcessedListener clientFitnessSessionUpdateOnUpdateProcessedListener, String str, String str2, RxIPCClient rxIPCClient, int i, Object obj) {
        if ((i & 1) != 0) {
            str = clientFitnessSessionUpdateOnUpdateProcessedListener.identifier;
        }
        if ((i & 2) != 0) {
            str2 = clientFitnessSessionUpdateOnUpdateProcessedListener.uuid;
        }
        if ((i & 4) != 0) {
            rxIPCClient = clientFitnessSessionUpdateOnUpdateProcessedListener.client;
        }
        return clientFitnessSessionUpdateOnUpdateProcessedListener.copy(str, str2, rxIPCClient);
    }

    @NotNull
    public final ClientFitnessSessionUpdateOnUpdateProcessedListener copy(@NotNull String identifier, @NotNull String uuid, @NotNull RxIPCClient client) {
        Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        Intrinsics.checkParameterIsNotNull(uuid, "uuid");
        Intrinsics.checkParameterIsNotNull(client, "client");
        return new ClientFitnessSessionUpdateOnUpdateProcessedListener(identifier, uuid, client);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof ClientFitnessSessionUpdateOnUpdateProcessedListener)) {
                return false;
            }
            ClientFitnessSessionUpdateOnUpdateProcessedListener clientFitnessSessionUpdateOnUpdateProcessedListener = (ClientFitnessSessionUpdateOnUpdateProcessedListener) obj;
            return Intrinsics.areEqual(this.identifier, clientFitnessSessionUpdateOnUpdateProcessedListener.identifier) && Intrinsics.areEqual(this.uuid, clientFitnessSessionUpdateOnUpdateProcessedListener.uuid) && Intrinsics.areEqual(this.client, clientFitnessSessionUpdateOnUpdateProcessedListener.client);
        }
        return true;
    }

    public int hashCode() {
        String str = this.identifier;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.uuid;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        RxIPCClient rxIPCClient = this.client;
        if (rxIPCClient != null) {
            i = rxIPCClient.hashCode();
        }
        return hashCode2 + i;
    }

    @Override // com.amazon.alexa.accessory.repositories.fitness.FitnessSessionUpdate.OnUpdateProcessedListener
    @SuppressLint({"CheckResult"})
    public void onFailure(@NotNull Throwable error) {
        Intrinsics.checkParameterIsNotNull(error, "error");
        this.client.createCompletable(ApiIdentifier.FITNESS_UPDATE_PROCESSED_INTERNAL, new QuerySessionBooleanStringRequest(this.identifier, false, this.uuid)).subscribe(new Action() { // from class: com.amazon.alexa.accessoryclient.client.accessories.ClientFitnessSessionUpdateOnUpdateProcessedListener$onFailure$1
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                String str;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ClientOnUpdateProcessedListener: Successfully notified fitness update metatdata uuid ");
                str = ClientFitnessSessionUpdateOnUpdateProcessedListener.this.uuid;
                outline107.append(str);
                outline107.append(" of  ");
                outline107.append("failed update consumption");
                Logger.v(outline107.toString());
            }
        }, new Consumer<Throwable>() { // from class: com.amazon.alexa.accessoryclient.client.accessories.ClientFitnessSessionUpdateOnUpdateProcessedListener$onFailure$2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable th) {
                String str;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ClientOnUpdateProcessedListener: Failed to notify fitness update metadata uuid ");
                str = ClientFitnessSessionUpdateOnUpdateProcessedListener.this.uuid;
                outline107.append(str);
                outline107.append(" of ");
                outline107.append("failed update consumption");
                Logger.e(outline107.toString(), th);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.repositories.fitness.FitnessSessionUpdate.OnUpdateProcessedListener
    @SuppressLint({"CheckResult"})
    public void onSuccess() {
        this.client.createCompletable(ApiIdentifier.FITNESS_UPDATE_PROCESSED_INTERNAL, new QuerySessionBooleanStringRequest(this.identifier, true, this.uuid)).subscribe(new Action() { // from class: com.amazon.alexa.accessoryclient.client.accessories.ClientFitnessSessionUpdateOnUpdateProcessedListener$onSuccess$1
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                String str;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ClientOnUpdateProcessedListener: Successfully notified fitness update metatdata uuid ");
                str = ClientFitnessSessionUpdateOnUpdateProcessedListener.this.uuid;
                outline107.append(str);
                outline107.append(" of  ");
                outline107.append("successful update consumption");
                Logger.v(outline107.toString());
            }
        }, new Consumer<Throwable>() { // from class: com.amazon.alexa.accessoryclient.client.accessories.ClientFitnessSessionUpdateOnUpdateProcessedListener$onSuccess$2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable th) {
                String str;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ClientOnUpdateProcessedListener: Failed to notify fitness update metadata uuid ");
                str = ClientFitnessSessionUpdateOnUpdateProcessedListener.this.uuid;
                outline107.append(str);
                outline107.append(" of successful ");
                outline107.append("update consumption");
                Logger.e(outline107.toString(), th);
            }
        });
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ClientFitnessSessionUpdateOnUpdateProcessedListener(identifier=");
        outline107.append(this.identifier);
        outline107.append(", uuid=");
        outline107.append(this.uuid);
        outline107.append(", client=");
        outline107.append(this.client);
        outline107.append(")");
        return outline107.toString();
    }
}
