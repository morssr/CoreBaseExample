package sr.mls.com.corebase.Utils;

import android.content.Context;
import android.net.Uri;

import androidx.core.content.FileProvider;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static boolean isFileExist(String filePath) {
        return new File(filePath).exists();
    }

    public static Uri getUriForFile(Context context, String authority, File file)
            throws IllegalArgumentException {
        return FileProvider.getUriForFile(
                context,
                authority,
                file
        );
    }

    public static List<Uri> getUriForFile(Context context, String authority, ArrayList<File> files)
            throws IllegalArgumentException {
        ArrayList<Uri> uris = new ArrayList<>();
        for (File file : files) {
            Uri uri = FileProvider.getUriForFile(
                    context,
                    authority,
                    file);

            uris.add(uri);
        }
        return uris;
    }
}