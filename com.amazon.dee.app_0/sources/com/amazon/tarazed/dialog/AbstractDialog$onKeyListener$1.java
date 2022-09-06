package com.amazon.tarazed.dialog;

import android.content.DialogInterface;
import android.view.KeyEvent;
import kotlin.Metadata;
/* compiled from: AbstractDialog.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u000e\u0010\u0007\u001a\n \u0004*\u0004\u0018\u00010\b0\bH\n¢\u0006\u0002\b\t"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/content/DialogInterface;", "kotlin.jvm.PlatformType", "keyCode", "", "<anonymous parameter 2>", "Landroid/view/KeyEvent;", "onKey"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
final class AbstractDialog$onKeyListener$1 implements DialogInterface.OnKeyListener {
    public static final AbstractDialog$onKeyListener$1 INSTANCE = new AbstractDialog$onKeyListener$1();

    AbstractDialog$onKeyListener$1() {
    }

    @Override // android.content.DialogInterface.OnKeyListener
    public final boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        return i == 84 || i == 19;
    }
}
