package one.group.oneapp;

import java.io.Serializable;
import java.util.ArrayList;

public class HarvestableManager implements Serializable {
    private ArrayList<Collidable> plants = new ArrayList<Collidable>();
    public HarvestableManager(){
        plants.add(new Grass(400,400));
        plants.add(new Grass(300,300));
        plants.add(new Grass(400,700));
        plants.add(new Grass(200,1200));
    }

    public void removePlant(Collidable plant){
        plants.remove(plant);
    }
    public void addPlant(int x,int y){
        plants.add(new Grass(x,y));
    }

    public ArrayList<Collidable> getPlants() {
        return (ArrayList<Collidable>) plants.clone();
    }
    public void collidePlayer(Player player){
        for(Collidable item: this.getPlants()){
            if(PhysicsManager.collides(item,player)){
                this.removePlant(item);
                player.incrementItems();
            }
        }
    }
    public int getItems(){
        return plants.size();
    }
}
