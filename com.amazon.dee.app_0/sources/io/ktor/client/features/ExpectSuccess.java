package io.ktor.client.features;

import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import io.ktor.client.HttpClient;
import io.ktor.client.response.HttpResponsePipeline;
import io.ktor.util.AttributeKey;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ExpectSuccess.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lio/ktor/client/features/ExpectSuccess;", "", "()V", "Companion", "ktor-client-core"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class ExpectSuccess {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final AttributeKey<ExpectSuccess> key = new AttributeKey<>("ExpectSuccess");

    /* compiled from: ExpectSuccess.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\u0018\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH\u0016J!\u0010\r\u001a\u00020\u00032\u0017\u0010\u000e\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u000f¢\u0006\u0002\b\u0010H\u0016R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0011"}, d2 = {"Lio/ktor/client/features/ExpectSuccess$Companion;", "Lio/ktor/client/features/HttpClientFeature;", "", "Lio/ktor/client/features/ExpectSuccess;", "()V", "key", "Lio/ktor/util/AttributeKey;", "getKey", "()Lio/ktor/util/AttributeKey;", "install", "feature", AuthorizationResponseParser.SCOPE, "Lio/ktor/client/HttpClient;", "prepare", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "ktor-client-core"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class Companion implements HttpClientFeature<Unit, ExpectSuccess> {
        private Companion() {
        }

        @Override // io.ktor.client.features.HttpClientFeature
        @NotNull
        public AttributeKey<ExpectSuccess> getKey() {
            return ExpectSuccess.key;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Override // io.ktor.client.features.HttpClientFeature
        public void install(@NotNull ExpectSuccess feature, @NotNull HttpClient scope) {
            Intrinsics.checkParameterIsNotNull(feature, "feature");
            Intrinsics.checkParameterIsNotNull(scope, "scope");
            scope.getResponsePipeline().intercept(HttpResponsePipeline.Phases.getReceive(), new ExpectSuccess$Companion$install$1(null));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.ktor.client.features.HttpClientFeature
        @NotNull
        /* renamed from: prepare */
        public ExpectSuccess mo10282prepare(@NotNull Function1<? super Unit, Unit> block) {
            Intrinsics.checkParameterIsNotNull(block, "block");
            return new ExpectSuccess();
        }
    }
}
