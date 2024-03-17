package by.asckarugin.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class MatchDTO {

    private UUID id;

    private PlayerDTO firstPlayer;

    private PlayerDTO secondPlayer;

    private boolean isTieBreak;

    private boolean isAdvantage;

}
