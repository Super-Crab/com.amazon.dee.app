package androidx.room;

import android.os.Build;
import androidx.arch.core.util.Function;
import androidx.sqlite.db.SupportSQLiteDatabase;
/* compiled from: lambda */
/* renamed from: androidx.room.-$$Lambda$AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$4QgN1EjdyJVlH_XLa1YbM6V7esk  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$4QgN1EjdyJVlH_XLa1YbM6V7esk implements Function {
    public static final /* synthetic */ $$Lambda$AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$4QgN1EjdyJVlH_XLa1YbM6V7esk INSTANCE = new $$Lambda$AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$4QgN1EjdyJVlH_XLa1YbM6V7esk();

    private /* synthetic */ $$Lambda$AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$4QgN1EjdyJVlH_XLa1YbM6V7esk() {
    }

    @Override // androidx.arch.core.util.Function
    public final Object apply(Object obj) {
        SupportSQLiteDatabase supportSQLiteDatabase = (SupportSQLiteDatabase) obj;
        return Build.VERSION.SDK_INT;
    }
}
