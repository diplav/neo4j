package com.dbit.neo4j.repository;

import com.dbit.neo4j.neo4jentities.Individual;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface IndividualRepo extends Neo4jRepository<Individual,Long> {


    @Query("match p=(a:Ind)-[*]-(a) return collect(distinct a)")
    List<Individual> getCycles();
}
