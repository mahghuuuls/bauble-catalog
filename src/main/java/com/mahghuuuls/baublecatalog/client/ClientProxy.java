package com.mahghuuuls.baublecatalog.client;

import com.mahghuuuls.baublecatalog.CommonProxy;
import com.mahghuuuls.baublecatalog.command.ExportBaublesCommand;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public class ClientProxy extends CommonProxy {

    @Override
    public void init(FMLInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(new ExportBaublesCommand());
    }
}
