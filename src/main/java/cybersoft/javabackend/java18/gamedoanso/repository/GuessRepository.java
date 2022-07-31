package cybersoft.javabackend.java18.gamedoanso.repository;

import cybersoft.javabackend.java18.gamedoanso.model.Guess;
import cybersoft.javabackend.java18.gamedoanso.store.GameStoreHolder;

import java.util.Collection;
import java.util.List;

public class GuessRepository {
    private final List<Guess> guesses;

    public GuessRepository() {
        guesses = GameStoreHolder.getStore().getGuesses();
    }

    public Collection<? extends Guess> findBySession(String session) {
        return guesses.stream()
                .filter(g -> g.getGameSession().equals(session))
                .toList();
    }
}
