package id.niklas.packetty.pingpong.client;

public final class PPClientBootstrap {

    private PPClientBootstrap() {
    }

    public static void main(String[] args) {
        new PPClient().start();
    }

}
