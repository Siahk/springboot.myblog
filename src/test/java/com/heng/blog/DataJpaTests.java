package com.heng.blog;

import com.heng.blog.entity.User;
import com.heng.blog.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class DataJpaTests {
    @Autowired
    UserRepository userRepository;
    @Test
    public void saveAUer_thenFindIt() {
        User leili = new User().setLogin("leili").setFirstName("Lei").setLastName("Li");
        userRepository.save(leili);
        User found = userRepository.findById(leili.getId()).orElse(null);
        assertThat(found).isNotEqualTo(null);
        assertThat(leili).isEqualTo(found);
    }
}