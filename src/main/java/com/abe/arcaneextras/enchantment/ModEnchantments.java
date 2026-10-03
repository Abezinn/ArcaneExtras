package com.abe.arcaneextras.enchantment;

import com.abe.arcaneextras.ArcaneExtras;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;

public class ModEnchantments { // Criamos uma classe para centralizar as referências aos encantamentos do mod

  public static final ResourceKey<Enchantment> HEAVYWEIGHT =
      // Criamos uma constante pública chamada HEAVYWEIGHT.
      // Ela guarda uma ResourceKey que aponta para um Enchantment.

      ResourceKey.create(
          // Chave que representa uma entrada dentro de um registry
          Registries.ENCHANTMENT,
          // Define que essa chave pertence ao registry de encantamentos
          ResourceLocation.fromNamespaceAndPath(ArcaneExtras.MODID, "heavyweight")
          // Cria o identificador "arcane_extras:heavyweight"
          // ArcaneExtras.MODID fornece o namespace "arcane_extras"
          // "heavyweight" é o path da ResourceLocation
      );
}