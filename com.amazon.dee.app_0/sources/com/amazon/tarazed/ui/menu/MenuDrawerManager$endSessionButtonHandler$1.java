package com.amazon.tarazed.ui.menu;

import com.amazon.tarazed.core.TarazedIntents;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MenuDrawerManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<no name provided>", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class MenuDrawerManager$endSessionButtonHandler$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ MenuDrawerManager this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MenuDrawerManager$endSessionButtonHandler$1(MenuDrawerManager menuDrawerManager) {
        super(0);
        this.this$0 = menuDrawerManager;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12560invoke() {
        mo12560invoke();
        return Unit.INSTANCE;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke  reason: collision with other method in class */
    public final void mo12560invoke() {
        TarazedSessionLogger tarazedSessionLogger;
        tarazedSessionLogger = this.this$0.logger;
        tarazedSessionLogger.d("MenuDrawerManager", "End session clicked");
        this.this$0.sendSessionStateChange(TarazedIntents.SessionAndroidService.END_SESSION);
    }
}
