package com.ecom.proto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecom.proto.model.AuthDTO;


@Repository
public interface AuthRepository extends JpaRepository<AuthDTO , String> {

    @Query(value = "SELECT COUNT(u) FROM Users u WHERE u.email = :email" , nativeQuery = true)
	public long countByEmail(@Param("email") String email);

    @Query(value = "SELECT uid FROM Users u WHERE u.email = :email AND u.password = :password" , nativeQuery = true)
	public String checkCredentials(@Param("email") String email, @Param("password") String password);
}
