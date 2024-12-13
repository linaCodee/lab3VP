package mk.finki.ukim.mk.lab203078.repository.jpa;

import mk.finki.ukim.mk.lab203078.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaArtistRepository extends JpaRepository<Artist,Long> {
}
