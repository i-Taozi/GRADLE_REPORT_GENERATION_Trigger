/*
 *  Prison is a Minecraft plugin for the prison game mode.
 *  Copyright (C) 2017-2020 The Prison Team
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package tech.mcprison.prison.internal.events.block;

import tech.mcprison.prison.internal.Player;
import tech.mcprison.prison.internal.events.Cancelable;
import tech.mcprison.prison.util.BlockType;
import tech.mcprison.prison.util.Location;

/**
 * Platform-independent event, which is posted when a player breaks a block.
 *
 * @author DMP9
 * @since API 1.0
 */
public class BlockBreakEvent implements Cancelable {

    private BlockType block;
    private Location blockLocation;
    private Player player;
    private boolean canceled = false;
    private int exp;

    public BlockBreakEvent(BlockType block, Location blockLocation, Player player) {
        this(block,blockLocation,player,0);
    }

    public BlockBreakEvent(BlockType block, Location blockLocation, Player player,int xp) {
        this.block = block;
        this.blockLocation = blockLocation;
        this.player = player;
        this.exp = xp;
    }

    @Override public boolean isCanceled() {
        return canceled;
    }

    @Override public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public BlockType getBlock() {
        return block;
    }

    public Location getBlockLocation() {
        return blockLocation;
    }

    public Player getPlayer() {
        return player;
    }

    public int getExpToDrop() {return exp;}

}
