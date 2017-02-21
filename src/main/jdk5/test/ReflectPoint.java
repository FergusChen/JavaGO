package main.jdk5.test;

import java.util.Date;

/**
 * Created by yudong on 17/2/20.
 */
public class ReflectPoint {
    private int x;
    public int y;
    private Date initDate = new Date();

    public Date getInitDate() {
        return initDate;
    }

    public void setInitDate(Date initDate) {
        this.initDate = initDate;
    }

    public String str1 = "ball";
    public String str2 = "baby";
    public String str3 = "case";

    public ReflectPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public String toString() {
        return "str1: " + str1 + "\t str2: " + str2 + "\t str3: " + str3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReflectPoint point = (ReflectPoint) o;

        if (x != point.x) return false;
        return y == point.y;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
