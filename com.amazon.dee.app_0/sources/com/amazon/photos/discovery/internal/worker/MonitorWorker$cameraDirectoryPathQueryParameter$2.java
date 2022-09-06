package com.amazon.photos.discovery.internal.worker;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: MonitorWorker.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
final class MonitorWorker$cameraDirectoryPathQueryParameter$2 extends Lambda implements Function0<String> {
    final /* synthetic */ MonitorWorker this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MonitorWorker$cameraDirectoryPathQueryParameter$2(MonitorWorker monitorWorker) {
        super(0);
        this.this$0 = monitorWorker;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final String mo12560invoke() {
        File cameraDirectory = this.this$0.getFileUtils$AndroidPhotosDiscovery_release().getCameraDirectory();
        if (cameraDirectory.exists()) {
            return cameraDirectory.getAbsolutePath() + "%";
        }
        return "";
    }
}
