package buggedbattles;

public class FireBee extends HoneyBee {
    private int maxAttack;
    public static int BASE_HEALTH;

    public static int BASE_COST;

    public FireBee(Tile posistion, int maxAttack) {

        super(posistion, BASE_HEALTH, BASE_COST);

        this.maxAttack = maxAttack;
    }

    public boolean takeAction() {
        //aiming a cursor to where the fire would be set
        Tile reference = this.getPosition().towardTheNest();

        //if the bee is on the path it will attack hornets
        if (this.getPosition().isOnThePath()){

            //look for hornets in range of the bee's range
            for (int attack = 0; (attack < this.maxAttack) && (reference != null); attack++) {

                /*if cursor is a tile which is part of path,occupied by hornets, not a nest and not one fire it will set
                that tile on fire
                 */
                if (reference.isOnThePath() && reference.getHornets().length != 0 && !reference.isNest() &&
                        !reference.isOnFire()) {

                    reference.setOnFire();
                    return true;

                } else if (attack == this.maxAttack){
                    return false;

                } else {
                    reference = reference.towardTheNest();
                }
            }
        }
        return false;
    }
}


