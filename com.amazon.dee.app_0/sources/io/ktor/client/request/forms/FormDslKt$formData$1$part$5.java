package io.ktor.client.request.forms;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlinx.io.core.Input;
import org.jetbrains.annotations.NotNull;
/* compiled from: formDsl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lkotlinx/io/core/Input;", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes3.dex */
final class FormDslKt$formData$1$part$5 extends Lambda implements Function0<Input> {
    final /* synthetic */ Object $value;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FormDslKt$formData$1$part$5(Object obj) {
        super(0);
        this.$value = obj;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final Input mo12560invoke() {
        return (Input) this.$value;
    }
}
