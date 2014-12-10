resrefname
==========

3 modules :

-> 1 Back-End Server (service). Communication avec BDD en REST+JPA

-> 1 Front-End (client). Envoi/Réception de requête avec GUI

-> 1 API. Interface entre client et server. Format les requetes, communique avec le server et offre des services au client


Travail :

Dev 1 : Front-End

Dev 2 : Back-End

Dev 1 & 2 : Discussion Archi + API




Cmd :

mvn clean (avant les git add)

mvn clean install (~compilation + exécution)

mvn jetty:run (~lancement du server)


////////////////////////////////////////////////////////

Structuration : 

Work *<->1 Author

Work 1<->* Picture

Work *->* Particularity

Museum 1->* IMuseum

Museum 1->* Author

CollectionWork 1->* Work

CollectionPicture 1->* Picture

NB : Si le temps, sortir Comments & tags de IMuseum pour table à part => plus rapide pour recherche
