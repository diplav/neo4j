package com.dbit.neo4j.neo4jentities;

import org.neo4j.ogm.annotation.*;

import java.util.Set;

@NodeEntity(label="Ind")
public class Individual {
    @Id @GeneratedValue
    Long nodeId;

    String name;
    String userSince;


    @Relationship(type = "Donate", direction = Relationship.UNDIRECTED)
    private Set<Donates> donatesSet;


    @Relationship(type = "Initiate", direction = Relationship.UNDIRECTED)
    private Set<Initiate> initiateSet;


    public Set<Donates> getDonatesSet() {
        return donatesSet;
    }

    public void setDonatesSet(Set<Donates> donatesSet) {
        this.donatesSet = donatesSet;
    }

    public Set<Initiate> getInitiateSet() {
        return initiateSet;
    }

    public void setInitiateSet(Set<Initiate> initiateSet) {
        this.initiateSet = initiateSet;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserSince() {
        return userSince;
    }

    public void setUserSince(String userSince) {
        this.userSince = userSince;
    }
}
