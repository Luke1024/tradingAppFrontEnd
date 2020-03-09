package frontend.chartDrawer.utilities.processor.utilities.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Container<T> {
    private Queue<Operation> neededOperations = new LinkedList<>();
    private List<T> memory = new ArrayList<T>();

    public Queue<Operation> getNeededOperations() {
        return neededOperations;
    }

    public List<T> getMemory() {
        return memory;
    }
}
