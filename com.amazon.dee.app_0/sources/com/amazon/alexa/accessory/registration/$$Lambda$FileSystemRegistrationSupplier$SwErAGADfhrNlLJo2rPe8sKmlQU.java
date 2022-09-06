package com.amazon.alexa.accessory.registration;

import com.amazon.alexa.accessory.User;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.registration.-$$Lambda$FileSystemRegistrationSupplier$SwErAGADfhrNlLJo2rPe8sKmlQU  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$FileSystemRegistrationSupplier$SwErAGADfhrNlLJo2rPe8sKmlQU implements Function {
    public static final /* synthetic */ $$Lambda$FileSystemRegistrationSupplier$SwErAGADfhrNlLJo2rPe8sKmlQU INSTANCE = new $$Lambda$FileSystemRegistrationSupplier$SwErAGADfhrNlLJo2rPe8sKmlQU();

    private /* synthetic */ $$Lambda$FileSystemRegistrationSupplier$SwErAGADfhrNlLJo2rPe8sKmlQU() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        User user = (User) obj;
        FileSystemRegistrationSupplier.lambda$getUser$16(user);
        return user;
    }
}
