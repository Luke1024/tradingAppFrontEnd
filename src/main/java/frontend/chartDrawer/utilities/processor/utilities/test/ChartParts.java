package frontend.chartDrawer.utilities.processor.utilities.test;

import frontend.chartDrawer.utilities.processor.utilities.containerParts.Color;

import java.util.Arrays;
import java.util.List;

public class ChartParts {
    interface ChartPart {}

    public class Line implements ChartPart {
        private Color color;
        private int x1;
        private int y1;
        private int x2;
        private int y2;

        public Line(Color color, int x1, int y1, int x2, int y2) {
            this.color = color;
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        public Color getColor() {
            return color;
        }

        public int getX1() {
            return x1;
        }

        public int getY1() {
            return y1;
        }

        public int getX2() {
            return x2;
        }

        public int getY2() {
            return y2;
        }
    }

    public class Rectangle implements ChartPart {}

    public class Text implements ChartPart {}

    public class Color {
        private int red;
        private int green;
        private int blue;

        public Color() {
            this.red = 0;
            this.green = 0;
            this.blue = 0;
        }

        public Color(int red, int green, int blue) {
            this.red = red;
            this.green = green;
            this.blue = blue;
        }

        public int getRed() {
            return red;
        }

        public int getGreen() {
            return green;
        }

        public int getBlue() {
            return blue;
        }

        public Color(String stringColor) {
            List<String> colors = Arrays.asList(stringColor.split(","));
            if(colors.size()==3){
                int red;
                int green;
                int blue;
                try {
                    red = Integer.parseInt(colors.get(0));
                    green = Integer.parseInt(colors.get(1));
                    blue = Integer.parseInt(colors.get(2));
                } catch (Exception e){
                    red = 0;
                    green = 0;
                    blue = 0;
                    System.out.println(e);
                }
                this.red = red;
                this.green = green;
                this.blue = blue;
            } else {
                System.out.println("Wrong color format, check application.propertes");
                this.red = 0;
                this.green = 0;
                this.blue = 0;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof frontend.chartDrawer.utilities.processor.utilities.containerParts.Color)) return false;
            frontend.chartDrawer.utilities.processor.utilities.containerParts.Color color = (frontend.chartDrawer.utilities.processor.utilities.containerParts.Color) o;
            return getRed() == color.getRed() &&
                    getGreen() == color.getGreen() &&
                    getBlue() == color.getBlue();
        }
    }
}
