package flingus.scythemare.advancement;

import com.mojang.serialization.Codec;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.Optional;

public class SoulReapCriterion extends AbstractCriterion<SoulReapCriterion.Conditions> {
    public void trigger(ServerPlayerEntity player) {
        this.trigger(player, conditions -> true);
    }

    public Codec<SoulReapCriterion.Conditions> getConditionsCodec() {
        return SoulReapCriterion.Conditions.CODEC;
    }

    public record Conditions(Optional<LootContextPredicate> playerPredicate) implements AbstractCriterion.Conditions {
        public static Codec<SoulReapCriterion.Conditions> CODEC = LootContextPredicate.CODEC.optionalFieldOf("player")
                .xmap(Conditions::new, Conditions::player).codec();

        @Override
        public Optional<LootContextPredicate> player() {
            return playerPredicate;
        }
    }
}