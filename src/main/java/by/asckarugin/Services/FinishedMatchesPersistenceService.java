package by.asckarugin.Services;

import by.asckarugin.DTO.MatchDTO;
import by.asckarugin.Models.Match;
import by.asckarugin.Models.Player;
import by.asckarugin.Repositories.MatchRepository;

import java.util.List;

public class FinishedMatchesPersistenceService {

    public static final MatchRepository matchRepository = new MatchRepository();

    public static final OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getINSTANCE();

    private final static Integer MAX_LIST_SIZE = 15;

    public static void finishedMatch(MatchDTO matchDTO){

        Match endedMatch = buildMatch(matchDTO);

        matchRepository.save(endedMatch);

        ongoingMatchesService.getCurrentMatches().remove(matchDTO.getId());

    }

    public List<Match> getMatches(Integer page){
        return matchRepository
                .findAll()
                .stream()
                .skip((page-1)*MAX_LIST_SIZE)
                .limit(MAX_LIST_SIZE)
                .toList();
    }

    public List<Match> getMatchesByName(String name, Integer page){
        return matchRepository
                .findByName(name)
                .stream()
                .skip((page-1)*MAX_LIST_SIZE)
                .limit(MAX_LIST_SIZE)
                .toList();
    }

    private static Match buildMatch(MatchDTO matchDTO) {

        Player firstPlayer = Player.builder()
                .id(matchDTO.getFirstPlayer().getId())
                .name(matchDTO.getFirstPlayer().getName())
                .build();

        Player secondPlayer = Player.builder()
                .id(matchDTO.getSecondPlayer().getId())
                .name(matchDTO.getSecondPlayer().getName())
                .build();

        Player winner = null;

        if(matchDTO.getFirstPlayer().getSet() == 2){
            winner = firstPlayer;
        } else if(matchDTO.getSecondPlayer().getSet() == 2){
            winner = secondPlayer;
        }

        return Match.builder()
                .firstPlayer(firstPlayer)
                .secondPlayer(secondPlayer)
                .winnerPlayer(winner)
                .build();
    }

}
