package androidx.room.migration.bundle;

import androidx.annotation.RestrictTo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public class SchemaBundle implements SchemaEquality<SchemaBundle> {
    private static final String CHARSET = "UTF-8";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().registerTypeAdapterFactory(new EntityTypeAdapterFactory()).create();
    public static final int LATEST_FORMAT = 1;
    @SerializedName("database")
    private DatabaseBundle mDatabase;
    @SerializedName("formatVersion")
    private int mFormatVersion;

    /* loaded from: classes.dex */
    private static class EntityTypeAdapterFactory implements TypeAdapterFactory {

        /* loaded from: classes.dex */
        private static class EntityTypeAdapter extends TypeAdapter<EntityBundle> {
            private final TypeAdapter<EntityBundle> mEntityBundleAdapter;
            private final TypeAdapter<FtsEntityBundle> mFtsEntityBundleAdapter;
            private final TypeAdapter<JsonElement> mJsonElementAdapter;

            EntityTypeAdapter(TypeAdapter<JsonElement> typeAdapter, TypeAdapter<EntityBundle> typeAdapter2, TypeAdapter<FtsEntityBundle> typeAdapter3) {
                this.mJsonElementAdapter = typeAdapter;
                this.mEntityBundleAdapter = typeAdapter2;
                this.mFtsEntityBundleAdapter = typeAdapter3;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.gson.TypeAdapter
            /* renamed from: read */
            public EntityBundle mo8353read(JsonReader jsonReader) throws IOException {
                JsonObject asJsonObject = this.mJsonElementAdapter.mo8353read(jsonReader).getAsJsonObject();
                if (asJsonObject.has("ftsVersion")) {
                    return this.mFtsEntityBundleAdapter.fromJsonTree(asJsonObject);
                }
                return this.mEntityBundleAdapter.fromJsonTree(asJsonObject);
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, EntityBundle entityBundle) throws IOException {
                if (entityBundle instanceof FtsEntityBundle) {
                    this.mFtsEntityBundleAdapter.write(jsonWriter, (FtsEntityBundle) entityBundle);
                } else {
                    this.mEntityBundleAdapter.write(jsonWriter, entityBundle);
                }
            }
        }

        EntityTypeAdapterFactory() {
        }

        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            if (!EntityBundle.class.isAssignableFrom(typeToken.getRawType())) {
                return null;
            }
            return new EntityTypeAdapter(gson.getAdapter(JsonElement.class), gson.getDelegateAdapter(this, TypeToken.get(EntityBundle.class)), gson.getDelegateAdapter(this, TypeToken.get(FtsEntityBundle.class)));
        }
    }

    public SchemaBundle(int i, DatabaseBundle databaseBundle) {
        this.mFormatVersion = i;
        this.mDatabase = databaseBundle;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static SchemaBundle deserialize(InputStream inputStream) throws UnsupportedEncodingException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
        try {
            return (SchemaBundle) GSON.fromJson((Reader) inputStreamReader, (Class<Object>) SchemaBundle.class);
        } finally {
            safeClose(inputStreamReader);
            safeClose(inputStream);
        }
    }

    private static void safeClose(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable unused) {
            }
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static void serialize(SchemaBundle schemaBundle, File file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file, false);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
        try {
            GSON.toJson(schemaBundle, outputStreamWriter);
        } finally {
            safeClose(outputStreamWriter);
            safeClose(fileOutputStream);
        }
    }

    public DatabaseBundle getDatabase() {
        return this.mDatabase;
    }

    public int getFormatVersion() {
        return this.mFormatVersion;
    }

    @Override // androidx.room.migration.bundle.SchemaEquality
    public boolean isSchemaEqual(SchemaBundle schemaBundle) {
        return SchemaEqualityUtil.checkSchemaEquality(this.mDatabase, schemaBundle.mDatabase) && this.mFormatVersion == schemaBundle.mFormatVersion;
    }
}
