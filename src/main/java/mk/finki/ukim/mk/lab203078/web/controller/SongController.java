package mk.finki.ukim.mk.lab203078.web.controller;

import mk.finki.ukim.mk.lab203078.model.Album;
import mk.finki.ukim.mk.lab203078.model.Song;
import mk.finki.ukim.mk.lab203078.service.AlbumService;
import mk.finki.ukim.mk.lab203078.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class SongController {
    private final AlbumService albumService;
    private final SongService songService;

    public SongController(AlbumService albumService, SongService songService) {
        this.albumService = albumService;
        this.songService = songService;
    }

    // Страница за листа на песни
    @GetMapping("/songs")
    public String getSongsPage(@RequestParam(required = false) String error, Model model){
        List<Song> songs = songService.listSongs();
        List<Album> albums = albumService.findAll();
        model.addAttribute("songs", songs);
        model.addAttribute("albums", albums);
        return "songsList";
    }

    // Страница за додавање и уредување песна
    @GetMapping("/songs/add")
    public String addSong(Model model){
        List<Album> albums = this.albumService.findAll();
        model.addAttribute("albums", albums);
        return "addSong";
    }

    // Чување на нова или изменета песна
    @PostMapping("/songs/save")
    public String saveSong(@RequestParam(required = false) Long trackId,
                           @RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Integer releasedYear,
                           @RequestParam Long albumId) {

        if (trackId == null) {
            // Додавање нова песна
            songService.save(title, genre, releasedYear, albumId, Optional.empty());
        } else {
            // Ако trackId е даден, се уредува постоечка песна
            songService.save(title, genre, releasedYear, albumId, Optional.of(trackId));
        }

        return "redirect:/songs"; // Пренасочување на главната страница
    }

    // Бришење на песна
    @GetMapping("/songs/delete/{id}")
    public String deleteSong(@PathVariable Long id){
        songService.deleteById(id);
        return "redirect:/songs";
    }

    // Страница за уредување песна
    @GetMapping("/songs/edit-form/{songId}")
    public String editFormSong(@PathVariable Long songId, Model model){
        Song song = songService.findByTrackId(songId); // Наоѓаме песна според trackId
        List<Album> albums = this.albumService.findAll(); // Наоѓаме сите албуми
        model.addAttribute("albums", albums); // Даваме ги албумите на view-то
        model.addAttribute("song", song); // Даваме ја песната што треба да се уреди
        return "addSong"; // Враќаме до addSong.html за уредување
    }

    // Филтрирање на песни според албум
    @GetMapping("/filter")
    public String filterSongs(@RequestParam Long albumId, Model model) {
        if (albumId == -1) {
            return "redirect:/songs";
        }
        List<Song> songs = songService.findByAlbumId(albumId);
        List<Album> albums = albumService.findAll();
        Optional<Album> selectedAlbum = albumService.findById(albumId);
        model.addAttribute("songs", songs);
        model.addAttribute("albums", albums);
        model.addAttribute("selectedAlbum", selectedAlbum.get());
        return "songsList";
    }
}
