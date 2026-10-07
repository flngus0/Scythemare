package flingus.scythemare.index;

import flingus.scythemare.advancement.SoulReapCriterion;
import net.minecraft.advancement.criterion.Criteria;

public class ModCriteria {
    public static final SoulReapCriterion SOUL_REAP = Criteria.register("soul_reap", new SoulReapCriterion());

    public static void init() {}
}