package one.group.oneapp;

import java.io.Serializable;
import java.util.ArrayList;

public class HarvestableManager implements Serializable {
    private ArrayList<Collidable> plants = new ArrayList<Collidable>();
    public HarvestableManager(){
        this.regenerate();
    }

    public void removePlant(Collidable plant){
        plants.remove(plant);
    }
    public void addPlant(int x,int y){
        plants.add(new Grass(x,y));
    }
    public void addPlant(){
        int x = PhysicsManager.minX + (int)(Math.random()*(PhysicsManager.maxX - Grass.width- PhysicsManager.minX));
        int y = PhysicsManager.minY + (int)(Math.random()*(PhysicsManager.maxY - Grass.height - PhysicsManager.minY));
        plants.add(new Grass(x,y));
    }

    public  void regenerate(){
        for(int i = 0;i<100;i++){
            addPlant();
        }
    }
    public ArrayList<Collidable> getPlants() {
        return (ArrayList<Collidable>) plants.clone();
    }
    public void collidePlayer(Player player){
        for(Collidable item: this.getPlants()){
            if(PhysicsManager.collides(item,player)){
                this.removePlant(item);
                player.incrementItems();
                if(this.plants.size() == 0) this.regenerate();
            }
        }
    }
    public int getItems(){
        return plants.size();
    }
    public void clearItems(){
        plants = new ArrayList<Collidable>();
    }
}
