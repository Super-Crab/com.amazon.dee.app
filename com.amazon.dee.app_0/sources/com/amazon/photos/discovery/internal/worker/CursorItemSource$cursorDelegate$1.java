package com.amazon.photos.discovery.internal.worker;

import android.database.Cursor;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;
/* compiled from: ScanAddedWorkerUtil.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroid/database/Cursor;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
final class CursorItemSource$cursorDelegate$1 extends Lambda implements Function0<Cursor> {
    final /* synthetic */ long $lastAddedRowId;
    final /* synthetic */ CursorItemSource this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CursorItemSource$cursorDelegate$1(CursorItemSource cursorItemSource, long j) {
        super(0);
        this.this$0 = cursorItemSource;
        this.$lastAddedRowId = j;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    @Nullable
    /* renamed from: invoke */
    public final Cursor mo12560invoke() {
        return this.this$0.createCursor(this.$lastAddedRowId);
    }
}
