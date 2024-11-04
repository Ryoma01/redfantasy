import java.util.Random;

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
        for (int i = 0; i < this.playerMonsters.length; i++) {
            this.playerMonsters[i] = -1;
            this.cpuMonsters[i] = -1;
        }
        this.playerHealthHistory[0] = this.playerHealth;
        this.cpuHealthHistory[0] = this.cpuHealth;
        for (int i = 0; i < this.playerHealthHistory.length; i++) {
            this.playerHealthHistory[i] = -9999;
            this.cpuHealthHistory[i] = -9999;
        }
    }

    public void startPhase() {

        // Draw player's monster card
        int playerDrawCount = this.rnd.nextInt(this.playerMonsters.length - 2) + 3;
        System.out.println("Player Draw " + playerDrawCount + " monsters");
        for (int i = 0; i < playerDrawCount; i++) {
            int monsterIndex = this.rnd.nextInt(this.monsters.length);
            this.playerMonsters[i] = monsterIndex;
            this.playerMonstersPoint[i] = this.monstersPoint[monsterIndex];
        }

        // Draw CPU's monster card
        int cpuDrawCount = this.rnd.nextInt(this.cpuMonsters.length - 2) + 3;
        System.out.println("CPU Draw " + cpuDrawCount + " monsters");
        for (int i = 0; i < cpuDrawCount; i++) {
            int monsterIndex = this.rnd.nextInt(this.monsters.length);
            this.cpuMonsters[i] = monsterIndex;
            this.cpuMonstersPoint[i] = this.monstersPoint[monsterIndex];
        }

        System.out.println("--------------------");
        System.out.print("Player Monsters List:");
        for (int i = 0; i < this.playerMonsters.length; i++) {
            if (this.playerMonsters[i] != -1) {
                System.out.print(this.monsters[this.playerMonsters[i]] + " ");
            }
        }
        System.out.print("\nCPU Monsters List:");
        for (int i = 0; i < this.cpuMonsters.length; i++) {
            if (this.cpuMonsters[i] != -1) {
                System.out.print(this.monsters[this.cpuMonsters[i]] + " ");
            }
        }
        System.out.println("\n--------------------");
        System.out.println("Battle!");
        
        int playerDiceRoll = this.rnd.nextInt(6) + 1; // 1~6のサイコロを振る
        System.out.println("Player's Dice': " + playerDiceRoll);
        if (playerDiceRoll == 1) {
            System.out.println("失敗！すべてのモンスターポイントが半分になる");
            for (int i = 0; i < this.playerMonsters.length; i++) {
                if (this.playerMonsters[i] != -1) {
                    this.playerMonstersPoint[i] = this.playerMonstersPoint[i] / 2;
                }
            }
        } else if (playerDiceRoll == 6) {
            System.out.println("Critical！すべてのモンスターポイントが倍になる");
            for (int i = 0; i < this.playerMonsters.length; i++) {
                if (this.playerMonsters[i] != -1) {
                    this.playerMonstersPoint[i] = this.playerMonstersPoint[i] * 2;
                }
            }
        } else {
            this.playerBonusPoints = playerDiceRoll;
        }
        
        int cpuDiceRoll = this.rnd.nextInt(6) + 1; // 1~6のサイコロを振る
        System.out.println("CPU's Dice': " + cpuDiceRoll);
        if (cpuDiceRoll == 1) {
            System.out.println("失敗！すべてのモンスターポイントが半分になる");
            for (int i = 0; i < this.cpuMonsters.length; i++) {
                if (this.cpuMonsters[i] != -1) {
                    this.cpuMonstersPoint[i] = this.cpuMonstersPoint[i] / 2;
                }
            }
        } else if (cpuDiceRoll == 6) {
            System.out.println("Critical！すべてのモンスターポイントが倍になる");
            for (int i = 0; i < this.cpuMonsters.length; i++) {
                if (this.cpuMonsters[i] != -1) {
                    this.cpuMonstersPoint[i] = this.cpuMonstersPoint[i] * 2;
                }
            }
        } else {
            this.cpuBonusPoints = cpuDiceRoll;
        }

        System.out.println("--------------------");
        System.out.print("Player Monster Pointの合計:");
        int playerTotalPoints = this.playerBonusPoints;
        for (int i = 0; i < this.playerMonsters.length; i++) {
            if (this.playerMonsters[i] != -1) {
                playerTotalPoints = playerTotalPoints + this.playerMonstersPoint[i];
            }
        }
        System.out.println(playerTotalPoints);

        System.out.print("CPU Monster Pointの合計:");
        int cpuTotalPoints = this.cpuBonusPoints;
        for (int i = 0; i < this.cpuMonsters.length; i++) {
            if (this.cpuMonsters[i] != -1) {
                cpuTotalPoints = cpuTotalPoints + this.cpuMonstersPoint[i];
            }
        }
        System.out.println(cpuTotalPoints);
        System.out.println("--------------------");

        if (playerTotalPoints > cpuTotalPoints) {
            System.out.println("Player Win!");
            this.cpuHealth = this.cpuHealth - (playerTotalPoints - cpuTotalPoints);
        } else if (cpuTotalPoints > playerTotalPoints) {
            System.out.println("CPU Win!");
            this.playerHealth = this.playerHealth - (cpuTotalPoints - playerTotalPoints);
        } else if (playerTotalPoints == cpuTotalPoints) {
            System.out.println("Draw!");
        }

        System.out.println("Player HP is " + this.playerHealth);
        System.out.println("CPU HP is " + this.cpuHealth);
        
        System.out.println("--------------------");
        // 対戦結果の記録
        for (int i = 0; i < this.playerHealthHistory.length; i++) {
            if (this.playerHealthHistory[i] == -9999) {
                this.playerHealthHistory[i] = this.playerHealth;
                break;
            }
        }
        for (int i = 0; i < this.cpuHealthHistory.length; i++) {
            if (this.cpuHealthHistory[i] == -9999) {
                this.cpuHealthHistory[i] = this.cpuHealth;
                break;
            }
        }
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