package flingus.scythemare.index;

import flingus.scythemare.advancement.SoulReapCriterion;
import net.minecraft.advancements.CriteriaTriggers;

public class ModCriteria {
    public static final SoulReapCriterion SOUL_REAP = CriteriaTriggers.register("soul_reap", new SoulReapCriterion());

    public static void init() {}
}