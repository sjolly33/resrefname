resrefname
==========

2 modules :

-> 1 Back-End Server (service). REST+JPA, Test Unitaires

-> 1 Front-End (client). AngularJS, Transformation JSON, Protractor 

//////////////////////////////////////////////////////

Cmd :

mvn clean (avant les git add)

mvn clean install (~compilation + exécution)

mvn jetty:run (~lancement du server)

////////////////////////////////////////////////////////

Fichiers : 

/entities : Tout ce qui concerne le model

/entities/Data : Les données disponibles à l'initialisation

/entities/Test : Jeux de données simple pour tester (avec routes, etc)

/filters : Tout ce qui concerne les filtres (EM, etc)

Museum.java : Construit un musée

IMuseum.java : Propriétés communes à tout element du musée

MuseumRoot.java : Chemin d'accès aux éléments

////////////////////////////////////////////////////////

ModelBDD : 

Work *<->1 Author

Work 1<->* Picture

Work 1->* Particularity

Museum 1->* IMuseum

Museum 1->* Author

CollectionWork 1->* Work

CollectionPicture 1->* Picture

NB : Si le temps, sortir Comments & tags de IMuseum pour table à part => plus rapide pour recherche ; table de jointure entre Work<->Picture et Work<->Particularity
