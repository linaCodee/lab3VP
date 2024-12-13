package mk.finki.ukim.mk.lab203078.service.impl;

import mk.finki.ukim.mk.lab203078.model.Album;
import mk.finki.ukim.mk.lab203078.model.Artist;
import mk.finki.ukim.mk.lab203078.model.Song;
import mk.finki.ukim.mk.lab203078.repository.jpa.JpaAlbumRepository;
import mk.finki.ukim.mk.lab203078.repository.jpa.JpaSongRepository;
import mk.finki.ukim.mk.lab203078.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {

    private final JpaSongRepository songRepository;
    private final JpaAlbumRepository albumRepository;
    public SongServiceImpl(JpaSongRepository songRepository, JpaAlbumRepository albumRepository){
        this.songRepository=songRepository;
        this.albumRepository = albumRepository;
    }
    @Override
    public List<Song> listSongs() {
        return songRepository.findAll();
    }

    @Override
    public Artist addArtistToSong(Artist artist, Song song) {
        if (!song.getPerformers().contains(artist)){
            song.getPerformers().add(artist);
            songRepository.save(song);
        }
        return artist;
    }

    @Override
    public Song findByTrackId(Long trackId) {
        return listSongs().stream().filter(r->r.getTrackId().equals(trackId)).findFirst().orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        this.songRepository.deleteById(id);
    }

    @Override
    public Optional<Song> save(String title, String genre, Integer releasedYear, Long albumId,Optional<Long> songId) {
        Album album1=albumRepository.findById(albumId).orElse(null);
        if (songId.isPresent()){
            Optional<Song> song = songRepository.findById(songId.get());
            if (song.isPresent()) {
                song.get().setTitle(title);
                song.get().setGenre(genre);
                song.get().setReleaseYear(releasedYear);
                song.get().setAlbum(album1);
                return Optional.of(songRepository.save(song.get()));
            } else {
                return Optional.of(songRepository.save(new Song(title, genre, releasedYear, album1)));
            }
        } else {
            return Optional.of(songRepository.save(new Song(title, genre, releasedYear, album1)));
        }
    }

    @Override
    public List<Song> findByAlbumId(Long albumId) {
        return songRepository.findByAlbum_Id(albumId);
    }


}
