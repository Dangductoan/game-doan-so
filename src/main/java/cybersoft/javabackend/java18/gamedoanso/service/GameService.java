package cybersoft.javabackend.java18.gamedoanso.service;

import cybersoft.javabackend.java18.gamedoanso.model.NguoiChoi;
import cybersoft.javabackend.java18.gamedoanso.store.GameStore;
import cybersoft.javabackend.java18.gamedoanso.store.GameStoreHolder;

public class GameService {
	private static GameService INSTANCE = null;
	public static GameService getINSTANCE () {
		if(INSTANCE == null)
			INSTANCE = new GameService();
		return INSTANCE;
	}

	private final GameStore store;
	
	private GameService () {
		store = GameStoreHolder.getStore();
	}
	
	public NguoiChoi dangNhap(String username, String password) {
		return store.getDsNguoiChoi().stream()
			.filter(player -> 
						player.getUsername().equals(username)
						&& player.getPassword().equals(password))
			.findFirst()
			.orElse(null);
	}
	
	public NguoiChoi dangKy(String username, String password, String name) {
		if (!isValidUser(username, password, name))
			return null;
		
		boolean userExisted = store.getDsNguoiChoi().stream()
			.anyMatch(player -> player.getUsername().equals(username));
		
		if (userExisted)
			return null;
		
		NguoiChoi newUser = new NguoiChoi(username, password, name);
		store.getDsNguoiChoi().add(newUser);
		
		return newUser;
	}

	private boolean isValidUser(String username, String password, String name) {
		if (username == null || "".equals(username.trim()))
			return false;
		
		if (password == null || "".equals(password.trim()))
			return false;

		return name != null && !"".equals(name.trim());
	}
	
	public static class KetQua {
		private KetQua(){}
		public static final String GREATER_THAN = "Số bạn đoán lớn hơn kết quả.";
		public static final String LESSER_THAN = "Số bạn đoán bé hơn kết quả.";
		public static final String PINGO = "Đoán chính xác!";
	}
}
