package com.dbit.neo4j.controller;

import com.dbit.neo4j.neo4jentities.Individual;
import com.dbit.neo4j.neo4jentities.Organization;
import com.dbit.neo4j.repository.IndividualRepo;
import com.dbit.neo4j.repository.OrganizationRepo;
import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Map;

import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Call;

//http://freegeoip.net/json/103.207.59.227
@Controller
@RequestMapping(value="/dashboard/")
public class MainController {

    @Value("${ACCOUNT_SID}")
    private String ACCOUNT_SID;

    @Value("${AUTH_TOKEN}")
    private String AUTH_TOKEN;


    @Autowired
    IndividualRepo individualRepo;

    @Autowired
    OrganizationRepo organizationRepo;

    @GetMapping("/menu")
    public String menu(Model model)
    {

        return "showMenu";
    }


    @GetMapping("/individualCycle")
    public String individualCycle(Model model)
    {
        ArrayList<Individual> cycle=(ArrayList<Individual>)individualRepo.getCycles();
        model.addAttribute("cycleSet",cycle);
        return "individualCycles";
    }

    @GetMapping("/organisationCycle")
    public String organisationCycle(Model model)
    {
        ArrayList<Organization> cycle=(ArrayList<Organization>)organizationRepo.getCycles();
        model.addAttribute("cycleSet",cycle);
        return "organisationCycle";
    }


    @GetMapping("/ivrCalls")
    public String ivrCalls(Model model)
    {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        ResourceSet<Call> calls = Call.reader().read();
        model.addAttribute("calls",calls);

        for(Call call:calls)
        for (Map.Entry<String, String> entry : call.getSubresourceUris().entrySet())
        {
            System.out.println(entry.getKey() + "/" + entry.getValue());
        }

        return "IVRDetails";
    }



}

/**

 creating relationship: Ind to Ind
 MATCH(M:Ind),(N:Ind) WHERE M.name="A B C" and N.name="M N O" CREATE (M)-[r:Donate{amount:9000,when:"01-10-17"}]->(N) return r;

 creating relationship: Ind to Campaign(initiate)
 MATCH(M:Ind),(N:Camp) WHERE M.name="Tayyab Sayyad" and N.name="Program A" CREATE (M)-[r:Initiate{amountRequired:1000000,when:"01-10-17"}]->(N) return r;

 relationship between: Ind to Campaign(donates)
 MATCH(M:Ind),(N:Camp) WHERE M.name="X Y Z" and N.name="Program A" CREATE (M)-[r:Donate{amount:8000,when:"01-10-17"}]->(N) return r;


 identifying cycles
 match p=(a:Ind)-[*]-(a) return p;

 match p=(a:Ind)-[*]-(a) return collect(distinct a);


 http://freegeoip.net/json/210.16.112.143
 */


/*
1. Create Organisation Type Nodes
-- Organisation Main Node

MATCH (n) DETACH DELETE n;

CREATE (:OrganisationType{TYPE:'Organisation'});
CREATE (:OrganisationType{TYPE:'Small'});
CREATE (:OrganisationType{TYPE:'Medium'});
CREATE (:OrganisationType{TYPE:'Large'});


MATCH (A:OrganisationType),(B:OrganisationType)
WHERE A.TYPE='Organisation' AND B.TYPE='Small'
CREATE (A)-[R:ORG_TYPE]->(B);


MATCH (A:OrganisationType),(B:OrganisationType)
WHERE A.TYPE='Organisation' AND B.TYPE='Medium'
CREATE (A)-[R:ORG_TYPE]->(B);


MATCH (A:OrganisationType),(B:OrganisationType)
WHERE A.TYPE='Organisation' AND B.TYPE='Large'
CREATE (A)-[R:ORG_TYPE]->(B);


--importing organisation data from csv
LOAD CSV WITH HEADERS FROM "file:///ORG.csv" AS line
CREATE (p:Organisation {  userName: line.org_username, name:line.org_name,
 country:line.org_country,location:line.org_location,description:line.org_description,pin:line.org_pin,
 license_no:line.org_license_no
 });



LOAD CSV WITH HEADERS FROM "file:///ORG_TYPE_RELATIONSHIPS.csv" AS line
MATCH (A:Organisation),(B:OrganisationType)
WHERE B.TYPE=line.org_type AND A.userName=line.org_username
CREATE (A)-[R:TYPE]->(B);


user_username,user_fname,user_mname,user_lname,user_location,
user_country,user_pincode

LOAD CSV WITH HEADERS FROM “file:///USERS.csv” AS line
CREATE (p:Users {
userName: line.org_username,
firstName: line.user_fname,
middleName:line.user_mname
lastName:line.user_lname,
location:line.user_location,
country:line.user_country,
pincode:line.user_pincode
 });



--importing command
./neo4j-shell -file ../import/script.cypher



 */