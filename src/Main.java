public class Main {
    static RedFantasy redFantasyInstance = new RedFantasy();

    public static void main(String[] args) {
        initializeMonsters();

        while (true) {
            try {
                if (redFantasyInstance.getPlayerHealth() > 0 && redFantasyInstance.getCpuHealth() > 0) {
                    Thread.sleep(3000);
                    redFantasyInstance.startPhase();
                } else if (redFantasyInstance.getPlayerHealth() <= 0) {
                    System.out.println("Playerは死んでしまった");
                    break;
                } else if (redFantasyInstance.getCpuHealth() <= 0) {
                    System.out.println("CPUは死んでしまった");
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Player History:");
        for (int i = 0; i < redFantasyInstance.getPlayerHealthHistory().length && redFantasyInstance.getPlayerHealthHistory()[i] != -9999; i++) {
            System.out.print(redFantasyInstance.getPlayerHealthHistory()[i] + "\t");
        }

        System.out.println("\nCPU History:");
        for (int i = 0; i < redFantasyInstance.getCpuHealthHistory().length && redFantasyInstance.getCpuHealthHistory()[i] != -9999; i++) {
            System.out.print(redFantasyInstance.getCpuHealthHistory()[i] + "\t");
        }
    }

    public static void initializeMonsters() {
        String monsterNames[] = new String[22];
        int monsterPoints[] = new int[22];
        monsterNames[0] = "イガキン"; monsterPoints[0] = 9;
        monsterNames[1] = "ナマチュウ"; monsterPoints[1] = 3;
        monsterNames[2] = "イノウエン"; monsterPoints[2] = 1;
        monsterNames[3] = "リョージィ"; monsterPoints[3] = 2;
        monsterNames[4] = "アキモトン"; monsterPoints[4] = 5;
        monsterNames[5] = "ゴージマ"; monsterPoints[5] = 4;
        monsterNames[6] = "チュウデン"; monsterPoints[6] = 6;
        monsterNames[7] = "ヅカホン"; monsterPoints[7] = 8;
        monsterNames[8] = "ニシムラー"; monsterPoints[8] = 7;
        monsterNames[9] = "サコーデン"; monsterPoints[9] = 2;
        monsterNames[10] = "ウッチー"; monsterPoints[10] = 5;
        monsterNames[11] = "ハヤッシー"; monsterPoints[11] = 4;
        monsterNames[12] = "キーチー"; monsterPoints[12] = 3;
        monsterNames[13] = "リョクン"; monsterPoints[13] = 1;
        monsterNames[14] = "デコポン"; monsterPoints[14] = 6;
        monsterNames[15] = "カミサン"; monsterPoints[15] = 5;
        monsterNames[16] = "シスイ"; monsterPoints[16] = 1;
        monsterNames[17] = "ジョナ"; monsterPoints[17] = 7;
        monsterNames[18] = "ギダギダ"; monsterPoints[18] = 2;
        monsterNames[19] = "ミッツー"; monsterPoints[19] = 8;
        monsterNames[20] = "ゾエサン"; monsterPoints[20] = 5;
        monsterNames[21] = "キタバー"; monsterPoints[21] = 3;

        redFantasyInstance.setMonsterZukan(monsterNames);
        redFantasyInstance.setMonstersPoint(monsterPoints);
    }
}
