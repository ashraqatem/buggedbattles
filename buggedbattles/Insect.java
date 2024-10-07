package buggedbattles;

public abstract class Insect {
    private Tile position;
    private int healthPoints;
    private boolean isQueen;
    private int countQueens;


    public Insect(Tile p, int h){
        if (p.addInsect(this)) {
            this.position = p;

        } else {
            throw new IllegalArgumentException("Can not add insect to this tile");
        }

        this.healthPoints = h;
        this.isQueen = false;
        this.countQueens = 0;

    }
    public final Tile getPosition() {
        return this.position;
    }
    public final int getHealth() {
        return this.healthPoints;
    }

    public void setPosition( Tile position ){
        this.position = position;
    }

    public void takeDamage(int damage){
        this.healthPoints -= damage;

        if (this.healthPoints <= 0){
            this.position.removeInsect(this);
        }
    }

    abstract boolean takeAction();


    public boolean equals(Object equalTest){

        if ((equalTest instanceof Insect) && ((Insect) equalTest).healthPoints == this.healthPoints &&

                ((Insect) equalTest).position == this.position){

            return true;
        }
        return false;
    }

    public void regenerateHealth(double percentageToGenerate){;
        int pointToAdd = (int) (this.healthPoints * percentageToGenerate);
        this.healthPoints += pointToAdd;
    }


}










