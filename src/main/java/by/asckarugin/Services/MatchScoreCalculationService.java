package by.asckarugin.Services;

import by.asckarugin.DTO.MatchDTO;
import lombok.Getter;

import java.util.UUID;

public class MatchScoreCalculationService {

    @Getter
    private static final MatchScoreCalculationService INSTANCE = new MatchScoreCalculationService();

    private MatchScoreCalculationService(){}

    private final OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getINSTANCE();

    private static Integer COUNTER_FIRST_PLAYER = 0;

    private static Integer COUNTER_SECOND_PLAYER = 0;

    public void pointToPlayer(UUID uuid, String playerName){

        MatchDTO matchDTO = ongoingMatchesService.getCurrentMatches().get(uuid);

        if(matchDTO.isAdvantage()){

            advantage(matchDTO, playerName);

        } else if (matchDTO.isTieBreak()) {

            tieBreak(matchDTO, playerName);

        } else{

            plusScoreToPlayer(matchDTO, playerName);
            checkWinGame(matchDTO);
            checkWinSet(matchDTO);

        }

        checkWin(matchDTO);
    }

    private void plusScoreToPlayer(MatchDTO matchDTO, String playerName) {

        if(matchDTO.getFirstPlayer().getName().equals(playerName)){

            matchDTO.getFirstPlayer().plusScore(++COUNTER_FIRST_PLAYER);

        } else{

            matchDTO.getSecondPlayer().plusScore(++COUNTER_SECOND_PLAYER);

        }

    }

    private void checkWin(MatchDTO matchDTO) {

        if(matchDTO.getFirstPlayer().getSet() == 2 || matchDTO.getSecondPlayer().getSet() == 2){
            FinishedMatchesPersistenceService.finishedMatch(matchDTO);
        }

    }

    private void tieBreak(MatchDTO matchDTO, String playerName) {

        if(matchDTO.getFirstPlayer().getScore() >= 6
           && matchDTO.getFirstPlayer().getScore() - matchDTO.getSecondPlayer().getScore() >= 2){

            resetScoreAndGame(matchDTO);
            matchDTO.getFirstPlayer().plusSet();
            matchDTO.setTieBreak(false);

        } else if(matchDTO.getSecondPlayer().getScore() >= 6
        && matchDTO.getSecondPlayer().getScore() - matchDTO.getFirstPlayer().getScore() >= 2){

            resetScoreAndGame(matchDTO);
            matchDTO.getSecondPlayer().plusSet();
            matchDTO.setTieBreak(false);

        } else if(matchDTO.getFirstPlayer().getName().equals(playerName)){

            matchDTO.getFirstPlayer().setScore(++COUNTER_FIRST_PLAYER);

        } else{

            matchDTO.getSecondPlayer().setScore(++COUNTER_SECOND_PLAYER);

        }

    }

    private void resetScoreAndGame(MatchDTO matchDTO) {

            COUNTER_FIRST_PLAYER = 0;
            matchDTO.getFirstPlayer().setScore(0);
            matchDTO.getFirstPlayer().setGame(0);

            COUNTER_SECOND_PLAYER = 0;
            matchDTO.getSecondPlayer().setScore(0);
            matchDTO.getSecondPlayer().setGame(0);

    }

    private void checkWinSet(MatchDTO matchDTO) {

        if(matchDTO.getFirstPlayer().getGame() == 7){

            resetScoreAndGame(matchDTO);
            matchDTO.getFirstPlayer().plusSet();

        } else if (matchDTO.getSecondPlayer().getGame() == 7){

            resetScoreAndGame(matchDTO);
            matchDTO.getSecondPlayer().plusSet();

        } else if(matchDTO.getFirstPlayer().getGame().equals(matchDTO.getSecondPlayer().getGame())
                && matchDTO.getFirstPlayer().getGame() >= 6){

            matchDTO.setTieBreak(true);

        }

    }

    private void checkWinGame(MatchDTO matchDTO) {

        if(COUNTER_FIRST_PLAYER == 4){

            matchDTO.getFirstPlayer().plusGame();
            resetScore(matchDTO);

        } else if (COUNTER_SECOND_PLAYER == 4) {

            matchDTO.getSecondPlayer().plusGame();
            resetScore(matchDTO);

        } else if (COUNTER_FIRST_PLAYER.equals(COUNTER_SECOND_PLAYER) && COUNTER_FIRST_PLAYER == 3) {

            matchDTO.setAdvantage(true);

        }

    }

    private void advantage(MatchDTO matchDTO, String playerName) {

        if(COUNTER_FIRST_PLAYER - COUNTER_SECOND_PLAYER == 1){

            resetScore(matchDTO);
            matchDTO.getFirstPlayer().plusGame();
            matchDTO.setAdvantage(false);

        } else if (COUNTER_SECOND_PLAYER - COUNTER_FIRST_PLAYER == 1) {

            resetScore(matchDTO);
            matchDTO.getSecondPlayer().plusGame();
            matchDTO.setAdvantage(false);

        } else if (matchDTO.getFirstPlayer().getName().equals(playerName)){

            ++COUNTER_FIRST_PLAYER;

        } else if(matchDTO.getSecondPlayer().getName().equals(playerName)){

            ++COUNTER_SECOND_PLAYER;

        }

    }

    private void resetScore(MatchDTO matchDTO) {

            COUNTER_FIRST_PLAYER = 0;
            matchDTO.getFirstPlayer().setScore(0);

            COUNTER_SECOND_PLAYER = 0;
            matchDTO.getSecondPlayer().setScore(0);

    }
}
