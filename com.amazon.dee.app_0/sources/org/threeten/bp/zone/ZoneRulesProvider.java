package org.threeten.bp.zone;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collections;
import java.util.Iterator;
import java.util.NavigableMap;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.threeten.bp.jdk8.Jdk8Methods;
/* loaded from: classes5.dex */
public abstract class ZoneRulesProvider {
    private static final CopyOnWriteArrayList<ZoneRulesProvider> PROVIDERS = new CopyOnWriteArrayList<>();
    private static final ConcurrentMap<String, ZoneRulesProvider> ZONES = new ConcurrentHashMap(512, 0.75f, 2);

    static {
        ZoneRulesInitializer.initialize();
    }

    public static Set<String> getAvailableZoneIds() {
        return Collections.unmodifiableSet(ZONES.keySet());
    }

    private static ZoneRulesProvider getProvider(String str) {
        ZoneRulesProvider zoneRulesProvider = ZONES.get(str);
        if (zoneRulesProvider == null) {
            if (ZONES.isEmpty()) {
                throw new ZoneRulesException("No time-zone data files registered");
            }
            throw new ZoneRulesException(GeneratedOutlineSupport1.outline72("Unknown time-zone ID: ", str));
        }
        return zoneRulesProvider;
    }

    public static ZoneRules getRules(String str, boolean z) {
        Jdk8Methods.requireNonNull(str, "zoneId");
        return getProvider(str).provideRules(str, z);
    }

    public static NavigableMap<String, ZoneRules> getVersions(String str) {
        Jdk8Methods.requireNonNull(str, "zoneId");
        return getProvider(str).provideVersions(str);
    }

    public static boolean refresh() {
        Iterator<ZoneRulesProvider> it2 = PROVIDERS.iterator();
        boolean z = false;
        while (it2.hasNext()) {
            z |= it2.next().provideRefresh();
        }
        return z;
    }

    public static void registerProvider(ZoneRulesProvider zoneRulesProvider) {
        Jdk8Methods.requireNonNull(zoneRulesProvider, EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PROVIDER);
        registerProvider0(zoneRulesProvider);
        PROVIDERS.add(zoneRulesProvider);
    }

    private static void registerProvider0(ZoneRulesProvider zoneRulesProvider) {
        for (String str : zoneRulesProvider.provideZoneIds()) {
            Jdk8Methods.requireNonNull(str, "zoneId");
            if (ZONES.putIfAbsent(str, zoneRulesProvider) != null) {
                throw new ZoneRulesException("Unable to register zone as one already registered with that ID: " + str + ", currently loading from provider: " + zoneRulesProvider);
            }
        }
    }

    protected boolean provideRefresh() {
        return false;
    }

    protected abstract ZoneRules provideRules(String str, boolean z);

    protected abstract NavigableMap<String, ZoneRules> provideVersions(String str);

    protected abstract Set<String> provideZoneIds();
}
