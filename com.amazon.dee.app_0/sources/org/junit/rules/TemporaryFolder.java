package org.junit.rules;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.io.IOException;
/* loaded from: classes5.dex */
public class TemporaryFolder extends ExternalResource {
    private File folder;
    private final File parentFolder;

    public TemporaryFolder() {
        this(null);
    }

    private File createTemporaryFolderIn(File file) throws IOException {
        File createTempFile = File.createTempFile("junit", "", file);
        createTempFile.delete();
        createTempFile.mkdir();
        return createTempFile;
    }

    private boolean isLastElementInArray(int i, String[] strArr) {
        return i == strArr.length - 1;
    }

    private void recursiveDelete(File file) {
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File file2 : listFiles) {
                recursiveDelete(file2);
            }
        }
        file.delete();
    }

    private void validateFolderName(String str) throws IOException {
        if (new File(str).getParent() == null) {
            return;
        }
        throw new IOException("Folder name cannot consist of multiple path components separated by a file separator. Please use newFolder('MyParentFolder','MyFolder') to create hierarchies of folders");
    }

    @Override // org.junit.rules.ExternalResource
    protected void after() {
        delete();
    }

    @Override // org.junit.rules.ExternalResource
    protected void before() throws Throwable {
        create();
    }

    public void create() throws IOException {
        this.folder = createTemporaryFolderIn(this.parentFolder);
    }

    public void delete() {
        File file = this.folder;
        if (file != null) {
            recursiveDelete(file);
        }
    }

    public File getRoot() {
        File file = this.folder;
        if (file != null) {
            return file;
        }
        throw new IllegalStateException("the temporary folder has not yet been created");
    }

    public File newFile(String str) throws IOException {
        File file = new File(getRoot(), str);
        if (file.createNewFile()) {
            return file;
        }
        throw new IOException(GeneratedOutlineSupport1.outline75("a file with the name '", str, "' already exists in the test folder"));
    }

    public File newFolder(String str) throws IOException {
        return newFolder(str);
    }

    public TemporaryFolder(File file) {
        this.parentFolder = file;
    }

    public File newFolder(String... strArr) throws IOException {
        File root = getRoot();
        int i = 0;
        while (i < strArr.length) {
            String str = strArr[i];
            validateFolderName(str);
            File file = new File(root, str);
            if (!file.mkdir() && isLastElementInArray(i, strArr)) {
                throw new IOException(GeneratedOutlineSupport1.outline75("a folder with the name '", str, "' already exists"));
            }
            i++;
            root = file;
        }
        return root;
    }

    public File newFile() throws IOException {
        return File.createTempFile("junit", null, getRoot());
    }

    public File newFolder() throws IOException {
        return createTemporaryFolderIn(getRoot());
    }
}
