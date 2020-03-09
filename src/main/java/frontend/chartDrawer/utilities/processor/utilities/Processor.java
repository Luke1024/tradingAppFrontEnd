package frontend.chartDrawer.utilities.processor.utilities;

import com.vaadin.flow.component.html.Image;
import frontend.chartDrawer.utilities.processor.utilities.test.Operation;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Processor<T> {
    private int operationCounter = 0;
    private Queue<Operation> operations = new LinkedList<>();
    private Queue<T> memory = new LinkedList<T>();

    public void load(Queue<Operation> operations){
        operations.addAll(operations);

    }

    public Image run(){
        Iterator iterator = operations.iterator();

        while(iterator.hasNext()){

            operationCounter += 1;
        }
        Image image = (Image) memory.poll();
        resetState();
        return image;
    }

    private void resetState(){
        memory.clear();
        operations.remove();
        operationCounter = 0;
    }
}
