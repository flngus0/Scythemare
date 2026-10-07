package flingus.scythemare.item;

import flingus.scythemare.index.ModEnchantments;
import flingus.scythemare.index.ModRegistryKeys;
import flingus.scythemare.index.ModTags;
import flingus.scythemare.util.ScythemareUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.LazyRegistryEntryReference;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Property;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ScytheItem extends Item {
    public ScytheItem(Settings settings) {
        super(settings.component(DataComponentTypes.DAMAGE_TYPE, new LazyRegistryEntryReference<>(ModRegistryKeys.SLICE)));
    }

    @Override
    public boolean canMine(ItemStack stack, BlockState state, World world, BlockPos pos, LivingEntity user) {
        if (ScythemareUtils.hasEnchantment(ModEnchantments.HARVEST_TOUCH, world, stack)) {
            if (state.isIn(ModTags.HARVEST_TOUCH_REPLACEABLE)) {
                for (Property<?> property : state.getProperties()) {
                    if (property instanceof IntProperty integerProperty) {
                        int currentAge = state.get(integerProperty);
                        int maxAge = integerProperty.getValues().stream().mapToInt(Integer::intValue).max().orElse(currentAge);

                        if (user.isSneaking()) {
                            return !user.isInCreativeMode();
                        }

                        return currentAge == maxAge;
                    }
                }
            }

            if (state.getBlock() instanceof CropBlock cropBlock) {
                if (user.isSneaking()) {
                    return !user.isInCreativeMode();
                }
                else {
                    return cropBlock.isMature(state);
                }
            }
        }

        return !user.isInCreativeMode();
    }


    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (!world.isClient() && ScythemareUtils.hasEnchantment(ModEnchantments.HARVEST_TOUCH, world, stack)) {
            if (state.isIn(ModTags.HARVEST_TOUCH_REPLACEABLE)) {
                for (Property<?> property : state.getProperties()) {
                    if (property instanceof IntProperty integerProperty) {
                        int currentAge = state.get(integerProperty);
                        int maxAge = integerProperty.getValues().stream().mapToInt(Integer::intValue).max().orElse(currentAge);

                        if (currentAge == maxAge) {
                            Block.dropStacks(state, world, pos, null, miner, stack);

                            world.setBlockState(pos, state.with(integerProperty, integerProperty.getValues().stream().mapToInt(Integer::intValue).min().orElse(0)), Block.NOTIFY_ALL);

                            world.playSound(null, pos, state.getSoundGroup().getPlaceSound(), SoundCategory.BLOCKS);

                            stack.damage(1, miner, EquipmentSlot.MAINHAND);

                            return false;
                        }
                    }
                }
            }

            if (state.getBlock() instanceof CropBlock cropBlock) {
                if (cropBlock.isMature(state)) {
                    Block.dropStacks(state, world, pos, null, miner, stack);

                    world.setBlockState(pos, cropBlock.withAge(0), Block.NOTIFY_ALL);

                    world.playSound(null, pos, cropBlock.getDefaultState().getSoundGroup().getPlaceSound(), SoundCategory.BLOCKS);

                    stack.damage(1, miner, EquipmentSlot.MAINHAND);

                    return false;
                }
            }
        }
        return super.postMine(stack, world, state, pos, miner);
    }

    @Override
    public Text getName(ItemStack stack) {
        return super.getName(stack);
    }
}