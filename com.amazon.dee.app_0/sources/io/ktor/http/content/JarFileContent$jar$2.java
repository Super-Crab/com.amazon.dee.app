package io.ktor.http.content;

import java.util.jar.JarFile;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: JarFileContent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljava/util/jar/JarFile;", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes3.dex */
final class JarFileContent$jar$2 extends Lambda implements Function0<JarFile> {
    final /* synthetic */ JarFileContent this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JarFileContent$jar$2(JarFileContent jarFileContent) {
        super(0);
        this.this$0 = jarFileContent;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final JarFile mo12560invoke() {
        return new JarFile(this.this$0.getJarFile());
    }
}
