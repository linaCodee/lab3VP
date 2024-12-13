package mk.finki.ukim.mk.lab203078.repository.impl;

import mk.finki.ukim.mk.lab203078.bootstrap.DataHolder2;
import mk.finki.ukim.mk.lab203078.model.Album;
import mk.finki.ukim.mk.lab203078.model.Artist;
import mk.finki.ukim.mk.lab203078.model.Song;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Repository
public class SongRepository {
    public List<Song> findAll(){
        return DataHolder2.songs;
    }
    public Song findByTrackId(Long trackId){
        return DataHolder2.songs.stream().filter(r->r.getTrackId().equals(trackId)).findFirst().orElse(null);
    }
    public void deleteById(Long id){
        DataHolder2.songs.removeIf(r->r.getTrackId().equals(id));
    }

    public Artist addArtistToSong(Song song, Artist artist){
        for (Song s:DataHolder2.songs){
            if (s.getTrackId().equals(song.getTrackId())){
                s.addArtist(artist);
                return artist;
            }
        }
        return null;
    }
    public Optional<Song> save(String title, String genre, Integer releaseYear, Album album,Optional<Long> trackId){
        if (trackId.isPresent()) {
            Optional<Song> se = DataHolder2.songs.stream().filter(s -> s.getTrackId().equals(trackId.get())).findFirst();
            if (se.isPresent()) {
                Song s = se.get();
                s.setTitle(title);
                s.setGenre(genre);
                s.setReleaseYear(releaseYear);
                s.setAlbum(album);
            }
        } else {
            Song song = new Song(title, genre, releaseYear, album);
            DataHolder2.songs.add(song);
            return Optional.of(song);
        }
        return Optional.empty();
    }
}
