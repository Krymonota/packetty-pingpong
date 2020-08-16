package id.niklas.packetty.pingpong.client;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import id.niklas.packetty.client.network.IPackettyClient;
import id.niklas.packetty.client.network.PackettyClient;
import id.niklas.packetty.client.network.PackettyClientInitializer;
import id.niklas.packetty.common.packet.IPacketManager;
import id.niklas.packetty.common.packet.PacketManager;
import id.niklas.packetty.pingpong.common.packet.AbstractPPApp;
import id.niklas.packetty.pingpong.common.packet.IPPApp;
import id.niklas.packetty.pingpong.common.packet.PingPongPacket;
import io.netty.channel.ChannelHandler;

import java.net.InetSocketAddress;

public class PPClient extends AbstractPPApp<IPackettyClient> {

    private IPackettyClient networkManager;

    @Override
    public void start() {
        super.start();

        final InetSocketAddress serverAddress = new InetSocketAddress("127.0.0.1", 4000);
        final ObjectMapper objectMapper = new ObjectMapper();
        final IPacketManager packetManager = new PacketManager();
        final ChannelHandler channelHandler = new PPClientHandler(packetManager, this);
        final ChannelHandler channelInitializer = new PackettyClientInitializer(serverAddress, objectMapper, IPPApp.PACKET_PACKAGE, channelHandler);

        this.networkManager = new PackettyClient(packetManager, channelInitializer);

        this.networkManager.getPacketManager().registerPacketListener(PingPongPacket.class, new PingPongPacketListener(this));

        this.networkManager.start();
        this.networkManager.connect(serverAddress, success -> {
            if (!success) {
                this.stop();
            }
        });
    }

    @Override
    public IPackettyClient getNetworkManager() {
        return this.networkManager;
    }

}
