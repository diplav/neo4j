package com.dbit.neo4j.neo4jentities;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type="Initiate")
public class Initiate {


    @GraphId
    Long nodeId;

    Double amountRequired;

    String when;

    @StartNode
    private Individual start;
    @EndNode
    private Campaign end;


    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public Double getAmountRequired() {
        return amountRequired;
    }

    public void setAmountRequired(Double amountRequired) {
        this.amountRequired = amountRequired;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }
}
