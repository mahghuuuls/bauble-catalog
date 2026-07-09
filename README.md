# Bauble Catalog

Bauble Catalog is a Minecraft 1.12.2 Forge/Cleanroom utility mod that exports Bubbles-recognized Bauble item data to timestamped CSV files for pack authors and maintainers.

Run `/baublecatalog` from the client to generate a CSV under:

```text
config/baublecatalog/
```

Generated files use timestamped names such as:

```text
baublecatalog-baubles-YYYY-MM-DD-HH-mm-ss.csv
```

## Scope

Bauble Catalog is an export utility only. It does not add Baubles, edit loot tables, edit trades, edit boss drops, or change gameplay content.

The first version targets Bubbles only. Original Baubles and BaublesEX are not supported compatibility targets.

The export includes required identification columns and planning metadata such as display name, source mod, Bauble type, rarity, tooltip, creative tab, max stack size, max durability, and ore dictionary names.

## Public Copy

- Player/download-page copy: [MOD-PAGE.md](MOD-PAGE.md)
- Changelog: [CHANGELOG.md](CHANGELOG.md)
- Third-party notices: [THIRD-PARTY-NOTICES.md](THIRD-PARTY-NOTICES.md)

## Development

This repository is initialized from CleanroomMC ForgeDevEnv. The mod targets Java 8 bytecode and keeps modern Java syntax disabled.

- Use the Gradle wrapper supplied by the repository.
- Recommended development JDK: Java 25 when supported by the toolchain.
- Release target: Java 8.

## License

Bauble Catalog is licensed under the MIT License. See [LICENSE](LICENSE).
