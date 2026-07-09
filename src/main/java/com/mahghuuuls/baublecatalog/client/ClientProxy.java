package com.mahghuuuls.baublecatalog.client;

import com.mahghuuuls.baublecatalog.CommonProxy;
import com.mahghuuuls.baublecatalog.command.ExportBaublesCommand;
import com.mahghuuuls.baublecatalog.csv.CsvWriter;
import com.mahghuuuls.baublecatalog.bubbles.BubblesBaubleClassifier;
import com.mahghuuuls.baublecatalog.discover.ItemVariantDiscoverer;
import com.mahghuuuls.baublecatalog.export.BaubleExportService;
import com.mahghuuuls.baublecatalog.io.ExportOutputPaths;
import com.mahghuuuls.baublecatalog.metadata.BaubleMetadataReader;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public class ClientProxy extends CommonProxy {

    @Override
    public void init(FMLInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(new ExportBaublesCommand(clientExportService()));
    }

    private BaubleExportService clientExportService() {
        return new BaubleExportService(
                new CsvWriter(),
                new ExportOutputPaths(),
                new ItemVariantDiscoverer(),
                new BubblesBaubleClassifier(),
                new BaubleMetadataReader(new ClientTooltipProvider())
        );
    }
}
