# オブジェクト指向エキササイズのためのクソコード

## リファクタリング意識したポイント
以下のポイントを意識してリファクタリングを行った
- 名前を省略しないこと
- 1つのメソッドにつきインデントは1段階までにすること
- else句を使用しないようにすること




## redfantasy仕様
- Main.javaではRedfantasyクラスをnewしたあと，3秒毎にstartPhase()を呼び出し，player/cpuいずれかのHPが0以下になるまで繰り返す．
- playerMonsters/playerMonstersPoint はint型の配列で，モンスターのカード番号とmonsterPointが格納される
  - モンスターのカード番号はMain.javaのsetMonstersメソッドでmonsters配列に格納される配列のindex(0~21)が表す
- startPhase()では，下記の順に処理が行われる
  1. モンスターカードを引き，playerMonsters, playerMonstersPointをセットし，何を引いたかを表示する（CPUも同様）
  1. サイコロを振る．1の場合はモンスターポイントが半分に，6の場合はモンスターポイントが倍になる．それ以外の場合はbonuspointがサイコロの目にセットされる（CPUも同様）
  1. player/cpuの全モンスターのモンスターポイントとbonuspointを合計する
  1. cpu vs player（モンスターポイントの合計で勝負），モンスターポイントの差分を負けた方のHPから引き，HPを表示する
  1. 結果を記録する
