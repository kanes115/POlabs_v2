package agh.cs.lab2;

/**
 * Created by Kanes on 28.10.2016.
 */
public class OptionsParser {

    public MoveDirection[] parse(String []args){

        MoveDirection[] res = new MoveDirection[args.length];
        for(int i=0; i< args.length; i++){
            if(args[i].equals("f") || args[i].equals("forward")) res[i]=MoveDirection.Forward;
            else if(args[i].equals("b") || args[i].equals("backward")) res[i]=MoveDirection.Backward;
            else if(args[i].equals("r") || args[i].equals("right")) res[i]=MoveDirection.Right;
            else if(args[i].equals("l") || args[i].equals("left")) res[i]=MoveDirection.Left;
            else throw new IllegalArgumentException("argument is not a legal move");
        }
        return res;
    }
}
