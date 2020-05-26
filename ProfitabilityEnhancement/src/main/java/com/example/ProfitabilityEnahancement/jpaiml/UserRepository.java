package com.example.ProfitabilityEnahancement.jpaiml;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.ProfitabilityEnahancement.entity.User;

@Repository
@CrossOrigin(origins = "*", allowedHeaders = "*",maxAge = 1800)
public interface UserRepository extends JpaRepository<User, Integer>
{

	// that's it ... no need to write any code LOL!
	
}