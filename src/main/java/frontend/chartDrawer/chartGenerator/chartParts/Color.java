package frontend.chartDrawer.chartGenerator.chartParts;

import java.util.Arrays;
import java.util.List;

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
        if (!(o instanceof Color)) return false;
        Color color = (Color) o;
        return getRed() == color.getRed() &&
                getGreen() == color.getGreen() &&
                getBlue() == color.getBlue();
    }
}
