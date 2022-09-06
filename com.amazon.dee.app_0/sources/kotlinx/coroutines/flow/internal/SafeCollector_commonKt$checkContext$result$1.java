package kotlinx.coroutines.flow.internal;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SafeCollector.common.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "count", "element", "Lkotlin/coroutines/CoroutineContext$Element;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SafeCollector_commonKt$checkContext$result$1 extends Lambda implements Function2<Integer, CoroutineContext.Element, Integer> {
    final /* synthetic */ SafeCollector $this_checkContext;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SafeCollector_commonKt$checkContext$result$1(SafeCollector safeCollector) {
        super(2);
        this.$this_checkContext = safeCollector;
    }

    public final int invoke(int i, @NotNull CoroutineContext.Element element) {
        CoroutineContext.Key<?> key = element.getKey();
        CoroutineContext.Element element2 = this.$this_checkContext.collectContext.get(key);
        if (key != Job.Key) {
            if (element == element2) {
                return i + 1;
            }
            return Integer.MIN_VALUE;
        }
        Job job = (Job) element2;
        Job transitiveCoroutineParent = SafeCollector_commonKt.transitiveCoroutineParent((Job) element, job);
        if (transitiveCoroutineParent == job) {
            return job == null ? i : i + 1;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Flow invariant is violated:\n\t\tEmission from another coroutine is detected.\n");
        sb.append("\t\tChild of ");
        sb.append(transitiveCoroutineParent);
        sb.append(", expected child of ");
        sb.append(job);
        throw new IllegalStateException(GeneratedOutlineSupport1.outline92(sb, ".\n", "\t\tFlowCollector is not thread-safe and concurrent emissions are prohibited.\n", "\t\tTo mitigate this restriction please use 'channelFlow' builder instead of 'flow'").toString());
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Integer mo12248invoke(Integer num, CoroutineContext.Element element) {
        return Integer.valueOf(invoke(num.intValue(), element));
    }
}
