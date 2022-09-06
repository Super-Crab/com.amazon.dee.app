package io.ktor.util.pipeline;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.device.messaging.ADMConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.ktor.util.Attributes;
import io.ktor.util.AttributesJvmKt;
import io.ktor.util.InternalAPI;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Pipeline.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u001c\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u00020\u0002:\u0002LMBS\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012?\u0010\u0006\u001a;\u00127\u00125\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b¢\u0006\u0002\b\f0\u0007ø\u0001\u0000¢\u0006\u0002\u0010\rB\u0019\u0012\u0012\u0010\u000e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u000f\"\u00020\u0005¢\u0006\u0002\u0010\u0010J\u000e\u0010$\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u0005J\b\u0010%\u001a\u00020\u000bH\u0016JD\u0010&\u001a;\u00127\u00125\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b¢\u0006\u0002\b\f0\u0007H\u0002ø\u0001\u0000J%\u0010'\u001a\b\u0012\u0004\u0012\u00028\u00000(2\u0006\u0010)\u001a\u00028\u00012\u0006\u0010*\u001a\u00028\u0000H\u0000¢\u0006\u0004\b+\u0010,J!\u0010-\u001a\u00028\u00002\u0006\u0010)\u001a\u00028\u00012\u0006\u0010*\u001a\u00028\u0000H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010.J\u001c\u0010/\u001a\u00020\u00162\u0012\u00100\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0000H\u0002J\u001e\u00101\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u0001022\u0006\u0010\u0004\u001a\u00020\u0005H\u0002J\u0010\u00103\u001a\u00020\u00192\u0006\u0010\u0004\u001a\u00020\u0005H\u0002J\u0010\u00104\u001a\u00020\u00162\u0006\u0010\u0004\u001a\u00020\u0005H\u0002J\u0016\u00105\u001a\u00020\u000b2\u0006\u00106\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005J\u0016\u00107\u001a\u00020\u000b2\u0006\u00106\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005JQ\u00108\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u000529\u00109\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b¢\u0006\u0002\b\fø\u0001\u0000¢\u0006\u0002\u0010:JI\u0010;\u001a;\u00127\u00125\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b¢\u0006\u0002\b\f0\u0007H\u0000ø\u0001\u0000¢\u0006\u0002\b<J\u001a\u0010=\u001a\u00020\u000b2\u0012\u00100\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0000JL\u0010>\u001a\u00020\u000b2?\u0010?\u001a;\u00127\u00125\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b¢\u0006\u0002\b\f0\u0007H\u0002ø\u0001\u0000JQ\u0010@\u001a;\u00127\u00125\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b¢\u0006\u0002\b\f0\u00072\u0006\u0010\u0004\u001a\u00020\u0005H\u0000ø\u0001\u0000¢\u0006\u0002\bAJ\b\u0010B\u001a\u00020\u000bH\u0002J\u001c\u0010C\u001a\u00020\u000b2\u0012\u0010D\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0000H\u0002J\u001c\u0010E\u001a\u00020\u000b2\u0012\u0010F\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000102H\u0002JD\u0010G\u001a;\u00127\u00125\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b¢\u0006\u0002\b\f0\u0007H\u0002ø\u0001\u0000JS\u0010H\u001a\u00020\u00162\u0006\u0010\u0004\u001a\u00020\u000529\u00109\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b¢\u0006\u0002\b\fH\u0002ø\u0001\u0000¢\u0006\u0002\u0010IJ:\u0010J\u001a\u00020\u000b\"\u0004\b\u0002\u0010K*\u0012\u0012\u0004\u0012\u0002HK0\"j\b\u0012\u0004\u0012\u0002HK`#2\u0016\u00100\u001a\u0012\u0012\u0004\u0012\u0002HK0\"j\b\u0012\u0004\u0012\u0002HK`#H\u0002R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014RL\u0010\u0006\u001a=\u00127\u00125\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b¢\u0006\u0002\b\f\u0018\u00010\u0007X\u0082\u000eø\u0001\u0000¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001a\u001a\u00020\u00168FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001a\u0010\u001dR\u0017\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00050\u00078F¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u001e\u0010!\u001a\u0012\u0012\u0004\u0012\u00020\u00020\"j\b\u0012\u0004\u0012\u00020\u0002`#X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006N"}, d2 = {"Lio/ktor/util/pipeline/Pipeline;", "TSubject", "", "TContext", "phase", "Lio/ktor/util/pipeline/PipelinePhase;", "interceptors", "", "Lkotlin/Function3;", "Lio/ktor/util/pipeline/PipelineContext;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lio/ktor/util/pipeline/PipelinePhase;Ljava/util/List;)V", "phases", "", "([Lio/ktor/util/pipeline/PipelinePhase;)V", "attributes", "Lio/ktor/util/Attributes;", "getAttributes", "()Lio/ktor/util/Attributes;", "interceptorsListShared", "", "interceptorsListSharedPhase", "interceptorsQuantity", "", "isEmpty", "isEmpty$annotations", "()V", "()Z", EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_ITEMS, "getItems", "()Ljava/util/List;", "phasesRaw", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "addPhase", "afterIntercepted", "cacheInterceptors", "createContext", "Lio/ktor/util/pipeline/PipelineExecutor;", "context", "subject", "createContext$ktor_utils", "(Ljava/lang/Object;Ljava/lang/Object;)Lio/ktor/util/pipeline/PipelineExecutor;", "execute", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fastPathMerge", ADMConstants.EXTRA_FROM, "findPhase", "Lio/ktor/util/pipeline/Pipeline$PhaseContent;", "findPhaseIndex", "hasPhase", "insertPhaseAfter", "reference", "insertPhaseBefore", "intercept", "block", "(Lio/ktor/util/pipeline/PipelinePhase;Lkotlin/jvm/functions/Function3;)V", "interceptorsForTests", "interceptorsForTests$ktor_utils", "merge", "notSharedInterceptorsList", "list", "phaseInterceptors", "phaseInterceptors$ktor_utils", "resetInterceptorsList", "setInterceptorsListFromAnotherPipeline", "pipeline", "setInterceptorsListFromPhase", "phaseContent", "sharedInterceptorsList", "tryAddToPhaseFastpath", "(Lio/ktor/util/pipeline/PipelinePhase;Lkotlin/jvm/functions/Function3;)Z", "addAllAF", ExifInterface.LONGITUDE_EAST, "PhaseContent", "PipelinePhaseRelation", "ktor-utils"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public class Pipeline<TSubject, TContext> {
    @NotNull
    private final Attributes attributes;
    private volatile List<? extends Function3<? super PipelineContext<TSubject, TContext>, ? super TSubject, ? super Continuation<? super Unit>, ? extends Object>> interceptors;
    private boolean interceptorsListShared;
    private PipelinePhase interceptorsListSharedPhase;
    private int interceptorsQuantity;
    private final ArrayList<Object> phasesRaw;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: Pipeline.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b2\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0002¢\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\t"}, d2 = {"Lio/ktor/util/pipeline/Pipeline$PipelinePhaseRelation;", "", "()V", "After", "Before", "Last", "Lio/ktor/util/pipeline/Pipeline$PipelinePhaseRelation$After;", "Lio/ktor/util/pipeline/Pipeline$PipelinePhaseRelation$Before;", "Lio/ktor/util/pipeline/Pipeline$PipelinePhaseRelation$Last;", "ktor-utils"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static abstract class PipelinePhaseRelation {

        /* compiled from: Pipeline.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007"}, d2 = {"Lio/ktor/util/pipeline/Pipeline$PipelinePhaseRelation$After;", "Lio/ktor/util/pipeline/Pipeline$PipelinePhaseRelation;", "relativeTo", "Lio/ktor/util/pipeline/PipelinePhase;", "(Lio/ktor/util/pipeline/PipelinePhase;)V", "getRelativeTo", "()Lio/ktor/util/pipeline/PipelinePhase;", "ktor-utils"}, k = 1, mv = {1, 1, 13})
        /* loaded from: classes3.dex */
        public static final class After extends PipelinePhaseRelation {
            @NotNull
            private final PipelinePhase relativeTo;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public After(@NotNull PipelinePhase relativeTo) {
                super(null);
                Intrinsics.checkParameterIsNotNull(relativeTo, "relativeTo");
                this.relativeTo = relativeTo;
            }

            @NotNull
            public final PipelinePhase getRelativeTo() {
                return this.relativeTo;
            }
        }

        /* compiled from: Pipeline.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007"}, d2 = {"Lio/ktor/util/pipeline/Pipeline$PipelinePhaseRelation$Before;", "Lio/ktor/util/pipeline/Pipeline$PipelinePhaseRelation;", "relativeTo", "Lio/ktor/util/pipeline/PipelinePhase;", "(Lio/ktor/util/pipeline/PipelinePhase;)V", "getRelativeTo", "()Lio/ktor/util/pipeline/PipelinePhase;", "ktor-utils"}, k = 1, mv = {1, 1, 13})
        /* loaded from: classes3.dex */
        public static final class Before extends PipelinePhaseRelation {
            @NotNull
            private final PipelinePhase relativeTo;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Before(@NotNull PipelinePhase relativeTo) {
                super(null);
                Intrinsics.checkParameterIsNotNull(relativeTo, "relativeTo");
                this.relativeTo = relativeTo;
            }

            @NotNull
            public final PipelinePhase getRelativeTo() {
                return this.relativeTo;
            }
        }

        /* compiled from: Pipeline.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0003"}, d2 = {"Lio/ktor/util/pipeline/Pipeline$PipelinePhaseRelation$Last;", "Lio/ktor/util/pipeline/Pipeline$PipelinePhaseRelation;", "()V", "ktor-utils"}, k = 1, mv = {1, 1, 13})
        /* loaded from: classes3.dex */
        public static final class Last extends PipelinePhaseRelation {
            public static final Last INSTANCE = new Last();

            private Last() {
                super(null);
            }
        }

        private PipelinePhaseRelation() {
        }

        public /* synthetic */ PipelinePhaseRelation(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public Pipeline(@NotNull PipelinePhase... phases) {
        Intrinsics.checkParameterIsNotNull(phases, "phases");
        this.attributes = AttributesJvmKt.Attributes$default(false, 1, null);
        ArrayList<Object> arrayList = new ArrayList<>(phases.length + 1);
        for (PipelinePhase pipelinePhase : phases) {
            arrayList.add(pipelinePhase);
        }
        this.phasesRaw = arrayList;
    }

    private final <E> void addAllAF(@NotNull ArrayList<E> arrayList, ArrayList<E> arrayList2) {
        arrayList.ensureCapacity(arrayList2.size() + arrayList.size());
        int size = arrayList2.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(arrayList2.get(i));
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0017, code lost:
        r4 = kotlin.collections.CollectionsKt__CollectionsKt.getLastIndex(r1);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.util.List<kotlin.jvm.functions.Function3<io.ktor.util.pipeline.PipelineContext<TSubject, TContext>, TSubject, kotlin.coroutines.Continuation<? super kotlin.Unit>, java.lang.Object>> cacheInterceptors() {
        /*
            r8 = this;
            int r0 = r8.interceptorsQuantity
            if (r0 != 0) goto L10
            java.util.List r0 = kotlin.collections.CollectionsKt.emptyList()
            r8.notSharedInterceptorsList(r0)
            java.util.List r0 = kotlin.collections.CollectionsKt.emptyList()
            return r0
        L10:
            java.util.ArrayList<java.lang.Object> r1 = r8.phasesRaw
            r2 = 0
            r3 = 0
            r4 = 1
            if (r0 != r4) goto L3e
            int r4 = kotlin.collections.CollectionsKt.getLastIndex(r1)
            if (r4 < 0) goto L3e
            r5 = r3
        L1e:
            java.lang.Object r6 = r1.get(r5)
            boolean r7 = r6 instanceof io.ktor.util.pipeline.Pipeline.PhaseContent
            if (r7 != 0) goto L27
            r6 = r2
        L27:
            io.ktor.util.pipeline.Pipeline$PhaseContent r6 = (io.ktor.util.pipeline.Pipeline.PhaseContent) r6
            if (r6 == 0) goto L39
            boolean r7 = r6.isEmpty()
            if (r7 != 0) goto L39
            java.util.ArrayList r0 = r6.sharedInterceptors()
            r8.setInterceptorsListFromPhase(r6)
            return r0
        L39:
            if (r5 == r4) goto L3e
            int r5 = r5 + 1
            goto L1e
        L3e:
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>(r0)
            int r0 = kotlin.collections.CollectionsKt.getLastIndex(r1)
            if (r0 < 0) goto L5e
        L49:
            java.lang.Object r5 = r1.get(r3)
            boolean r6 = r5 instanceof io.ktor.util.pipeline.Pipeline.PhaseContent
            if (r6 != 0) goto L52
            r5 = r2
        L52:
            io.ktor.util.pipeline.Pipeline$PhaseContent r5 = (io.ktor.util.pipeline.Pipeline.PhaseContent) r5
            if (r5 == 0) goto L59
            r5.addTo(r4)
        L59:
            if (r3 == r0) goto L5e
            int r3 = r3 + 1
            goto L49
        L5e:
            r8.notSharedInterceptorsList(r4)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.util.pipeline.Pipeline.cacheInterceptors():java.util.List");
    }

    private final boolean fastPathMerge(Pipeline<TSubject, TContext> pipeline) {
        int lastIndex;
        if (pipeline.phasesRaw.isEmpty()) {
            return true;
        }
        int i = 0;
        if (!this.phasesRaw.isEmpty()) {
            return false;
        }
        ArrayList<Object> arrayList = pipeline.phasesRaw;
        lastIndex = CollectionsKt__CollectionsKt.getLastIndex(arrayList);
        if (lastIndex >= 0) {
            while (true) {
                Object obj = arrayList.get(i);
                Intrinsics.checkExpressionValueIsNotNull(obj, "fromPhases[index]");
                if (obj instanceof PipelinePhase) {
                    this.phasesRaw.add(obj);
                } else if (obj instanceof PhaseContent) {
                    PhaseContent phaseContent = (PhaseContent) obj;
                    this.phasesRaw.add(new PhaseContent(phaseContent.getPhase(), phaseContent.getRelation(), phaseContent.sharedInterceptors()));
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        this.interceptorsQuantity += pipeline.interceptorsQuantity;
        setInterceptorsListFromAnotherPipeline(pipeline);
        return true;
    }

    private final PhaseContent<TSubject, TContext> findPhase(PipelinePhase pipelinePhase) {
        ArrayList<Object> arrayList = this.phasesRaw;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            Object obj = arrayList.get(i);
            Intrinsics.checkExpressionValueIsNotNull(obj, "phasesList[index]");
            if (obj == pipelinePhase) {
                PhaseContent<TSubject, TContext> phaseContent = new PhaseContent<>(pipelinePhase, PipelinePhaseRelation.Last.INSTANCE);
                arrayList.set(i, phaseContent);
                return phaseContent;
            }
            if (obj instanceof PhaseContent) {
                PhaseContent<TSubject, TContext> phaseContent2 = (PhaseContent) obj;
                if (phaseContent2.getPhase() == pipelinePhase) {
                    return phaseContent2;
                }
            }
        }
        return null;
    }

    private final int findPhaseIndex(PipelinePhase pipelinePhase) {
        ArrayList<Object> arrayList = this.phasesRaw;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            Object obj = arrayList.get(i);
            Intrinsics.checkExpressionValueIsNotNull(obj, "phasesList[index]");
            if (obj == pipelinePhase) {
                return i;
            }
            if ((obj instanceof PhaseContent) && ((PhaseContent) obj).getPhase() == pipelinePhase) {
                return i;
            }
        }
        return -1;
    }

    private final boolean hasPhase(PipelinePhase pipelinePhase) {
        ArrayList<Object> arrayList = this.phasesRaw;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            Object obj = arrayList.get(i);
            Intrinsics.checkExpressionValueIsNotNull(obj, "phasesList[index]");
            if (obj == pipelinePhase) {
                return true;
            }
            if ((obj instanceof PhaseContent) && ((PhaseContent) obj).getPhase() == pipelinePhase) {
                return true;
            }
        }
        return false;
    }

    @InternalAPI
    public static /* synthetic */ void isEmpty$annotations() {
    }

    private final void notSharedInterceptorsList(List<? extends Function3<? super PipelineContext<TSubject, TContext>, ? super TSubject, ? super Continuation<? super Unit>, ? extends Object>> list) {
        this.interceptors = list;
        this.interceptorsListShared = false;
        this.interceptorsListSharedPhase = null;
    }

    private final void resetInterceptorsList() {
        this.interceptors = null;
        this.interceptorsListShared = false;
        this.interceptorsListSharedPhase = null;
    }

    private final void setInterceptorsListFromAnotherPipeline(Pipeline<TSubject, TContext> pipeline) {
        this.interceptors = pipeline.sharedInterceptorsList();
        this.interceptorsListShared = true;
        this.interceptorsListSharedPhase = null;
    }

    private final void setInterceptorsListFromPhase(PhaseContent<TSubject, TContext> phaseContent) {
        this.interceptors = phaseContent.sharedInterceptors();
        this.interceptorsListShared = false;
        this.interceptorsListSharedPhase = phaseContent.getPhase();
    }

    private final List<Function3<PipelineContext<TSubject, TContext>, TSubject, Continuation<? super Unit>, Object>> sharedInterceptorsList() {
        if (this.interceptors == null) {
            cacheInterceptors();
        }
        this.interceptorsListShared = true;
        List list = (List<? extends Function3<? super PipelineContext<TSubject, TContext>, ? super TSubject, ? super Continuation<? super Unit>, ? extends Object>>) this.interceptors;
        if (list == null) {
            Intrinsics.throwNpe();
        }
        return list;
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0041, code lost:
        if (r0 == r3) goto L22;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final boolean tryAddToPhaseFastpath(io.ktor.util.pipeline.PipelinePhase r5, kotlin.jvm.functions.Function3<? super io.ktor.util.pipeline.PipelineContext<TSubject, TContext>, ? super TSubject, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> r6) {
        /*
            r4 = this;
            java.util.ArrayList<java.lang.Object> r0 = r4.phasesRaw
            boolean r0 = r0.isEmpty()
            r1 = 0
            if (r0 == 0) goto La
            return r1
        La:
            java.util.List<? extends kotlin.jvm.functions.Function3<? super io.ktor.util.pipeline.PipelineContext<TSubject, TContext>, ? super TSubject, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object>> r0 = r4.interceptors
            if (r0 != 0) goto Lf
            return r1
        Lf:
            boolean r0 = r4.interceptorsListShared
            if (r0 != 0) goto L6b
            io.ktor.util.pipeline.PipelinePhase r0 = r4.interceptorsListSharedPhase
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r5)
            r2 = 1
            if (r0 == 0) goto L2b
            java.util.List<? extends kotlin.jvm.functions.Function3<? super io.ktor.util.pipeline.PipelineContext<TSubject, TContext>, ? super TSubject, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object>> r0 = r4.interceptors
            boolean r3 = kotlin.jvm.internal.TypeIntrinsics.isMutableList(r0)
            if (r3 != 0) goto L25
            r0 = 0
        L25:
            if (r0 == 0) goto L2b
            r0.add(r6)
            return r2
        L2b:
            java.util.ArrayList<java.lang.Object> r0 = r4.phasesRaw
            java.lang.Object r0 = kotlin.collections.CollectionsKt.last(r0)
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r0)
            if (r0 != 0) goto L43
            int r0 = r4.findPhaseIndex(r5)
            java.util.ArrayList<java.lang.Object> r3 = r4.phasesRaw
            int r3 = kotlin.collections.CollectionsKt.getLastIndex(r3)
            if (r0 != r3) goto L6b
        L43:
            java.util.List<? extends kotlin.jvm.functions.Function3<? super io.ktor.util.pipeline.PipelineContext<TSubject, TContext>, ? super TSubject, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object>> r0 = r4.interceptors
            boolean r0 = kotlin.jvm.internal.TypeIntrinsics.isMutableList(r0)
            if (r0 == 0) goto L6b
            io.ktor.util.pipeline.Pipeline$PhaseContent r5 = r4.findPhase(r5)
            if (r5 != 0) goto L54
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L54:
            r5.addInterceptor(r6)
            java.util.List<? extends kotlin.jvm.functions.Function3<? super io.ktor.util.pipeline.PipelineContext<TSubject, TContext>, ? super TSubject, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object>> r5 = r4.interceptors
            if (r5 == 0) goto L63
            java.util.List r5 = kotlin.jvm.internal.TypeIntrinsics.asMutableList(r5)
            r5.add(r6)
            return r2
        L63:
            kotlin.TypeCastException r5 = new kotlin.TypeCastException
        */
        //  java.lang.String r6 = "null cannot be cast to non-null type kotlin.collections.MutableList<io.ktor.util.pipeline.PipelineInterceptor<TSubject, TContext> /* = suspend io.ktor.util.pipeline.PipelineContext<TSubject, TContext>.(TSubject) -> kotlin.Unit */>"
        /*
            r5.<init>(r6)
            throw r5
        L6b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.util.pipeline.Pipeline.tryAddToPhaseFastpath(io.ktor.util.pipeline.PipelinePhase, kotlin.jvm.functions.Function3):boolean");
    }

    public final void addPhase(@NotNull PipelinePhase phase) {
        Intrinsics.checkParameterIsNotNull(phase, "phase");
        if (hasPhase(phase)) {
            return;
        }
        this.phasesRaw.add(phase);
    }

    public void afterIntercepted() {
    }

    @NotNull
    public final PipelineExecutor<TSubject> createContext$ktor_utils(@NotNull TContext context, @NotNull TSubject subject) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(subject, "subject");
        return PipelineContextKt.pipelineExecutorFor(context, sharedInterceptorsList(), subject);
    }

    @Nullable
    public final Object execute(@NotNull TContext tcontext, @NotNull TSubject tsubject, @NotNull Continuation<? super TSubject> continuation) {
        return createContext$ktor_utils(tcontext, tsubject).execute(tsubject, continuation);
    }

    @NotNull
    public final Attributes getAttributes() {
        return this.attributes;
    }

    @NotNull
    public final List<PipelinePhase> getItems() {
        int collectionSizeOrDefault;
        ArrayList<Object> arrayList = this.phasesRaw;
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10);
        ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault);
        for (Object obj : arrayList) {
            PipelinePhase pipelinePhase = null;
            PipelinePhase pipelinePhase2 = (PipelinePhase) (!(obj instanceof PipelinePhase) ? null : obj);
            if (pipelinePhase2 == null) {
                if (!(obj instanceof PhaseContent)) {
                    obj = null;
                }
                PhaseContent phaseContent = (PhaseContent) obj;
                if (phaseContent != null) {
                    pipelinePhase = phaseContent.getPhase();
                }
                pipelinePhase2 = pipelinePhase;
                if (pipelinePhase2 == null) {
                    Intrinsics.throwNpe();
                }
            }
            arrayList2.add(pipelinePhase2);
        }
        return arrayList2;
    }

    public final void insertPhaseAfter(@NotNull PipelinePhase reference, @NotNull PipelinePhase phase) {
        Intrinsics.checkParameterIsNotNull(reference, "reference");
        Intrinsics.checkParameterIsNotNull(phase, "phase");
        if (hasPhase(phase)) {
            return;
        }
        int findPhaseIndex = findPhaseIndex(reference);
        if (findPhaseIndex != -1) {
            this.phasesRaw.add(findPhaseIndex + 1, new PhaseContent(phase, new PipelinePhaseRelation.After(reference)));
            return;
        }
        throw new InvalidPhaseException("Phase " + reference + " was not registered for this pipeline");
    }

    public final void insertPhaseBefore(@NotNull PipelinePhase reference, @NotNull PipelinePhase phase) {
        Intrinsics.checkParameterIsNotNull(reference, "reference");
        Intrinsics.checkParameterIsNotNull(phase, "phase");
        if (hasPhase(phase)) {
            return;
        }
        int findPhaseIndex = findPhaseIndex(reference);
        if (findPhaseIndex != -1) {
            this.phasesRaw.add(findPhaseIndex, new PhaseContent(phase, new PipelinePhaseRelation.Before(reference)));
            return;
        }
        throw new InvalidPhaseException("Phase " + reference + " was not registered for this pipeline");
    }

    public final void intercept(@NotNull PipelinePhase phase, @NotNull Function3<? super PipelineContext<TSubject, TContext>, ? super TSubject, ? super Continuation<? super Unit>, ? extends Object> block) {
        Intrinsics.checkParameterIsNotNull(phase, "phase");
        Intrinsics.checkParameterIsNotNull(block, "block");
        PhaseContent<TSubject, TContext> findPhase = findPhase(phase);
        if (findPhase != null) {
            if (tryAddToPhaseFastpath(phase, block)) {
                this.interceptorsQuantity++;
                return;
            }
            findPhase.addInterceptor(block);
            this.interceptorsQuantity++;
            resetInterceptorsList();
            afterIntercepted();
            return;
        }
        throw new InvalidPhaseException("Phase " + phase + " was not registered for this pipeline");
    }

    @NotNull
    public final List<Function3<PipelineContext<TSubject, TContext>, TSubject, Continuation<? super Unit>, Object>> interceptorsForTests$ktor_utils() {
        List list = (List<? extends Function3<? super PipelineContext<TSubject, TContext>, ? super TSubject, ? super Continuation<? super Unit>, ? extends Object>>) this.interceptors;
        return list != null ? list : cacheInterceptors();
    }

    public final boolean isEmpty() {
        return this.interceptorsQuantity == 0;
    }

    public final void merge(@NotNull Pipeline<TSubject, TContext> from) {
        int lastIndex;
        PipelinePhaseRelation relation;
        Intrinsics.checkParameterIsNotNull(from, "from");
        if (fastPathMerge(from)) {
            return;
        }
        if (this.interceptorsQuantity == 0) {
            setInterceptorsListFromAnotherPipeline(from);
        } else {
            resetInterceptorsList();
        }
        ArrayList<Object> arrayList = from.phasesRaw;
        int i = 0;
        lastIndex = CollectionsKt__CollectionsKt.getLastIndex(arrayList);
        if (lastIndex < 0) {
            return;
        }
        while (true) {
            Object obj = arrayList.get(i);
            Intrinsics.checkExpressionValueIsNotNull(obj, "fromPhases[index]");
            PipelinePhase pipelinePhase = (PipelinePhase) (!(obj instanceof PipelinePhase) ? null : obj);
            if (pipelinePhase == null) {
                pipelinePhase = ((PhaseContent) obj).getPhase();
            }
            if (!hasPhase(pipelinePhase)) {
                if (obj == pipelinePhase) {
                    relation = PipelinePhaseRelation.Last.INSTANCE;
                } else {
                    relation = ((PhaseContent) obj).getRelation();
                }
                if (relation instanceof PipelinePhaseRelation.Last) {
                    addPhase(pipelinePhase);
                } else if (relation instanceof PipelinePhaseRelation.Before) {
                    insertPhaseBefore(((PipelinePhaseRelation.Before) relation).getRelativeTo(), pipelinePhase);
                } else if (relation instanceof PipelinePhaseRelation.After) {
                    insertPhaseAfter(((PipelinePhaseRelation.After) relation).getRelativeTo(), pipelinePhase);
                }
            }
            if (obj instanceof PhaseContent) {
                PhaseContent phaseContent = (PhaseContent) obj;
                if (!phaseContent.isEmpty()) {
                    PhaseContent<TSubject, TContext> findPhase = findPhase(pipelinePhase);
                    if (findPhase == null) {
                        Intrinsics.throwNpe();
                    }
                    phaseContent.addTo(findPhase);
                    this.interceptorsQuantity += phaseContent.getSize();
                }
            }
            if (i == lastIndex) {
                return;
            }
            i++;
        }
    }

    @NotNull
    public final List<Function3<PipelineContext<TSubject, TContext>, TSubject, Continuation<? super Unit>, Object>> phaseInterceptors$ktor_utils(@NotNull PipelinePhase phase) {
        List<Function3<PipelineContext<TSubject, TContext>, TSubject, Continuation<? super Unit>, Object>> emptyList;
        ArrayList<Function3<PipelineContext<TSubject, TContext>, TSubject, Continuation<? super Unit>, Object>> sharedInterceptors;
        Intrinsics.checkParameterIsNotNull(phase, "phase");
        PhaseContent<TSubject, TContext> findPhase = findPhase(phase);
        if (findPhase == null || (sharedInterceptors = findPhase.sharedInterceptors()) == null) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        return sharedInterceptors;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: Pipeline.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0002\u0018\u0000 +*\b\b\u0002\u0010\u0001*\u00020\u0002*\b\b\u0003\u0010\u0003*\u00020\u00022\u00020\u0002:\u0001+B\u0017\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bB\u0096\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012|\u0010\t\u001ax\u00127\u00125\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\f\u0012\u0004\u0012\u00028\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u000b¢\u0006\u0002\b\u000f0\nj;\u00127\u00125\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\f\u0012\u0004\u0012\u00028\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u000b¢\u0006\u0002\b\u000f`\u0010ø\u0001\u0000¢\u0006\u0002\u0010\u0011JI\u0010!\u001a\u00020\u000e29\u0010\"\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\f\u0012\u0004\u0012\u00028\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u000b¢\u0006\u0002\b\u000fø\u0001\u0000¢\u0006\u0002\u0010#J\u001a\u0010$\u001a\u00020\u000e2\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0000J\u0087\u0001\u0010$\u001a\u00020\u000e2|\u0010%\u001ax\u00127\u00125\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\f\u0012\u0004\u0012\u00028\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u000b¢\u0006\u0002\b\u000f0\nj;\u00127\u00125\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\f\u0012\u0004\u0012\u00028\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u000b¢\u0006\u0002\b\u000f`\u0010ø\u0001\u0000J\u007f\u0010&\u001ax\u00127\u00125\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\f\u0012\u0004\u0012\u00028\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u000b¢\u0006\u0002\b\u000f0\nj;\u00127\u00125\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\f\u0012\u0004\u0012\u00028\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u000b¢\u0006\u0002\b\u000f`\u0010ø\u0001\u0000J\b\u0010'\u001a\u00020\u000eH\u0002J\u007f\u0010(\u001ax\u00127\u00125\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\f\u0012\u0004\u0012\u00028\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u000b¢\u0006\u0002\b\u000f0\nj;\u00127\u00125\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\f\u0012\u0004\u0012\u00028\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u000b¢\u0006\u0002\b\u000f`\u0010ø\u0001\u0000J\b\u0010)\u001a\u00020*H\u0016R\u0087\u0001\u0010\t\u001ax\u00127\u00125\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\f\u0012\u0004\u0012\u00028\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u000b¢\u0006\u0002\b\u000f0\nj;\u00127\u00125\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\f\u0012\u0004\u0012\u00028\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u000b¢\u0006\u0002\b\u000f`\u0010X\u0082\u000eø\u0001\u0000¢\u0006\u0002\n\u0000R\u0011\u0010\u0012\u001a\u00020\u00138F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0014\"\u0004\b\u001b\u0010\u001cR\u0011\u0010\u001d\u001a\u00020\u001e8F¢\u0006\u0006\u001a\u0004\b\u001f\u0010 \u0082\u0002\u0004\n\u0002\b\u0019¨\u0006,"}, d2 = {"Lio/ktor/util/pipeline/Pipeline$PhaseContent;", "TSubject", "", "Call", "phase", "Lio/ktor/util/pipeline/PipelinePhase;", "relation", "Lio/ktor/util/pipeline/Pipeline$PipelinePhaseRelation;", "(Lio/ktor/util/pipeline/PipelinePhase;Lio/ktor/util/pipeline/Pipeline$PipelinePhaseRelation;)V", "interceptors", "Ljava/util/ArrayList;", "Lkotlin/Function3;", "Lio/ktor/util/pipeline/PipelineContext;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "Lkotlin/collections/ArrayList;", "(Lio/ktor/util/pipeline/PipelinePhase;Lio/ktor/util/pipeline/Pipeline$PipelinePhaseRelation;Ljava/util/ArrayList;)V", "isEmpty", "", "()Z", "getPhase", "()Lio/ktor/util/pipeline/PipelinePhase;", "getRelation", "()Lio/ktor/util/pipeline/Pipeline$PipelinePhaseRelation;", "shared", "getShared", "setShared", "(Z)V", "size", "", "getSize", "()I", "addInterceptor", "interceptor", "(Lkotlin/jvm/functions/Function3;)V", "addTo", "destination", "copiedInterceptors", "copyInterceptors", "sharedInterceptors", "toString", "", "Companion", "ktor-utils"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class PhaseContent<TSubject, Call> {
        public static final Companion Companion = new Companion(null);
        @NotNull
        private static final ArrayList<Object> SharedArrayList = new ArrayList<>(0);
        private ArrayList<Function3<PipelineContext<TSubject, Call>, TSubject, Continuation<? super Unit>, Object>> interceptors;
        @NotNull
        private final PipelinePhase phase;
        @NotNull
        private final PipelinePhaseRelation relation;
        private boolean shared;

        /* compiled from: Pipeline.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R%\u0010\u0003\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004j\n\u0012\u0006\u0012\u0004\u0018\u00010\u0001`\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lio/ktor/util/pipeline/Pipeline$PhaseContent$Companion;", "", "()V", "SharedArrayList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getSharedArrayList", "()Ljava/util/ArrayList;", "ktor-utils"}, k = 1, mv = {1, 1, 13})
        /* loaded from: classes3.dex */
        public static final class Companion {
            private Companion() {
            }

            @NotNull
            public final ArrayList<Object> getSharedArrayList() {
                return PhaseContent.SharedArrayList;
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        public PhaseContent(@NotNull PipelinePhase phase, @NotNull PipelinePhaseRelation relation, @NotNull ArrayList<Function3<PipelineContext<TSubject, Call>, TSubject, Continuation<? super Unit>, Object>> interceptors) {
            Intrinsics.checkParameterIsNotNull(phase, "phase");
            Intrinsics.checkParameterIsNotNull(relation, "relation");
            Intrinsics.checkParameterIsNotNull(interceptors, "interceptors");
            this.phase = phase;
            this.relation = relation;
            this.interceptors = interceptors;
            this.shared = true;
        }

        private final void copyInterceptors() {
            this.interceptors = copiedInterceptors();
            this.shared = false;
        }

        public final void addInterceptor(@NotNull Function3<? super PipelineContext<TSubject, Call>, ? super TSubject, ? super Continuation<? super Unit>, ? extends Object> interceptor) {
            Intrinsics.checkParameterIsNotNull(interceptor, "interceptor");
            if (this.shared) {
                copyInterceptors();
            }
            this.interceptors.add(interceptor);
        }

        public final void addTo(@NotNull ArrayList<Function3<PipelineContext<TSubject, Call>, TSubject, Continuation<? super Unit>, Object>> destination) {
            Intrinsics.checkParameterIsNotNull(destination, "destination");
            ArrayList<Function3<PipelineContext<TSubject, Call>, TSubject, Continuation<? super Unit>, Object>> arrayList = this.interceptors;
            destination.ensureCapacity(arrayList.size() + destination.size());
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                destination.add(arrayList.get(i));
            }
        }

        @NotNull
        public final ArrayList<Function3<PipelineContext<TSubject, Call>, TSubject, Continuation<? super Unit>, Object>> copiedInterceptors() {
            return new ArrayList<>(this.interceptors);
        }

        @NotNull
        public final PipelinePhase getPhase() {
            return this.phase;
        }

        @NotNull
        public final PipelinePhaseRelation getRelation() {
            return this.relation;
        }

        public final boolean getShared() {
            return this.shared;
        }

        public final int getSize() {
            return this.interceptors.size();
        }

        public final boolean isEmpty() {
            return this.interceptors.isEmpty();
        }

        public final void setShared(boolean z) {
            this.shared = z;
        }

        @NotNull
        public final ArrayList<Function3<PipelineContext<TSubject, Call>, TSubject, Continuation<? super Unit>, Object>> sharedInterceptors() {
            this.shared = true;
            return this.interceptors;
        }

        @NotNull
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Phase `");
            outline107.append(this.phase.getName());
            outline107.append("`, ");
            return GeneratedOutlineSupport1.outline86(outline107, getSize(), " handlers");
        }

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public PhaseContent(@org.jetbrains.annotations.NotNull io.ktor.util.pipeline.PipelinePhase r2, @org.jetbrains.annotations.NotNull io.ktor.util.pipeline.Pipeline.PipelinePhaseRelation r3) {
            /*
                r1 = this;
                java.lang.String r0 = "phase"
                kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r0)
                java.lang.String r0 = "relation"
                kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r0)
                java.util.ArrayList<java.lang.Object> r0 = io.ktor.util.pipeline.Pipeline.PhaseContent.SharedArrayList
                if (r0 == 0) goto L26
                r1.<init>(r2, r3, r0)
                java.util.ArrayList<java.lang.Object> r2 = io.ktor.util.pipeline.Pipeline.PhaseContent.SharedArrayList
                boolean r2 = r2.isEmpty()
                if (r2 == 0) goto L1a
                return
            L1a:
                java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
                java.lang.String r3 = "The shared empty array list has been modified"
                java.lang.String r3 = r3.toString()
                r2.<init>(r3)
                throw r2
            L26:
                kotlin.TypeCastException r2 = new kotlin.TypeCastException
            */
            //  java.lang.String r3 = "null cannot be cast to non-null type kotlin.collections.ArrayList<io.ktor.util.pipeline.PipelineInterceptor<TSubject, Call> /* = suspend io.ktor.util.pipeline.PipelineContext<TSubject, Call>.(TSubject) -> kotlin.Unit */> /* = java.util.ArrayList<suspend io.ktor.util.pipeline.PipelineContext<TSubject, Call>.(TSubject) -> kotlin.Unit> */"
            /*
                r2.<init>(r3)
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.util.pipeline.Pipeline.PhaseContent.<init>(io.ktor.util.pipeline.PipelinePhase, io.ktor.util.pipeline.Pipeline$PipelinePhaseRelation):void");
        }

        public final void addTo(@NotNull PhaseContent<TSubject, Call> destination) {
            Intrinsics.checkParameterIsNotNull(destination, "destination");
            if (isEmpty()) {
                return;
            }
            if (destination.isEmpty()) {
                destination.interceptors = sharedInterceptors();
                destination.shared = true;
                return;
            }
            if (destination.shared) {
                destination.copyInterceptors();
            }
            addTo(destination.interceptors);
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Pipeline(@NotNull PipelinePhase phase, @NotNull List<? extends Function3<? super PipelineContext<TSubject, TContext>, ? super TSubject, ? super Continuation<? super Unit>, ? extends Object>> interceptors) {
        this(phase);
        Intrinsics.checkParameterIsNotNull(phase, "phase");
        Intrinsics.checkParameterIsNotNull(interceptors, "interceptors");
        for (Function3<? super PipelineContext<TSubject, TContext>, ? super TSubject, ? super Continuation<? super Unit>, ? extends Object> function3 : interceptors) {
            intercept(phase, function3);
        }
    }
}
