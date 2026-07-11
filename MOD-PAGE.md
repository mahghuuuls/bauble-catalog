# Bauble Catalog

Helps pack authors see which Baubles are available in a Bubbles-based modpack by generating a CSV file with information about the Baubles the modpack contains.

<p style="color: #d6a100;"><strong>AI usage disclaimer:</strong> This mod was developed with AI-agent assistance. I reviewed the work step by step during development and test releases in my personal Cleanroom modpack with 300+ mods.</p>

I tested the mod with Bubbles, not with original Baubles or BaublesEX, so it might not work with those.

## Features

The export includes Bubbles-recognized Bauble item-stack variants and writes them to `config/baublecatalog/` with timestamped filenames. Each row includes identification fields such as registry ID, metadata, display name, source mod ID, and Bauble type ID, plus planning fields such as source mod name, Bauble type name, rarity, tooltip, creative tab, max stack size, max durability, and ore dictionary names. When the command finishes, it reports the output path and exported row count in chat.

This mod is only a catalog/export tool. It does not add items, edit loot tables, edit trades, edit drops, or otherwise change gameplay content.

## Usage

Install the mod in the client with Bubbles, then run:

```text
/baublecatalog
```

The generated CSV is written under:

```text
config/baublecatalog/
```

Example filename:

```text
baublecatalog-baubles-YYYY-MM-DD-HH-mm-ss.csv
```

## Links

- GitHub: https://github.com/mahghuuuls/bauble-catalog
