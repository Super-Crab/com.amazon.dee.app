package io.ktor.client.request;

import io.ktor.util.pipeline.Pipeline;
import io.ktor.util.pipeline.PipelinePhase;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
/* compiled from: HttpRequestPipeline.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00052\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u0005B\u0005¢\u0006\u0002\u0010\u0004¨\u0006\u0006"}, d2 = {"Lio/ktor/client/request/HttpSendPipeline;", "Lio/ktor/util/pipeline/Pipeline;", "", "Lio/ktor/client/request/HttpRequestBuilder;", "()V", "Phases", "ktor-client-core"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class HttpSendPipeline extends Pipeline<Object, HttpRequestBuilder> {
    public static final Phases Phases = new Phases(null);
    @NotNull
    private static final PipelinePhase Before = new PipelinePhase("Before");
    @NotNull
    private static final PipelinePhase State = new PipelinePhase("State");
    @NotNull
    private static final PipelinePhase Engine = new PipelinePhase("Engine");

    /* compiled from: HttpRequestPipeline.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006¨\u0006\u000b"}, d2 = {"Lio/ktor/client/request/HttpSendPipeline$Phases;", "", "()V", "Before", "Lio/ktor/util/pipeline/PipelinePhase;", "getBefore", "()Lio/ktor/util/pipeline/PipelinePhase;", "Engine", "getEngine", "State", "getState", "ktor-client-core"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class Phases {
        private Phases() {
        }

        @NotNull
        public final PipelinePhase getBefore() {
            return HttpSendPipeline.Before;
        }

        @NotNull
        public final PipelinePhase getEngine() {
            return HttpSendPipeline.Engine;
        }

        @NotNull
        public final PipelinePhase getState() {
            return HttpSendPipeline.State;
        }

        public /* synthetic */ Phases(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public HttpSendPipeline() {
        super(Before, State, Engine);
    }
}
