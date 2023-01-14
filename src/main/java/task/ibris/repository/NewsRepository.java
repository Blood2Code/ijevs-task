package task.ibris.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import task.ibris.dto.ResponseDto;
import task.ibris.entity.News;
import task.ibris.entity.Thematic;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {
    @Override
    Page<News> findAll(Pageable pageable);
    News findByName(String name);
    List<News> findAllByThematic(Thematic thematic);
}
