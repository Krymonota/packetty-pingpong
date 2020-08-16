package id.niklas.packetty.pingpong.client;

import id.niklas.packetty.client.network.PackettyClientHandler;
import id.niklas.packetty.common.packet.IPacketManager;
import io.netty.channel.ChannelHandlerContext;

public class PPClientHandler extends PackettyClientHandler {

    private final PPClient client;

    public PPClientHandler(IPacketManager packetManager, PPClient client) {
        super(packetManager);

        this.client = client;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        this.client.stop();
    }

}
