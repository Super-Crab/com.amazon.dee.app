package com.amazon.comms.ringservice.util;

import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.amazon.comms.log.CommsLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes12.dex */
public class SipUri {
    public static final String PARAM_GR = "gr";
    private String domain;
    private Map<String, String> headers;
    private Map<String, String> params;
    private String password;
    private int port;
    private String scheme;
    private String user;
    private static final CommsLogger log = CommsLogger.getLogger(SipUri.class);
    private static final Pattern SIP_URI_FORMAT = Pattern.compile("<?([^:]*):([^@:]*):?([^@]*)@([^:;>\\?]*):?([0-9]*);?([^>\\?]*)\\??([^>]*)");

    /* loaded from: classes12.dex */
    public static class SipUriBuilder {
        private String domain;
        private ArrayList<String> headers$key;
        private ArrayList<String> headers$value;
        private ArrayList<String> params$key;
        private ArrayList<String> params$value;
        private String password;
        private int port;
        private String scheme;
        private String user;

        SipUriBuilder() {
        }

        public SipUri build() {
            Map emptyMap;
            Map emptyMap2;
            ArrayList<String> arrayList = this.params$key;
            int size = arrayList == null ? 0 : arrayList.size();
            int i = Integer.MAX_VALUE;
            if (size == 0) {
                emptyMap = Collections.emptyMap();
            } else if (size != 1) {
                LinkedHashMap linkedHashMap = new LinkedHashMap(this.params$key.size() < 1073741824 ? this.params$key.size() + 1 + ((this.params$key.size() - 3) / 3) : Integer.MAX_VALUE);
                for (int i2 = 0; i2 < this.params$key.size(); i2++) {
                    linkedHashMap.put(this.params$key.get(i2), this.params$value.get(i2));
                }
                emptyMap = Collections.unmodifiableMap(linkedHashMap);
            } else {
                emptyMap = Collections.singletonMap(this.params$key.get(0), this.params$value.get(0));
            }
            Map map = emptyMap;
            ArrayList<String> arrayList2 = this.headers$key;
            int size2 = arrayList2 == null ? 0 : arrayList2.size();
            if (size2 == 0) {
                emptyMap2 = Collections.emptyMap();
            } else if (size2 != 1) {
                if (this.headers$key.size() < 1073741824) {
                    i = this.headers$key.size() + 1 + ((this.headers$key.size() - 3) / 3);
                }
                LinkedHashMap linkedHashMap2 = new LinkedHashMap(i);
                for (int i3 = 0; i3 < this.headers$key.size(); i3++) {
                    linkedHashMap2.put(this.headers$key.get(i3), this.headers$value.get(i3));
                }
                emptyMap2 = Collections.unmodifiableMap(linkedHashMap2);
            } else {
                emptyMap2 = Collections.singletonMap(this.headers$key.get(0), this.headers$value.get(0));
            }
            return new SipUri(this.scheme, this.user, this.password, this.domain, this.port, map, emptyMap2);
        }

        public SipUriBuilder clearHeaders() {
            ArrayList<String> arrayList = this.headers$key;
            if (arrayList != null) {
                arrayList.clear();
                this.headers$value.clear();
            }
            return this;
        }

        public SipUriBuilder clearParams() {
            ArrayList<String> arrayList = this.params$key;
            if (arrayList != null) {
                arrayList.clear();
                this.params$value.clear();
            }
            return this;
        }

        public SipUriBuilder domain(String str) {
            this.domain = str;
            return this;
        }

        public SipUriBuilder header(String str, String str2) {
            if (this.headers$key == null) {
                this.headers$key = new ArrayList<>();
                this.headers$value = new ArrayList<>();
            }
            this.headers$key.add(str);
            this.headers$value.add(str2);
            return this;
        }

        public SipUriBuilder headers(Map<? extends String, ? extends String> map) {
            if (this.headers$key == null) {
                this.headers$key = new ArrayList<>();
                this.headers$value = new ArrayList<>();
            }
            for (Map.Entry<? extends String, ? extends String> entry : map.entrySet()) {
                this.headers$key.add(entry.getKey());
                this.headers$value.add(entry.getValue());
            }
            return this;
        }

        public SipUriBuilder param(String str, String str2) {
            if (this.params$key == null) {
                this.params$key = new ArrayList<>();
                this.params$value = new ArrayList<>();
            }
            this.params$key.add(str);
            this.params$value.add(str2);
            return this;
        }

        public SipUriBuilder params(Map<? extends String, ? extends String> map) {
            if (this.params$key == null) {
                this.params$key = new ArrayList<>();
                this.params$value = new ArrayList<>();
            }
            for (Map.Entry<? extends String, ? extends String> entry : map.entrySet()) {
                this.params$key.add(entry.getKey());
                this.params$value.add(entry.getValue());
            }
            return this;
        }

        public SipUriBuilder password(String str) {
            this.password = str;
            return this;
        }

        public SipUriBuilder port(int i) {
            this.port = i;
            return this;
        }

        public SipUriBuilder scheme(String str) {
            this.scheme = str;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SipUri.SipUriBuilder(scheme=");
            outline107.append(this.scheme);
            outline107.append(", user=");
            outline107.append(this.user);
            outline107.append(", password=");
            outline107.append(this.password);
            outline107.append(", domain=");
            outline107.append(this.domain);
            outline107.append(", port=");
            outline107.append(this.port);
            outline107.append(", params$key=");
            outline107.append(this.params$key);
            outline107.append(", params$value=");
            outline107.append(this.params$value);
            outline107.append(", headers$key=");
            outline107.append(this.headers$key);
            outline107.append(", headers$value=");
            outline107.append(this.headers$value);
            outline107.append(")");
            return outline107.toString();
        }

        public SipUriBuilder user(String str) {
            this.user = str;
            return this;
        }
    }

    public SipUri(String str, String str2, String str3, String str4, int i, Map<String, String> map, Map<String, String> map2) {
        this.scheme = str;
        this.user = str2;
        this.password = str3;
        this.domain = str4;
        this.port = i;
        this.params = map;
        this.headers = map2;
    }

    public static SipUriBuilder builder() {
        return new SipUriBuilder();
    }

    public String getAOR() {
        String format = String.format(Locale.US, "%s:%s", this.scheme, this.user);
        if (!Strings.isNullOrEmpty(this.password)) {
            StringBuilder outline113 = GeneratedOutlineSupport1.outline113(format, ":");
            outline113.append(this.password);
            format = outline113.toString();
        }
        StringBuilder outline1132 = GeneratedOutlineSupport1.outline113(format, "@");
        outline1132.append(this.domain);
        String sb = outline1132.toString();
        if (this.port != 0) {
            StringBuilder outline1133 = GeneratedOutlineSupport1.outline113(sb, ":");
            outline1133.append(this.port);
            return outline1133.toString();
        }
        return sb;
    }

    public String getDomain() {
        return this.domain;
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }

    public Map<String, String> getParams() {
        return this.params;
    }

    public String getPassword() {
        return this.password;
    }

    public int getPort() {
        return this.port;
    }

    public String getScheme() {
        return this.scheme;
    }

    public String getUser() {
        return this.user;
    }

    public SipUriBuilder toBuilder() {
        return new SipUriBuilder().scheme(this.scheme).user(this.user).password(this.password).domain(this.domain).port(this.port).params(this.params).headers(this.headers);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getAOR());
        for (Map.Entry<String, String> entry : this.params.entrySet()) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(";");
            outline107.append(entry.getKey());
            sb.append(outline107.toString());
            if (!Strings.isNullOrEmpty(entry.getValue())) {
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107(Config.Compare.EQUAL_TO);
                outline1072.append(entry.getValue());
                sb.append(outline1072.toString());
            }
        }
        boolean z = true;
        for (Map.Entry<String, String> entry2 : this.headers.entrySet()) {
            if (z) {
                sb.append(WebConstants.UriConstants.QUESTIONMARK_KEY);
            } else {
                sb.append(WebConstants.UriConstants.AMPERSAND_KEY);
            }
            sb.append(entry2.getKey() + Config.Compare.EQUAL_TO + entry2.getValue());
            z = false;
        }
        return sb.toString();
    }

    public SipUri(String str) throws URISyntaxException {
        Matcher matcher = SIP_URI_FORMAT.matcher(str);
        if (matcher.find()) {
            this.scheme = matcher.group(1);
            this.user = matcher.group(2);
            this.password = matcher.group(3);
            this.domain = matcher.group(4);
            String group = matcher.group(5);
            if (!Strings.isNullOrEmpty(group)) {
                this.port = Integer.parseInt(group);
            } else {
                this.port = 0;
            }
            String group2 = matcher.group(6);
            String group3 = matcher.group(7);
            this.params = new LinkedHashMap();
            if (!Strings.isNullOrEmpty(group2)) {
                for (String str2 : group2.split(";")) {
                    String[] split = str2.split(Config.Compare.EQUAL_TO);
                    if (split.length > 1) {
                        this.params.put(split[0], split[1]);
                    } else {
                        this.params.put(split[0], "");
                    }
                }
            }
            this.headers = new LinkedHashMap();
            if (Strings.isNullOrEmpty(group3)) {
                return;
            }
            for (String str3 : group3.split(WebConstants.UriConstants.AMPERSAND_KEY)) {
                String[] split2 = str3.split(Config.Compare.EQUAL_TO);
                if (split2.length > 1) {
                    this.headers.put(split2[0], split2[1]);
                } else {
                    throw new URISyntaxException(str, "Cannot parse");
                }
            }
            return;
        }
        throw new URISyntaxException(str, "Cannot parse");
    }
}
