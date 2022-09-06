package com.amazon.alexa.wakeword.davs;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ArtifactInfo.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0010 \n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0007R$\u0010\r\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u000f0\u000eX¤\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lcom/amazon/alexa/wakeword/davs/ArtifactInfo;", "", "artifactType", "", "artifactKey", "(Ljava/lang/String;Ljava/lang/String;)V", "getArtifactKey", "()Ljava/lang/String;", "artifactRequest", "Lcom/amazon/alexa/wakeword/davs/ArtifactRequest;", "getArtifactRequest", "()Lcom/amazon/alexa/wakeword/davs/ArtifactRequest;", "getArtifactType", "davsFilters", "", "", "getDavsFilters", "()Ljava/util/Map;", "AVSAndroidClient-pryonprovider_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public abstract class ArtifactInfo {
    @NotNull
    private final String artifactKey;
    @NotNull
    private final String artifactType;

    public ArtifactInfo(@NotNull String artifactType, @NotNull String artifactKey) {
        Intrinsics.checkParameterIsNotNull(artifactType, "artifactType");
        Intrinsics.checkParameterIsNotNull(artifactKey, "artifactKey");
        this.artifactType = artifactType;
        this.artifactKey = artifactKey;
    }

    @NotNull
    public final String getArtifactKey() {
        return this.artifactKey;
    }

    @NotNull
    public final ArtifactRequest getArtifactRequest() {
        ArtifactRequest build = ArtifactRequest.builder().setArtifactType(this.artifactType).setArtifactKey(this.artifactKey).setFilters(getDavsFilters()).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "ArtifactRequest.builder(…\n                .build()");
        return build;
    }

    @NotNull
    public final String getArtifactType() {
        return this.artifactType;
    }

    @NotNull
    protected abstract Map<String, List<String>> getDavsFilters();
}
