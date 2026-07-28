package com.gvp.unit4.runner;

import com.gvp.unit4.config.SeedUserProperties;
import com.gvp.unit4.model.Book;
import com.gvp.unit4.model.Category;
import com.gvp.unit4.model.Role;
import com.gvp.unit4.model.User;
import com.gvp.unit4.repository.BookRepository;
import com.gvp.unit4.repository.CategoryRepository;
import com.gvp.unit4.repository.UserRepository;
import java.math.BigDecimal;
import java.util.EnumSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class DataSeeder implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataSeeder.class);

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;
    private final PasswordEncoder passwordEncoder;
    private final SeedUserProperties seedUserProperties;

    public DataSeeder(UserRepository userRepository, CategoryRepository categoryRepository,
            BookRepository bookRepository, PasswordEncoder passwordEncoder, SeedUserProperties seedUserProperties) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.bookRepository = bookRepository;
        this.passwordEncoder = passwordEncoder;
        this.seedUserProperties = seedUserProperties;
    }

    @Override
    public void run(String... args) {
        log.info("Seeding mock users and catalog data (no database configured)...");

        userRepository.save(new User(null, seedUserProperties.getAdminUsername(),
                passwordEncoder.encode(seedUserProperties.getAdminPassword()), EnumSet.of(Role.ROLE_ADMIN, Role.ROLE_USER)));
        userRepository.save(new User(null, seedUserProperties.getUserUsername(),
                passwordEncoder.encode(seedUserProperties.getUserPassword()), EnumSet.of(Role.ROLE_USER)));

        Category programming = categoryRepository.save(new Category(null, "Programming", "Software development and programming languages"));
        Category fiction = categoryRepository.save(new Category(null, "Fiction", "Novels and short stories"));

        bookRepository.save(new Book(null, "Effective Java", "Joshua Bloch", programming.getId(), new BigDecimal("45.99"), 12));
        bookRepository.save(new Book(null, "Clean Code", "Robert C. Martin", programming.getId(), new BigDecimal("39.99"), 0));
        bookRepository.save(new Book(null, "The Silent Patient", "Alex Michaelides", fiction.getId(), new BigDecimal("14.99"), 25));

        log.info("Seeded accounts -> admin: '{}' (ROLE_ADMIN, ROLE_USER), user: '{}' (ROLE_USER)",
                seedUserProperties.getAdminUsername(), seedUserProperties.getUserUsername());
    }
}