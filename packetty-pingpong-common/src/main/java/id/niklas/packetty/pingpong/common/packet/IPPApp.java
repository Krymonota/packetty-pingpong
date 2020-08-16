package id.niklas.packetty.pingpong.common.packet;

import id.niklas.packetty.common.network.INetworkManager;

public interface IPPApp<NM extends INetworkManager> {

    String PACKET_PACKAGE = "id.niklas.packetty.pingpong.common.packet";

    void start();

    void stop();

    NM getNetworkManager();

}
