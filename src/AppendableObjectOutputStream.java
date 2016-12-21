import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;

public class AppendableObjectOutputStream extends ObjectOutputStream {

    public AppendableObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }
    private boolean append = false;
    public AppendableObjectOutputStream(String fileName, boolean appendIfExists) throws IOException {
        super(new FileOutputStream(fileName, appendIfExists && Files.exists(Paths.get(fileName), LinkOption.NOFOLLOW_LINKS)));
        append = appendIfExists && Files.exists(Paths.get(fileName), LinkOption.NOFOLLOW_LINKS);
    }

    @Override
    protected void writeStreamHeader() throws IOException {
        if (append) {
            reset();
        } else {
            super.writeStreamHeader();
        }
    }
}