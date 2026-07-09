package com.mahghuuuls.baublecatalog.csv;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

public class CsvWriter {

    private static final String RECORD_SEPARATOR = "\r\n";

    public void write(File outputFile, Iterable<String> header, Iterable<? extends Iterable<String>> rows)
            throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(outputFile),
                StandardCharsets.UTF_8
        ));
        try {
            writeRecord(writer, header);
            for (Iterable<String> row : rows) {
                writeRecord(writer, row);
            }
        } finally {
            writer.close();
        }
    }

    private void writeRecord(Writer writer, Iterable<String> fields) throws IOException {
        boolean first = true;
        for (String field : fields) {
            if (!first) {
                writer.write(',');
            }
            writer.write(escapeField(field));
            first = false;
        }
        writer.write(RECORD_SEPARATOR);
    }

    static String escapeField(String field) {
        if (field == null) {
            return "";
        }

        boolean quoted = field.indexOf(',') >= 0
                || field.indexOf('"') >= 0
                || field.indexOf('\r') >= 0
                || field.indexOf('\n') >= 0;
        if (!quoted) {
            return field;
        }

        return "\"" + field.replace("\"", "\"\"") + "\"";
    }
}
