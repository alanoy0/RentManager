# RentManager

>>>Fonction pour le client
- Créer un client majeur, avec un prénom et un nom avec plus de 3 caractères, une adresse mail n'existant pas déjà
- Modifier le client qui doit rester majeur, avec un prénom et un nom de plus de 3 caractères
- Supprimer le client en indiquant son id
Si le client ne respecte pas les conditions celui ci n'est pas créer mais aucun message d'erreur ne s'affiche

>>>Fonction pour le vehicule
- Créer un vehicule en indiquant le modèle et le nombre de place qui doit être compris entre 2 et 9
- Modifier le vehicule avec le nombre de place compris entre 2 et 9
- Supprimer le vehicule en indiquant son id

>>>Fonction pour la réservation
- Créer une réservation, la durée ne pouvant pas dépasser 7 jours 
- Modifier la réservation en ne dépassant pas 7 jours
- -Supprimer la reservation en indiquant l'id

>>>Spring est mis en place
>>>4 tests ont été réalié: un qui vérifie que le client est majeur, l'autre qu'il est mineur, un autre qui vérifie que le nom comprend plus 
   de 3 caractères et le dernier vérifie que le prénom contient moins de 3 caractères
>>>Lorsque les contraintes ne sont pas validées, l'objet ne se crée pas, mais rien n'indique la raison à l'utilisateur
