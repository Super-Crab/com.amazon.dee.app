package com.amazon.alexa.accessoryclient.common.datatransfer;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.io.Source;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import org.aspectj.lang.JoinPoint;
import org.jetbrains.annotations.NotNull;
/* compiled from: IpcSource.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u000f\u001a\u00020\u0010H\u0007J\u0006\u0010\u0011\u001a\u00020\u000eJ\b\u0010\u0012\u001a\u00020\u0010H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/datatransfer/AutomaticSourceTransferrer;", "", "source", "Lcom/amazon/alexa/accessory/io/Source;", "bufferSize", "", "(Lcom/amazon/alexa/accessory/io/Source;I)V", "hasCompleted", "", JoinPoint.SYNCHRONIZATION_LOCK, "Ljava/lang/Object;", "outputStream", "Ljava/io/OutputStream;", "readFd", "Lcom/amazon/alexa/accessoryclient/common/datatransfer/ParcelFileDescriptorWrapper;", "await", "", "getReadFileDescriptor", "notifyComplete", "Companion", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AutomaticSourceTransferrer {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "AutomaticSourceTransferrer:";
    private boolean hasCompleted;
    private final Object lock;
    private OutputStream outputStream;
    private final ParcelFileDescriptorWrapper readFd;

    /* compiled from: IpcSource.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 16})
    /* renamed from: com.amazon.alexa.accessoryclient.common.datatransfer.AutomaticSourceTransferrer$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static final class AnonymousClass1 extends Lambda implements Function0<Unit> {
        AnonymousClass1() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: invoke */
        public /* bridge */ /* synthetic */ Unit mo12560invoke() {
            mo12560invoke();
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void mo12560invoke() {
            AutomaticSourceTransferrer.this.notifyComplete();
        }
    }

    /* compiled from: IpcSource.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J(\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J6\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u0011H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/datatransfer/AutomaticSourceTransferrer$Companion;", "", "()V", "TAG", "", "transferLoop", "", "readFileDescriptor", "Lcom/amazon/alexa/accessoryclient/common/datatransfer/ParcelFileDescriptorWrapper;", "source", "Lcom/amazon/alexa/accessory/io/Source;", "outputStream", "Ljava/io/OutputStream;", "bufferSize", "", "transferViaThread", "complete", "Lkotlin/Function0;", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void transferLoop(ParcelFileDescriptorWrapper parcelFileDescriptorWrapper, Source source, OutputStream outputStream, int i) throws IOException {
            Ref.IntRef intRef = new Ref.IntRef();
            intRef.element = 0;
            byte[] bArr = new byte[i];
            while (new AutomaticSourceTransferrer$Companion$transferLoop$1(intRef, source, bArr, parcelFileDescriptorWrapper).mo12560invoke().intValue() != -1) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AutomaticSourceTransferrer: successfully read ");
                outline107.append(intRef.element);
                outline107.append(" bytes from source ");
                outline107.append(source);
                Logger.e(outline107.toString());
                try {
                    outputStream.write(bArr, 0, intRef.element);
                    StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("AutomaticSourceTransferrer: successfully wrote ");
                    outline1072.append(intRef.element);
                    outline1072.append(" bytes to outputStream ");
                    outline1072.append(outputStream);
                    Logger.v(outline1072.toString());
                } catch (IOException e) {
                    Logger.e("AutomaticSourceTransferrer: error in writing to outputStream " + outputStream, e);
                    throw e;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void transferViaThread(final ParcelFileDescriptorWrapper parcelFileDescriptorWrapper, final Source source, final OutputStream outputStream, final int i, final Function0<Unit> function0) {
            new Thread(new Runnable() { // from class: com.amazon.alexa.accessoryclient.common.datatransfer.AutomaticSourceTransferrer$Companion$transferViaThread$1
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        Source source2 = Source.this;
                        OutputStream outputStream2 = outputStream;
                        AutomaticSourceTransferrer.Companion.transferLoop(parcelFileDescriptorWrapper, Source.this, outputStream, i);
                        Unit unit = Unit.INSTANCE;
                        CloseableKt.closeFinally(outputStream2, null);
                        Unit unit2 = Unit.INSTANCE;
                        CloseableKt.closeFinally(source2, null);
                    } catch (IOException e) {
                        Logger.e("AutomaticSourceTransferrer: error in transfer", e);
                    }
                    function0.mo12560invoke();
                }
            }).start();
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0082  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public AutomaticSourceTransferrer(@org.jetbrains.annotations.NotNull com.amazon.alexa.accessory.io.Source r11, int r12) {
        /*
            r10 = this;
            java.lang.String r0 = "source"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r11, r0)
            r10.<init>()
            java.lang.Object r0 = new java.lang.Object
            r0.<init>()
            r10.lock = r0
            r0 = 1
            r1 = 0
            com.amazon.alexa.accessoryclient.common.datatransfer.ParcelFileDescriptorProviderHolder r2 = com.amazon.alexa.accessoryclient.common.datatransfer.ParcelFileDescriptorProviderHolder.INSTANCE     // Catch: java.io.IOException -> L3f
            com.amazon.alexa.accessoryclient.common.datatransfer.ParcelFileDescriptorProvider r2 = r2.get()     // Catch: java.io.IOException -> L3f
            com.amazon.alexa.accessoryclient.common.datatransfer.ParcelFileDescriptorWrapper[] r2 = r2.createReliableSocketPair()     // Catch: java.io.IOException -> L3f
            r3 = 0
            r3 = r2[r3]     // Catch: java.io.IOException -> L3f
            r2 = r2[r0]     // Catch: java.io.IOException -> L3d
            java.io.OutputStream r2 = r2.getAutoCloseOutputStream()     // Catch: java.io.IOException -> L3d
            r10.outputStream = r2     // Catch: java.io.IOException -> L3d
            com.amazon.alexa.accessoryclient.common.datatransfer.AutomaticSourceTransferrer$Companion r4 = com.amazon.alexa.accessoryclient.common.datatransfer.AutomaticSourceTransferrer.Companion     // Catch: java.io.IOException -> L3d
            java.io.OutputStream r7 = r10.outputStream     // Catch: java.io.IOException -> L3d
            if (r7 != 0) goto L31
            java.lang.String r2 = "outputStream"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)     // Catch: java.io.IOException -> L3d
        L31:
            com.amazon.alexa.accessoryclient.common.datatransfer.AutomaticSourceTransferrer$1 r9 = new com.amazon.alexa.accessoryclient.common.datatransfer.AutomaticSourceTransferrer$1     // Catch: java.io.IOException -> L3d
            r9.<init>()     // Catch: java.io.IOException -> L3d
            r5 = r3
            r6 = r11
            r8 = r12
            com.amazon.alexa.accessoryclient.common.datatransfer.AutomaticSourceTransferrer.Companion.access$transferViaThread(r4, r5, r6, r7, r8, r9)     // Catch: java.io.IOException -> L3d
            goto L7f
        L3d:
            r12 = move-exception
            goto L41
        L3f:
            r12 = move-exception
            r3 = r1
        L41:
            r11.close()     // Catch: java.io.IOException -> L45
            goto L5e
        L45:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "AutomaticSourceTransferrer: caught error in closing source "
            r2.append(r4)
            r2.append(r11)
            java.lang.String r4 = " after error in createReliableSocketPair"
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            com.amazon.alexa.accessory.internal.util.Logger.e(r2)
        L5e:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "AutomaticSourceTransferrer: caught error in createReliableSocketPair, transfer will not occur "
            r2.append(r4)
            java.lang.String r4 = "for source "
            r2.append(r4)
            r2.append(r11)
            java.lang.String r11 = " and setting -1 as value for readFd"
            r2.append(r11)
            java.lang.String r11 = r2.toString()
            com.amazon.alexa.accessory.internal.util.Logger.e(r11, r12)
            r10.notifyComplete()
        L7f:
            if (r3 == 0) goto L82
            goto L87
        L82:
            com.amazon.alexa.accessoryclient.common.datatransfer.DefaultParcelFileDescriptorWrapper r3 = new com.amazon.alexa.accessoryclient.common.datatransfer.DefaultParcelFileDescriptorWrapper
            r3.<init>(r1, r0, r1)
        L87:
            r10.readFd = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.accessoryclient.common.datatransfer.AutomaticSourceTransferrer.<init>(com.amazon.alexa.accessory.io.Source, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifyComplete() {
        synchronized (this.lock) {
            this.hasCompleted = true;
            this.lock.notifyAll();
            Unit unit = Unit.INSTANCE;
        }
    }

    @VisibleForTesting
    public final void await() {
        synchronized (this.lock) {
            while (!this.hasCompleted) {
                this.lock.wait();
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @NotNull
    public final ParcelFileDescriptorWrapper getReadFileDescriptor() {
        return this.readFd;
    }

    public /* synthetic */ AutomaticSourceTransferrer(Source source, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(source, (i2 & 2) != 0 ? 256 : i);
    }
}
