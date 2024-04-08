package net.illuminatijoe.esotericcircuitry.block;

import net.illuminatijoe.esotericcircuitry.EsotericCircuitry;
import net.illuminatijoe.esotericcircuitry.block.entity.ArcaneConduitEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, EsotericCircuitry.MOD_ID);

    public static final RegistryObject<BlockEntityType<ArcaneConduitEntity>> ARCANE_CONDUIT_ENTITY =
            BLOCK_ENTITIES.register("arcane_conduit_entity",
                    () -> BlockEntityType.Builder
                            .of(ArcaneConduitEntity::new, ModBlocks.ARCANE_CONDUIT.get())
                            .build(null)
            );

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
