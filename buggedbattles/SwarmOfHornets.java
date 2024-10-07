package buggedbattles;

public class SwarmOfHornets {
    private Hornet[] hornets;
    private int sizeSwarm;
    public static double QUEEN_BOOST; //percent of health regeneration

    public SwarmOfHornets(){
        this.hornets = new Hornet[15];
        this.sizeSwarm = 0;
    }

    public int sizeOfSwarm(){
        return this.sizeSwarm;
    }

    public Hornet[] getHornets(){

        // count the non-null elements
        int count = 0;
        for (int i = 0; this.hornets[i] != null; i++){
            count++;
        }

        // create an array with the count of the non-null elements
        Hornet [] noNullHornets = new Hornet[count];

        int i = 0;
        for (Hornet h : this.hornets){
            if (h != null){
                noNullHornets[i] = this.hornets[i];
                i++;
            }
        }
        return noNullHornets;
    }

    public Hornet getFirstHornet(){
        if (this.sizeSwarm == 0){
            return null;
        }
        return this.hornets[0];
    }

    private void resizeHornets(){
        Hornet[] biggerHornets = new Hornet[this.hornets.length*2];

        for (int i=0; i < this.sizeSwarm; i++){
            biggerHornets[i] = this.hornets[i];
        }
        this.hornets = biggerHornets;
    }

    public void addHornet(Hornet h){
        if (this.hornets.length == this.sizeSwarm){
            resizeHornets();
        }

        this.hornets[this.sizeSwarm] = h;
        this.sizeSwarm ++;

        if (h.isTheQueen()){
            Hornet [] hornets1 = getHornets();
            for (Hornet notQueenHornet : hornets1){
                if (!notQueenHornet.isTheQueen()){
                    notQueenHornet.regenerateHealth(QUEEN_BOOST);
                }

            }
        }
    }

    private void shift() {
        for (int i = 0; i < this.hornets.length; i++) {

            if (this.hornets[i] == null) {
                for (int removed = i; removed < (this.hornets.length - 1); removed++) {

                    this.hornets[removed] = this.hornets[removed + 1];

                    if (removed == this.sizeSwarm) {
                        this.hornets[removed] = null;
                    }
                }

            }
        }
    }

    public boolean removeHornet (Hornet h){

        for (int i = 0; this.hornets[i] == h; i++) {
            this.hornets[i] = null;
            this.sizeSwarm -= 1;
            shift();
            return true;
        }
        return false;
    }

}

