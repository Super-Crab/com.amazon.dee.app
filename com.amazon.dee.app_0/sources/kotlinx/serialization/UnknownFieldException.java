package kotlinx.serialization;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
/* compiled from: Exceptions.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lkotlinx/serialization/UnknownFieldException;", "Lkotlinx/serialization/SerializationException;", "index", "", "(I)V", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class UnknownFieldException extends SerializationException {
    public UnknownFieldException(int i) {
        super(GeneratedOutlineSupport1.outline49("Unknown field for index ", i), null, 2, null);
    }
}
