package com.mahghuuuls.baublecatalog.io;

import com.mahghuuuls.baublecatalog.Tags;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExportOutputPaths {

    private static final DateTimeFormatter FILE_TIMESTAMP_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");

    public File outputDirectory(File minecraftConfigDirectory) {
        return new File(minecraftConfigDirectory, Tags.MOD_ID);
    }

    public File timestampedCsvFile(File minecraftConfigDirectory, LocalDateTime timestamp) {
        String fileName = Tags.MOD_ID + "-baubles-" + FILE_TIMESTAMP_FORMAT.format(timestamp) + ".csv";
        return new File(outputDirectory(minecraftConfigDirectory), fileName);
    }
}
