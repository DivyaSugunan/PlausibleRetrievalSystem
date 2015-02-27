package irintegratedproject;

import java.util.ArrayList;

public class QueryObject {
	Integer queryid;
	String informationNeed;
	String query = "Info about what eat.";
	ArrayList<String> entities = new ArrayList<>();
	ArrayList<String> actions  = new ArrayList<>();
	
        
	public void setQueryid(Integer queryid){
		this.queryid = queryid;
	}
	public void setInformationNeed(String informationNeed){
		this.informationNeed = informationNeed;
	}
	public void setQuery(String query){
		this.query = query;
	}
	public void addEntity(String entity){
		this.entities.add(entity);
	}
	public void addAction(String action){
		this.actions.add(action);
	}
	
	public Integer getQueryid(){
		return this.queryid;
	}
	public String getInformationNeed(){
		return this.informationNeed;
	}
	public String getQuery(){
		return this.query;
	}
	public ArrayList<String> getEntities(){
		return this.entities;
	}
	public ArrayList<String> getActions(){
		return this.actions;
	}
}


/*
<Query id=Â”1Â”>
  <Scenario>
Sarah just adopted a Shiba Inu puppy. She has never owned a pet and is uneasy about her new responsibilities, particularly what kind of foods she should feed her new dog. She would like to find blog posts where dogs eat food so she can determine which kinds of food other people are feeding their dogs.
  </Scenario>
  <Information Need>
    Info about what kinds of food dogs can eat.
  </Information Need>
  <Query>
    DOG eats FOOD
  </Query>
  <Entities>
    <Entity>THING.SPECIES.EUKARYOTE.ANIMAL.MAMAL.DOG</Entity>
    <Entity>THING.FOOD</Entity>
  </Entities>
  <Actions>
    <Action>EATS</ Action>
  </Actions>
</Query>


*/
