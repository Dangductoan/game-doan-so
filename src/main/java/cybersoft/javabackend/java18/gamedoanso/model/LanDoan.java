package cybersoft.javabackend.java18.gamedoanso.model;

public class LanDoan {
	private final int soDoan;
	private String ketQua;
	
	public LanDoan(int soDoan) {
		this.soDoan = soDoan;
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
}
