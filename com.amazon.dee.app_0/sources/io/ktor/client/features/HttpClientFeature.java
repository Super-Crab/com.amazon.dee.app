package io.ktor.client.features;

import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import io.ktor.client.HttpClient;
import io.ktor.util.AttributeKey;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
/* compiled from: HttpClientFeature.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u00020\u0002J\u001d\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00028\u00012\u0006\u0010\u000b\u001a\u00020\fH&¢\u0006\u0002\u0010\rJ(\u0010\u000e\u001a\u00028\u00012\u0019\b\u0002\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\t0\u0010¢\u0006\u0002\b\u0011H&¢\u0006\u0002\u0010\u0012R\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0013"}, d2 = {"Lio/ktor/client/features/HttpClientFeature;", "TConfig", "", "TFeature", "key", "Lio/ktor/util/AttributeKey;", "getKey", "()Lio/ktor/util/AttributeKey;", "install", "", "feature", AuthorizationResponseParser.SCOPE, "Lio/ktor/client/HttpClient;", "(Ljava/lang/Object;Lio/ktor/client/HttpClient;)V", "prepare", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "ktor-client-core"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public interface HttpClientFeature<TConfig, TFeature> {

    /* compiled from: HttpClientFeature.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class DefaultImpls {
        /* JADX WARN: Multi-variable type inference failed */
        @NotNull
        public static /* synthetic */ Object prepare$default(HttpClientFeature httpClientFeature, Function1 function1, int i, Object obj) {
            if (obj == null) {
                if ((i & 1) != 0) {
                    function1 = HttpClientFeature$prepare$1.INSTANCE;
                }
                return httpClientFeature.mo10282prepare(function1);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: prepare");
        }
    }

    @NotNull
    AttributeKey<TFeature> getKey();

    void install(@NotNull TFeature tfeature, @NotNull HttpClient httpClient);

    @NotNull
    /* renamed from: prepare */
    TFeature mo10282prepare(@NotNull Function1<? super TConfig, Unit> function1);
}
