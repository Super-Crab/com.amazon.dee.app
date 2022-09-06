package com.amazon.photos.autosave.internal.workers;

import com.amazon.photos.autosave.model.MediaType;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: AutosaveWorker.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lcom/amazon/photos/autosave/model/MediaType;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
final class AutosaveWorker$handleMediaType$2 extends Lambda implements Function0<MediaType> {
    final /* synthetic */ AutosaveWorker this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AutosaveWorker$handleMediaType$2(AutosaveWorker autosaveWorker) {
        super(0);
        this.this$0 = autosaveWorker;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke */
    public final MediaType mo12560invoke() {
        return MediaType.valueOf(this.this$0.handleMediaTypeStr);
    }
}
