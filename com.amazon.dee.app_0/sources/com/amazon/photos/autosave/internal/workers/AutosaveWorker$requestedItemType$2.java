package com.amazon.photos.autosave.internal.workers;

import com.amazon.photos.autosave.model.ModelExtensionsKt;
import com.amazon.photos.discovery.model.ItemType;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: AutosaveWorker.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lcom/amazon/photos/discovery/model/ItemType;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
final class AutosaveWorker$requestedItemType$2 extends Lambda implements Function0<Set<ItemType>> {
    final /* synthetic */ AutosaveWorker this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AutosaveWorker$requestedItemType$2(AutosaveWorker autosaveWorker) {
        super(0);
        this.this$0 = autosaveWorker;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final Set<ItemType> mo12560invoke() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        if (this.this$0.getAutosavePreferences().isAutosaveEnabled(this.this$0.getHandleMediaType())) {
            linkedHashSet.add(ModelExtensionsKt.toItemType(this.this$0.getHandleMediaType()));
        }
        return linkedHashSet;
    }
}
