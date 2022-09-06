package androidx.room.migration.bundle;

import androidx.annotation.RestrictTo;
import com.google.gson.annotations.SerializedName;
import java.util.List;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public class FtsOptionsBundle implements SchemaEquality<FtsOptionsBundle> {
    @SerializedName("contentTable")
    private final String mContentTable;
    @SerializedName("languageIdColumnName")
    private final String mLanguageIdColumnName;
    @SerializedName("matchInfo")
    private final String mMatchInfo;
    @SerializedName("notIndexedColumns")
    private final List<String> mNotIndexedColumns;
    @SerializedName("preferredOrder")
    private final String mPreferredOrder;
    @SerializedName("prefixSizes")
    private final List<Integer> mPrefixSizes;
    @SerializedName("tokenizer")
    private final String mTokenizer;
    @SerializedName("tokenizerArgs")
    private final List<String> mTokenizerArgs;

    public FtsOptionsBundle(String str, List<String> list, String str2, String str3, String str4, List<String> list2, List<Integer> list3, String str5) {
        this.mTokenizer = str;
        this.mTokenizerArgs = list;
        this.mContentTable = str2;
        this.mLanguageIdColumnName = str3;
        this.mMatchInfo = str4;
        this.mNotIndexedColumns = list2;
        this.mPrefixSizes = list3;
        this.mPreferredOrder = str5;
    }

    public String getContentTable() {
        return this.mContentTable;
    }

    @Override // androidx.room.migration.bundle.SchemaEquality
    public boolean isSchemaEqual(FtsOptionsBundle ftsOptionsBundle) {
        return this.mTokenizer.equals(ftsOptionsBundle.mTokenizer) && this.mTokenizerArgs.equals(ftsOptionsBundle.mTokenizerArgs) && this.mContentTable.equals(ftsOptionsBundle.mContentTable) && this.mLanguageIdColumnName.equals(ftsOptionsBundle.mLanguageIdColumnName) && this.mMatchInfo.equals(ftsOptionsBundle.mMatchInfo) && this.mNotIndexedColumns.equals(ftsOptionsBundle.mNotIndexedColumns) && this.mPrefixSizes.equals(ftsOptionsBundle.mPrefixSizes) && this.mPreferredOrder.equals(ftsOptionsBundle.mPreferredOrder);
    }
}
