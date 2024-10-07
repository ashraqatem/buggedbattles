package buggedbattles;

public class Tile {
    private int food;
    private boolean hive;
    private boolean hornetNest;
    private boolean path; //path from hornet to nest
    private Tile nextHornet;
    private Tile nextHive;
    private HoneyBee honeyBee;
    private SwarmOfHornets listHornets;
    private boolean fireTile; //bool indicates if tile is on fire or not

    public Tile() {
        this.food = 0;
        this.hive = false;
        this.hornetNest = false;
        this.path = false;
        this.honeyBee = null;
        this.fireTile = false; //set to false by default and set to true later
        this.listHornets = new SwarmOfHornets();//is this how we would intilize the list
    }

    public Tile(int food, boolean hive, boolean hornetNest, boolean path,
                Tile toHive, Tile nest, HoneyBee honeyBee, SwarmOfHornets listHornets) {

        this.food = food;
        this.hive = hive;
        this.hornetNest = hornetNest;
        this.path = path;
        this.nextHornet = nest;
        this.nextHive = toHive;
        this.honeyBee = honeyBee;
        this.listHornets = listHornets;
    }

    public boolean isHive() {
        return this.hive;
    }

    public boolean isNest() {
        return this.hornetNest;
    }

    public void buildHive() {
        if (!this.hive) {
            this.hive = true;
        }
    }

    public void buildNest() {
        if (!this.hornetNest) {
            this.hornetNest = true;
        }
    }

    public boolean isOnThePath() {
        return this.path;
    }

    public Tile towardTheHive() {
        if (!this.isOnThePath() || this.isHive()){
            return null;
        }
        return this.nextHive;
    }

    public Tile towardTheNest() {
        if (!this.isOnThePath() || this.isNest()){
            return null;
        }
        return this.nextHornet;
    }

    public void createPath(Tile beforeTowardHive, Tile afterTowardNest) {
        if (beforeTowardHive == null){
            if (!(this.hive)){
                throw new IllegalArgumentException("This tile should have a hive!");
            }else {
                this.nextHive = null;
                this.nextHornet = afterTowardNest;
                this.path = true;
            }

        } else if (afterTowardNest == null) {
            if (!(this.hornetNest)) {
                throw new IllegalArgumentException("This tile should have a nest!");
            } else {
                this.nextHive = beforeTowardHive;
                this.nextHornet = null;
                this.path = true;
            }

        } else {
            this.nextHive = beforeTowardHive;
            this.nextHornet = afterTowardNest;
            this.path = true;
        }
    }

    public int collectFood() {
        int collectedFood = this.food;
        this.food = 0;
        return collectedFood;
    }

    public void storeFood(int addedFood) {
        this.food += addedFood;
    }

    public HoneyBee getBee() {
        return this.honeyBee;
    }

    public int getNumOfHornets() {
        return this.listHornets.sizeOfSwarm();
    }

    public Hornet getHornet() {
        return this.listHornets.getFirstHornet();
    }

    public Hornet[] getHornets() {
        return this.listHornets.getHornets();
    }

    public boolean addInsect(Insect insect) {
        if (((insect instanceof HoneyBee) && (this.honeyBee == null) && (!this.hornetNest))) {
            HoneyBee bee = (HoneyBee) insect;
            this.honeyBee = bee;
            bee.setPosition(this);
            return true;

        } else if ((insect instanceof Hornet) && (this.path)){
            Hornet hornet = (Hornet) insect;

            if (this.listHornets == null){
                this.listHornets = new SwarmOfHornets();
            }

            this.listHornets.addHornet(hornet);
            hornet.setPosition(this);
            return true;

        } else {
            return false;
        }
    }

    public boolean removeInsect(Insect insect) {
        if (insect.equals(this.getBee())) {
            HoneyBee bee = (HoneyBee) insect;
            this.honeyBee = null;
            bee.setPosition(null);
            return true;

        } else if (insect.equals(this.getHornet())) {
            Hornet hornet = (Hornet) insect;
            this.listHornets.removeHornet(hornet);
            hornet.setPosition(null);
            return true;

        } else {
            return false;
        }
    }

    public void setOnFire(){
        this.fireTile = true;
    }

    public boolean isOnFire(){
        return this.fireTile;
    }
}

