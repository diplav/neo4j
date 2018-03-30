package com.dbit.neo4j.repository;

import com.dbit.neo4j.neo4jentities.Individual;
import com.dbit.neo4j.neo4jentities.Organization;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizationRepo extends Neo4jRepository<Organization,Long>{


    @Query("match p=(a:Org)-[*]-(a) return collect(distinct a)")
    List<Organization> getCycles();

}

