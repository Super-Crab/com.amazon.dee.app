package com.amazon.alexa.accessory.kota;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.kota.PackageCandidateIdentifier;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.text.ParseException;
import java.util.Objects;
/* loaded from: classes.dex */
public final class PackageIdentifier {
    private static final String DELIMETER = "-";
    private static final String fileExtension = ".zip";
    public final PackageCandidateIdentifier packageCandidateIdentifier;
    public final Integer updateVersion;

    public PackageIdentifier(PackageCandidateIdentifier packageCandidateIdentifier, Integer num) {
        Preconditions.notNull(packageCandidateIdentifier, "packageCandidateIdentifier");
        Preconditions.notNull(num, "updateVersion");
        Preconditions.precondition(num.intValue() >= 0, "Update version cannot be negative");
        this.packageCandidateIdentifier = packageCandidateIdentifier;
        this.updateVersion = num;
    }

    public static PackageIdentifier fromIdentifierString(String str) throws ParseException {
        Preconditions.notNull(str, "identifier");
        String[] split = str.split("-");
        if (split.length == 5) {
            try {
                PackageCandidateIdentifier build = new PackageCandidateIdentifier.Builder().deviceType(split[0]).deviceSerialNumber(split[1]).componentId(split[2]).currentVersion(Integer.valueOf(Integer.parseInt(split[3]))).build();
                if (split[4].substring(split[4].length() - 4).equals(fileExtension)) {
                    return new PackageIdentifier(build, Integer.valueOf(Integer.parseInt(split[4].substring(0, split[4].length() - 4))));
                }
                throw new ParseException("Could not parse updateVersion from identifier " + str, 0);
            } catch (NumberFormatException e) {
                throw new ParseException(e.toString(), 0);
            }
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Package identifier must contain 5 components. Contained ");
        outline107.append(split.length);
        throw new ParseException(outline107.toString(), 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || PackageIdentifier.class != obj.getClass()) {
            return false;
        }
        PackageIdentifier packageIdentifier = (PackageIdentifier) obj;
        return Objects.equals(this.packageCandidateIdentifier, packageIdentifier.packageCandidateIdentifier) && Objects.equals(this.updateVersion, packageIdentifier.updateVersion);
    }

    public int hashCode() {
        return Objects.hash(this.packageCandidateIdentifier, this.updateVersion);
    }

    public String toIdentifierString() {
        return this.packageCandidateIdentifier.deviceType + "-" + this.packageCandidateIdentifier.deviceSerialNumber + "-" + this.packageCandidateIdentifier.componentId + "-" + this.packageCandidateIdentifier.currentVersion + "-" + this.updateVersion + fileExtension;
    }
}
