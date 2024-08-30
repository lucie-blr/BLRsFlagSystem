package net.blr.blrsflagsystem.flag;

import net.minecraft.nbt.CompoundTag;

public class PlayerFlag {

    private String flagName;

    public String getFlagName() {
        return flagName;
    }

    public void setFlagName(String flagName) {
        this.flagName = flagName;
    }

    public void removePlayerFlag() {
        this.flagName = null;
    }

    public void copyFrom(PlayerFlag source) {
        this.flagName = source.flagName;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putString("flagName", flagName);
    }

    public void LoadNBTData(CompoundTag nbt) {
        this.flagName = nbt.getString("flagName");
    }


}
