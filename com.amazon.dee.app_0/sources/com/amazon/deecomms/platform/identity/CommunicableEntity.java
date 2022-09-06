package com.amazon.deecomms.platform.identity;

import android.text.TextUtils;
import com.amazon.deecomms.platform.identity.Exceptions.MalformedCommsIDException;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.animated.InterpolationAnimatedNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes12.dex */
public final class CommunicableEntity {
    private static final String DEFAULT_DOMAIN = "id";
    private static final String DEFAULT_ENTITY_ID_PROVIDER = "amzn";
    private static final int DEFAULT_ENTITY_ID_VERSION = 1;
    private static final String DEFAULT_NAMESPACE = "amzn1.comms";
    private static final String DEFAULT_SCHEME = "sips";
    public static final String ENTITY_ID_PROVIDER_AMAZON = "amzn";
    public static final String ENTITY_ID_PROVIDER_PSTN = "pstn";
    private static final String ID_COMMS_SEPARATOR = "@";
    private static final String INFO_ID_SEPARATOR = "~";
    private static final String SEPARATOR = ".";
    private static final String SEPARATOR_REGEX = "\\.";
    private final String mCommsID;
    private final String mCommsIDShortened;
    private String mEntityID;
    private static final Entity DEFAULT_ENTITY_TYPE = Entity.PERSON;
    private static final String NON_DIGITS_ONLY = "\\D+";
    private static final Pattern ENTITY_ID_PROVIDER_PATTERN = Pattern.compile(NON_DIGITS_ONLY);
    private static final String DIGITS_ONLY = "\\d+";
    private static final Pattern ENTITY_ID_VERSION_PATTERN = Pattern.compile(DIGITS_ONLY);
    private String mScheme = "sips";
    private String mNamespace = DEFAULT_NAMESPACE;
    private String mDomain = "id";
    private Entity mEntityType = DEFAULT_ENTITY_TYPE;
    private String mEntityIDProvider = "amzn";
    private int mEntityIDVersion = 1;

    private CommunicableEntity(String str) throws MalformedCommsIDException, IllegalArgumentException {
        if (!TextUtils.isEmpty(str)) {
            if (!str.contains(ID_COMMS_SEPARATOR)) {
                parseCommsID(str);
                this.mCommsIDShortened = buildCommsIDShortened();
                this.mCommsID = buildCommsID();
                return;
            }
            throw new IllegalArgumentException("Use fromAOR() to deconstruct a SIP AOR");
        }
        throw new IllegalArgumentException("commsID cannot be empty");
    }

    private String buildCommsID() {
        return this.mNamespace + "." + buildCommsIDShortened();
    }

    private String buildCommsIDShortened() {
        return this.mDomain + "." + this.mEntityType + "." + this.mEntityIDProvider + this.mEntityIDVersion + INFO_ID_SEPARATOR + this.mEntityID;
    }

    private int extractEntityIDVersion(String str) throws MalformedCommsIDException {
        Matcher matcher = ENTITY_ID_VERSION_PATTERN.matcher(str);
        if (matcher.find()) {
            return Integer.parseInt(str.substring(matcher.start(), matcher.end()));
        }
        throw new MalformedCommsIDException("No entity ID version specified in the commsID");
    }

    private String extractEntityProvider(String str) throws MalformedCommsIDException {
        Matcher matcher = ENTITY_ID_PROVIDER_PATTERN.matcher(str);
        if (matcher.find()) {
            return str.substring(matcher.start(), matcher.end());
        }
        throw new MalformedCommsIDException("No entity provider specified in the commsID");
    }

    public static CommunicableEntity fromCommsID(String str) throws MalformedCommsIDException, IllegalArgumentException {
        return new CommunicableEntity(str);
    }

    private void parseCommsID(String str) throws MalformedCommsIDException {
        if (parseOldSStyleCommsID(str)) {
            return;
        }
        String[] split = str.trim().split(INFO_ID_SEPARATOR);
        if (split.length == 2) {
            this.mEntityID = split[1].trim();
            ArrayList arrayList = new ArrayList(Arrays.asList(split[0].split("\\.")));
            if (arrayList.size() >= 3) {
                int size = arrayList.size() - 1;
                this.mEntityIDVersion = extractEntityIDVersion((String) arrayList.get(size));
                this.mEntityIDProvider = extractEntityProvider((String) arrayList.get(size)).trim();
                int i = size - 1;
                arrayList.remove(size);
                this.mEntityType = Entity.getValueOf(((String) arrayList.get(i)).trim());
                int i2 = i - 1;
                arrayList.remove(i);
                this.mDomain = ((String) arrayList.get(i2)).trim();
                arrayList.remove(i2);
                String trim = TextUtils.join(".", arrayList).trim();
                if (TextUtils.isEmpty(trim)) {
                    return;
                }
                this.mNamespace = trim;
                return;
            }
            throw new MalformedCommsIDException(GeneratedOutlineSupport1.outline75("Expecting {Domain}.{EntityType}.{EntityIDProvider}{EntityIDVersion}~{EntityID} to be present in the commsID. Provided: [", str, "]"));
        }
        throw new MalformedCommsIDException("Expecting entityID to be separated by '~'.");
    }

    private boolean parseOldSStyleCommsID(String str) {
        ArrayList arrayList = new ArrayList(Arrays.asList(str.split("\\.")));
        if (arrayList.size() != 5 || !((String) arrayList.get(0)).equals("amzn1") || !((String) arrayList.get(1)).equals("alexa-comms") || !((String) arrayList.get(2)).equals(InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY) || !((String) arrayList.get(3)).equals("comms-id")) {
            return false;
        }
        this.mEntityID = (String) arrayList.get(4);
        return true;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && CommunicableEntity.class == obj.getClass()) {
            return getCommsID().equals(((CommunicableEntity) obj).getCommsID());
        }
        return false;
    }

    public String getCommsID() {
        return this.mCommsID;
    }

    public String getCommsIDShortened() {
        return this.mCommsIDShortened;
    }

    public String getDomain() {
        return this.mDomain;
    }

    public String getEntityIDProvider() {
        return this.mEntityIDProvider;
    }

    public int getEntityIDVersion() {
        return this.mEntityIDVersion;
    }

    public Entity getEntityType() {
        return this.mEntityType;
    }

    public String getNamespace() {
        return this.mNamespace;
    }

    public String getScheme() {
        return this.mScheme;
    }

    public int hashCode() {
        return getCommsID().hashCode();
    }

    public boolean isHomegroup() {
        return getEntityType() == Entity.HOMEGROUP;
    }

    public boolean isPerson() {
        return getEntityType() == Entity.PERSON;
    }

    public String toString() {
        return getCommsID();
    }
}
