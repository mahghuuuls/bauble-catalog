package com.mahghuuuls.baublecatalog.export;

import com.mahghuuuls.baublecatalog.BaubleCatalogMod;
import com.mahghuuuls.baublecatalog.bubbles.BaubleClassification;
import com.mahghuuuls.baublecatalog.bubbles.BubblesBaubleClassifier;
import com.mahghuuuls.baublecatalog.csv.CsvSchema;
import com.mahghuuuls.baublecatalog.csv.CsvWriter;
import com.mahghuuuls.baublecatalog.discover.DiscoveredVariant;
import com.mahghuuuls.baublecatalog.discover.ItemVariantDiscoverer;
import com.mahghuuuls.baublecatalog.io.ExportOutputPaths;
import com.mahghuuuls.baublecatalog.metadata.BaubleExportRow;
import com.mahghuuuls.baublecatalog.metadata.BaubleMetadataReader;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BaubleExportService {

    private final CsvWriter csvWriter;
    private final ExportOutputPaths outputPaths;
    private final ItemVariantDiscoverer itemVariantDiscoverer;
    private final BubblesBaubleClassifier baubleClassifier;
    private final BaubleMetadataReader metadataReader;

    public BaubleExportService() {
        this(
                new CsvWriter(),
                new ExportOutputPaths(),
                new ItemVariantDiscoverer(),
                new BubblesBaubleClassifier(),
                new BaubleMetadataReader()
        );
    }

    public BaubleExportService(CsvWriter csvWriter, ExportOutputPaths outputPaths) {
        this(
                csvWriter,
                outputPaths,
                new ItemVariantDiscoverer(),
                new BubblesBaubleClassifier(),
                new BaubleMetadataReader()
        );
    }

    public BaubleExportService(
            CsvWriter csvWriter,
            ExportOutputPaths outputPaths,
            ItemVariantDiscoverer itemVariantDiscoverer,
            BubblesBaubleClassifier baubleClassifier,
            BaubleMetadataReader metadataReader
    ) {
        this.csvWriter = csvWriter;
        this.outputPaths = outputPaths;
        this.itemVariantDiscoverer = itemVariantDiscoverer;
        this.baubleClassifier = baubleClassifier;
        this.metadataReader = metadataReader;
    }

    public ExportResult export(File minecraftConfigDirectory) {
        return export(minecraftConfigDirectory, LocalDateTime.now());
    }

    ExportResult export(File minecraftConfigDirectory, LocalDateTime timestamp) {
        if (minecraftConfigDirectory == null) {
            return ExportResult.failure(null, "Minecraft config directory is not available.", null);
        }

        File outputDirectory = outputPaths.outputDirectory(minecraftConfigDirectory);
        File outputFile = outputPaths.timestampedCsvFile(minecraftConfigDirectory, timestamp);

        try {
            ensureOutputDirectory(outputDirectory);
            ExportRows rows = buildRows();
            csvWriter.write(outputFile, CsvSchema.headers(), rows.getRows());
            return ExportResult.success(outputFile, rows.getRows().size(), rows.getProblemRowCount());
        } catch (IOException e) {
            BaubleCatalogMod.LOGGER.error("Failed to write Bauble Catalog export to {}", outputFile, e);
            return ExportResult.failure(outputFile, "Could not write " + outputFile.getAbsolutePath(), e);
        } catch (SecurityException e) {
            BaubleCatalogMod.LOGGER.error("Permission denied while writing Bauble Catalog export to {}", outputFile, e);
            return ExportResult.failure(outputFile, "Permission denied for " + outputFile.getAbsolutePath(), e);
        }
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

    private ExportRows buildRows() {
        List<Iterable<String>> rows = new ArrayList<Iterable<String>>();
        int problemRowCount = 0;
        List<DiscoveredVariant> variants = itemVariantDiscoverer.discover();
        for (DiscoveredVariant variant : variants) {
            try {
                BaubleClassification classification = baubleClassifier.classify(variant.getStack());
                if (classification == null) {
                    continue;
                }

                BaubleExportRow row = metadataReader.readRow(variant, classification);
                rows.add(row.toCsvFields());
            } catch (RuntimeException e) {
                BaubleCatalogMod.LOGGER.warn(
                        "Skipping bauble catalog variant after export-row failure: {}",
                        variant.describe(),
                        e
                );
                problemRowCount++;
            }
        }
        return new ExportRows(rows, problemRowCount);
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

    private static final class ExportRows {

        private final List<Iterable<String>> rows;
        private final int problemRowCount;

        private ExportRows(List<Iterable<String>> rows, int problemRowCount) {
            this.rows = rows;
            this.problemRowCount = problemRowCount;
        }

        private List<Iterable<String>> getRows() {
            return rows;
        }

        private int getProblemRowCount() {
            return problemRowCount;
        }
    }
}
