package mk.finki.ukim.mk.lab203078.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trackId;
    private String title;
    private String genre;
    private Integer releaseYear;
    @ManyToMany
    private List<Artist> performers=new ArrayList<>();
    @ManyToOne
    private Album album;

    public Song(String title, String genre, Integer releaseYear,Album album) {
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.album=album;
    }

    public void addArtist(Artist performer){
        performers.add(performer);
    }


}
