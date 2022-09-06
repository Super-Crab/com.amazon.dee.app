package org.threeten.bp;

import com.amazon.dee.sdk.iotsoftap.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.regex.Pattern;
import org.apache.logging.log4j.util.ProcessIdUtil;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.zone.ZoneRules;
import org.threeten.bp.zone.ZoneRulesException;
import org.threeten.bp.zone.ZoneRulesProvider;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public final class ZoneRegion extends ZoneId implements Serializable {
    private static final Pattern PATTERN = Pattern.compile("[A-Za-z][A-Za-z0-9~/._+-]+");
    private static final long serialVersionUID = 8386373296231747096L;
    private final String id;
    private final transient ZoneRules rules;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ZoneRegion(String str, ZoneRules zoneRules) {
        this.id = str;
        this.rules = zoneRules;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ZoneRegion ofId(String str, boolean z) {
        Jdk8Methods.requireNonNull(str, "zoneId");
        if (str.length() >= 2 && PATTERN.matcher(str).matches()) {
            ZoneRules zoneRules = null;
            try {
                zoneRules = ZoneRulesProvider.getRules(str, true);
            } catch (ZoneRulesException e) {
                if (str.equals("GMT0")) {
                    zoneRules = ZoneOffset.UTC.getRules();
                } else if (z) {
                    throw e;
                }
            }
            return new ZoneRegion(str, zoneRules);
        }
        throw new DateTimeException(GeneratedOutlineSupport1.outline72("Invalid ID for region-based ZoneId, invalid format: ", str));
    }

    private static ZoneRegion ofLenient(String str) {
        if (!str.equals("Z") && !str.startsWith("+") && !str.startsWith(ProcessIdUtil.DEFAULT_PROCESSID)) {
            if (!str.equals(Constants.UTC) && !str.equals("GMT") && !str.equals("UT")) {
                if (!str.startsWith("UTC+") && !str.startsWith("GMT+") && !str.startsWith("UTC-") && !str.startsWith("GMT-")) {
                    if (!str.startsWith("UT+") && !str.startsWith("UT-")) {
                        return ofId(str, false);
                    }
                    ZoneOffset of = ZoneOffset.of(str.substring(2));
                    if (of.getTotalSeconds() == 0) {
                        return new ZoneRegion("UT", of.getRules());
                    }
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("UT");
                    outline107.append(of.getId());
                    return new ZoneRegion(outline107.toString(), of.getRules());
                }
                ZoneOffset of2 = ZoneOffset.of(str.substring(3));
                if (of2.getTotalSeconds() == 0) {
                    return new ZoneRegion(str.substring(0, 3), of2.getRules());
                }
                return new ZoneRegion(str.substring(0, 3) + of2.getId(), of2.getRules());
            }
            return new ZoneRegion(str, ZoneOffset.UTC.getRules());
        }
        throw new DateTimeException(GeneratedOutlineSupport1.outline72("Invalid ID for region-based ZoneId, invalid format: ", str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ZoneId readExternal(DataInput dataInput) throws IOException {
        return ofLenient(dataInput.readUTF());
    }

    private Object readResolve() throws ObjectStreamException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new Ser((byte) 7, this);
    }

    @Override // org.threeten.bp.ZoneId
    public String getId() {
        return this.id;
    }

    @Override // org.threeten.bp.ZoneId
    public ZoneRules getRules() {
        ZoneRules zoneRules = this.rules;
        return zoneRules != null ? zoneRules : ZoneRulesProvider.getRules(this.id, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.threeten.bp.ZoneId
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeByte(7);
        writeExternal(dataOutput);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeExternal(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(this.id);
    }
}
