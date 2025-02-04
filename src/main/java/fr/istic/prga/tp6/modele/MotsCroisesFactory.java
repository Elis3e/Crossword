package fr.istic.prga.tp6.modele;

public class MotsCroisesFactory
{
    public static MotsCroisesTP6 creerMotsCroises2x3()
    {
        MotsCroisesTP6 mc = new MotsCroisesTP6(2,3) ;
        for (int i=1; i<=mc.getHauteur(); i++)
            for (int j=1; j<=mc.getLargeur(); j++)
            {
                mc.setCaseNoire(i, j, false);
            }
        mc.setCaseNoire(2, 2, true);
        mc.setDefinition(1, 1, true, "Note");
        mc.setSolution(1, 1, 'S');
        mc.setSolution(1, 2, 'O');
        mc.setSolution(1, 3, 'L');
        mc.setDefinition(1, 1, false, "Autre note");
        mc.setSolution(2, 1, 'I');
        mc.setDefinition(1, 3, false, "Et encore une note");
        mc.setSolution(2, 3, 'A');
        return mc ;
    }

    public static void main(String[] args)
    {
        System.out.println(creerMotsCroises2x3());
    }
}
