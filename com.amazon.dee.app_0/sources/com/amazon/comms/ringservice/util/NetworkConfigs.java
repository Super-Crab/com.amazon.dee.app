package com.amazon.comms.ringservice.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.log.LogLevel;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
/* loaded from: classes12.dex */
public class NetworkConfigs {
    private static final CommsLogger log = CommsLogger.getLogger(NetworkConfigs.class);
    private static final boolean DEBUG_LOGS = log.isLoggable(LogLevel.Verbose);

    private static boolean addDefaultNameServerIfNeeded(Collection<String> collection, String str) {
        if (collection.size() != 0 || TextUtils.isEmpty(str)) {
            return false;
        }
        if (DEBUG_LOGS) {
            CommsLogger commsLogger = log;
            commsLogger.v(" Adding default name server = " + str);
        }
        collection.add(str);
        return true;
    }

    private static boolean filterIPv6NameServers(Collection<String> collection) {
        String next;
        Iterator<String> it2 = collection.iterator();
        String str = null;
        boolean z = false;
        while (it2.hasNext()) {
            try {
                next = it2.next();
            } catch (UnknownHostException unused) {
            }
            try {
                InetAddress byName = InetAddress.getByName(next);
                if (DEBUG_LOGS) {
                    log.v(" checking v4 or v6 for inetAddress = " + byName);
                }
                if (byName instanceof Inet6Address) {
                    it2.remove();
                    z = true;
                }
                str = next;
            } catch (UnknownHostException unused2) {
                str = next;
                log.e("Unable to resolve nameServers :: " + str);
            }
        }
        return z && collection.size() == 0;
    }

    public static NameServersResult getNameServers(Context context, String str) {
        Network[] allNetworks;
        LinkedList linkedList = new LinkedList();
        if (context == null) {
            log.e("Supplied app context was null! Return false as no IPv6 server presents. Apply default name servers.");
            return new NameServersResult(linkedList, filterIPv6NameServers(linkedList), addDefaultNameServerIfNeeded(linkedList, str));
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            log.e("ConnectivityManager was null! Return false as no IPv6 server presents. Apply default name servers.");
            return new NameServersResult(linkedList, filterIPv6NameServers(linkedList), addDefaultNameServerIfNeeded(linkedList, str));
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
        boolean z = false;
        for (Network network : connectivityManager.getAllNetworks()) {
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(network);
            if (networkInfo != null && networkInfo.isConnected()) {
                if (DEBUG_LOGS) {
                    log.v(" netInfo= " + networkInfo);
                }
                LinkProperties linkProperties = connectivityManager.getLinkProperties(network);
                if (linkProperties != null) {
                    if (DEBUG_LOGS) {
                        log.v(" linkProperties= " + linkProperties);
                    }
                    List<InetAddress> dnsServers = linkProperties.getDnsServers();
                    if (dnsServers != null) {
                        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(network);
                        if (DEBUG_LOGS) {
                            log.v(" networkCapabilities= " + networkCapabilities);
                        }
                        LinkedHashSet linkedHashSet3 = (networkCapabilities == null || networkCapabilities.hasCapability(12)) ? linkedHashSet : linkedHashSet2;
                        for (InetAddress inetAddress : dnsServers) {
                            linkedHashSet3.add(inetAddress.getHostAddress());
                        }
                    }
                }
            } else if (DEBUG_LOGS) {
                log.v("ignoring netInfo= " + networkInfo);
            }
        }
        boolean filterIPv6NameServers = filterIPv6NameServers(linkedHashSet);
        boolean addDefaultNameServerIfNeeded = addDefaultNameServerIfNeeded(linkedHashSet, str);
        if (filterIPv6NameServers(linkedHashSet2) || filterIPv6NameServers) {
            z = true;
        }
        linkedHashSet.addAll(linkedHashSet2);
        linkedList.addAll(linkedHashSet);
        return new NameServersResult(linkedList, z, addDefaultNameServerIfNeeded);
    }
}
