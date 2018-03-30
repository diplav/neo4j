package com.dbit.neo4j.neo4jentities;

import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type="Donate")
public class Donates {


    @GraphId
    Long nodeId;

    Double amount;
    String when;


    @StartNode
    private Individual start;
    @EndNode
    private Individual end;



    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }
}
