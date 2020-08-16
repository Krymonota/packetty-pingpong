package id.niklas.packetty.pingpong.client;

import id.niklas.packetty.common.packet.IPacketListener;
import id.niklas.packetty.pingpong.common.packet.PingPongPacket;
import io.netty.channel.Channel;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class PingPongPacketListener implements IPacketListener<PingPongPacket> {

    private final PPClient client;

    public PingPongPacketListener(PPClient client) {
        this.client = client;
    }

    @Override
    public void handle(Channel channel, PingPongPacket pingPongPacket) {
        System.out.println(pingPongPacket.getTime());

        new Timer(true).schedule(new TimerTask() {

            @Override
            public void run() {
                if (channel.isActive()) {
                    PingPongPacketListener.this.client.getNetworkManager().sendPacket(new PingPongPacket(System.currentTimeMillis()));
                }
            }

        }, TimeUnit.SECONDS.toMillis(3));
    }

}
