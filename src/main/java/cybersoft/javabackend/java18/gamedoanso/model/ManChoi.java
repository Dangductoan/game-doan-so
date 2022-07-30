package cybersoft.javabackend.java18.gamedoanso.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ManChoi {
	private static int soId = 1;
	private static Random random = null;
	private String id;
	private int soNgauNhien;
	private List<LanDoan> lanDoan;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private boolean hoanThanh;
	private String nguoiChoi; // username
	
	public ManChoi (String nguoiChoi) {
		this.id = "GAME" + String.format("%05d", soId++);
		this.soNgauNhien = getRandomInt();
		this.lanDoan = new ArrayList<>();
		this.startTime = LocalDateTime.now();
		this.nguoiChoi = nguoiChoi;
	}
	
	private int getRandomInt() {
		if (random == null)
			random = new Random();
		
		return random.nextInt(1000-1) + 1;
	}
	
	public int getSoNgauNhien () {
		return this.soNgauNhien;
	}
}
