package GameComponents;

import javafx.scene.image.Image;

public class StaticElement {
    private String _type;
    private int _positionInDataArray;
    private int _points;
    public StaticElement(String type,int positionInDataArray,int points) {
        this._type=type;
        this._positionInDataArray=positionInDataArray;
        this._points=points;
    }

    public String get_type() {
        return _type;
    }

    public void set_type(String _type) {
        this._type = _type;
    }

    public int get_positionInDataArray() {
        return _positionInDataArray;
    }

    public void set_positionInDataArray(int _positionInDataArray) {
        this._positionInDataArray = _positionInDataArray;
    }

    public int get_points() {
        return _points;
    }

    public void set_points(int _points) {
        this._points = _points;
    }
}
