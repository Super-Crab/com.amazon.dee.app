package kotlin.reflect.jvm.internal.impl.load.kotlin;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Arrays;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.KotlinMetadataFinder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: KotlinClassFinder.kt */
/* loaded from: classes3.dex */
public interface KotlinClassFinder extends KotlinMetadataFinder {

    /* compiled from: KotlinClassFinder.kt */
    /* loaded from: classes3.dex */
    public static abstract class Result {

        /* compiled from: KotlinClassFinder.kt */
        /* loaded from: classes3.dex */
        public static final class ClassFileContent extends Result {
            @NotNull
            private final byte[] content;

            public boolean equals(@Nullable Object obj) {
                if (this != obj) {
                    return (obj instanceof ClassFileContent) && Intrinsics.areEqual(this.content, ((ClassFileContent) obj).content);
                }
                return true;
            }

            @NotNull
            public final byte[] getContent() {
                return this.content;
            }

            public int hashCode() {
                byte[] bArr = this.content;
                if (bArr != null) {
                    return Arrays.hashCode(bArr);
                }
                return 0;
            }

            @NotNull
            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ClassFileContent(content=");
                outline107.append(Arrays.toString(this.content));
                outline107.append(")");
                return outline107.toString();
            }
        }

        /* compiled from: KotlinClassFinder.kt */
        /* loaded from: classes3.dex */
        public static final class KotlinClass extends Result {
            @NotNull
            private final KotlinJvmBinaryClass kotlinJvmBinaryClass;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public KotlinClass(@NotNull KotlinJvmBinaryClass kotlinJvmBinaryClass) {
                super(null);
                Intrinsics.checkParameterIsNotNull(kotlinJvmBinaryClass, "kotlinJvmBinaryClass");
                this.kotlinJvmBinaryClass = kotlinJvmBinaryClass;
            }

            public boolean equals(@Nullable Object obj) {
                if (this != obj) {
                    return (obj instanceof KotlinClass) && Intrinsics.areEqual(this.kotlinJvmBinaryClass, ((KotlinClass) obj).kotlinJvmBinaryClass);
                }
                return true;
            }

            @NotNull
            public final KotlinJvmBinaryClass getKotlinJvmBinaryClass() {
                return this.kotlinJvmBinaryClass;
            }

            public int hashCode() {
                KotlinJvmBinaryClass kotlinJvmBinaryClass = this.kotlinJvmBinaryClass;
                if (kotlinJvmBinaryClass != null) {
                    return kotlinJvmBinaryClass.hashCode();
                }
                return 0;
            }

            @NotNull
            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("KotlinClass(kotlinJvmBinaryClass=");
                outline107.append(this.kotlinJvmBinaryClass);
                outline107.append(")");
                return outline107.toString();
            }
        }

        private Result() {
        }

        @Nullable
        public final KotlinJvmBinaryClass toKotlinJvmBinaryClass() {
            KotlinClass kotlinClass = (KotlinClass) (!(this instanceof KotlinClass) ? null : this);
            if (kotlinClass != null) {
                return kotlinClass.getKotlinJvmBinaryClass();
            }
            return null;
        }

        public /* synthetic */ Result(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Nullable
    Result findKotlinClassOrContent(@NotNull JavaClass javaClass);

    @Nullable
    Result findKotlinClassOrContent(@NotNull ClassId classId);
}
