package buggedbattles;

public abstract class HoneyBee extends Insect{
    private int price;

    public static double HIVE_DMG_REDUCTION;

    public HoneyBee(Tile p, int h, int price){
        super(p,h);

        this.price = price;
    }
    public int getCost() {
        return this.price;
    }

    public void takeDamage(int damage){
        if (this.getPosition().isHive()){
            int damageToUse =  (int) (damage - (damage * HoneyBee.HIVE_DMG_REDUCTION));
            super.takeDamage(damageToUse);
        }
        else super.takeDamage(damage);
    }
}
