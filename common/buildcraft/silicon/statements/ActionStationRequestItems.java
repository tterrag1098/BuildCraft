/**
 * Copyright (c) 2011-2014, SpaceToad and the BuildCraft Team
 * http://www.mod-buildcraft.com
 *
 * BuildCraft is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://www.mod-buildcraft.com/MMPL-1.0.txt
 */
package buildcraft.silicon.statements;

import net.minecraft.client.renderer.texture.IIconRegister;

import buildcraft.api.core.IInvSlot;
import buildcraft.api.gates.ActionParameterItemStack;
import buildcraft.api.gates.IActionParameter;
import buildcraft.core.inventory.filters.StatementParameterStackFilter;
import buildcraft.core.robots.DockingStation;
import buildcraft.core.robots.EntityRobot;
import buildcraft.core.triggers.BCActionPassive;
import buildcraft.core.utils.StringUtils;
import buildcraft.transport.gates.ActionSlot;

public abstract class ActionStationRequestItems extends BCActionPassive {

	public ActionStationRequestItems(String name) {
		super(name);
	}

	@Override
	public String getDescription() {
		return StringUtils.localize("gate.action.station.request_items");
	}

	@Override
	public void registerIcons(IIconRegister iconRegister) {
		icon = iconRegister.registerIcon("buildcraft:triggers/action_station_request_items");
	}

	@Override
	public int maxParameters() {
		return 3;
	}

	@Override
	public IActionParameter createParameter(int index) {
		return new ActionParameterItemStack();
	}

	public boolean insert(DockingStation station, EntityRobot robot, ActionSlot actionSlot, IInvSlot invSlot,
			boolean doInsert) {
		StatementParameterStackFilter param = new StatementParameterStackFilter(actionSlot.parameters);

		return !param.hasFilter() || param.matches(invSlot.getStackInSlot());
	}
}
