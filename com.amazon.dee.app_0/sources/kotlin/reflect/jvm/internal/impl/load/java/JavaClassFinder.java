package kotlin.reflect.jvm.internal.impl.load.java;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Arrays;
import java.util.Set;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaPackage;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: JavaClassFinder.kt */
/* loaded from: classes3.dex */
public interface JavaClassFinder {

    /* compiled from: JavaClassFinder.kt */
    /* loaded from: classes3.dex */
    public static final class Request {
        @NotNull
        private final ClassId classId;
        @Nullable
        private final JavaClass outerClass;
        @Nullable
        private final byte[] previouslyFoundClassFileContent;

        public Request(@NotNull ClassId classId, @Nullable byte[] bArr, @Nullable JavaClass javaClass) {
            Intrinsics.checkParameterIsNotNull(classId, "classId");
            this.classId = classId;
            this.previouslyFoundClassFileContent = bArr;
            this.outerClass = javaClass;
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                if (!(obj instanceof Request)) {
                    return false;
                }
                Request request = (Request) obj;
                return Intrinsics.areEqual(this.classId, request.classId) && Intrinsics.areEqual(this.previouslyFoundClassFileContent, request.previouslyFoundClassFileContent) && Intrinsics.areEqual(this.outerClass, request.outerClass);
            }
            return true;
        }

        @NotNull
        public final ClassId getClassId() {
            return this.classId;
        }

        public int hashCode() {
            ClassId classId = this.classId;
            int i = 0;
            int hashCode = (classId != null ? classId.hashCode() : 0) * 31;
            byte[] bArr = this.previouslyFoundClassFileContent;
            int hashCode2 = (hashCode + (bArr != null ? Arrays.hashCode(bArr) : 0)) * 31;
            JavaClass javaClass = this.outerClass;
            if (javaClass != null) {
                i = javaClass.hashCode();
            }
            return hashCode2 + i;
        }

        @NotNull
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Request(classId=");
            outline107.append(this.classId);
            outline107.append(", previouslyFoundClassFileContent=");
            outline107.append(Arrays.toString(this.previouslyFoundClassFileContent));
            outline107.append(", outerClass=");
            outline107.append(this.outerClass);
            outline107.append(")");
            return outline107.toString();
        }

        public /* synthetic */ Request(ClassId classId, byte[] bArr, JavaClass javaClass, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(classId, (i & 2) != 0 ? null : bArr, (i & 4) != 0 ? null : javaClass);
        }
    }

    @Nullable
    JavaClass findClass(@NotNull Request request);

    @Nullable
    JavaPackage findPackage(@NotNull FqName fqName);

    @Nullable
    Set<String> knownClassNamesInPackage(@NotNull FqName fqName);
}
