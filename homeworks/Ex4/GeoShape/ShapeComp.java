package geo;

import ex4.Ex4_Const;
import gui.GUIShape;
import gui.GUI_Shape;

import java.util.Comparator;


    public class ShapeComp implements Comparator<GUI_Shape> {
    private int ind;

    public ShapeComp(int flag) {
        this.ind = flag;
    }

//    /**
//     * @param o1 the first object to be compared.
//     * @param o2 the second object to be compared.
//     * @return
//     */
    @Override
    public int compare(GUI_Shape o1, GUI_Shape o2) {
        if (o1 == null || o2 == null) {
            return 0;
        }
        int ans = 0;
        {
            if (ind == Ex4_Const.Sort_By_Area) {
                ans = Double.compare(o1.getShape().area(), o2.getShape().area());
            }
            else if (ind == Ex4_Const.Sort_By_Anti_Area) {
                ans = Double.compare(o1.getShape().area(), o2.getShape().area()) * -1;
            }
            else if (ind == Ex4_Const.Sort_By_Perimeter) {
                ans = Double.compare(o1.getShape().perimeter(), o2.getShape().perimeter());
            }
            else if (ind == Ex4_Const.Sort_By_Anti_Perimeter){
                ans = Double.compare(o1.getShape().perimeter(), o2.getShape().perimeter()) * -1;
            }
            else if (ind == Ex4_Const.Sort_By_toString){
                ans = o1.getShape().toString().compareTo(o2.getShape().toString());
            }
            else if (ind == Ex4_Const.Sort_By_Anti_toString){
                ans = o1.getShape().toString().compareTo(o2.getShape().toString()) * -1;
            }
            else if (ind == Ex4_Const.Sort_By_Tag){
                ans = Double.compare(o1.getTag(), o2.getTag());
            }
            else if (ind == Ex4_Const.Sort_By_Anti_Tag){
                ans = Double.compare(o1.getTag(), o2.getTag()) * -1;
            }
        }
        return ans;
    }
}
