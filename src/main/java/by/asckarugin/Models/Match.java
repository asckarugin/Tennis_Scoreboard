package by.asckarugin.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="first_player",
            foreignKey = @ForeignKey(name = "fk_first_player"))
    private Player firstPlayer;

    @ManyToOne
    @JoinColumn(name="second_player",
            foreignKey = @ForeignKey(name = "fk_second_player"))
    private Player secondPlayer;

    @ManyToOne
    @JoinColumn(name="winner_player",
            foreignKey = @ForeignKey(name = "fk_winner_player"))
    private Player winnerPlayer;

}
