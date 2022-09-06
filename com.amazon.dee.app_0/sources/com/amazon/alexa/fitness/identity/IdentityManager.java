package com.amazon.alexa.fitness.identity;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: IdentityManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0010\u0010\u0004\u001a\f\u0012\u0004\u0012\u00020\u00030\u0005j\u0002`\u0006H&J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\bH&J\n\u0010\n\u001a\u0004\u0018\u00010\bH&J\n\u0010\u000b\u001a\u0004\u0018\u00010\bH&J\b\u0010\f\u001a\u00020\rH&J\u001a\u0010\u000e\u001a\u00020\u00032\u0010\u0010\u0004\u001a\f\u0012\u0004\u0012\u00020\u00030\u0005j\u0002`\u0006H&¨\u0006\u000f"}, d2 = {"Lcom/amazon/alexa/fitness/identity/IdentityManager;", "", "addIdentityChangedListener", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lkotlin/Function0;", "Lcom/amazon/alexa/fitness/identity/IdentityManagerIdentityChangedListener;", "getMapTokenByDirectedId", "", "directedId", "getUserIdentityDirectedId", "getUserProfileDirectedId", "isUserLoggedIn", "", "removeIdentityChangedListener", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public interface IdentityManager {
    void addIdentityChangedListener(@NotNull Function0<Unit> function0);

    @Nullable
    String getMapTokenByDirectedId(@NotNull String str);

    @Nullable
    String getUserIdentityDirectedId();

    @Nullable
    String getUserProfileDirectedId();

    boolean isUserLoggedIn();

    void removeIdentityChangedListener(@NotNull Function0<Unit> function0);
}
