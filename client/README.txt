Execution : npm install

Le server puis le client puis protractor se lancent pour rendre la main au shell.

L'exécution de protractor peut prendre du temps à cause de l'update du driver sur chrome ! 

Si protractor ne s'exécute pas (erreur npm entre node/nodejs) alors faire :

-> "ps" & "kill" pour supprimer le processus de nodejs ou mongodb qui tourne en background
-> sudo apt-get install nodejs-legacy

puis réexécuté npm install
