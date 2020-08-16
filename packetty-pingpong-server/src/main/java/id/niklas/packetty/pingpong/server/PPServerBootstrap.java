package id.niklas.packetty.pingpong.server;

public final class PPServerBootstrap {

    private PPServerBootstrap() {
    }

    public static void main(String[] args) {
        new PPServer().start();
    }

}
