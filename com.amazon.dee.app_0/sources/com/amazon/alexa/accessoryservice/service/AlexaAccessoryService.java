package com.amazon.alexa.accessoryservice.service;

import android.os.Bundle;
import com.amazon.alexa.accessory.Accessories;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessoryclient.common.connection.BundleSink;
import com.amazon.alexa.accessoryclient.common.connection.DelineatedBundleSink;
import com.amazon.alexa.accessoryclient.common.connection.DelineatedBundleSource;
import com.amazon.alexa.accessoryservice.service.rxipc.AccessoriesRequestHandler;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.aspectj.lang.JoinPoint;
import org.jetbrains.annotations.NotNull;
/* compiled from: AlexaAccessoryService.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u00152\u00020\u0001:\u0003\u0015\u0016\u0017B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u0004X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u00020\u000fX\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0018"}, d2 = {"Lcom/amazon/alexa/accessoryservice/service/AlexaAccessoryService;", "Lcom/amazon/alexa/accessoryservice/service/AbstractAlexaAccessoryService;", "()V", "inputStream", "Lcom/amazon/alexa/accessoryclient/common/connection/DelineatedBundleSink;", "inputStreamSink", "getInputStreamSink", "()Lcom/amazon/alexa/accessoryclient/common/connection/DelineatedBundleSink;", "inputStreamSinks", "", "", "Lcom/amazon/alexa/accessoryclient/common/connection/BundleSink;", JoinPoint.SYNCHRONIZATION_LOCK, "", "outputStream", "Lcom/amazon/alexa/accessoryclient/common/connection/DelineatedBundleSource;", "outputStreamSource", "getOutputStreamSource", "()Lcom/amazon/alexa/accessoryclient/common/connection/DelineatedBundleSource;", "onServiceDestroyed", "", "Companion", "InputStream", "OutputStream", "AlexaAccessoryAndroidService_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AlexaAccessoryService extends AbstractAlexaAccessoryService {
    public static final Companion Companion = new Companion(null);
    private static final int MAJOR_VERSION = 1;
    private static final int MINIMUM_CLIENT_MAJOR_VERSION = 1;
    private static final int MINIMUM_CLIENT_MINOR_VERSION = 0;
    private static final int MINOR_VERSION = 0;
    private static final String TAG = "AlexaAccessoryService: ";
    private final DelineatedBundleSink inputStream;
    @NotNull
    private final DelineatedBundleSink inputStreamSink;
    private final Map<String, BundleSink> inputStreamSinks;
    private final Object lock;
    private final DelineatedBundleSource outputStream;
    @NotNull
    private final DelineatedBundleSource outputStreamSource;

    /* compiled from: AlexaAccessoryService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/alexa/accessoryservice/service/AlexaAccessoryService$Companion;", "", "()V", "MAJOR_VERSION", "", "MINIMUM_CLIENT_MAJOR_VERSION", "MINIMUM_CLIENT_MINOR_VERSION", "MINOR_VERSION", "TAG", "", "AlexaAccessoryAndroidService_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* compiled from: AlexaAccessoryService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\n\u0010\u0005\u001a\u00060\u0006j\u0002`\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/accessoryservice/service/AlexaAccessoryService$InputStream;", "Lcom/amazon/alexa/accessoryclient/common/connection/DelineatedBundleSink;", "(Lcom/amazon/alexa/accessoryservice/service/AlexaAccessoryService;)V", "dispose", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "id", "", "handleBundle", "bundle", "Landroid/os/Bundle;", "AlexaAccessoryAndroidService_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    private final class InputStream implements DelineatedBundleSink {
        public InputStream() {
        }

        @Override // com.amazon.alexa.accessoryclient.common.connection.DelineatedBundleSink
        public void dispose(@NotNull Exception e, @NotNull String id) {
            Intrinsics.checkParameterIsNotNull(e, "e");
            Intrinsics.checkParameterIsNotNull(id, "id");
            synchronized (AlexaAccessoryService.this.lock) {
                Logger.d("AlexaAccessoryService:  Received dispose request for client " + id + " with exception", e);
                BundleSink bundleSink = (BundleSink) AlexaAccessoryService.this.inputStreamSinks.remove(id);
                if (bundleSink != null) {
                    bundleSink.dispose(e);
                    Unit unit = Unit.INSTANCE;
                }
            }
        }

        @Override // com.amazon.alexa.accessoryclient.common.connection.DelineatedBundleSink
        public void handleBundle(@NotNull Bundle bundle, @NotNull String id) {
            Intrinsics.checkParameterIsNotNull(bundle, "bundle");
            Intrinsics.checkParameterIsNotNull(id, "id");
            synchronized (AlexaAccessoryService.this.lock) {
                BundleSink bundleSink = (BundleSink) AlexaAccessoryService.this.inputStreamSinks.get(id);
                if (bundleSink != null) {
                    bundleSink.handleBundle(bundle);
                } else {
                    Logger.e("AlexaAccessoryService:  Can't find client " + id + " input stream, ignoring bundle");
                }
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    /* compiled from: AlexaAccessoryService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lcom/amazon/alexa/accessoryservice/service/AlexaAccessoryService$OutputStream;", "Lcom/amazon/alexa/accessoryclient/common/connection/DelineatedBundleSource;", "(Lcom/amazon/alexa/accessoryservice/service/AlexaAccessoryService;)V", "setDelineatedBundleSink", "", "bundleSink", "Lcom/amazon/alexa/accessoryclient/common/connection/BundleSink;", "id", "", "AlexaAccessoryAndroidService_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    private final class OutputStream implements DelineatedBundleSource {
        public OutputStream() {
        }

        @Override // com.amazon.alexa.accessoryclient.common.connection.DelineatedBundleSource
        public void setDelineatedBundleSink(@NotNull BundleSink bundleSink, @NotNull String id) {
            Intrinsics.checkParameterIsNotNull(bundleSink, "bundleSink");
            Intrinsics.checkParameterIsNotNull(id, "id");
            Accessories sharedInstance = Accessories.getSharedInstance();
            Intrinsics.checkExpressionValueIsNotNull(sharedInstance, "Accessories.getSharedInstance()");
            AccessoriesRequestHandler accessoriesRequestHandler = new AccessoriesRequestHandler(sharedInstance);
            accessoriesRequestHandler.setBundleSink(bundleSink);
            synchronized (AlexaAccessoryService.this.lock) {
                AlexaAccessoryService.this.inputStreamSinks.put(id, accessoriesRequestHandler);
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    public AlexaAccessoryService() {
        super(1, 0, 1, 0);
        this.inputStreamSinks = new HashMap();
        this.lock = new Object();
        this.outputStream = new OutputStream();
        this.inputStream = new InputStream();
        this.inputStreamSink = this.inputStream;
        this.outputStreamSource = this.outputStream;
    }

    @Override // com.amazon.alexa.accessoryservice.service.AbstractAlexaAccessoryService
    @NotNull
    protected DelineatedBundleSink getInputStreamSink() {
        return this.inputStreamSink;
    }

    @Override // com.amazon.alexa.accessoryservice.service.AbstractAlexaAccessoryService
    @NotNull
    protected DelineatedBundleSource getOutputStreamSource() {
        return this.outputStreamSource;
    }

    @Override // com.amazon.alexa.accessoryservice.service.AbstractAlexaAccessoryService
    protected void onServiceDestroyed() {
        synchronized (this.lock) {
            IOException iOException = new IOException("AlexaAccessoryService:  was destroyed");
            for (BundleSink bundleSink : this.inputStreamSinks.values()) {
                bundleSink.dispose(iOException);
            }
            this.inputStreamSinks.clear();
            Unit unit = Unit.INSTANCE;
        }
    }
}
