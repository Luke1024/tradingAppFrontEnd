package frontend.chartDrawer.utilities.processor.utilities.test.operations;

import frontend.chartDrawer.utilities.processor.utilities.test.Operation;
import frontend.chartDrawer.utilities.processor.utilities.test.chartParts.ChartPart;

import java.util.List;

public class ToolSet {
    public class RectangleGenerator implements Operation {

        @Override
        public Object execute(Object input) {

            return null;
        }
    }
    public class ObjectHolder<T> {
        List<ChartPart> objects;
        public ObjectHolder() {}
    }
}
