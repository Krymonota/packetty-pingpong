package id.niklas.packetty.pingpong.common.packet;

import id.niklas.packetty.common.network.INetworkManager;

public abstract class AbstractPPApp<NM extends INetworkManager> implements IPPApp<NM> {

    @Override
    public void start() {
        Runtime.getRuntime().addShutdownHook(new Thread(AbstractPPApp.this::stop));
    }

    @Override
    public void stop() {
        if (this.getNetworkManager() != null) {
            this.getNetworkManager().stop();
        }
    }

}
