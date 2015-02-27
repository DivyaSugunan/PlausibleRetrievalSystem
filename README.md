# PlausibleRetrievalSystem
In this project, an information retrieval architecture involving several modules is
built. The main characteristic of this project is that it builds a modern retrieval
framework that takes into account plausibility information expressed in documents.
That is, each action (e.g. eating, cooking, surfing, selling) that is mentioned in a
document can only be performed by certain types of entities (e.g. people can surf,
animals can eat, companies can sell). In this project, each team will construct a retrieval
model capable of identifying documents where an entity type (e.g. person) performs
some action (e.g. cooking).For this project, the entity types we will consider are listed here:
http://mappings.dbpedia.org/server/ontology/classes/
These entity types are inspired by Wikipedia, and capture a hierarchy of common nouns,
such as THING.AGENT.PERSON which indicates a person (e.g. Sarah, Bryan) or
THING.FOOD which indicates some sort of food (e.g. cake, steak, pizza).
We will loosely define an action as any verb that expresses something that an entity
can do (e.g. the man ate the cake, the cat drank milk).
The tasks in this project are:
TASK: Dataset Acquisition
TASK: Index Construction
TASK: Query Formulation
TASK: Retrieval Model
TASK: RelevanceJudgements
TASK:GUI Construction
