package io.ktor.http.content;

import com.android.tools.r8.GeneratedOutlineSupport1;
import io.ktor.http.ContentType;
import io.ktor.http.content.OutgoingContent;
import io.ktor.util.cio.ByteBufferPoolKt;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.util.Collection;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.coroutines.io.ByteReadChannel;
import kotlinx.coroutines.io.jvm.javaio.ReadingKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: JarFileContent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bB\u001d\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\u000bJ\b\u0010#\u001a\u00020$H\u0016R\u0016\u0010\f\u001a\u0004\u0018\u00010\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0012\u001a\u00020\u00138BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0014\u0010\u0015R#\u0010\u0018\u001a\n \u001a*\u0004\u0018\u00010\u00190\u00198BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001d\u0010\u0017\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u000e\u0010 \u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"¨\u0006%"}, d2 = {"Lio/ktor/http/content/JarFileContent;", "Lio/ktor/http/content/OutgoingContent$ReadChannelContent;", "zipFilePath", "Ljava/nio/file/Path;", "resourcePath", "", "contentType", "Lio/ktor/http/ContentType;", "(Ljava/nio/file/Path;Ljava/lang/String;Lio/ktor/http/ContentType;)V", "jarFile", "Ljava/io/File;", "(Ljava/io/File;Ljava/lang/String;Lio/ktor/http/ContentType;)V", "contentLength", "", "getContentLength", "()Ljava/lang/Long;", "getContentType", "()Lio/ktor/http/ContentType;", "jar", "Ljava/util/jar/JarFile;", "getJar", "()Ljava/util/jar/JarFile;", "jar$delegate", "Lkotlin/Lazy;", "jarEntry", "Ljava/util/jar/JarEntry;", "kotlin.jvm.PlatformType", "getJarEntry", "()Ljava/util/jar/JarEntry;", "jarEntry$delegate", "getJarFile", "()Ljava/io/File;", "normalized", "getResourcePath", "()Ljava/lang/String;", "readFrom", "Lkotlinx/coroutines/io/ByteReadChannel;", "ktor-http"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class JarFileContent extends OutgoingContent.ReadChannelContent {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(JarFileContent.class), "jarEntry", "getJarEntry()Ljava/util/jar/JarEntry;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(JarFileContent.class), "jar", "getJar()Ljava/util/jar/JarFile;"))};
    @NotNull
    private final ContentType contentType;
    private final Lazy jar$delegate;
    private final Lazy jarEntry$delegate;
    @NotNull
    private final File jarFile;
    private final String normalized;
    @NotNull
    private final String resourcePath;

    public JarFileContent(@NotNull File jarFile, @NotNull String resourcePath, @NotNull ContentType contentType) {
        String replace$default;
        Lazy lazy;
        Lazy lazy2;
        boolean startsWith$default;
        List plus;
        Intrinsics.checkParameterIsNotNull(jarFile, "jarFile");
        Intrinsics.checkParameterIsNotNull(resourcePath, "resourcePath");
        Intrinsics.checkParameterIsNotNull(contentType, "contentType");
        this.jarFile = jarFile;
        this.resourcePath = resourcePath;
        this.contentType = contentType;
        replace$default = StringsKt__StringsJVMKt.replace$default(Paths.get(this.resourcePath, new String[0]).normalize().toString(), File.separatorChar, '/', false, 4, (Object) null);
        this.normalized = replace$default;
        lazy = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new JarFileContent$jarEntry$2(this));
        this.jarEntry$delegate = lazy;
        lazy2 = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new JarFileContent$jar$2(this));
        this.jar$delegate = lazy2;
        startsWith$default = StringsKt__StringsJVMKt.startsWith$default(this.normalized, "..", false, 2, null);
        if (!startsWith$default) {
            List<Version> versions = VersionsKt.getVersions(this);
            JarEntry jarEntry = getJarEntry();
            Intrinsics.checkExpressionValueIsNotNull(jarEntry, "jarEntry");
            FileTime lastModifiedTime = jarEntry.getLastModifiedTime();
            Intrinsics.checkExpressionValueIsNotNull(lastModifiedTime, "jarEntry.lastModifiedTime");
            plus = CollectionsKt___CollectionsKt.plus((Collection<? extends Object>) ((Collection) versions), (Object) LastModifiedJavaTimeKt.LastModifiedVersion(lastModifiedTime));
            VersionsKt.setVersions(this, plus);
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Bad resource relative path ");
        outline107.append(this.resourcePath);
        throw new IllegalArgumentException(outline107.toString().toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final JarFile getJar() {
        Lazy lazy = this.jar$delegate;
        KProperty kProperty = $$delegatedProperties[1];
        return (JarFile) lazy.getValue();
    }

    private final JarEntry getJarEntry() {
        Lazy lazy = this.jarEntry$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (JarEntry) lazy.getValue();
    }

    @Override // io.ktor.http.content.OutgoingContent
    @Nullable
    public Long getContentLength() {
        JarEntry jarEntry = getJarEntry();
        if (jarEntry != null) {
            return Long.valueOf(jarEntry.getSize());
        }
        return null;
    }

    @Override // io.ktor.http.content.OutgoingContent
    @NotNull
    public ContentType getContentType() {
        return this.contentType;
    }

    @NotNull
    public final File getJarFile() {
        return this.jarFile;
    }

    @NotNull
    public final String getResourcePath() {
        return this.resourcePath;
    }

    @Override // io.ktor.http.content.OutgoingContent.ReadChannelContent
    @NotNull
    public ByteReadChannel readFrom() {
        ByteReadChannel byteReadChannel$default;
        InputStream inputStream = getJar().getInputStream(getJarEntry());
        if (inputStream == null || (byteReadChannel$default = ReadingKt.toByteReadChannel$default(inputStream, null, ByteBufferPoolKt.getKtorDefaultPool(), 1, null)) == null) {
            throw new IOException(GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("Resource "), this.normalized, " not found"));
        }
        return byteReadChannel$default;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public JarFileContent(@org.jetbrains.annotations.NotNull java.nio.file.Path r2, @org.jetbrains.annotations.NotNull java.lang.String r3, @org.jetbrains.annotations.NotNull io.ktor.http.ContentType r4) {
        /*
            r1 = this;
            java.lang.String r0 = "zipFilePath"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r0)
            java.lang.String r0 = "resourcePath"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r0)
            java.lang.String r0 = "contentType"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r4, r0)
            java.io.File r2 = r2.toFile()
            java.lang.String r0 = "zipFilePath.toFile()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r0)
            r1.<init>(r2, r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.content.JarFileContent.<init>(java.nio.file.Path, java.lang.String, io.ktor.http.ContentType):void");
    }
}
