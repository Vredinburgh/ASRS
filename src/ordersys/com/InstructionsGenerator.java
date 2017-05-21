package ordersys.com;

/**
 * Created by ian on 18/05/2017.
 */
public class InstructionsGenerator {
    private int currentX = 0, currentY = 0;
    private int[][] route;
    private String instructions = "";

    public InstructionsGenerator() {

    }

    public void newInstructions(int[][] route) {
        this.route = route;

        for(int i = 0; i < route.length; i++) {
            checkDiffX(route[i][0] - currentX);
            checkDiffY(route[i][1] - currentY);
            currentX = route[i][0];
            currentY = route[i][1];
        }
    }

    private void checkDiffX(int xDiff) {

        if(xDiff > 0) {
            instructions += "r";
        } else if(xDiff < 0) {
            instructions += "l";
        }
        instructions += Math.abs(xDiff);
    }

    private void checkDiffY(int yDiff) {

        if(yDiff > 0) {
            instructions += "u";
        } else if(yDiff < 0) {
            instructions += "d";
        }
        instructions += Math.abs(yDiff);
    }

    public String getInstructions() {
        return instructions;
    }
}
