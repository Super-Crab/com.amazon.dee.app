package com.amazon.alexa.accessorykit;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.-$$Lambda$AccessoriesFactory$Ny5y-UU0mZIZWltywT617OsPNQ4  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$AccessoriesFactory$Ny5yUU0mZIZWltywT617OsPNQ4 implements Runnable {
    public static final /* synthetic */ $$Lambda$AccessoriesFactory$Ny5yUU0mZIZWltywT617OsPNQ4 INSTANCE = new $$Lambda$AccessoriesFactory$Ny5yUU0mZIZWltywT617OsPNQ4();

    private /* synthetic */ $$Lambda$AccessoriesFactory$Ny5yUU0mZIZWltywT617OsPNQ4() {
    }

    @Override // java.lang.Runnable
    public final void run() {
        AccessoriesFactory.focusFilter = AccessoriesFactory.dependenciesProvider.createFocusFilter();
    }
}
