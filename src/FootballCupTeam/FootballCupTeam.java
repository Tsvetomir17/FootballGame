package FootballCupTeam;

public class FootballCupTeam {

    private final String name;
    private final int defence;
    private final int midfield;
    private final int attack;

    public FootballCupTeam(String name, int defence, int midfield, int attack) {

        this.name = name;
        this.defence = defence;
        this.midfield = midfield;
        this.attack = attack;
    }

    public String getName() {

        return name;
    }

    public int getDefence() {

        return defence;
    }

    public int getMidfield() {

        return midfield;
    }

    public int getAttack() {

        return attack;
    }

    public void printTeam(){

        System.out.println(name);
        System.out.println("Defence:  " + defence);
        System.out.println("Midfield: " + midfield);
        System.out.println("Attack:   " + attack);
        System.out.println();
    }
}
