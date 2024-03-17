package by.asckarugin.Services;

import by.asckarugin.DTO.MatchDTO;
import by.asckarugin.DTO.PlayerDTO;
import by.asckarugin.Models.Match;
import by.asckarugin.Models.Player;
import by.asckarugin.Repositories.MatchRepository;
import by.asckarugin.Repositories.PlayerRepository;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesService {

    @Getter
    private static final OngoingMatchesService INSTANCE = new OngoingMatchesService();

    private OngoingMatchesService(){}
    private final PlayerRepository playerRepository = new PlayerRepository();

    private final MatchRepository matchRepository = new MatchRepository();

    @Getter
    private final Map<UUID, MatchDTO> currentMatches = new ConcurrentHashMap<>();

    public UUID startNewGame(String firstPlayer, String secondPlayer){

        if(playerRepository.findByName(firstPlayer).isEmpty()){
            Player player = Player.builder()
                    .name(firstPlayer)
                    .build();
            playerRepository.save(player);
        }

        Player playerFirst = playerRepository.findByName(firstPlayer).get();
        PlayerDTO playerDTOFirst = activePlayer(playerFirst);

        if(playerRepository.findByName(secondPlayer).isEmpty()){
            Player player = Player.builder()
                    .name(secondPlayer)
                    .build();
            playerRepository.save(player);
        }

        Player playerSecond = playerRepository.findByName(secondPlayer).get();
        PlayerDTO playerDTOSecond = activePlayer(playerSecond);

        UUID randomUUID = UUID.randomUUID();

        MatchDTO currentMatch = MatchDTO.builder()
                    .id(randomUUID)
                    .firstPlayer(playerDTOFirst)
                    .secondPlayer(playerDTOSecond)
                    .build();

        currentMatches.put(randomUUID, currentMatch);

        return randomUUID;
    }

    public List<Match> getFinishedMatches(){
        return matchRepository.findAll();
    }

    private PlayerDTO activePlayer(Player player) {
        return PlayerDTO.builder()
                .id(player.getId())
                .name(player.getName())
                .score(0)
                .game(0)
                .set(0)
                .build();
    }

}
