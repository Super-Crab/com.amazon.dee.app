package io.ktor.client.request;

import com.amazon.alexa.accessory.frames.contacts.ContactsModuleConstants;
import com.dee.app.data.reactnative.bridges.HttpClientModule;
import io.ktor.http.Headers;
import io.ktor.http.content.OutgoingContent;
import kotlin.Metadata;
import kotlinx.coroutines.io.ByteChannel;
import kotlinx.coroutines.io.ByteChannelKt;
import kotlinx.coroutines.io.ByteWriteChannel;
import org.jetbrains.annotations.NotNull;
/* compiled from: Content.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0019\u0010\t\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0006H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u0010\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eH&R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, d2 = {"Lio/ktor/client/request/ClientUpgradeContent;", "Lio/ktor/http/content/OutgoingContent$NoContent;", "()V", "content", "Lkotlinx/coroutines/io/ByteChannel;", ContactsModuleConstants.OUTPUT, "Lkotlinx/coroutines/io/ByteWriteChannel;", "getOutput", "()Lkotlinx/coroutines/io/ByteWriteChannel;", "pipeTo", "", "(Lkotlinx/coroutines/io/ByteWriteChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "verify", HttpClientModule.ElementsRequestKey.HEADERS, "Lio/ktor/http/Headers;", "ktor-client-core"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public abstract class ClientUpgradeContent extends OutgoingContent.NoContent {
    private final ByteChannel content = ByteChannelKt.ByteChannel$default(false, 1, null);

    @NotNull
    public final ByteWriteChannel getOutput() {
        return this.content;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0040  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object pipeTo(@org.jetbrains.annotations.NotNull kotlinx.coroutines.io.ByteWriteChannel r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof io.ktor.client.request.ClientUpgradeContent$pipeTo$1
            if (r0 == 0) goto L13
            r0 = r10
            io.ktor.client.request.ClientUpgradeContent$pipeTo$1 r0 = (io.ktor.client.request.ClientUpgradeContent$pipeTo$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.client.request.ClientUpgradeContent$pipeTo$1 r0 = new io.ktor.client.request.ClientUpgradeContent$pipeTo$1
            r0.<init>(r8, r10)
        L18:
            r5 = r0
            java.lang.Object r10 = r5.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r5.label
            r2 = 1
            if (r1 == 0) goto L40
            if (r1 != r2) goto L38
            java.lang.Object r9 = r5.L$1
            kotlinx.coroutines.io.ByteWriteChannel r9 = (kotlinx.coroutines.io.ByteWriteChannel) r9
            java.lang.Object r9 = r5.L$0
            io.ktor.client.request.ClientUpgradeContent r9 = (io.ktor.client.request.ClientUpgradeContent) r9
            boolean r9 = r10 instanceof kotlin.Result.Failure
            if (r9 != 0) goto L33
            goto L58
        L33:
            kotlin.Result$Failure r10 = (kotlin.Result.Failure) r10
            java.lang.Throwable r9 = r10.exception
            throw r9
        L38:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L40:
            boolean r1 = r10 instanceof kotlin.Result.Failure
            if (r1 != 0) goto L5b
            kotlinx.coroutines.io.ByteChannel r1 = r8.content
            r3 = 0
            r6 = 2
            r7 = 0
            r5.L$0 = r8
            r5.L$1 = r9
            r5.label = r2
            r2 = r9
            java.lang.Object r9 = kotlinx.coroutines.io.ByteReadChannelKt.copyAndClose$default(r1, r2, r3, r5, r6, r7)
            if (r9 != r0) goto L58
            return r0
        L58:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        L5b:
            kotlin.Result$Failure r10 = (kotlin.Result.Failure) r10
            java.lang.Throwable r9 = r10.exception
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.request.ClientUpgradeContent.pipeTo(kotlinx.coroutines.io.ByteWriteChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public abstract void verify(@NotNull Headers headers);
}
