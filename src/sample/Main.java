package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Scanner;


public class Main extends Application {
    static int count = 0;


    @Override
    public void start(Stage primaryStage) throws Exception {
        Group group = new Group();
        int width = 1000;
        int height = 600;

        Scene scene = new Scene(group, width, height);

        primaryStage.setScene(scene);
        primaryStage.show();


        ArrayList<Integer> inputList = getInput();

//        ArrayList<Integer> testlist = new ArrayList<Integer>(Arrays.asList(8,4,3,7,10,14,13,1,6));

        ArrayList<Node> ArrangedNodes = ArrangeNodes(inputList);
        drawNodeRecursive(width/2, 20, width/2, 20, ArrangedNodes.get(0), group);




        primaryStage.show();


    }

    private synchronized ArrayList<Integer>  getInput() {
        ArrayList<Integer> bstNumbers = new ArrayList<Integer>();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter an Int for the bst. Type exit to exit");
        while ( scan.hasNext()){
            System.out.println("Enter next int");
            String temp = scan.next();
            if (temp.equals("exit")){
                break;}
            int num = Integer.parseInt(temp);
            bstNumbers.add(num);
        }
        for (Integer intg:bstNumbers){
            System.out.print(intg);

        }
        return bstNumbers;
    }


    public static void main(String[] args) {
        launch(args);
    }

    public void RecursiveArrange(ArrayList<Node> thisList){


        for (int i = 0; i < thisList.size()-1; i++){

            Arange(thisList.get(i+1), thisList.get(0));

        }

    }


    void Arange(Node toCompare, Node root){
        if (toCompare.value < root.value){
            if (root.left == null)
                root.left = toCompare;
            else{
                Arange(toCompare, root.left);
            }
        }
        else {
            if (root.right == null)
                root.right = toCompare;
            else{
                Arange(toCompare, root.right);
            }
        }
    }


    public ArrayList<Node> ArrangeNodes(ArrayList<Integer> IntegerList){

        ArrayList<Node> NodesList = new ArrayList<Node>();


        for (int currentint:IntegerList ){

            Node node = new Node(currentint);
            NodesList.add(node);


        }

        RecursiveArrange(NodesList);

        return NodesList;
    }

    public void drawNodeRecursive(int x1, int y1, int x, int y, Node node, Group thisgroup) {
        boolean isRootNode;
        isRootNode = count == 0;
        Line line = new Line(x1, y1 + 5, x, y);
        int linelgt = 50;
        line.setStroke(Color.BLACK);
        thisgroup.setStyle("-fx-background-color: black;");
//        System.out.println("came");
        thisgroup.getChildren().add(line);
        Circle circle = new Circle(x, y, 15, Paint.valueOf("blue"));
        thisgroup.getChildren().add(circle);
        Text txt = new Text(x - 3, y + 3, String.valueOf(node.value));
        thisgroup.getChildren().add(txt);
        count++;
        if (isRootNode ){
            if(node.left != null)
                drawNodeRecursive(x,y,x-(linelgt+30),y+linelgt,node.left, thisgroup);
            if(node.right != null)
                drawNodeRecursive(x,y,x+(linelgt+30),y+linelgt,node.right,thisgroup);
        }
        else{
            if(node.left != null)
                drawNodeRecursive(x,y,x-(linelgt),y+linelgt,node.left, thisgroup);
            if(node.right != null)
                drawNodeRecursive(x,y,x+(linelgt),y+linelgt,node.right,thisgroup);

        }



    }
}





