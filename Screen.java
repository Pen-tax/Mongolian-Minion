import java.util.Random;

public class Screen{ 

    public static void main(String[] args) {
        GameArena arena = new GameArena(640, 480);
        Random rand = new Random();
        int state = 1;
        int score = 1;
        int negscore = 0;

        Line topBorder = new Line(0,50,640,50,20,"WHITE");
        Line bottomBorder = new Line(0,420,640,420,30,"WHITE");

        Text title = new Text("Mongolian Minion", 40, 33,30,"RED");
        Text score1 = new Text("Score: "+ score, 40, 400,30,"RED");
        Ball enemy = new Ball(640, 230, 50, "Red");
        Ball Banana = new Ball(640, 230, 50, "Green");
        // temp ball for minion
        Ball minion = new Ball(100,100,30,"YELLOW");
        //setText(title);
        arena.addBall(minion);
        arena.addText(title);
        arena.addText(score1);
        arena.addBall(enemy);
        arena.addBall(Banana);
        arena.addLine(topBorder);
        arena.addLine(bottomBorder);
        while(state == 1)
        {
            String filename = "Tree.jpg";
            arena.setBackgroundImage(filename);
            arena.pause();
            enemy.move(-10 + negscore,0);
            Banana.move(-10 + negscore,0);
            if (enemy.getXPosition() < 0.0) {
                enemy.setXPosition(640);
                int randY = rand.nextInt(440);
                enemy.setYPosition(randY);
            }
            if (Banana.getXPosition() < 0.0) {
                Banana.setXPosition(640);
                int randY = rand.nextInt(440);
                Banana.setYPosition(randY);
            }
// Minion up and down movement
            if (arena.downPressed() && minion.getYPosition() < 420) {
                double current = minion.getYPosition();
                minion.setYPosition(current + score);
            }

            if (arena.upPressed() && minion.getYPosition() > 0) {
                double current = minion.getYPosition();
                minion.setYPosition(current - score);
            }

            if (Banana.collides(minion)) {
                score += 1;
                negscore -= 1;
            }
            if (enemy.collides(minion)) {
                state = 2;
            }
        }
        // game over screen
        while (state == 2) {
            String filename = "over.jpg";
            arena.setBackgroundImage(filename);
        }
    }
}