package com.drmangotea.createindustry.registry;

import com.drmangotea.createindustry.CreateTFMG;
import com.drmangotea.createindustry.base.palettes.TFMGPaletteBlockPattern;
import com.drmangotea.createindustry.base.palettes.TFMGPalettesVariantEntry;
import com.simibubi.create.AllTags;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.utility.Lang;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Function;

import static com.drmangotea.createindustry.base.palettes.TFMGPaletteBlockPattern.STANDARD_RANGE;

public enum TFMGPaletteStoneTypes {


    BAUXITE(STANDARD_RANGE, r -> r.paletteStoneBlock("bauxite", () -> Blocks.DEEPSLATE, true, true)
            .properties(p -> p.destroyTime(1.25f)
                    .mapColor(MapColor.COLOR_BLUE))
            .register()),

    ;

    private Function<CreateRegistrate, NonNullSupplier<Block>> factory;
    private TFMGPalettesVariantEntry variants;

    public NonNullSupplier<Block> baseBlock;
    public TFMGPaletteBlockPattern[] variantTypes;
    public TagKey<Item> materialTag;

    private TFMGPaletteStoneTypes(TFMGPaletteBlockPattern[] variantTypes,
                                 Function<CreateRegistrate, NonNullSupplier<Block>> factory) {
        this.factory = factory;
        this.variantTypes = variantTypes;
    }

    public NonNullSupplier<Block> getBaseBlock() {
        return baseBlock;
    }

    public TFMGPalettesVariantEntry getVariants() {
        return variants;
    }

    public static void register(CreateRegistrate registrate) {
        for (TFMGPaletteStoneTypes paletteStoneVariants : values()) {
            NonNullSupplier<Block> baseBlock = paletteStoneVariants.factory.apply(registrate);
            paletteStoneVariants.baseBlock = baseBlock;
            String id = Lang.asId(paletteStoneVariants.name());
            paletteStoneVariants.materialTag =
                    AllTags.optionalTag(ForgeRegistries.ITEMS, CreateTFMG.asResource("stone_types/" + id));
            paletteStoneVariants.variants = new TFMGPalettesVariantEntry(id, paletteStoneVariants);
        }
    }

}
