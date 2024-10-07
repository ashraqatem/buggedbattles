package buggedbattles;

public class SniperBee extends HoneyBee {

    private int attackDamage;
    private int piercingPower;
    public static int BASE_HEALTH;
    public static int BASE_COST;

    private int flag; //flag when hornet aims or shots

    public SniperBee(Tile posistion, int attackDamage, int piercingPower) {

        super(posistion, BASE_HEALTH, BASE_COST);

        this.attackDamage = attackDamage;
        this.piercingPower = piercingPower;
    }

    boolean takeAction() {
        this.flag = 0;

        if (this.getPosition().isOnThePath()) {
            return false;
        }

        Tile refrence = this.getPosition();

        while (this.flag == 0) {

            if (refrence.getHornets().length != 0) {
                this.flag = 1;

            } else if (refrence.getHornets().length == 0) {
                refrence = refrence.towardTheNest();
            }
        }
        if (this.flag != 0) {
            Hornet[] hornetsAction = this.getPosition().getHornets();

            int counter = 0;

            for (int i = 0; counter <= this.piercingPower; i++) {

                hornetsAction[i].takeDamage(this.attackDamage);

                counter++;
            }
            return true;
        }
        return false;
    }
}


