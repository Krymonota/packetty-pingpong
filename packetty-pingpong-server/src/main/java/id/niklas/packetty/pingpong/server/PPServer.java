package id.niklas.packetty.pingpong.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.niklas.packetty.common.packet.IPacketManager;
import id.niklas.packetty.common.packet.PacketManager;
import id.niklas.packetty.pingpong.common.packet.AbstractPPApp;
import id.niklas.packetty.pingpong.common.packet.IPPApp;
import id.niklas.packetty.pingpong.common.packet.PingPongPacket;
import id.niklas.packetty.server.network.IPackettyServer;
import id.niklas.packetty.server.network.PackettyServer;
import id.niklas.packetty.server.network.PackettyServerInitializer;
import io.netty.channel.ChannelHandler;

import java.net.InetSocketAddress;

public class PPServer extends AbstractPPApp<IPackettyServer> {

    private IPackettyServer networkManager;

    @Override
    public void start() {
        super.start();

        final InetSocketAddress serverAddress = new InetSocketAddress("127.0.0.1", 4000);
        final ObjectMapper objectMapper = new ObjectMapper();
        final IPacketManager packetManager = new PacketManager();
        final ChannelHandler channelHandler = new PPServerHandler(packetManager, this);
        final ChannelHandler channelInitializer = new PackettyServerInitializer(objectMapper, IPPApp.PACKET_PACKAGE, channelHandler);

        this.networkManager = new PackettyServer(packetManager, channelInitializer);

        this.networkManager.getPacketManager().registerPacketListener(PingPongPacket.class, new PingPongPacketListener(this));

        this.networkManager.start();
        this.networkManager.bind(serverAddress);
    }

    @Override
    public IPackettyServer getNetworkManager() {
        return this.networkManager;
    }

}
