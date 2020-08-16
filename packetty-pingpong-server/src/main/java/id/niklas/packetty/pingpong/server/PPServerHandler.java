package id.niklas.packetty.pingpong.server;

import id.niklas.packetty.common.packet.IPacketManager;
import id.niklas.packetty.pingpong.common.packet.PingPongPacket;
import id.niklas.packetty.server.network.PackettyServerHandler;
import io.netty.channel.ChannelHandlerContext;

public class PPServerHandler extends PackettyServerHandler {

    private final PPServer server;

    public PPServerHandler(IPacketManager packetManager, PPServer server) {
        super(packetManager);

        this.server = server;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        this.server.getNetworkManager().sendPacket(ctx.channel(), new PingPongPacket(System.currentTimeMillis()));
    }

}
