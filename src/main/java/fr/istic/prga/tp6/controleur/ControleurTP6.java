package fr.istic.prga.tp6.controleur;

import fr.istic.prga.tp6.modele.ChargerGrille;
import fr.istic.prga.tp6.modele.MotsCroisesFactory;
import fr.istic.prga.tp6.modele.MotsCroisesTP6;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.sql.SQLException;
import java.util.Map;


public class ControleurTP6 {

    private MotsCroisesTP6 motsCroisesTP6;

    private TextField caseCourante;

    private boolean directionCourante;

    @FXML
    private GridPane grilleMC;

    @FXML
    private Label nomDeLaGrille;

    @FXML
    private void initialize() throws SQLException {
        this.grilleAleatoire();
        // this.motsCroisesTP6 = MotsCroisesFactory.creerMotsCroises2x3();
    }

    @FXML
    public void clicLettre(MouseEvent e) {

        TextField laCase = (TextField) e.getSource();
        int lig = ((int) laCase.getProperties().get("gridpane-row")) + 1;
        int col = ((int) laCase.getProperties().get("gridpane-column")) + 1;

        if (e.getButton() == MouseButton.MIDDLE) {
            this.motsCroisesTP6.montrerSolution(lig, col);
        }

        if (e.getButton() == MouseButton.PRIMARY) {
            this.setCaseCourante(laCase);
        }
    }

    @FXML
    private void appuiTouches(KeyEvent e) {

        int lig = ((int) caseCourante.getProperties().get("gridpane-row")) + 1;
        int col = ((int) caseCourante.getProperties().get("gridpane-column")) + 1;

        if (e.getCode() == KeyCode.UP) {
            this.enHaut(lig, col);
            directionCourante = false;
        } else if (e.getCode() == KeyCode.DOWN) {
            this.enBas(lig, col);
            directionCourante = false;
        } else if (e.getCode() == KeyCode.LEFT) {
            this.aGauche(lig, col);
            directionCourante = true;
        } else if (e.getCode() == KeyCode.RIGHT) {
            this.aDroite(lig, col);
            directionCourante = true;
        } else if (e.getCode() == KeyCode.ENTER) {
            colorierCasesCorrectesEnVert();
        }
    }

    private void setCaseCourante(TextField tf) {
        caseCourante = tf;
        caseCourante.requestFocus();
    }


    private TextField getTextField(int lig, int col) {
        for (Node n : grilleMC.getChildren()) {
            if (n instanceof TextField) {
                int row = (int) n.getProperties().get("gridpane-row") + 1;
                int column = (int) n.getProperties().get("gridpane-column") + 1;
                if (row == lig && column == col) {
                    return (TextField) n;
                }
            }
        }
        return null;
    }

    private void colorierCasesCorrectesEnVert() {
        for (Node n : grilleMC.getChildren()) {
            if (n instanceof TextField) {
                TextField tf = (TextField) n;
                int lig = ((int) n.getProperties().get("gridpane-row")) + 1;
                int col = ((int) n.getProperties().get("gridpane-column")) + 1;
                String proposition = tf.textProperty().getValue() + "";
                String solution = motsCroisesTP6.getSolution(lig, col) + "";
                if (proposition.equals(solution)) {
                    tf.getStyleClass().add("case_correcte");
                }
            }
        }
    }

    private void enHaut(int lig, int col) {
        if (lig != 1) {
            int i = 1;
            TextField tf = getTextField(lig - i, col);
            while (tf == null && (lig - i) != 0) {
                i++;
                tf = getTextField(lig - i, col);
            }
            if (tf != null)
                setCaseCourante(tf);
        }
    }

    private void enBas(int lig, int col) {
        if (lig != grilleMC.getRowCount()) {
            int i = 1;
            TextField tf = getTextField(lig + i, col);
            while (tf == null && (lig + i) != grilleMC.getRowCount()) {
                i++;
                tf = getTextField(lig + i, col);
            }
            if (tf != null)
                setCaseCourante(tf);
        }
    }

    private void aGauche(int lig, int col) {
        if (col != 1) {
            int j = 1;
            TextField tf = getTextField(lig, col - j);
            while (tf == null && (col - j) != 1) {
                j++;
                tf = getTextField(lig, col - j);
            }
            if (tf != null)
                setCaseCourante(tf);
        }
    }

    private void aDroite(int lig, int col) {
        if (col != grilleMC.getColumnCount()) {
            int j = 1;
            TextField tf = getTextField(lig, col + j);
            while (tf == null && (col + j) != grilleMC.getColumnCount()) {
                j++;
                tf = getTextField(lig, col + j);
            }
            if (tf != null)
                setCaseCourante(tf);
        }
    }

    @FXML
    private void grilleAleatoire() throws SQLException {
        int Min = 1, Max = 11, nombreAleatoire = Min + (int) (Math.random() * ((Max - Min) + 1));
        chargerGrille(nombreAleatoire);
    }

    private void chargerGrille(int numGrille) throws SQLException {
        TextField modele = (TextField) grilleMC.getChildren().get(0);
        grilleMC.getChildren().clear();
        this.caseCourante = modele;
        this.directionCourante = true;
        ChargerGrille chargerGrille = new ChargerGrille();
        Map<Integer, String> grilles = chargerGrille.grillesDisponibles();
        this.nomDeLaGrille.setText(grilles.get(numGrille));
        this.motsCroisesTP6 = chargerGrille.extraireGrille(numGrille);
        for (int lig = 1; lig <= motsCroisesTP6.getHauteur(); lig++) {
            for (int col = 1; col <= motsCroisesTP6.getLargeur(); col++) {
                if (!motsCroisesTP6.estCaseNoire(lig, col)) {
                    TextField nouveau = new TextField();
                    nouveau.setPrefWidth(modele.getPrefWidth());
                    nouveau.setPrefHeight(modele.getPrefHeight());
                    for (Object cle : modele.getProperties().keySet())
                        nouveau.getProperties().put(cle, modele.getProperties().get(cle));

                    grilleMC.add(nouveau, col - 1, lig - 1);
                }
            }
        }
        configurerCases();
    }

    private void configurerCases() {
        for (Node n : grilleMC.getChildren()) {
            if (n instanceof TextField tf) {

                int lig = ((int) n.getProperties().get("gridpane-row")) + 1;
                int col = ((int) n.getProperties().get("gridpane-column")) + 1;

                //Bind des cases de la vue avec celles du modele
                tf.textProperty().bindBidirectional(motsCroisesTP6.propositionProperty(lig, col));

                //Ajout des toolTip
                tf.setTooltip(new Tooltip(infoBulles(lig, col)));

                //Ajout des cliques molette pour devoiler la solution / selectionner la case courante
                tf.setOnMouseClicked(this::clicLettre);

                // Ajouts d'écouteurs sur les touches du clavier
                tf.setOnKeyPressed(this::appuiTouches);

                tf.textProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        if (newValue.length() == 0) {
                            if (directionCourante) {
                                this.aGauche(lig, col);
                            } else {
                                this.enHaut(lig, col);
                            }
                        } else {
                            String character = newValue.substring(0, 1);
                            if (!character.matches("[a-zA-Z]"))
                                tf.setText(oldValue);
                            else {
                                tf.setText(character.toUpperCase());
                                if (!tf.getText().equals(motsCroisesTP6.getSolution(lig, col) + ""))
                                    tf.getStyleClass().remove("case_correcte");
                                if (directionCourante) {
                                    this.aDroite(lig, col);
                                } else {
                                    this.enBas(lig, col);
                                }
                            }
                        }
                    }
                });

                // Ajouter un ChangeListener pour détecter les changements de texte
                tf.textProperty().addListener((observable, oldValue, newValue) -> {
                    // Vérifier si le nouveau texte est une lettre majuscule sans accent
                    if (newValue != null && newValue.matches("[A-Z]")) {
                        // Créer une nouvelle ScaleTransition pour agrandir la lettre
                        ScaleTransition st = new ScaleTransition(Duration.millis(300), tf);
                        st.setFromX(0.5);
                        st.setFromY(0.5);
                        st.setToX(1.01);
                        st.setToY(1.01);
                        // Appliquer la transition
                        st.play();
                    }
                });
            }
        }
    }

    private String infoBulles(int lig, int col) {
        String defHoriz = motsCroisesTP6.getDefinition(lig, col, true);
        String defVert = motsCroisesTP6.getDefinition(lig, col, false);
        return defHoriz != null && defVert != null ? defHoriz + " / " + defVert : defHoriz != null ? defHoriz : defVert;
    }
}