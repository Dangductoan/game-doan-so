package cybersoft.javabackend.java18.gamedoanso.store;

import java.util.ArrayList;
import java.util.List;

import cybersoft.javabackend.java18.gamedoanso.model.LanDoan;
import cybersoft.javabackend.java18.gamedoanso.model.ManChoi;
import cybersoft.javabackend.java18.gamedoanso.model.NguoiChoi;

public class GameStore {
	private List<NguoiChoi> dsNguoiChoi;
	private List<ManChoi> dsManChoi;
	private List<LanDoan> dsLanDoan;
	
	GameStore () {
		dsNguoiChoi = new ArrayList<>();
		dsManChoi = new ArrayList<>();
		dsLanDoan = new ArrayList<>();

		dsNguoiChoi.add(new NguoiChoi("admin", "1234", "Admin"));
	}

	public List<NguoiChoi> getDsNguoiChoi() {
		return dsNguoiChoi;
	}

	public List<ManChoi> getDsManChoi() {
		return dsManChoi;
	}

	public List<LanDoan> getDsLanDoan() {
		return dsLanDoan;
	}
}
