package com.abe.arcaneextras.event;

import com.abe.arcaneextras.ArcaneExtras;
import com.abe.arcaneextras.enchantment.ModEnchantments;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingEquipmentChangeEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;


@EventBusSubscriber(modid = ArcaneExtras.MODID)
public class HeavyweightEvents {

  private static boolean hasHeavyweight(LivingEntity entity, ItemStack boots) {
    Holder<Enchantment> heavyweight = entity.level().registryAccess().holderOrThrow(ModEnchantments.HEAVYWEIGHT);
    return boots.getEnchantmentLevel(heavyweight) > 0;
  }

  @SubscribeEvent
  public static void onMobEffectApplicable(MobEffectEvent.Applicable event) {
    if (event.getEffectInstance().getEffect() == MobEffects.LEVITATION) {
      ItemStack boots = event.getEntity().getItemBySlot(EquipmentSlot.FEET);
      if (hasHeavyweight(event.getEntity(), boots)) {
        event.setResult(MobEffectEvent.Applicable.Result.DO_NOT_APPLY);
      }
    }
  }

  @SubscribeEvent
  public static void onFootChange(LivingEquipmentChangeEvent event) {
    if (event.getSlot() == EquipmentSlot.FEET) {
      ItemStack boots = event.getTo();
      if (hasHeavyweight(event.getEntity(), boots) && (event.getEntity().hasEffect(MobEffects.LEVITATION))) {
        event.getEntity().removeEffect(MobEffects.LEVITATION);
      }
    }
  }
}

/*
getEntity() retorna LivingEntity
getTo() retorna ItemStack
getSlot() retorna EquipmentSlot

boots.getEnchantmentLevel(arcane_extras)
→ retorna um int com o nível do encantamento naquele ItemStack
→ no nosso caso: 0 ou 1

event.getEntity().hasEffect(MobEffects.LEVITATION)
→ retorna boolean
→ true se a entidade tiver Levitação ativa
→ false se não tiver

entity.level()
→ pega o Level onde a entidade está

registryAccess()
→ dá acesso aos registries desse Level

"pegue o mundo onde essa entidade está e, a partir dele, acesse os registries disponíveis para esse contexto."

holderOrThrow(ModEnchantments.HEAVYWEIGHT)
→ resolve a ResourceKey do ArcaneExtras para um Holder<Enchantment>
→ se não encontrar a entrada no registry, lança uma exceção
*/