package id.niklas.packetty.pingpong.common.packet;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import id.niklas.packetty.common.packet.IPacket;
import id.niklas.packetty.common.packet.PacketDirection;

public class PingPongPacket implements IPacket {

    private final long time;

    @JsonCreator
    public PingPongPacket(@JsonProperty("time") long time) {
        this.time = time;
    }

    public long getTime() {
        return this.time;
    }

    @Override
    public PacketDirection getPacketDirection() {
        return PacketDirection.BOTH;
    }

}
