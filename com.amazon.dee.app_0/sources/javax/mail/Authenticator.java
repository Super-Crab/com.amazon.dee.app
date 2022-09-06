package javax.mail;

import java.net.InetAddress;
/* loaded from: classes3.dex */
public abstract class Authenticator {
    private int requestingPort;
    private String requestingPrompt;
    private String requestingProtocol;
    private InetAddress requestingSite;
    private String requestingUserName;

    /* JADX INFO: Access modifiers changed from: protected */
    public final String getDefaultUserName() {
        return this.requestingUserName;
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return null;
    }

    protected final int getRequestingPort() {
        return this.requestingPort;
    }

    protected final String getRequestingPrompt() {
        return this.requestingPrompt;
    }

    protected final String getRequestingProtocol() {
        return this.requestingProtocol;
    }

    protected final InetAddress getRequestingSite() {
        return this.requestingSite;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized PasswordAuthentication requestPasswordAuthentication(InetAddress inetAddress, int i, String str, String str2, String str3) {
        this.requestingSite = inetAddress;
        this.requestingPort = i;
        this.requestingProtocol = str;
        this.requestingPrompt = str2;
        this.requestingUserName = str3;
        return getPasswordAuthentication();
    }
}
