package com.amazon.alexa.accessory.davs;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
/* loaded from: classes.dex */
public interface DeviceArtifactContract {

    /* loaded from: classes.dex */
    public static final class ArtifactIdentifier {
        private static final String EXTENSION = ".bin";
        private static final String SEPARATOR = "_";
        private final String artifactIdentifier;
        private final String artifactKey;
        private final String artifactType;

        /* loaded from: classes.dex */
        public static final class Builder {
            private String artifactIdentifier;
            private String artifactKey;
            private String artifactType;

            public Builder artifactIdentifier(String str) {
                this.artifactIdentifier = str;
                return this;
            }

            public Builder artifactKey(String str) {
                this.artifactKey = str;
                return this;
            }

            public Builder artifactType(String str) {
                this.artifactType = str;
                return this;
            }

            public ArtifactIdentifier build() {
                Preconditions.notEmpty(this.artifactType, "artifactType");
                Preconditions.notEmpty(this.artifactKey, "artifactKey");
                Preconditions.notEmpty(this.artifactIdentifier, "artifactIdentifier");
                return new ArtifactIdentifier(this);
            }
        }

        public String toIdentifierString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.artifactType);
            sb.append("_");
            sb.append(this.artifactKey);
            sb.append("_");
            return GeneratedOutlineSupport1.outline91(sb, this.artifactIdentifier, EXTENSION);
        }

        private ArtifactIdentifier(Builder builder) {
            this.artifactType = builder.artifactType;
            this.artifactKey = builder.artifactKey;
            this.artifactIdentifier = builder.artifactIdentifier;
        }
    }

    /* loaded from: classes.dex */
    public static final class ArtifactPackage {
        private final ArtifactPackageIdentifier artifactPackageIdentifier;
        private final DavsArtifactSignature davsArtifactSignature;
        private final File file;

        private ArtifactPackage(File file, ArtifactPackageIdentifier artifactPackageIdentifier, DavsArtifactSignature davsArtifactSignature) {
            this.file = file;
            this.artifactPackageIdentifier = artifactPackageIdentifier;
            this.davsArtifactSignature = davsArtifactSignature;
        }

        public static ArtifactPackage create(File file, ArtifactPackageIdentifier artifactPackageIdentifier, DavsArtifactSignature davsArtifactSignature) {
            String artifactIdentifier = artifactPackageIdentifier.getArtifactIdentifier();
            Preconditions.validFileName(artifactIdentifier, "artifactIdentifier");
            return new ArtifactPackage(new File(file, artifactIdentifier), artifactPackageIdentifier, davsArtifactSignature);
        }

        public static ArtifactPackage createFromExistingFile(File file, ArtifactPackageIdentifier artifactPackageIdentifier, DavsArtifactSignature davsArtifactSignature) {
            return new ArtifactPackage(file, artifactPackageIdentifier, davsArtifactSignature);
        }

        public String getArtifactName() {
            return this.artifactPackageIdentifier.getArtifactName();
        }

        public ArtifactPackageIdentifier getArtifactPackageIdentifier() {
            return this.artifactPackageIdentifier;
        }

        public DavsArtifactSignature getDavsArtifactSignature() {
            return this.davsArtifactSignature;
        }

        public File getFile() {
            return this.file;
        }
    }

    /* loaded from: classes.dex */
    public static final class ArtifactPackageIdentifier {
        private final String artifactIdentifier;
        private final String artifactName;

        /* loaded from: classes.dex */
        public static final class Builder {
            private String artifactIdentifier;
            private String artifactName;

            public Builder artifactIdentifier(ArtifactIdentifier artifactIdentifier) {
                this.artifactIdentifier = artifactIdentifier.toIdentifierString();
                return this;
            }

            public Builder artifactName(String str) {
                this.artifactName = str;
                return this;
            }

            public ArtifactPackageIdentifier build() {
                Preconditions.notEmpty(this.artifactName, "artifactName");
                Preconditions.notNull(this.artifactIdentifier, "artifactIdentifier");
                return new ArtifactPackageIdentifier(this);
            }

            public Builder artifactIdentifier(String str) {
                this.artifactIdentifier = str;
                return this;
            }
        }

        public String getArtifactIdentifier() {
            return this.artifactIdentifier;
        }

        public String getArtifactName() {
            return this.artifactName;
        }

        private ArtifactPackageIdentifier(Builder builder) {
            this.artifactName = builder.artifactName;
            this.artifactIdentifier = builder.artifactIdentifier;
        }
    }
}
