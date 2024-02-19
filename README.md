# MotsCroisesFX
Un jeu de mots croisés avec 11 grilles différentes (en anglais & français) et 3 niveaux de difficulté. 
L'interface graphique a été faite avec JavaFX en utilisant un pattern MVC.

  Menu principal
  <br> <br>
  <img src="screenshots/main_menu.png" alt="Capture d'écran du menu principal" width="350">

<p> 
  Choix de la grille
 <p> <img src="screenshots/grid_choice.png" alt="Capture d'écran choix de la grille de jeu" width="350"> </p>
  
Liste des grilles disponibles :
<table>
  <tr>
    <th>N°</th>
    <th>Intitulé</th>
    <th>Difficulté</th>
  </tr>
  <tr>
    <td>1 à 8</td>
    <td>Anglais</td>
    <td>Moyen<td>
  </tr>
  <tr>
    <td>9</td>
    <td>NYT</td>
    <td>Expert<td>
  </tr>
  <tr>
    <td>10</td>
    <td>Français</td>
    <td>Débutant<td>
  </tr>
  <tr>
    <td>11</td>
    <td>Français</td>
    <td>Impossible<td>
  </tr>
</table>
</p> 

## Déroulement d'une partie
<p>
  Un cadre bleu apparait sur la case sélectionnée dans laquelle on veut ecrire une lettre. 
 <p> <img src="screenshots/def.png" alt="Capture d'écran de la grille" width="400"> </p>
</p>
<p> 
  Les définitions apparaissent en info-bulle quand le curseur est placé sur une case. [Définition horizontale / Définition verticale]
 <p> <img src="screenshots/def_2.png" alt="Définition" width="400"> </p>
</p>
<p> 
  Le cadre se deplace automatiquement vers la case suivante (horizontalement ou verticalement) quand on ecrit une lettre dans la case selectionnée
  et inversement quand on supprime une lettre.
  <p> <img src="screenshots/move.gif" alt="Gif pour illustrer le deplacement du curseur" width="400"> </p>
</p>
<p> 
  Appuyer sur la touche ENTER permet de vérifier si les lettres placées sont correctes, les cases sont colorées en vert si c'est le cas.
  <br> <br>
  <img src="screenshots/enter.gif" alt="Gif pour illustrer la coloration des cases correctes" width="400"> 
</p>

<p> 
  Appuyer sur le clic central permet de dévoiler la solution d'une case.
  <p> <img src="screenshots/central clic.gif" alt="Gif pour illustrer la révélation des solutions" width="400"> </p>
</p>

<p>
  La partie se termine une fois que toutes les cases sont correctement remplies, une fenêtre de fin de partie apparait alors.
  <p> <img src="screenshots/won.png" alt="Partie terminée" width="400"> </p> 
</p>

## Config ⚙️
<p>Java 16+ & javafx sdk pour executer le programme </p>
<P>Run src/motCroisee/v2/App.java </p>








