package kotlin.jvm.internal;

import com.amazon.clouddrive.extended.model.BulkOperationType;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: PrimitiveSpreadBuilders.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\f\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\u0002J\f\u0010\f\u001a\u00020\u0004*\u00020\u0002H\u0014R\u000e\u0010\u0006\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lkotlin/jvm/internal/CharSpreadBuilder;", "Lkotlin/jvm/internal/PrimitiveSpreadBuilder;", "", "size", "", "(I)V", "values", BulkOperationType.add, "", "value", "", "toArray", "getSize", "kotlin-stdlib"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class CharSpreadBuilder extends PrimitiveSpreadBuilder<char[]> {
    private final char[] values;

    public CharSpreadBuilder(int i) {
        super(i);
        this.values = new char[i];
    }

    public final void add(char c) {
        char[] cArr = this.values;
        int position = getPosition();
        setPosition(position + 1);
        cArr[position] = c;
    }

    @NotNull
    public final char[] toArray() {
        return toArray(this.values, new char[size()]);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.jvm.internal.PrimitiveSpreadBuilder
    public int getSize(@NotNull char[] getSize) {
        Intrinsics.checkParameterIsNotNull(getSize, "$this$getSize");
        return getSize.length;
    }
}