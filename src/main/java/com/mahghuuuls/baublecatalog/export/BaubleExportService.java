package com.mahghuuuls.baublecatalog.export;

import com.mahghuuuls.baublecatalog.BaubleCatalogMod;
import com.mahghuuuls.baublecatalog.csv.CsvSchema;
import com.mahghuuuls.baublecatalog.csv.CsvWriter;
import com.mahghuuuls.baublecatalog.io.ExportOutputPaths;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;

public class BaubleExportService {

    private final CsvWriter csvWriter;
    private final ExportOutputPaths outputPaths;

    public BaubleExportService() {
        this(new CsvWriter(), new ExportOutputPaths());
    }

    public BaubleExportService(CsvWriter csvWriter, ExportOutputPaths outputPaths) {
        this.csvWriter = csvWriter;
        this.outputPaths = outputPaths;
    }

    public ExportResult exportHeadersOnly(File minecraftConfigDirectory) {
        return exportHeadersOnly(minecraftConfigDirectory, LocalDateTime.now());
    }

    ExportResult exportHeadersOnly(File minecraftConfigDirectory, LocalDateTime timestamp) {
        if (minecraftConfigDirectory == null) {
            return ExportResult.failure(null, "Minecraft config directory is not available.", null);
        }

        File outputDirectory = outputPaths.outputDirectory(minecraftConfigDirectory);
        File outputFile = outputPaths.timestampedCsvFile(minecraftConfigDirectory, timestamp);

        try {
            ensureOutputDirectory(outputDirectory);
            csvWriter.write(outputFile, CsvSchema.headers(), Collections.<Iterable<String>>emptyList());
            return ExportResult.success(outputFile, 0);
        } catch (IOException e) {
            BaubleCatalogMod.LOGGER.error("Failed to write Bauble Catalog export to {}", outputFile, e);
            return ExportResult.failure(outputFile, "Could not write " + outputFile.getAbsolutePath(), e);
        } catch (SecurityException e) {
            BaubleCatalogMod.LOGGER.error("Permission denied while writing Bauble Catalog export to {}", outputFile, e);
            return ExportResult.failure(outputFile, "Permission denied for " + outputFile.getAbsolutePath(), e);
        }
    }

    private void ensureOutputDirectory(File outputDirectory) throws IOException {
        if (outputDirectory.exists()) {
            if (!outputDirectory.isDirectory()) {
                throw new IOException(outputDirectory.getAbsolutePath() + " exists but is not a directory.");
            }
            return;
        }

        if (!outputDirectory.mkdirs() && !outputDirectory.isDirectory()) {
            throw new IOException("Could not create output directory " + outputDirectory.getAbsolutePath());
        }
    }
}
