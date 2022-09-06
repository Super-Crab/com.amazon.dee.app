package com.amazon.alexa.accessorykit;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.-$$Lambda$AccessoriesFactory$l1xu8X2pe49yFqmn35nJtjZn-VM  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$AccessoriesFactory$l1xu8X2pe49yFqmn35nJtjZnVM implements Runnable {
    public static final /* synthetic */ $$Lambda$AccessoriesFactory$l1xu8X2pe49yFqmn35nJtjZnVM INSTANCE = new $$Lambda$AccessoriesFactory$l1xu8X2pe49yFqmn35nJtjZnVM();

    private /* synthetic */ $$Lambda$AccessoriesFactory$l1xu8X2pe49yFqmn35nJtjZnVM() {
    }

    @Override // java.lang.Runnable
    public final void run() {
        AccessoriesFactory.topContact = AccessoriesFactory.dependenciesProvider.createTopContact();
    }
}
