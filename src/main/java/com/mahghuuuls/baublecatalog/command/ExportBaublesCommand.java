package com.mahghuuuls.baublecatalog.command;

import com.mahghuuuls.baublecatalog.BaubleCatalogMod;
import com.mahghuuuls.baublecatalog.Tags;
import com.mahghuuuls.baublecatalog.export.BaubleExportService;
import com.mahghuuuls.baublecatalog.export.ExportResult;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

import java.io.File;

public class ExportBaublesCommand extends CommandBase {

    private final BaubleExportService exportService;

    public ExportBaublesCommand() {
        this(new BaubleExportService());
    }

    ExportBaublesCommand(BaubleExportService exportService) {
        this.exportService = exportService;
    }

    @Override
    public String getName() {
        return Tags.MOD_ID;
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/" + Tags.MOD_ID;
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length > 0) {
            throw new WrongUsageException(getUsage(sender));
        }

        ExportResult result = exportService.exportHeadersOnly(BaubleCatalogMod.getConfigDirectory());
        if (result.isSuccess()) {
            sender.sendMessage(new TextComponentString(successMessage(result)));
            return;
        }

        sender.sendMessage(new TextComponentString(failureMessage(result)));
    }

    private String successMessage(ExportResult result) {
        File outputFile = result.getOutputFile();
        return Tags.MOD_NAME + " export complete: wrote " + result.getRowCount()
                + " rows to " + outputFile.getAbsolutePath();
    }

    private String failureMessage(ExportResult result) {
        return Tags.MOD_NAME + " export failed: no valid CSV was produced. " + result.getMessage();
    }
}
