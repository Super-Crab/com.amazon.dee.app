package com.amazon.identity.auth.device;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import com.amazon.identity.auth.accounts.InvalidSubAuthenticatorDefinitionException;
import com.amazon.identity.auth.device.framework.MAPApplicationInformationQueryer;
import com.amazon.identity.auth.device.utils.AccountConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class al {
    private static final String TAG = "com.amazon.identity.auth.device.al";
    private final ek cp;
    private final ak dc;
    private final MAPApplicationInformationQueryer dd;
    private final Object de;
    private List<aj> df;
    private int dg;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class a {
        private String bm;
        private XmlResourceParser dh;

        public a(String str, XmlResourceParser xmlResourceParser) {
            this.bm = str;
            this.dh = xmlResourceParser;
        }

        public XmlResourceParser af() {
            return this.dh;
        }

        public String getPackageName() {
            return this.bm;
        }
    }

    public al(Context context) {
        this(new ak(), new ek(context), MAPApplicationInformationQueryer.E(context));
    }

    private List<aj> ab() {
        synchronized (this.de) {
            int i = this.dg;
            if (this.df != null) {
                return this.df;
            }
            List<aj> ac = ac();
            List<aj> ad = ad();
            ArrayList arrayList = new ArrayList();
            HashSet hashSet = new HashSet();
            for (aj ajVar : ac) {
                hashSet.add(ajVar.packageName);
                arrayList.add(ajVar);
            }
            for (aj ajVar2 : ad) {
                if (!hashSet.contains(ajVar2.packageName)) {
                    arrayList.add(ajVar2);
                } else {
                    String str = TAG;
                    new StringBuilder("Deduped sub-authenticator").append(ajVar2.packageName);
                    io.dm(str);
                }
            }
            List<aj> unmodifiableList = Collections.unmodifiableList(b(arrayList));
            synchronized (this.de) {
                if (i == this.dg) {
                    this.df = unmodifiableList;
                }
            }
            return unmodifiableList;
        }
    }

    private List<aj> ac() {
        ServiceInfo serviceInfo;
        XmlResourceParser a2;
        ArrayList arrayList = new ArrayList();
        for (ResolveInfo resolveInfo : this.cp.queryIntentServices(new Intent("com.amazon.dcp.sso.AccountSubAuthenticator"), 128)) {
            try {
                serviceInfo = resolveInfo.serviceInfo;
                a2 = this.cp.a(serviceInfo);
            } catch (InvalidSubAuthenticatorDefinitionException e) {
                io.e(TAG, String.format("Ignored invalid sub authenticator from calling package : %s", e.getMessage()));
            }
            if (a2 != null) {
                aj a3 = this.dc.a(((PackageItemInfo) serviceInfo).packageName, ((PackageItemInfo) serviceInfo).name, a2);
                arrayList.add(a3);
                String str = TAG;
                String.format("Detected sub-authenticator: %s/%s", a3.packageName, a3.className);
                io.dm(str);
                String str2 = TAG;
                String.format(" Supports token types:", new Object[0]);
                io.dm(str2);
                Iterator<String> it2 = a3.cV.iterator();
                while (it2.hasNext()) {
                    String str3 = TAG;
                    String.format("  %s", it2.next());
                    io.dm(str3);
                }
            } else {
                throw new InvalidSubAuthenticatorDefinitionException(String.format("%s does not have a valid sub authenticator metadata file", ((PackageItemInfo) serviceInfo).packageName));
                break;
            }
        }
        return arrayList;
    }

    private List<aj> ad() {
        ArrayList arrayList = new ArrayList();
        for (a aVar : ae()) {
            try {
                aj a2 = this.dc.a(aVar.getPackageName(), null, aVar.af());
                arrayList.add(a2);
                String str = TAG;
                String.format("Detected DMS sub-authenticator: %s/%s", a2.packageName, a2.className);
                io.dm(str);
                String str2 = TAG;
                String.format(" Supports token types:", new Object[0]);
                io.dm(str2);
                Iterator<String> it2 = a2.cV.iterator();
                while (it2.hasNext()) {
                    String str3 = TAG;
                    String.format("  %s", it2.next());
                    io.dm(str3);
                }
            } catch (InvalidSubAuthenticatorDefinitionException e) {
                io.e(TAG, String.format("Ignored invalid sub authenticator from calling package : %s", e.getMessage()));
            }
        }
        return arrayList;
    }

    private List<a> ae() {
        int identifier;
        Set<String> ee = this.cp.ee();
        ArrayList arrayList = new ArrayList();
        for (String str : ee) {
            try {
                Resources resourcesForApplication = this.cp.getResourcesForApplication(str);
                if (resourcesForApplication != null && (identifier = resourcesForApplication.getIdentifier(AccountConstants.DMS_SUB_AUTHENTICATOR_XML_FILE_NAME, "xml", str)) != 0) {
                    arrayList.add(new a(str, resourcesForApplication.getXml(identifier)));
                }
            } catch (PackageManager.NameNotFoundException unused) {
                String str2 = TAG;
                StringBuilder sb = new StringBuilder("Cannot get resources for applicatoin. ");
                sb.append(str);
                sb.append(" not found");
                io.dm(str2);
            }
        }
        return arrayList;
    }

    private List<aj> b(List<aj> list) {
        ArrayList arrayList = new ArrayList();
        for (aj ajVar : list) {
            String str = ajVar.packageName;
            if (this.dd.bm(str) == null) {
                io.a("Package %s is not a MAP R5 app, so using it's Sub Auth.", str);
                arrayList.add(ajVar);
            } else {
                io.a("Package %s is a MAP R5 app, so not using it's Sub Auth.", str);
            }
        }
        return arrayList;
    }

    public List<aj> aa() {
        return ab();
    }

    public al(ak akVar, ek ekVar, MAPApplicationInformationQueryer mAPApplicationInformationQueryer) {
        this.de = new Object[0];
        this.dg = 0;
        this.cp = ekVar;
        this.dd = mAPApplicationInformationQueryer;
        this.dc = akVar;
    }
}
