package tk.sciwhiz12.snowyweaponry.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.checkerframework.checker.nullness.qual.Nullable;
import tk.sciwhiz12.snowyweaponry.Reference;
import tk.sciwhiz12.snowyweaponry.SnowyWeaponry;

import java.util.concurrent.CompletableFuture;

public class ItemTags extends ItemTagsProvider {
    public ItemTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, BlockTagsProvider blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, SnowyWeaponry.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider lookupProvider) {
        tag(Tags.Items.NUGGETS)
                .addTag(Reference.Tags.NUGGETS_DIAMOND)
                .addTag(Reference.Tags.NUGGETS_NETHERITE);

        tag(Reference.Tags.NUGGETS_DIAMOND)
                .add(Reference.Items.DIAMOND_CHUNK.get());
        tag(Reference.Tags.NUGGETS_NETHERITE)
                .add(Reference.Items.NETHERITE_NUGGET.get());
    }

    public String getName() {
        return "Item Tags";
    }
}
