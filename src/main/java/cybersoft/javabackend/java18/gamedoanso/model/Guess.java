package cybersoft.javabackend.java18.gamedoanso.model;

public class Guess {
    private final int soDoan;
    private final String gameSession;
    private String ketQua;

    public Guess(int soDoan, String gameSession) {
        this.soDoan = soDoan;
        this.gameSession = gameSession;
    }

    public int getSoDoan() {
        return this.soDoan;
    }

    public String getKetQua() {
        return this.ketQua;
    }

    public void setKetQua(String ketQua) {
        this.ketQua = ketQua;
    }

    public String getGameSession() {
        return this.gameSession;
    }
}
