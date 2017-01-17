package com.api.cassandra.repository;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import com.api.cassandra.entity.Team;

public interface TeamRepository extends CassandraRepository<Team> {
	@Query("SELECT * FROM team WHERE first_name=?0 LIMIT ?1")
	Iterable<Team> findByUser(String user,Integer limit);

	@Query("SELECT * FROM team")
    Iterable<Team> findAll();
	
	@Query("SELECT * FROM team where id=?0")
	public Team findByName(String id);
}