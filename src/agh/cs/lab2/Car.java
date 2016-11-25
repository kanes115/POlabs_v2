package agh.cs.lab2;

import java.util.LinkedList;
import java.util.List;

import static agh.cs.lab2.MapDirection.North;

/**
 * Created by Kanes on 28.10.2016.
 */
public class Car {
    private MapDirection orientation = North;
    private Position position = new Position(2, 2);
    private IWorldMap map;
    private List<IPositionChangeObserver> observers = new LinkedList<>();


    public Car(IWorldMap map){
        this.map = map;
    }

    public Car(IWorldMap map, Position startPos){
        this.map = map;
        position = startPos;
    }

    public Car(IWorldMap map, int x, int y){
        this.map = map;
        position = new Position(x, y);
    }


    public String toString(){
        switch(orientation) {
            case North:
                return "N";
            case South:
                return "S";
            case West:
                return "W";
            case East:
                return "E";
            default:
                return null;
        }
    }


    private Position updatePos(Position pos){
        return new Position(position).add(pos);
    }


    private Position checkAndMove(int ornt){
        Position pos = new Position(0 ,0);

        if(orientation == North) {
            pos = updatePos(new Position(0, -1).multiplyBy(ornt));
        }
        if(orientation == MapDirection.South){
            pos = updatePos(new Position(0, 1).multiplyBy(ornt));
        }
        if(orientation == MapDirection.East) {
            pos = updatePos(new Position(1, 0).multiplyBy(ornt));
        }
        if(orientation == MapDirection.West){
            pos = updatePos(new Position(-1, 0).multiplyBy(ornt));
        }


        if(!map.canMoveTo(pos)) return position;
        return pos;
    }


    public void move(MoveDirection direction){

        Position oldP = position;

        if(direction == MoveDirection.Right || direction == MoveDirection.Left) {
            orientation = orientation.next(orientation);
            return;
        }
        if(direction == MoveDirection.Backward){
            position = checkAndMove(-1);
        }else {
            position = checkAndMove(1);
        }

        positionChanged(oldP, position);

    }

    public Position getPosition(){
        return position;
    }

    public void addListener(IPositionChangeObserver observer){
        observers.add(observer);
    }

    public void removeListener(IPositionChangeObserver observer){
        observers.remove(observer);
    }

    public void positionChanged(Position oldP, Position newP){
        for(IPositionChangeObserver observer : observers){
            observer.positionChanged(oldP, newP);
        }
    }




    public static void main(){

        try {
            String[] tab = new String[5];
            tab[0] = "f";
            tab[1] = "f";
            tab[2] = "r";
            tab[3] = "f";
            tab[4] = "f";

            List<HayStack> haystacks = new LinkedList<>();
            haystacks.add(new HayStack(new Position(7, 7)));
            haystacks.add(new HayStack(new Position(-4, -4)));
            haystacks.add(new HayStack(new Position(3, 6)));
            haystacks.add(new HayStack(new Position(2, 0)));

            MoveDirection[] directions = new OptionsParser().parse(tab);
            AbstractWorldMap map = new UnboundedMap(haystacks);
            Car car1 = new Car(map);
            Car car2 = new Car(map, 3, 4);

            map.add(car1);
            map.add(car2);
            map.run(directions);
            System.out.println(map.toString());
        }catch(IllegalArgumentException ex){
            System.out.println(ex.getMessage());
        }
    }

}
