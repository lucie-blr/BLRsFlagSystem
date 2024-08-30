package net.blr.blrsflagsystem.flag;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.level.NoteBlockEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerFlagProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<PlayerFlag> PLAYER_FLAG = CapabilityManager.get(new CapabilityToken<PlayerFlag>() {});

    private PlayerFlag flag = null;
    private final LazyOptional<PlayerFlag> optional = LazyOptional.of(this::createPlayerFlag);

    private PlayerFlag createPlayerFlag() {
        if (this.flag == null) {
            this.flag = new PlayerFlag();
        }

        return this.flag;
    }


    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == PLAYER_FLAG) {
            return optional.cast();
        }

        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerFlag().saveNBTData(nbt);

        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerFlag().LoadNBTData(nbt);



    }
}
