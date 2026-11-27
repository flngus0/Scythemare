package flingus.scythemare.advancement;

import com.mojang.serialization.Codec;
import net.minecraft.advancements.criterion.ContextAwarePredicate;
import net.minecraft.advancements.criterion.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;

import java.util.Optional;

public class SoulReapCriterion extends SimpleCriterionTrigger<SoulReapCriterion.Conditions> {
    public void trigger(ServerPlayer player) {
        this.trigger(player, conditions -> true);
    }

    public Codec<SoulReapCriterion.Conditions> codec() {
        return SoulReapCriterion.Conditions.CODEC;
    }

    public record Conditions(Optional<ContextAwarePredicate> playerPredicate) implements SimpleCriterionTrigger.SimpleInstance {
        public static Codec<SoulReapCriterion.Conditions> CODEC = ContextAwarePredicate.CODEC.optionalFieldOf("player")
                .xmap(Conditions::new, Conditions::player).codec();

        @Override
        public Optional<ContextAwarePredicate> player() {
            return playerPredicate;
        }
    }
}