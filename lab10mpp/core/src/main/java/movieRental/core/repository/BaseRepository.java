package movieRental.core.repository;

import movieRental.core.model.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseRepository<T extends Entity<ID>, ID extends Serializable> extends JpaRepository<T, ID> {
}
