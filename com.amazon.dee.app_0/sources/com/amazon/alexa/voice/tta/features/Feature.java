package com.amazon.alexa.voice.tta.features;

import com.amazon.alexa.mobilytics.configuration.KinesisEndpoint;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Feature.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\b\t\nB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0011\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0000H\u0096\u0004J\u0011\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u0000H\u0096\u0004\u0082\u0001\u0003\u000b\u0007\u0004¨\u0006\f"}, d2 = {"Lcom/amazon/alexa/voice/tta/features/Feature;", "", "()V", "and", "Lcom/amazon/alexa/voice/tta/features/Feature$All;", "other", "or", "Lcom/amazon/alexa/voice/tta/features/Feature$Any;", "All", KinesisEndpoint.AppState.ANY, "Named", "Lcom/amazon/alexa/voice/tta/features/Feature$Named;", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public abstract class Feature {

    /* compiled from: Feature.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\b\u0016\u0012\u0012\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0003\"\u00020\u0001¢\u0006\u0002\u0010\u0004B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0011\u0010\t\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0001H\u0096\u0004J\u000f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005HÆ\u0003J\u0019\u0010\f\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\n\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\b\u0010\u0012\u001a\u00020\u0013H\u0016R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/amazon/alexa/voice/tta/features/Feature$All;", "Lcom/amazon/alexa/voice/tta/features/Feature;", "children", "", "([Lcom/amazon/alexa/voice/tta/features/Feature;)V", "", "(Ljava/util/List;)V", "getChildren", "()Ljava/util/List;", "and", "other", "component1", "copy", "equals", "", "", "hashCode", "", "toString", "", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes11.dex */
    public static final class All extends Feature {
        @NotNull
        private final List<Feature> children;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public All(@NotNull List<? extends Feature> children) {
            super(null);
            Intrinsics.checkParameterIsNotNull(children, "children");
            this.children = children;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ All copy$default(All all, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = all.children;
            }
            return all.copy(list);
        }

        @Override // com.amazon.alexa.voice.tta.features.Feature
        @NotNull
        public All and(@NotNull Feature other) {
            List plus;
            Intrinsics.checkParameterIsNotNull(other, "other");
            plus = CollectionsKt___CollectionsKt.plus((Collection<? extends Object>) ((Collection) this.children), (Object) other);
            return new All(plus);
        }

        @NotNull
        public final List<Feature> component1() {
            return this.children;
        }

        @NotNull
        public final All copy(@NotNull List<? extends Feature> children) {
            Intrinsics.checkParameterIsNotNull(children, "children");
            return new All(children);
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                return (obj instanceof All) && Intrinsics.areEqual(this.children, ((All) obj).children);
            }
            return true;
        }

        @NotNull
        public final List<Feature> getChildren() {
            return this.children;
        }

        public int hashCode() {
            List<Feature> list = this.children;
            if (list != null) {
                return list.hashCode();
            }
            return 0;
        }

        @NotNull
        public String toString() {
            String joinToString$default;
            StringBuilder outline104 = GeneratedOutlineSupport1.outline104('(');
            joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(this.children, " and ", null, null, 0, null, null, 62, null);
            return GeneratedOutlineSupport1.outline89(outline104, joinToString$default, ')');
        }

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public All(@org.jetbrains.annotations.NotNull com.amazon.alexa.voice.tta.features.Feature... r2) {
            /*
                r1 = this;
                java.lang.String r0 = "children"
                kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r0)
                int r0 = r2.length
                java.lang.Object[] r2 = java.util.Arrays.copyOf(r2, r0)
                com.amazon.alexa.voice.tta.features.Feature[] r2 = (com.amazon.alexa.voice.tta.features.Feature[]) r2
                java.util.List r2 = kotlin.collections.CollectionsKt.listOf(r2)
                r1.<init>(r2)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.voice.tta.features.Feature.All.<init>(com.amazon.alexa.voice.tta.features.Feature[]):void");
        }
    }

    /* compiled from: Feature.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\b\u0016\u0012\u0012\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0003\"\u00020\u0001¢\u0006\u0002\u0010\u0004B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\u0010\u0006J\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005HÆ\u0003J\u0019\u0010\n\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\u0011\u0010\u0011\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\u0001H\u0096\u0004J\b\u0010\u0012\u001a\u00020\u0013H\u0016R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/amazon/alexa/voice/tta/features/Feature$Any;", "Lcom/amazon/alexa/voice/tta/features/Feature;", "children", "", "([Lcom/amazon/alexa/voice/tta/features/Feature;)V", "", "(Ljava/util/List;)V", "getChildren", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "or", "toString", "", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes11.dex */
    public static final class Any extends Feature {
        @NotNull
        private final List<Feature> children;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public Any(@NotNull List<? extends Feature> children) {
            super(null);
            Intrinsics.checkParameterIsNotNull(children, "children");
            this.children = children;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Any copy$default(Any any, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = any.children;
            }
            return any.copy(list);
        }

        @NotNull
        public final List<Feature> component1() {
            return this.children;
        }

        @NotNull
        public final Any copy(@NotNull List<? extends Feature> children) {
            Intrinsics.checkParameterIsNotNull(children, "children");
            return new Any(children);
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                return (obj instanceof Any) && Intrinsics.areEqual(this.children, ((Any) obj).children);
            }
            return true;
        }

        @NotNull
        public final List<Feature> getChildren() {
            return this.children;
        }

        public int hashCode() {
            List<Feature> list = this.children;
            if (list != null) {
                return list.hashCode();
            }
            return 0;
        }

        @Override // com.amazon.alexa.voice.tta.features.Feature
        @NotNull
        public Any or(@NotNull Feature other) {
            List plus;
            Intrinsics.checkParameterIsNotNull(other, "other");
            plus = CollectionsKt___CollectionsKt.plus((Collection<? extends Object>) ((Collection) this.children), (Object) other);
            return new Any(plus);
        }

        @NotNull
        public String toString() {
            String joinToString$default;
            StringBuilder outline104 = GeneratedOutlineSupport1.outline104('(');
            joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(this.children, " or ", null, null, 0, null, null, 62, null);
            return GeneratedOutlineSupport1.outline89(outline104, joinToString$default, ')');
        }

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public Any(@org.jetbrains.annotations.NotNull com.amazon.alexa.voice.tta.features.Feature... r2) {
            /*
                r1 = this;
                java.lang.String r0 = "children"
                kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r0)
                int r0 = r2.length
                java.lang.Object[] r2 = java.util.Arrays.copyOf(r2, r0)
                com.amazon.alexa.voice.tta.features.Feature[] r2 = (com.amazon.alexa.voice.tta.features.Feature[]) r2
                java.util.List r2 = kotlin.collections.CollectionsKt.listOf(r2)
                r1.<init>(r2)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.voice.tta.features.Feature.Any.<init>(com.amazon.alexa.voice.tta.features.Feature[]):void");
        }
    }

    /* compiled from: Feature.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\b\u0010\u000f\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/amazon/alexa/voice/tta/features/Feature$Named;", "Lcom/amazon/alexa/voice/tta/features/Feature;", "name", "", "(Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes11.dex */
    public static final class Named extends Feature {
        @NotNull
        private final String name;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Named(@NotNull String name) {
            super(null);
            Intrinsics.checkParameterIsNotNull(name, "name");
            this.name = name;
        }

        public static /* synthetic */ Named copy$default(Named named, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = named.name;
            }
            return named.copy(str);
        }

        @NotNull
        public final String component1() {
            return this.name;
        }

        @NotNull
        public final Named copy(@NotNull String name) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            return new Named(name);
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                return (obj instanceof Named) && Intrinsics.areEqual(this.name, ((Named) obj).name);
            }
            return true;
        }

        @NotNull
        public final String getName() {
            return this.name;
        }

        public int hashCode() {
            String str = this.name;
            if (str != null) {
                return str.hashCode();
            }
            return 0;
        }

        @NotNull
        public String toString() {
            return this.name;
        }
    }

    private Feature() {
    }

    @NotNull
    public All and(@NotNull Feature other) {
        Intrinsics.checkParameterIsNotNull(other, "other");
        return new All(this, other);
    }

    @NotNull
    public Any or(@NotNull Feature other) {
        Intrinsics.checkParameterIsNotNull(other, "other");
        return new Any(this, other);
    }

    public /* synthetic */ Feature(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
