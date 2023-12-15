package skku.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import skku.library.domain.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByLoginIdAndPassword(String loginId, String password);

    User findByLoginId(String loginId);
}
