package com.example.ProfitabilityEnahancement.jpaiml;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ProfitabilityEnahancement.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{

	// that's it ... no need to write any code LOL!
	
}