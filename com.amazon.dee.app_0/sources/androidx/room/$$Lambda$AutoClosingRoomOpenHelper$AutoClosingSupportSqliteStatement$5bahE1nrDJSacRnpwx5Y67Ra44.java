package androidx.room;

import androidx.arch.core.util.Function;
import androidx.sqlite.db.SupportSQLiteStatement;
/* compiled from: lambda */
/* renamed from: androidx.room.-$$Lambda$AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$5bahE1n-rDJSacRnpwx5Y67Ra44  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$5bahE1nrDJSacRnpwx5Y67Ra44 implements Function {
    public static final /* synthetic */ $$Lambda$AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$5bahE1nrDJSacRnpwx5Y67Ra44 INSTANCE = new $$Lambda$AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$5bahE1nrDJSacRnpwx5Y67Ra44();

    private /* synthetic */ $$Lambda$AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$5bahE1nrDJSacRnpwx5Y67Ra44() {
    }

    @Override // androidx.arch.core.util.Function
    public final Object apply(Object obj) {
        ((SupportSQLiteStatement) obj).execute();
        return null;
    }
}
