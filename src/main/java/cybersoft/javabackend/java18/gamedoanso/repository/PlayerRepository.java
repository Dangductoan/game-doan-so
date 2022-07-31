package cybersoft.javabackend.java18.gamedoanso.repository;

import cybersoft.javabackend.java18.gamedoanso.model.Player;
import cybersoft.javabackend.java18.gamedoanso.store.GameStoreHolder;

import java.util.List;

public class PlayerRepository {
    private final List<Player> players;

    public PlayerRepository() {
        players = GameStoreHolder.getStore().getPlayers();
    }

    public Player findByUsername(String username) {
        return players.stream()
                .filter(p -> p.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    public boolean existedByUsername(String username) {
        return players.stream()
                .anyMatch(p -> p.getUsername().equals(username));
    }

    public void save(Player newUser) {
        players.add(newUser);
    }
}
