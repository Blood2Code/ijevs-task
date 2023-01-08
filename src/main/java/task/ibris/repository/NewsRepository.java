package task.ibris.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import task.ibris.dto.ResponseDto;
import task.ibris.entity.News;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {
    List<News> findAll();
    ResponseDto<News> findByName(String name);
}
