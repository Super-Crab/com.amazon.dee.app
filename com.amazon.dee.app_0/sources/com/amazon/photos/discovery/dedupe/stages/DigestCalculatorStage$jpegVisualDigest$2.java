package com.amazon.photos.discovery.dedupe.stages;

import com.amazon.imageutilities.JpegVisualDigest;
import com.amazon.photos.discovery.internal.util.FileUtils;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DigestCalculatorStage.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lcom/amazon/imageutilities/JpegVisualDigest;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class DigestCalculatorStage$jpegVisualDigest$2 extends Lambda implements Function0<JpegVisualDigest> {
    final /* synthetic */ DigestCalculatorStage this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DigestCalculatorStage$jpegVisualDigest$2(DigestCalculatorStage digestCalculatorStage) {
        super(0);
        this.this$0 = digestCalculatorStage;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke */
    public final JpegVisualDigest mo12560invoke() {
        return FileUtils.createJpegVisualDigest$default(this.this$0.getFileUtils$AndroidPhotosDiscovery_release(), null, 1, null);
    }
}
