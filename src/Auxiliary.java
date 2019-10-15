import java.awt.Robot;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.MouseInfo;
import java.awt.event.InputEvent;

/* I don't need so much Color functional like in Java implementation. */
final class Color
{
    private int r;
    private int g;
    private int b;

    Color(int r, int g, int b)
    {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    int getRed()
    {
        return r;
    }

    int getGreen()
    {
        return g;
    }

    int getBlue()
    {
        return b;
    }

    void setRed(int r)
    {
        this.r = r;
    }

    void setGreen(int g)
    {
        this.g = g;
    }

    void setBlue(int b)
    {
        this.b = b;
    }
}

final class Pixel
{
    private int x;
    private int y;
    private int r;
    private int g;
    private int b;

    Pixel(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.r = 0;
        this.g = 0;
        this.b = 0;
    }

    Pixel(int r, int g, int b)
    {
        this.x = 0;
        this.y = 0;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    Pixel(int x, int y, int r, int g, int b)
    {
        this.x = x;
        this.y = y;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    int getX()
    {
        return x;
    }

    int getY()
    {
        return y;
    }

    Point getPoint()
    {
        Point p = new Point(x, y);

        return p;
    }

    int getRed()
    {
        return r;
    }

    int getGreen()
    {
        return g;
    }

    int getBlue()
    {
        return b;
    }

    Color getColor()
    {
        Color c = new Color(r, g, b);

        return c;
    }

    void setX(int x)
    {
        this.x = x;
    }

    void setY(int y)
    {
        this.y = y;
    }

    void setRed(int r)
    {
        this.r = r;
    }

    void setGreen(int g)
    {
        this.g = g;
    }

    void setBlue(int b)
    {
        this.b = b;
    }
}

class Auxiliary
{
    private static Robot r;

    Auxiliary(Robot r)
    {
        this.r = r;
    }

    static void move(Point p) throws Exception
    {
        r.mouseMove(p.x, p.y);
    }

    static void click(int btime, Point p, int atime) throws Exception
    {
        Thread.sleep(btime);

        r.mouseMove(p.x, p.y);
        r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        Thread.sleep(atime);
    }

    static void pressKey(int btime, int key, int atime, int delay)
        throws Exception
    {
        Thread.sleep(btime);

        r.keyPress(key);
        Thread.sleep(delay);
        r.keyRelease(key);

        Thread.sleep(atime);
    }

    static Point record(int secs) throws Exception
    {
        Point p;
        int i;

        System.out.printf("Mouse coordinates will be recorded in %d seconds",
            secs);

        for (i = secs; i > 0; i--) {
            Thread.sleep(1000);

            System.out.printf(".");
        }

        System.out.printf("\n");

        p = MouseInfo.getPointerInfo().getLocation();

        return p;
    }

    static boolean compareColors(Point p, Color c)
    {
        int rgb = r.getPixelColor(p.x, p.y).getRGB();
        int r = (rgb & 0x00ff0000) >> 16;
        int g = (rgb & 0x0000ff00) >> 8;
        int b = rgb & 0x000000ff;

        if (r == c.getRed() && g == c.getGreen() && b == c.getBlue())
            return true;

        return false;
    }
}
