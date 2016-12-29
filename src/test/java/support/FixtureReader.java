package support;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class FixtureReader {
    public static String readFixture(String fileWhichExistsInResources) {
        // ../ exists because FixtureReader lives in support package
        URL url = FixtureReader.class.getResource("../" + fileWhichExistsInResources);

        try {
            if (url == null) {
                throw new FileNotFoundException(fileWhichExistsInResources);
            }

            Path path = Paths.get(url.toURI());

            return Files.readAllLines(path).stream()
                .collect(Collectors.joining("\n"));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }
}
