package flingus.scythemare.item;

import flingus.scythemare.index.ModEnchantments;
import flingus.scythemare.index.ModRegistryKeys;
import flingus.scythemare.index.ModTags;
import flingus.scythemare.util.ScythemareUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.EitherHolder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;

public class ScytheItem extends Item {
    public ScytheItem(Properties settings) {
        super(settings.component(DataComponents.DAMAGE_TYPE, new EitherHolder<>(ModRegistryKeys.SLICE)));
    }

    @Override
    public boolean canDestroyBlock(ItemStack stack, BlockState state, Level world, BlockPos pos, LivingEntity user) {
        if (ScythemareUtils.hasEnchantment(ModEnchantments.HARVEST_TOUCH, world, stack)) {
            if (state.is(ModTags.HARVEST_TOUCH_REPLACEABLE)) {
                for (Property<?> property : state.getProperties()) {
                    if (property instanceof IntegerProperty integerProperty) {
                        int currentAge = state.getValue(integerProperty);
                        int maxAge = integerProperty.getPossibleValues().stream().mapToInt(Integer::intValue).max().orElse(currentAge);

                        if (user.isShiftKeyDown()) {
                            return !user.hasInfiniteMaterials();
                        }

                        return currentAge == maxAge;
                    }
                }
            }

            if (state.getBlock() instanceof CropBlock cropBlock) {
                if (user.isShiftKeyDown()) {
                    return !user.hasInfiniteMaterials();
                }
                else {
                    return cropBlock.isMaxAge(state);
                }
            }

            if (state.getBlock() instanceof SugarCaneBlock) {
                if (!world.getBlockState(pos.below()).is(Blocks.SUGAR_CANE)) {
                    return user.isShiftKeyDown() && !user.hasInfiniteMaterials();
                }
                return !user.hasInfiniteMaterials();
            }
        }

        return !user.hasInfiniteMaterials();
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (!world.isClientSide() && ScythemareUtils.hasEnchantment(ModEnchantments.HARVEST_TOUCH, world, stack)) {
            if (state.is(ModTags.HARVEST_TOUCH_REPLACEABLE)) {
                if (state.getBlock() instanceof SweetBerryBushBlock berryBushBlock) {
                    if(state.getValue(SweetBerryBushBlock.AGE) == SweetBerryBushBlock.MAX_AGE) {
                        Block.popResource(world, pos, new ItemStack(Items.SWEET_BERRIES, 2 + world.random.nextInt(2)));

                        world.setBlock(pos, state.setValue(SweetBerryBushBlock.AGE, 1), Block.UPDATE_ALL);

                        world.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS);

                        stack.hurtAndBreak(1, miner, EquipmentSlot.MAINHAND);
                    }
                    return false;
                }
                for (Property<?> property : state.getProperties()) {
                    if (property instanceof IntegerProperty integerProperty) {
                        int currentAge = state.getValue(integerProperty);
                        int maxAge = integerProperty.getPossibleValues().stream().mapToInt(Integer::intValue).max().orElse(currentAge);

                        if (currentAge == maxAge) {
                            Block.dropResources(state, world, pos, null, miner, stack);

                            world.setBlock(pos, state.setValue(integerProperty, integerProperty.getPossibleValues().stream().mapToInt(Integer::intValue).min().orElse(0)), Block.UPDATE_ALL);

                            world.playSound(null, pos, state.getSoundType().getPlaceSound(), SoundSource.BLOCKS);

                            stack.hurtAndBreak(1, miner, EquipmentSlot.MAINHAND);

                            return false;
                        }
                    }
                }
            }

            if (state.getBlock() instanceof CropBlock cropBlock) {
                if (cropBlock.isMaxAge(state)) {
                    Block.dropResources(state, world, pos, null, miner, stack);

                    world.setBlock(pos, cropBlock.getStateForAge(0), Block.UPDATE_ALL);

                    world.playSound(null, pos, cropBlock.defaultBlockState().getSoundType().getPlaceSound(), SoundSource.BLOCKS);

                    stack.hurtAndBreak(1, miner, EquipmentSlot.MAINHAND);

                    return false;
                }
            }
        }
        return super.mineBlock(stack, world, state, pos, miner);
    }

    @Override
    public Component getName(ItemStack stack) {
        return super.getName(stack);
    }
}