package io.ktor.http.content;

import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: JarFileContent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Ljava/util/jar/JarEntry;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class JarFileContent$jarEntry$2 extends Lambda implements Function0<JarEntry> {
    final /* synthetic */ JarFileContent this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JarFileContent$jarEntry$2(JarFileContent jarFileContent) {
        super(0);
        this.this$0 = jarFileContent;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke  reason: collision with other method in class */
    public final JarEntry mo12560invoke() {
        JarFile jar;
        jar = this.this$0.getJar();
        return jar.getJarEntry(this.this$0.getResourcePath());
    }
}
