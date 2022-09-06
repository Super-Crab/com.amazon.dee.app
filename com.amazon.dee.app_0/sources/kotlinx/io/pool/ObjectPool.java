package kotlinx.io.pool;

import androidx.exifinterface.media.ExifInterface;
import java.io.Closeable;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: Pool.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00060\u0003j\u0002`\u0004J\r\u0010\t\u001a\u00028\u0000H&¢\u0006\u0002\u0010\nJ\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\fH&J\u0015\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0010R\u0012\u0010\u0005\u001a\u00020\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0011"}, d2 = {"Lkotlinx/io/pool/ObjectPool;", ExifInterface.GPS_DIRECTION_TRUE, "", "Ljava/io/Closeable;", "Lkotlinx/io/core/Closeable;", "capacity", "", "getCapacity", "()I", "borrow", "()Ljava/lang/Object;", "close", "", "dispose", "recycle", "instance", "(Ljava/lang/Object;)V", "kotlinx-io"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public interface ObjectPool<T> extends Closeable {

    /* compiled from: Pool.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    /* loaded from: classes4.dex */
    public static final class DefaultImpls {
        public static <T> void close(ObjectPool<T> objectPool) {
            objectPool.dispose();
        }
    }

    @NotNull
    /* renamed from: borrow */
    T mo12351borrow();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    void dispose();

    int getCapacity();

    void recycle(@NotNull T t);
}
