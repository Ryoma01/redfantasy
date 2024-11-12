import java.util.Random;
import java.util.stream.IntStream;
/**
 * RedFantasy
 */
public class RedFantasy {
    String[] monsters = new String[22];
    int[] monstersPoint = new int[22];

    int[] playerMonsters = new int[5];
    int[] playerMonstersPoint = new int[5];

    int[] cpuMonsters = new int[5];
    int[] cpuMonstersPoint = new int[5];

    int playerHealth = 50;
    int cpuHealth = 50;
    int playerBonusPoints = 0;        
    int cpuBonusPoints = 0;

    Random rnd = new Random();

    // battle history
    int[] playerHealthHistory = new int[100];
    int[] cpuHealthHistory = new int[100];
    
    public RedFantasy() {
        // init player/cpu monster array
        IntStream.range(0, this.playerMonsters.length).forEach(i -> {
            this.playerMonsters[i] = -1;
            this.cpuMonsters[i] = -1;
        });
        this.playerHealthHistory[0] = this.playerHealth;
        this.cpuHealthHistory[0] = this.cpuHealth;
        IntStream.range(0, this.playerHealthHistory.length).forEach(i -> {
            this.playerHealthHistory[i] = -9999;
            this.cpuHealthHistory[i] = -9999;
        });
    }

    public void startPhase() {
        drawPlayerMonsters();
        drawCpuMonsters();
        displayMonsterLists();
        calculatePoints();
        displayResults();
        recordBattleHistory();
    }

    private void drawPlayerMonsters() {
         // Draw player's monster card
        int playerDrawCount = this.rnd.nextInt(this.playerMonsters.length - 2) + 3;
        System.out.println("Player Draw " + playerDrawCount + " monsters");
        IntStream.range(0, playerDrawCount).forEach(i -> {
            int monsterIndex = this.rnd.nextInt(this.monsters.length);
            this.playerMonsters[i] = monsterIndex;
            this.playerMonstersPoint[i] = this.monstersPoint[monsterIndex];
        });
    }

    private void drawCpuMonsters() {
        // Draw CPU's monster card
        int cpuDrawCount = this.rnd.nextInt(this.cpuMonsters.length - 2) + 3;
        System.out.println("CPU Draw " + cpuDrawCount + " monsters");
        IntStream.range(0, cpuDrawCount).forEach(i -> {
            int monsterIndex = this.rnd.nextInt(this.monsters.length);
            this.cpuMonsters[i] = monsterIndex;
            this.cpuMonstersPoint[i] = this.monstersPoint[monsterIndex];
        });
    }

    private void displayMonsterLists() {
        System.out.println("--------------------");
        System.out.print("Player Monsters List:");
        IntStream.range(0, this.playerMonsters.length)
         .filter(i -> this.playerMonsters[i] != -1)
         .mapToObj(i -> this.monsters[this.playerMonsters[i]] + " ")
         .forEach(System.out::print);

        System.out.print("\nCPU Monsters List:");
        IntStream.range(0, this.cpuMonsters.length)
         .filter(i -> this.cpuMonsters[i] != -1)
         .mapToObj(i -> this.monsters[this.cpuMonsters[i]] + " ")
         .forEach(System.out::print);
        System.out.println("\n--------------------");
        System.out.println("Battle!");
    }

    private void calculatePoints() {
        playerBonusPoints = rollDiceForPlayer();
        cpuBonusPoints = rollDiceForCpu();
    }

    private int rollDiceForPlayer() {
        int playerDiceRoll = this.rnd.nextInt(6) + 1; // 1~6のサイコロを振る
        System.out.println("Player's Dice': " + playerDiceRoll);
        return calculateBonusPoints(playerDiceRoll, playerMonsters, playerMonstersPoint);
    }

    private int rollDiceForCpu() {
        int cpuDiceRoll = this.rnd.nextInt(6) + 1; // 1~6のサイコロを振る
        System.out.println("CPU's Dice': " + cpuDiceRoll);
        return calculateBonusPoints(cpuDiceRoll, cpuMonsters, cpuMonstersPoint);
    }

    private int calculateBonusPoints(int diceRoll, int[] monsters, int[] monstersPoint) {
        if (diceRoll == 1) {
            halveMonsterPoints(monsters, monstersPoint);
            return diceRoll;
        }
        if (diceRoll == 6) {
            doubleMonsterPoints(monsters, monstersPoint);
            return diceRoll;
        }
        return diceRoll;
    }

    private void halveMonsterPoints(int[] monsters, int[] monstersPoint) {
        System.out.println("失敗！すべてのモンスターポイントが半分になる");
        IntStream.range(0, monsters.length)
         .filter(i -> monsters[i] != -1)
         .forEach(i -> monstersPoint[i] /= 2);
    }

    private void doubleMonsterPoints(int[] monsters, int[] monstersPoint) {
        System.out.println("Critical！すべてのモンスターポイントが倍になる");
        IntStream.range(0, monsters.length)
         .filter(i -> monsters[i] != -1)
         .forEach(i -> monstersPoint[i] *= 2);
    }

    private void displayResults() {
        int playerTotalPoints = calculateTotalPoints(playerMonsters, playerMonstersPoint, playerBonusPoints);
        int cpuTotalPoints = calculateTotalPoints(cpuMonsters, cpuMonstersPoint, cpuBonusPoints);
        System.out.println("Player Monster Pointの合計: " + playerTotalPoints);
        System.out.println("CPU Monster Pointの合計: " + cpuTotalPoints);
        System.out.println("--------------------");

        determineWinner(playerTotalPoints, cpuTotalPoints);
        System.out.println("Player HP is " + this.playerHealth);
        System.out.println("CPU HP is " + this.cpuHealth);
        System.out.println("--------------------");
    }

    private int calculateTotalPoints(int[] monsters, int[] monstersPoint, int bonusPoints) {
        int totalPoints = bonusPoints;
        return IntStream.range(0, monsters.length)
                .filter(i -> monsters[i] != -1)
                .map(i -> monstersPoint[i])
                .sum() + bonusPoints;
    }

    private void determineWinner(int playerTotalPoints, int cpuTotalPoints) {
        if (playerTotalPoints > cpuTotalPoints) {
            System.out.println("Player Wins!");
            this.cpuHealth -= (playerTotalPoints - cpuTotalPoints);
            return;
        }
        
        if (cpuTotalPoints > playerTotalPoints) {
            System.out.println("CPU Wins!");
            this.playerHealth -= (cpuTotalPoints - playerTotalPoints);
            return;
        }
        
        System.out.println("Draw!");
    }

    private void recordBattleHistory() {
        // 対戦結果の記録
        recordHealthHistory(playerHealth, playerHealthHistory);
        recordHealthHistory(cpuHealth, cpuHealthHistory);
    }

    private void recordHealthHistory(int health, int[] healthHistory) {
        IntStream.range(0, healthHistory.length)
         .filter(i -> healthHistory[i] == -9999)
         .findFirst()
         .ifPresent(i -> healthHistory[i] = health);
    }

    public int[] getPlayerHealthHistory() {
        return this.playerHealthHistory;
    }

    public int[] getCpuHealthHistory() {
        return this.cpuHealthHistory;
    }

    public int getPlayerHealth() {
        return this.playerHealth; 
    }

    public int getCpuHealth() {
        return this.cpuHealth;
    }

    public void setMonstersPoint(int[] monsterPoints) {
        this.monstersPoint = monsterPoints;
    }

    public void setMonsterZukan(String[] monsterNames) {
        this.monsters = monsterNames;
    }
}