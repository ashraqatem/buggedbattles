package buggedbattles;

public class AngryBee extends HoneyBee{
    private int attack;
    public static int BASE_HEALTH;
    public static int BASE_COST;

    public AngryBee(Tile p, int a){

        super(p,BASE_HEALTH, BASE_COST);

        this.attack = a;
    }

    boolean takeAction(){
        while (this.getPosition().isOnThePath()){
            if(this.getPosition().getHornets().length != 0){
                getPosition().getHornet().takeDamage(this.attack);
                return true;

            } else if (this.getPosition().towardTheHive().getHornets().length != 0){
                getPosition().getHornet().takeDamage(this.attack);
            }
        }
        return false;
    }
}
