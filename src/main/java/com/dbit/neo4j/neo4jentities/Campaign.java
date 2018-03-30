package com.dbit.neo4j.neo4jentities;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label = "Camp")
public class Campaign {

    @Id
    @GeneratedValue
    Long nodeId;

    String name;

    String eventOn;


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

    public String getEventOn() {
        return eventOn;
    }

    public void setEventOn(String eventOn) {
        this.eventOn = eventOn;
    }
}
