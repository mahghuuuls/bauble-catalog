package com.mahghuuuls.baublecatalog.export;

import java.io.File;

public final class ExportResult {

    private final boolean success;
    private final File outputFile;
    private final int rowCount;
    private final int problemRowCount;
    private final String message;
    private final Throwable error;

    private ExportResult(
            boolean success,
            File outputFile,
            int rowCount,
            int problemRowCount,
            String message,
            Throwable error
    ) {
        this.success = success;
        this.outputFile = outputFile;
        this.rowCount = rowCount;
        this.problemRowCount = problemRowCount;
        this.message = message;
        this.error = error;
    }

    public static ExportResult success(File outputFile, int rowCount) {
        return success(outputFile, rowCount, 0);
    }

    public static ExportResult success(File outputFile, int rowCount, int problemRowCount) {
        return new ExportResult(true, outputFile, rowCount, problemRowCount, "Export completed.", null);
    }

    public static ExportResult failure(File outputFile, String message, Throwable error) {
        return new ExportResult(false, outputFile, 0, 0, message, error);
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

    public int getProblemRowCount() {
        return problemRowCount;
    }

    public String getMessage() {
        return message;
    }

    public Throwable getError() {
        return error;
    }
}
