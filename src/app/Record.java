package app;

import javafx.beans.property.SimpleStringProperty;

public class Record {
    private SimpleStringProperty time, speed, acc, current, power;

    Record(String time, String speed, String acc, String current, String power) {
        this.time = new SimpleStringProperty(time);
        this.speed = new SimpleStringProperty(speed);
        this.acc = new SimpleStringProperty(acc);
        this.current = new SimpleStringProperty(current);
        this.power = new SimpleStringProperty(power);
    }

    public String getTime() {
        return time.get();
    }

    public SimpleStringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public SimpleStringProperty speedProperty() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed.set(speed);
    }

    public String getAcc() {
        return acc.get();
    }

    public SimpleStringProperty accProperty() {
        return acc;
    }

    public void setAcc(String acc) {
        this.acc.set(acc);
    }

    public String getCurrent() {
        return current.get();
    }

    public SimpleStringProperty currentProperty() {
        return current;
    }

    public void setCurrent(String current) {
        this.current.set(current);
    }

    public SimpleStringProperty powerProperty() {
        return power;
    }

    public void setPower(String power) {
        this.power.set(power);
    }

    public String getSpeed() {
        return speed.get();
    }

    public String getPower() {
        return power.get();
    }
}
