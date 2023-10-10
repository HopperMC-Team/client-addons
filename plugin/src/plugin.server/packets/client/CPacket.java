package plugin.server.packets.client;

import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import plugin.server.packets.ECPacket;

public abstract class CPacket extends ECPacket {

	@Override
	public void writePacketData(PacketDataSerializer data) {
		// Sent from client
	}
}
