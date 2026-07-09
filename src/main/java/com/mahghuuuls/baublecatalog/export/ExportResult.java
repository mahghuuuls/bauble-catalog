package com.mahghuuuls.baublecatalog.export;

import java.io.File;

public final class ExportResult {

    private final boolean success;
    private final File outputFile;
    private final int rowCount;
    private final String message;
    private final Throwable error;

    private ExportResult(boolean success, File outputFile, int rowCount, String message, Throwable error) {
        this.success = success;
        this.outputFile = outputFile;
        this.rowCount = rowCount;
        this.message = message;
        this.error = error;
    }

    public static ExportResult success(File outputFile, int rowCount) {
        return new ExportResult(true, outputFile, rowCount, "Export completed.", null);
    }

    public static ExportResult failure(File outputFile, String message, Throwable error) {
        return new ExportResult(false, outputFile, 0, message, error);
    }

    public boolean isSuccess() {
        return success;
    }

    public File getOutputFile() {
        return outputFile;
    }

    public int getRowCount() {
        return rowCount;
    }

    public String getMessage() {
        return message;
    }

    public Throwable getError() {
        return error;
    }
}
