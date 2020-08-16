package id.niklas.packetty.pingpong.server;

import id.niklas.packetty.common.packet.IPacketListener;
import id.niklas.packetty.pingpong.common.packet.PingPongPacket;
import io.netty.channel.Channel;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class PingPongPacketListener implements IPacketListener<PingPongPacket> {

    private final PPServer server;

    public PingPongPacketListener(PPServer server) {
        this.server = server;
    }

    @Override
    public void handle(Channel channel, PingPongPacket pingPongPacket) {
        System.out.println(pingPongPacket.getTime());

        new Timer(true).schedule(new TimerTask() {

            @Override
            public void run() {
                if (channel.isActive()) {
                    PingPongPacketListener.this.server.getNetworkManager().sendPacket(channel, new PingPongPacket(System.currentTimeMillis()));
                }
            }

        }, TimeUnit.SECONDS.toMillis(3));
    }

}
