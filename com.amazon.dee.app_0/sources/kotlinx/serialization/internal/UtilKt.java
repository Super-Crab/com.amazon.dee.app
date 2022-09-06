package kotlinx.serialization.internal;

import androidx.exifinterface.media.ExifInterface;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.ReplaceWith;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.InternalSerializationApi;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialDescriptor;
import kotlinx.serialization.SerialKind;
import org.jetbrains.annotations.NotNull;
/* compiled from: Util.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0000\u001a\u0012\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005*\u00020\u0001H\u0000\u001a\u001d\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\t0\b\"\u0004\b\u0000\u0010\t*\u0006\u0012\u0002\b\u00030\bH\u0081\b\u001a\u0018\u0010\n\u001a\u00020\u000b*\u00060\fj\u0002`\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007¨\u0006\u0010"}, d2 = {"defer", "Lkotlinx/serialization/SerialDescriptor;", "deferred", "Lkotlin/Function0;", "cachedSerialNames", "", "", "cast", "Lkotlinx/serialization/KSerializer;", ExifInterface.GPS_DIRECTION_TRUE, "readExactNBytes", "", "Ljava/io/InputStream;", "Lkotlinx/io/InputStream;", "bytes", "", "kotlinx-serialization-runtime"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class UtilKt {
    @NotNull
    public static final Set<String> cachedSerialNames(@NotNull SerialDescriptor cachedSerialNames) {
        Intrinsics.checkParameterIsNotNull(cachedSerialNames, "$this$cachedSerialNames");
        if (cachedSerialNames instanceof PluginGeneratedSerialDescriptor) {
            return ((PluginGeneratedSerialDescriptor) cachedSerialNames).getNamesSet$kotlinx_serialization_runtime();
        }
        HashSet hashSet = new HashSet(cachedSerialNames.getElementsCount());
        int elementsCount = cachedSerialNames.getElementsCount();
        for (int i = 0; i < elementsCount; i++) {
            hashSet.add(cachedSerialNames.getElementName(i));
        }
        return hashSet;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @PublishedApi
    @NotNull
    public static final <T> KSerializer<T> cast(@NotNull KSerializer<?> cast) {
        Intrinsics.checkParameterIsNotNull(cast, "$this$cast");
        return cast;
    }

    @NotNull
    public static final SerialDescriptor defer(@NotNull final Function0<? extends SerialDescriptor> deferred) {
        Intrinsics.checkParameterIsNotNull(deferred, "deferred");
        return new SerialDescriptor() { // from class: kotlinx.serialization.internal.UtilKt$defer$1
            private final Lazy original$delegate;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                Lazy lazy;
                lazy = LazyKt__LazyJVMKt.lazy(Function0.this);
                this.original$delegate = lazy;
            }

            private final SerialDescriptor getOriginal() {
                return (SerialDescriptor) this.original$delegate.getValue();
            }

            @Override // kotlinx.serialization.SerialDescriptor
            @NotNull
            public List<Annotation> getAnnotations() {
                return SerialDescriptor.DefaultImpls.getAnnotations(this);
            }

            @Override // kotlinx.serialization.SerialDescriptor
            @NotNull
            public List<Annotation> getElementAnnotations(int i) {
                return getOriginal().getElementAnnotations(i);
            }

            @Override // kotlinx.serialization.SerialDescriptor
            @NotNull
            public SerialDescriptor getElementDescriptor(int i) {
                return getOriginal().getElementDescriptor(i);
            }

            @Override // kotlinx.serialization.SerialDescriptor
            public int getElementIndex(@NotNull String name) {
                Intrinsics.checkParameterIsNotNull(name, "name");
                return getOriginal().getElementIndex(name);
            }

            @Override // kotlinx.serialization.SerialDescriptor
            @NotNull
            public String getElementName(int i) {
                return getOriginal().getElementName(i);
            }

            @Override // kotlinx.serialization.SerialDescriptor
            public int getElementsCount() {
                return getOriginal().getElementsCount();
            }

            @Override // kotlinx.serialization.SerialDescriptor
            @Deprecated(message = "Deprecated in the favour of 'annotations' property", replaceWith = @ReplaceWith(expression = "annotations", imports = {}))
            @NotNull
            public List<Annotation> getEntityAnnotations() {
                return SerialDescriptor.DefaultImpls.getEntityAnnotations(this);
            }

            @Override // kotlinx.serialization.SerialDescriptor
            @NotNull
            /* renamed from: getKind */
            public SerialKind mo12397getKind() {
                return getOriginal().mo12397getKind();
            }

            @Override // kotlinx.serialization.SerialDescriptor
            @NotNull
            public String getName() {
                return SerialDescriptor.DefaultImpls.getName(this);
            }

            @Override // kotlinx.serialization.SerialDescriptor
            @NotNull
            public String getSerialName() {
                return getOriginal().getSerialName();
            }

            @Override // kotlinx.serialization.SerialDescriptor
            public boolean isElementOptional(int i) {
                return getOriginal().isElementOptional(i);
            }

            @Override // kotlinx.serialization.SerialDescriptor
            public boolean isNullable() {
                return SerialDescriptor.DefaultImpls.isNullable(this);
            }
        };
    }

    @InternalSerializationApi
    @NotNull
    public static final byte[] readExactNBytes(@NotNull InputStream readExactNBytes, int i) {
        Intrinsics.checkParameterIsNotNull(readExactNBytes, "$this$readExactNBytes");
        byte[] bArr = new byte[i];
        int i2 = 0;
        while (i2 < i) {
            int read = readExactNBytes.read(bArr, i2, i - i2);
            if (read == -1) {
                throw new IllegalStateException("Unexpected EOF".toString());
            }
            i2 += read;
        }
        return bArr;
    }
}
