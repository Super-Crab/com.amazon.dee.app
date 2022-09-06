package com.amazon.alexa.accessoryclient.common.query;

import android.os.Bundle;
import com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: Query.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0002\u0002\u0003¨\u0006\u0004"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/query/Query;", "", "Request", "Response", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface Query {

    /* compiled from: Query.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0018\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00028\u00008BX\u0082\u0004¢\u0006\u0006\u0012\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/query/Query$Request;", "I", "", "bundle", "Landroid/os/Bundle;", "getBundle", "()Landroid/os/Bundle;", "bundleTransformer", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "getBundleTransformer", "()Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "self", "self$annotations", "()V", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public interface Request<I> {

        /* compiled from: Query.kt */
        @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
        /* loaded from: classes6.dex */
        public static final class DefaultImpls {
            @NotNull
            public static <I> Bundle getBundle(Request<I> request) {
                return request.getBundleTransformer().toBundle((I) getSelf(request));
            }

            /* JADX WARN: Multi-variable type inference failed */
            private static <I> I getSelf(Request<I> request) {
                return request;
            }

            private static /* synthetic */ void self$annotations() {
            }
        }

        @NotNull
        Bundle getBundle();

        @NotNull
        BundleTransformer<I> getBundleTransformer();
    }

    /* compiled from: Query.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0018\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00028\u00008BX\u0082\u0004¢\u0006\u0006\u0012\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/query/Query$Response;", "O", "", "bundle", "Landroid/os/Bundle;", "getBundle", "()Landroid/os/Bundle;", "bundleTransformer", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "getBundleTransformer", "()Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "self", "self$annotations", "()V", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public interface Response<O> {

        /* compiled from: Query.kt */
        @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
        /* loaded from: classes6.dex */
        public static final class DefaultImpls {
            @NotNull
            public static <O> Bundle getBundle(Response<O> response) {
                return response.getBundleTransformer().toBundle((O) getSelf(response));
            }

            /* JADX WARN: Multi-variable type inference failed */
            private static <O> O getSelf(Response<O> response) {
                return response;
            }

            private static /* synthetic */ void self$annotations() {
            }
        }

        @NotNull
        Bundle getBundle();

        @NotNull
        BundleTransformer<O> getBundleTransformer();
    }
}
