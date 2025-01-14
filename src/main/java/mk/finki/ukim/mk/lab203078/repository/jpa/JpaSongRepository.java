package mk.finki.ukim.mk.lab203078.repository.jpa;

import mk.finki.ukim.mk.lab203078.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaSongRepository extends JpaRepository<Song,Long> {
    List<Song> findByAlbum_Id(Long albumId);
}
