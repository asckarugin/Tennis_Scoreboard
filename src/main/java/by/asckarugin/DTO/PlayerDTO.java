package by.asckarugin.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerDTO {

    private Long id;

    private String name;

    private Integer score;

    private Integer game;

    private Integer set;

    public void plusScore(Integer score){
        switch (score){
            case 1:
                setScore(15);
                break;
            case 2:
                setScore(30);
                break;
            case 3:
                setScore(40);
                break;
        }
    }

    public void plusGame(){
        game++;
    }

    public void plusSet(){
        set++;
    }

}
