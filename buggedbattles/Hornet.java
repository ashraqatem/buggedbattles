package buggedbattles;

public class Hornet extends Insect {
    private int damage;
    private static int countQueens;
    private boolean isQueen;
    public static int BASE_FIRE_DMG; //stores damage hornet takes from a fire tile

    public Hornet(Tile p, int h, int d) {
        super(p, h);

        this.damage = d;
    }

    public boolean takeAction() {
        for (int i = 0; i < 2 && this.getPosition() !=null ;){
            //if the hornet is on a tile that is on fire take damage

            if (this.getPosition().isOnFire()) {
                this.takeDamage(BASE_FIRE_DMG);

                if (this.getPosition() == null){
                    return false;
                }

            } if (this.getPosition().getBee() != null) {
                //if the hornet is on a tile with a bee attack the bee
                this.getPosition().getBee().takeDamage(this.damage);

                //if not a queen exit for loop
                if (!this.isQueen) {
                    return true;

                    //if it is a queen and it is a 2nd round return true
                } else if (i > 0) {
                    return true;
                }

            } else if (this.getPosition().getBee() == null && !this.getPosition().isHive() &&
                    this.getPosition().towardTheHive() != null) {
                /*if there is not a bee on the tile and tile is not a hive and next tile toward hive is not null
            then the bee will move to the next tile
             */
                this.getPosition().towardTheHive().addInsect(this);

                if (!this.isQueen) {
                    return true;

                } else if (i > 0) {
                    return true;
                }

            } if (this.isQueen) {
                i++;
            }
        }
        return false;
    }


    public boolean equals(Object testEquals) {
        if (super.equals(testEquals) && ((Hornet) testEquals).damage == this.damage) {
            return true;
        }
        return false;
    }

    public boolean isTheQueen() {
        return this.isQueen;
    }

    public void promote() {
        if (countQueens == 0) {
            this.isQueen = true;
            this.countQueens++;
            {

            }
        }
    }

}