package com.bugsnag.android;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmName;
import org.jetbrains.annotations.NotNull;
/* compiled from: BugsnagPluginInterface.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0001J\u0010\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0001J\u0012\u0010\u000f\u001a\u00020\n2\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u0005J\u0014\u0010\u0010\u001a\u00020\n2\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0001R\u001e\u0010\u0003\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u0007\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/bugsnag/android/BugsnagPluginInterface;", "", "()V", "plugins", "", "Ljava/lang/Class;", "Lcom/bugsnag/android/BugsnagPlugin;", "registeredPluginClasses", "", "loadPlugin", "", "client", "Lcom/bugsnag/android/Client;", "clz", "loadRegisteredPlugins", "registerPlugin", "unloadPlugin", "bugsnag-android-core_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class BugsnagPluginInterface {
    public static final BugsnagPluginInterface INSTANCE = new BugsnagPluginInterface();
    private static Map<Class<?>, BugsnagPlugin> plugins = new LinkedHashMap();
    private static Set<Class<?>> registeredPluginClasses = new LinkedHashSet();

    private BugsnagPluginInterface() {
    }

    @JvmName(name = "loadPlugin")
    public final void loadPlugin(@NotNull Client client, @NotNull Class<?> clz) {
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(client, "client");
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(clz, "clz");
        BugsnagPlugin bugsnagPlugin = plugins.get(clz);
        if (bugsnagPlugin == null) {
            try {
                Object newInstance = clz.newInstance();
                if (newInstance == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.bugsnag.android.BugsnagPlugin");
                }
                bugsnagPlugin = (BugsnagPlugin) newInstance;
            } catch (Exception unused) {
                bugsnagPlugin = null;
            }
        }
        if (bugsnagPlugin == null || bugsnagPlugin.getLoaded()) {
            return;
        }
        plugins.put(clz, bugsnagPlugin);
        bugsnagPlugin.loadPlugin(client);
        bugsnagPlugin.setLoaded(true);
    }

    @JvmName(name = "loadRegisteredPlugins")
    public final void loadRegisteredPlugins(@NotNull Client client) {
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(client, "client");
        for (Class<?> cls : registeredPluginClasses) {
            INSTANCE.loadPlugin(client, cls);
        }
    }

    public final void registerPlugin(@NotNull Class<?> clz) {
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(clz, "clz");
        registeredPluginClasses.add(clz);
    }

    @JvmName(name = "unloadPlugin")
    public final void unloadPlugin(@NotNull Class<?> clz) {
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(clz, "clz");
        BugsnagPlugin bugsnagPlugin = plugins.get(clz);
        if (bugsnagPlugin == null || !bugsnagPlugin.getLoaded()) {
            return;
        }
        bugsnagPlugin.unloadPlugin();
        bugsnagPlugin.setLoaded(false);
    }
}
