package tc97939.lab3_1.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tc97939.lab3_1.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByName(String name);
}
