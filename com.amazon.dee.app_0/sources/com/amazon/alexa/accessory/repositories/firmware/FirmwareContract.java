package com.amazon.alexa.accessory.repositories.firmware;

import com.amazon.alexa.accessory.internal.util.IOUtils;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.kota.PackageCandidateIdentifier;
import com.amazon.alexa.accessory.kota.PackageIdentifier;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public interface FirmwareContract {

    /* loaded from: classes6.dex */
    public static final class ArtifactFilter {
        private final String artifactKey;
        private final String artifactType;
        private final Map<String, List<String>> filters;
        private final String name;

        /* loaded from: classes6.dex */
        public static final class Builder {
            private String artifactKey;
            private String artifactType;
            private Map<String, List<String>> filters;
            private String name;

            public Builder artifactKey(String str) {
                this.artifactKey = str;
                return this;
            }

            public Builder artifactType(String str) {
                this.artifactType = str;
                return this;
            }

            public ArtifactFilter build() {
                Preconditions.notNull(this.name, "name");
                Preconditions.notNull(this.artifactType, "artifactType");
                Preconditions.notNull(this.artifactKey, "artifactKey");
                Preconditions.notNull(this.filters, "filters");
                return new ArtifactFilter(this);
            }

            public Builder filters(Map<String, List<String>> map) {
                this.filters = map;
                return this;
            }

            public Builder name(String str) {
                this.name = str;
                return this;
            }
        }

        ArtifactFilter(Builder builder) {
            this.name = builder.name;
            this.artifactType = builder.artifactType;
            this.artifactKey = builder.artifactKey;
            this.filters = builder.filters;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || ArtifactFilter.class != obj.getClass()) {
                return false;
            }
            ArtifactFilter artifactFilter = (ArtifactFilter) obj;
            if (!Objects.equals(this.name, artifactFilter.name) || !Objects.equals(this.artifactType, artifactFilter.artifactType) || !Objects.equals(this.artifactKey, artifactFilter.artifactKey)) {
                return false;
            }
            return Objects.equals(this.filters, artifactFilter.filters);
        }

        public String getArtifactKey() {
            return this.artifactKey;
        }

        public String getArtifactType() {
            return this.artifactType;
        }

        public Map<String, List<String>> getFilters() {
            return this.filters;
        }

        public String getName() {
            return this.name;
        }

        public int hashCode() {
            String str = this.artifactType;
            int i = 0;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.artifactKey;
            int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
            Map<String, List<String>> map = this.filters;
            int hashCode3 = (hashCode2 + (map != null ? map.hashCode() : 0)) * 31;
            String str3 = this.name;
            if (str3 != null) {
                i = str3.hashCode();
            }
            return hashCode3 + i;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ArtifactFilter{name='");
            GeneratedOutlineSupport1.outline176(outline107, this.name, Chars.QUOTE, "artifactType='");
            GeneratedOutlineSupport1.outline176(outline107, this.artifactType, Chars.QUOTE, "artifactKey='");
            GeneratedOutlineSupport1.outline176(outline107, this.artifactKey, Chars.QUOTE, ", filters='");
            outline107.append(this.filters);
            outline107.append(Chars.QUOTE);
            outline107.append(JsonReaderKt.END_OBJ);
            return outline107.toString();
        }
    }

    /* loaded from: classes6.dex */
    public static final class Component {
        private final String name;
        private String packageName;
        private final String reference;
        private final int version;

        /* loaded from: classes6.dex */
        public static final class Builder {
            boolean isVersionSet;
            String name;
            String packageName;
            String reference;
            int version;

            public Component build() {
                Preconditions.notNull(this.name, "name");
                Preconditions.notNull(this.reference, "reference");
                Preconditions.precondition(this.isVersionSet, "Version wasn't set!");
                return new Component(this);
            }

            public Builder name(String str) {
                this.name = str;
                return this;
            }

            public Builder reference(String str) {
                this.reference = str;
                return this;
            }

            public Builder version(int i) {
                this.version = i;
                this.isVersionSet = true;
                return this;
            }

            public Builder withPackageName(String str) {
                this.packageName = str;
                return this;
            }
        }

        Component(Builder builder) {
            this.name = builder.name;
            this.reference = builder.reference;
            this.version = builder.version;
            this.packageName = builder.packageName;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || Component.class != obj.getClass()) {
                return false;
            }
            Component component = (Component) obj;
            if (this.version != component.version) {
                return false;
            }
            String str = this.name;
            if (str == null ? component.name != null : !str.equals(component.name)) {
                return false;
            }
            String str2 = this.reference;
            if (str2 == null ? component.reference != null : !str2.equals(component.reference)) {
                return false;
            }
            String str3 = this.packageName;
            String str4 = component.packageName;
            return str3 != null ? str3.equals(str4) : str4 == null;
        }

        public String getName() {
            return this.name;
        }

        public String getPackageName() {
            return this.packageName;
        }

        public String getReference() {
            return this.reference;
        }

        public int getVersion() {
            return this.version;
        }

        public int hashCode() {
            String str = this.name;
            int i = 0;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.reference;
            int hashCode2 = (((hashCode + (str2 != null ? str2.hashCode() : 0)) * 31) + this.version) * 31;
            String str3 = this.packageName;
            if (str3 != null) {
                i = str3.hashCode();
            }
            return hashCode2 + i;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Component{name='");
            GeneratedOutlineSupport1.outline176(outline107, this.name, Chars.QUOTE, ", reference='");
            GeneratedOutlineSupport1.outline176(outline107, this.reference, Chars.QUOTE, ", version=");
            outline107.append(this.version);
            outline107.append(", packageName='");
            return GeneratedOutlineSupport1.outline90(outline107, this.packageName, Chars.QUOTE, JsonReaderKt.END_OBJ);
        }
    }

    /* loaded from: classes6.dex */
    public static final class Package {
        private static final String ARTIFACT_KEY_JSON_KEY = "artifactKey";
        private static final String ARTIFACT_TYPE_JSON_KEY = "artifactType";
        private static final String DEVICE_ARTIFACTS_JSON_KEY = "device_artifacts";
        private static final String FILTERS_JSON_KEY = "filters";
        private static final String NAME_JSON_KEY = "name";
        private static final String TAG = "FirmwareContract.Package:";
        private final File file;
        private volatile JSONObject fileJSON;
        private final PackageIdentifier packageIdentifier;

        /* loaded from: classes6.dex */
        public static final class Metadata {
            private final String reference;

            public Metadata(String str) {
                Preconditions.notNull(str, "reference");
                this.reference = str;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj != null && Metadata.class == obj.getClass()) {
                    return this.reference.equals(((Metadata) obj).reference);
                }
                return false;
            }

            public String getReference() {
                return this.reference;
            }

            public int hashCode() {
                return this.reference.hashCode();
            }

            public String toString() {
                return GeneratedOutlineSupport1.outline90(GeneratedOutlineSupport1.outline107("Metadata{reference='"), this.reference, Chars.QUOTE, JsonReaderKt.END_OBJ);
            }
        }

        private Package(File file, PackageIdentifier packageIdentifier) {
            Preconditions.notNull(file, "file");
            Preconditions.notNull(packageIdentifier, "packageIdentifier");
            this.file = file;
            this.packageIdentifier = packageIdentifier;
        }

        public static Package create(File file, PackageIdentifier packageIdentifier) {
            return new Package(new File(file, packageIdentifier.toIdentifierString()), packageIdentifier);
        }

        private synchronized JSONObject getFileJSON() throws IOException, JSONException {
            if (this.fileJSON != null) {
                return this.fileJSON;
            }
            ZipFile zipFile = new ZipFile(this.file, 1);
            ZipEntry entry = zipFile.getEntry("index.json");
            if (entry != null) {
                InputStream inputStream = zipFile.getInputStream(entry);
                this.fileJSON = new JSONObject(new String(IOUtils.streamToByteArray(inputStream)));
                JSONObject jSONObject = this.fileJSON;
                inputStream.close();
                zipFile.close();
                return jSONObject;
            }
            throw new IOException("Unable to get index.json from zip file " + this.file.getName());
        }

        public static Package greaterHop(Package r2, Package r3) {
            Preconditions.notNull(r2, "package1");
            Preconditions.notNull(r3, "package2");
            return r2.packageIdentifier.updateVersion.intValue() > r3.packageIdentifier.updateVersion.intValue() ? r2 : r3;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || Package.class != obj.getClass()) {
                return false;
            }
            Package r5 = (Package) obj;
            return Objects.equals(this.file, r5.file) && Objects.equals(this.packageIdentifier, r5.packageIdentifier);
        }

        public List<ArtifactFilter> getArtifactFilters() throws JSONException, IOException {
            JSONObject fileJSON = getFileJSON();
            if (!fileJSON.has(DEVICE_ARTIFACTS_JSON_KEY)) {
                Logger.d("%s Filter does not exist in the package. No DAVS component to update.", TAG);
                return Collections.emptyList();
            }
            JSONArray jSONArray = fileJSON.getJSONArray(DEVICE_ARTIFACTS_JSON_KEY);
            if (jSONArray.length() != 0) {
                ArrayList arrayList = new ArrayList();
                Gson gson = new Gson();
                Type type = new TypeToken<Map<String, List<String>>>() { // from class: com.amazon.alexa.accessory.repositories.firmware.FirmwareContract.Package.1
                }.getType();
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    arrayList.add(new ArtifactFilter.Builder().name(jSONObject.getString("name")).artifactType(jSONObject.getString(ARTIFACT_TYPE_JSON_KEY)).artifactKey(jSONObject.getString(ARTIFACT_KEY_JSON_KEY)).filters((Map) gson.fromJson(jSONObject.getJSONObject(FILTERS_JSON_KEY).toString(), type)).build());
                }
                return arrayList;
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Device artifacts are malformed for ");
            outline107.append(this.file.getName());
            throw new JSONException(outline107.toString());
        }

        public Component getComponent(String str) throws JSONException, IOException {
            for (Component component : getComponents()) {
                if (component.getName().equals(str)) {
                    return component;
                }
            }
            throw new FileNotFoundException(GeneratedOutlineSupport1.outline72("No matching component found with name ", str));
        }

        public List<Component> getComponents() throws IOException, JSONException {
            JSONArray jSONArray = getFileJSON().getJSONArray("components");
            if (jSONArray.length() != 0) {
                ArrayList arrayList = new ArrayList();
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    arrayList.add(new Component.Builder().version(jSONArray.getJSONObject(i).getInt("version")).name(jSONArray.getJSONObject(i).getString("name")).reference(jSONArray.getJSONObject(i).getString("reference")).withPackageName(this.file.getName()).build());
                }
                return arrayList;
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Components are malformed for ");
            outline107.append(this.file.getName());
            throw new JSONException(outline107.toString());
        }

        public Integer getCurrentVersion() {
            return this.packageIdentifier.packageCandidateIdentifier.currentVersion;
        }

        public File getFile() {
            return this.file;
        }

        public Metadata getMetadata() throws IOException, JSONException {
            return new Metadata(getFileJSON().getJSONObject("metadata").getString("reference"));
        }

        public String getName() throws IOException, JSONException {
            return getFileJSON().getString("name");
        }

        public String getReference() {
            return this.file.getPath();
        }

        public int getVersion() throws IOException, JSONException {
            return getFileJSON().getInt("version");
        }

        public int hashCode() {
            return Objects.hash(this.file, this.packageIdentifier, this.fileJSON);
        }

        public boolean isCandidate(PackageCandidateIdentifier packageCandidateIdentifier) {
            Preconditions.notNull(this.packageIdentifier, "packageIdentifier");
            return this.packageIdentifier.packageCandidateIdentifier.equals(packageCandidateIdentifier);
        }

        public static Package create(File file) throws IOException, ParseException {
            Preconditions.notNull(file, "file");
            File parentFile = file.getParentFile();
            if (parentFile != null) {
                try {
                    return create(parentFile, PackageIdentifier.fromIdentifierString(file.getName()));
                } catch (ParseException e) {
                    Logger.e("Error trying to parse firmware file name %s, deleted it: %b", e, file.getName(), Boolean.valueOf(file.delete()));
                    throw e;
                }
            }
            throw new IOException(String.format(Locale.US, "Directory of file was null! File: %s", file));
        }
    }
}
