package com.amazon.deecomms.calling.ndt.ui;

import com.amazon.deecomms.contacts.presence.model.PresenceDocument;
import java.util.Comparator;
/* compiled from: lambda */
/* renamed from: com.amazon.deecomms.calling.ndt.ui.-$$Lambda$ContactAdapter$KR_PNi3yJVoyCPnb-GIlnKIZ_hs  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$ContactAdapter$KR_PNi3yJVoyCPnbGIlnKIZ_hs implements Comparator {
    public static final /* synthetic */ $$Lambda$ContactAdapter$KR_PNi3yJVoyCPnbGIlnKIZ_hs INSTANCE = new $$Lambda$ContactAdapter$KR_PNi3yJVoyCPnbGIlnKIZ_hs();

    private /* synthetic */ $$Lambda$ContactAdapter$KR_PNi3yJVoyCPnbGIlnKIZ_hs() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int compareToIgnoreCase;
        compareToIgnoreCase = ((PresenceDocument) obj).getContactName().compareToIgnoreCase(((PresenceDocument) obj2).getContactName());
        return compareToIgnoreCase;
    }
}
