package com.amazon.alexa.voice.navigation;

import java.util.Comparator;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.voice.navigation.-$$Lambda$PreferredNavigationAppRepository$HI6bCpjxq197od8KULwHzeZmi9o  reason: invalid class name */
/* loaded from: classes11.dex */
public final /* synthetic */ class $$Lambda$PreferredNavigationAppRepository$HI6bCpjxq197od8KULwHzeZmi9o implements Comparator {
    public static final /* synthetic */ $$Lambda$PreferredNavigationAppRepository$HI6bCpjxq197od8KULwHzeZmi9o INSTANCE = new $$Lambda$PreferredNavigationAppRepository$HI6bCpjxq197od8KULwHzeZmi9o();

    private /* synthetic */ $$Lambda$PreferredNavigationAppRepository$HI6bCpjxq197od8KULwHzeZmi9o() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int compareToIgnoreCase;
        compareToIgnoreCase = ((NavigationAppInfo) obj).getAppDisplayName().compareToIgnoreCase(((NavigationAppInfo) obj2).getAppDisplayName());
        return compareToIgnoreCase;
    }
}
