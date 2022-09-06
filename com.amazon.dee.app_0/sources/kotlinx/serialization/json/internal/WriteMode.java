package kotlinx.serialization.json.internal;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
/* compiled from: WriteMode.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\b\u0006\b\u0080\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000j\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, d2 = {"Lkotlinx/serialization/json/internal/WriteMode;", "", "begin", "", "end", "(Ljava/lang/String;ICC)V", "beginTc", "", "endTc", "OBJ", "LIST", "MAP", "POLY_OBJ", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public enum WriteMode {
    OBJ(JsonReaderKt.BEGIN_OBJ, JsonReaderKt.END_OBJ),
    LIST(JsonReaderKt.BEGIN_LIST, JsonReaderKt.END_LIST),
    MAP(JsonReaderKt.BEGIN_OBJ, JsonReaderKt.END_OBJ),
    POLY_OBJ(JsonReaderKt.BEGIN_LIST, JsonReaderKt.END_LIST);
    
    @JvmField
    public final char begin;
    @JvmField
    public final byte beginTc;
    @JvmField
    public final char end;
    @JvmField
    public final byte endTc;

    WriteMode(char c, char c2) {
        this.begin = c;
        this.end = c2;
        this.beginTc = JsonReaderKt.charToTokenClass(this.begin);
        this.endTc = JsonReaderKt.charToTokenClass(this.end);
    }
}
