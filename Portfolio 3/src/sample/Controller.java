package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import java.util.Stack;


public class Controller{
    private  final  AStarGraph model;
    private  final  A_StarFormView view;

    public Controller(AStarGraph GraphModel, A_StarFormView StarView ){
        this.model = GraphModel;
        this.view = StarView;
        view.exitBtn.setOnAction(e-> Platform.exit());
        view.AStarBtn.setOnAction(e -> model.A_Star(view.startVertexComB.getValue(),view.endVertexComB.getValue(),true));
        EventHandler <ActionEvent> PrintRequestHndl = e -> HandlePrintRequest(view.endVertexComB.getValue(),view.shortestPathTA);
        view.PrintBtn.setOnAction(PrintRequestHndl);

    }
    public void HandlePrintRequest(Vertex destination, TextArea TArea){
        Vertex pvertex = destination;
        TArea.appendText("To " + destination.getid() +" Shortest length: "+destination.getNeighbourDistance()+"\n");
        Stack<Vertex> Path1 = new Stack<>();
        int limit = 0;
        while (pvertex != null){
            Path1.push(pvertex);
            pvertex=pvertex.getPrev();
        }
        if(!Path1.isEmpty())
            limit = Path1.size();
        for(int i = 0; i <limit -1 ;i++)
            TArea.appendText(Path1.pop().getid() + " -> "+"\n");
        if(limit>0)
            TArea.appendText(Path1.pop().getid()+"\n");

    }

}
