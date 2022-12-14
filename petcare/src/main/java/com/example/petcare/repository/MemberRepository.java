package com.example.petcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.petcare.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{

	Member findByUsername(String username);
}
