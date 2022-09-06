package com.google.firebase.platforminfo;

import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.components.Dependency;
import java.util.Iterator;
import java.util.Set;
import org.apache.logging.log4j.util.Chars;
/* compiled from: com.google.firebase:firebase-common@@19.3.0 */
/* loaded from: classes3.dex */
public class DefaultUserAgentPublisher implements UserAgentPublisher {
    private final GlobalLibraryVersionRegistrar gamesSDKRegistrar;
    private final String javaSDKVersionUserAgent;

    DefaultUserAgentPublisher(Set<LibraryVersion> set, GlobalLibraryVersionRegistrar globalLibraryVersionRegistrar) {
        this.javaSDKVersionUserAgent = toUserAgent(set);
        this.gamesSDKRegistrar = globalLibraryVersionRegistrar;
    }

    public static Component<UserAgentPublisher> component() {
        ComponentFactory componentFactory;
        Component.Builder add = Component.builder(UserAgentPublisher.class).add(Dependency.setOf(LibraryVersion.class));
        componentFactory = DefaultUserAgentPublisher$$Lambda$1.instance;
        return add.factory(componentFactory).build();
    }

    public static /* synthetic */ UserAgentPublisher lambda$component$0(ComponentContainer componentContainer) {
        return new DefaultUserAgentPublisher(componentContainer.setOf(LibraryVersion.class), GlobalLibraryVersionRegistrar.getInstance());
    }

    private static String toUserAgent(Set<LibraryVersion> set) {
        StringBuilder sb = new StringBuilder();
        Iterator<LibraryVersion> it2 = set.iterator();
        while (it2.hasNext()) {
            LibraryVersion next = it2.next();
            sb.append(next.getLibraryName());
            sb.append('/');
            sb.append(next.getVersion());
            if (it2.hasNext()) {
                sb.append(Chars.SPACE);
            }
        }
        return sb.toString();
    }

    @Override // com.google.firebase.platforminfo.UserAgentPublisher
    public String getUserAgent() {
        if (this.gamesSDKRegistrar.getRegisteredVersions().isEmpty()) {
            return this.javaSDKVersionUserAgent;
        }
        return this.javaSDKVersionUserAgent + Chars.SPACE + toUserAgent(this.gamesSDKRegistrar.getRegisteredVersions());
    }
}
